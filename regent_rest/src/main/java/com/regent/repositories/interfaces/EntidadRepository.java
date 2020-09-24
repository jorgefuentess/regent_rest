package com.regent.repositories.interfaces;

import com.regent.negocio.Entidad;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadRepository extends CrudRepository<Entidad, Integer> {
  @Query("SELECT e.codigoEntidad, e.denominacion, te.codigoTipoEntidad FROM Entidad e join e.tipoEntidad te WHERE e.estadoEntidad.nombreEstado = 'Alta' ORDER BY e.codigoEntidad desc ")
  Collection<Object[]> getAllEntidad();
  
  Entidad findByCuit(Long paramLong);
  
  Entidad findByEmail(String paramString);
  
  @Query("SELECT e FROM Entidad e ORDER BY e.codigoEntidad desc ")
  Collection<Entidad> getAllEntidadCompleta();
  
  @Query("SELECT e FROM Entidad e WHERE e.estadoEntidad.nombreEstado = 'Alta' AND e.vigente = 'SI' ORDER BY e.codigoEntidad desc ")
  Collection<Entidad> getEntidadVigenteForPublico();
  
  @Query("SELECT e FROM Entidad e WHERE e.estadoEntidad.nombreEstado IN('Baja por Solicitud', 'Baja de Oficio', 'Suspendida') ORDER BY e.codigoEntidad desc ")
  Collection<Entidad> getEntidadBajaForPublico();
  
  @Query("SELECT e FROM Entidad e WHERE e.estadoEntidad.nombreEstado = 'Alta' AND e.vigente = 'SI' AND NOT EXISTS (SELECT 1 FROM Usuario u where u.rol.nombreRol = 'ENTIDAD' AND LENGTH(u.nombreUsuario ) = 11 AND u.nombreUsuario = e.cuit ) ")
  Collection<Entidad> getAllEntidadSinUsuario();
  
  @Query(value = "SELECT max(e.cuid) FROM entidades e ", nativeQuery = true)
  Integer getMaxCUID();
}