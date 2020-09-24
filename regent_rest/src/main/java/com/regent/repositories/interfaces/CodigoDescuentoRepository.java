package com.regent.repositories.interfaces;

import com.regent.negocio.CodigoDescuento;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CodigoDescuentoRepository extends CrudRepository<CodigoDescuento, Integer> {
  @Query(value = "SELECT tdte.codigo_tipo_entidad, tdte.codigo_tipo_descuento, td.nombre_tipo_descuento, cd.estado as estado, codigo_descuento as codigo FROM tipo_dto_tipo_entidad tdte INNER JOIN tipo_descuento td ON td.codigo_tipo_descuento = tdte.codigo_tipo_descuento INNER JOIN tipo_entidades te on tdte.codigo_tipo_entidad = te.codigo_tipo_entidad INNER JOIN entidades e on e.codigo_tipo_entidad = te.codigo_tipo_entidad LEFT JOIN codigo_descuento cd on cd.codigo_tipo_descuento = tdte.codigo_tipo_descuento LEFT JOIN resoluciones r on cd.codigo_resolucion = r.codigo_resolucion WHERE tdte.estado = 'A' AND (r.codigo_resolucion IS NULL OR r.codigo_resolucion = :codigoResolucion)  AND e.codigo_ENTIDAD = :codigoEntidad ORDER BY codigo_tipo_descuento ASC ", nativeQuery = true)
  Collection<Object[]> getTipoDescuentoWithCodigoDescuento(@Param("codigoResolucion") Integer paramInteger1, @Param("codigoEntidad") Integer paramInteger2);
  
  @Query("SELECT cd FROM CodigoDescuento cd WHERE cd.tipoDescuento.codigoTipoDescuento = :codigoTipoDescuento AND cd.resolucion.codigoResolucion = :codigoResolucion ")
  CodigoDescuento getCodigoDescuentoByResolucionAndTipoDescuento(@Param("codigoResolucion") Integer paramInteger1, @Param("codigoTipoDescuento") Integer paramInteger2);
  
  @Query("SELECT cd FROM CodigoDescuento cd WHERE cd.tipoDescuento.codigoTipoDescuento = :codigoTipoDescuento AND cd.resolucion.entidad.codigoEntidad = :codigoEntidad AND cd.actualizado >= all(select cd2.actualizado from CodigoDescuento cd2 WHERE cd2.tipoDescuento.codigoTipoDescuento = :codigoTipoDescuento AND cd2.resolucion.entidad.codigoEntidad = :codigoEntidad)")
  CodigoDescuento getCodigoDescuentoByEntidadAndTipoDescuento(@Param("codigoEntidad") Integer paramInteger1, @Param("codigoTipoDescuento") Integer paramInteger2);
  
  @Query("SELECT cd.tipoDescuento.nombreTipoDescuento FROM CodigoDescuento cd WHERE cd.resolucion.entidad.codigoEntidad = :codigoEntidad ")
  Collection<String> getDescuentosByEntidad(@Param("codigoEntidad") Integer paramInteger);
  
  @Query("SELECT cd.codigoDescuento FROM CodigoDescuento cd WHERE cd.resolucion.entidad.codigoEntidad = :codigoEntidad ")
  Collection<String> getCodigosDescuentosByEntidad(@Param("codigoEntidad") Integer paramInteger);
  
  @Query("SELECT cd.tipoDescuento.nombreTipoDescuento, cd.codigoDescuento FROM CodigoDescuento cd WHERE cd.resolucion.entidad.codigoEntidad = :codigoEntidad ")
  Collection<Object[]> getDescuentosWithCodigoByEntidad(@Param("codigoEntidad") Integer paramInteger);
}