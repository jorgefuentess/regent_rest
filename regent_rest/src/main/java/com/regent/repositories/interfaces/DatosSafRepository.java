package com.regent.repositories.interfaces;

import com.regent.negocio.DatosSaf;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DatosSafRepository extends CrudRepository<DatosSaf, Integer> {
  @Query("SELECT ds FROM DatosSaf ds WHERE ds.saf.nSaf = :nSaf ORDER BY actualizado DESC ")
  Collection<DatosSaf> getAllDatosSafBySaf(@Param("nSaf") Integer paramInteger);
}
