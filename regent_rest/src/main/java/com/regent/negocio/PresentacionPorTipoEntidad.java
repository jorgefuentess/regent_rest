package com.regent.negocio;

import com.regent.negocio.pk.PresentacionPorTipoEntidadPk;
 import javax.persistence.Column;
 import javax.persistence.EmbeddedId;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 
 
 
 
 @Entity
 @Table(name = "pres_por_tipo_entidad")
 public class PresentacionPorTipoEntidad
 {
   @EmbeddedId
   private PresentacionPorTipoEntidadPk presentacionPorTipoEntidadPk;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   @Column(name = "estado")
   private String estado;
   
   public PresentacionPorTipoEntidad(PresentacionPorTipoEntidadPk presentacionPorTipoEntidadPk, String usuario, String actualizado, String estado) {
     this.presentacionPorTipoEntidadPk = presentacionPorTipoEntidadPk;
     this.usuario = usuario;
     this.actualizado = actualizado;
     this.estado = estado;
   }
 
   
   public PresentacionPorTipoEntidad() {}
 
   
   public PresentacionPorTipoEntidadPk getPresentacionPorTipoEntidadPk() {
     return this.presentacionPorTipoEntidadPk;
   }
 
   
   public void setPresentacionPorTipoEntidadPk(PresentacionPorTipoEntidadPk presentacionPorTipoEntidadPk) {
     this.presentacionPorTipoEntidadPk = presentacionPorTipoEntidadPk;
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

