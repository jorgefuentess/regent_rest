package com.regent.dtos;


public class PresentacionEntidadDTO {
  private Integer codigoPresEnt;
  
  private Integer codigoTipoPresentacion;
  
  private String tipoPresentacion;
  
  private String nombreArchivo;
  
  private String fechaCarga;
  
  private Boolean validada;
  
  private String mesCierreDeBalance;
  
  private String fechaInicioMandato;
  
  private String mandatoEnAnios;
  
  private String siglaArchivo;
  
  private String ifGedo;
  
  private String vigente;
  
  public PresentacionEntidadDTO() {}
  
  public PresentacionEntidadDTO(Integer codigoPresEnt, Integer codigoTipoPresentacion, String tipoPresentacion, Boolean validada, String mesCierreDeBalance, String fechaInicioMandato, String nombreArchivo, String fechaCarga, String mandatoEnAnios, String siglaArchivo, String ifGedo, String vigente) {
    this.codigoPresEnt = codigoPresEnt;
    this.codigoTipoPresentacion = codigoTipoPresentacion;
    this.tipoPresentacion = tipoPresentacion;
    this.validada = validada;
    this.mesCierreDeBalance = mesCierreDeBalance;
    this.fechaInicioMandato = fechaInicioMandato;
    this.nombreArchivo = nombreArchivo;
    this.mandatoEnAnios = mandatoEnAnios;
    this.fechaCarga = fechaCarga;
    this.siglaArchivo = siglaArchivo;
    this.ifGedo = ifGedo;
    this.vigente = vigente;
  }
  
  public Integer getCodigoPresEnt() {
    return this.codigoPresEnt;
  }
  
  public void setCodigoPresEnt(Integer codigoPresEnt) {
    this.codigoPresEnt = codigoPresEnt;
  }
  
  public Integer getCodigoTipoPresentacion() {
    return this.codigoTipoPresentacion;
  }
  
  public void setCodigoTipoPresentacion(Integer codigoTipoPresentacion) {
    this.codigoTipoPresentacion = codigoTipoPresentacion;
  }
  
  public String getTipoPresentacion() {
    return this.tipoPresentacion;
  }
  
  public void setTipoPresentacion(String tipoPresentacion) {
    this.tipoPresentacion = tipoPresentacion;
  }
  
  public Boolean getValidada() {
    return this.validada;
  }
  
  public void setValidada(Boolean validada) {
    this.validada = validada;
  }
  
  public String getMesCierreDeBalance() {
    return this.mesCierreDeBalance;
  }
  
  public void setMesCierreDeBalance(String mesCierreDeBalance) {
    this.mesCierreDeBalance = mesCierreDeBalance;
  }
  
  public String getFechaInicioMandato() {
    return this.fechaInicioMandato;
  }
  
  public void setFechaInicioMandato(String fechaInicioMandato) {
    this.fechaInicioMandato = fechaInicioMandato;
  }
  
  public String getMandatoEnAnios() {
    return this.mandatoEnAnios;
  }
  
  public void setMandatoEnAnios(String mandatoEnAnios) {
    this.mandatoEnAnios = mandatoEnAnios;
  }
  
  public String getNombreArchivo() {
    return this.nombreArchivo;
  }
  
  public void setNombreArchivo(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo;
  }
  
  public String getFechaCarga() {
    return this.fechaCarga;
  }
  
  public void setFechaCarga(String fechaCarga) {
    this.fechaCarga = fechaCarga;
  }
  
  public String getSiglaArchivo() {
    return this.siglaArchivo;
  }
  
  public void setSiglaArchivo(String siglaArchivo) {
    this.siglaArchivo = siglaArchivo;
  }
  
  public String getIfGedo() {
    return this.ifGedo;
  }
  
  public void setIfGedo(String ifGedo) {
    this.ifGedo = ifGedo;
  }
  
  public String getVigente() {
    return this.vigente;
  }
  
  public void setVigente(String vigente) {
    this.vigente = vigente;
  }
}
