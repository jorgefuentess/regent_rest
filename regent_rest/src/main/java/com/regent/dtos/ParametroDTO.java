package com.regent.dtos;


 public class ParametroDTO
 {
   private Integer codigoParametro;
   private String nombreParametro;
   private String descripcion;
   private String estado;
   
   public ParametroDTO(Integer codigoParametro, String nombreParametro, String descripcion, String estado) {
     this.codigoParametro = codigoParametro;
     this.nombreParametro = nombreParametro;
     this.descripcion = descripcion;
     this.estado = estado;
   }
 
 
   
   public ParametroDTO() {}
 
 
   
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
 }

