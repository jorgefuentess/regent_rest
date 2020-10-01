package com.regent.servicios.interfaces;

import com.regent.dtos.InstitucionHabilitanteDTO;
import java.util.Collection;

public interface InstitucionHabilitanteService {
  Collection<InstitucionHabilitanteDTO> getAllInstitucionHabilitante();
  
  void nuevaInstitucionHabilitante(InstitucionHabilitanteDTO paramInstitucionHabilitanteDTO, String paramString);
  
  void updateInstitucionHabilitante(InstitucionHabilitanteDTO paramInstitucionHabilitanteDTO, String paramString);
  
  Collection<InstitucionHabilitanteDTO> getAllInstitucionHabilitanteActivas();
}
