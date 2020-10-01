package com.regent.servicios.interfaces;

import com.regent.dtos.TipoDescuentoDTO;
import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
import java.util.Collection;

public interface TipoDescuentoService {
  Collection<TipoDescuentoDTO> getAllTipoDescuento();
  
  Collection<TipoDescuentoDTO> getAllTipoDescuentoVigente();
  
  void nuevoTipoDescuento(TipoDescuentoDTO paramTipoDescuentoDTO, String paramString);
  
  void updateTipoDescuento(TipoDescuentoDTO paramTipoDescuentoDTO, String paramString);
  
  Collection<TipoDescuentoTipoEntidadDTO> getAllTipoEntidadTipoDescuento();
  
  Collection<TipoDescuentoTipoEntidadDTO> getTipoEntidadWithCheckedFlag();
  
  Collection<TipoDescuentoTipoEntidadDTO> getTipoEntidadTipoDescuentoByEntidad();
}

