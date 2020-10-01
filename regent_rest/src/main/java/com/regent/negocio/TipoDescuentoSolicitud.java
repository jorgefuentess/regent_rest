package com.regent.negocio;


 import com.regent.negocio.pk.TipoDescuentoSolicitudPk;
 import javax.persistence.Column;
 import javax.persistence.EmbeddedId;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 
 
 @Entity
 @Table(name = "tipo_descuento_solicitud")
 public class TipoDescuentoSolicitud
 {
   @EmbeddedId
   private TipoDescuentoSolicitudPk tipoDescuentoSolicitudPk;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public TipoDescuentoSolicitud(TipoDescuentoSolicitudPk tipoDescuentoSolicitudPk, String usuario, String actualizado, String estado) {
     this.tipoDescuentoSolicitudPk = tipoDescuentoSolicitudPk;
     this.usuario = usuario;
     this.actualizado = actualizado;
     this.estado = estado;
   }
 
 
   
   public TipoDescuentoSolicitud() {}
 
   
   public TipoDescuentoSolicitudPk getTipoDescuentoSolicitudPk() {
     return this.tipoDescuentoSolicitudPk;
   }
   
   public void setTipoDescuentoSolicitudPk(TipoDescuentoSolicitudPk tipoDescuentoSolicitudPk) {
     this.tipoDescuentoSolicitudPk = tipoDescuentoSolicitudPk;
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