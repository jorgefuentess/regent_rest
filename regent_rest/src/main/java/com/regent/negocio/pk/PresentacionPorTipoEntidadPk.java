package com.regent.negocio.pk;


 import com.regent.negocio.TipoDescuento;
 import com.regent.negocio.TipoEntidad;
 import com.regent.negocio.TipoPresentacion;
 import java.io.Serializable;
 import javax.persistence.Embeddable;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 
 
 
 
 
 
 
 @Embeddable
 public class PresentacionPorTipoEntidadPk
   implements Serializable
 {
   private static final long serialVersionUID = 76777126122114643L;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_presentacion")
   private TipoPresentacion tipoPresentacion;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_entidad")
   private TipoEntidad tipoEntidad;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_descuento")
   private TipoDescuento tipoDescuento;
   
   public PresentacionPorTipoEntidadPk() {}
   
   public PresentacionPorTipoEntidadPk(TipoPresentacion tipoPresentacion, TipoEntidad tipoEntidad, TipoDescuento tipoDescuento) {
     this.tipoPresentacion = tipoPresentacion;
     this.tipoEntidad = tipoEntidad;
     this.tipoDescuento = tipoDescuento;
   }
 
   
   public TipoPresentacion getTipoPresentacion() {
     return this.tipoPresentacion;
   }
 
   
   public void setTipoPresentacion(TipoPresentacion tipoPresentacion) {
     this.tipoPresentacion = tipoPresentacion;
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
