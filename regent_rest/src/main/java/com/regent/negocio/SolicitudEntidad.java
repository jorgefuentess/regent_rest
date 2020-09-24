package com.regent.negocio;


 import com.regent.negocio.EstadoSolicitud;
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
 @Table(name = "solicitud_entidad")
 public class SolicitudEntidad
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_solicitud")
   @SequenceGenerator(allocationSize = 1, name = "sq_solicitud", sequenceName = "sq_solicitud")
   @Column(name = "codigo_solicitud")
   private Integer codigoSolicitud;
   @Column(name = "denominacion")
   private String denominacion;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_entidad")
   private TipoEntidad tipoEntidad;
   @ManyToOne
   @JoinColumn(name = "codigo_estado_solicitud")
   private EstadoSolicitud estadoSolicitud;
   @Column(name = "solicitante")
   private String solicitante;
   @Column(name = "caracter_solicitante")
   private String caracterSolicitante;
   @Column(name = "instrumento_personeria")
   private String instrumentoPersoneria;
   @Column(name = "e_mail")
   private String eMail;
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
   private String domicilioEspecial;
   @Column(name = "domicilio_comercial")
   private String domicilioComercial;
   @Column(name = "telefono_comercial")
   private String telefonoComercial;
   @Column(name = "pagina_web")
   private String paginaWeb;
   @Column(name = "modificable")
   private String modificable;
   @Column(name = "observaciones")
   private String observaciones;
   @Column(name = "resolucion")
   private String resolucion;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public SolicitudEntidad() {}
   
   public SolicitudEntidad(Integer codigoSolicitud, String denominacion, TipoEntidad tipoEntidad, EstadoSolicitud estadoSolicitud, String solicitante, String caracterSolicitante, String instrumentoPersoneria, String eMail, String telefono, InstitucionHabilitante institucionHabilitante, Long cuit, String nroRegHabilitante, String domicilioLegal, String localidad, String codigoPostal, String domicilioEspecial, String domicilioComercial, String telefonoComercial, String paginaWeb, String modificable, String observaciones, String estado, String usuario, String actualizado) {
     this.codigoSolicitud = codigoSolicitud;
     this.denominacion = denominacion;
     this.tipoEntidad = tipoEntidad;
     this.estadoSolicitud = estadoSolicitud;
     this.solicitante = solicitante;
     this.caracterSolicitante = caracterSolicitante;
     this.instrumentoPersoneria = instrumentoPersoneria;
     this.eMail = eMail;
     this.telefono = telefono;
     this.institucionHabilitante = institucionHabilitante;
     this.cuit = cuit;
     this.nroRegHabilitante = nroRegHabilitante;
     this.domicilioLegal = domicilioLegal;
     this.localidad = localidad;
     this.codigoPostal = codigoPostal;
     this.domicilioEspecial = domicilioEspecial;
     this.domicilioComercial = domicilioComercial;
     this.telefonoComercial = telefonoComercial;
     this.paginaWeb = paginaWeb;
     this.modificable = modificable;
     this.observaciones = observaciones;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public Integer getCodigoSolicitud() {
     return this.codigoSolicitud;
   }
   
   public void setCodigoSolicitud(Integer codigoSolicitud) {
     this.codigoSolicitud = codigoSolicitud;
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
   
   public EstadoSolicitud getEstadoSolicitud() {
     return this.estadoSolicitud;
   }
   
   public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
     this.estadoSolicitud = estadoSolicitud;
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
     return this.eMail;
   }
   
   public void seteMail(String eMail) {
     this.eMail = eMail;
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
   
   public String getDomicilioEspecial() {
     return this.domicilioEspecial;
   }
   
   public void setDomicilioEspecial(String domicilioEspecial) {
     this.domicilioEspecial = domicilioEspecial;
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
   
   public String getModificable() {
     return this.modificable;
   }
   
   public void setModificable(String modificable) {
     this.modificable = modificable;
   }
   
   public String getEstado() {
     return this.estado;
   }
   
   public String getObservaciones() {
     return this.observaciones;
   }
   
   public void setObservaciones(String observaciones) {
     this.observaciones = observaciones;
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
 
   
   public String getResolucion() {
     return this.resolucion;
   }
   
   public void setResolucion(String resolucion) {
     this.resolucion = resolucion;
   }
 }