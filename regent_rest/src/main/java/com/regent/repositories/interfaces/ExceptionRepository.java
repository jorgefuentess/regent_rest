package com.regent.repositories.interfaces;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.regent.negocio.Usuario;

public interface ExceptionRepository extends CrudRepository<Usuario, Integer> {
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO AUDITORIA_ERRORES (usuario, servicio, fecha, error) VALUES (:usuario, :servicio, :fecha, :error)", nativeQuery = true)
  int registrarExcepcion(@Param("usuario") String paramString1, @Param("servicio") String paramString2, @Param("fecha") String paramString3, @Param("error") String paramString4);
}