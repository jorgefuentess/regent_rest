package com.regent.negocio;


 import com.regent.negocio.Resolucion;
 import com.regent.negocio.TipoDescuento;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 
 @Entity
 @Table(name = "codigo_descuento")
 public class CodigoDescuento
 {
   @Id
   @Column(name = "codigo_descuento")
   private Integer codigoDescuento;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_descuento")
   private TipoDescuento tipoDescuento;
   @ManyToOne
   @JoinColumn(name = "codigo_resolucion")
   private Resolucion resolucion;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public CodigoDescuento(Integer codigoDescuento, TipoDescuento tipoDescuento, Resolucion resolucion, String estado, String usuario, String actualizado) {
     this.codigoDescuento = codigoDescuento;
     this.tipoDescuento = tipoDescuento;
     this.resolucion = resolucion;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
 
   
   public CodigoDescuento() {}
 
   
   public Integer getCodigoDescuento() {
     return this.codigoDescuento;
   }
 
   
   public void setCodigoDescuento(Integer codigoDescuento) {
     this.codigoDescuento = codigoDescuento;
   }
 
   
   public TipoDescuento getTipoDescuento() {
     return this.tipoDescuento;
   }
 
   
   public void setTipoDescuento(TipoDescuento tipoDescuento) {
     this.tipoDescuento = tipoDescuento;
   }
 
   
   public Resolucion getResolucion() {
     return this.resolucion;
   }
 
   
   public void setResolucion(Resolucion resolucion) {
     this.resolucion = resolucion;
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