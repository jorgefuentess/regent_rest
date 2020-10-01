package com.regent.negocio;


 import com.regent.negocio.CodigoDescuento;
 import com.regent.negocio.Saf;
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 
 
 
 
 @Entity
 @Table(name = "datos_saf")
 public class DatosSaf
 {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_datos_saf")
   @SequenceGenerator(allocationSize = 1, name = "sq_datos_saf", sequenceName = "sq_datos_saf")
   @Column(name = "codigo_dato_saf")
   private Integer codigoDatoSaf;
   @ManyToOne
   @JoinColumn(name = "n_saf")
   private Saf saf;
   @Column(name = "mes")
   private Integer mes;
   @Column(name = "anio")
   private Integer anio;
   @Column(name = "cuil")
   private Long cuil;
   @ManyToOne
   @JoinColumn(name = "codigo_descuento")
   private CodigoDescuento codigoDescuento;
   @Column(name = "nro_certificado")
   private String nroCertificado;
   @Column(name = "fecha_emision")
   private Date fechaEmision;
   @Column(name = "tasa")
   private Double tasa;
   @Column(name = "remuneracion")
   private Double remuneracion;
   @Column(name = "porcentaje")
   private Double porcentaje;
   @Column(name = "comienzo_afectacion")
   private Date comienzoAfectacion;
   @Column(name = "monto_total")
   private Double montoTotal;
   @Column(name = "monto_cuota")
   private Double montoCuota;
   @Column(name = "cant_cuotas")
   private Integer cantCuotas;
   @Column(name = "nro_cuota")
   private Integer nroCuota;
   @Column(name = "fecha_fin")
   private Date fechaFin;
   @Column(name = "usuario")
   private String usuario;
   @Column(name = "actualizado")
   private String actualizado;
   
   public DatosSaf(Integer codigoDatoSaf, Saf saf, Integer mes, Integer anio, Long cuil, CodigoDescuento codigoDescuento, String nroCertificado, Date fechaEmision, Double tasa, Double remuneracion, Double porcentaje, Date comienzoAfectacion, Double montoTotal, Double montoCuota, Integer cantCuotas, Integer nroCuota, Date fechaFin, String usuario, String actualizado) {
     this.codigoDatoSaf = codigoDatoSaf;
     this.saf = saf;
     this.mes = mes;
     this.anio = anio;
     this.cuil = cuil;
     this.codigoDescuento = codigoDescuento;
     this.nroCertificado = nroCertificado;
     this.fechaEmision = fechaEmision;
     this.tasa = tasa;
     this.remuneracion = remuneracion;
     this.porcentaje = porcentaje;
     this.comienzoAfectacion = comienzoAfectacion;
     this.montoTotal = montoTotal;
     this.montoCuota = montoCuota;
     this.cantCuotas = cantCuotas;
     this.nroCuota = nroCuota;
     this.fechaFin = fechaFin;
     this.usuario = usuario;
     this.actualizado = actualizado;
   }
 
   
   public DatosSaf() {}
 
   
   public Integer getCodigoDatoSaf() {
     return this.codigoDatoSaf;
   }
 
   
   public void setCodigoDatoSaf(Integer codigoDatoSaf) {
     this.codigoDatoSaf = codigoDatoSaf;
   }
 
   
   public Saf getSaf() {
     return this.saf;
   }
 
   
   public void setSaf(Saf saf) {
     this.saf = saf;
   }
 
   
   public Integer getMes() {
     return this.mes;
   }
 
   
   public void setMes(Integer mes) {
     this.mes = mes;
   }
 
   
   public Integer getAnio() {
     return this.anio;
   }
 
   
   public void setAnio(Integer anio) {
     this.anio = anio;
   }
 
   
   public Long getCuil() {
     return this.cuil;
   }
 
   
   public void setCuil(Long cuil) {
     this.cuil = cuil;
   }
 
   
   public CodigoDescuento getCodigoDescuento() {
     return this.codigoDescuento;
   }
 
   
   public void setCodigoDescuento(CodigoDescuento codigoDescuento) {
     this.codigoDescuento = codigoDescuento;
   }
 
   
   public String getNroCertificado() {
     return this.nroCertificado;
   }
 
   
   public void setNroCertificado(String nroCertificado) {
     this.nroCertificado = nroCertificado;
   }
 
   
   public Date getFechaEmision() {
     return this.fechaEmision;
   }
 
   
   public void setFechaEmision(Date fechaEmision) {
     this.fechaEmision = fechaEmision;
   }
 
   
   public Double getTasa() {
     return this.tasa;
   }
 
   
   public void setTasa(Double tasa) {
     this.tasa = tasa;
   }
 
   
   public Double getRemuneracion() {
     return this.remuneracion;
   }
 
   
   public void setRemuneracion(Double remuneracion) {
     this.remuneracion = remuneracion;
   }
 
   
   public Double getPorcentaje() {
     return this.porcentaje;
   }
 
   
   public void setPorcentaje(Double porcentaje) {
     this.porcentaje = porcentaje;
   }
 
   
   public Date getComienzoAfectacion() {
     return this.comienzoAfectacion;
   }
 
   
   public void setComienzoAfectacion(Date comienzoAfectacion) {
     this.comienzoAfectacion = comienzoAfectacion;
   }
 
   
   public Double getMontoTotal() {
     return this.montoTotal;
   }
 
   
   public void setMontoTotal(Double montoTotal) {
     this.montoTotal = montoTotal;
   }
 
   
   public Double getMontoCuota() {
     return this.montoCuota;
   }
 
   
   public void setMontoCuota(Double montoCuota) {
     this.montoCuota = montoCuota;
   }
 
   
   public Integer getCantCuotas() {
     return this.cantCuotas;
   }
 
   
   public void setCantCuotas(Integer cantCuotas) {
     this.cantCuotas = cantCuotas;
   }
 
   
   public Integer getNroCuota() {
     return this.nroCuota;
   }
 
   
   public void setNroCuota(Integer nroCuota) {
     this.nroCuota = nroCuota;
   }
 
   
   public Date getFechaFin() {
     return this.fechaFin;
   }
 
   
   public void setFechaFin(Date fechaFin) {
     this.fechaFin = fechaFin;
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