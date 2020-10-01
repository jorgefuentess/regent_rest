package com.regent.repositories.interfaces;

import com.regent.negocio.EstadoSolicitud;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EstadoSolicitudRepository extends CrudRepository<EstadoSolicitud, Integer> {
  @Query("SELECT e.codigoEstado, e.nombreEstado, e.estado FROM EstadoSolicitud e ORDER BY e.codigoEstado asc ")
  Collection<Object[]> getAllEstadoSolicitud();
  
  EstadoSolicitud findByNombreEstado(String paramString);
}
