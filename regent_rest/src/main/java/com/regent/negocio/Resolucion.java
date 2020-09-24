package com.regent.negocio;

import com.regent.negocio.Entidad;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 
 
 
 
 
 
 
 
 
 @Entity
 @Table(name = "resoluciones")
 public class Resolucion
 {
   @Id
   @Column(name = "codigo_resolucion")
   private Integer codigoResolucion;
   @ManyToOne
   @JoinColumn(name = "codigo_entidad")
   private Entidad entidad;
   @Column(name = "nro_resolucion")
   private String nroResolucion;
   @Column(name = "exp_jgm")
   private String expJgm;
   @Column(name = "exp_mmod")
   private String expMmod;
   @Column(name = "link_boletin")
   private String linkBoletin;
   @Column(name = "fecha_vigencia")
   private Date fechaVigencia;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public Resolucion(Integer codigoResolucion, Entidad entidad, String nroResolucion, String expJgm, String expMmod, String linkBoletin, Date fechaVigencia, String usuario, String actualizado) {
     this.codigoResolucion = codigoResolucion;
     this.entidad = entidad;
     this.nroResolucion = nroResolucion;
     this.expJgm = expJgm;
     this.expMmod = expMmod;
     this.linkBoletin = linkBoletin;
     this.fechaVigencia = fechaVigencia;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public Resolucion() {}
 
   
   public Integer getCodigoResolucion() {
     return this.codigoResolucion;
   }
 
   
   public void setCodigoResolucion(Integer codigoResolucion) {
     this.codigoResolucion = codigoResolucion;
   }
 
   
   public Entidad getEntidad() {
     return this.entidad;
   }
 
   
   public void setEntidad(Entidad entidad) {
     this.entidad = entidad;
   }
 
   
   public String getNroResolucion() {
     return this.nroResolucion;
   }
 
   
   public void setNroResolucion(String nroResolucion) {
     this.nroResolucion = nroResolucion;
   }
 
   
   public String getExpJgm() {
     return this.expJgm;
   }
 
   
   public void setExpJgm(String expJgm) {
     this.expJgm = expJgm;
   }
 
   
   public String getExpMmod() {
     return this.expMmod;
   }
 
   
   public void setExpMmod(String expMmod) {
     this.expMmod = expMmod;
   }
 
   
   public String getLinkBoletin() {
     return this.linkBoletin;
   }
 
   
   public void setLinkBoletin(String linkBoletin) {
     this.linkBoletin = linkBoletin;
   }
 
   
   public Date getFechaVigencia() {
     return this.fechaVigencia;
   }
 
   
   public void setFechaVigencia(Date fechaVigencia) {
     this.fechaVigencia = fechaVigencia;
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
