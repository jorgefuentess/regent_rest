package com.regent.negocio.pk;

import com.regent.negocio.Intimacion;
 import com.regent.negocio.TipoPresentacion;
 import java.io.Serializable;
 import javax.persistence.Embeddable;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 
 
 
 
 
 
 
 @Embeddable
 public class PresentacionIntimacionPk
   implements Serializable
 {
   private static final long serialVersionUID = 1501347501070326946L;
   @ManyToOne
   @JoinColumn(name = "codigo_intimacion")
   private Intimacion intimacion;
   @ManyToOne
   @JoinColumn(name = "codigo_tipo_presentacion")
   private TipoPresentacion tipoPresentacion;
   
   public PresentacionIntimacionPk() {}
   
   public PresentacionIntimacionPk(Intimacion intimacion, TipoPresentacion tipoPresentacion) {
     this.intimacion = intimacion;
     this.tipoPresentacion = tipoPresentacion;
   }
 
   
   public Intimacion getIntimacion() {
     return this.intimacion;
   }
 
   
   public void setIntimacion(Intimacion intimacion) {
     this.intimacion = intimacion;
   }
 
   
   public TipoPresentacion getTipoPresentacion() {
     return this.tipoPresentacion;
   }
 
   
   public void setTipoPresentacion(TipoPresentacion tipoPresentacion) {
     this.tipoPresentacion = tipoPresentacion;
   }
 }
