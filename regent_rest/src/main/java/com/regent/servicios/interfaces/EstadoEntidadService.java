package com.regent.servicios.interfaces;


import java.util.Collection;

import com.regent.dtos.EstadoEntidadDTO;

public interface EstadoEntidadService {
  Collection<EstadoEntidadDTO> getAllEstadoEntidad();
  
  void nuevoEstadoEntidad(EstadoEntidadDTO paramEstadoEntidadDTO, String paramString);
  
  void updateEstadoEntidad(EstadoEntidadDTO paramEstadoEntidadDTO, String paramString);
}
