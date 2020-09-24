package com.regent.negocio;

import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "motivos_notificacion")
 public class MotivoNotificacion
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_motivos_notificacion")
   @SequenceGenerator(allocationSize = 1, name = "sq_motivos_notificacion", sequenceName = "sq_motivos_notificacion")
   @Column(name = "codigo_motivo_notif")
   private Integer codigoMotivoNotif;
   @Column(name = "nombre_motivo_notif")
   private String nombreMotivoNotif;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public MotivoNotificacion(Integer codigoMotivoNotif, String nombreMotivoNotif, String estado, String usuario, String actualizado) {
     this.codigoMotivoNotif = codigoMotivoNotif;
     this.nombreMotivoNotif = nombreMotivoNotif;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public MotivoNotificacion() {}
 
   
   public Integer getCodigoMotivoNotif() {
     return this.codigoMotivoNotif;
   }
 
   
   public void setCodigoMotivoNotif(Integer codigoMotivoNotif) {
     this.codigoMotivoNotif = codigoMotivoNotif;
   }
 
   
   public String getNombreMotivoNotif() {
     return this.nombreMotivoNotif;
   }
 
   
   public void setNombreMotivoNotif(String nombreMotivoNotif) {
     this.nombreMotivoNotif = nombreMotivoNotif;
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