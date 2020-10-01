package com.regent.servicios.implementaciones;

 import com.regent.dtos.ResolucionDTO;
 import com.regent.dtos.SolicitudEntidadDTO;
 import com.regent.dtos.TipoDescuentoSolicitudDTO;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.InstitucionHabilitante;
 import com.regent.negocio.Parametro;
 import com.regent.negocio.Resolucion;
 import com.regent.negocio.SolicitudEntidad;
 import com.regent.negocio.TipoDescuento;
 import com.regent.negocio.TipoDescuentoSolicitud;
 import com.regent.negocio.TipoEntidad;
 import com.regent.negocio.TipoPresentacion;
 import com.regent.negocio.Usuario;
 import com.regent.pdf.PdfGenerator;
 import com.regent.repositories.interfaces.EntidadRepository;
 import com.regent.repositories.interfaces.EstadoSolicitudRepository;
 import com.regent.repositories.interfaces.InstitucionHabilitanteRepository;
 import com.regent.repositories.interfaces.ResolucionRepository;
 import com.regent.repositories.interfaces.RolRepository;
 import com.regent.repositories.interfaces.SolicitudEntidadRepository;
 import com.regent.repositories.interfaces.TipoDescuentoRepository;
 import com.regent.repositories.interfaces.TipoDescuentoSolicitudRepository;
 import com.regent.repositories.interfaces.TipoEntidadRepository;
 import com.regent.repositories.interfaces.TipoPresentacionRepository;
 import com.regent.repositories.interfaces.UsuarioRepository;
 import com.regent.security.hash.PasswordGenerator;
 import com.regent.security.hash.SimpleMD5;
 import com.regent.servicios.interfaces.EntidadService;
 import com.regent.servicios.interfaces.MailService;
 import com.regent.servicios.interfaces.ParametroService;
 import com.regent.servicios.interfaces.PresentacionEntidadService;
 import com.regent.servicios.interfaces.ResolucionService;
 import com.regent.servicios.interfaces.SolicitudEntidadService;
 import com.regent.util.Config;
 import com.regent.util.interfaces.FileUtil;
 import java.io.File;
 import java.io.IOException;
 import java.math.BigDecimal;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.sql.Timestamp;
 import java.text.ParseException;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.web.multipart.MultipartFile;
 
 
 
 
 @Service
 public class SolicitudEntidadServiceImpl
   implements SolicitudEntidadService
 {
   public static String CARGA = "En proceso de carga";
   public static String REVISION = "En revisión";
   public static String REVISADA = "Revisada pendiente de caratulación";
   public static String EXPEDIENTE_EN_TRAMITE = "Expediente en trámite";
   public static String APROBADA = "Aprobada";
   public static String RECHAZADA = "Rechazada";
 
   
   public static String ENTIDAD = "ENTIDAD";
   public static String SOLICITANTE = "SOLICITANTE";
   public static String MESA_ENTRADAS = "MESA DE ENTRADAS";
 
   
   public static String MEMBRETE_ANIO = "MEMBRETE_ANIO";
   public static String MEMBRETE_ORGANISMO = "MEMBRETE_ORGANISMO";
   public static String SOLICITUD_DECRETO = "NRO_DECRETO";
 
   
   public static String PATH = "/home/registro";
   
   @Autowired
   SolicitudEntidadRepository seRepo;
   
   @Autowired
   TipoEntidadRepository tRepo;
   
   @Autowired
   InstitucionHabilitanteRepository ihRepo;
   
   @Autowired
   EstadoSolicitudRepository esRepo;
   
   @Autowired
   TipoDescuentoRepository tdRepo;
   
   @Autowired
   TipoDescuentoSolicitudRepository tdsRepo;
   @Autowired
   RolRepository rRepo;
   @Autowired
   UsuarioRepository uRepo;
   @Autowired
   TipoPresentacionRepository tpRepo;
   @Autowired
   EntidadRepository eRepo;
   @Autowired
   ResolucionRepository reRepo;
   @Autowired
   MailService mService;
   @Autowired
   EntidadService eService;
   @Autowired
   ResolucionService reService;
   @Autowired
   PresentacionEntidadService peService;
   @Autowired
   ParametroService paService;
   @Autowired
   FileUtil fileUtil;
   
   public String nuevaSolicitudEntidad(SolicitudEntidadDTO seDTO, String usuario) throws IOException {
     try {
       SolicitudEntidad solC = this.seRepo.findByCuit(Long.valueOf(seDTO.getCuit().trim()));
       Entidad entC = this.eRepo.findByCuit(Long.valueOf(seDTO.getCuit().trim()));
       
       if (solC != null && !APROBADA.equals(solC.getEstadoSolicitud().getNombreEstado()) && 
         !RECHAZADA.equals(solC.getEstadoSolicitud().getNombreEstado()))
         return "CUIT_SOLICITUD"; 
       if (entC != null && "Alta".equals(entC.getEstadoEntidad().getNombreEstado())) {
         return "CUIT_ENTIDAD";
       }
       
       SolicitudEntidad solE = this.seRepo.findSolicitudEnTramiteByEMail(seDTO.geteMail().trim());
       Entidad entE = eRepo.findByEMail(seDTO.geteMail().trim());
       if (solE != null && !APROBADA.equals(solE.getEstadoSolicitud().getNombreEstado()) && 
         !RECHAZADA.equals(solE.getEstadoSolicitud().getNombreEstado()))
         return "MAIL_SOLICITUD"; 
       if (entE != null && "Alta".equals(entE.getEstadoEntidad().getNombreEstado())) {
         return "MAIL_ENTIDAD";
       }
 
       
       SolicitudEntidad se = new SolicitudEntidad();
       
       se.setDenominacion(seDTO.getDenominacion().trim());
       se.setTipoEntidad((TipoEntidad)this.tRepo.findOne(Integer.valueOf(seDTO.getCodigo_tipo_entidad().trim())));
       se.setSolicitante(seDTO.getSolicitante().trim());
       se.setCaracterSolicitante(seDTO.getCaracterSolicitante().trim());
       se.setInstrumentoPersoneria(seDTO.getInstrumentoPersoneria().trim());
       se.seteMail(seDTO.geteMail().trim());
       se.setTelefono(seDTO.getTelefono().trim());
       se.setInstitucionHabilitante((InstitucionHabilitante)this.ihRepo.findOne(Integer.valueOf(seDTO.getCodigo_institucion_habilitante().trim())));
       se.setCuit(Long.valueOf(seDTO.getCuit().trim()));
       se.setNroRegHabilitante(seDTO.getNroRegHabilitante().trim());
       se.setDomicilioLegal(seDTO.getDomicilioLegal().trim());
       se.setLocalidad(seDTO.getLocalidad().trim());
       se.setCodigoPostal(seDTO.getCodigoPostal().trim());
       se.setDomicilioEspecial(seDTO.getDomicilioEspecial().trim());
       se.setDomicilioComercial(seDTO.getDomicilioComercial().trim());
       se.setTelefonoComercial(seDTO.getTelefonoComercial().trim());
       se.setPaginaWeb(seDTO.getPaginaWeb());
       se.setModificable("SI");
       se.setEstadoSolicitud(this.esRepo.findByNombreEstado(CARGA));
       se.setResolucion("NO");
       se.setEstado("A");
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       se = (SolicitudEntidad)this.seRepo.save(se);
 
       
       Boolean alMenosUnoCheckeado = Boolean.FALSE;
       for (TipoDescuentoSolicitudDTO tdsDTO : seDTO.getTipoDescuentoSolicitud()) {
         if ("true".equals(tdsDTO.getEstado())) {
           alMenosUnoCheckeado = Boolean.TRUE;
           
           TipoDescuento td = (TipoDescuento)this.tdRepo.findOne(Integer.valueOf(tdsDTO.getCodigoTipoDescuento()));
           
           this.tdsRepo.insertTipoDescuentoSolicitud(se.getCodigoSolicitud(), td.getCodigoTipoDescuento(), "A", "sistema", String.valueOf(new Timestamp(System.currentTimeMillis())));
         } 
       } 
       
       if (alMenosUnoCheckeado.booleanValue()) {
         
         Usuario u = new Usuario();
         u.setDenominacion(se.getSolicitante().trim());
         u.setCorreoElectronico(se.geteMail().trim());
         u.setObservaciones("-");
         u.setRol(this.rRepo.findByNombreRol(SOLICITANTE));
         u.setNombreUsuario(se.getCuit().toString().trim());
         
         String clave = PasswordGenerator.getPassword(6);
         String claveHash = SimpleMD5.hash(clave);
         u.setPassword(claveHash);
         
         u.setEstado("A");
         u.setCambiaClave("SI");
         u.setUsuario("sistema");
         u.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         
         this.uRepo.save(u);
         
         this.mService.sendNuevoUsuarioSolicitante(u, clave);
         
         return "";
       } 
       this.seRepo.delete(se);
       return "NO_CHECK";
     }
     catch (Exception e) {
       return "ERROR";
     } 
   }
 
   
   public SolicitudEntidadDTO findSolicitudEntidadByCuit(Long cuit, HttpServletRequest request) {
     SolicitudEntidad se = this.seRepo.findByCuit(cuit);
     
     SolicitudEntidadDTO seDTO = new SolicitudEntidadDTO();
     
     seDTO.setCodigoSolicitud(se.getCodigoSolicitud().toString());
     seDTO.setDenominacion(se.getDenominacion());
     seDTO.setCodigo_tipo_entidad(se.getTipoEntidad().getCodigoTipoEntidad().toString());
     seDTO.setSolicitante(se.getSolicitante());
     
     String estado = se.getEstadoSolicitud().getNombreEstado();
     if (EXPEDIENTE_EN_TRAMITE.equals(se.getEstadoSolicitud().getNombreEstado())) {
       Resolucion r = this.reRepo.findByEntidadCuit(se.getCuit());
       if (r != null) {
         String expJgm = r.getExpJgm();
         String expMm = r.getExpMmod();
         
         if (!"".equals(expJgm) && expJgm != null) {
           estado = String.valueOf(estado) + ": " + expJgm;
         } else if (!"".equals(expMm) && expMm != null) {
           estado = String.valueOf(estado) + ": " + expMm;
         } 
       } 
     } 
     
     seDTO.setEstadoSolicitud(estado);
     seDTO.setCaracterSolicitante(se.getCaracterSolicitante());
     seDTO.setInstrumentoPersoneria(se.getInstrumentoPersoneria());
     seDTO.seteMail(se.geteMail());
     seDTO.setTelefono(se.getTelefono());
     seDTO.setCodigo_institucion_habilitante(se.getInstitucionHabilitante().getCodigoInstitucionHabilitante().toString());
     seDTO.setNroRegHabilitante(se.getNroRegHabilitante());
     seDTO.setDomicilioLegal(se.getDomicilioLegal());
     seDTO.setLocalidad(se.getLocalidad());
     seDTO.setCodigoPostal(se.getCodigoPostal());
     seDTO.setDomicilioEspecial(se.getDomicilioEspecial());
     seDTO.setDomicilioComercial(se.getDomicilioComercial());
     seDTO.setTelefonoComercial(se.getTelefonoComercial());
     seDTO.setObservaciones(se.getObservaciones());
     seDTO.setPaginaWeb(se.getPaginaWeb());
     seDTO.setModificable(se.getModificable());
     
     Collection<TipoDescuentoSolicitudDTO> tdsDTO = new ArrayList<>();
     
     Collection<Object[]> tipoDescuentoSolicitud = this.tdsRepo.getTipoDescuentoSolicitudCheckedByCodigoSolicitud(se.getCodigoSolicitud());
     for (Object[] o : tipoDescuentoSolicitud) {
       TipoDescuentoSolicitudDTO tDTO = new TipoDescuentoSolicitudDTO();
       tDTO.setCodigoTipoDescuento(String.valueOf(o[0]));
       tdsDTO.add(tDTO);
     } 
     seDTO.setTipoDescuentoSolicitud(tdsDTO);
 
     
     Collection<String[]> archivos = (Collection)new ArrayList<>();
     
     String ruta = String.valueOf(PATH) + "/pdf/solicitud_" + cuit.toString();
     if (Config.getLocal().booleanValue()) {
       ruta = request.getServletContext().getRealPath(ruta);
     }
     File folder = new File(ruta);
     if (folder.listFiles() != null) {
       byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = folder.listFiles()).length, b = 0; b < i; ) { File fileEntry = arrayOfFile[b];
         String nombre = fileEntry.getName();
         if (!("solicitu_" + cuit.toString() + ".pdf").equals(nombre)) {
           String nombreCorto = nombre.split("_")[0];
           TipoPresentacion tp = this.tpRepo.findByNombreArchivo(nombreCorto);
           String[] arch = new String[2];
           arch[0] = tp.getCodigoTipoPresentacion().toString();
           arch[1] = nombreCorto;
           
           archivos.add(arch);
         } 
         b++; }
     
     } 
     seDTO.setNombresArchivos(archivos);
     
     return seDTO;
   }
 
 
 
 
 
 
 
 
   
   public String guardarEdicionSolicitud(SolicitudEntidadDTO seDTO, MultipartFile[] files, String usuario, HttpServletRequest request) {
     String error = "";
     
     try {
       SolicitudEntidad se = this.seRepo.findByCuit(Long.valueOf(usuario));
       if ("NO".equals(se.getModificable())) {
         return "ERROR";
       }
       
       if (!"".equals(seDTO.getDenominacion().trim())) {
         se.setDenominacion(seDTO.getDenominacion().trim());
       }
       if (!"".equals(seDTO.getCodigo_tipo_entidad().trim())) {
         se.setTipoEntidad((TipoEntidad)this.tRepo.findOne(Integer.valueOf(seDTO.getCodigo_tipo_entidad().trim())));
       }
       if (!"".equals(seDTO.getSolicitante().trim())) {
         se.setSolicitante(seDTO.getSolicitante().trim());
       }
       if (!"".equals(seDTO.getCaracterSolicitante().trim())) {
         se.setCaracterSolicitante(seDTO.getCaracterSolicitante().trim());
       }
       if (!"".equals(seDTO.getInstrumentoPersoneria().trim())) {
         se.setInstrumentoPersoneria(seDTO.getInstrumentoPersoneria().trim());
       }
       if (!"".equals(seDTO.geteMail().trim())) {
         se.seteMail(seDTO.geteMail().trim());
       }
       if (!"".equals(seDTO.getTelefono().trim())) {
         se.setTelefono(seDTO.getTelefono().trim());
       }
       if (!"".equals(seDTO.getCodigo_institucion_habilitante().trim())) {
         se.setInstitucionHabilitante((InstitucionHabilitante)this.ihRepo.findOne(Integer.valueOf(seDTO.getCodigo_institucion_habilitante().trim())));
       }
       if (!"".equals(seDTO.getNroRegHabilitante().trim())) {
         se.setNroRegHabilitante(seDTO.getNroRegHabilitante().trim());
       }
       if (!"".equals(seDTO.getDomicilioLegal().trim())) {
         se.setDomicilioLegal(seDTO.getDomicilioLegal().trim());
       }
       if (!"".equals(seDTO.getLocalidad().trim())) {
         se.setLocalidad(seDTO.getLocalidad().trim());
       }
       if (!"".equals(seDTO.getCodigoPostal().trim())) {
         se.setCodigoPostal(seDTO.getCodigoPostal().trim());
       }
       if (!"".equals(seDTO.getDomicilioEspecial().trim())) {
         se.setDomicilioEspecial(seDTO.getDomicilioEspecial().trim());
       }
       if (!"".equals(seDTO.getDomicilioComercial().trim())) {
         se.setDomicilioComercial(seDTO.getDomicilioComercial().trim());
       }
       if (!"".equals(seDTO.getTelefonoComercial().trim())) {
         se.setTelefonoComercial(seDTO.getTelefonoComercial().trim());
       }
       
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       se = (SolicitudEntidad)this.seRepo.save(se);
 
       
       Boolean alMenosUnoCheckeado = Boolean.FALSE;
       for (TipoDescuentoSolicitudDTO tdsDTO : seDTO.getTipoDescuentoSolicitud()) {
         TipoDescuentoSolicitud tds = this.tdsRepo.getTipoDescuentoSolicituBySolicitudAndTipoDescuento(se.getCodigoSolicitud(), Integer.valueOf(tdsDTO.getCodigoTipoDescuento()));
         if (tds != null && "true".equals(tdsDTO.getEstado())) {
 
           
           alMenosUnoCheckeado = Boolean.TRUE;
           if ("I".equals(tds.getEstado())) {
             
             tds.setEstado("A");
             tds.setUsuario(usuario);
             tds.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
             this.tdsRepo.save(tds);
           }  continue;
         } 
         if (tds != null && "false".equals(tdsDTO.getEstado())) {
 
           
           if ("A".equals(tds.getEstado())) {
             
             tds.setEstado("I");
             tds.setUsuario(usuario);
             tds.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
             this.tdsRepo.save(tds);
           }  continue;
         } 
         if (tds == null && "true".equals(tdsDTO.getEstado())) {
           
           alMenosUnoCheckeado = Boolean.TRUE;
           TipoDescuento td = (TipoDescuento)this.tdRepo.findOne(Integer.valueOf(tdsDTO.getCodigoTipoDescuento()));
           this.tdsRepo.insertTipoDescuentoSolicitud(se.getCodigoSolicitud(), td.getCodigoTipoDescuento(), "A", "sistema", String.valueOf(new Timestamp(System.currentTimeMillis())));
         } 
       } 
       
       if (!alMenosUnoCheckeado.booleanValue()) {
         return "NO_CHECK";
       }
       
       String uploadsDir = String.valueOf(PATH) + "/pdf/solicitud_" + usuario + "/";
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
         TipoPresentacion tp = (TipoPresentacion)this.tpRepo.findOne(Integer.valueOf(codigoTipoPresentacion));
         String periodo = nombre.split("_")[2].replace(".pdf", "");
         
         if (!file.getOriginalFilename().contains(".pdf")) {
           return "EXTENSION";
         }
         
         if (!nombre.contains(tp.getNombreArchivo()) || !nombre.contains(usuario))
           return "NOMBRE_ARCHIVO"; 
         if (!nombre.contains(se.getCuit().toString())) {
           return "NOMBRE_ARCHIVO";
         }
         if ((nombre.split("_")).length < 3 || periodo.length() != 6 || Integer.valueOf(periodo).intValue() < 201801) {
           return "NOMBRE_ARCHIVO";
         }
 
 
 
         
         if (Integer.valueOf(periodo.substring(4)).intValue() < 13) {
           error = borrarPdfAnterior(tp.getNombreArchivo(), usuario, periodo);
         } else {
           error = "NOMBRE_ARCHIVO";
         } 
 
 
         
         if (validacionPeriodoValido(tp.getNombreArchivo(), usuario, periodo)) {
           String filePath = String.valueOf(uploadsDir) + nombre;
           File dest = new File(filePath);
           file.transferTo(dest);
         } 
         b++; }
       
       return error;
     }
     catch (Exception e) {
       
       return "ERROR";
     } 
   }
   
   private boolean validacionPeriodoValido(String codigoTipoPresentacion, String cuit, String periodo) {
     File folder = new File(String.valueOf(PATH) + "/pdf/solicitud_" + cuit + "/");
     File[] listOfFiles = folder.listFiles();
     List<String> nombresPdf = new ArrayList<>();
     Integer mesActual = Integer.valueOf(periodo.substring(4));
     Boolean validacionPeriodo = Boolean.valueOf(true);
 
     
     for (int i = 0; i < listOfFiles.length; i++) {
       nombresPdf.add(listOfFiles[i].getName().replace(".pdf", ""));
     }
     
     nombresPdf.remove("solicitu_" + cuit);
 
     
     for (String NombrePdf : nombresPdf) {
       
       if (NombrePdf.contains(codigoTipoPresentacion)) {
         String[] parts = NombrePdf.split("_");
         String periodoAnterior = parts[2];
         
         validacionPeriodo = Boolean.valueOf((Integer.valueOf(periodoAnterior).intValue() <= Integer.valueOf(periodo).intValue()));
       } 
     } 
     
     return (mesActual.intValue() < 13 && validacionPeriodo.booleanValue());
   }
 
 
 
 
 
 
 
 
 
 
   
   public String borrarPdfAnterior(String codigoTipoPresentacion, String cuit, String periodo) throws IOException {
     File folder = new File(String.valueOf(PATH) + "/pdf/solicitud_" + cuit + "/");
     File[] listOfFiles = folder.listFiles();
     List<String> nombresPdf = new ArrayList<>();
 
     
     for (int i = 0; i < listOfFiles.length; i++) {
       nombresPdf.add(listOfFiles[i].getName().replace(".pdf", ""));
     }
     
     nombresPdf.remove("solicitu_" + cuit);
 
     
     for (String pdf : nombresPdf) {
       if (pdf.contains(codigoTipoPresentacion)) {
         
         String[] parts = pdf.split("_");
         String periodoAnterior = parts[2];
 
         
         if (Integer.valueOf(periodoAnterior).intValue() <= Integer.valueOf(periodo).intValue()) {
           deletePdf(codigoTipoPresentacion, cuit, periodoAnterior);
           return "";
         }  return "PERIODO_INVALIDO";
       } 
     } 
     
     return "";
   }
 
 
   
   public String enviarARevisionSolicitud(SolicitudEntidadDTO seDTO, MultipartFile[] files, String usuario, HttpServletRequest request) {
     String error = "";
     
     try {
       SolicitudEntidad se = this.seRepo.findByCuit(Long.valueOf(usuario));
       
       if ("NO".equals(se.getModificable())) {
         return "NO MODIFICABLE";
       }
       
       se.setDenominacion(seDTO.getDenominacion().trim());
       se.setTipoEntidad((TipoEntidad)this.tRepo.findOne(Integer.valueOf(seDTO.getCodigo_tipo_entidad().trim())));
       se.setSolicitante(seDTO.getSolicitante().trim());
       se.setCaracterSolicitante(seDTO.getCaracterSolicitante().trim());
       se.setInstrumentoPersoneria(seDTO.getInstrumentoPersoneria().trim());
       se.seteMail(seDTO.geteMail().trim());
       se.setTelefono(seDTO.getTelefono().trim());
       se.setInstitucionHabilitante((InstitucionHabilitante)this.ihRepo.findOne(Integer.valueOf(seDTO.getCodigo_institucion_habilitante().trim())));
       se.setNroRegHabilitante(seDTO.getNroRegHabilitante().trim());
       se.setDomicilioLegal(seDTO.getDomicilioLegal().trim());
       se.setLocalidad(seDTO.getLocalidad().trim());
       se.setCodigoPostal(seDTO.getCodigoPostal().trim());
       se.setDomicilioEspecial(seDTO.getDomicilioEspecial().trim());
       se.setDomicilioComercial(seDTO.getDomicilioComercial().trim());
       se.setTelefonoComercial(seDTO.getTelefonoComercial().trim());
       se.setObservaciones("");
       
       se.setEstadoSolicitud(this.esRepo.findByNombreEstado(REVISION));
       se.setModificable("NO");
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
 
 
       
       Boolean alMenosUnoCheckeado = Boolean.FALSE;
       for (TipoDescuentoSolicitudDTO tdsDTO : seDTO.getTipoDescuentoSolicitud()) {
         TipoDescuentoSolicitud tds = this.tdsRepo.getTipoDescuentoSolicituBySolicitudAndTipoDescuento(se.getCodigoSolicitud(), Integer.valueOf(tdsDTO.getCodigoTipoDescuento()));
         if (tds != null && "true".equals(tdsDTO.getEstado())) {
 
           
           alMenosUnoCheckeado = Boolean.TRUE;
           if ("I".equals(tds.getEstado())) {
             
             tds.setEstado("A");
             tds.setUsuario(usuario);
             tds.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
             this.tdsRepo.save(tds);
           }  continue;
         } 
         if (tds != null && "false".equals(tdsDTO.getEstado())) {
 
           
           if ("A".equals(tds.getEstado())) {
             
             tds.setEstado("I");
             tds.setUsuario(usuario);
             tds.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
             this.tdsRepo.save(tds);
           }  continue;
         } 
         if (tds == null && "true".equals(tdsDTO.getEstado())) {
           
           alMenosUnoCheckeado = Boolean.TRUE;
           TipoDescuento td = (TipoDescuento)this.tdRepo.findOne(Integer.valueOf(tdsDTO.getCodigoTipoDescuento()));
           this.tdsRepo.insertTipoDescuentoSolicitud(se.getCodigoSolicitud(), td.getCodigoTipoDescuento(), "A", "sistema", String.valueOf(new Timestamp(System.currentTimeMillis())));
         } 
       } 
       
       if (!alMenosUnoCheckeado.booleanValue()) {
         return "NO_CHECK";
       }
 
       
       String uploadsDir = String.valueOf(PATH) + "/pdf/solicitud_" + usuario + "/";
       if (Config.getLocal().booleanValue()) {
         uploadsDir = request.getServletContext().getRealPath(uploadsDir);
       }
       if (!(new File(uploadsDir)).exists())
         (new File(uploadsDir)).mkdir();  byte b1; int i;
       MultipartFile[] arrayOfMultipartFile;
       for (i = (arrayOfMultipartFile = files).length, b1 = 0; b1 < i; ) { MultipartFile file = arrayOfMultipartFile[b1];
         String orgName = file.getOriginalFilename();
         String codigoTipoPresentacion = orgName.split("#")[0];
         String nombre = orgName.split("#")[1];
         
         if (!file.getOriginalFilename().contains(".pdf")) {
           return "EXTENSION";
         }
         
         TipoPresentacion tp = (TipoPresentacion)this.tpRepo.findOne(Integer.valueOf(codigoTipoPresentacion));
         if (!nombre.contains(tp.getNombreArchivo()) || !nombre.contains(usuario)) {
           return "NOMBRE_ARCHIVO";
         }
         String periodo = nombre.split("_")[2].replace(".pdf", "");
         if ((nombre.split("_")).length < 3 || periodo.length() != 6 || Integer.valueOf(periodo).intValue() < 201801) {
           return "NOMBRE_ARCHIVO";
         }
 
 
 
         
         String filePath = String.valueOf(uploadsDir) + nombre;
         File dest = new File(filePath);
         file.transferTo(dest);
         
         b1++; }
 
       
       Collection<String> nombresArchivos = this.seRepo.getNombresArchivosACargar(se.getCodigoSolicitud());
 
       
       Integer archivosCorrectos = Integer.valueOf(0);
       File folder = new File(uploadsDir); byte b2; int j; File[] arrayOfFile;
       for (j = (arrayOfFile = folder.listFiles()).length, b2 = 0; b2 < j; ) { File fileEntry = arrayOfFile[b2];
         String nombre = fileEntry.getName();
         String nombreCorto = nombre.split("_")[0];
         
         if (nombresArchivos.contains(nombreCorto)) {
           archivosCorrectos = Integer.valueOf(archivosCorrectos.intValue() + 1);
         }
         b2++; }
       
       if (archivosCorrectos.intValue() < nombresArchivos.size()) {
         return "FALTAN_ARCHIVOS";
       }
       this.seRepo.save(se);
       
       this.mService.sendAvisoSolicitudARevision(this.uRepo.findByCorreoElectronico(seDTO.geteMail()));
 
       
       Collection<Usuario> usuarios = this.uRepo.getUsuariosRolRegistro();
       for (Usuario u : usuarios) {
         this.mService.sendNuevaSolicitudARolRegistro(se, u);
       }
 
       
       return error;
     } catch (Exception e) {
       
       return "MAIL_OR_FILE_EXCEPTION";
     } 
   }
 
 
   
   public Collection<SolicitudEntidadDTO> getSolicitudesForAprobacion() {
     Collection<SolicitudEntidad> solicitudes = this.seRepo.getSolicitudesByEstado(REVISION);
     Collection<SolicitudEntidadDTO> soliDTO = new ArrayList<>();
     
     for (SolicitudEntidad se : solicitudes) {
       SolicitudEntidadDTO seDTO = new SolicitudEntidadDTO();
       
       seDTO.setCuit(se.getCuit().toString());
       seDTO.setCodigoSolicitud(se.getCodigoSolicitud().toString());
       seDTO.setDenominacion(se.getDenominacion());
       seDTO.setCodigo_tipo_entidad(se.getTipoEntidad().getNombreTipoEntidad());
       seDTO.setSolicitante(se.getSolicitante());
       seDTO.setCaracterSolicitante(se.getCaracterSolicitante());
       seDTO.setInstrumentoPersoneria(se.getInstrumentoPersoneria());
       seDTO.seteMail(se.geteMail());
       seDTO.setTelefono(se.getTelefono());
       seDTO.setCodigo_institucion_habilitante(se.getInstitucionHabilitante().getNombreInstitucionHabilitante());
       seDTO.setNroRegHabilitante(se.getNroRegHabilitante());
       seDTO.setDomicilioLegal(se.getDomicilioLegal());
       seDTO.setLocalidad(se.getLocalidad());
       seDTO.setCodigoPostal(se.getCodigoPostal());
       seDTO.setDomicilioEspecial(se.getDomicilioEspecial());
       seDTO.setDomicilioComercial(se.getDomicilioComercial());
       seDTO.setTelefonoComercial(se.getTelefonoComercial());
       seDTO.setPaginaWeb(se.getPaginaWeb());
       
       Collection<TipoDescuentoSolicitudDTO> tdsDTO = new ArrayList<>();
       
       Collection<Object[]> tipoDescuentoSolicitud = this.tdsRepo.getTipoDescuentoSolicitudByCodigoSolicitud(se.getCodigoSolicitud());
       for (Object[] o : tipoDescuentoSolicitud) {
         TipoDescuentoSolicitudDTO tDTO = new TipoDescuentoSolicitudDTO();
         tDTO.setTipoDescuento((String)o[0]);
         tDTO.setEstado((String)o[1]);
         tdsDTO.add(tDTO);
       } 
       seDTO.setTipoDescuentoSolicitud(tdsDTO);
       
       soliDTO.add(seDTO);
     } 
     return soliDTO;
   }
 
 
   
   public Collection<SolicitudEntidadDTO> getSolicitudesForCaratulacion() {
     Collection<SolicitudEntidad> solicitudes = this.seRepo.getSolicitudesByEstado(REVISADA);
     Collection<SolicitudEntidadDTO> soliDTO = new ArrayList<>();
     
     for (SolicitudEntidad se : solicitudes) {
       SolicitudEntidadDTO seDTO = new SolicitudEntidadDTO();
       
       seDTO.setCuit(se.getCuit().toString());
       seDTO.setCodigoSolicitud(se.getCodigoSolicitud().toString());
       seDTO.setDenominacion(se.getDenominacion());
       seDTO.setCodigo_tipo_entidad(se.getTipoEntidad().getNombreTipoEntidad());
       seDTO.setSolicitante(se.getSolicitante());
       seDTO.setCaracterSolicitante(se.getCaracterSolicitante());
       seDTO.setInstrumentoPersoneria(se.getInstrumentoPersoneria());
       seDTO.seteMail(se.geteMail());
       seDTO.setTelefono(se.getTelefono());
       seDTO.setCodigo_institucion_habilitante(se.getInstitucionHabilitante().getNombreInstitucionHabilitante());
       seDTO.setNroRegHabilitante(se.getNroRegHabilitante());
       seDTO.setDomicilioLegal(se.getDomicilioLegal());
       seDTO.setLocalidad(se.getLocalidad());
       seDTO.setCodigoPostal(se.getCodigoPostal());
       seDTO.setDomicilioEspecial(se.getDomicilioEspecial());
       seDTO.setDomicilioComercial(se.getDomicilioComercial());
       seDTO.setTelefonoComercial(se.getTelefonoComercial());
       seDTO.setPaginaWeb(se.getPaginaWeb());
       
       Collection<TipoDescuentoSolicitudDTO> tdsDTO = new ArrayList<>();
       
       Collection<Object[]> tipoDescuentoSolicitud = this.tdsRepo.getTipoDescuentoSolicitudByCodigoSolicitud(se.getCodigoSolicitud());
       for (Object[] o : tipoDescuentoSolicitud) {
         TipoDescuentoSolicitudDTO tDTO = new TipoDescuentoSolicitudDTO();
         tDTO.setTipoDescuento((String)o[0]);
         tDTO.setEstado((String)o[1]);
         tdsDTO.add(tDTO);
       } 
       seDTO.setTipoDescuentoSolicitud(tdsDTO);
       
       soliDTO.add(seDTO);
     } 
     return soliDTO;
   }
 
 
   
   public Collection<SolicitudEntidadDTO> getSolicitudesForResolucion() {
     Collection<SolicitudEntidad> solicitudes = this.seRepo.getSolicitudesByEstadoAndResolucion(EXPEDIENTE_EN_TRAMITE, "NO");
     Collection<SolicitudEntidadDTO> soliDTO = new ArrayList<>();
     
     for (SolicitudEntidad se : solicitudes) {
       SolicitudEntidadDTO seDTO = new SolicitudEntidadDTO();
       
       seDTO.setCuit(se.getCuit().toString());
       seDTO.setCodigoSolicitud(se.getCodigoSolicitud().toString());
       seDTO.setDenominacion(se.getDenominacion());
       seDTO.setCodigo_tipo_entidad(se.getTipoEntidad().getNombreTipoEntidad());
       seDTO.setSolicitante(se.getSolicitante());
       seDTO.setCaracterSolicitante(se.getCaracterSolicitante());
       seDTO.setInstrumentoPersoneria(se.getInstrumentoPersoneria());
       seDTO.seteMail(se.geteMail());
       seDTO.setTelefono(se.getTelefono());
       seDTO.setCodigo_institucion_habilitante(se.getInstitucionHabilitante().getNombreInstitucionHabilitante());
       seDTO.setNroRegHabilitante(se.getNroRegHabilitante());
       seDTO.setDomicilioLegal(se.getDomicilioLegal());
       seDTO.setLocalidad(se.getLocalidad());
       seDTO.setCodigoPostal(se.getCodigoPostal());
       seDTO.setDomicilioEspecial(se.getDomicilioEspecial());
       seDTO.setDomicilioComercial(se.getDomicilioComercial());
       seDTO.setTelefonoComercial(se.getTelefonoComercial());
       seDTO.setPaginaWeb(se.getPaginaWeb());
       
       Collection<TipoDescuentoSolicitudDTO> tdsDTO = new ArrayList<>();
       
       Collection<Object[]> tipoDescuentoSolicitud = this.tdsRepo.getTipoDescuentoSolicitudByCodigoSolicitud(se.getCodigoSolicitud());
       for (Object[] o : tipoDescuentoSolicitud) {
         TipoDescuentoSolicitudDTO tDTO = new TipoDescuentoSolicitudDTO();
         tDTO.setTipoDescuento((String)o[0]);
         tDTO.setEstado((String)o[1]);
         tdsDTO.add(tDTO);
       } 
       seDTO.setTipoDescuentoSolicitud(tdsDTO);
       
       soliDTO.add(seDTO);
     } 
     return soliDTO;
   }
 
   
   public SolicitudEntidad findSolicitudEntidadByCodigoSolicitud(Integer codigo) {
     return (SolicitudEntidad)this.seRepo.findOne(codigo);
   }
 
   
   public String registrarCambioSolicitud(Integer codigo, String tipo, String usuario, String obs) {
     SolicitudEntidad se = (SolicitudEntidad)this.seRepo.findOne(codigo);
     if ("A".equals(tipo) && REVISION.equals(se.getEstadoSolicitud().getNombreEstado()) && 
       "A".equals(se.getEstado()) && "NO".equals(se.getModificable())) {
       se.setEstadoSolicitud(this.esRepo.findByNombreEstado(REVISADA));
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.seRepo.save(se);
 
       
       Collection<Usuario> usuarios = this.uRepo.getUsuariosRolMesaEntradas();
       for (Usuario u : usuarios) {
         this.mService.sendNuevaSolicitudAMesaEntradas(u, se);
       }
       
       return "";
     } 
     
     if ("R".equals(tipo) && REVISION.equals(se.getEstadoSolicitud().getNombreEstado()) && 
       "A".equals(se.getEstado()) && "NO".equals(se.getModificable())) {
       se.setEstadoSolicitud(this.esRepo.findByNombreEstado(RECHAZADA));
       se.setEstado("I");
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.seRepo.save(se);
       
       return "";
     } 
     
     if ("D".equals(tipo) && REVISION.equals(se.getEstadoSolicitud().getNombreEstado()) && 
       "A".equals(se.getEstado()) && "NO".equals(se.getModificable())) {
       se.setObservaciones((obs.length() > 0) ? obs : "-");
       se.setModificable("SI");
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.seRepo.save(se);
       
       this.mService.sendAvisoDevolucionSolicitud(se.getSolicitante(), se.geteMail(), (obs.length() > 0) ? obs : "");
       
       return "";
     } 
     
     return "ERROR";
   }
 
   
   public String caratularSolicitud(Integer codigo, String usuario) {
     SolicitudEntidad se = (SolicitudEntidad)this.seRepo.findOne(codigo);
     if (REVISADA.equals(se.getEstadoSolicitud().getNombreEstado()) && 
       "A".equals(se.getEstado())) {
       se.setEstadoSolicitud(this.esRepo.findByNombreEstado(EXPEDIENTE_EN_TRAMITE));
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.seRepo.save(se);
       
       Collection<Usuario> usuarios = this.uRepo.getUsuariosRolRegistro();
       for (Usuario u : usuarios) {
         this.mService.sendSolicitudCaratuladaARolRegistro(se, u);
       }
       
       return "";
     } 
     
     return "ERROR";
   }
 
 
 
   
   @Transactional
   public void resolverSolicitud(ResolucionDTO rDTO, Integer codigoSolicitud, Integer mesCierre, String fechaInicio, Integer mandato, String observaciones, String usuario, HttpServletRequest request) throws ParseException {
     SolicitudEntidad se = (SolicitudEntidad)this.seRepo.findOne(codigoSolicitud);
     
     if (this.eRepo.findByCuit(se.getCuit()) == null) {
       
       Entidad e = this.eService.nuevaEntidad(se, mesCierre, fechaInicio, mandato, observaciones);
 
       
       Resolucion r = this.reService.nuevaResolucion(rDTO, se, e, usuario);
 
 
 
       
       Collection<String> tiposPresentacion = new ArrayList<>();
       
       String ruta = String.valueOf(PATH) + "/pdf/solicitud_" + e.getCuit().toString();
       if (Config.getLocal().booleanValue()) {
         ruta = request.getServletContext().getRealPath(ruta);
       }
       File folder = new File(ruta);
       if (folder.listFiles() != null) {
         byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = folder.listFiles()).length, b = 0; b < i; ) { File fileEntry = arrayOfFile[b];
           String nombre = fileEntry.getName();
           tiposPresentacion.add(nombre); b++; }
       
       } 
       String exp = "";
       if (!"".equals(r.getExpJgm())) {
         exp = r.getExpJgm();
       } else if (!"".equals(r.getExpMmod())) {
         exp = r.getExpMmod();
       } 
       
       this.peService.nuevaPresentacionEntidad(tiposPresentacion, e, exp);
 
       
       se.setResolucion("SI");
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.seRepo.save(se);
     } 
   }
 
 
   
   public void aprobarSolicitud(Entidad e, String usuario) {
     SolicitudEntidad se = this.seRepo.findByCuitAndEstado(e.getCuit(), EXPEDIENTE_EN_TRAMITE);
     
     if (se != null) {
       
       se.setEstadoSolicitud(this.esRepo.findByNombreEstado(APROBADA));
       se.setUsuario(usuario);
       se.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.seRepo.save(se);
 
       
       e.setVigente("SI");
       e.setUsuario(usuario);
       e.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.eRepo.save(e);
 
       
       Usuario u = this.uRepo.findByNombreUsuario(se.getCuit().toString());
       if (SOLICITANTE.equals(u.getRol().getNombreRol())) {
         u.setRol(this.rRepo.findByNombreRol(ENTIDAD));
         u.setUsuario("sistema");
         u.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         this.uRepo.save(u);
 
         
         this.mService.sendEntidadAprobada(e);
 
         
         this.uRepo.cerrarSesion(u.getNombreUsuario());
       } 
     } 
   }
 
 
 
   
   public void generarPdfSolicitud(Long cuit) throws IOException {
     SolicitudEntidad se = this.seRepo.findByCuit(cuit);
 
 
 
 
 
     
     ArrayList<String> servicios = new ArrayList<>();
     
     Collection<Object[]> tipoDescuentoSolicitud = this.tdsRepo.getTipoDescuentoSolicitudCheckedByCodigoSolicitud(se.getCodigoSolicitud());
     for (Object[] o : tipoDescuentoSolicitud) {
       BigDecimal id = (BigDecimal)o[0];
       TipoDescuento td = (TipoDescuento)this.tdRepo.findOne(Integer.valueOf(id.intValue()));
       servicios.add(td.getNombreTipoDescuento());
     } 
 
     
     ArrayList<String> archivos = new ArrayList<>();
     
     String ruta = String.valueOf(PATH) + "/pdf/solicitud_" + cuit.toString() + "/";
 
     
     File folder = new File(ruta);
     folder.mkdir();
 
 
 
 
 
 
 
 
 
     
     Parametro param = this.paService.findByNombreParametro(MEMBRETE_ANIO);
     String membreteAnio = (param != null && "A".equals(param.getEstado())) ? param.getDescripcion() : "";
     
     param = this.paService.findByNombreParametro(MEMBRETE_ORGANISMO);
     String membreteOrganismo = (param != null && "A".equals(param.getEstado())) ? param.getDescripcion() : "Poder Ejecutivo Nacional";
     
     param = this.paService.findByNombreParametro(SOLICITUD_DECRETO);
     String nroDecreto = (param != null && "A".equals(param.getEstado())) ? ("(" + param.getDescripcion() + ")") : "";
     
     PdfGenerator.generarPdfSolicitud(se, servicios, archivos, membreteAnio, membreteOrganismo, nroDecreto, ruta);
   }
 
 
   
   public boolean deletePdfSolicitud(Long cuit) throws IOException {
     Path ruta = Paths.get(String.valueOf(PATH) + "/pdf/solicitud_" + cuit.toString() + "/", new String[0]);
     File folder = new File(ruta.toString());
     ruta = Paths.get(String.valueOf(PATH) + "/pdf/solicitud_" + cuit.toString() + "/solicitu_" + cuit.toString() + ".pdf", new String[0]);
     
     boolean deletedPdfSuccessfully = Files.deleteIfExists(ruta);
     boolean deletedFolderSuccessfully = folder.delete();
     return (deletedPdfSuccessfully && deletedFolderSuccessfully);
   }
 
 
 
 
 
 
 
 
 
 
 
   
   public boolean deletePdf(String codigoTipoPres, String cuit, String periodo) throws IOException {
     Path ruta = Paths.get(String.valueOf(PATH) + "/pdf/solicitud_" + cuit.toString() + "/", new String[0]);
     
     ruta = Paths.get(String.valueOf(PATH) + "/pdf/solicitud_" + cuit.toString() + "/" + codigoTipoPres + "_" + cuit + "_" + periodo + ".pdf", new String[0]);
 
     
     return Files.deleteIfExists(ruta);
   }
 }
