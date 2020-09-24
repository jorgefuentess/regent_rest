package com.regent.negocio;

import com.regent.negocio.Entidad;
 import com.regent.negocio.MotivoNotificacion;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "notificaciones_de_cambio")
 public class NotificacionCambio
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_notificaciones_de_cambio")
   @SequenceGenerator(allocationSize = 1, name = "sq_notificaciones_de_cambio", sequenceName = "sq_notificaciones_de_cambio")
   @Column(name = "codigo_notificacion")
   private Integer codigoNotificacion;
   @ManyToOne
   @JoinColumn(name = "codigo_entidad")
   private Entidad entidad;
   @ManyToOne
   @JoinColumn(name = "codigo_motivo_notif")
   private MotivoNotificacion motivoNotificacion;
   @Column(name = "fecha_notificacion")
   private Date fechaNotificacion;
   @Column(name = "observaciones")
   private String observaciones;
   @Column(name = "if_gedo")
   private String ifGedo;
   @Column(name = "tri")
   private String tri;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public NotificacionCambio(Integer codigoNotificacion, Entidad entidad, MotivoNotificacion motivoNotificacion, Date fechaNotificacion, String observaciones, String ifGedo, String tri, String usuario, String actualizado) {
     this.codigoNotificacion = codigoNotificacion;
     this.entidad = entidad;
     this.motivoNotificacion = motivoNotificacion;
     this.fechaNotificacion = fechaNotificacion;
     this.observaciones = observaciones;
     this.ifGedo = ifGedo;
     this.tri = tri;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public NotificacionCambio() {}
 
   
   public Integer getCodigoNotificacion() {
     return this.codigoNotificacion;
   }
 
   
   public void setCodigoNotificacion(Integer codigoNotificacion) {
     this.codigoNotificacion = codigoNotificacion;
   }
 
   
   public Entidad getEntidad() {
     return this.entidad;
   }
 
   
   public void setEntidad(Entidad entidad) {
     this.entidad = entidad;
   }
 
   
   public MotivoNotificacion getMotivoNotificacion() {
     return this.motivoNotificacion;
   }
 
   
   public void setMotivoNotificacion(MotivoNotificacion motivoNotificacion) {
     this.motivoNotificacion = motivoNotificacion;
   }
 
   
   public Date getFechaNotificacion() {
     return this.fechaNotificacion;
   }
 
   
   public void setFechaNotificacion(Date fechaNotificacion) {
     this.fechaNotificacion = fechaNotificacion;
   }
 
   
   public String getObservaciones() {
     return this.observaciones;
   }
 
   
   public void setObservaciones(String observaciones) {
     this.observaciones = observaciones;
   }
 
   
   public String getIfGedo() {
     return this.ifGedo;
   }
 
   
   public void setIfGedo(String ifGedo) {
     this.ifGedo = ifGedo;
   }
 
   
   public String getTri() {
     return this.tri;
   }
 
   
   public void setTri(String tri) {
     this.tri = tri;
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