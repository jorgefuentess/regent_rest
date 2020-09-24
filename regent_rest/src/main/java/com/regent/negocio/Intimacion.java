package com.regent.negocio;

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
 @Table(name = "intimaciones")
 public class Intimacion
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_intimaciones")
   @SequenceGenerator(allocationSize = 1, name = "sq_intimaciones", sequenceName = "sq_intimaciones")
   @Column(name = "codigo_intimacion")
   private Integer codigoIntimacion;
   @Column(name = "detalle")
   private String detalle;
   @ManyToOne
   @JoinColumn(name = "codigo_entidad")
   private Entidad entidad;
   @Column(name = "fecha_envio")
   private Date fechaEnvio;
   @Column(name = "fecha_recepcion")
   private Date fechaRecepcion;
   @Column(name = "aviso_recibo")
   private String avisoRecibo;
   @Column(name = "numero_de_intimacion")
   private Integer numeroDeIntimacion;
   @Column(name = "notificacion_positiva")
   private String notificacionPositiva;
   @Column(name = "cumplida")
   private String cumplida;
   @Column(name = "fecha_cumplimiento")
   private Date fechaCumplimiento;
   @Column(name = "numero_carta_documento")
   private String numeroCartaDocumento;
   @Column(name = "numero_expediente")
   private String numeroExpediente;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public Intimacion(Integer codigoIntimacion, String detalle, Entidad entidad, Date fechaEnvio, Date fechaRecepcion, String avisoRecibo, Integer numeroDeIntimacion, String notificacionPositiva, String cumplida, Date fechaCumplimiento, String numeroCartaDocumento, String numeroExpediente, String usuario, String actualizado) {
     this.codigoIntimacion = codigoIntimacion;
     this.detalle = detalle;
     this.entidad = entidad;
     this.fechaEnvio = fechaEnvio;
     this.fechaRecepcion = fechaRecepcion;
     this.avisoRecibo = avisoRecibo;
     this.numeroDeIntimacion = numeroDeIntimacion;
     this.notificacionPositiva = notificacionPositiva;
     this.cumplida = cumplida;
     this.fechaCumplimiento = fechaCumplimiento;
     this.numeroCartaDocumento = numeroCartaDocumento;
     this.numeroExpediente = numeroExpediente;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public Intimacion() {}
 
   
   public Integer getCodigoIntimacion() {
     return this.codigoIntimacion;
   }
 
   
   public void setCodigoIntimacion(Integer codigoIntimacion) {
     this.codigoIntimacion = codigoIntimacion;
   }
 
   
   public String getDetalle() {
     return this.detalle;
   }
 
   
   public void setDetalle(String detalle) {
     this.detalle = detalle;
   }
 
   
   public Entidad getEntidad() {
     return this.entidad;
   }
 
   
   public void setEntidad(Entidad entidad) {
     this.entidad = entidad;
   }
 
   
   public Date getFechaEnvio() {
     return this.fechaEnvio;
   }
 
   
   public void setFechaEnvio(Date fechaEnvio) {
     this.fechaEnvio = fechaEnvio;
   }
 
   
   public Date getFechaRecepcion() {
     return this.fechaRecepcion;
   }
 
   
   public void setFechaRecepcion(Date fechaRecepcion) {
     this.fechaRecepcion = fechaRecepcion;
   }
 
   
   public String getAvisoRecibo() {
     return this.avisoRecibo;
   }
 
   
   public void setAvisoRecibo(String avisoRecibo) {
     this.avisoRecibo = avisoRecibo;
   }
 
   
   public Integer getNumeroDeIntimacion() {
     return this.numeroDeIntimacion;
   }
 
   
   public void setNumeroDeIntimacion(Integer numeroDeIntimacion) {
     this.numeroDeIntimacion = numeroDeIntimacion;
   }
 
   
   public String getNotificacionPositiva() {
     return this.notificacionPositiva;
   }
 
   
   public void setNotificacionPositiva(String notificacionPositiva) {
     this.notificacionPositiva = notificacionPositiva;
   }
 
   
   public String getCumplida() {
     return this.cumplida;
   }
 
   
   public void setCumplida(String cumplida) {
     this.cumplida = cumplida;
   }
 
   
   public Date getFechaCumplimiento() {
     return this.fechaCumplimiento;
   }
 
   
   public void setFechaCumplimiento(Date fechaCumplimiento) {
     this.fechaCumplimiento = fechaCumplimiento;
   }
 
   
   public String getNumeroCartaDocumento() {
     return this.numeroCartaDocumento;
   }
 
   
   public void setNumeroCartaDocumento(String numeroCartaDocumento) {
     this.numeroCartaDocumento = numeroCartaDocumento;
   }
 
   
   public String getNumeroExpediente() {
     return this.numeroExpediente;
   }
 
   
   public void setNumeroExpediente(String numeroExpediente) {
     this.numeroExpediente = numeroExpediente;
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