package com.regent.dtos;


 public class TipoDescuentoSolicitudDTO
 {
   private String codigoTipoDescuento;
   private String tipoDescuento;
   private String estado;
   
   public TipoDescuentoSolicitudDTO() {}
   
   public TipoDescuentoSolicitudDTO(String codigoTipoDescuento, String tipoDescuento, String estado) {
     this.codigoTipoDescuento = codigoTipoDescuento;
     this.tipoDescuento = tipoDescuento;
     this.estado = estado;
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
 }
