package com.regent.negocio;
import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "consulta_sql")
 public class ConsultaSQL
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_consulta_sql")
   @SequenceGenerator(allocationSize = 1, name = "sq_consulta_sql", sequenceName = "sq_consulta_sql")
   @Column(name = "codigo_consulta")
   private Integer codigoConsulta;
   @Column(name = "nombre_consulta")
   private String nombre;
   @Column(name = "consulta")
   private String consulta;
   @Column(name = "observaciones")
   private String observaciones;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public ConsultaSQL(Integer codigoConsulta, String nombre, String consulta, String observaciones, String estado, String usuario, String actualizado) {
     this.codigoConsulta = codigoConsulta;
     this.nombre = nombre;
     this.consulta = consulta;
     this.observaciones = observaciones;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public ConsultaSQL() {}
 
   
   public Integer getCodigoConsulta() {
     return this.codigoConsulta;
   }
   
   public void setCodigoConsulta(Integer codigoConsulta) {
     this.codigoConsulta = codigoConsulta;
   }
   
   public String getNombre() {
     return this.nombre;
   }
   
   public void setNombre(String nombre) {
     this.nombre = nombre;
   }
   
   public String getConsulta() {
     return this.consulta;
   }
   
   public void setConsulta(String consulta) {
     this.consulta = consulta;
   }
   
   public String getObservaciones() {
     return this.observaciones;
   }
   
   public void setObservaciones(String observaciones) {
     this.observaciones = observaciones;
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
