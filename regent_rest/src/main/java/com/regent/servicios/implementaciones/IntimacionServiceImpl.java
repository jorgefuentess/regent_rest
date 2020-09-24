package com.regent.servicios.implementaciones;

import com.regent.dtos.IntimacionDTO;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.Intimacion;
 import com.regent.negocio.Parametro;
 import com.regent.negocio.Usuario;
 import com.regent.repositories.interfaces.EntidadRepository;
 import com.regent.repositories.interfaces.IntimacionRepository;
 import com.regent.repositories.interfaces.UsuarioRepository;
 import com.regent.servicios.interfaces.EntidadService;
 import com.regent.servicios.interfaces.IntimacionService;
 import com.regent.servicios.interfaces.MailService;
 import com.regent.servicios.interfaces.ParametroService;
 import com.regent.servicios.interfaces.UsuarioService;
 import java.sql.Timestamp;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Collection;
 import java.util.Date;
 import java.util.concurrent.Executors;
 import java.util.concurrent.ScheduledExecutorService;
 import java.util.concurrent.TimeUnit;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 @Service
 public class IntimacionServiceImpl
   implements IntimacionService
 {
   public static String ALTA = "Alta";
   public static String SUSP = "Suspendida";
   public static String BAJA_O = "Baja de Oficio";
   
   @Autowired
   IntimacionRepository iRepo;
   
   @Autowired
   EntidadRepository eRepo;
   
   @Autowired
   UsuarioRepository uRepo;
   @Autowired
   EntidadService eService;
   @Autowired
   MailService mService;
   @Autowired
   UsuarioService uService;
   @Autowired
   ParametroService paService;
   
   public IntimacionDTO getIntimacion(String codigoEntidad) {
     Intimacion i = this.iRepo.getIntimacionVigenteByCodigoEntidad(Integer.valueOf(codigoEntidad));
     
     IntimacionDTO iDTO = new IntimacionDTO();
     if (i != null) {
       iDTO.setAvisoRecibo(i.getAvisoRecibo());
       iDTO.setCodigoIntimacion(i.getCodigoIntimacion().toString());
       iDTO.setCumplida(i.getCumplida());
       iDTO.setDetalle(i.getDetalle());
       iDTO.setEntidad(i.getEntidad().getCodigoEntidad().toString());
       iDTO.setFechaCumplimiento(String.valueOf(i.getFechaCumplimiento()));
       iDTO.setFechaEnvio(String.valueOf(i.getFechaEnvio()));
       iDTO.setFechaRecepcion(String.valueOf(i.getFechaRecepcion()));
       iDTO.setNotificacionPositiva(i.getNotificacionPositiva());
       iDTO.setNumeroCartaDocumento(i.getNumeroCartaDocumento());
       iDTO.setNumeroDeIntimacion(i.getNumeroDeIntimacion().toString());
       iDTO.setNumeroExpediente(i.getNumeroExpediente());
     } 
 
     
     return iDTO;
   }
 
   
   public void nuevaIntimacion(IntimacionDTO iDTO, String usuario) throws ParseException {
     Intimacion i = new Intimacion();
     
     Entidad e = (Entidad)this.eRepo.findOne(Integer.valueOf(iDTO.getEntidad()));
     if (e != null && ALTA.equals(e.getEstadoEntidad().getNombreEstado())) {
       i.setEntidad(e);
       
       i.setNumeroCartaDocumento(iDTO.getNumeroCartaDocumento().trim());
       i.setFechaEnvio((new SimpleDateFormat("yyyy-MM-dd")).parse(iDTO.getFechaEnvio().trim()));
       i.setDetalle(iDTO.getDetalle().trim());
       i.setNumeroDeIntimacion(Integer.valueOf(1));
       
       i.setEstado("A");
       i.setUsuario(usuario);
       i.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.iRepo.save(i);
 
       
       this.eService.cambiarEstadoEntidad(e, SUSP, usuario);
     } 
   }
 
   
   public void updateIntimacion(IntimacionDTO iDTO, String usuario) throws ParseException {
     Intimacion i = (Intimacion)this.iRepo.findOne(Integer.valueOf(iDTO.getCodigoIntimacion()));
     
     if (i != null && SUSP.equals(i.getEntidad().getEstadoEntidad().getNombreEstado()) && 
       "A".equals(i.getEstado())) {
       
       i.setNumeroCartaDocumento(iDTO.getNumeroCartaDocumento().trim());
       i.setFechaEnvio((new SimpleDateFormat("yyyy-MM-dd")).parse(iDTO.getFechaEnvio().trim()));
       i.setDetalle(iDTO.getDetalle().trim());
       
       i.setFechaRecepcion((new SimpleDateFormat("yyyy-MM-dd")).parse(iDTO.getFechaRecepcion().trim()));
       i.setAvisoRecibo(iDTO.getAvisoRecibo().trim());
       i.setNotificacionPositiva("true".equals(iDTO.getNotificacionPositiva().trim()) ? "SI" : "NO");
       
       i.setUsuario(usuario);
       i.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.iRepo.save(i);
     } 
   }
 
   
   public void cerrarIntimacion(IntimacionDTO iDTO, String usuario) throws ParseException {
     Intimacion i = (Intimacion)this.iRepo.findOne(Integer.valueOf(iDTO.getCodigoIntimacion()));
     
     if (i != null && SUSP.equals(i.getEntidad().getEstadoEntidad().getNombreEstado()) && 
       "A".equals(i.getEstado())) {
       
       i.setNumeroCartaDocumento(iDTO.getNumeroCartaDocumento().trim());
       i.setFechaEnvio((new SimpleDateFormat("yyyy-MM-dd")).parse(iDTO.getFechaEnvio().trim()));
       i.setDetalle(iDTO.getDetalle().trim());
       
       i.setFechaRecepcion((new SimpleDateFormat("yyyy-MM-dd")).parse(iDTO.getFechaRecepcion().trim()));
       i.setAvisoRecibo(iDTO.getAvisoRecibo().trim());
       i.setNotificacionPositiva("true".equals(iDTO.getNotificacionPositiva().trim()) ? "SI" : "NO");
       
       i.setNotificacionPositiva("true".equals(iDTO.getNotificacionPositiva().trim()) ? "SI" : "NO");
       i.setCumplida("true".equals(iDTO.getCumplida().trim()) ? "SI" : "NO");
       if ("SI".equals(i.getCumplida())) {
         i.setFechaCumplimiento((new SimpleDateFormat("yyyy-MM-dd")).parse(iDTO.getFechaCumplimiento().trim()));
       } else {
         i.setNumeroExpediente(iDTO.getNumeroExpediente().trim());
       } 
       
       i.setEstado("I");
       i.setUsuario(usuario);
       i.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.iRepo.save(i);
       
       if ("SI".equals(i.getCumplida())) {
         
         this.eService.cambiarEstadoEntidad(i.getEntidad(), ALTA, usuario);
       } else {
         
         this.eService.cambiarEstadoEntidad(i.getEntidad(), BAJA_O, usuario);
         this.uService.inhabilitarUsuario(i.getEntidad().getCuit().toString(), usuario);
       } 
     } 
   }
 
 
 
 
   
   public void programarAvisosPorIntimacionVencida() {
     ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
     execService.scheduleAtFixedRate(() -> enviarAvisosPorIntimacionVencida(), 
         
         0L, 86400000L, TimeUnit.MILLISECONDS);
   }
 
   
   private void enviarAvisosPorIntimacionVencida() {
     Parametro param = this.paService.findByNombreParametro("ENVIO_NOTIF (SI|NO)");
     Boolean envioNotif = (param != null && "A".equals(param.getEstado()) && "SI".equals(param.getDescripcion())) ? Boolean.TRUE : Boolean.FALSE;
     
     if (envioNotif.booleanValue()) {
       Date hoy = new Date();
       
       Calendar cal = Calendar.getInstance();
       cal.setTime(hoy);
       
       cal.add(6, -15);
       
       int year = cal.get(1);
       int month = cal.get(2);
       int day = cal.get(5);
       
       String fecha = String.valueOf(day) + "/" + (month + 1) + "/" + year;
       
       Collection<Intimacion> intimaciones = this.iRepo.getIntimacionesParaAviso(fecha);
 
       
       if (intimaciones.size() > 0) {
         Collection<Usuario> usuarios = this.uRepo.getUsuariosRolRegistro();
         for (Usuario u : usuarios)
           this.mService.sendAvisosIntimacionARegistro(intimaciones, u); 
       } 
     } 
   }
 }

