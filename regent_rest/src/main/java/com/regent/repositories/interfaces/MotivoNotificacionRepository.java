package com.regent.repositories.interfaces;

import com.regent.negocio.MotivoNotificacion;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MotivoNotificacionRepository extends CrudRepository<MotivoNotificacion, Integer> {
  @Query("SELECT mn.codigoMotivoNotif, mn.nombreMotivoNotif, mn.estado FROM MotivoNotificacion mn ORDER BY mn.codigoMotivoNotif asc ")
  Collection<Object[]> getAllMotivoNotificacion();
  
  MotivoNotificacion findByNombreMotivoNotif(String paramString);
}
