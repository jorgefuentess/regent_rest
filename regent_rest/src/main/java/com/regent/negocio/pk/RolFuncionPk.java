package com.regent.negocio.pk;

import com.regent.negocio.Funcion;
 import com.regent.negocio.Rol;
 import java.io.Serializable;
 import javax.persistence.Embeddable;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 
 
 
 
 
 
 @Embeddable
 public class RolFuncionPk
   implements Serializable
 {
   private static final long serialVersionUID = 7732651004689734518L;
   @ManyToOne
   @JoinColumn(name = "codigo_rol")
   private Rol rol;
   @ManyToOne
   @JoinColumn(name = "codigo_funcion")
   private Funcion funcion;
   
   public RolFuncionPk() {}
   
   public RolFuncionPk(Rol rol, Funcion funcion) {
     this.rol = rol;
     this.funcion = funcion;
   }
   
   public Rol getRol() {
     return this.rol;
   }
   
   public void setRol(Rol rol) {
     this.rol = rol;
   }
   
   public Funcion getFuncion() {
     return this.funcion;
   }
   
   public void setFuncion(Funcion funcion) {
     this.funcion = funcion;
   }
 }
