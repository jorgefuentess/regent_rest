package com.regent.repositories.interfaces;

import com.regent.negocio.Saf;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SafRepository extends CrudRepository<Saf, Integer> {
  @Query("SELECT saf.nSaf, saf.organismoExt, saf.responsable, saf.eMail, saf.telefono, saf.estado FROM Saf saf ")
  Collection<Object[]> getAllSafActivos();
  
  Saf findByCfuOrganismo(String paramString);
  
  @Query(value = "SELECT organismo_ext, to_char(n_saf) as n_saf FROM saf WHERE estado = 'A' ", nativeQuery = true)
  Collection<Object[]> getAllOrganismosFromSaf();
  
  @Query(value = "SELECT o.organismo_ext, to_char(o.n_saf) as n_saf FROM organismos o left join saf s on o.N_SAF=s.N_SAF WHERE o.estado = 'A' and s.N_SAF IS NULL ORDER BY N_SAF ASC ", nativeQuery = true)
  Collection<Object[]> getOrganismosWithoutSaf();
  
  @Query(value = "select o.organismo_ext, to_char(o.n_saf) as n_saf from organismos o inner join saf s on o.n_saf = s.N_SAF left join usuarios u on UPPER(o.CFU_ORG) = UPPER(u.NOMBRE_USUARIO) WHERE o.estado = 'A' and u.codigo_usuario IS NULL ORDER BY N_SAF ASC ", nativeQuery = true)
  Collection<Object[]> getOrganismosWithoutUsuarioSaf();
  
  @Query(value = "SELECT cfu_org, organismo_ext FROM organismos WHERE n_saf = :nSaf ", nativeQuery = true)
  Object[] getCfuOrgAndOrganismoExtByNSaf(@Param("nSaf") String paramString);
  
  @Query(value = "SELECT cfu_org, organismo_ext FROM organismos WHERE cfu_org = :cfuOrg ", nativeQuery = true)
  Object[] getCfuOrgAndOrganismoExtByCfuOrg(@Param("cfuOrg") String paramString);
  
  @Modifying
  @Transactional
  @Query(value = "INSERT INTO progreso_archivos(id, procesadas, total) VALUES (:id, 0, :total) ", nativeQuery = true)
  int insertProgreso(@Param("id") String paramString, @Param("total") Integer paramInteger);
  
  @Modifying
  @Transactional
  @Query(value = "UPDATE progreso_archivos SET procesadas = :procesadas WHERE id = :id", nativeQuery = true)
  int updateProgreso(@Param("id") String paramString, @Param("procesadas") Integer paramInteger);
  
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM progreso_archivos WHERE id = :id", nativeQuery = true)
  int deleteProgreso(@Param("id") String paramString);
  
  @Query(value = "SELECT ROUND(procesadas/total*100) as porcentaje FROM progreso_archivos WHERE id = :id", nativeQuery = true)
  Integer getPorcentajeProcesamiento(@Param("id") String paramString);
  
  @Query("SELECT s FROM Saf s WHERE s.estado = 'A' AND NOT EXISTS (SELECT 1 FROM Usuario u where u.rol.nombreRol = 'SAF' AND u.nombreUsuario = LOWER(s.cfuOrganismo) ) ")
  Collection<Saf> getAllSafSinUsuario();
}