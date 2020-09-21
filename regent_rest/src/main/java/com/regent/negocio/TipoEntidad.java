package com.regent.negocio;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_entidades")
public class TipoEntidad {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_entidad")
  @SequenceGenerator(allocationSize = 1, name = "sq_tipo_entidad", sequenceName = "sq_tipo_entidad")
  @Column(name = "codigo_tipo_entidad")
  private Integer codigoTipoEntidad;
  
  @Column(name = "nombre_tipo_entidad")
  private String nombreTipoEntidad;
  
  @Column(name = "estado")
  private String estado;
  
  @Column(name = "usuario")
  private String usuario;
  
  @Column(name = "actualizado")
  private String actualizado;
  
  public TipoEntidad(Integer codigoTipoEntidad, String nombreTipoEntidad, String estado, String usuario, String actualizado) {
    this.codigoTipoEntidad = codigoTipoEntidad;
    this.nombreTipoEntidad = nombreTipoEntidad;
    this.estado = estado;
    this.usuario = usuario;
    this.actualizado = actualizado;
  }
  
  public TipoEntidad() {}
  
  public Integer getCodigoTipoEntidad() {
    return this.codigoTipoEntidad;
  }
  
  public void setCodigoTipoEntidad(Integer codigoTipoEntidad) {
    this.codigoTipoEntidad = codigoTipoEntidad;
  }
  
  public String getNombreTipoEntidad() {
    return this.nombreTipoEntidad;
  }
  
  public void setNombreTipoEntidad(String nombreTipoEntidad) {
    this.nombreTipoEntidad = nombreTipoEntidad;
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
