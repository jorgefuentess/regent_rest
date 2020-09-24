package com.regent.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.UsuarioDTO;
 import com.regent.negocio.Usuario;
 import com.regent.servicios.interfaces.UsuarioService;
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
 public class UsuarioController
 {
   @Autowired
   private UsuarioService uService;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getUsuarios"})
   public ResponseEntity<?> getFunciones(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.uService.getUsuarios(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getUsuarios", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/getFuncionesByRol"})
   public ResponseEntity<?> getFuncionesByRol(Principal principal) throws JsonProcessingException {
     try {
       Usuario u = this.uService.findUsuarioByNombreUsuario(principal.getName());
       String rol = "";
       if (u != null) {
         rol = u.getRol().getNombreRol();
       }
       if (!"SI".equals(u.getCambiaClave())) {
         return new ResponseEntity(this.uService.getFuncionesByRol(rol), HttpStatus.OK);
       }
       return new ResponseEntity("CAMBIACLAVE", HttpStatus.OK);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getFuncionesByRol", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/getUserName"})
   public ResponseEntity<?> getUserName(Principal principal) throws JsonProcessingException {
     try {
       return new ResponseEntity(principal.getName(), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getUserName", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevoUsuario"})
   public ResponseEntity<?> nuevoUsuario(Principal principal, @RequestBody UsuarioDTO uDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         UsuarioDTO u2DTO = this.uService.nuevoUsuario(uDTO, principal.getName());
         return new ResponseEntity(u2DTO, HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevoUsuario", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateUsuario"})
   public ResponseEntity<?> updateUsuario(Principal principal, @RequestBody UsuarioDTO uDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.uService.updateUsuario(uDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateUsuario", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/cambiarClave"})
   public ResponseEntity<?> cambiarClave(Principal principal, @RequestBody UsuarioDTO uDTO) throws JsonProcessingException {
     try {
       String cambio = this.uService.cambiarClave(uDTO, principal.getName());
       return new ResponseEntity(cambio, HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/cambiarClave", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/getLogueado"})
   public ResponseEntity<?> getLogueado(Principal principal) throws JsonProcessingException {
     try {
       return new ResponseEntity(Boolean.valueOf((principal != null)), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getLogueado", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/ingresoIncorrecto"})
   public ResponseEntity<?> ingresoIncorrecto(@RequestBody UsuarioDTO uDTO) throws JsonProcessingException {
     try {
       return new ResponseEntity(this.uService.ingresoIncorrecto(uDTO), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/ingresoIncorrecto", uDTO.getUsuario());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/olvidoClave"})
   public ResponseEntity<?> olvidoClave(@RequestBody UsuarioDTO uDTO) throws JsonProcessingException {
     try {
       this.uService.olvidoClave(uDTO);
       return new ResponseEntity(HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/olvidoClave", uDTO.getUsuario());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/enviarUsuariosNuevos"})
   public ResponseEntity<?> enviarUsuariosNuevos(Principal principal) throws JsonProcessingException {
     try {
       Usuario u = this.uService.findUsuarioByNombreUsuario(principal.getName());
       if (u != null && "ADMINISTRACION".equals(u.getRol().getNombreRol())) {
         String[] respuesta = this.uService.enviarUsuariosNuevos(principal.getName());
         String retorno = "Se crearon " + respuesta[0] + " usuarios de entidades y " + respuesta[1] + " usuarios de saf.";
         return new ResponseEntity(retorno, HttpStatus.OK);
       } 
       return new ResponseEntity(HttpStatus.UNAUTHORIZED);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/enviarUsuariosNuevos", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }
