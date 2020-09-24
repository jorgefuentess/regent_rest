package com.regent.negocio;

import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "saf")
 public class Saf
 {
   @Id
   @Column(name = "n_saf")
   private Integer nSaf;
   @Column(name = "cfu_organismo")
   private String cfuOrganismo;
   @Column(name = "organismo_ext")
   private String organismoExt;
   @Column(name = "responsable")
   private String responsable;
   @Column(name = "e_mail")
   private String eMail;
   @Column(name = "telefono")
   private String telefono;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public Saf(Integer nSaf, String cfuOrganismo, String organismoExt, String responsable, String eMail, String telefono, String estado, String usuario, String actualizado) {
     this.nSaf = nSaf;
     this.cfuOrganismo = cfuOrganismo;
     this.organismoExt = organismoExt;
     this.responsable = responsable;
     this.eMail = eMail;
     this.telefono = telefono;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public Saf() {}
 
   
   public Integer getnSaf() {
     return this.nSaf;
   }
   public void setnSaf(Integer nSaf) {
     this.nSaf = nSaf;
   }
   public String getCfuOrganismo() {
     return this.cfuOrganismo;
   }
   public void setCfuOrganismo(String cfuOrganismo) {
     this.cfuOrganismo = cfuOrganismo;
   }
   public String getOrganismoExt() {
     return this.organismoExt;
   }
   public void setOrganismoExt(String organismoExt) {
     this.organismoExt = organismoExt;
   }
   public String getResponsable() {
     return this.responsable;
   }
   public void setResponsable(String responsable) {
     this.responsable = responsable;
   }
   public String geteMail() {
     return this.eMail;
   }
   public void seteMail(String eMail) {
     this.eMail = eMail;
   }
   public String getTelefono() {
     return this.telefono;
   }
   public void setTelefono(String telefono) {
     this.telefono = telefono;
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