package com.regent.dtos;


 
 public class DatosSafDTO
 {
   private String mesAnio;
   private String cuil;
   private String codigoDescuento;
   private String nroCertificado;
   private String fechaEmision;
   private String tasa;
   private String remuneracion;
   private String porcentaje;
   private String comienzoAfectacion;
   private String montoTotal;
   private String montoCuota;
   private String nroCuota;
   private String fechaFin;
   private String fechaCarga;
   
   public DatosSafDTO(String mesAnio, String cuil, String codigoDescuento, String nroCertificado, String fechaEmision, String tasa, String remuneracion, String porcentaje, String comienzoAfectacion, String montoTotal, String montoCuota, String nroCuota, String fechaFin, String fechaCarga) {
     this.mesAnio = mesAnio;
     this.cuil = cuil;
     this.codigoDescuento = codigoDescuento;
     this.nroCertificado = nroCertificado;
     this.fechaEmision = fechaEmision;
     this.tasa = tasa;
     this.remuneracion = remuneracion;
     this.porcentaje = porcentaje;
     this.comienzoAfectacion = comienzoAfectacion;
     this.montoTotal = montoTotal;
     this.montoCuota = montoCuota;
     this.nroCuota = nroCuota;
     this.fechaFin = fechaFin;
     this.fechaCarga = fechaCarga;
   }
 
   
   public DatosSafDTO() {}
 
   
   public String getMesAnio() {
     return this.mesAnio;
   }
   
   public void setMesAnio(String mesAnio) {
     this.mesAnio = mesAnio;
   }
   
   public String getCuil() {
     return this.cuil;
   }
   
   public void setCuil(String cuil) {
     this.cuil = cuil;
   }
   
   public String getCodigoDescuento() {
     return this.codigoDescuento;
   }
   
   public void setCodigoDescuento(String codigoDescuento) {
     this.codigoDescuento = codigoDescuento;
   }
   
   public String getNroCertificado() {
     return this.nroCertificado;
   }
   
   public void setNroCertificado(String nroCertificado) {
     this.nroCertificado = nroCertificado;
   }
   
   public String getFechaEmision() {
     return this.fechaEmision;
   }
   
   public void setFechaEmision(String fechaEmision) {
     this.fechaEmision = fechaEmision;
   }
   
   public String getTasa() {
     return this.tasa;
   }
   
   public void setTasa(String tasa) {
     this.tasa = tasa;
   }
   
   public String getRemuneracion() {
     return this.remuneracion;
   }
   
   public void setRemuneracion(String remuneracion) {
     this.remuneracion = remuneracion;
   }
   
   public String getPorcentaje() {
     return this.porcentaje;
   }
   
   public void setPorcentaje(String porcentaje) {
     this.porcentaje = porcentaje;
   }
   
   public String getComienzoAfectacion() {
     return this.comienzoAfectacion;
   }
   
   public void setComienzoAfectacion(String comienzoAfectacion) {
     this.comienzoAfectacion = comienzoAfectacion;
   }
   
   public String getMontoTotal() {
     return this.montoTotal;
   }
   
   public void setMontoTotal(String montoTotal) {
     this.montoTotal = montoTotal;
   }
   
   public String getMontoCuota() {
     return this.montoCuota;
   }
   
   public void setMontoCuota(String montoCuota) {
     this.montoCuota = montoCuota;
   }
   
   public String getNroCuota() {
     return this.nroCuota;
   }
   
   public void setNroCuota(String nroCuota) {
     this.nroCuota = nroCuota;
   }
   
   public String getFechaFin() {
     return this.fechaFin;
   }
   
   public void setFechaFin(String fechaFin) {
     this.fechaFin = fechaFin;
   }
   
   public String getFechaCarga() {
     return this.fechaCarga;
   }
   
   public void setFechaCarga(String fechaCarga) {
     this.fechaCarga = fechaCarga;
   }
 }