package com.regent.repositories.interfaces;

import com.regent.negocio.Rol;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {
  @Query("SELECT rol.codigoRol, rol.nombreRol, rol.estado FROM Rol rol WHERE LOWER(rol.nombreRol) != 'sistema' ORDER BY rol.codigoRol asc ")
  Collection<Object[]> getRoles();
  
  @Query("SELECT rol.codigoRol, rol.nombreRol, rol.estado FROM Rol rol WHERE LOWER(rol.nombreRol) != 'sistema' AND rol.estado = 'A' ORDER BY rol.codigoRol asc ")
  Collection<Object[]> getRolesActivos();
  
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO Roles (CODIGO_ROL, NOMBRE_ROL, ESTADO, USUARIO, ACTUALIZADO) VALUES (sq_roles.nextval, :rol, :estado, :usuario, :actualizado) ", nativeQuery = true)
  void nuevoRol(@Param("rol") String paramString1, @Param("estado") String paramString2, @Param("usuario") String paramString3, @Param("actualizado") String paramString4);
  
  Rol findByNombreRol(String paramString);
  
}
