package com.regent.servicios.interfaces;

import com.regent.dtos.EstadoSolicitudDTO;
import java.util.Collection;

public interface EstadoSolicitudService {
  Collection<EstadoSolicitudDTO> getAllEstadoSolicitud();
  
  void nuevoEstadoSolicitud(EstadoSolicitudDTO paramEstadoSolicitudDTO, String paramString);
  
  void updateEstadoSolicitud(EstadoSolicitudDTO paramEstadoSolicitudDTO, String paramString);
}

