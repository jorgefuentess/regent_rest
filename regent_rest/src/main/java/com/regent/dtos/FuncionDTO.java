package com.regent.dtos;

public class FuncionDTO {

	private String codigoFuncion;
	private String nombreFuncion;
	private String path;
	private String detalle;
	private String estado;

	public FuncionDTO(String codigoFuncion, String nombreFuncion, String path, String detalle, String estado) {
		this.codigoFuncion = codigoFuncion;
		this.nombreFuncion = nombreFuncion;
		this.path = path;
		this.detalle = detalle;
		this.estado = estado;
	}

	public FuncionDTO() {
	}

	public String getCodigoFuncion() {
		return this.codigoFuncion;
	}

	public void setCodigoFuncion(String codigoFuncion) {
		this.codigoFuncion = codigoFuncion;
	}

	public String getNombreFuncion() {
		return this.nombreFuncion;
	}

	public void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
