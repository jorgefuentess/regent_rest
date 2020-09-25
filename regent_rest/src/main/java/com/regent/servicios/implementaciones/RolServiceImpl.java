package com.regent.servicios.implementaciones;


 import com.regent.dtos.RolDTO;
 import com.regent.negocio.Rol;
 import com.regent.repositories.interfaces.RolRepository;
 import com.regent.servicios.interfaces.RolService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 
 
 
 
 @Service
 public class RolServiceImpl
   implements RolService
 {
   @Autowired
   RolRepository rRepo;
   
   public Collection<RolDTO> getRoles() {
     Collection<Object[]> rol = this.rRepo.getRoles();
     Collection<RolDTO> rolDTO = new ArrayList<>();
     for (Object[] r : rol) {
       RolDTO rDTO = new RolDTO(String.valueOf(r[0]), (String)r[1], (String)r[2]);
       rolDTO.add(rDTO);
     } 
     return rolDTO;
   }
 
 
 
 
 
 
 
   
   public Collection<RolDTO> getRolesActivos() {
     Collection<Object[]> rol = this.rRepo.getRolesActivos();
     Collection<RolDTO> rolDTO = new ArrayList<>();
     for (Object[] r : rol) {
       RolDTO rDTO = new RolDTO(String.valueOf(r[0]), (String)r[1], (String)r[2]);
       rolDTO.add(rDTO);
     } 
     return rolDTO;
   }
 
 
 
 
 
 
   
   public void nuevoRol(RolDTO rDTO, String usuario) {
     String estado = "";
     if ("Activo".equals(rDTO.getEstado().trim()) || "A".equals(rDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(rDTO.getEstado().trim()) || "I".equals(rDTO.getEstado().trim())) {
       estado = "I";
     } 
     Rol rol = new Rol();
     rol.setEstado(!"".equals(estado) ? estado : "A");
     
     rol.setNombreRol(rDTO.getRol().trim().toUpperCase());
     rol.setUsuario(usuario);
     rol.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.rRepo.nuevoRol(rol.getNombreRol(), rol.getEstado(), rol.getUsuario(), rol.getActualizado());
   }
 
 
 
 
 
 
   
   public void updateRol(RolDTO rDTO, String usuario) {
     Rol rol = rRepo.findById(Integer.valueOf(rDTO.getCodigoRol())).orElse(new Rol());
     
     rol.setEstado(rDTO.getEstado().trim());
     rol.setNombreRol(rDTO.getRol().trim().toUpperCase());
     rol.setUsuario(usuario);
     rol.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.rRepo.save(rol);
   }
 }
