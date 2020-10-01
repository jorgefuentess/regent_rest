package com.regent.servicios.interfaces;

import com.regent.dtos.SolicitudBajaDTO;
import com.regent.negocio.Entidad;

public interface SolicitudBajaService {
  void modificarSolicitudBaja(Integer paramInteger, String paramString1, String paramString2, String paramString3);
  
  SolicitudBajaDTO getSolicitudBajaVigente(Entidad paramEntidad);
}
