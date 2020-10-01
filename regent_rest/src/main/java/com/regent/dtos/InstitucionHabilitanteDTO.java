package com.regent.dtos;


 public class InstitucionHabilitanteDTO
 {
   private String codigoInstitucionHabilitante;
   private String institucionHabilitante;
   private String estado;
   
   public InstitucionHabilitanteDTO(String codigoInstitucionHabilitante, String institucionHabilitante, String estado) {
     this.codigoInstitucionHabilitante = codigoInstitucionHabilitante;
     this.institucionHabilitante = institucionHabilitante;
     this.estado = estado;
   }
 
   
   public InstitucionHabilitanteDTO() {}
 
   
   public String getCodigoInstitucionHabilitante() {
     return this.codigoInstitucionHabilitante;
   }
   
   public void setCodigoInstitucionHabilitante(String codigoInstitucionHabilitante) {
     this.codigoInstitucionHabilitante = codigoInstitucionHabilitante;
   }
   
   public String getInstitucionHabilitante() {
     return this.institucionHabilitante;
   }
   
   public void setInstitucionHabilitante(String institucionHabilitante) {
     this.institucionHabilitante = institucionHabilitante;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
 }
