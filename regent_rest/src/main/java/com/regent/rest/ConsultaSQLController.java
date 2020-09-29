package com.regent.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.ConsultaSQLDTO;
 import com.regent.servicios.interfaces.ConsultaSQLService;
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
 public class ConsultaSQLController
 {
   @Autowired
   private ConsultaSQLService cService;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getConsultasLite"})
   public ResponseEntity<?> getConsultasLite(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.cService.getConsultasLite(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getConsultasLite", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getConsultasActivas"})
   public ResponseEntity<?> getConsultasActivas(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.cService.getConsultasActivas(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getConsultasActivas", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/getConsultaById"})
   public ResponseEntity<?> getConsultaById(Principal principal, Authentication authentication, @RequestParam("c") Integer codigoConsulta) throws JsonProcessingException {
     try {
       String pathPadre = "/gestionConsulta.html";
       if (PathUrl.tienePermiso(authentication, pathPadre).booleanValue()) {
         return new ResponseEntity(this.cService.getConsultaById(codigoConsulta), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getConsultas", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevaConsulta"})
   public ResponseEntity<?> nuevaFuncion(Principal principal, @RequestBody ConsultaSQLDTO cDTO, Authentication authentication) throws JsonProcessingException {
     try {
       String pathPadre = "/gestionConsulta.html";
       if (PathUrl.tienePermiso(authentication, pathPadre).booleanValue()) {
         this.cService.nuevaConsulta(cDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevaConsulta", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateConsulta"})
   public ResponseEntity<?> updateFuncion(Principal principal, @RequestBody ConsultaSQLDTO cDTO, Authentication authentication) throws JsonProcessingException {
     try {
       String pathPadre = "/gestionConsulta.html";
       if (PathUrl.tienePermiso(authentication, pathPadre).booleanValue()) {
         this.cService.updateConsulta(cDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateConsulta", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/ejecutarConsulta"})
   public ResponseEntity<?> ejecutarConsulta(Principal principal, @RequestBody ConsultaSQLDTO cDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.cService.ejecutarConsulta(cDTO, principal.getName()), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/ejecutarConsulta", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }