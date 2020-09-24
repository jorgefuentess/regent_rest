package com.regent.repositories.interfaces;


import com.regent.negocio.Usuario;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
  @Query("SELECT usuario.codigoUsuario, usuario.denominacion, usuario.correoElectronico, usuario.observaciones, usuario.rol.nombreRol, usuario.nombreUsuario, usuario.password, usuario.estado, usuario.cambiaClave FROM Usuario usuario WHERE usuario.rol.nombreRol!='SISTEMA' ORDER BY usuario.codigoUsuario desc ")
  Collection<Object[]> getUsuarios();
  
  Usuario findByNombreUsuario(String paramString);
  
  Usuario findByCorreoElectronico(String paramString);
  
  @Query(value = "select accesos_incorrectos from ACCESOS_USUARIO where codigo_usuario = :codigoUsuario ", nativeQuery = true)
  Integer getAccesosIncorrectos(@Param("codigoUsuario") Integer paramInteger);
  
  @Modifying
  @Transactional
  @Query(value = "insert into ACCESOS_USUARIO (codigo_usuario, accesos_incorrectos, actualizado) values (:codigoUsuario, :accesos, :actualizado) ", nativeQuery = true)
  Integer nuevoAccesoIncorrecto(@Param("codigoUsuario") Integer paramInteger1, @Param("accesos") Integer paramInteger2, @Param("actualizado") String paramString);
  
  @Modifying
  @Transactional
  @Query(value = "UPDATE ACCESOS_USUARIO SET accesos_incorrectos = :accesos, actualizado = :actualizado WHERE codigo_usuario = :codigoUsuario", nativeQuery = true)
  void updateAccesoIncorrecto(@Param("codigoUsuario") Integer paramInteger1, @Param("accesos") Integer paramInteger2, @Param("actualizado") String paramString);
  
  @Query("SELECT usuario FROM Usuario usuario WHERE UPPER(usuario.rol.nombreRol) = 'REGISTRO' AND usuario.estado = 'A' ")
  Collection<Usuario> getUsuariosRolRegistro();
  
  @Query("SELECT usuario FROM Usuario usuario WHERE UPPER(usuario.rol.nombreRol) = 'MESA DE ENTRADAS' AND usuario.estado = 'A' ")
  Collection<Usuario> getUsuariosRolMesaEntradas();
  
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM PERSISTENT_LOGINS WHERE USERNAME = :usuario", nativeQuery = true)
  void cerrarSesion(@Param("usuario") String paramString);
}