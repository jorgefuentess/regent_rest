package com.regent.repositories.interfaces;

import com.regent.negocio.ConsultaSQL;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ConsultaSQLRepository extends CrudRepository<ConsultaSQL, Integer> {
  @Query("SELECT c FROM ConsultaSQL c ORDER BY c.codigoConsulta asc ")
  Collection<ConsultaSQL> getConsultas();
  
  @Query("SELECT c FROM ConsultaSQL c WHERE c.estado = 'A' ORDER BY c.codigoConsulta asc ")
  Collection<ConsultaSQL> getConsultasActivas();
}
