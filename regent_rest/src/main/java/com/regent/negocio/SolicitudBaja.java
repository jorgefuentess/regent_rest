package com.regent.negocio;


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
 @Table(name = "solicitud_baja")
 public class SolicitudBaja
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_solicitud_baja")
   @SequenceGenerator(allocationSize = 1, name = "sq_solicitud_baja", sequenceName = "sq_solicitud_baja")
   @Column(name = "codigo_solicitud")
   private Integer codigoSolicitud;
   @ManyToOne
   @JoinColumn(name = "codigo_entidad")
   private Entidad entidad;
   @Column(name = "fecha_solicitud")
   private String fechaSolicitud;
   @Column(name = "numero_expediente")
   private String numeroExpediente;
   @Column(name = "baja")
   private String baja;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public SolicitudBaja(Integer codigoSolicitud, Entidad entidad, String fechaSolicitud, String numeroExpediente, String baja, String usuario, String actualizado) {
     this.codigoSolicitud = codigoSolicitud;
     this.entidad = entidad;
     this.fechaSolicitud = fechaSolicitud;
     this.numeroExpediente = numeroExpediente;
     this.baja = baja;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
 
   
   public SolicitudBaja() {}
 
 
   
   public Integer getCodigoSolicitud() {
     return this.codigoSolicitud;
   }
 
   
   public void setCodigoSolicitud(Integer codigoSolicitud) {
     this.codigoSolicitud = codigoSolicitud;
   }
 
   
   public Entidad getEntidad() {
     return this.entidad;
   }
 
   
   public void setEntidad(Entidad entidad) {
     this.entidad = entidad;
   }
 
   
   public String getFechaSolicitud() {
     return this.fechaSolicitud;
   }
 
   
   public void setFechaSolicitud(String fechaSolicitud) {
     this.fechaSolicitud = fechaSolicitud;
   }
 
   
   public String getNumeroExpediente() {
     return this.numeroExpediente;
   }
 
   
   public void setNumeroExpediente(String numeroExpediente) {
     this.numeroExpediente = numeroExpediente;
   }
 
   
   public String getBaja() {
     return this.baja;
   }
 
   
   public void setBaja(String baja) {
     this.baja = baja;
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