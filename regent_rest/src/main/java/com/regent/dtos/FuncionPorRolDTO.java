package com.regent.dtos;


 public class FuncionPorRolDTO
 {
   private String codigoRol;
   private String codigoFuncion;
   private String checked;
   
   public FuncionPorRolDTO(String codigoRol, String codigoFuncion, String checked) {
     this.codigoRol = codigoRol;
     this.codigoFuncion = codigoFuncion;
     this.checked = checked;
   }
 
 
   
   public FuncionPorRolDTO() {}
 
   
   public String getCodigoRol() {
     return this.codigoRol;
   }
 
   
   public void setCodigoRol(String codigoRol) {
     this.codigoRol = codigoRol;
   }
 
   
   public String getCodigoFuncion() {
     return this.codigoFuncion;
   }
 
   
   public void setCodigoFuncion(String codigoFuncion) {
     this.codigoFuncion = codigoFuncion;
   }
 
   
   public String getChecked() {
     return this.checked;
   }
 
   
   public void setChecked(String checked) {
     this.checked = checked;
   }
 }
