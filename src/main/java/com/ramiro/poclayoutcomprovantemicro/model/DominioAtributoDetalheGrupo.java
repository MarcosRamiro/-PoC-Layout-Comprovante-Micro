package com.ramiro.poclayoutcomprovantemicro.model;

import java.io.Serializable;

public class DominioAtributoDetalheGrupo implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String tituloAtributo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTituloAtributo() {
		return tituloAtributo;
	}

	public void setTituloAtributo(String tituloAtributo) {
		this.tituloAtributo = tituloAtributo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
