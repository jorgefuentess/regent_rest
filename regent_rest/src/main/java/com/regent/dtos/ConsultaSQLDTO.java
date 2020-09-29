package com.regent.dtos;

 
 public class ConsultaSQLDTO
 {
   private Integer codigoConsulta;
   private String nombre;
   private String consulta;
   private String observaciones;
   private String estado;
   private Boolean periodoDesde;
   private Boolean periodoHasta;
   private Boolean periodo;
   private Integer periodoDesdeI;
   private Integer periodoHastaI;
   private Integer periodoI;
   
   public ConsultaSQLDTO(Integer codigoConsulta, String nombre, String consulta, String observaciones, String estado, Boolean periodoDesde, Boolean periodoHasta, Boolean periodo, Integer periodoDesdeI, Integer periodoHastaI, Integer periodoI) {
     this.codigoConsulta = codigoConsulta;
     this.nombre = nombre;
     this.consulta = consulta;
     this.observaciones = observaciones;
     this.estado = estado;
     this.periodoDesde = periodoDesde;
     this.periodoHasta = periodoHasta;
     this.periodo = periodo;
     this.periodoDesdeI = periodoDesdeI;
     this.periodoHastaI = periodoHastaI;
     this.periodoI = periodoI;
   }
 
   
   public ConsultaSQLDTO() {}
 
   
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
   
   public Boolean getPeriodoDesde() {
     return this.periodoDesde;
   }
   
   public void setPeriodoDesde(Boolean periodoDesde) {
     this.periodoDesde = periodoDesde;
   }
   
   public Boolean getPeriodoHasta() {
     return this.periodoHasta;
   }
   
   public void setPeriodoHasta(Boolean periodoHasta) {
     this.periodoHasta = periodoHasta;
   }
   
   public Boolean getPeriodo() {
     return this.periodo;
   }
   
   public void setPeriodo(Boolean periodo) {
     this.periodo = periodo;
   }
   
   public Integer getPeriodoDesdeI() {
     return this.periodoDesdeI;
   }
   
   public void setPeriodoDesdeI(Integer periodoDesdeI) {
     this.periodoDesdeI = periodoDesdeI;
   }
   
   public Integer getPeriodoHastaI() {
     return this.periodoHastaI;
   }
   
   public void setPeriodoHastaI(Integer periodoHastaI) {
     this.periodoHastaI = periodoHastaI;
   }
   
   public Integer getPeriodoI() {
     return this.periodoI;
   }
   
   public void setPeriodoI(Integer periodoI) {
     this.periodoI = periodoI;
   }
 }