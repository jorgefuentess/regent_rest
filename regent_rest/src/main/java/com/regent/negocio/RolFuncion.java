package com.regent.negocio;


 import com.regent.negocio.pk.RolFuncionPk;
 import javax.persistence.Column;
 import javax.persistence.EmbeddedId;
 import javax.persistence.Entity;
 import javax.persistence.Table;
 
 
 
 
 @Entity
 @Table(name = "rol_funcion")
 public class RolFuncion
 {
   @EmbeddedId
   private RolFuncionPk rolFuncionPk;
   @Column(name = "orden")
   private Integer orden;
   @Column(name = "estado")
   private String estado;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public RolFuncion(RolFuncionPk rolFuncionPk, Integer orden, String estado, String usuario, String actualizado) {
     this.rolFuncionPk = rolFuncionPk;
     this.orden = orden;
     this.estado = estado;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public RolFuncion() {}
 
   
   public RolFuncionPk getRolFuncionPk() {
     return this.rolFuncionPk;
   }
 
   
   public void setRolFuncionPk(RolFuncionPk rolFuncionPk) {
     this.rolFuncionPk = rolFuncionPk;
   }
 
   
   public Integer getOrden() {
     return this.orden;
   }
 
   
   public void setOrden(Integer orden) {
     this.orden = orden;
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
