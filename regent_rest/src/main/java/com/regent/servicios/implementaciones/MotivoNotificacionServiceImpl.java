package com.regent.servicios.implementaciones;


 import com.regent.dtos.MotivoNotificacionDTO;
 import com.regent.negocio.MotivoNotificacion;
 import com.regent.repositories.interfaces.MotivoNotificacionRepository;
 import com.regent.servicios.interfaces.MotivoNotificacionService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class MotivoNotificacionServiceImpl
   implements MotivoNotificacionService
 {
   @Autowired
   MotivoNotificacionRepository mnRepo;
   
   public Collection<MotivoNotificacionDTO> getAllMotivoNotificacion() {
     Collection<Object[]> motivos = this.mnRepo.getAllMotivoNotificacion();
     Collection<MotivoNotificacionDTO> motivosDTO = new ArrayList<>();
     for (Object[] mn : motivos) {
       MotivoNotificacionDTO ihDTO = new MotivoNotificacionDTO(String.valueOf(mn[0]), (String)mn[1], (String)mn[2]);
       motivosDTO.add(ihDTO);
     } 
     return motivosDTO;
   }
   
   public void nuevoMotivoNotificacion(MotivoNotificacionDTO mnDTO, String usuario) {
     String estado = "";
     
     if ("Activo".equals(mnDTO.getEstado().trim()) || "A".equals(mnDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(mnDTO.getEstado().trim()) || "I".equals(mnDTO.getEstado().trim())) {
       estado = "I";
     } 
     
     MotivoNotificacion mn = new MotivoNotificacion();
     mn.setEstado(!"".equals(estado) ? estado : "A");
     
     String nombre = mnDTO.getMotivoNotificacion().trim();
     mn.setNombreMotivoNotif(nombre);
     
     mn.setUsuario(usuario);
     mn.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.mnRepo.save(mn);
   }
 
   
   public void updateMotivoNotificacion(MotivoNotificacionDTO mnDTO, String usuario) {
     MotivoNotificacion ih = mnRepo.findOne(Integer.valueOf(mnDTO.getCodigoMotivoNotificacion()));
     
     ih.setEstado(mnDTO.getEstado().trim());
     
     String nombre = mnDTO.getMotivoNotificacion().trim();
     ih.setNombreMotivoNotif(nombre);
     
     ih.setUsuario(usuario);
     ih.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.mnRepo.save(ih);
   }
 }

