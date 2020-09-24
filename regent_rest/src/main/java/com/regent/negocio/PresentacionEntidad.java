package com.regent.negocio;


 import com.regent.negocio.Entidad;
 import com.regent.negocio.Intimacion;
 import com.regent.negocio.TipoPresentacion;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 
 
 
 
 
 
 
 
 @Entity
 @Table(name = "presentaciones_entidad")
 public class PresentacionEntidad
 {
   @Id
   @Column(name = "codigo_pres_ent")
   private Integer codigoPresEnt;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_presentacion")
   private TipoPresentacion tipoPresentacion;
   @ManyToOne
   @JoinColumn(name = "codigo_entidad")
   private Entidad entidad;
   @Column(name = "fecha_vencimiento")
   private Date fechaVencimiento;
   @Column(name = "intimada")
   private String intimada;
   @Column(name = "if_gedo")
   private String ifGedo;
   @ManyToOne
   @JoinColumn(name = "codigo_intimacion")
   private Intimacion intimacion;
   @Column(name = "nombre_archivo")
   private String nombreArchivo;
   @Column(name = "vigente")
   private String vigente;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public PresentacionEntidad(Integer codigoPresEnt, TipoPresentacion tipoPresentacion, Entidad entidad, Date fechaVencimiento, String intimada, String ifGedo, Intimacion intimacion, String nombreArchivo, String vigente, String usuario, String actualizado) {
     this.codigoPresEnt = codigoPresEnt;
     this.tipoPresentacion = tipoPresentacion;
     this.entidad = entidad;
     this.fechaVencimiento = fechaVencimiento;
     this.intimada = intimada;
     this.ifGedo = ifGedo;
     this.intimacion = intimacion;
     this.nombreArchivo = nombreArchivo;
     this.vigente = vigente;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public PresentacionEntidad() {}
 
   
   public Integer getCodigoPresEnt() {
     return this.codigoPresEnt;
   }
 
   
   public void setCodigoPresEnt(Integer codigoPresEnt) {
     this.codigoPresEnt = codigoPresEnt;
   }
 
   
   public TipoPresentacion getTipoPresentacion() {
     return this.tipoPresentacion;
   }
 
   
   public void setTipoPresentacion(TipoPresentacion tipoPresentacion) {
     this.tipoPresentacion = tipoPresentacion;
   }
 
   
   public Entidad getEntidad() {
     return this.entidad;
   }
 
   
   public void setEntidad(Entidad entidad) {
     this.entidad = entidad;
   }
 
   
   public Date getFechaVencimiento() {
     return this.fechaVencimiento;
   }
 
   
   public void setFechaVencimiento(Date fechaVencimiento) {
     this.fechaVencimiento = fechaVencimiento;
   }
 
   
   public String getIntimada() {
     return this.intimada;
   }
 
   
   public void setIntimada(String intimada) {
     this.intimada = intimada;
   }
 
   
   public String getIfGedo() {
     return this.ifGedo;
   }
 
   
   public void setIfGedo(String ifGedo) {
     this.ifGedo = ifGedo;
   }
 
   
   public Intimacion getIntimacion() {
     return this.intimacion;
   }
 
   
   public void setIntimacion(Intimacion intimacion) {
     this.intimacion = intimacion;
   }
 
   
   public String getNombreArchivo() {
     return this.nombreArchivo;
   }
   
   public void setNombreArchivo(String nombreArchivo) {
     this.nombreArchivo = nombreArchivo;
   }
   
   public String getVigente() {
     return this.vigente;
   }
   
   public void setVigente(String vigente) {
     this.vigente = vigente;
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