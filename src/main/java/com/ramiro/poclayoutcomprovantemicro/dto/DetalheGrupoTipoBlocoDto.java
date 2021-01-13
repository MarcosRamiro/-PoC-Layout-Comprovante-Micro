package com.ramiro.poclayoutcomprovantemicro.dto;

import java.util.function.Function;

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
	public void tratarAtributos(Function<String, String> funcao) {
		this.setTituloAtributo(funcao.apply(this.getTituloAtributo()));
		this.setValorAtributo(funcao.apply(this.getValorAtributo()));
	}
}
