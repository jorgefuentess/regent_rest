package com.regent.negocio;

import com.regent.negocio.pk.TipoDescuentoTipoEntidadPk;
 import javax.persistence.Column;
 import javax.persistence.EmbeddedId;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 
 
 
 
 @Entity
 @Table(name = "tipo_dto_tipo_entidad")
 public class TipoDescuentoTipoEntidad
 {
   @EmbeddedId
   private TipoDescuentoTipoEntidadPk tipoDescuentoTipoEntidadPk;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   @Column(name = "estado")
   private String estado;
   
   public TipoDescuentoTipoEntidad(TipoDescuentoTipoEntidadPk tipoDescuentoTipoEntidadPk, String usuario, String actualizado, String estado) {
     this.tipoDescuentoTipoEntidadPk = tipoDescuentoTipoEntidadPk;
     this.usuario = usuario;
     this.actualizado = actualizado;
     this.estado = estado;
   }
 
   
   public TipoDescuentoTipoEntidad() {}
 
   
   public TipoDescuentoTipoEntidadPk getTipoDescuentoTipoEntidadPk() {
     return this.tipoDescuentoTipoEntidadPk;
   }
 
   
   public void setTipoDescuentoTipoEntidadPk(TipoDescuentoTipoEntidadPk tipoDescuentoTipoEntidadPk) {
     this.tipoDescuentoTipoEntidadPk = tipoDescuentoTipoEntidadPk;
   }
 
   
   public String getUsuario() {
     return this.usuario;
   }
 
   
   public void setUsuario(String usuario) {
     this.usuario = usuario;
   }
 
   
   public String getActualizado() {
     return this.actualizado;
   }
 
   
   public void setActualizado(String actualizado) {
     this.actualizado = actualizado;
   }
 
   
   public String getEstado() {
     return this.estado;
   }
 
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
 }

