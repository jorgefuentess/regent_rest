package com.regent.negocio;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_roles")
  @SequenceGenerator(allocationSize = 1, name = "sq_roles", sequenceName = "sq_roles")
  @Column(name = "codigo_rol")
  private Integer codigoRol;
  
  @Column(name = "nombre_rol")
  private String nombreRol;
  
  @Column(name = "estado")
  private String estado;
  
  @Column(name = "usuario")
  private String usuario;
  
  @Column(name = "actualizado")
  private String actualizado;
  
  public Rol(Integer codigoRol, String nombreRol, String estado, String usuario, String actualizado) {
    this.codigoRol = codigoRol;
    this.nombreRol = nombreRol;
    this.estado = estado;
    this.usuario = usuario;
    this.actualizado = actualizado;
  }
  
  public Rol() {}
  
  public Integer getCodigoRol() {
    return this.codigoRol;
  }
  
  public void setCodigoRol(Integer codigoRol) {
    this.codigoRol = codigoRol;
  }
  
  public String getNombreRol() {
    return this.nombreRol;
  }
  
  public void setNombreRol(String nombreRol) {
    this.nombreRol = nombreRol;
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
