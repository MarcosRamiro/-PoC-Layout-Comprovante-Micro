package com.ramiro.poclayoutcomprovantemicro.dto;

import com.ramiro.poclayoutcomprovantemicro.service.ServiceBind;

public class DetalheGrupoTipoBlocoDto extends DetalheGrupoDto {

	private String tituloAtributo;
	private String valorAtributo;

	public String getTituloAtributo() {
		return tituloAtributo;
	}
	public void setTituloAtributo(String tituloAtributo) {
		this.tituloAtributo = tituloAtributo;
	}
	public String getValorAtributo() {
		return valorAtributo;
	}
	public void setValorAtributo(String valorAtributo) {
		this.valorAtributo = valorAtributo;
	}

	@Override
	public void tratarAtributos(ServiceBind serviceBind, String json) {
		this.setTituloAtributo(serviceBind.bind(this.getTituloAtributo(), json));
		this.setValorAtributo(serviceBind.bind(this.getValorAtributo(), json));
	}

}
