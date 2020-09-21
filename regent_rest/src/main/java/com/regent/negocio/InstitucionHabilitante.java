package com.regent.negocio;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "institucion_habilitante")
public class InstitucionHabilitante {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_institucion_habilitante")
  @SequenceGenerator(allocationSize = 1, name = "sq_institucion_habilitante", sequenceName = "sq_institucion_habilitante")
  @Column(name = "codigo_institucion_habilitante")
  private Integer codigoInstitucionHabilitante;
  
  @Column(name = "nombre_institucion_habilitante")
  private String nombreInstitucionHabilitante;
  
  @Column(name = "estado")
  private String estado;
  
  @Column(name = "usuario")
  private String usuario;
  
  @Column(name = "actualizado")
  private String actualizado;
  
  public InstitucionHabilitante(Integer codigoInstitucionHabilitante, String nombreInstitucionHabilitante, String estado, String usuario, String actualizado) {
    this.codigoInstitucionHabilitante = codigoInstitucionHabilitante;
    this.nombreInstitucionHabilitante = nombreInstitucionHabilitante;
    this.estado = estado;
    this.usuario = usuario;
    this.actualizado = actualizado;
  }
  
  public InstitucionHabilitante() {}
  
  public Integer getCodigoInstitucionHabilitante() {
    return this.codigoInstitucionHabilitante;
  }
  
  public void setCodigoInstitucionHabilitante(Integer codigoInstitucionHabilitante) {
    this.codigoInstitucionHabilitante = codigoInstitucionHabilitante;
  }
  
  public String getNombreInstitucionHabilitante() {
    return this.nombreInstitucionHabilitante;
  }
  
  public void setNombreInstitucionHabilitante(String nombreInstitucionHabilitante) {
    this.nombreInstitucionHabilitante = nombreInstitucionHabilitante;
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
