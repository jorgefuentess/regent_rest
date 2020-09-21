package com.regent.dtos;


public class EstadoEntidadDTO {
  private String codigoEstadoEntidad;
  
  private String estadoEntidad;
  
  private String estado;
  
  public EstadoEntidadDTO(String codigoEstadoEntidad, String estadoEntidad, String estado) {
    this.codigoEstadoEntidad = codigoEstadoEntidad;
    this.estadoEntidad = estadoEntidad;
    this.estado = estado;
  }
  
  public EstadoEntidadDTO() {}
  
  public String getCodigoEstadoEntidad() {
    return this.codigoEstadoEntidad;
  }
  
  public void setCodigoEstadoEntidad(String codigoEstadoEntidad) {
    this.codigoEstadoEntidad = codigoEstadoEntidad;
  }
  
  public String getEstadoEntidad() {
    return this.estadoEntidad;
  }
  
  public void setEstadoEntidad(String estadoEntidad) {
    this.estadoEntidad = estadoEntidad;
  }
  
  public String getEstado() {
    return this.estado;
  }
  
  public void setEstado(String estado) {
    this.estado = estado;
  }
}
