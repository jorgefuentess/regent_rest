package com.regent.rest;


 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.regent.dtos.TipoDescuentoDTO;
 import com.regent.servicios.interfaces.TipoDescuentoService;
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
 public class TipoDescuentoController
 {
   @Autowired
   private TipoDescuentoService teService;
   @Autowired
   private ExceptionUtil eUtil;
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllTipoDescuento"})
   public ResponseEntity<?> getAllTipoDescuento(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.teService.getAllTipoDescuento(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllTipoDescuento", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.GET}, value = {"/getAllTipoDescuentoVigente"})
   public ResponseEntity<?> getAllTipoDescuentoVigente() throws JsonProcessingException {
     try {
       return new ResponseEntity(this.teService.getAllTipoDescuentoVigente(), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllTipoDescuentoVigente", "Sin logueo");
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevoTipoDescuento"})
   public ResponseEntity<?> nuevoTipoDescuento(Principal principal, @RequestBody TipoDescuentoDTO tdDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.teService.nuevoTipoDescuento(tdDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevoTipoDescuento", principal.getName());
       return new ResponseEntity("Es posible que los datos ingresados posean algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/updateTipoDescuento"})
   public ResponseEntity<?> updateTipoDescuento(Principal principal, @RequestBody TipoDescuentoDTO tdDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         this.teService.updateTipoDescuento(tdDTO, principal.getName());
         return new ResponseEntity(HttpStatus.OK);
       } 
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateTipoDescuento", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllTipoEntidadTipoDescuento"})
   public ResponseEntity<?> getAllTipoEntidadTipoDescuento(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       return new ResponseEntity(this.teService.getAllTipoEntidadTipoDescuento(), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllTipoEntidadTipoDescuento", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getTipoEntidadTipoDescuentoByEntidad"})
   public ResponseEntity<?> getTipoEntidadTipoDescuentoForEntidad(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       return new ResponseEntity(this.teService.getTipoEntidadTipoDescuentoByEntidad(), HttpStatus.OK);
     } catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getTipoEntidadTipoDescuentoByEntidad", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
   
   @RequestMapping(method = {RequestMethod.POST}, value = {"/getTipoEntidadWithCheckedFlag"})
   public ResponseEntity<?> getTipoEntidadWithCheckedFlag(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
     try {
       if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
         return new ResponseEntity(this.teService.getTipoEntidadWithCheckedFlag(), HttpStatus.OK);
       }
       return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
     }
     catch (Exception e) {
       this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getTipoEntidadWithCheckedFlag", principal.getName());
       return new ResponseEntity("Ha ocurrido algún error", HttpStatus.INTERNAL_SERVER_ERROR);
     } 
   }
 }
