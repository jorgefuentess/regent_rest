package com.regent.repositories.interfaces;


import com.regent.negocio.TipoDescuento;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TipoDescuentoRepository extends CrudRepository<TipoDescuento, Integer> {
  @Query("SELECT td.codigoTipoDescuento, td.nombreTipoDescuento, td.estado FROM TipoDescuento td ORDER BY td.codigoTipoDescuento asc ")
  Collection<Object[]> getAllTipoDescuento();
  
  @Query("SELECT td FROM TipoDescuento td WHERE td.estado = 'A' ORDER BY td.codigoTipoDescuento asc ")
  Collection<TipoDescuento> getAllTipoDescuentoVigente();
  
  @Query(value = "SELECT tdte.codigo_tipo_entidad, tdte.codigo_tipo_descuento, td.nombre_tipo_descuento FROM tipo_dto_tipo_entidad tdte INNER JOIN tipo_descuento td ON td.codigo_tipo_descuento = tdte.codigo_tipo_descuento WHERE tdte.estado = 'A' ", nativeQuery = true)
  Collection<Object[]> getAllTipoEntidadTipoDescuento();
  
  @Query("SELECT cd.resolucion.entidad.codigoEntidad, cd.tipoDescuento.codigoTipoDescuento, cd.tipoDescuento.nombreTipoDescuento FROM CodigoDescuento cd WHERE resolucion.entidad.estadoEntidad.nombreEstado = 'Alta' and resolucion.entidad.estado = 'A' and cd.estado = 'A' ")
  Collection<Object[]> getTipoEntidadTipoDescuentoByEntidad();
  
  @Query(value = "SELECT te.codigo_tipo_entidad, td.codigo_tipo_descuento, td.nombre_tipo_descuento, CASE WHEN EXISTS (SELECT tdte.estado from TIPO_DTO_TIPO_ENTIDAD tdte WHERE tdte.codigo_tipo_descuento=td.codigo_tipo_descuento AND tdte.codigo_tipo_entidad = te.codigo_tipo_entidad AND estado='A') \t\tTHEN 'A' ELSE 'I' END as estado from TIPO_DESCUENTO td, TIPO_ENTIDADES te  UNION SELECT -1 as codigo_tipo_entidad, codigo_tipo_descuento, nombre_tipo_descuento, 'I' from TIPO_DESCUENTO order by codigo_tipo_entidad asc, codigo_tipo_descuento asc ", nativeQuery = true)
  Collection<Object[]> getTipoEntidadWithCheckedFlag();
  
  @Query(value = "SELECT tdte.estado FROM tipo_dto_tipo_entidad tdte WHERE tdte.codigo_tipo_entidad = :codigoTipoEntidad AND tdte.codigo_tipo_descuento = :codigoTipoDescuento ", nativeQuery = true)
  Object[] getTipoDescuentoTipoEntidadByCodTipoDescuentoAndCodTipoEntidad(@Param("codigoTipoEntidad") Integer paramInteger1, @Param("codigoTipoDescuento") Integer paramInteger2);
  
  @Modifying
  @Transactional
  @Query(value = "UPDATE tipo_dto_tipo_entidad SET estado = :estado, usuario = :usuario, actualizado = :actualizado WHERE codigo_tipo_entidad = :codigoTipoEntidad AND codigo_tipo_descuento = :codigoTipoDescuento ", nativeQuery = true)
  int updateTipoDescuentoTipoEntidadByCodTipoDescuentoAndCodTipoEntidad(@Param("codigoTipoEntidad") Integer paramInteger1, @Param("codigoTipoDescuento") Integer paramInteger2, @Param("estado") String paramString1, @Param("usuario") String paramString2, @Param("actualizado") String paramString3);
  
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO tipo_dto_tipo_entidad (codigo_tipo_entidad, codigo_tipo_descuento, estado, usuario, actualizado) VALUES (:codigoTipoEntidad, :codigoTipoDescuento, :estado, :usuario, :actualizado) ", nativeQuery = true)
  int insertTipoDescuentoTipoEntidadByCodTipoDescuentoAndCodTipoEntidad(@Param("codigoTipoEntidad") Integer paramInteger1, @Param("codigoTipoDescuento") Integer paramInteger2, @Param("estado") String paramString1, @Param("usuario") String paramString2, @Param("actualizado") String paramString3);
}
