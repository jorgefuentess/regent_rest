package com.regent.servicios.implementaciones;

import com.regent.dtos.EstadoSolicitudDTO;
 import com.regent.negocio.EstadoSolicitud;
 import com.regent.repositories.interfaces.EstadoSolicitudRepository;
 import com.regent.servicios.interfaces.EstadoSolicitudService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 
 
 @Service
 public class EstadoSolicitudServiceImpl
   implements EstadoSolicitudService
 {
   @Autowired
   EstadoSolicitudRepository eRepo;
   
   public Collection<EstadoSolicitudDTO> getAllEstadoSolicitud() {
     Collection<Object[]> estados = this.eRepo.getAllEstadoSolicitud();
     Collection<EstadoSolicitudDTO> estadosDTO = new ArrayList<>();
     for (Object[] ee : estados) {
       EstadoSolicitudDTO eDTO = new EstadoSolicitudDTO(String.valueOf(ee[0]), (String)ee[1], (String)ee[2]);
       estadosDTO.add(eDTO);
     } 
     return estadosDTO;
   }
 
 
 
 
 
 
 
   
   public void nuevoEstadoSolicitud(EstadoSolicitudDTO eDTO, String usuario) {
     String estado = "";
     
     if ("Activo".equals(eDTO.getEstado().trim()) || "A".equals(eDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(eDTO.getEstado().trim()) || "I".equals(eDTO.getEstado().trim())) {
       estado = "I";
     } 
     
     EstadoSolicitud ee = new EstadoSolicitud();
     ee.setEstado(!"".equals(estado) ? estado : "A");
     
     String nombre = eDTO.getEstadoSolicitud().trim();
     ee.setNombreEstado(nombre);
     
     ee.setUsuario(usuario);
     ee.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
 
     
     this.eRepo.save(ee);
   }
 
 
 
 
 
 
   
   public void updateEstadoSolicitud(EstadoSolicitudDTO eDTO, String usuario) {
     EstadoSolicitud ee = (EstadoSolicitud)eRepo.findOne(Integer.valueOf(eDTO.getCodigoEstadoSolicitud()));
     
     ee.setEstado(eDTO.getEstado().trim());
     
     String nombre = eDTO.getEstadoSolicitud().trim();
     ee.setNombreEstado(nombre);
     
     ee.setUsuario(usuario);
     ee.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.eRepo.save(ee);
   }
 }

