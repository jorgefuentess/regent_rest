package com.regent.dtos;

public class EntidadLiteDTO
 {
   private String codigoEntidad;
   private String entidad;
   private String codigoTipoEntidad;
   
   public EntidadLiteDTO(String codigoEntidad, String entidad, String codigoTipoEntidad) {
     this.codigoEntidad = codigoEntidad;
     this.entidad = entidad;
     this.codigoTipoEntidad = codigoTipoEntidad;
   }
 
   
   public EntidadLiteDTO() {}
 
   
   public String getCodigoEntidad() {
     return this.codigoEntidad;
   }
   
   public void setCodigoEntidad(String codigoEntidad) {
     this.codigoEntidad = codigoEntidad;
   }
   
   public String getEntidad() {
     return this.entidad;
   }
   
   public void setEntidad(String entidad) {
     this.entidad = entidad;
   }
   
   public String getCodigoTipoEntidad() {
     return this.codigoTipoEntidad;
   }
   
   public void setCodigoTipoEntidad(String codigoTipoEntidad) {
     this.codigoTipoEntidad = codigoTipoEntidad;
   }
 }

