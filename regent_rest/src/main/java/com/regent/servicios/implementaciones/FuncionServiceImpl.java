package com.regent.servicios.implementaciones;


 import com.regent.dtos.FuncionDTO;
 import com.regent.dtos.FuncionPorRolDTO;
 import com.regent.dtos.RolDTO;
 import com.regent.negocio.Funcion;
 import com.regent.repositories.interfaces.FuncionRepository;
 import com.regent.servicios.interfaces.FuncionService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class FuncionServiceImpl
   implements FuncionService
 {
   @Autowired
   FuncionRepository fRepo;
   
   public Collection<FuncionDTO> getFunciones() {
     Collection<Object[]> funcion = this.fRepo.getFunciones();
     Collection<FuncionDTO> funcionDTO = new ArrayList<>();
     for (Object[] f : funcion) {
       FuncionDTO fDTO = new FuncionDTO(String.valueOf(f[0]), (String)f[1], (String)f[2], (String)f[3], (String)f[4]);
       funcionDTO.add(fDTO);
     } 
     return funcionDTO;
   }
 
   public Collection<FuncionDTO> getFuncionesByCodigoRol(RolDTO rDTO) {
     Collection<Object[]> funcion = this.fRepo.getFuncionesByCodigoRol(rDTO.getCodigoRol());
     Collection<FuncionDTO> funcionDTO = new ArrayList<>();
     for (Object[] f : funcion) {
       FuncionDTO fDTO = new FuncionDTO(String.valueOf(f[0]), (String)f[1], (String)f[2], (String)f[3], String.valueOf(f[4]));
       funcionDTO.add(fDTO);
     } 
     return funcionDTO;
   }
 
 
   public void nuevaFuncion(FuncionDTO fDTO, String usuario) {
     String estado = "";
     if ("Activo".equals(fDTO.getEstado().trim()) || "A".equals(fDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(fDTO.getEstado().trim()) || "I".equals(fDTO.getEstado().trim())) {
       estado = "I";
     } 
     Funcion funcion = new Funcion();
     funcion.setEstado(!"".equals(estado) ? estado : "A");
     
     funcion.setNombreFuncion(fDTO.getNombreFuncion().trim().toUpperCase());
     funcion.setDetalle(fDTO.getDetalle().trim());
     funcion.setPath(fDTO.getPath().trim());
     
     funcion.setUsuario(usuario);
     funcion.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.fRepo.nuevaFuncion(funcion.getNombreFuncion(), funcion.getPath(), funcion.getDetalle(), funcion.getEstado(), usuario, funcion.getActualizado());
   }
 
 
   public void updateFuncion(FuncionDTO fDTO, String usuario) {
     Funcion funcion = fRepo.findOne(Integer.valueOf(fDTO.getCodigoFuncion()));
     
     funcion.setEstado(fDTO.getEstado().trim());
     funcion.setNombreFuncion(fDTO.getNombreFuncion().trim().toUpperCase());
     funcion.setDetalle(fDTO.getDetalle().trim());
     funcion.setPath(fDTO.getPath().trim());
     
     funcion.setUsuario(usuario);
     funcion.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     fRepo.save(funcion);
   }
 
 
 
 
 
 
   
   public void updateFuncionPorRol(Collection<FuncionPorRolDTO> funcionesDTO, String usuario) {
     for (FuncionPorRolDTO fprDTO : funcionesDTO) {
       Object[] o = this.fRepo.getRolFuncion(fprDTO.getCodigoRol(), fprDTO.getCodigoFuncion());
       
       if (o.length > 0) {
         
         Object[] o2 = (Object[])o[0];
         String estadoAnterior = (String)o2[3];
         String checked = fprDTO.getChecked();
         
         if (("A".equals(estadoAnterior) && "false".equals(checked)) || (
           "I".equals(estadoAnterior) && "true".equals(checked))) {
           String estadoNuevo = "true".equals(checked) ? "A" : "I";
           String actualizado = String.valueOf(new Timestamp(System.currentTimeMillis()));
           this.fRepo.updateRolFuncion(fprDTO.getCodigoRol(), fprDTO.getCodigoFuncion(), estadoNuevo, usuario, actualizado);
         }  continue;
       }  if ("true".equals(fprDTO.getChecked())) {
         
         String actualizado = String.valueOf(new Timestamp(System.currentTimeMillis()));
         this.fRepo.insertRolFuncion(Integer.valueOf(fprDTO.getCodigoRol()), Integer.valueOf(fprDTO.getCodigoFuncion()), "A", Integer.valueOf(1), usuario, actualizado);
       } 
     } 
   }
 }

