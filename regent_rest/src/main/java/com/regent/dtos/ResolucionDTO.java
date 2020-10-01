package com.regent.dtos;

import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
import java.util.Collection;

public class ResolucionDTO {
	private String codigoResolucion;
	private String codigo_entidad;
	private String estadoEntidad;
	private String nroResolucion;
	private String expJgm;
	private String expMmod;
	private String linkBoletin;
	private String fechaVigencia;
	private Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidad;

	public ResolucionDTO(String codigoResolucion, String codigo_entidad, String estadoEntidad, String nroResolucion,
			String expJgm, String expMmod, String linkBoletin, String fechaVigencia,
			Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidad) {
		this.codigoResolucion = codigoResolucion;
		this.codigo_entidad = codigo_entidad;
		this.estadoEntidad = estadoEntidad;
		this.nroResolucion = nroResolucion;
		this.expJgm = expJgm;
		this.expMmod = expMmod;
		this.linkBoletin = linkBoletin;
		this.fechaVigencia = fechaVigencia;
		this.tipoDescuentoTipoEntidad = tipoDescuentoTipoEntidad;
	}

	public ResolucionDTO() {
	}

	public String getCodigoResolucion() {
		return this.codigoResolucion;
	}

	public void setCodigoResolucion(String codigoResolucion) {
		this.codigoResolucion = codigoResolucion;
	}

	public String getCodigo_entidad() {
		return this.codigo_entidad;
	}

	public void setCodigo_entidad(String codigo_entidad) {
		this.codigo_entidad = codigo_entidad;
	}

	public String getEstadoEntidad() {
		return this.estadoEntidad;
	}

	public void setEstadoEntidad(String estadoEntidad) {
		this.estadoEntidad = estadoEntidad;
	}

	public String getNroResolucion() {
		return this.nroResolucion;
	}

	public void setNroResolucion(String nroResolucion) {
		this.nroResolucion = nroResolucion;
	}

	public String getExpJgm() {
		return this.expJgm;
	}

	public void setExpJgm(String expJgm) {
		this.expJgm = expJgm;
	}

	public String getExpMmod() {
		return this.expMmod;
	}

	public void setExpMmod(String expMmod) {
		this.expMmod = expMmod;
	}

	public String getLinkBoletin() {
		return this.linkBoletin;
	}

	public void setLinkBoletin(String linkBoletin) {
		this.linkBoletin = linkBoletin;
	}

	public String getFechaVigencia() {
		return this.fechaVigencia;
	}

	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public Collection<TipoDescuentoTipoEntidadDTO> getTipoDescuentoTipoEntidad() {
		return this.tipoDescuentoTipoEntidad;
	}

	public void setTipoDescuentoTipoEntidad(Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidad) {
		this.tipoDescuentoTipoEntidad = tipoDescuentoTipoEntidad;
	}
}
