package com.regent.dtos;


 import java.util.Collection;
 
 
 
 
 public class EntidadInfoDTO
 {
   private String codigoEntidad;
   private String denominacion;
   private String tipo_entidad;
   private String estado;
   private String telefono;
   private String cuit;
   private String localidad;
   private String codigoPostal;
   private String domicilio_especial;
   private String paginaWeb;
   private Collection<String> codigosDescuento;
   private String resolucionBaja;
   private String fechaSuspension;
   
   public EntidadInfoDTO() {}
   
   public EntidadInfoDTO(String codigoEntidad, String denominacion, String tipo_entidad, String estado, String telefono, String cuit, String paginaWeb, String localidad, String codigoPostal, String domicilio_especial, Collection<String> codigosDescuento) {
     this.codigoEntidad = codigoEntidad;
     this.denominacion = denominacion;
     this.tipo_entidad = tipo_entidad;
     this.estado = estado;
     this.telefono = telefono;
     this.cuit = cuit;
     this.paginaWeb = paginaWeb;
     this.localidad = localidad;
     this.codigoPostal = codigoPostal;
     this.domicilio_especial = domicilio_especial;
     this.codigosDescuento = codigosDescuento;
   }
 
   
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
 
   
   public String getTipo_entidad() {
     return this.tipo_entidad;
   }
 
   
   public void setTipo_entidad(String tipo_entidad) {
     this.tipo_entidad = tipo_entidad;
   }
 
   
   public String getEstado() {
     return this.estado;
   }
   
   public void setEstado(String estado) {
     this.estado = estado;
   }
   
   public String getTelefono() {
     return this.telefono;
   }
 
   
   public void setTelefono(String telefono) {
     this.telefono = telefono;
   }
 
   
   public String getCuit() {
     return this.cuit;
   }
 
   
   public void setCuit(String cuit) {
     this.cuit = cuit;
   }
 
   
   public String getPaginaWeb() {
     return this.paginaWeb;
   }
   
   public void setPaginaWeb(String paginaWeb) {
     this.paginaWeb = paginaWeb;
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
 
   
   public Collection<String> getCodigosDescuento() {
     return this.codigosDescuento;
   }
 
   
   public void setCodigosDescuento(Collection<String> codigosDescuento) {
     this.codigosDescuento = codigosDescuento;
   }
   
   public String getResolucionBaja() {
     return this.resolucionBaja;
   }
   
   public void setResolucionBaja(String resolucionBaja) {
     this.resolucionBaja = resolucionBaja;
   }
   
   public String getFechaSuspension() {
     return this.fechaSuspension;
   }
   
   public void setFechaSuspension(String fechaSuspension) {
     this.fechaSuspension = fechaSuspension;
   }
 }
