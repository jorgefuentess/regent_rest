package com.regent.servicios.interfaces;

import com.regent.dtos.ConsultaSQLDTO;
import java.util.Collection;

public interface ConsultaSQLService {
  Collection<ConsultaSQLDTO> getConsultasLite();
  
  void nuevaConsulta(ConsultaSQLDTO paramConsultaSQLDTO, String paramString);
  
  void updateConsulta(ConsultaSQLDTO paramConsultaSQLDTO, String paramString);
  
  ConsultaSQLDTO getConsultaById(Integer paramInteger);
  
  Collection<ConsultaSQLDTO> getConsultasActivas();
  
  Collection<Object[]> ejecutarConsulta(ConsultaSQLDTO paramConsultaSQLDTO, String paramString);
}
