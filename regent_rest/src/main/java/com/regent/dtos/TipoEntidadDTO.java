package com.regent.dtos;


 import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
 import java.util.Collection;
 
 public class TipoEntidadDTO
 {
   private String codigoTipoEntidad;
   private String tipoEntidad;
   private String estado;
   private Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidad;
   
   public TipoEntidadDTO(String codigoTipoEntidad, String tipoEntidad, String estado, Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidad) {
     this.codigoTipoEntidad = codigoTipoEntidad;
     this.tipoEntidad = tipoEntidad;
     this.estado = estado;
     this.tipoDescuentoTipoEntidad = tipoDescuentoTipoEntidad;
   }
 
   
   public TipoEntidadDTO() {}
 
   
   public String getCodigoTipoEntidad() {
     return this.codigoTipoEntidad;
   }
   
   public void setCodigoTipoEntidad(String codigoTipoEntidad) {
     this.codigoTipoEntidad = codigoTipoEntidad;
   }
   
   public String getTipoEntidad() {
     return this.tipoEntidad;
   }
   
   public void setTipoEntidad(String tipoEntidad) {
     this.tipoEntidad = tipoEntidad;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
   
   public Collection<TipoDescuentoTipoEntidadDTO> getTipoDescuentoTipoEntidad() {
     return this.tipoDescuentoTipoEntidad;
   }
   
   public void setTipoDescuentoTipoEntidad(Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidad) {
     this.tipoDescuentoTipoEntidad = tipoDescuentoTipoEntidad;
   }
 }
