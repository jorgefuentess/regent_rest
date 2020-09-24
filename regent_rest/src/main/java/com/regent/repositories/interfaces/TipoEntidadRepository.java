package com.regent.repositories.interfaces;

import com.regent.negocio.TipoEntidad;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TipoEntidadRepository extends CrudRepository<TipoEntidad, Integer> {
  @Query("SELECT te.codigoTipoEntidad, te.nombreTipoEntidad, te.estado FROM TipoEntidad te ORDER BY te.codigoTipoEntidad asc ")
  Collection<Object[]> getAllTipoEntidad();
  
  @Query("SELECT te.codigoTipoEntidad, te.nombreTipoEntidad, te.estado FROM TipoEntidad te WHERE te.estado = 'A' ORDER BY te.codigoTipoEntidad asc ")
  Collection<Object[]> getAllTipoEntidadActivo();
}