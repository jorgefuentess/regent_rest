package com.regent.rest;


 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.EntidadDTO;
 import com.regent.dtos.PresentacionEntidadDTO;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.PresentacionEntidad;
 import com.regent.negocio.SolicitudBaja;
 import com.regent.repositories.interfaces.PresentacionEntidadRepository;
 import com.regent.repositories.interfaces.SolicitudBajaRepository;
 import com.regent.servicios.interfaces.EntidadService;
 import com.regent.servicios.interfaces.PresentacionEntidadService;
 import com.regent.servicios.interfaces.SolicitudBajaService;
 import com.regent.util.Config;
 import com.regent.util.PathUrl;
 import com.regent.util.interfaces.ExceptionUtil;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.security.Principal;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.io.IOUtils;
 import org.apache.commons.lang3.exception.ExceptionUtils;
 import org.dom4j.DocumentException;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.Authentication;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.multipart.MultipartFile;
 
 
 
 
 
 
 @RestController
 public class EntidadController
 {
   @Autowired
   private EntidadService eService;
   @Autowired
   private PresentacionEntidadService peService;
   @Autowired
   private SolicitudBajaService sbService;
   @Autowired
   private PresentacionEntidadRepository peRepo;
   @Autowired
   private SolicitudBajaRepository sbRepo;
   @Autowired
   private HttpServletRequest request;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getEntidades"})
   public ResponseEntity<?> getEntidades(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.eService.getEntidades(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getEntidades", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllEntidades"})
   public ResponseEntity<?> getAllEntidades(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.eService.getAllEntidades(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllEntidades", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getDatosEntidad"})
   public ResponseEntity<?> getDatosEntidad(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.eService.getEntidadByCuit(principal.getName()), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getDatosEntidad", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateEntidad"})
   public ResponseEntity<?> updateEntidad(Principal principal, @RequestBody EntidadDTO eDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.eService.updateEntidad(eDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateEntidad", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/getInfoEntidades"})
   public ResponseEntity<?> getInfoEntidades(@RequestParam("v") String opcion) throws JsonProcessingException {
     try {
       return new ResponseEntity(this.eService.getInfoEntidades(opcion), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getInfoEntidades", "Sin logueo");
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getArchivosForActualizacion"})
   public ResponseEntity<?> getArchivosForActualizacion(Principal principal, Authentication authentication, @RequestParam("p") String path) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.eService.getArchivosForActualizacion(principal.getName()), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getArchivosForActualizacion", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/enviarActualizacion"})
   public ResponseEntity<?> enviarActualizacion(Principal principal, @RequestBody MultipartFile[] file, Authentication authentication, @RequestParam("p") String path) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         String error = this.eService.enviarActualizacion(file, principal.getName(), this.request);
         
         if ("".equals(error))
           return new ResponseEntity(HttpStatus.OK); 
         if ("NO_CHECK".equals(error))
           return new ResponseEntity("Debe seleccionar al menos un servicio", HttpStatus.NOT_ACCEPTABLE); 
         if ("EXTENSION".equals(error))
           return new ResponseEntity("La extensión de todos los archivos debe ser .pdf", HttpStatus.NOT_ACCEPTABLE); 
         if ("NOMBRE_ARCHIVO".equals(error))
           return new ResponseEntity("El nombre de todos los archivos debe cumplir con el formato solicitado", HttpStatus.NOT_ACCEPTABLE); 
         if ("FALTAN_ARCHIVOS".equals(error))
           return new ResponseEntity("No se puede enviar una solicitud a revisión hasta no cargar todos los archivos", HttpStatus.NOT_ACCEPTABLE); 
         if (error.contains(".pdf")) {
           return new ResponseEntity("Ya existe un archivo con el nombre " + error, HttpStatus.NOT_ACCEPTABLE);
         }
         return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
       } 
       
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/enviarActualizacion", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllPresentacionByEntidad"})
   public ResponseEntity<?> getAllPresentacionByEntidad(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("c") String codigoEntidad) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.peService.getAllPresentacionByEntidad(Integer.valueOf(codigoEntidad)), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllPresentacionByEntidad", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
 
 
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/descargarArchivoAValidar"})
   @ResponseBody
   public void descargarArchivo(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("codigo") String codigo, HttpServletRequest request, HttpServletResponse response) throws IOException {
     PresentacionEntidad pe = peRepo.findOne(Integer.valueOf(codigo));
     String nombreArchivo = pe.getNombreArchivo();
 
     
     response.setContentType("Content-type: application/pdf");
     response.addHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
 
 
     
     String downloadFile = "/home/registro/pdf/presentaciones_" + pe.getEntidad().getCuit().toString() + "/" + nombreArchivo;
     if (Config.getLocal().booleanValue()) {
       downloadFile = request.getServletContext().getRealPath(downloadFile);
     }
     FileInputStream fis = new FileInputStream(downloadFile);
     IOUtils.copy(fis, (OutputStream)response.getOutputStream());
     response.flushBuffer();
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/confirmarValidacion"})
   public ResponseEntity<?> confirmarValidacion(Principal principal, @RequestBody PresentacionEntidadDTO peDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.peService.confirmarValidacion(peDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/confirmarValidacion", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/rechazarPresentacion"})
   public ResponseEntity<?> rechazarPresentacion(Principal principal, @RequestParam("c") String codigoPresEnt, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.peService.rechazarPresentacion(codigoPresEnt, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/confirmarValidacion", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
 
 
 
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/descargarSolicitudBaja"})
   @ResponseBody
   public void descargarSolicitudBaja(Principal principal, Authentication authentication, @RequestParam("p") String path, HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
     Long cuit = Long.valueOf(principal.getName());
     
     this.eService.generarArchivoSolicitudBaja(cuit, request);
 
     
     String nombreArchivo = "solicitud_baja_" + cuit.toString() + ".pdf";
     
     response.setContentType("Content-type: application/pdf");
     response.addHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
 
 
     
     String downloadFile = "/home/registro/bajas/" + nombreArchivo;
     if (Config.getLocal().booleanValue()) {
       downloadFile = request.getServletContext().getRealPath(downloadFile);
     }
     FileInputStream fis = new FileInputStream(downloadFile);
     IOUtils.copy(fis, (OutputStream)response.getOutputStream());
     response.flushBuffer();
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/enviarSolicitudBaja"})
   public ResponseEntity<?> enviarSolicitudBaja(Principal principal, @RequestBody MultipartFile file, Authentication authentication, @RequestParam("p") String path) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.eService.enviarSolicitudBaja(file, principal.getName(), this.request);
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/enviarSolicitudBaja", principal.getName());
       if (e.getMessage() != null && e.getMessage().contains("UQ_DS_DESCUENTO"))
         return new ResponseEntity("El archivo no puede contener dos filas con el mismo número de saf, mes, año y número de certificado.<br>Si no es el caso, es posible que se esté intentando cargar una fila con número de saf, mes, año y número que ya hayan sido cargados anteriormente.", HttpStatus.NOT_ACCEPTABLE); 
       if (file == null) {
         return new ResponseEntity("Debe subir un archivo", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getSolicitudesBajaEnTramite"})
   public ResponseEntity<?> getSolicitudesBajaEnTramite(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.eService.getSolicitudesBajaEnTramite(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getSolicitudesBajaEnTramite", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
 
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/descargarSolicitudBajaFirmada"})
   @ResponseBody
   public void descargarSolicitudBajaFirmada(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("c") Integer codigoSolicitud, HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
     SolicitudBaja sb = sbRepo.findOne(codigoSolicitud);
     
     String nombreArchivo = "baja_" + sb.getEntidad().getCuit().toString();
     
     response.setContentType("Content-type: application/pdf");
     response.addHeader("Content-Disposition", "attachment; filename=" + nombreArchivo + ".pdf");
 
 
     
     String downloadFile = "/home/registro/pdf/" + nombreArchivo + "/" + nombreArchivo + ".pdf";
     if (Config.getLocal().booleanValue()) {
       downloadFile = request.getServletContext().getRealPath(downloadFile);
     }
     FileInputStream fis = new FileInputStream(downloadFile);
     IOUtils.copy(fis, (OutputStream)response.getOutputStream());
     response.flushBuffer();
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/modificarSolicitudBaja"})
   public ResponseEntity<?> modificarSolicitudBaja(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("c") Integer codigoSolicitud, @RequestParam("baja") String baja, @RequestParam("r") String nroResolucion) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.sbService.modificarSolicitudBaja(codigoSolicitud, baja, nroResolucion, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/modificarSolicitudBaja", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getSolicitudBajaVigente"})
   public ResponseEntity<?> getSolicitudBaja(Principal principal, Authentication authentication, @RequestParam("p") String path) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         Entidad e = this.eService.findEntidadByCuit(principal.getName());
         return new ResponseEntity(this.sbService.getSolicitudBajaVigente(e), HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getSolicitudBaja", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }