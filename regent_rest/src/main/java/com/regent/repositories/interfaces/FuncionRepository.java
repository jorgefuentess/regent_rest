package com.regent.repositories.interfaces;

import com.regent.negocio.Funcion;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FuncionRepository extends CrudRepository<Funcion, Integer> {
  @Query("SELECT funcion.codigoFuncion, funcion.nombreFuncion, funcion.path, funcion.detalle, funcion.estado FROM Funcion funcion ORDER BY funcion.codigoFuncion asc ")
  Collection<Object[]> getFunciones();
  
  @Query(value = "SELECT f.codigo_funcion, f.nombre_funcion, f.path, f.detalle, CASE WHEN EXISTS (SELECT estado from rol_funcion WHERE codigo_rol=:codigoRol and codigo_funcion=f.codigo_funcion AND estado='A') \t\tTHEN 'A' ELSE 'I' END as estado from funciones f order by f.codigo_funcion asc ", nativeQuery = true)
  Collection<Object[]> getFuncionesByCodigoRol(@Param("codigoRol") String paramString);
  
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO Funciones (CODIGO_FUNCION, NOMBRE_FUNCION, PATH, DETALLE, ESTADO, USUARIO, ACTUALIZADO) VALUES (sq_funciones.nextval, :nombreFuncion, :path, :detalle, :estado, :usuario, :actualizado) ", nativeQuery = true)
  int nuevaFuncion(@Param("nombreFuncion") String paramString1, @Param("path") String paramString2, @Param("detalle") String paramString3, @Param("estado") String paramString4, @Param("usuario") String paramString5, @Param("actualizado") String paramString6);
  
  @Query(value = "select f.codigo_funcion, f.nombre_funcion, f.path, f.detalle, f.estado from rol_funcion rf inner join roles r on rf.codigo_rol=r.codigo_rol inner join funciones f on rf.codigo_funcion=f.codigo_funcion where r.nombre_rol = :rol and f.estado = 'A' and rf.estado = 'A' ", nativeQuery = true)
  Collection<Object[]> getFuncionesByRol(@Param("rol") String paramString);
  
  @Query(value = "select rf.codigo_rol, rf.codigo_funcion, rf.orden, rf.estado, rf.usuario, rf.actualizado from rol_funcion rf where rf.codigo_rol = :codigoRol and rf.codigo_funcion = :codigoFuncion", nativeQuery = true)
  Object[] getRolFuncion(@Param("codigoRol") String paramString1, @Param("codigoFuncion") String paramString2);
  
  @Modifying
  @Transactional
  @Query(value = "update rol_funcion set estado=:estado, usuario=:usuario, actualizado=:actualizado where codigo_rol = :codigoRol and codigo_funcion = :codigoFuncion", nativeQuery = true)
  int updateRolFuncion(@Param("codigoRol") String paramString1, @Param("codigoFuncion") String paramString2, @Param("estado") String paramString3, @Param("usuario") String paramString4, @Param("actualizado") String paramString5);
  
  @Modifying
  @Transactional
  @Query(value = "insert into rol_funcion (codigo_rol, codigo_funcion, orden, estado, usuario, actualizado) values (:codigoRol, :codigoFuncion, :orden, :estado, :usuario, :actualizado) ", nativeQuery = true)
  int insertRolFuncion(@Param("codigoRol") Integer paramInteger1, @Param("codigoFuncion") Integer paramInteger2, @Param("estado") String paramString1, @Param("orden") Integer paramInteger3, @Param("usuario") String paramString2, @Param("actualizado") String paramString3);
}