package com.regent.repositories.interfaces;


import com.regent.negocio.InstitucionHabilitante;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InstitucionHabilitanteRepository extends CrudRepository<InstitucionHabilitante, Integer> {
  @Query("SELECT ih.codigoInstitucionHabilitante, ih.nombreInstitucionHabilitante, ih.estado FROM InstitucionHabilitante ih ORDER BY ih.codigoInstitucionHabilitante asc ")
  Collection<Object[]> getAllInstitucionHabilitante();
  
  @Query("SELECT ih.codigoInstitucionHabilitante, ih.nombreInstitucionHabilitante, ih.estado FROM InstitucionHabilitante ih WHERE estado = 'A' ORDER BY ih.codigoInstitucionHabilitante asc ")
  Collection<Object[]> getAllInstitucionHabilitanteActivas();
}
