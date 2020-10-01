package com.regent.servicios.interfaces;

import com.regent.dtos.ResolucionDTO;
import com.regent.negocio.Entidad;
import com.regent.negocio.Resolucion;
import com.regent.negocio.SolicitudEntidad;
import java.text.ParseException;
import java.util.Collection;

public interface ResolucionService {
  Collection<ResolucionDTO> getResoluciones();
  
  void nuevaResolucion(ResolucionDTO paramResolucionDTO, String paramString) throws ParseException;
  
  void updateResolucion(ResolucionDTO paramResolucionDTO, String paramString) throws ParseException;
  
  Resolucion nuevaResolucion(ResolucionDTO paramResolucionDTO, SolicitudEntidad paramSolicitudEntidad, Entidad paramEntidad, String paramString) throws ParseException;
}
