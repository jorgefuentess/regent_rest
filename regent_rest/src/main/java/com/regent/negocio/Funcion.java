package com.regent.negocio;

import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "funciones")
 public class Funcion
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_funciones")
   @SequenceGenerator(allocationSize = 1, name = "sq_funciones", sequenceName = "sq_funciones")
   @Column(name = "codigo_funcion")
   private Integer codigoFuncion;
   @Column(name = "nombre_funcion")
   private String nombreFuncion;
   @Column(name = "path")
   private String path;
   @Column(name = "detalle")
   private String detalle;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public Funcion(Integer codigoFuncion, String nombreFuncion, String path, String detalle, String estado, String usuario, String actualizado) {
       this.codigoFuncion = codigoFuncion;
       this.nombreFuncion = nombreFuncion;
       this.path = path;
       this.detalle = detalle;
       this.estado = estado;
       this.usuario = usuario;
       this.actualizado = actualizado;
   }
 
   
   public Funcion() {}
 
   
   public Integer getCodigoFuncion() {
       return this.codigoFuncion;
   }
 
   
   public void setCodigoFuncion(Integer codigoFuncion) {
       this.codigoFuncion = codigoFuncion;
   }
 
   
   public String getNombreFuncion() {
       return this.nombreFuncion;
   }
 
   
   public void setNombreFuncion(String nombreFuncion) {
       this.nombreFuncion = nombreFuncion;
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
   
   public String getPath() {
       return this.path;
   }
   
   public void setPath(String path) {
       this.path = path;
   }
   
   public String getDetalle() {
       return this.detalle;
   }
   
   public void setDetalle(String detalle) {
	     this.detalle = detalle;
   }
 }