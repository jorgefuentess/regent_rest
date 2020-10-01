package com.regent.negocio;


 import com.regent.negocio.pk.PresentacionIntimacionPk;
 import javax.persistence.Column;
 import javax.persistence.EmbeddedId;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 
 
 
 
 @Entity
 @Table(name = "presentacion_intimacion")
 public class PresentacionIntimacion
 {
   @EmbeddedId
   private PresentacionIntimacionPk presentacionIntimacionPk;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public PresentacionIntimacion(PresentacionIntimacionPk presentacionIntimacionPk, String estado, String usuario, String actualizado) {
     this.presentacionIntimacionPk = presentacionIntimacionPk;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public PresentacionIntimacion() {}
 
   
   public PresentacionIntimacionPk getPresentacionIntimacionPk() {
     return this.presentacionIntimacionPk;
   }
 
   
   public void setPresentacionIntimacionPk(PresentacionIntimacionPk presentacionIntimacionPk) {
     this.presentacionIntimacionPk = presentacionIntimacionPk;
   }
 
   
   public String getEstado() {
     return this.estado;
   }
 
   
   public void setEstado(String estado) {
     this.estado = estado;
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
 }