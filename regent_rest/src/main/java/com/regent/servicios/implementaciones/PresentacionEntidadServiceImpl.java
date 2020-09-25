package com.regent.servicios.implementaciones;


 import com.regent.dtos.PresentacionEntidadDTO;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.NotificacionCambio;
 import com.regent.negocio.Parametro;
 import com.regent.negocio.PresentacionEntidad;
 import com.regent.negocio.TipoPresentacion;
 import com.regent.negocio.Usuario;
 import com.regent.repositories.interfaces.EntidadRepository;
 import com.regent.repositories.interfaces.MotivoNotificacionRepository;
 import com.regent.repositories.interfaces.NotificacionCambioRepository;
 import com.regent.repositories.interfaces.PresentacionEntidadRepository;
 import com.regent.repositories.interfaces.ResolucionRepository;
 import com.regent.repositories.interfaces.TipoPresentacionRepository;
 import com.regent.repositories.interfaces.UsuarioRepository;
 import com.regent.servicios.interfaces.MailNotificacionService;
 import com.regent.servicios.interfaces.ParametroService;
 import com.regent.servicios.interfaces.PresentacionEntidadService;
 import java.sql.Timestamp;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collection;
 import java.util.Date;
 import java.util.concurrent.Executors;
 import java.util.concurrent.ScheduledExecutorService;
 import java.util.concurrent.TimeUnit;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class PresentacionEntidadServiceImpl
   implements PresentacionEntidadService
 {
   public static String MANDATO = "actadire";
   public static String MANDATO_VENCIDO = "Vencimiento de Mandato";
   public static String MANDATO_POR_VENCER = "Mandato por Vencer";
   
   public static String BALANCE = "balances";
   public static String BALANCE_VENCIDO = "Vencimiento de Balance";
   public static String BALANCE_POR_VENCER = "Balance por Vencer";
   
   @Autowired
   TipoPresentacionRepository tpRepo;
   
   @Autowired
   PresentacionEntidadRepository peRepo;
   
   @Autowired
   NotificacionCambioRepository nRepo;
   
   @Autowired
   MotivoNotificacionRepository mnRepo;
   @Autowired
   UsuarioRepository uRepo;
   @Autowired
   ResolucionRepository rRepo;
   @Autowired
   EntidadRepository eRepo;
   @Autowired
   ParametroService paService;
   @Autowired
   MailNotificacionService miService;
   
   public void nuevaPresentacionEntidad(Collection<String> tiposPresentaciones, Entidad e, String exp) throws ParseException {
     for (String s : tiposPresentaciones) {
       TipoPresentacion tp = this.tpRepo.findByNombreArchivo(s.split("_")[0]);
       if (tp != null) {
         PresentacionEntidad pe = new PresentacionEntidad();
         pe.setCodigoPresEnt(Integer.valueOf(this.peRepo.getMaxCodigoPresEnt().intValue() + 1));
         pe.setTipoPresentacion(tp);
         pe.setEntidad(e);
         pe.setIntimada("NO");
         pe.setIntimacion(null);
         pe.setIfGedo(exp);
         pe.setNombreArchivo(s);
         pe.setVigente("SI");
         pe.setUsuario("sistema");
         pe.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
 
         
         if ("NO".equals(tp.getVence())) {
           pe.setFechaVencimiento(null);
         } else if ("SI".equals(tp.getVence())) {
           pe.setFechaVencimiento(getFechaVencimiento(tp.getNombreArchivo(), e));
         } 
 
 
         
         PresentacionEntidad pe2 = this.peRepo.getUltimaPresentacionValidaByTipoPresentacionAndCuitEntidad(pe.getTipoPresentacion().getCodigoTipoPresentacion(), pe.getEntidad().getCuit());
         if (pe2 != null) {
           pe2.setVigente("NO");
           pe2.setUsuario("sistema");
           pe2.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           this.peRepo.save(pe2);
         } 
         
         this.peRepo.save(pe);
       } 
     } 
   }
 
   
   public Collection<PresentacionEntidadDTO> getAllPresentacionByEntidad(Integer codigoEntidad) throws ParseException {
     Collection<PresentacionEntidadDTO> presentacionesDTO = new ArrayList<>();
     Collection<PresentacionEntidad> presentaciones = this.peRepo.getAllPresentacionesByEntidad(codigoEntidad);
     
     Entidad e = eRepo.findById(codigoEntidad).orElse(new Entidad());
     
     String nombreArch = (e != null) ? ("solicitu_" + e.getCuit().toString() + ".pdf") : "";
     
     for (PresentacionEntidad p : presentaciones) {
       if (!nombreArch.equals(p.getNombreArchivo())) {
         PresentacionEntidadDTO pDTO = new PresentacionEntidadDTO();
         pDTO.setCodigoPresEnt(p.getCodigoPresEnt());
         pDTO.setCodigoTipoPresentacion(p.getTipoPresentacion().getCodigoTipoPresentacion());
         pDTO.setTipoPresentacion(p.getTipoPresentacion().getNombreTipoPresentacion());
         pDTO.setNombreArchivo(p.getNombreArchivo());
         pDTO.setValidada(Boolean.valueOf((p.getIfGedo() != null && !"".equals(p.getIfGedo()))));
         pDTO.setVigente(p.getVigente());
         pDTO.setSiglaArchivo(p.getTipoPresentacion().getNombreArchivo());
         
         String fecha = p.getActualizado();
         Boolean fechaOk = Boolean.FALSE;
         Date date = null;
         try {
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           date = format.parse(fecha);
           fechaOk = Boolean.TRUE;
         } catch (Exception exception) {}
 
         
         if (fechaOk.booleanValue()) {
           pDTO.setFechaCarga((new SimpleDateFormat("dd/MM/yyyy")).format(date));
         } else {
           pDTO.setFechaCarga(p.getActualizado().substring(0, 10));
         } 
 
         
         presentacionesDTO.add(pDTO);
       } 
     } 
     
     return presentacionesDTO;
   }
 
   
   public void confirmarValidacion(PresentacionEntidadDTO peDTO, String usuario) throws ParseException {
     PresentacionEntidad pe = peRepo.findById(peDTO.getCodigoPresEnt()).orElse(new PresentacionEntidad());
     
     if (pe != null) {
       PresentacionEntidad pe2 = this.peRepo.getUltimaPresentacionValidaByTipoPresentacionAndCuitEntidad(pe.getTipoPresentacion().getCodigoTipoPresentacion(), pe.getEntidad().getCuit());
       pe.setIfGedo(peDTO.getIfGedo());
       pe.setVigente("SI");
       
       if ("actadire".equals(peDTO.getSiglaArchivo())) {
         
         Entidad e = pe.getEntidad();
         if (peDTO.getFechaInicioMandato() != null && peDTO.getMandatoEnAnios() != null) {
           e.setFechaInicioMandato((new SimpleDateFormat("yyyy-MM-dd")).parse(peDTO.getFechaInicioMandato().trim()));
           e.setMandatoEnAnios(Integer.valueOf(peDTO.getMandatoEnAnios()));
           e.setUsuario(usuario);
           e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           this.eRepo.save(e);
 
           
           if ("SI".equals(pe.getTipoPresentacion().getVence())) {
             pe.setFechaVencimiento(getFechaVencimiento(peDTO.getSiglaArchivo(), e));
           }
         }
       
       } else if ("balances".equals(peDTO.getSiglaArchivo())) {
         
         Entidad e = pe.getEntidad();
         if (peDTO.getMesCierreDeBalance() != null) {
           e.setMesCierreDeBalance(Integer.valueOf(peDTO.getMesCierreDeBalance()));
           e.setUsuario(usuario);
           e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           this.eRepo.save(e);
 
           
           if ("SI".equals(pe.getTipoPresentacion().getVence())) {
             pe.setFechaVencimiento(getFechaVencimiento(peDTO.getSiglaArchivo(), e));
           }
         } 
       } 
       
       pe.setUsuario(usuario);
       pe.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.peRepo.save(pe);
 
 
       
       if (pe2 != null) {
         pe2.setVigente("NO");
         pe2.setUsuario("sistema");
         pe2.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         this.peRepo.save(pe2);
       } 
     } 
   }
 
   
   public void rechazarPresentacion(String codigoPresEnt, String usuario) {
     PresentacionEntidad pe = peRepo.findById(Integer.valueOf(codigoPresEnt)).orElse(new PresentacionEntidad());
     if (pe != null) {
       pe.setVigente("RE");
       pe.setIfGedo("N/A");
       pe.setUsuario(usuario);
       pe.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.peRepo.save(pe);
     } 
   }
   
   private Date getFechaVencimiento(String tipoPresentacion, Entidad e) throws ParseException {
     if ("actadire".equals(tipoPresentacion)) {
       
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(e.getFechaInicioMandato());
       calendar.add(1, e.getMandatoEnAnios().intValue());
       calendar.add(5, 1);
       
       Integer dia = Integer.valueOf(calendar.get(5));
       Integer mes = Integer.valueOf(calendar.get(2) + 1);
       Integer anio = Integer.valueOf(calendar.get(1));
       String fecha = String.valueOf(dia.toString()) + "/" + mes.toString() + "/" + anio.toString();
       
       return (new SimpleDateFormat("dd/MM/yyyy")).parse(fecha);
     } 
     if ("balances".equals(tipoPresentacion)) {
       
       Calendar calendar = Calendar.getInstance();
       Integer anio = Integer.valueOf(calendar.get(1));
       Integer mes = Integer.valueOf(calendar.get(2) + 1);
 
       
       if (mes.intValue() >= e.getMesCierreDeBalance().intValue()) {
         anio = Integer.valueOf(anio.intValue() + 1);
       }
       mes = e.getMesCierreDeBalance();
 
       
       if (mes.intValue() == 12) {
         mes = Integer.valueOf(1);
         anio = Integer.valueOf(anio.intValue() + 1);
       } else {
         mes = Integer.valueOf(mes.intValue() + 1);
       } 
       
       String fecha = "1/" + mes.toString() + "/" + anio.toString();
       
       return (new SimpleDateFormat("dd/MM/yyyy")).parse(fecha);
     } 
     return null;
   }
 
 
 
 
   
   public void programarEnvioNotificaciones() {
     ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
     execService.scheduleAtFixedRate(() -> enviarNotificaciones(), 
         
         0L, 86400000L, TimeUnit.MILLISECONDS);
   }
 
 
   
   public void enviarNotificaciones() {
     Parametro param = this.paService.findByNombreParametro("ENVIO_NOTIF (SI|NO)");
     Boolean envioNotif = (param != null && "A".equals(param.getEstado()) && "SI".equals(param.getDescripcion())) ? Boolean.TRUE : Boolean.FALSE;
     
     if (envioNotif.booleanValue()) {
       String mandatosVencidos = "";
       String balancesVencidos = "";
 
       
       Collection<PresentacionEntidad> mandatos1 = this.peRepo.getPresentacionesParaPrimeraNotificacion(MANDATO, MANDATO_POR_VENCER);
       for (PresentacionEntidad p : mandatos1) {
         if ("Alta".equals(p.getEntidad().getEstadoEntidad().getNombreEstado()) && "SI".equals(p.getEntidad().getVigente())) {
           NotificacionCambio n = new NotificacionCambio();
           n.setEntidad(p.getEntidad());
           n.setMotivoNotificacion(this.mnRepo.findByNombreMotivoNotif(MANDATO_POR_VENCER));
           n.setFechaNotificacion(new Date());
           n.setObservaciones(null);
           n.setIfGedo(null);
           n.setTri(null);
           n.setUsuario("sistema");
           n.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           
           this.nRepo.save(n);
           
           this.miService.sendPrimeraNotificacionMandato(p.getEntidad().geteMail());
         } 
       } 
 
       
       Collection<PresentacionEntidad> balances1 = this.peRepo.getPresentacionesParaPrimeraNotificacion(BALANCE, BALANCE_POR_VENCER);
       for (PresentacionEntidad p : balances1) {
         if ("Alta".equals(p.getEntidad().getEstadoEntidad().getNombreEstado()) && "SI".equals(p.getEntidad().getVigente())) {
           NotificacionCambio n = new NotificacionCambio();
           n.setEntidad(p.getEntidad());
           n.setMotivoNotificacion(this.mnRepo.findByNombreMotivoNotif(BALANCE_POR_VENCER));
           n.setFechaNotificacion(new Date());
           n.setObservaciones(null);
           n.setIfGedo(null);
           n.setTri(null);
           n.setUsuario("sistema");
           n.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           
           this.nRepo.save(n);
           
           this.miService.sendPrimeraNotificacionBalance(p.getEntidad().geteMail());
         } 
       } 
 
       
       Collection<PresentacionEntidad> mandatos2 = this.peRepo.getPresentacionesParaSegundaNotificacionMandato(MANDATO, MANDATO_VENCIDO);
       for (PresentacionEntidad p : mandatos2) {
         if ("Alta".equals(p.getEntidad().getEstadoEntidad().getNombreEstado()) && "SI".equals(p.getEntidad().getVigente())) {
           NotificacionCambio n = new NotificacionCambio();
           n.setEntidad(p.getEntidad());
           n.setMotivoNotificacion(this.mnRepo.findByNombreMotivoNotif(MANDATO_VENCIDO));
           n.setFechaNotificacion(new Date());
           n.setObservaciones(null);
           n.setIfGedo(null);
           n.setTri(null);
           n.setUsuario("sistema");
           n.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           
           this.nRepo.save(n);
           
           this.miService.sendSegundaNotificacionMandato(p.getEntidad().geteMail());
           mandatosVencidos = String.valueOf(mandatosVencidos) + "Entidad " + p.getEntidad().getDenominacion() + " (" + p.getEntidad().getCuit().toString() + ")<br>";
         } 
       } 
 
       
       Collection<PresentacionEntidad> balances2 = this.peRepo.getPresentacionesParaSegundaNotificacionBalance(BALANCE, BALANCE_VENCIDO);
       for (PresentacionEntidad p : balances2) {
         if ("Alta".equals(p.getEntidad().getEstadoEntidad().getNombreEstado()) && "SI".equals(p.getEntidad().getVigente())) {
           NotificacionCambio n = new NotificacionCambio();
           n.setEntidad(p.getEntidad());
           n.setMotivoNotificacion(this.mnRepo.findByNombreMotivoNotif(BALANCE_VENCIDO));
           n.setFechaNotificacion(new Date());
           n.setObservaciones(null);
           n.setIfGedo(null);
           n.setTri(null);
           n.setUsuario("sistema");
           n.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           
           this.nRepo.save(n);
           
           this.miService.sendSegundaNotificacionBalance(p.getEntidad().geteMail());
           balancesVencidos = String.valueOf(balancesVencidos) + "Entidad " + p.getEntidad().getDenominacion() + " (" + p.getEntidad().getCuit().toString() + ")<br>";
         } 
       } 
 
       
       if (mandatosVencidos.length() > 0 || balancesVencidos.length() > 0) {
         Collection<Usuario> usuarios = this.uRepo.getUsuariosRolRegistro();
         for (Usuario u : usuarios)
           this.miService.sendNotificacionesDelDiaARegistro(mandatosVencidos, balancesVencidos, u); 
       } 
     } 
   }
 }