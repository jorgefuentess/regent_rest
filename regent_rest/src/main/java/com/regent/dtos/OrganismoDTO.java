package com.regent.dtos;


 public class OrganismoDTO
 {
   private String nSaf;
   private String organismoExt;
   
   public OrganismoDTO(String nSaf, String organismoExt) {
     this.nSaf = nSaf;
     this.organismoExt = organismoExt;
   }
 
   
   public OrganismoDTO() {}
 
   
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
 }
