package com.regent.negocio;


 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "parametros")
 public class Parametro
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_parametros")
   @SequenceGenerator(allocationSize = 1, name = "sq_parametros", sequenceName = "sq_parametros")
   @Column(name = "codigo_parametro")
   private Integer codigoParametro;
   @Column(name = "nombre_parametro")
   private String nombreParametro;
   @Column(name = "descripcion")
   private String descripcion;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public Parametro(Integer codigoParametro, String nombreParametro, String descripcion, String estado, String usuario, String actualizado) {
     this.codigoParametro = codigoParametro;
     this.nombreParametro = nombreParametro;
     this.descripcion = descripcion;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
 
   
   public Parametro() {}
 
 
   
   public Integer getCodigoParametro() {
     return this.codigoParametro;
   }
   
   public void setCodigoParametro(Integer codigoParametro) {
     this.codigoParametro = codigoParametro;
   }
   
   public String getNombreParametro() {
     return this.nombreParametro;
   }
   
   public void setNombreParametro(String nombreParametro) {
     this.nombreParametro = nombreParametro;
   }
   
   public String getDescripcion() {
     return this.descripcion;
   }
   
   public void setDescripcion(String descripcion) {
     this.descripcion = descripcion;
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
