package com.regent.servicios.implementaciones;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.regent.dtos.EstadoEntidadDTO;
import com.regent.negocio.EstadoEntidad;
import com.regent.repositories.interfaces.EstadoEntidadRepository;
import com.regent.servicios.interfaces.EstadoEntidadService;


@Service
public class EstadoEntidadServiceImpl implements EstadoEntidadService {
  @Autowired
  EstadoEntidadRepository eRepo;
  
  public Collection<EstadoEntidadDTO> getAllEstadoEntidad() {
    Collection<Object[]> estados = this.eRepo.getAllEstadoEntidad();
    Collection<EstadoEntidadDTO> estadosDTO = new ArrayList<>();
    for (Object[] ee : estados) {
      EstadoEntidadDTO eDTO = new EstadoEntidadDTO(String.valueOf(ee[0]), (String)ee[1], (String)ee[2]);
      estadosDTO.add(eDTO);
    } 
    return estadosDTO;
  }
  
  public void nuevoEstadoEntidad(EstadoEntidadDTO eDTO, String usuario) {
    String estado = "";
    if ("Activo".equals(eDTO.getEstado().trim()) || "A".equals(eDTO.getEstado().trim())) {
      estado = "A";
    } else if ("Inactivo".equals(eDTO.getEstado().trim()) || "I".equals(eDTO.getEstado().trim())) {
      estado = "I";
    } 
    EstadoEntidad ee = new EstadoEntidad();
    ee.setEstado(!"".equals(estado) ? estado : "A");
    String nombre = eDTO.getEstadoEntidad().trim();
    ee.setNombreEstado(nombre);
    ee.setUsuario(usuario);
    ee.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
    ee.setCodigoEstado(Integer.valueOf(0));
    this.eRepo.save(ee);
  }

@Override
public void updateEstadoEntidad(EstadoEntidadDTO paramEstadoEntidadDTO, String paramString) {
	// TODO Auto-generated method stub
	
}
  
//  public void updateEstadoEntidad(EstadoEntidadDTO eDTO, String usuario) {
//    EstadoEntidad ee = (EstadoEntidad)this.eRepo.findOne(Integer.valueOf(eDTO.getCodigoEstadoEntidad()));
//    ee.setEstado(eDTO.getEstado().trim());
//    String nombre = eDTO.getEstadoEntidad().trim();
//    ee.setNombreEstado(nombre);
//    ee.setUsuario(usuario);
//    ee.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
//    this.eRepo.save(ee);
//  }
}
