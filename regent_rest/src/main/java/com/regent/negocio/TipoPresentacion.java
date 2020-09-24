package com.regent.negocio;


 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "tipo_presentaciones")
 public class TipoPresentacion
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_presentacion")
   @SequenceGenerator(allocationSize = 1, name = "sq_tipo_presentacion", sequenceName = "sq_tipo_presentacion")
   @Column(name = "codigo_tipo_presentacion")
   private Integer codigoTipoPresentacion;
   @Column(name = "nombre_tipo_presentacion")
   private String nombreTipoPresentacion;
   @Column(name = "nombre_archivo")
   private String nombreArchivo;
   @Column(name = "vence")
   private String vence;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public TipoPresentacion(Integer codigoTipoPresentacion, String nombreTipoPresentacion, String nombreArchivo, String estado, String vence, String usuario, String actualizado) {
     this.codigoTipoPresentacion = codigoTipoPresentacion;
     this.nombreTipoPresentacion = nombreTipoPresentacion;
     this.nombreArchivo = nombreArchivo;
     this.vence = vence;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
 
   
   public TipoPresentacion() {}
 
   
   public Integer getCodigoTipoPresentacion() {
     return this.codigoTipoPresentacion;
   }
   public void setCodigoTipoPresentacion(Integer codigoTipoPresentacion) {
     this.codigoTipoPresentacion = codigoTipoPresentacion;
   }
   public String getNombreTipoPresentacion() {
     return this.nombreTipoPresentacion;
   }
   public void setNombreTipoPresentacion(String nombreTipoPresentacion) {
     this.nombreTipoPresentacion = nombreTipoPresentacion;
   }
   public String getNombreArchivo() {
     return this.nombreArchivo;
   }
   public void setNombreArchivo(String nombreArchivo) {
     this.nombreArchivo = nombreArchivo;
   }
   public String getVence() {
     return this.vence;
   }
   public void setVence(String vence) {
     this.vence = vence;
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

