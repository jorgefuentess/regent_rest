package com.regent.servicios.interfaces;

import com.regent.dtos.RolDTO;
import java.util.Collection;

public interface RolService {
  Collection<RolDTO> getRoles();
  
  Collection<RolDTO> getRolesActivos();
  
  void nuevoRol(RolDTO paramRolDTO, String paramString);
  
  void updateRol(RolDTO paramRolDTO, String paramString);
}
