package com.regent.dtos;


 public class SafDTO
 {
   private String nSaf;
   private String organismoExt;
   private String responsable;
   private String eMail;
   private String telefono;
   private String estado;
   
   public SafDTO(String nSaf, String organismoExt, String responsable, String eMail, String telefono, String estado) {
     this.nSaf = nSaf;
     this.organismoExt = organismoExt;
     this.responsable = responsable;
     this.eMail = eMail;
     this.telefono = telefono;
     this.estado = estado;
   }
 
   
   public SafDTO() {}
 
   
   public String getnSaf() {
     return this.nSaf;
   }
   public void setnSaf(String nSaf) {
     this.nSaf = nSaf;
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
 }