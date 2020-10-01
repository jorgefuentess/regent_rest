package com.regent.repositories.interfaces;

import com.regent.negocio.SolicitudEntidad;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SolicitudEntidadRepository extends CrudRepository<SolicitudEntidad, Integer> {
  SolicitudEntidad findByCuit(Long paramLong);
  
  SolicitudEntidad findByEMail(String paramString);
  
  @Query("SELECT se FROM SolicitudEntidad se WHERE se.estadoSolicitud.nombreEstado not in ('Aprobada', 'Rechazada') and se.eMail=:eMail ")
  SolicitudEntidad findSolicitudEnTramiteByEMail(@Param("eMail") String paramString);
  
  @Query(value = "SELECT DISTINCT TP.NOMBRE_ARCHIVO FROM SOLICITUD_ENTIDAD SE INNER JOIN TIPO_DESCUENTO_SOLICITUD TDS ON TDS.CODIGO_SOLICITUD = SE.CODIGO_SOLICITUD INNER JOIN PRES_POR_TIPO_ENTIDAD PPTE ON SE.CODIGO_TIPO_ENTIDAD=PPTE.CODIGO_TIPO_ENTIDAD AND PPTE.CODIGO_TIPO_DESCUENTO=TDS.CODIGO_TIPO_DESCUENTO INNER JOIN TIPO_PRESENTACIONES TP ON PPTE.CODIGO_TIPO_PRESENTACION=TP.CODIGO_TIPO_PRESENTACION WHERE SE.CODIGO_SOLICITUD = :codigoSolicitud AND TDS.ESTADO = 'A' AND PPTE.ESTADO = 'A' AND TP.ESTADO = 'A' ", nativeQuery = true)
  Collection<String> getNombresArchivosACargar(@Param("codigoSolicitud") Integer paramInteger);
  
  @Query("SELECT se FROM SolicitudEntidad se WHERE se.estadoSolicitud.nombreEstado = :estadoSolicitud and se.modificable = 'NO' ")
  Collection<SolicitudEntidad> getSolicitudesByEstado(@Param("estadoSolicitud") String paramString);
  
  @Query("SELECT se FROM SolicitudEntidad se WHERE se.estadoSolicitud.nombreEstado = :estadoSolicitud and se.modificable = 'NO' AND se.resolucion = :reso ")
  Collection<SolicitudEntidad> getSolicitudesByEstadoAndResolucion(@Param("estadoSolicitud") String paramString1, @Param("reso") String paramString2);
  
  @Query("SELECT se FROM SolicitudEntidad se WHERE se.cuit = :cuit AND se.estadoSolicitud.nombreEstado = :estado ")
  SolicitudEntidad findByCuitAndEstado(@Param("cuit") Long paramLong, @Param("estado") String paramString);
}