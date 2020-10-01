package com.regent.dtos;

public class PresentacionPorTipoEntidadDTO
 {
   private String codigoTipoPresentacion;
   private String tipoPresentacion;
   private String nombreArchivo;
   private String codigoTipoEntidad;
   private String codigoTipoDescuento;
   private String estado;
   
   public PresentacionPorTipoEntidadDTO(String codigoTipoPresentacion, String tipoPresentacion, String nombreArchivo, String codigoTipoEntidad, String codigoTipoDescuento, String estado) {
     this.codigoTipoPresentacion = codigoTipoPresentacion;
     this.tipoPresentacion = tipoPresentacion;
     this.nombreArchivo = nombreArchivo;
     this.codigoTipoEntidad = codigoTipoEntidad;
     this.codigoTipoDescuento = codigoTipoDescuento;
     this.estado = estado;
   }
 
 
   
   public PresentacionPorTipoEntidadDTO() {}
 
   
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
   
   public String getCodigoTipoEntidad() {
     return this.codigoTipoEntidad;
   }
   
   public void setCodigoTipoEntidad(String codigoTipoEntidad) {
     this.codigoTipoEntidad = codigoTipoEntidad;
   }
   
   public String getCodigoTipoDescuento() {
     return this.codigoTipoDescuento;
   }
   
   public void setCodigoTipoDescuento(String codigoTipoDescuento) {
     this.codigoTipoDescuento = codigoTipoDescuento;
   }
   public String getEstado() {
     return this.estado;
   }
   public void setEstado(String estado) {
     this.estado = estado;
   }
 }
