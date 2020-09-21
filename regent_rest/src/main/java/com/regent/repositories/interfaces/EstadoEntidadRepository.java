package com.regent.repositories.interfaces;


import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.regent.negocio.EstadoEntidad;

public interface EstadoEntidadRepository extends CrudRepository<EstadoEntidad, Integer> {
  @Query("SELECT e.codigoEstado, e.nombreEstado, e.estado FROM EstadoEntidad e ORDER BY e.codigoEstado asc ")
  Collection<Object[]> getAllEstadoEntidad();
  
  EstadoEntidad findByNombreEstado(String paramString);
}
