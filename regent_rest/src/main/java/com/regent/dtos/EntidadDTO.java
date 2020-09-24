package com.regent.dtos;

import java.util.Collection;
 
 
 
 
 
 
 
 
 
 public class EntidadDTO
 {
   private String codigoEntidad;
   private String denominacion;
   private String codigo_tipo_entidad;
   private String estadoEntidad;
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
   private String domicilio_especial;
   private String domicilioComercial;
   private String telefonoComercial;
   private String paginaWeb;
   private String mesCierreDeBalance;
   private String fechaInicioMandato;
   private String mandatoEnAnios;
   private String observaciones;
   private String vigente;
   private Collection<String> codigosDescuento;
   
   public EntidadDTO(String codigoEntidad, String denominacion, String codigo_tipo_entidad, String estadoEntidad, String solicitante, String caracterSolicitante, String instrumentoPersoneria, String eMail, String telefono, String codigo_institucion_habilitante, String cuit, String nroRegHabilitante, String domicilioLegal, String domicilioComercial, String telefonoComercial, String paginaWeb, String localidad, String codigoPostal, String domicilio_especial, String mesCierreDeBalance, String fechaInicioMandato, String mandatoEnAnios, String observaciones, String vigente, Collection<String> codigosDescuento) {
     this.codigoEntidad = codigoEntidad;
     this.denominacion = denominacion;
     this.codigo_tipo_entidad = codigo_tipo_entidad;
     this.estadoEntidad = estadoEntidad;
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
     this.domicilio_especial = domicilio_especial;
     this.domicilioComercial = domicilioComercial;
     this.telefonoComercial = telefonoComercial;
     this.paginaWeb = paginaWeb;
     this.mesCierreDeBalance = mesCierreDeBalance;
     this.fechaInicioMandato = fechaInicioMandato;
     this.mandatoEnAnios = mandatoEnAnios;
     this.observaciones = observaciones;
     this.vigente = vigente;
     this.codigosDescuento = codigosDescuento;
   }
 
   
   public EntidadDTO() {}
 
   
   public String getCodigoEntidad() {
     return this.codigoEntidad;
   }
   
   public void setCodigoEntidad(String codigoEntidad) {
     this.codigoEntidad = codigoEntidad;
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
   
   public String getEstadoEntidad() {
     return this.estadoEntidad;
   }
   
   public void setEstadoEntidad(String estadoEntidad) {
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
   
   public String getMesCierreDeBalance() {
     return this.mesCierreDeBalance;
   }
   
   public void setMesCierreDeBalance(String mesCierreDeBalance) {
     this.mesCierreDeBalance = mesCierreDeBalance;
   }
   
   public String getFechaInicioMandato() {
     return this.fechaInicioMandato;
   }
   
   public void setFechaInicioMandato(String fechaInicioMandato) {
     this.fechaInicioMandato = fechaInicioMandato;
   }
   
   public String getMandatoEnAnios() {
     return this.mandatoEnAnios;
   }
   
   public void setMandatoEnAnios(String mandatoEnAnios) {
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
   
   public Collection<String> getCodigosDescuento() {
     return this.codigosDescuento;
   }
   
   public void setCodigosDescuento(Collection<String> codigosDescuento) {
     this.codigosDescuento = codigosDescuento;
   }
 }

