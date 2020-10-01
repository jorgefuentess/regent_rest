package com.regent.servicios.interfaces;

import com.regent.dtos.FuncionDTO;
import com.regent.dtos.FuncionPorRolDTO;
import com.regent.dtos.RolDTO;
import java.util.Collection;

public interface FuncionService {
  Collection<FuncionDTO> getFunciones();
  
  Collection<FuncionDTO> getFuncionesByCodigoRol(RolDTO paramRolDTO);
  
  void nuevaFuncion(FuncionDTO paramFuncionDTO, String paramString);
  
  void updateFuncion(FuncionDTO paramFuncionDTO, String paramString);
  
  void updateFuncionPorRol(Collection<FuncionPorRolDTO> paramCollection, String paramString);
}