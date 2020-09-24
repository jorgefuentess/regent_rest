package com.regent.dtos;

import java.util.Collection;
 
 
 
 
 public class CertificacionDTO
 {
   private String generoSolicitante;
   private String naSolicitante;
   private String dniSolicitante;
   private String situRevistaSolicitante;
   private String antiguedad;
   private String retribucion;
   private String codigoEntidad;
   private String tasa;
   private String naSuscribe;
   private String cargoSuscribe;
   private String codigoDescuento;
   private Collection<Object[]> conceptos;
   
   public CertificacionDTO(String generoSolicitante, String naSolicitante, String dniSolicitante, String situRevistaSolicitante, String antiguedad, String retribucion, String codigoEntidad, String tasa, String naSuscribe, String cargoSuscribe, String codigoDescuento, Collection<Object[]> conceptos) {
     this.generoSolicitante = generoSolicitante;
     this.naSolicitante = naSolicitante;
     this.dniSolicitante = dniSolicitante;
     this.situRevistaSolicitante = situRevistaSolicitante;
     this.antiguedad = antiguedad;
     this.retribucion = retribucion;
     this.codigoEntidad = codigoEntidad;
     this.tasa = tasa;
     this.naSuscribe = naSuscribe;
     this.cargoSuscribe = cargoSuscribe;
     this.codigoDescuento = codigoDescuento;
     this.conceptos = conceptos;
   }
 
   
   public CertificacionDTO() {}
 
   
   public String getNaSolicitante() {
     return this.naSolicitante;
   }
   
   public void setNaSolicitante(String naSolicitante) {
     this.naSolicitante = naSolicitante;
   }
   
   public String getDniSolicitante() {
     return this.dniSolicitante;
   }
   
   public void setDniSolicitante(String dniSolicitante) {
     this.dniSolicitante = dniSolicitante;
   }
   
   public String getSituRevistaSolicitante() {
     return this.situRevistaSolicitante;
   }
   
   public void setSituRevistaSolicitante(String situRevistaSolicitante) {
     this.situRevistaSolicitante = situRevistaSolicitante;
   }
   
   public String getAntiguedad() {
     return this.antiguedad;
   }
   
   public void setAntiguedad(String antiguedad) {
     this.antiguedad = antiguedad;
   }
   
   public String getRetribucion() {
     return this.retribucion;
   }
   
   public void setRetribucion(String retribucion) {
     this.retribucion = retribucion;
   }
   
   public String getTasa() {
     return this.tasa;
   }
   
   public void setTasa(String tasa) {
     this.tasa = tasa;
   }
   
   public String getNaSuscribe() {
     return this.naSuscribe;
   }
   
   public void setNaSuscribe(String naSuscribe) {
     this.naSuscribe = naSuscribe;
   }
   
   public String getCargoSuscribe() {
     return this.cargoSuscribe;
   }
   
   public void setCargoSuscribe(String cargoSuscribe) {
     this.cargoSuscribe = cargoSuscribe;
   }
   
   public String getCodigoDescuento() {
     return this.codigoDescuento;
   }
   
   public void setCodigoDescuento(String codigoDescuento) {
     this.codigoDescuento = codigoDescuento;
   }
   
   public Collection<Object[]> getConceptos() {
     return this.conceptos;
   }
   
   public void setConceptos(Collection<Object[]> conceptos) {
     this.conceptos = conceptos;
   }
 
   
   public String getCodigoEntidad() {
     return this.codigoEntidad;
   }
   
   public void setCodigoEntidad(String codigoEntidad) {
     this.codigoEntidad = codigoEntidad;
   }
   
   public String getGeneroSolicitante() {
     return this.generoSolicitante;
   }
   
   public void setGeneroSolicitante(String generoSolicitante) {
     this.generoSolicitante = generoSolicitante;
   }
 }

