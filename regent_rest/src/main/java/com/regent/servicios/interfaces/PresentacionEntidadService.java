package com.regent.servicios.interfaces;

import java.text.ParseException;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.regent.dtos.PresentacionEntidadDTO;
import com.regent.negocio.Entidad;

@Service
public interface PresentacionEntidadService {
  void nuevaPresentacionEntidad(Collection<String> paramCollection, Entidad paramEntidad, String paramString) throws ParseException;
  
  void programarEnvioNotificaciones();
  
  void enviarNotificaciones();
  
  Collection<PresentacionEntidadDTO> getAllPresentacionByEntidad(Integer paramInteger) throws ParseException;
  
  void confirmarValidacion(PresentacionEntidadDTO paramPresentacionEntidadDTO, String paramString) throws ParseException;
  
  void rechazarPresentacion(String paramString1, String paramString2);
}