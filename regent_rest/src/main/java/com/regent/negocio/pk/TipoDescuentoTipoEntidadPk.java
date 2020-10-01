package com.regent.negocio.pk;

import com.regent.negocio.TipoDescuento;
 import com.regent.negocio.TipoEntidad;
 import java.io.Serializable;
 import javax.persistence.Embeddable;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 
 
 
 
 
 
 
 @Embeddable
 public class TipoDescuentoTipoEntidadPk
   implements Serializable
 {
   private static final long serialVersionUID = 76777126122114643L;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_entidad")
   private TipoEntidad tipoEntidad;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_descuento")
   private TipoDescuento tipoDescuento;
   
   public TipoDescuentoTipoEntidadPk() {}
   
   public TipoDescuentoTipoEntidadPk(TipoEntidad tipoEntidad, TipoDescuento tipoDescuento) {
     this.tipoEntidad = tipoEntidad;
     this.tipoDescuento = tipoDescuento;
   }
 
   
   public TipoEntidad getTipoEntidad() {
     return this.tipoEntidad;
   }
 
   
   public void setTipoEntidad(TipoEntidad tipoEntidad) {
     this.tipoEntidad = tipoEntidad;
   }
 
   
   public TipoDescuento getTipoDescuento() {
     return this.tipoDescuento;
   }
 
   
   public void setTipoDescuento(TipoDescuento tipoDescuento) {
     this.tipoDescuento = tipoDescuento;
   }
 }
