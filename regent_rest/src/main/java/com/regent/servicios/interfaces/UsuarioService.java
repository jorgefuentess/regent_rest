package com.regent.servicios.interfaces;

import com.regent.dtos.FuncionDTO;
import com.regent.dtos.UsuarioDTO;
import com.regent.negocio.Usuario;
import java.util.Collection;

public interface UsuarioService {

	Collection<UsuarioDTO> getUsuarios();
	  
	  Collection<FuncionDTO> getFuncionesByRol(String paramString);
	  
	  Usuario findUsuarioByNombreUsuario(String paramString);
	  
	  UsuarioDTO nuevoUsuario(UsuarioDTO paramUsuarioDTO, String paramString);
	  
	  void updateUsuario(UsuarioDTO paramUsuarioDTO, String paramString);
	  
	  String cambiarClave(UsuarioDTO paramUsuarioDTO, String paramString);
	  
	  String ingresoIncorrecto(UsuarioDTO paramUsuarioDTO);
	  
	  void olvidoClave(UsuarioDTO paramUsuarioDTO);
	  
	  void inhabilitarUsuario(String paramString1, String paramString2);
	  
	  String[] enviarUsuariosNuevos(String paramString);
}
