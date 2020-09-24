package com.regent.repositories.interfaces;

import com.regent.negocio.SolicitudBaja;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SolicitudBajaRepository extends CrudRepository<SolicitudBaja, Integer> {
  @Query("SELECT sb FROM SolicitudBaja sb WHERE sb.baja IS NULL ORDER BY sb.codigoSolicitud DESC ")
  Collection<SolicitudBaja> getSolicitudesBajaEnTramite();
  
  @Query("SELECT sb FROM SolicitudBaja sb WHERE sb.baja IS NULL AND sb.entidad.codigoEntidad = :codigoEntidad AND sb.codigoSolicitud >= ALL(select sb2.codigoSolicitud from SolicitudBaja sb2 where sb2.baja IS NULL and sb2.entidad.codigoEntidad = :codigoEntidad) ")
  SolicitudBaja findSolicitudBajaVigente(@Param("codigoEntidad") Integer paramInteger);
}