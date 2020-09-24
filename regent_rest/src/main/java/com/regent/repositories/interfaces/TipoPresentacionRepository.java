package com.regent.repositories.interfaces;

import com.regent.negocio.TipoPresentacion;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TipoPresentacionRepository extends CrudRepository<TipoPresentacion, Integer> {
  @Query("SELECT tp.codigoTipoPresentacion, tp.nombreTipoPresentacion, tp.nombreArchivo, tp.vence, tp.estado FROM TipoPresentacion tp ORDER BY tp.codigoTipoPresentacion asc ")
  Collection<Object[]> getAllTipoPresentacion();
  
  TipoPresentacion findByNombreArchivo(String paramString);
}
