package com.regent.negocio;

import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 @Entity
 @Table(name = "tipo_descuento")
 public class TipoDescuento
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_descuento")
   @SequenceGenerator(allocationSize = 1, name = "sq_tipo_descuento", sequenceName = "sq_tipo_descuento")
   @Column(name = "codigo_tipo_descuento")
   private Integer codigoTipoDescuento;
   @Column(name = "nombre_tipo_descuento")
   private String nombreTipoDescuento;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public TipoDescuento(Integer codigoTipoDescuento, String nombreTipoDescuento, String estado, String usuario, String actualizado) {
     this.codigoTipoDescuento = codigoTipoDescuento;
     this.nombreTipoDescuento = nombreTipoDescuento;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public TipoDescuento() {}
 
   
   public Integer getCodigoTipoDescuento() {
     return this.codigoTipoDescuento;
   }
 
   
   public void setCodigoTipoDescuento(Integer codigoTipoDescuento) {
     this.codigoTipoDescuento = codigoTipoDescuento;
   }
 
   
   public String getNombreTipoDescuento() {
     return this.nombreTipoDescuento;
   }
 
   
   public void setNombreTipoDescuento(String nombreTipoDescuento) {
     this.nombreTipoDescuento = nombreTipoDescuento;
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


