package com.regent.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuarios")
  @SequenceGenerator(allocationSize = 1, name = "sq_usuarios", sequenceName = "sq_usuarios")
  @Column(name = "codigo_usuario")
  private Integer codigoUsuario;
  
  @Column(name = "denominacion")
  private String denominacion;
  
  @Column(name = "correo_electronico")
  private String correoElectronico;
  
  @Column(name = "observaciones")
  private String observaciones;
  
  @ManyToOne
  @JoinColumn(name = "codigo_rol")
  private Rol rol;
  
  @Column(name = "nombre_usuario")
  private String nombreUsuario;
  
  @Column(name = "password")
  private String password;
  
  @Column(name = "estado")
  private String estado;
  
  @Column(name = "cambiaclave")
  private String cambiaClave;
  
  @Column(name = "usuario")
  private String usuario;
  
  @Column(name = "actualizado")
  private String actualizado;
  
  public Usuario(Integer codigoUsuario, String denominacion, String correoElectronico, String observaciones, Rol rol, String nombreUsuario, String password, String estado, String cambiaClave, String usuario, String actualizado) {
    this.codigoUsuario = codigoUsuario;
    this.denominacion = denominacion;
    this.correoElectronico = correoElectronico;
    this.observaciones = observaciones;
    this.rol = rol;
    this.nombreUsuario = nombreUsuario;
    this.password = password;
    this.estado = estado;
    this.cambiaClave = cambiaClave;
    this.usuario = usuario;
    this.actualizado = actualizado;
  }
  
  public Usuario() {}
  
  public Integer getCodigoUsuario() {
    return this.codigoUsuario;
  }
  
  public void setCodigoUsuario(Integer codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }
  
  public String getDenominacion() {
    return this.denominacion;
  }
  
  public void setDenominacion(String denominacion) {
    this.denominacion = denominacion;
  }
  
  public String getCorreoElectronico() {
    return this.correoElectronico;
  }
  
  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }
  
  public String getObservaciones() {
    return this.observaciones;
  }
  
  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }
  
  public Rol getRol() {
    return this.rol;
  }
  
  public void setRol(Rol rol) {
    this.rol = rol;
  }
  
  public String getNombreUsuario() {
    return this.nombreUsuario;
  }
  
  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getEstado() {
    return this.estado;
  }
  
  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  public String getCambiaClave() {
    return this.cambiaClave;
  }
  
  public void setCambiaClave(String cambiaClave) {
    this.cambiaClave = cambiaClave;
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

