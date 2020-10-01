package com.regent.rest;

 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.CertificacionDTO;
 import com.regent.dtos.SafDTO;
 import com.regent.servicios.interfaces.SafService;
 import com.regent.util.Config;
 import com.regent.util.PathUrl;
 import com.regent.util.interfaces.ExceptionUtil;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.security.Principal;
 import java.text.ParseException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.io.IOUtils;
 import org.apache.commons.lang3.exception.ExceptionUtils;
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
 public class SafController
 {
   @Autowired
   private SafService sService;
   @Autowired
   private HttpServletRequest request;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllSafActivos"})
   public ResponseEntity<?> getAllSafActivos(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue())
       {
         return new ResponseEntity(this.sService.getAllSafActivos(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllSafActivos", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getInfoSaf"})
   public ResponseEntity<?> getInfoSaf(Principal principal, Authentication authentication, @RequestParam("p") String path) throws ParseException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue())
       {
         return new ResponseEntity(this.sService.getInfoSaf(principal.getName()), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getInfoSaf", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevoSaf"})
   public ResponseEntity<?> nuevoSaf(Principal principal, @RequestBody SafDTO sDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.sService.nuevoSAF(sDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevoSaf", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateSaf"})
   public ResponseEntity<?> updateSaf(Principal principal, @RequestBody SafDTO sDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.sService.updateSAF(sDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateSaf", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateInfoSaf"})
   public ResponseEntity<?> updateInfoSaf(Principal principal, Authentication authentication, @RequestBody SafDTO sDTO, @RequestParam("p") String path) throws ParseException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.sService.updateInfoSaf(principal.getName(), sDTO);
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateInfoSaf", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getOrganismosFromSaf"})
   public ResponseEntity<?> getOrganismosFromSaf(Principal principal, Authentication authentication, @RequestParam("p") String path) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.sService.getOrganismosFromSaf(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getOrganismosFromSaf", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getOrganismosWithoutSaf"})
   public ResponseEntity<?> getOrganismos(Principal principal, Authentication authentication, @RequestParam("p") String path) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.sService.getOrganismosWithoutSaf(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getOrganismosWithoutSaf", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getOrganismosWithoutUsuarioSaf"})
   public ResponseEntity<?> getOrganismosWithoutUsuarioSaf(Principal principal, Authentication authentication, @RequestParam("p") String path) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.sService.getOrganismosWithoutUsuarioSaf(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getOrganismosWithoutUsuarioSaf", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/sendCsvFile"})
   public ResponseEntity<?> sendCsvFile(Principal principal, @RequestBody MultipartFile file, Authentication authentication, @RequestParam("p") String path) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.sService.sendCsvFile(file, principal.getName(), this.request), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/sendCsvFile", principal.getName());
       if (e.getMessage() != null && e.getMessage().contains("UQ_DS_DESCUENTO"))
         return new ResponseEntity("El archivo no puede contener dos filas con el mismo número de saf, mes, año y número de certificado.<br>Si no es el caso, es posible que se esté intentando cargar una fila con número de saf, mes, año y número que ya hayan sido cargados anteriormente.", HttpStatus.NOT_ACCEPTABLE); 
       if (file == null) {
         return new ResponseEntity("Debe subir un archivo", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllDatosSafByUsuario"})
   public ResponseEntity<?> getAllDatosSafByUsuario(Principal principal, Authentication authentication, @RequestParam("p") String path) throws ParseException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue())
       {
         return new ResponseEntity(this.sService.getAllDatosSafByUsuario(principal.getName()), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllDatosSafByUsuario", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/generarCertificacionHaberes"})
   public ResponseEntity<?> generarCertificacionHaberes(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestBody CertificacionDTO cDTO) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.sService.generarCertificacionHaberes(cDTO, principal.getName(), this.request);
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/generarCertificacionHaberes", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
 
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/descargarCertificacionHaberes"})
   @ResponseBody
   public void descargarArchivo(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("dni") String dniSolicitante, HttpServletRequest request, HttpServletResponse response) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         String nombreArchivo = "certificacion_" + dniSolicitante + ".pdf";
         
         response.setContentType("Content-type: application/pdf");
         response.addHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
 
 
         
         String downloadFile = "/home/registro/certificaciones/" + nombreArchivo;
         if (Config.getLocal().booleanValue()) {
           downloadFile = request.getServletContext().getRealPath(downloadFile);
         }
         FileInputStream fis = new FileInputStream(downloadFile);
         IOUtils.copy(fis, (OutputStream)response.getOutputStream());
         response.flushBuffer();
       } 
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/descargarCertificacionHaberes", principal.getName());
     } 
   }
 
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getPorcentajeProcesamiento"})
   public ResponseEntity<?> getPorcentajeProcesamiento(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("f") String file) throws ParseException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.sService.getPorcentajeProcesamiento(file), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getPorcentajeProcesamiento", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }