package com.regent.negocio.pk;

import com.regent.negocio.SolicitudEntidad;
 import com.regent.negocio.TipoDescuento;
 import java.io.Serializable;
 import javax.persistence.Embeddable;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 
 @Embeddable
 public class TipoDescuentoSolicitudPk
   implements Serializable
 {
   private static final long serialVersionUID = 76777126122114643L;
   @ManyToOne
   @JoinColumn(name = "codigo_solicitud")
   private SolicitudEntidad solicitud;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_descuento")
   private TipoDescuento tipoDescuento;
   
   public TipoDescuentoSolicitudPk() {}
   
   public TipoDescuentoSolicitudPk(SolicitudEntidad solicitud, TipoDescuento tipoDescuento) {
     this.solicitud = solicitud;
     this.tipoDescuento = tipoDescuento;
   }
 
 
   
   public SolicitudEntidad getSolicitud() {
     return this.solicitud;
   }
 
   
   public void setSolicitud(SolicitudEntidad solicitud) {
     this.solicitud = solicitud;
   }
 
   
   public TipoDescuento getTipoDescuento() {
     return this.tipoDescuento;
   }
 
   
   public void setTipoDescuento(TipoDescuento tipoDescuento) {
     this.tipoDescuento = tipoDescuento;
   }
 }
