package com.regent.repositories.interfaces;

import com.regent.negocio.TipoDescuentoSolicitud;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TipoDescuentoSolicitudRepository extends CrudRepository<TipoDescuentoSolicitud, Integer> {
  @Modifying
  @Transactional
  @Query(value = "insert into tipo_descuento_solicitud (codigo_solicitud, codigo_tipo_descuento, estado, usuario, actualizado) values (:codigoSolicitud, :codigoTipoDescuento, :estado, :usuario, :actualizado) ", nativeQuery = true)
  int insertTipoDescuentoSolicitud(@Param("codigoSolicitud") Integer paramInteger1, @Param("codigoTipoDescuento") Integer paramInteger2, @Param("estado") String paramString1, @Param("usuario") String paramString2, @Param("actualizado") String paramString3);
  
  @Query(value = "select codigo_tipo_descuento, estado from tipo_descuento_solicitud where codigo_solicitud = :codigoSolicitud and estado = 'A' ", nativeQuery = true)
  Collection<Object[]> getTipoDescuentoSolicitudCheckedByCodigoSolicitud(@Param("codigoSolicitud") Integer paramInteger);
  
  @Query(value = "select td.nombre_tipo_descuento, tds.estado from tipo_descuento_solicitud tds inner join tipo_descuento td on tds.codigo_tipo_descuento = td.codigo_tipo_descuento where codigo_solicitud = :codigoSolicitud", nativeQuery = true)
  Collection<Object[]> getTipoDescuentoSolicitudByCodigoSolicitud(@Param("codigoSolicitud") Integer paramInteger);
  
  @Query("SELECT tds FROM TipoDescuentoSolicitud tds WHERE tds.tipoDescuentoSolicitudPk.tipoDescuento.codigoTipoDescuento = :codigoTipoDescuento AND tds.tipoDescuentoSolicitudPk.solicitud.codigoSolicitud = :codigoSolicitud ")
  TipoDescuentoSolicitud getTipoDescuentoSolicituBySolicitudAndTipoDescuento(@Param("codigoSolicitud") Integer paramInteger1, @Param("codigoTipoDescuento") Integer paramInteger2);
}

