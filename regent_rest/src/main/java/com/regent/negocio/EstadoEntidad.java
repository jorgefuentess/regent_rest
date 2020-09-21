package com.regent.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "estado_entidad")
public class EstadoEntidad {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_estado_entidad")
  @SequenceGenerator(allocationSize = 1, name = "sq_estado_entidad", sequenceName = "sq_estado_entidad")
  @Column(name = "codigo_estado")
  private Integer codigoEstado;
  
  @Column(name = "nombre_estado")
  private String nombreEstado;
  
  @Column(name = "estado")
  private String estado;
  
  @Column(name = "usuario")
  private String usuario;
  
  @Column(name = "actualizado")
  private String actualizado;
  
  public EstadoEntidad(Integer codigoEstado, String nombreEstado, String estado, String usuario, String actualizado) {
    this.codigoEstado = codigoEstado;
    this.nombreEstado = nombreEstado;
    this.estado = estado;
    this.usuario = usuario;
    this.actualizado = actualizado;
  }
  
  public EstadoEntidad() {}
  
  public Integer getCodigoEstado() {
    return this.codigoEstado;
  }
  
  public void setCodigoEstado(Integer codigoEstado) {
    this.codigoEstado = codigoEstado;
  }
  
  public String getNombreEstado() {
    return this.nombreEstado;
  }
  
  public void setNombreEstado(String nombreEstado) {
    this.nombreEstado = nombreEstado;
  }
  
  public String getEstado() {
    return this.estado;
  }
  
  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  public String getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }
  
  public String getActualizado() {
    return this.actualizado;
  }
  
  public void setActualizado(String actualizado) {
    this.actualizado = actualizado;
  }
}
