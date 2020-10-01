package com.regent.dtos;

import com.regent.dtos.TipoDescuentoSolicitudDTO;
 import java.util.Collection;
 
 
 
 
 
 
 
 
 
 public class SolicitudEntidadDTO
 {
   private String codigoSolicitud;
   private String denominacion;
   private String codigo_tipo_entidad;
   private String estadoSolicitud;
   private String solicitante;
   private String caracterSolicitante;
   private String instrumentoPersoneria;
   private String eMail;
   private String telefono;
   private String codigo_institucion_habilitante;
   private String cuit;
   private String nroRegHabilitante;
   private String domicilioLegal;
   private String localidad;
   private String codigoPostal;
   private String domicilioEspecial;
   private String domicilioComercial;
   private String telefonoComercial;
   private String paginaWeb;
   private String modificable;
   private String observaciones;
   private Collection<TipoDescuentoSolicitudDTO> tipoDescuentoSolicitud;
   private Collection<String[]> nombresArchivos;
   
   public SolicitudEntidadDTO() {}
   
   public SolicitudEntidadDTO(String codigoSolicitud, String denominacion, String codigo_tipo_entidad, String estadoSolicitud, String solicitante, String caracterSolicitante, String instrumentoPersoneria, String eMail, String telefono, String codigo_institucion_habilitante, String cuit, String nroRegHabilitante, String domicilioLegal, String localidad, String codigoPostal, String domicilioEspecial, String domicilioComercial, String telefonoComercial, String paginaWeb, String modificable, String observaciones, Collection<TipoDescuentoSolicitudDTO> tipoDescuentoSolicitud, Collection<String[]> nombresArchivos) {
     this.codigoSolicitud = codigoSolicitud;
     this.denominacion = denominacion;
     this.codigo_tipo_entidad = codigo_tipo_entidad;
     this.estadoSolicitud = estadoSolicitud;
     this.solicitante = solicitante;
     this.caracterSolicitante = caracterSolicitante;
     this.instrumentoPersoneria = instrumentoPersoneria;
     this.eMail = eMail;
     this.telefono = telefono;
     this.codigo_institucion_habilitante = codigo_institucion_habilitante;
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
     this.tipoDescuentoSolicitud = tipoDescuentoSolicitud;
     this.nombresArchivos = nombresArchivos;
   }
   
   public String getCodigoSolicitud() {
     return this.codigoSolicitud;
   }
   
   public void setCodigoSolicitud(String codigoSolicitud) {
     this.codigoSolicitud = codigoSolicitud;
   }
   
   public String getDenominacion() {
     return this.denominacion;
   }
   
   public void setDenominacion(String denominacion) {
     this.denominacion = denominacion;
   }
   
   public String getCodigo_tipo_entidad() {
     return this.codigo_tipo_entidad;
   }
   
   public void setCodigo_tipo_entidad(String codigo_tipo_entidad) {
     this.codigo_tipo_entidad = codigo_tipo_entidad;
   }
   
   public String getEstadoSolicitud() {
     return this.estadoSolicitud;
   }
   
   public void setEstadoSolicitud(String estadoSolicitud) {
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
   
   public String getCodigo_institucion_habilitante() {
     return this.codigo_institucion_habilitante;
   }
   
   public void setCodigo_institucion_habilitante(String codigo_institucion_habilitante) {
     this.codigo_institucion_habilitante = codigo_institucion_habilitante;
   }
   
   public String getCuit() {
     return this.cuit;
   }
   
   public void setCuit(String cuit) {
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
   
   public String getObservaciones() {
     return this.observaciones;
   }
   
   public void setObservaciones(String observaciones) {
     this.observaciones = observaciones;
   }
   
   public Collection<TipoDescuentoSolicitudDTO> getTipoDescuentoSolicitud() {
     return this.tipoDescuentoSolicitud;
   }
   
   public void setTipoDescuentoSolicitud(Collection<TipoDescuentoSolicitudDTO> tipoDescuentoSolicitud) {
     this.tipoDescuentoSolicitud = tipoDescuentoSolicitud;
   }
   
   public Collection<String[]> getNombresArchivos() {
     return this.nombresArchivos;
   }
   
   public void setNombresArchivos(Collection<String[]> nombresArchivos) {
     this.nombresArchivos = nombresArchivos;
   }
 }