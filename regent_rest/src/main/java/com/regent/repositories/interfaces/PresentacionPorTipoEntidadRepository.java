package com.regent.repositories.interfaces;


import com.regent.negocio.PresentacionPorTipoEntidad;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PresentacionPorTipoEntidadRepository extends CrudRepository<PresentacionPorTipoEntidad, Integer> {
  @Query(value = "SELECT tp.codigo_tipo_presentacion, tp.nombre_tipo_presentacion, CASE WHEN EXISTS (SELECT estado from pres_por_tipo_entidad WHERE codigo_tipo_entidad=:codigoTipoEntidad and codigo_tipo_descuento=:codigoTipoDescuento and codigo_tipo_presentacion=tp.codigo_tipo_presentacion AND estado='A') \t\tTHEN 'A' ELSE 'I' END as estado from tipo_presentaciones tp order by tp.codigo_tipo_presentacion asc ", nativeQuery = true)
  Collection<Object[]> getPresentacionPorTipoEntidadByCodigoTipoEntidadAndCodigoTipoDescuento(@Param("codigoTipoEntidad") String paramString1, @Param("codigoTipoDescuento") String paramString2);
  
  @Query(value = "select ppte.codigo_tipo_presentacion, ppte.codigo_tipo_entidad, ppte.estado, ppte.usuario, ppte.actualizado from PRES_POR_TIPO_ENTIDAD ppte where ppte.codigo_tipo_presentacion = :codigoTipoPresentacion and ppte.codigo_tipo_entidad = :codigoTipoEntidad and ppte.codigo_tipo_descuento = :codigoTipoDescuento", nativeQuery = true)
  Object[] getPresentacionPorTipoEntidadAndTipoDescuento(@Param("codigoTipoPresentacion") String paramString1, @Param("codigoTipoEntidad") String paramString2, @Param("codigoTipoDescuento") String paramString3);
  
  @Modifying
  @Transactional
  @Query(value = "update PRES_POR_TIPO_ENTIDAD set estado=:estado, usuario=:usuario, actualizado=:actualizado where codigo_tipo_presentacion = :codigoTipoPresentacion and codigo_tipo_descuento = :codigoTipoDescuento and codigo_tipo_entidad = :codigoTipoEntidad", nativeQuery = true)
  int updatePresentacionPorTipoEntidadAndTipoDescuento(@Param("codigoTipoPresentacion") String paramString1, @Param("codigoTipoEntidad") String paramString2, @Param("codigoTipoDescuento") String paramString3, @Param("estado") String paramString4, @Param("usuario") String paramString5, @Param("actualizado") String paramString6);
  
  @Modifying
  @Transactional
  @Query(value = "insert into PRES_POR_TIPO_ENTIDAD (codigo_tipo_presentacion, codigo_tipo_entidad, codigo_tipo_descuento, estado, usuario, actualizado) values (:codigoTipoPresentacion, :codigoTipoEntidad, :codigoTipoDescuento, :estado, :usuario, :actualizado) ", nativeQuery = true)
  int insertPresentacionPorTipoEntidadAndTipoDescuento(@Param("codigoTipoPresentacion") Integer paramInteger1, @Param("codigoTipoEntidad") Integer paramInteger2, @Param("codigoTipoDescuento") Integer paramInteger3, @Param("estado") String paramString1, @Param("usuario") String paramString2, @Param("actualizado") String paramString3);
  
  @Query("SELECT ppte from PresentacionPorTipoEntidad ppte where ppte.estado = 'A' ")
  Collection<PresentacionPorTipoEntidad> getPresentaciones();
  
  @Query(value = "SELECT DISTINCT TP.* FROM CODIGO_DESCUENTO cd INNER JOIN RESOLUCIONES R ON R.CODIGO_RESOLUCION = cd.CODIGO_RESOLUCION INNER JOIN ENTIDADES E ON E.CODIGO_ENTIDAD = R.CODIGO_ENTIDAD INNER JOIN PRES_POR_TIPO_ENTIDAD PPTE ON PPTE.CODIGO_TIPO_ENTIDAD = E.CODIGO_TIPO_ENTIDAD AND PPTE.CODIGO_TIPO_DESCUENTO = CD.CODIGO_TIPO_DESCUENTO INNER JOIN TIPO_PRESENTACIONES TP ON TP.CODIGO_TIPO_PRESENTACION = PPTE.CODIGO_TIPO_PRESENTACION WHERE E.CUIT = :cuit ORDER BY TP.CODIGO_TIPO_PRESENTACION ASC", nativeQuery = true)
  Collection<Object[]> getPresentacionesForEntidad(@Param("cuit") Long paramLong);
}
