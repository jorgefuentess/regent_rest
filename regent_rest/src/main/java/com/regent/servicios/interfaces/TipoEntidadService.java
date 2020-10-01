package com.regent.servicios.interfaces;

import com.regent.dtos.TipoEntidadDTO;
import java.util.Collection;

public interface TipoEntidadService {
  Collection<TipoEntidadDTO> getAllTipoEntidad();
  
  Collection<TipoEntidadDTO> getAllTipoEntidadActivos();
  
  void nuevoTipoEntidad(TipoEntidadDTO paramTipoEntidadDTO, String paramString);
  
  void updateTipoEntidad(TipoEntidadDTO paramTipoEntidadDTO, String paramString);
}