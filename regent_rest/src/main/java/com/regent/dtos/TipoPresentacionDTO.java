package com.regent.dtos;

 
 public class TipoPresentacionDTO
 {
   private String codigoTipoPresentacion;
   private String tipoPresentacion;
   private String nombreArchivo;
   private String fechaUltimaCarga;
   private String vence;
   private String estado;
   
   public TipoPresentacionDTO(String codigoTipoPresentacion, String tipoPresentacion, String nombreArchivo, String fechaUltimaCarga, String vence, String estado) {
     this.codigoTipoPresentacion = codigoTipoPresentacion;
     this.tipoPresentacion = tipoPresentacion;
     this.nombreArchivo = nombreArchivo;
     this.fechaUltimaCarga = fechaUltimaCarga;
     this.vence = vence;
     this.estado = estado;
   }
 
 
   
   public TipoPresentacionDTO() {}
 
   
   public String getCodigoTipoPresentacion() {
     return this.codigoTipoPresentacion;
   }
   
   public void setCodigoTipoPresentacion(String codigoTipoPresentacion) {
     this.codigoTipoPresentacion = codigoTipoPresentacion;
   }
   
   public String getTipoPresentacion() {
     return this.tipoPresentacion;
   }
   
   public void setTipoPresentacion(String tipoPresentacion) {
     this.tipoPresentacion = tipoPresentacion;
   }
   
   public String getNombreArchivo() {
     return this.nombreArchivo;
   }
   
   public void setNombreArchivo(String nombreArchivo) {
     this.nombreArchivo = nombreArchivo;
   }
   
   public String getFechaUltimaCarga() {
     return this.fechaUltimaCarga;
   }
   
   public void setFechaUltimaCarga(String fechaUltimaCarga) {
     this.fechaUltimaCarga = fechaUltimaCarga;
   }
   
   public String getVence() {
     return this.vence;
   }
   
   public void setVence(String vence) {
     this.vence = vence;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
 }


