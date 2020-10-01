package com.regent.servicios.interfaces;

import com.regent.dtos.PresentacionPorTipoEntidadDTO;
import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
import com.regent.dtos.TipoPresentacionDTO;
import java.util.Collection;

public interface TipoPresentacionService {
  Collection<TipoPresentacionDTO> getAllTipoPresentacion();
  
  void nuevoTipoPresentacion(TipoPresentacionDTO paramTipoPresentacionDTO, String paramString);
  
  void updateTipoPresentacion(TipoPresentacionDTO paramTipoPresentacionDTO, String paramString);
  
  Collection<PresentacionPorTipoEntidadDTO> getTipoPresentacionByCodigoTipoEntidad(TipoDescuentoTipoEntidadDTO paramTipoDescuentoTipoEntidadDTO);
  
  void updateTipoPresentacionPorTipoEntidad(Collection<PresentacionPorTipoEntidadDTO> paramCollection, String paramString);
}
