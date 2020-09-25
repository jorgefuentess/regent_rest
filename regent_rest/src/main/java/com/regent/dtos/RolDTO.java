package com.regent.dtos;


 public class RolDTO
 {
   private String codigoRol;
   private String rol;
   private String estado;
   
   public RolDTO(String codigoRol, String rol, String estado) {
     this.codigoRol = codigoRol;
     this.rol = rol;
     this.estado = estado;
   }
 
   
   public RolDTO() {}
 
   
   public String getCodigoRol() {
     return this.codigoRol;
   }
   
   public void setCodigoRol(String codigoRol) {
     this.codigoRol = codigoRol;
   }
   
   public String getRol() {
     return this.rol;
   }
   
   public void setRol(String rol) {
     this.rol = rol;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
 }