package com.regent.dtos;

public class UsuarioDTO {

	private String codigoUsuario;
	   private String usuario;
	   private String clave;
	   private String cambiaClave;
	   private String rol;
	   private String denominacion;
	   private String correo;
	   private String observaciones;
	   private String estado;
	   private String nSaf;
	   private String orgExt;
	   private String codEntidad;
	   
	   public UsuarioDTO(String codigoUsuario, String usuario, String clave, String cambiaClave, String rol, String denominacion, String correo, String observaciones, String estado, String nSaf, String orgExt, String codEntidad) {
	this.codigoUsuario = codigoUsuario;
	this.usuario = usuario;
	this.clave = clave;
	this.cambiaClave = cambiaClave;
	this.rol = rol;
	this.denominacion = denominacion;
	this.correo = correo;
	this.observaciones = observaciones;
	this.estado = estado;
	this.nSaf = nSaf;
	this.orgExt = orgExt;
	this.codEntidad = codEntidad;
	   }
	 
	   
	   public UsuarioDTO() {}
	 
	   
	   public String getUsuario() {
	return this.usuario;
	   }
	   public void setUsuario(String usuario) {
	this.usuario = usuario;
	   }
	   public String getRol() {
	return this.rol;
	   }
	   public void setRol(String rol) {
	this.rol = rol;
	   }
	   public String getDenominacion() {
	return this.denominacion;
	   }
	   public void setDenominacion(String denominacion) {
	this.denominacion = denominacion;
	   }
	   public String getCorreo() {
	return this.correo;
	   }
	   public void setCorreo(String correo) {
	this.correo = correo;
	   }
	   public String getObservaciones() {
	return this.observaciones;
	   }
	   public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
	   }
	   public String getEstado() {
	return this.estado;
	   }
	   public void setEstado(String estado) {
	this.estado = estado;
	   }
	   public String getCodigoUsuario() {
	return this.codigoUsuario;
	   }
	   public void setCodigoUsuario(String codigoUsuario) {
	this.codigoUsuario = codigoUsuario;
	   }
	   public String getClave() {
	return this.clave;
	   }
	   public void setClave(String clave) {
	this.clave = clave;
	   }
	   public String getCambiaClave() {
	return this.cambiaClave;
	   }
	   public void setCambiaClave(String cambiaClave) {
	this.cambiaClave = cambiaClave;
	   }
	   public String getnSaf() {
	return this.nSaf;
	   }
	   public void setnSaf(String nSaf) {
	this.nSaf = nSaf;
	   }
	   public String getOrgExt() {
	return this.orgExt;
	   }
	   public void setOrgExt(String orgExt) {
	this.orgExt = orgExt;
	   }
	   public String getCodEntidad() {
	return this.codEntidad;
	   }
	   public void setCodEntidad(String codEntidad) {
	this.codEntidad = codEntidad;
	   }
}
