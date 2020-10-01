package com.regent.rest;


 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.IntimacionDTO;
 import com.regent.servicios.interfaces.IntimacionService;
 import com.regent.util.PathUrl;
 import com.regent.util.interfaces.ExceptionUtil;
 import java.security.Principal;
 import org.apache.commons.lang3.exception.ExceptionUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.Authentication;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;
 
 
 
 
 
 @RestController
 public class IntimacionController
 {
   @Autowired
   private IntimacionService iService;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getLastIntimacionOfEntidad"})
   public ResponseEntity<?> getLastIntimacionOfEntidad(Principal principal, Authentication authentication, @RequestParam("c") String codigoEntidad, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.iService.getIntimacion(codigoEntidad), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getLastIntimacionOfEntidad", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevaIntimacion"})
   public ResponseEntity<?> nuevaIntimacion(Principal principal, @RequestBody IntimacionDTO iDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.iService.nuevaIntimacion(iDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevaIntimacion", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateIntimacion"})
   public ResponseEntity<?> updateIntimacion(Principal principal, @RequestBody IntimacionDTO iDTO, Authentication authentication, @RequestParam("p") String path, @RequestParam("ce") String cerrar) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         if (Integer.valueOf(cerrar).intValue() == 0) {
           this.iService.updateIntimacion(iDTO, principal.getName());
         } else if (Integer.valueOf(cerrar).intValue() == 1) {
           this.iService.cerrarIntimacion(iDTO, principal.getName());
         } 
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateIntimacion", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }
