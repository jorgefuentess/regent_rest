package com.regent.rest;


 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.RolDTO;
 import com.regent.servicios.interfaces.RolService;
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
 public class RolController
 {
   @Autowired
   private RolService rService;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getRoles"})
   public ResponseEntity<?> getRoles(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue())
       {
         return new ResponseEntity(this.rService.getRoles(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getRoles", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getRolesActivos"})
   public ResponseEntity<?> getRolesActivos(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue())
       {
         return new ResponseEntity(this.rService.getRolesActivos(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getRolesActivos", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevoRol"})
   public ResponseEntity<?> nuevoRol(Principal principal, @RequestBody RolDTO rDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.rService.nuevoRol(rDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevoRol", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateRol"})
   public ResponseEntity<?> updateRol(Principal principal, @RequestBody RolDTO rDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.rService.updateRol(rDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateRol", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }
