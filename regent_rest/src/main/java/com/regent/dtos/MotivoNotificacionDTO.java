package com.regent.dtos;


 public class MotivoNotificacionDTO
 {
   private String codigoMotivoNotificacion;
   private String motivoNotificacion;
   private String estado;
   
   public MotivoNotificacionDTO(String codigoMotivoNotificacion, String motivoNotificacion, String estado) {
     this.codigoMotivoNotificacion = codigoMotivoNotificacion;
     this.motivoNotificacion = motivoNotificacion;
     this.estado = estado;
   }
 
   
   public MotivoNotificacionDTO() {}
 
   
   public String getCodigoMotivoNotificacion() {
     return this.codigoMotivoNotificacion;
   }
   
   public void setCodigoMotivoNotificacion(String codigoMotivoNotificacion) {
     this.codigoMotivoNotificacion = codigoMotivoNotificacion;
   }
   
   public String getMotivoNotificacion() {
     return this.motivoNotificacion;
   }
   
   public void setMotivoNotificacion(String motivoNotificacion) {
     this.motivoNotificacion = motivoNotificacion;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
 }