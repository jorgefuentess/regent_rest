package com.regent.dtos;


 public class EstadoSolicitudDTO
 {
   private String codigoEstadoSolicitud;
   private String estadoSolicitud;
   private String estado;
   
   public EstadoSolicitudDTO(String codigoEstadoSolicitud, String estadoSolicitud, String estado) {
     this.codigoEstadoSolicitud = codigoEstadoSolicitud;
     this.estadoSolicitud = estadoSolicitud;
     this.estado = estado;
   }
 
   
   public EstadoSolicitudDTO() {}
 
   
   public String getCodigoEstadoSolicitud() {
     return this.codigoEstadoSolicitud;
   }
   
   public void setCodigoEstadoSolicitud(String codigoEstadoSolicitud) {
     this.codigoEstadoSolicitud = codigoEstadoSolicitud;
   }
   
   public String getEstadoSolicitud() {
     return this.estadoSolicitud;
   }
   
   public void setEstadoSolicitud(String estadoSolicitud) {
     this.estadoSolicitud = estadoSolicitud;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
 }