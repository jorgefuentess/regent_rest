package com.regent.servicios.implementaciones;

import com.regent.dtos.InstitucionHabilitanteDTO;
 import com.regent.negocio.InstitucionHabilitante;
 import com.regent.repositories.interfaces.InstitucionHabilitanteRepository;
 import com.regent.servicios.interfaces.InstitucionHabilitanteService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class InstitucionHabilitanteServiceImpl
   implements InstitucionHabilitanteService
 {
   @Autowired
   InstitucionHabilitanteRepository ihRepo;
   
   public Collection<InstitucionHabilitanteDTO> getAllInstitucionHabilitante() {
     Collection<Object[]> instituciones = this.ihRepo.getAllInstitucionHabilitante();
     Collection<InstitucionHabilitanteDTO> institucionesDTO = new ArrayList<>();
     for (Object[] ih : instituciones) {
       InstitucionHabilitanteDTO ihDTO = new InstitucionHabilitanteDTO(String.valueOf(ih[0]), (String)ih[1], (String)ih[2]);
       institucionesDTO.add(ihDTO);
     } 
     return institucionesDTO;
   }
 
   
   public void nuevaInstitucionHabilitante(InstitucionHabilitanteDTO dDTO, String usuario) {
     String estado = "";
     
     if ("Activo".equals(dDTO.getEstado().trim()) || "A".equals(dDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(dDTO.getEstado().trim()) || "I".equals(dDTO.getEstado().trim())) {
       estado = "I";
     } 
     
     InstitucionHabilitante ih = new InstitucionHabilitante();
     ih.setEstado(!"".equals(estado) ? estado : "A");
     
     String nombre = dDTO.getInstitucionHabilitante().trim();
     ih.setNombreInstitucionHabilitante(nombre);
     
     ih.setUsuario(usuario);
     ih.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.ihRepo.save(ih);
   }
   
   public void updateInstitucionHabilitante(InstitucionHabilitanteDTO ihDTO, String usuario) {
     InstitucionHabilitante ih = ihRepo.findOne(Integer.valueOf(ihDTO.getCodigoInstitucionHabilitante()));
     
     ih.setEstado(ihDTO.getEstado().trim());
     
     String nombre = ihDTO.getInstitucionHabilitante().trim();
     ih.setNombreInstitucionHabilitante(nombre);
     
     ih.setUsuario(usuario);
     ih.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.ihRepo.save(ih);
   }
 
 
 
 
 
 
   
   public Collection<InstitucionHabilitanteDTO> getAllInstitucionHabilitanteActivas() {
     Collection<Object[]> instituciones = this.ihRepo.getAllInstitucionHabilitanteActivas();
     Collection<InstitucionHabilitanteDTO> institucionesDTO = new ArrayList<>();
     for (Object[] ih : instituciones) {
       InstitucionHabilitanteDTO ihDTO = new InstitucionHabilitanteDTO(String.valueOf(ih[0]), (String)ih[1], (String)ih[2]);
       institucionesDTO.add(ihDTO);
     } 
     return institucionesDTO;
   }
 }
