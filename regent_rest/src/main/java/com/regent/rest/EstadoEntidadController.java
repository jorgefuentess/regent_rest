package com.regent.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.regent.dtos.EstadoEntidadDTO;
import com.regent.servicios.interfaces.EstadoEntidadService;
import com.regent.util.PathUrl;
import com.regent.util.interfaces.ExceptionUtil;

import java.security.Principal;

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
public class EstadoEntidadController {
  @Autowired
  private EstadoEntidadService eeService;
  
  @Autowired
  private ExceptionUtil eUtil;
  
  @RequestMapping(method = {RequestMethod.POST}, value = {"/getAllEstadoEntidad"})
  public ResponseEntity<?> getAllEstadoEntidad(Principal principal, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
    try {
      if (PathUrl.tienePermiso(authentication, path).booleanValue())
        return new ResponseEntity(this.eeService.getAllEstadoEntidad(), HttpStatus.OK); 
      return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      //this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtil.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/getAllEstadoEntidad", principal.getName());
      return new ResponseEntity("Ha ocurrido algerror", HttpStatus.INTERNAL_SERVER_ERROR);
    } 
  }
  
  @RequestMapping(method = {RequestMethod.POST}, value = {"/nuevoEstadoEntidad"})
  public ResponseEntity<?> nuevoEstadoEntidad(Principal principal, @RequestBody EstadoEntidadDTO eDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
    try {
      if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
        this.eeService.nuevoEstadoEntidad(eDTO, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
      } 
      return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      //this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/nuevoEstadoEntidad", principal.getName());
      return new ResponseEntity("Es posible que los datos ingresados posean algerror", HttpStatus.INTERNAL_SERVER_ERROR);
    } 
  }
  
  @RequestMapping(method = {RequestMethod.POST}, value = {"/updateEstadoEntidad"})
  public ResponseEntity<?> updateEstadoEntidad(Principal principal, @RequestBody EstadoEntidadDTO eDTO, Authentication authentication, @RequestParam("p") String path) throws JsonProcessingException {
    try {
      if (PathUrl.tienePermiso(authentication, path).booleanValue()) {
        this.eeService.updateEstadoEntidad(eDTO, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
      } 
      return new ResponseEntity("Acceso denegado", HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      //this.eUtil.registrarExcepcion(String.valueOf(ExceptionUtils.getRootCauseMessage(e.getCause())) + " // " + e.getMessage(), "/updateEstadoEntidad", principal.getName());
      return new ResponseEntity("Ha ocurrido algerror", HttpStatus.INTERNAL_SERVER_ERROR);
    } 
  }
}
