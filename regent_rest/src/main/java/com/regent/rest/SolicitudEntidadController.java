package com.regent.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
 import com.google.gson.Gson;
 import com.regent.dtos.ResolucionDTO;
 import com.regent.dtos.SolicitudEntidadDTO;
 import com.regent.negocio.SolicitudEntidad;
 import com.regent.servicios.interfaces.SolicitudEntidadService;
 import com.regent.util.Config;
 import com.regent.util.PathUrl;
 import com.regent.util.interfaces.ExceptionUtil;
 import java.io.ByteArrayOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.security.Principal;
 import java.util.zip.ZipEntry;
 import java.util.zip.ZipOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.io.IOUtils;
 import org.apache.commons.lang3.exception.ExceptionUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.Authentication;
 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.multipart.MultipartFile;
 
 
 
 
 
 
 @RestController
 public class SolicitudEntidadController
 {
   @Autowired
   private SolicitudEntidadService seService;
   @Autowired
   private HttpServletRequest request;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevaSolicitudEntidad"})
   public ResponseEntity<?> nuevaSolicitudEntidad(@RequestBody SolicitudEntidadDTO seDTO) throws IOException {
     try {
       String error = this.seService.nuevaSolicitudEntidad(seDTO, "sistema");
       if ("".equals(error))
         return new ResponseEntity(HttpStatus.OK); 
       if ("MAIL_SOLICITUD".equals(error))
         return new ResponseEntity("Ya existe una Solicitud en proceso con ese correo electrónico", 
             HttpStatus.NOT_ACCEPTABLE); 
       if ("CUIT_SOLICITUD".equals(error))
         return new ResponseEntity(
             "Ya existe una Solicitud en proceso para una entidad con ese número de CUIT", 
             HttpStatus.NOT_ACCEPTABLE); 
       if ("MAIL_ENTIDAD".equals(error))
         return new ResponseEntity("Ya existe una Entidad con ese correo electrónico", 
             HttpStatus.NOT_ACCEPTABLE); 
       if ("CUIT_ENTIDAD".equals(error))
         return new ResponseEntity("Ya existe una Entidad con ese número de CUIT", HttpStatus.NOT_ACCEPTABLE); 
       if ("NO_CHECK".equals(error)) {
         return new ResponseEntity("Debe seleccionar al menos un servicio", HttpStatus.NOT_ACCEPTABLE);
       }
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", 
           HttpStatus.INTERNAL_SERVER_ERROR);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/nuevaSolicitudEntidad", "sistema");
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", 
           HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getSolicitudEntidad"})
   public ResponseEntity<?> getSolicitudEntidad(Principal principal, Authentication authentication, @RequestParam("p") String path) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(
             this.seService.findSolicitudEntidadByCuit(Long.valueOf(principal.getName()), this.request), 
             HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/getSolicitudEntidad", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/guardarEdicionSolicitud"})
   public ResponseEntity<?> guardarEdicionSolicitud(Principal principal, @RequestBody MultipartFile[] file, Authentication authentication, @RequestParam("p") String path, @RequestParam("seDTO") Object seDTO) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         SolicitudEntidadDTO solicitud = (SolicitudEntidadDTO)(new Gson()).fromJson(seDTO.toString(), SolicitudEntidadDTO.class);
         
         String error = this.seService.guardarEdicionSolicitud(solicitud, file, principal.getName(), this.request);
         
         if ("".equals(error))
           return new ResponseEntity(HttpStatus.OK); 
         if ("NO_CHECK".equals(error))
           return new ResponseEntity("Debe seleccionar al menos un servicio", HttpStatus.NOT_ACCEPTABLE); 
         if ("EXTENSION".equals(error))
           return new ResponseEntity("La extensión de todos los archivos debe ser .pdf", 
               HttpStatus.NOT_ACCEPTABLE); 
         if ("NOMBRE_ARCHIVO".equals(error))
           return new ResponseEntity(
               "Por favor, verifique que el nombre de todos los archivos corresponda con el formato solicitado.", 
               HttpStatus.NOT_ACCEPTABLE); 
         if (error.contains("PERIODO_INVALIDO")) {
           return new ResponseEntity("Por favor, verifique que el período de los archivos que intenta subir sea correcto.", 
               HttpStatus.NOT_ACCEPTABLE);
         }
         return new ResponseEntity(error, 
             HttpStatus.INTERNAL_SERVER_ERROR);
       } 
       
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/guardarEdicionSolicitud", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/enviarARevisionSolicitud"})
   public ResponseEntity<?> enviarARevisionSolicitud(Principal principal, @RequestBody MultipartFile[] file, Authentication authentication, @RequestParam("p") String path, @RequestParam("seDTO") Object seDTO) {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         SolicitudEntidadDTO solicitud = (SolicitudEntidadDTO)(new Gson()).fromJson(seDTO.toString(), SolicitudEntidadDTO.class);
         
         String error = this.seService.enviarARevisionSolicitud(solicitud, file, principal.getName(), this.request);
         
         if ("".equals(error))
           return new ResponseEntity(HttpStatus.OK); 
         if ("NO_CHECK".equals(error))
           return new ResponseEntity("Debe seleccionar al menos un servicio", HttpStatus.NOT_ACCEPTABLE); 
         if ("EXTENSION".equals(error))
           return new ResponseEntity("La extensión de todos los archivos debe ser .pdf", 
               HttpStatus.NOT_ACCEPTABLE); 
         if ("NOMBRE_ARCHIVO".equals(error))
           return new ResponseEntity(
               "El nombre de todos los archivos debe cumplir con el formato solicitado", 
               HttpStatus.NOT_ACCEPTABLE); 
         if ("FALTAN_ARCHIVOS".equals(error))
           return new ResponseEntity(
               "No se puede enviar una solicitud a revisión hasta no cargar todos los archivos", 
               HttpStatus.NOT_ACCEPTABLE); 
         if ("MAIL_OR_FILE_EXCEPTION".equals(error)) {
           return new ResponseEntity(error, HttpStatus.OK);
         }
         return new ResponseEntity(error, 
             HttpStatus.INTERNAL_SERVER_ERROR);
       } 
       
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/enviarARevisionSolicitud", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getSolicitudesForAprobacion"})
   public ResponseEntity<?> getSolicitudesForAprobacion(Principal principal, Authentication authentication, @RequestParam("p") String path) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.seService.getSolicitudesForAprobacion(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/getSolicitudesForAprobacion", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getSolicitudesForCaratulacion"})
   public ResponseEntity<?> getSolicitudesForCaratulacion(Principal principal, Authentication authentication, @RequestParam("p") String path) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.seService.getSolicitudesForCaratulacion(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/getSolicitudesForCaratulacion", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getSolicitudesForResolucion"})
   public ResponseEntity<?> getSolicitudesForResolucion(Principal principal, Authentication authentication, @RequestParam("p") String path) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.seService.getSolicitudesForResolucion(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/getSolicitudesForResolucion", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
 
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/descargarArchivo"})
   @ResponseBody
   public void descargarArchivo(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("codigo") String codigo, HttpServletRequest request, HttpServletResponse response) throws IOException {
     String ruta = "/home/registro/zip/";
     
     SolicitudEntidad se = this.seService.findSolicitudEntidadByCodigoSolicitud(Integer.valueOf(codigo));
     String nombreArchivo = "presentaciones_" + se.getCuit().toString() + ".zip";
 
     
     response.setContentType("Content-type: application/octet-stream");
     response.addHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
 
     
     String downloadDir = "/home/registro/pdf/solicitud_" + se.getCuit().toString() + "/";
     if (Config.getLocal().booleanValue()) {
       downloadDir = request.getServletContext().getRealPath(downloadDir);
     }
     File folder = new File(downloadDir);
     
     FileOutputStream fos = new FileOutputStream(request.getServletContext().getRealPath(String.valueOf(ruta) + nombreArchivo));
     ZipOutputStream zipOut = new ZipOutputStream(fos);
     
     ByteArrayOutputStream retorno = new ByteArrayOutputStream();
     
     String nombreSoli = "solicitu_" + se.getCuit().toString() + ".pdf"; byte b; int i;
     File[] arrayOfFile;
     for (i = (arrayOfFile = folder.listFiles()).length, b = 0; b < i; ) { File file = arrayOfFile[b];
       if (!nombreSoli.equals(file.getName())) {
         FileInputStream fileInputStream = new FileInputStream(file);
         ZipEntry zipEntry = new ZipEntry(file.getName());
         zipOut.putNextEntry(zipEntry);
         
         byte[] bytes = new byte[1024];
         int length;
         while ((length = fileInputStream.read(bytes)) >= 0) {
           zipOut.write(bytes, 0, length);
           retorno.write(bytes);
         } 
         fileInputStream.close();
       }  b++; }
     
     zipOut.close();
     fos.close();
 
     
     FileInputStream fis = new FileInputStream(request.getServletContext().getRealPath(String.valueOf(ruta) + nombreArchivo));
     IOUtils.copy(fis, (OutputStream)response.getOutputStream());
     response.flushBuffer();
   }
 
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/registrarCambioSolicitud"})
   public ResponseEntity<?> registrarCambioSolicitud(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("c") String codigo, @RequestParam("t") String tipo, @RequestParam("o") String obs) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         String error = this.seService.registrarCambioSolicitud(Integer.valueOf(codigo), tipo, principal.getName(), 
             obs);
         if ("".equals(error))
           return new ResponseEntity(HttpStatus.OK); 
         if ("ERROR".equals(error)) {
           return new ResponseEntity("Ha ocurrido algún error", HttpStatus.NOT_ACCEPTABLE);
         }
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/registrarCambioSolicitud", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/caratularSolicitud"})
   public ResponseEntity<?> caratularSolicitud(Principal principal, Authentication authentication, @RequestParam("p") String path, @RequestParam("cod") String codigo) throws IOException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         String error = this.seService.caratularSolicitud(Integer.valueOf(codigo), principal.getName());
         if ("".equals(error))
           return new ResponseEntity(HttpStatus.OK); 
         if ("ERROR".equals(error)) {
           return new ResponseEntity("Ha ocurrido algún error", HttpStatus.NOT_ACCEPTABLE);
         }
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/caratularSolicitud", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
 
 
 
   
   @Transactional
   @RequestMapping(method = {RequestMethod.POST}, value = {"/resolverSolicitud"})
   public ResponseEntity<?> resolverSolicitud(Principal principal, @RequestBody ResolucionDTO rDTO, Authentication authentication, @RequestParam("p") String path, @RequestParam("cod") Integer codigoSolicitud, @RequestParam("m") Integer mesCierre, @RequestParam("f") String fechaInicio, @RequestParam("a") Integer mandato, @RequestParam("o") String observaciones) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.seService.resolverSolicitud(rDTO, codigoSolicitud, mesCierre, fechaInicio, mandato, observaciones, 
             principal.getName(), this.request);
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       String traceS = "";
       int counter = 40;
       
       Throwable[] trace = ExceptionUtils.getThrowables(e); byte b; int i; Throwable[] arrayOfThrowable1;
       for (i = (arrayOfThrowable1 = trace).length, b = 0; b < i; ) { Throwable t = arrayOfThrowable1[b];
         if (counter > 0) {
           traceS = String.valueOf(traceS) + " " + t;
           counter--; b++;
         } 
         break; }
       
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage() + " ||| " + traceS, 
           "/resolverSolicitud", principal.getName());
       
       return new ResponseEntity("Ha ocurrido algún error", 
           HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/generarPdfSolicitud"})
   public void generarPdfSolicitud(Principal principal) throws IOException {
     Long cuit = Long.valueOf(principal.getName());
     this.seService.generarPdfSolicitud(cuit);
   }
 
 
 
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/getPdfSolicitud"}, produces = {"application/pdf"})
   @ResponseBody
   public void getPdfSolicitud(Principal principal, Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException {
     Long cuit = Long.valueOf(principal.getName());
     String nombreArchivo = "solicitu_" + cuit.toString() + ".pdf";
     String downloadFile = "/home/registro/pdf/solicitud_" + cuit.toString() + "/" + nombreArchivo;
 
     
     response.setContentType("Content-type: application/pdf");
     response.addHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
     
     FileInputStream fis = new FileInputStream(downloadFile);
     IOUtils.copy(fis, (OutputStream)response.getOutputStream());
     response.flushBuffer();
   }
 
   
   @RequestMapping(method = {RequestMethod.DELETE}, value = {"/deletePdfSolicitud"})
   @ResponseBody
   public ResponseEntity<?> deletePdfSolicitud(Principal principal, Authentication authentication, HttpServletRequest request) {
     Long cuit = Long.valueOf(principal.getName());
     try {
       if (this.seService.deletePdfSolicitud(cuit)) {
         return new ResponseEntity("Success.", HttpStatus.OK);
       }
     } catch (IOException e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), 
           "/deletePdfSolicitud", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
     return new ResponseEntity("El pdf no se encuentra en el sistema.", HttpStatus.NOT_FOUND);
   }
 }
