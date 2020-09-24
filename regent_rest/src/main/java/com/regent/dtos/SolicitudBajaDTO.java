package com.regent.dtos;

public class SolicitudBajaDTO
 {
   private String codigoSolicitud;
   private String codigoEntidad;
   private String entidad;
   private String cuit;
   private String fechaSolicitud;
   private String numeroExpediente;
   
   public SolicitudBajaDTO(String codigoSolicitud, String codigoEntidad, String entidad, String cuit, String fechaSolicitud, String numeroExpediente) {
     this.codigoSolicitud = codigoSolicitud;
     this.codigoEntidad = codigoEntidad;
     this.entidad = entidad;
     this.cuit = cuit;
     this.fechaSolicitud = fechaSolicitud;
     this.numeroExpediente = numeroExpediente;
   }
 
 
   
   public SolicitudBajaDTO() {}
 
 
   
   public String getCodigoSolicitud() {
     return this.codigoSolicitud;
   }
 
   
   public void setCodigoSolicitud(String codigoSolicitud) {
     this.codigoSolicitud = codigoSolicitud;
   }
 
   
   public String getCodigoEntidad() {
     return this.codigoEntidad;
   }
 
   
   public void setCodigoEntidad(String codigoEntidad) {
     this.codigoEntidad = codigoEntidad;
   }
 
   
   public String getFechaSolicitud() {
     return this.fechaSolicitud;
   }
 
   
   public void setFechaSolicitud(String fechaSolicitud) {
     this.fechaSolicitud = fechaSolicitud;
   }
 
   
   public String getNumeroExpediente() {
     return this.numeroExpediente;
   }
 
   
   public void setNumeroExpediente(String numeroExpediente) {
     this.numeroExpediente = numeroExpediente;
   }
 
   
   public String getEntidad() {
     return this.entidad;
   }
 
   
   public void setEntidad(String entidad) {
     this.entidad = entidad;
   }
 
   
   public String getCuit() {
     return this.cuit;
   }
 
   
   public void setCuit(String cuit) {
     this.cuit = cuit;
   }
 }

