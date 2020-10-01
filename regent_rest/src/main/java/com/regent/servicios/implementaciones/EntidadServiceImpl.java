package com.regent.servicios.implementaciones;

import com.regent.dtos.EntidadDTO;
 import com.regent.dtos.EntidadInfoDTO;
 import com.regent.dtos.EntidadLiteDTO;
 import com.regent.dtos.SolicitudBajaDTO;
 import com.regent.dtos.TipoPresentacionDTO;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.EstadoEntidad;
 import com.regent.negocio.InstitucionHabilitante;
 import com.regent.negocio.Parametro;
 import com.regent.negocio.PresentacionEntidad;
 import com.regent.negocio.Resolucion;
 import com.regent.negocio.SolicitudBaja;
 import com.regent.negocio.SolicitudEntidad;
 import com.regent.negocio.TipoEntidad;
 import com.regent.negocio.TipoPresentacion;
 import com.regent.negocio.Usuario;
 import com.regent.pdf.PdfGenerator;
 import com.regent.repositories.interfaces.CodigoDescuentoRepository;
 import com.regent.repositories.interfaces.EntidadRepository;
 import com.regent.repositories.interfaces.EstadoEntidadRepository;
 import com.regent.repositories.interfaces.InstitucionHabilitanteRepository;
 import com.regent.repositories.interfaces.PresentacionEntidadRepository;
 import com.regent.repositories.interfaces.PresentacionPorTipoEntidadRepository;
 import com.regent.repositories.interfaces.ResolucionRepository;
 import com.regent.repositories.interfaces.SolicitudBajaRepository;
 import com.regent.repositories.interfaces.TipoEntidadRepository;
 import com.regent.repositories.interfaces.TipoPresentacionRepository;
 import com.regent.repositories.interfaces.UsuarioRepository;
 import com.regent.servicios.interfaces.EntidadService;
 import com.regent.servicios.interfaces.MailService;
 import com.regent.servicios.interfaces.ParametroService;
 import com.regent.util.Config;
 import java.io.File;
 import java.io.IOException;
 import java.sql.Timestamp;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collection;
 import java.util.Date;
 import javax.servlet.http.HttpServletRequest;
 import org.dom4j.DocumentException;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.web.multipart.MultipartFile;
 
 
 
 
 @Service
 public class EntidadServiceImpl
   implements EntidadService
 {
   public static String ALTA = "Alta";
   public static String SUSP = "Suspendida";
   public static String BAJA = "Baja";
 
   
   public static String MEMBRETE_ANIO = "MEMBRETE_ANIO";
   public static String MEMBRETE_ORGANISMO = "MEMBRETE_ORGANISMO";
   public static String SOLICITUD_DECRETO = "NRO_DECRETO";
 
   
   public static String PATH = "/home/registro";
   
   @Autowired
   EntidadRepository eRepo;
   
   @Autowired
   EstadoEntidadRepository eeRepo;
   
   @Autowired
   TipoEntidadRepository tRepo;
   
   @Autowired
   InstitucionHabilitanteRepository ihRepo;
   
   @Autowired
   UsuarioRepository uRepo;
   
   @Autowired
   CodigoDescuentoRepository cRepo;
   
   @Autowired
   PresentacionPorTipoEntidadRepository ppteRepo;
   
   @Autowired
   TipoPresentacionRepository tpRepo;
   
   @Autowired
   PresentacionEntidadRepository peRepo;
   @Autowired
   ResolucionRepository rRepo;
   @Autowired
   SolicitudBajaRepository sbRepo;
   @Autowired
   MailService mService;
   @Autowired
   ParametroService paService;
   
   public Collection<EntidadLiteDTO> getEntidades() {
     Collection<Object[]> entidades = this.eRepo.getAllEntidad();
     Collection<EntidadLiteDTO> entidadesDTO = new ArrayList<>();
     for (Object[] e : entidades) {
       EntidadLiteDTO eDTO = new EntidadLiteDTO(String.valueOf(e[0]), (String)e[1], String.valueOf(e[2]));
       entidadesDTO.add(eDTO);
     } 
     return entidadesDTO;
   }
 
 
 
 
 
 
   
   public Collection<EntidadDTO> getAllEntidades() {
     Collection<Entidad> entidades = this.eRepo.getAllEntidadCompleta();
     Collection<EntidadDTO> entidadesDTO = new ArrayList<>();
     for (Entidad e : entidades) {
       EntidadDTO eDTO = new EntidadDTO();
       eDTO.setCodigoEntidad(String.valueOf(e.getCodigoEntidad()));
       eDTO.setDenominacion(e.getDenominacion());
       eDTO.setCodigo_tipo_entidad(String.valueOf(e.getTipoEntidad().getCodigoTipoEntidad()));
       eDTO.setEstadoEntidad(String.valueOf(e.getEstadoEntidad().getNombreEstado()));
       eDTO.setSolicitante(e.getSolicitante());
       eDTO.setCaracterSolicitante(e.getCaracterSolicitante());
       eDTO.setInstrumentoPersoneria(e.getInstrumentoPersoneria());
       eDTO.seteMail(e.geteMail());
       eDTO.setTelefono(e.getTelefono());
       eDTO.setCodigo_institucion_habilitante(String.valueOf(e.getInstitucionHabilitante().getCodigoInstitucionHabilitante()));
       eDTO.setCuit(String.valueOf(e.getCuit()));
       eDTO.setNroRegHabilitante(e.getNroRegHabilitante());
       eDTO.setDomicilioLegal(e.getDomicilioLegal());
       eDTO.setLocalidad(e.getLocalidad());
       eDTO.setCodigoPostal(e.getCodigoPostal());
       eDTO.setDomicilio_especial(e.getDomicilio_especial());
       eDTO.setDomicilioComercial(e.getDomicilioComercial());
       eDTO.setTelefonoComercial(e.getTelefonoComercial());
       eDTO.setPaginaWeb(e.getPaginaWeb());
       eDTO.setMesCierreDeBalance(String.valueOf(e.getMesCierreDeBalance()));
       eDTO.setFechaInicioMandato(String.valueOf(e.getFechaInicioMandato()));
       eDTO.setMandatoEnAnios(String.valueOf(e.getMandatoEnAnios()));
       eDTO.setObservaciones(e.getObservaciones());
       eDTO.setVigente(e.getVigente());
       
       entidadesDTO.add(eDTO);
     } 
     return entidadesDTO;
   }
 
 
 
 
 
   
   public Collection<EntidadInfoDTO> getInfoEntidades(String o) {
     Collection<Entidad> entidades = new ArrayList<>();
     if ("v".equals(o)) {
       entidades = this.eRepo.getEntidadVigenteForPublico();
     } else {
       entidades = this.eRepo.getEntidadBajaForPublico();
     } 
     
     Collection<EntidadInfoDTO> entidadesDTO = new ArrayList<>();
     for (Entidad e : entidades) {
       EntidadInfoDTO eDTO = new EntidadInfoDTO();
       eDTO.setCodigoEntidad(String.valueOf(e.getCodigoEntidad()));
       eDTO.setDenominacion(e.getDenominacion());
       eDTO.setTipo_entidad(e.getTipoEntidad().getNombreTipoEntidad());
       eDTO.setTelefono(e.getTelefonoComercial());
       eDTO.setCuit(String.valueOf(e.getCuit()));
       eDTO.setLocalidad(e.getLocalidad());
       eDTO.setCodigoPostal(e.getCodigoPostal());
       eDTO.setDomicilio_especial(e.getDomicilioComercial());
       eDTO.setPaginaWeb(e.getPaginaWeb());
       if ("v".equals(o)) {
         eDTO.setCodigosDescuento(this.cRepo.getDescuentosByEntidad(e.getCodigoEntidad()));
       } else {
         eDTO.setEstado(SUSP.equals(e.getEstadoEntidad().getNombreEstado()) ? SUSP : "Baja");
       } 
       
       BAJA.equals(eDTO.getEstado());
 
 
 
       
       SUSP.equals(eDTO.getEstado());
 
 
 
       
       entidadesDTO.add(eDTO);
     } 
     return entidadesDTO;
   }
 
 
 
 
 
   
   public EntidadDTO getEntidadByCuit(String cuit) {
     Entidad e = this.eRepo.findByCuit(Long.valueOf(cuit));
     
     EntidadDTO eDTO = new EntidadDTO();
     eDTO.setCodigoEntidad(String.valueOf(e.getCodigoEntidad()));
     eDTO.setDenominacion(e.getDenominacion());
     eDTO.setCodigo_tipo_entidad(e.getTipoEntidad().getNombreTipoEntidad());
     eDTO.setEstadoEntidad(String.valueOf(e.getEstadoEntidad().getNombreEstado()));
     eDTO.setSolicitante(e.getSolicitante());
     eDTO.setCaracterSolicitante(e.getCaracterSolicitante());
     eDTO.setInstrumentoPersoneria(e.getInstrumentoPersoneria());
     eDTO.seteMail(e.geteMail());
     eDTO.setTelefono(e.getTelefono());
     eDTO.setCodigo_institucion_habilitante(e.getInstitucionHabilitante().getNombreInstitucionHabilitante());
     eDTO.setCuit(String.valueOf(e.getCuit()));
     eDTO.setNroRegHabilitante(e.getNroRegHabilitante());
     eDTO.setDomicilioLegal(e.getDomicilioLegal());
     eDTO.setLocalidad(e.getLocalidad());
     eDTO.setCodigoPostal(e.getCodigoPostal());
     eDTO.setDomicilio_especial(e.getDomicilio_especial());
     eDTO.setMesCierreDeBalance(String.valueOf(e.getMesCierreDeBalance()));
     eDTO.setFechaInicioMandato(String.valueOf(e.getFechaInicioMandato()));
     eDTO.setMandatoEnAnios(String.valueOf(e.getMandatoEnAnios()));
     eDTO.setDomicilioComercial(e.getDomicilioComercial());
     eDTO.setTelefonoComercial(e.getTelefonoComercial());
     eDTO.setPaginaWeb(e.getPaginaWeb());
     
     Collection<Object[]> codigos = this.cRepo.getDescuentosWithCodigoByEntidad(e.getCodigoEntidad());
     Collection<String> codigosS = new ArrayList<>();
     for (Object[] o : codigos) {
       String retorno = "";
       retorno = String.valueOf(retorno) + (String)o[0];
       retorno = String.valueOf(retorno) + " (";
       retorno = String.valueOf(retorno) + String.valueOf(o[1]);
       retorno = String.valueOf(retorno) + ")";
       
       codigosS.add(retorno);
     } 
     eDTO.setCodigosDescuento(codigosS);
     
     return eDTO;
   }
 
   
   public Entidad findEntidadByCuit(String cuit) {
     Entidad e = this.eRepo.findByCuit(Long.valueOf(cuit));
     return e;
   }
   
   public void nuevaEntidad(EntidadDTO eDTO, String usuario) throws ParseException {
     Entidad e = new Entidad();
     
     e.setDenominacion(eDTO.getDenominacion().trim());
     e.setTipoEntidad(tRepo.findOne(Integer.valueOf(eDTO.getCodigo_tipo_entidad().trim())));
     e.setSolicitante(eDTO.getSolicitante().trim());
     e.setCaracterSolicitante(eDTO.getCaracterSolicitante().trim());
     e.setInstrumentoPersoneria(eDTO.getInstrumentoPersoneria().trim());
     e.seteMail(eDTO.geteMail().trim());
     e.setTelefono(!"".equals(eDTO.getTelefono().trim()) ? eDTO.getTelefono().trim() : "No relevado");
     e.setInstitucionHabilitante(ihRepo.findOne(Integer.valueOf(eDTO.getCodigo_institucion_habilitante().trim())));
     e.setCuit(Long.valueOf(eDTO.getCuit().trim()));
     e.setNroRegHabilitante(eDTO.getNroRegHabilitante().trim());
     e.setDomicilioLegal(eDTO.getDomicilioLegal().trim());
     e.setLocalidad(eDTO.getLocalidad().trim());
     e.setCodigoPostal(eDTO.getCodigoPostal().trim());
     e.setDomicilio_especial(eDTO.getDomicilio_especial().trim());
     e.setDomicilioComercial(eDTO.getDomicilioComercial());
     e.setTelefonoComercial(eDTO.getTelefonoComercial());
     e.setPaginaWeb(eDTO.getPaginaWeb());
     e.setMesCierreDeBalance(Integer.valueOf(eDTO.getMesCierreDeBalance().trim()));
     e.setFechaInicioMandato((new SimpleDateFormat("yyyy-MM-dd")).parse(eDTO.getFechaInicioMandato().trim()));
     e.setMandatoEnAnios(Integer.valueOf(eDTO.getMandatoEnAnios().trim()));
     e.setObservaciones(eDTO.getObservaciones().trim());
     e.setEstado("A");
     e.setEstadoEntidad(this.eeRepo.findByNombreEstado(ALTA));
     e.setVigente("SI");
     e.setUsuario(usuario);
     e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
 
     
     e.setCUID(generarCUID());
     
     this.eRepo.save(e);
   }
 
 
 
 
 
 
 
   
   private Integer generarCUID() {
     return Integer.valueOf(this.eRepo.getMaxCUID().intValue() + 1);
   }
 
 
   
   public Entidad nuevaEntidad(SolicitudEntidad se, Integer mesCierre, String fechaInicio, Integer mandato, String observaciones) throws ParseException {
     Entidad e = new Entidad();
     
     e.setDenominacion(se.getDenominacion().trim());
     e.setTipoEntidad(se.getTipoEntidad());
     e.setSolicitante(se.getSolicitante().trim());
     e.setCaracterSolicitante(se.getCaracterSolicitante().trim());
     e.setInstrumentoPersoneria(se.getInstrumentoPersoneria().trim());
     e.seteMail(se.geteMail().trim());
     e.setTelefono(!"".equals(se.getTelefono().trim()) ? se.getTelefono().trim() : "No relevado");
     e.setInstitucionHabilitante(se.getInstitucionHabilitante());
     e.setCuit(se.getCuit());
     e.setNroRegHabilitante(se.getNroRegHabilitante().trim());
     e.setDomicilioLegal(se.getDomicilioLegal().trim());
     e.setLocalidad(se.getLocalidad().trim());
     e.setCodigoPostal(se.getCodigoPostal().trim());
     e.setDomicilio_especial(se.getDomicilioEspecial().trim());
     e.setDomicilioComercial(se.getDomicilioComercial());
     e.setTelefonoComercial(se.getTelefonoComercial());
     e.setPaginaWeb(se.getPaginaWeb());
     e.setMesCierreDeBalance(mesCierre);
     e.setFechaInicioMandato((new SimpleDateFormat("yyyy-MM-dd")).parse(fechaInicio.trim()));
     e.setMandatoEnAnios(mandato);
     e.setObservaciones(observaciones);
     e.setEstado("A");
     e.setEstadoEntidad(this.eeRepo.findByNombreEstado(ALTA));
     e.setVigente("NO");
     e.setUsuario("sistema");
     e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     e.setCUID(generarCUID());
     
     this.eRepo.save(e);
     
     return e;
   }
 
 
 
 
 
 
   
   public void updateEntidad(EntidadDTO eDTO, String usuario) throws ParseException {
     Usuario u = this.uRepo.findByNombreUsuario(usuario);
     if (u != null && !"ENTIDAD".equals(u.getRol().getNombreRol())) {
       
       Boolean cambio = Boolean.FALSE;
       Entidad e = eRepo.findOne(Integer.valueOf(eDTO.getCodigoEntidad()));
       
       if (!eDTO.getDenominacion().trim().equals(e.getDenominacion())) {
         e.setDenominacion(eDTO.getDenominacion().trim());
         cambio = Boolean.TRUE;
       } 
       if (Integer.valueOf(eDTO.getCodigo_tipo_entidad().trim()) != e.getTipoEntidad().getCodigoTipoEntidad()) {
         e.setTipoEntidad(tRepo.findOne(Integer.valueOf(eDTO.getCodigo_tipo_entidad().trim())));
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getSolicitante().trim().equals(e.getSolicitante())) {
         e.setSolicitante(eDTO.getSolicitante().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getCaracterSolicitante().trim().equals(e.getCaracterSolicitante())) {
         e.setCaracterSolicitante(eDTO.getCaracterSolicitante().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getInstrumentoPersoneria().trim().equals(e.getInstrumentoPersoneria())) {
         e.setInstrumentoPersoneria(eDTO.getInstrumentoPersoneria().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.geteMail().trim().equals(e.geteMail())) {
         e.seteMail(eDTO.geteMail().trim());
         cambio = Boolean.TRUE;
       } 
       if ("".equals(eDTO.getTelefono().trim())) {
         eDTO.setTelefono("No relevado");
       }
       if (!eDTO.getTelefono().trim().equals(e.getTelefono())) {
         e.setTelefono(eDTO.getTelefono().trim());
         cambio = Boolean.TRUE;
       } 
       if (Integer.valueOf(eDTO.getCodigo_institucion_habilitante().trim()) != e.getInstitucionHabilitante().getCodigoInstitucionHabilitante()) {
         e.setInstitucionHabilitante(ihRepo.findOne(Integer.valueOf(eDTO.getCodigo_institucion_habilitante().trim())));
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getCuit().trim().equals(e.getCuit().toString())) {
         e.setCuit(Long.valueOf(eDTO.getCuit().trim()));
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getNroRegHabilitante().trim().equals(e.getNroRegHabilitante())) {
         e.setNroRegHabilitante(eDTO.getNroRegHabilitante().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getDomicilioLegal().trim().equals(e.getDomicilioLegal())) {
         e.setDomicilioLegal(eDTO.getDomicilioLegal().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getLocalidad().trim().equals(e.getLocalidad())) {
         e.setLocalidad(eDTO.getLocalidad().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getCodigoPostal().trim().equals(e.getCodigoPostal())) {
         e.setCodigoPostal(eDTO.getCodigoPostal().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getDomicilio_especial().trim().equals(e.getDomicilio_especial())) {
         e.setDomicilio_especial(eDTO.getDomicilio_especial().trim());
         cambio = Boolean.TRUE;
       } 
       if (Integer.valueOf(eDTO.getMesCierreDeBalance().trim()) != e.getMesCierreDeBalance()) {
         e.setMesCierreDeBalance(Integer.valueOf(eDTO.getMesCierreDeBalance().trim()));
         cambio = Boolean.TRUE;
       } 
       if (!(new SimpleDateFormat("yyyy-MM-dd")).parse(eDTO.getFechaInicioMandato().trim()).equals((new SimpleDateFormat("yyyy-MM-dd")).parse(e.getFechaInicioMandato().toString()))) {
         e.setFechaInicioMandato((new SimpleDateFormat("yyyy-MM-dd")).parse(eDTO.getFechaInicioMandato().trim()));
         cambio = Boolean.TRUE;
       } 
       if (Integer.valueOf(eDTO.getMandatoEnAnios().trim()) != e.getMandatoEnAnios()) {
         e.setMandatoEnAnios(Integer.valueOf(eDTO.getMandatoEnAnios().trim()));
         cambio = Boolean.TRUE;
       } 
       if (!compararString(eDTO.getObservaciones(), e.getObservaciones()).booleanValue()) {
         e.setObservaciones(eDTO.getObservaciones().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getDomicilioComercial().trim().equals(e.getDomicilioComercial())) {
         e.setDomicilioComercial(eDTO.getDomicilioComercial().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getTelefonoComercial().trim().equals(e.getTelefonoComercial())) {
         e.setTelefonoComercial(eDTO.getTelefonoComercial().trim());
         cambio = Boolean.TRUE;
       } 
       if (!eDTO.getPaginaWeb().trim().equals(e.getPaginaWeb())) {
         e.setPaginaWeb(eDTO.getPaginaWeb().trim());
         cambio = Boolean.TRUE;
       } 
       
       if (cambio == Boolean.TRUE) {
         e.setUsuario(usuario);
         e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         this.eRepo.save(e);
       } 
     } else {
       
       Boolean cambio = Boolean.FALSE;
       Entidad e = this.eRepo.findByCuit(Long.valueOf(usuario));
       
       if ("".equals(eDTO.getTelefono().trim())) {
         eDTO.setTelefono("No relevado");
       }
       if (!eDTO.getTelefono().trim().equals(e.getTelefono())) {
         e.setTelefono(eDTO.getTelefono().trim());
         cambio = Boolean.TRUE;
       } 
       
       if ("".equals(eDTO.getDomicilioLegal().trim())) {
         eDTO.setDomicilioLegal("No relevado");
       }
       if (!eDTO.getDomicilioLegal().trim().equals(e.getDomicilioLegal())) {
         e.setDomicilioLegal(eDTO.getDomicilioLegal().trim());
         cambio = Boolean.TRUE;
       } 
       
       if (!eDTO.geteMail().trim().equals(e.geteMail())) {
         e.seteMail(eDTO.geteMail().trim());
         cambio = Boolean.TRUE;
       } 
       if (eDTO.getDomicilioComercial() != null && !eDTO.getDomicilioComercial().trim().equals(e.getDomicilioComercial())) {
         e.setDomicilioComercial(eDTO.getDomicilioComercial().trim());
         cambio = Boolean.TRUE;
       } 
       if (eDTO.getTelefonoComercial() != null && !eDTO.getTelefonoComercial().trim().equals(e.getTelefonoComercial())) {
         e.setTelefonoComercial(eDTO.getTelefonoComercial().trim());
         cambio = Boolean.TRUE;
       } 
       if (eDTO.getPaginaWeb() != null && !eDTO.getPaginaWeb().trim().equals(e.getPaginaWeb())) {
         e.setPaginaWeb(eDTO.getPaginaWeb().trim());
         cambio = Boolean.TRUE;
       } 
       
       if (cambio == Boolean.TRUE) {
         e.setUsuario(usuario);
         e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         this.eRepo.save(e);
       } 
     } 
   }
 
   
   private Boolean compararString(String str1, String str2) {
     return (!str1.equals(str2) && (!str1.equals("") || str2 != null) && (!str2.equals("") || str1 != null)) ? Boolean.valueOf(false) : Boolean.valueOf(true);
   }
   
   public Collection<TipoPresentacionDTO> getArchivosForActualizacion(String usuario) throws ParseException {
     Collection<TipoPresentacionDTO> tiposPresentacionDTO = new ArrayList<>();
     Collection<Object[]> tiposPresentacion = this.ppteRepo.getPresentacionesForEntidad(Long.valueOf(usuario));
     for (Object[] tp : tiposPresentacion) {
       if (!"solicitu".equals(tp[2])) {
         TipoPresentacionDTO tpDTO = new TipoPresentacionDTO();
         tpDTO.setCodigoTipoPresentacion(String.valueOf(tp[0]));
         tpDTO.setTipoPresentacion((String)tp[1]);
         tpDTO.setNombreArchivo((String)tp[2]);
         
         String fecha = this.peRepo.getFechaUltimaCargaValidaByTipoPresentacionAndCuitEntidad(Integer.valueOf(tpDTO.getCodigoTipoPresentacion()), Long.valueOf(usuario));
         if (fecha != null && !"".equals(fecha)) {
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           Date date = format.parse(fecha);
           tpDTO.setFechaUltimaCarga((new SimpleDateFormat("dd/MM/yyyy")).format(date));
         } 
         tiposPresentacionDTO.add(tpDTO);
       } 
     } 
     return tiposPresentacionDTO;
   }
 
 
   
   public String enviarActualizacion(MultipartFile[] files, String usuario, HttpServletRequest request) {
     Boolean archivoCargado = Boolean.FALSE;
     
     try {
       String uploadsDir = String.valueOf(PATH) + "/pdf/presentaciones_" + usuario + "/";
       if (Config.getLocal().booleanValue()) {
         uploadsDir = request.getServletContext().getRealPath(uploadsDir);
       }
       if (!(new File(uploadsDir)).exists())
         (new File(uploadsDir)).mkdir();  byte b; int i;
       MultipartFile[] arrayOfMultipartFile;
       for (i = (arrayOfMultipartFile = files).length, b = 0; b < i; ) { MultipartFile file = arrayOfMultipartFile[b];
         String orgName = file.getOriginalFilename();
         String codigoTipoPresentacion = orgName.split("#")[0];
         String nombre = orgName.split("#")[1];
         
         if (!file.getOriginalFilename().contains(".pdf")) {
           return "EXTENSION";
         }
         
         TipoPresentacion tp = tpRepo.findOne(Integer.valueOf(codigoTipoPresentacion));
         if (!nombre.contains(tp.getNombreArchivo()) || !nombre.contains(usuario)) {
           return "NOMBRE_ARCHIVO";
         }
         String periodo = nombre.split("_")[2].replace(".pdf", "");
         if ((nombre.split("_")).length < 3 || periodo.length() != 6 || Integer.valueOf(periodo).intValue() < 201801) {
           return "NOMBRE_ARCHIVO";
         }
 
 
         
         if (this.peRepo.findByNombreArchivo(nombre) != null) {
           return nombre;
         }
 
 
         
         String filePath = String.valueOf(uploadsDir) + nombre;
         File dest = new File(filePath);
         file.transferTo(dest);
 
         
         TipoPresentacion tipoPresentacion1 = tpRepo.findOne(Integer.valueOf(codigoTipoPresentacion));
         Entidad e = this.eRepo.findByCuit(Long.valueOf(usuario));
         if (tipoPresentacion1 != null) {
           PresentacionEntidad pe = new PresentacionEntidad();
           pe.setTipoPresentacion(tipoPresentacion1);
           pe.setEntidad(e);
           pe.setIntimada("NO");
           pe.setIntimacion(null);
           pe.setIfGedo(null);
           pe.setNombreArchivo(nombre);
           pe.setVigente("NO");
           pe.setUsuario(usuario);
           pe.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
 
           
           if ("NO".equals(tipoPresentacion1.getVence())) {
             pe.setFechaVencimiento(null);
           } else if ("SI".equals(tipoPresentacion1.getVence()) && "actadire".equals(tipoPresentacion1.getNombreArchivo())) {
             
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(e.getFechaInicioMandato());
             calendar.add(1, e.getMandatoEnAnios().intValue());
             calendar.add(5, 1);
             
             Integer dia = Integer.valueOf(calendar.get(5));
             Integer mes = Integer.valueOf(calendar.get(2) + 1);
             Integer anio = Integer.valueOf(calendar.get(1));
             String fecha = String.valueOf(dia.toString()) + "/" + mes.toString() + "/" + anio.toString();
             
             pe.setFechaVencimiento((new SimpleDateFormat("dd/MM/yyyy")).parse(fecha));
           }
           else if ("SI".equals(tipoPresentacion1.getVence()) && "balances".equals(tipoPresentacion1.getNombreArchivo())) {
             
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
             
             pe.setFechaVencimiento((new SimpleDateFormat("dd/MM/yyyy")).parse(fecha));
           } 
 
           
           this.peRepo.save(pe);
           archivoCargado = Boolean.TRUE;
         } 
         b++; }
       
       if (archivoCargado.booleanValue()) {
         Entidad e = this.eRepo.findByCuit(Long.valueOf(usuario));
         this.mService.sendRecuerdoDocumentacion(e.getSolicitante(), e.geteMail());
       } 
       
       return "";
     } catch (Exception e) {
       
       return "ERROR";
     } 
   }
   
   public void cambiarEstadoEntidad(Entidad e, String estado, String usuario) {
     EstadoEntidad ee = this.eeRepo.findByNombreEstado(estado);
     if (ee != null) {
       e.setEstadoEntidad(ee);
       e.setUsuario(usuario);
       e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       if (!"Alta".equals(ee.getNombreEstado())) {
         e.setVigente("NO");
       } else {
         e.setVigente("SI");
       } 
       
       this.eRepo.save(e);
     } 
   }
 
 
 
 
   
   public void generarArchivoSolicitudBaja(Long cuit, HttpServletRequest request) throws DocumentException, IOException {
     Entidad e = this.eRepo.findByCuit(cuit);
     Resolucion r = this.rRepo.findByEntidadCuit(e.getCuit());
     
     String codigosStr = "";
     ArrayList<String> codigos = (ArrayList<String>)this.cRepo.getCodigosDescuentosByEntidad(e.getCodigoEntidad());
     if (codigos.size() == 1) {
       codigosStr = String.valueOf(codigosStr) + String.valueOf(codigos.get(0));
     } else {
       for (int i = 0; i < codigos.size() - 1; i++) {
         codigosStr = String.valueOf(codigosStr) + String.valueOf(codigos.get(i));
         codigosStr = String.valueOf(codigosStr) + ", ";
       } 
       codigosStr = codigosStr.substring(0, codigosStr.length() - 2);
       codigosStr = String.valueOf(codigosStr) + " y " + String.valueOf(codigos.get(codigos.size() - 1));
     } 
     
     Parametro param = this.paService.findByNombreParametro(MEMBRETE_ANIO);
     String membreteAnio = (param != null && "A".equals(param.getEstado())) ? param.getDescripcion() : "";
     
     param = this.paService.findByNombreParametro(MEMBRETE_ORGANISMO);
     String membreteOrganismo = (param != null && "A".equals(param.getEstado())) ? param.getDescripcion() : "Poder Ejecutivo Nacional";
     
     param = this.paService.findByNombreParametro(SOLICITUD_DECRETO);
     String nroDecreto = (param != null && "A".equals(param.getEstado())) ? param.getDescripcion() : "";
     
     PdfGenerator.generarPdfSolicitudBaja(e, r.getNroResolucion(), codigosStr, membreteAnio, membreteOrganismo, nroDecreto, request);
   }
 
   
   public void enviarSolicitudBaja(MultipartFile file, String usuario, HttpServletRequest request) throws IllegalStateException, IOException {
     Entidad e = this.eRepo.findByCuit(Long.valueOf(usuario));
     
     if (e != null && file.getOriginalFilename().contains(".pdf")) {
       String nombre = "baja_" + e.getCuit() + ".pdf";
 
       
       String uploadsDir = String.valueOf(PATH) + "/pdf/baja_" + e.getCuit() + "/";
       if (Config.getLocal().booleanValue()) {
         uploadsDir = request.getServletContext().getRealPath(uploadsDir);
       }
       if (!(new File(uploadsDir)).exists()) {
         (new File(uploadsDir)).mkdir();
       }
       
       String filePath = String.valueOf(uploadsDir) + nombre;
       File dest = new File(filePath);
       file.transferTo(dest);
 
       
       SolicitudBaja sb = new SolicitudBaja();
       
       sb.setEntidad(e);
       sb.setFechaSolicitud(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       sb.setUsuario(usuario);
       sb.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.sbRepo.save(sb);
 
 
       
       this.mService.sendAvisoRecepcionSolicitudBaja(e.geteMail(), e.getSolicitante());
 
       
       Collection<Usuario> usuarios = this.uRepo.getUsuariosRolRegistro();
       for (Usuario u : usuarios) {
         this.mService.sendNuevaSolicitudBajaARegistro(sb, u);
       }
     } 
   }
 
 
 
 
   
   public Collection<SolicitudBajaDTO> getSolicitudesBajaEnTramite() {
     Collection<SolicitudBaja> solicitudes = this.sbRepo.getSolicitudesBajaEnTramite();
     ArrayList<SolicitudBajaDTO> solicitudesDTO = new ArrayList<>();
     
     for (SolicitudBaja sb : solicitudes) {
       SolicitudBajaDTO sbDTO = new SolicitudBajaDTO();
       sbDTO.setCodigoSolicitud(sb.getCodigoSolicitud().toString());
       sbDTO.setCodigoEntidad(sb.getEntidad().getCodigoEntidad().toString());
       sbDTO.setCuit(sb.getEntidad().getCuit().toString());
       sbDTO.setEntidad(sb.getEntidad().getDenominacion());
       sbDTO.setFechaSolicitud(sb.getFechaSolicitud());
       
       solicitudesDTO.add(sbDTO);
     } 
     return solicitudesDTO;
   }
   
   public void descargarSolicitudBajaFirmada() {}
 }
