package com.regent.servicios.interfaces;

import com.regent.dtos.ParametroDTO;
import com.regent.negocio.Parametro;
import java.util.Collection;

public interface ParametroService {
  Collection<ParametroDTO> getParametros();
  
  void nuevoParametro(ParametroDTO paramParametroDTO, String paramString);
  
  void updateParametro(ParametroDTO paramParametroDTO, String paramString);
  
  Parametro findByNombreParametro(String paramString);
}
