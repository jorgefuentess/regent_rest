package com.regent.repositories.interfaces;

import com.regent.negocio.Intimacion;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IntimacionRepository extends CrudRepository<Intimacion, Integer> {
  @Query("SELECT i FROM Intimacion i WHERE i.entidad.codigoEntidad = :codigoEntidad AND i.estado = 'A' ")
  Intimacion getIntimacionVigenteByCodigoEntidad(@Param("codigoEntidad") Integer paramInteger);
  
  @Query("select i from Intimacion i where i.estado = 'A' and i.fechaRecepcion = TO_DATE(:fechaRecepcion, 'DD/MM/YYYY') ")
  Collection<Intimacion> getIntimacionesParaAviso(@Param("fechaRecepcion") String paramString);
}
