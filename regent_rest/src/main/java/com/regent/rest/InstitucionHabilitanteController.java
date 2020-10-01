package com.regent.rest;


 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.InstitucionHabilitanteDTO;
 import com.regent.servicios.interfaces.InstitucionHabilitanteService;
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
 public class InstitucionHabilitanteController
 {
   @Autowired
   private InstitucionHabilitanteService ihService;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllInstitucionHabilitante"})
   public ResponseEntity<?> getAllInstitucionHabilitante(Principal principal, @RequestParam("p") String path, Authentication authentication) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.ihService.getAllInstitucionHabilitante(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllInstitucionHabilitante", principal.getName());
       return new ResponseEntity("No logró cargar el menú \"Institución Habilitantes\". Por favor, póngase en contacto con un administrador.", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevaInstitucionHabilitante"})
   public ResponseEntity<?> nuevoInstitucionHabilitante(Principal principal, @RequestBody InstitucionHabilitanteDTO ihDTO, @RequestParam("p") String path, Authentication authentication) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.ihService.nuevaInstitucionHabilitante(ihDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevaInstitucionHabilitante", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateInstitucionHabilitante"})
   public ResponseEntity<?> updateInstitucionHabilitante(Principal principal, @RequestBody InstitucionHabilitanteDTO ihDTO, @RequestParam("p") String path, Authentication authentication) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.ihService.updateInstitucionHabilitante(ihDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateInstitucionHabilitante", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllInstitucionHabilitanteActivas"})
   public ResponseEntity<?> getAllInstitucionHabilitanteActivas(Principal principal, @RequestParam("p") String path, Authentication authentication) throws JsonProcessingException {
     try {
       return new ResponseEntity(this.ihService.getAllInstitucionHabilitanteActivas(), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllInstitucionHabilitanteActivas", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }