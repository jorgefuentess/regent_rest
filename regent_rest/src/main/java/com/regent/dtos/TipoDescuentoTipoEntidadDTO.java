package com.regent.dtos;


 public class TipoDescuentoTipoEntidadDTO
 {
   private String codigoTipoEntidad;
   private String codigoTipoDescuento;
   private String tipoDescuento;
   private String estado;
   private String codigo;
   
   public TipoDescuentoTipoEntidadDTO(String codigoTipoEntidad, String codigoTipoDescuento, String tipoDescuento, String estado, String codigo) {
     this.codigoTipoEntidad = codigoTipoEntidad;
     this.codigoTipoDescuento = codigoTipoDescuento;
     this.tipoDescuento = tipoDescuento;
     this.estado = estado;
     this.codigo = codigo;
   }
 
   
   public TipoDescuentoTipoEntidadDTO() {}
 
   
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
   
   public String getTipoDescuento() {
     return this.tipoDescuento;
   }
   
   public void setTipoDescuento(String tipoDescuento) {
     this.tipoDescuento = tipoDescuento;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
   
   public String getCodigo() {
     return this.codigo;
   }
   
   public void setCodigo(String codigo) {
     this.codigo = codigo;
   }
 }