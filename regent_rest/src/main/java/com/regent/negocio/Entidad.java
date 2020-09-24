package com.regent.negocio;


import com.regent.negocio.EstadoEntidad;

import java.util.Date;
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
@Table(name = "entidades")
public class Entidad {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_entidades")
  @SequenceGenerator(allocationSize = 1, name = "sq_entidades", sequenceName = "sq_entidades")
  @Column(name = "codigo_entidad")
  private Integer codigoEntidad;
  
  @Column(name = "cuid", nullable = false, unique = true)
  private Integer CUID;
  
  @Column(name = "denominacion")
  private String denominacion;
  
  @ManyToOne
  @JoinColumn(name = "codigo_tipo_entidad")
  private TipoEntidad tipoEntidad;
  
  @ManyToOne
  @JoinColumn(name = "codigo_estado_entidad")
  private EstadoEntidad estadoEntidad;
  
  @Column(name = "solicitante")
  private String solicitante;
  
  @Column(name = "caracter_solicitante")
  private String caracterSolicitante;
  
  @Column(name = "instrumento_personeria")
  private String instrumentoPersoneria;
  
  @Column(name = "e_mail")
  private String email;
  
  @Column(name = "telefono")
  private String telefono;
  
  @ManyToOne
  @JoinColumn(name = "codigo_institucion_habilitante")
  private InstitucionHabilitante institucionHabilitante;
  
  @Column(name = "cuit")
  private Long cuit;
  
  @Column(name = "nro_reg_habilitante")
  private String nroRegHabilitante;
  
  @Column(name = "domicilio_legal")
  private String domicilioLegal;
  
  @Column(name = "localidad")
  private String localidad;
  
  @Column(name = "codigo_postal")
  private String codigoPostal;
  
  @Column(name = "domicilio_especial")
  private String domicilio_especial;
  
  @Column(name = "domicilio_comercial")
  private String domicilioComercial;
  
  @Column(name = "telefono_comercial")
  private String telefonoComercial;
  
  @Column(name = "paginaWeb")
  private String paginaWeb;
  
  @Column(name = "mes_cierre_de_balance")
  private Integer mesCierreDeBalance;
  
  @Column(name = "fecha_inicio_mandato")
  private Date fechaInicioMandato;
  
  @Column(name = "mandato_en_anios")
  private Integer mandatoEnAnios;
  
  @Column(name = "observaciones")
  private String observaciones;
  
  @Column(name = "vigente")
  private String vigente;
  
  @Column(name = "estado")
  private String estado;
  
  @Column(name = "usuario")
  private String usuario;
  
  @Column(name = "actualizado")
  private String actualizado;
  
  public Entidad(Integer codigoEntidad, String denominacion, TipoEntidad tipoEntidad, EstadoEntidad estadoEntidad, String solicitante, String caracterSolicitante, String instrumentoPersoneria, String eMail, String telefono, InstitucionHabilitante institucionHabilitante, Long cuit, String nroRegHabilitante, String domicilioLegal, String localidad, String codigoPostal, String domicilio_especial, String domicilioComercial, String telefonoComercial, String paginaWeb, Integer mesCierreDeBalance, Date fechaInicioMandato, Integer mandatoEnAnios, String observaciones, String vigente, String estado, String usuario, String actualizado) {
    this.codigoEntidad = codigoEntidad;
    this.denominacion = denominacion;
    this.tipoEntidad = tipoEntidad;
    this.estadoEntidad = estadoEntidad;
    this.solicitante = solicitante;
    this.caracterSolicitante = caracterSolicitante;
    this.instrumentoPersoneria = instrumentoPersoneria;
    this.email = eMail;
    this.telefono = telefono;
    this.institucionHabilitante = institucionHabilitante;
    this.cuit = cuit;
    this.nroRegHabilitante = nroRegHabilitante;
    this.domicilioLegal = domicilioLegal;
    this.localidad = localidad;
    this.codigoPostal = codigoPostal;
    this.domicilio_especial = domicilio_especial;
    this.domicilioComercial = domicilioComercial;
    this.telefonoComercial = telefonoComercial;
    this.paginaWeb = paginaWeb;
    this.mesCierreDeBalance = mesCierreDeBalance;
    this.fechaInicioMandato = fechaInicioMandato;
    this.mandatoEnAnios = mandatoEnAnios;
    this.observaciones = observaciones;
    this.vigente = vigente;
    this.estado = estado;
    this.usuario = usuario;
    this.actualizado = actualizado;
  }
  
  public Entidad() {}
  
  public Integer getCodigoEntidad() {
    return this.codigoEntidad;
  }
  
  public void setCodigoEntidad(Integer codigoEntidad) {
    this.codigoEntidad = codigoEntidad;
  }
  
  public String getDenominacion() {
    return this.denominacion;
  }
  
  public void setDenominacion(String denominacion) {
    this.denominacion = denominacion;
  }
  
  public TipoEntidad getTipoEntidad() {
    return this.tipoEntidad;
  }
  
  public void setTipoEntidad(TipoEntidad tipoEntidad) {
    this.tipoEntidad = tipoEntidad;
  }
  
  public EstadoEntidad getEstadoEntidad() {
    return this.estadoEntidad;
  }
  
  public void setEstadoEntidad(EstadoEntidad estadoEntidad) {
    this.estadoEntidad = estadoEntidad;
  }
  
  public String getSolicitante() {
    return this.solicitante;
  }
  
  public void setSolicitante(String solicitante) {
    this.solicitante = solicitante;
  }
  
  public String getCaracterSolicitante() {
    return this.caracterSolicitante;
  }
  
  public void setCaracterSolicitante(String caracterSolicitante) {
    this.caracterSolicitante = caracterSolicitante;
  }
  
  public String getInstrumentoPersoneria() {
    return this.instrumentoPersoneria;
  }
  
  public void setInstrumentoPersoneria(String instrumentoPersoneria) {
    this.instrumentoPersoneria = instrumentoPersoneria;
  }
  
  public String geteMail() {
    return this.email;
  }
  
  public void seteMail(String eMail) {
    this.email = eMail;
  }
  
  public String getTelefono() {
    return this.telefono;
  }
  
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
  
  public InstitucionHabilitante getInstitucionHabilitante() {
    return this.institucionHabilitante;
  }
  
  public void setInstitucionHabilitante(InstitucionHabilitante institucionHabilitante) {
    this.institucionHabilitante = institucionHabilitante;
  }
  
  public Long getCuit() {
    return this.cuit;
  }
  
  public void setCuit(Long cuit) {
    this.cuit = cuit;
  }
  
  public String getNroRegHabilitante() {
    return this.nroRegHabilitante;
  }
  
  public void setNroRegHabilitante(String nroRegHabilitante) {
    this.nroRegHabilitante = nroRegHabilitante;
  }
  
  public String getDomicilioLegal() {
    return this.domicilioLegal;
  }
  
  public void setDomicilioLegal(String domicilioLegal) {
    this.domicilioLegal = domicilioLegal;
  }
  
  public String getLocalidad() {
    return this.localidad;
  }
  
  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }
  
  public String getCodigoPostal() {
    return this.codigoPostal;
  }
  
  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }
  
  public String getDomicilio_especial() {
    return this.domicilio_especial;
  }
  
  public void setDomicilio_especial(String domicilio_especial) {
    this.domicilio_especial = domicilio_especial;
  }
  
  public String getDomicilioComercial() {
    return this.domicilioComercial;
  }
  
  public void setDomicilioComercial(String domicilioComercial) {
    this.domicilioComercial = domicilioComercial;
  }
  
  public String getTelefonoComercial() {
    return this.telefonoComercial;
  }
  
  public void setTelefonoComercial(String telefonoComercial) {
    this.telefonoComercial = telefonoComercial;
  }
  
  public String getPaginaWeb() {
    return this.paginaWeb;
  }
  
  public void setPaginaWeb(String paginaWeb) {
    this.paginaWeb = paginaWeb;
  }
  
  public Integer getMesCierreDeBalance() {
    return this.mesCierreDeBalance;
  }
  
  public void setMesCierreDeBalance(Integer mesCierreDeBalance) {
    this.mesCierreDeBalance = mesCierreDeBalance;
  }
  
  public Date getFechaInicioMandato() {
    return this.fechaInicioMandato;
  }
  
  public void setFechaInicioMandato(Date fechaInicioMandato) {
    this.fechaInicioMandato = fechaInicioMandato;
  }
  
  public Integer getMandatoEnAnios() {
    return this.mandatoEnAnios;
  }
  
  public void setMandatoEnAnios(Integer mandatoEnAnios) {
    this.mandatoEnAnios = mandatoEnAnios;
  }
  
  public String getObservaciones() {
    return this.observaciones;
  }
  
  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }
  
  public String getVigente() {
    return this.vigente;
  }
  
  public void setVigente(String vigente) {
    this.vigente = vigente;
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
  
  public Integer getCUID1() {
    return this.CUID;
  }
  
  public void setCUID(Integer cUID) {
    this.CUID = cUID;
  }
}
