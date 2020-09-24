package com.regent.repositories.interfaces;

import com.regent.negocio.Resolucion;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ResolucionRepository extends CrudRepository<Resolucion, Integer> {
  @Query("SELECT re FROM Resolucion re ORDER BY re.codigoResolucion DESC ")
  Collection<Resolucion> getResoluciones();
  
  Resolucion findByEntidadCuit(Long paramLong);
  
  @Query(value = "SELECT max(r.codigo_resolucion) FROM resoluciones r ", nativeQuery = true)
  Integer getMaxCodigoResolucion();
  
  @Query(value = "SELECT r.nro_resolucion FROM resoluciones rWHERE r.codigo_resolucion = (SELECT max(r.codigo_resolucion) FROM resoluciones rWHERE r.codigo_entidad = :codEntidad) ", nativeQuery = true)
  String getMaxNroResolucion(@Param("codEntidad") Integer paramInteger);
}