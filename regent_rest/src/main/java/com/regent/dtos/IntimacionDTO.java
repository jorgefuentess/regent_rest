package com.regent.dtos;


public class IntimacionDTO {
  private String codigoIntimacion;
  
  private String detalle;
  
  private String entidad;
  
  private String fechaEnvio;
  
  private String fechaRecepcion;
  
  private String avisoRecibo;
  
  private String numeroDeIntimacion;
  
  private String notificacionPositiva;
  
  private String cumplida;
  
  private String fechaCumplimiento;
  
  private String numeroCartaDocumento;
  
  private String numeroExpediente;
  
  public IntimacionDTO() {}
  
  public IntimacionDTO(String codigoIntimacion, String detalle, String entidad, String fechaEnvio, String fechaRecepcion, String avisoRecibo, String numeroDeIntimacion, String notificacionPositiva, String cumplida, String fechaCumplimiento, String numeroCartaDocumento, String numeroExpediente) {
    this.codigoIntimacion = codigoIntimacion;
    this.detalle = detalle;
    this.entidad = entidad;
    this.fechaEnvio = fechaEnvio;
    this.fechaRecepcion = fechaRecepcion;
    this.avisoRecibo = avisoRecibo;
    this.numeroDeIntimacion = numeroDeIntimacion;
    this.notificacionPositiva = notificacionPositiva;
    this.cumplida = cumplida;
    this.fechaCumplimiento = fechaCumplimiento;
    this.numeroCartaDocumento = numeroCartaDocumento;
    this.numeroExpediente = numeroExpediente;
  }
  
  public String getCodigoIntimacion() {
    return this.codigoIntimacion;
  }
  
  public void setCodigoIntimacion(String codigoIntimacion) {
    this.codigoIntimacion = codigoIntimacion;
  }
  
  public String getDetalle() {
    return this.detalle;
  }
  
  public void setDetalle(String detalle) {
    this.detalle = detalle;
  }
  
  public String getEntidad() {
    return this.entidad;
  }
  
  public void setEntidad(String entidad) {
    this.entidad = entidad;
  }
  
  public String getFechaEnvio() {
    return this.fechaEnvio;
  }
  
  public void setFechaEnvio(String fechaEnvio) {
    this.fechaEnvio = fechaEnvio;
  }
  
  public String getFechaRecepcion() {
    return this.fechaRecepcion;
  }
  
  public void setFechaRecepcion(String fechaRecepcion) {
    this.fechaRecepcion = fechaRecepcion;
  }
  
  public String getAvisoRecibo() {
    return this.avisoRecibo;
  }
  
  public void setAvisoRecibo(String avisoRecibo) {
    this.avisoRecibo = avisoRecibo;
  }
  
  public String getNumeroDeIntimacion() {
    return this.numeroDeIntimacion;
  }
  
  public void setNumeroDeIntimacion(String numeroDeIntimacion) {
    this.numeroDeIntimacion = numeroDeIntimacion;
  }
  
  public String getNotificacionPositiva() {
    return this.notificacionPositiva;
  }
  
  public void setNotificacionPositiva(String notificacionPositiva) {
    this.notificacionPositiva = notificacionPositiva;
  }
  
  public String getCumplida() {
    return this.cumplida;
  }
  
  public void setCumplida(String cumplida) {
    this.cumplida = cumplida;
  }
  
  public String getFechaCumplimiento() {
    return this.fechaCumplimiento;
  }
  
  public void setFechaCumplimiento(String fechaCumplimiento) {
    this.fechaCumplimiento = fechaCumplimiento;
  }
  
  public String getNumeroCartaDocumento() {
    return this.numeroCartaDocumento;
  }
  
  public void setNumeroCartaDocumento(String numeroCartaDocumento) {
    this.numeroCartaDocumento = numeroCartaDocumento;
  }
  
  public String getNumeroExpediente() {
    return this.numeroExpediente;
  }
  
  public void setNumeroExpediente(String numeroExpediente) {
    this.numeroExpediente = numeroExpediente;
  }
}

