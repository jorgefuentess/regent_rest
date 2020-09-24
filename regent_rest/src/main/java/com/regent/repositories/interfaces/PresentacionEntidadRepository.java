package com.regent.repositories.interfaces;

import com.regent.negocio.PresentacionEntidad;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PresentacionEntidadRepository extends CrudRepository<PresentacionEntidad, Integer> {
  PresentacionEntidad findByNombreArchivo(String paramString);
  
  @Query(value = "select pe.* from presentaciones_entidad pe INNER JOIN tipo_presentaciones tp on pe.codigo_tipo_presentacion=tp.CODIGO_TIPO_PRESENTACION where tp.NOMBRE_ARCHIVO = :nombreArchivo and tp.VENCE='SI' and sysdate >= fecha_vencimiento-15 and pe.vigente = 'SI' and pe.if_gedo is not null and not exists (select 1 from NOTIFICACIONES_DE_CAMBIO M INNER JOIN MOTIVOS_NOTIFICACION MN ON M.CODIGO_MOTIVO_NOTIF=MN.CODIGO_MOTIVO_NOTIF where M.codigo_entidad=pe.codigo_entidad and MN.NOMBRE_MOTIVO_NOTIF=:motivo)", nativeQuery = true)
  Collection<PresentacionEntidad> getPresentacionesParaPrimeraNotificacion(@Param("nombreArchivo") String paramString1, @Param("motivo") String paramString2);
  
  @Query(value = "select pe.* from presentaciones_entidad pe INNER JOIN tipo_presentaciones tp on pe.codigo_tipo_presentacion=tp.CODIGO_TIPO_PRESENTACION where tp.NOMBRE_ARCHIVO = :nombreArchivo and tp.VENCE='SI' and sysdate >= fecha_vencimiento and pe.vigente = 'SI' and pe.if_gedo is not null and not exists (select 1 from NOTIFICACIONES_DE_CAMBIO M INNER JOIN MOTIVOS_NOTIFICACION MN ON M.CODIGO_MOTIVO_NOTIF=MN.CODIGO_MOTIVO_NOTIF where M.codigo_entidad=pe.codigo_entidad and MN.NOMBRE_MOTIVO_NOTIF=:motivo)", nativeQuery = true)
  Collection<PresentacionEntidad> getPresentacionesParaSegundaNotificacionMandato(@Param("nombreArchivo") String paramString1, @Param("motivo") String paramString2);
  
  @Query(value = "select pe.* from presentaciones_entidad pe INNER JOIN tipo_presentaciones tp on pe.codigo_tipo_presentacion=tp.CODIGO_TIPO_PRESENTACION where tp.NOMBRE_ARCHIVO = :nombreArchivo and tp.VENCE='SI' and sysdate >= ADD_MONTHS(fecha_vencimiento, 4) and pe.vigente = 'SI' and pe.if_gedo is not null and not exists (select 1 from NOTIFICACIONES_DE_CAMBIO M INNER JOIN MOTIVOS_NOTIFICACION MN ON M.CODIGO_MOTIVO_NOTIF=MN.CODIGO_MOTIVO_NOTIF where M.codigo_entidad=pe.codigo_entidad and MN.NOMBRE_MOTIVO_NOTIF=:motivo)", nativeQuery = true)
  Collection<PresentacionEntidad> getPresentacionesParaSegundaNotificacionBalance(@Param("nombreArchivo") String paramString1, @Param("motivo") String paramString2);
  
  @Query("select pe.actualizado from PresentacionEntidad pe where pe.entidad.cuit = :cuit and pe.tipoPresentacion.codigoTipoPresentacion = :tipoPresentacion and pe.ifGedo is not null and pe.vigente = 'SI' and pe.codigoPresEnt >= all(select pe2.codigoPresEnt from PresentacionEntidad pe2 where pe2.entidad.cuit = :cuit and pe2.tipoPresentacion.codigoTipoPresentacion = :tipoPresentacion and pe2.ifGedo is not null and pe2.vigente = 'SI' )")
  String getFechaUltimaCargaValidaByTipoPresentacionAndCuitEntidad(@Param("tipoPresentacion") Integer paramInteger, @Param("cuit") Long paramLong);
  
  @Query("select pe from PresentacionEntidad pe where pe.entidad.cuit = :cuit and pe.tipoPresentacion.codigoTipoPresentacion = :tipoPresentacion and pe.ifGedo is not null and pe.vigente = 'SI' and pe.codigoPresEnt >= all(select pe2.codigoPresEnt from PresentacionEntidad pe2 where pe2.entidad.cuit = :cuit and pe2.tipoPresentacion.codigoTipoPresentacion = :tipoPresentacion and pe2.ifGedo is not null and pe2.vigente = 'SI' )")
  PresentacionEntidad getUltimaPresentacionValidaByTipoPresentacionAndCuitEntidad(@Param("tipoPresentacion") Integer paramInteger, @Param("cuit") Long paramLong);
  
  @Query("select pe from PresentacionEntidad pe where pe.entidad.codigoEntidad = :codigoEntidad order by pe.tipoPresentacion.codigoTipoPresentacion asc, pe.codigoPresEnt desc ")
  Collection<PresentacionEntidad> getAllPresentacionesByEntidad(@Param("codigoEntidad") Integer paramInteger);
  
  @Query(value = "SELECT max(p.codigo_pres_ent) FROM presentaciones_entidad p ", nativeQuery = true)
  Integer getMaxCodigoPresEnt();
}

