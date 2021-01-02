package com.ramiro.poclayoutcomprovantemicro.model;

import io.vertx.reactivex.sqlclient.Row;

import java.io.Serializable;

public class DetalheGrupoConteudo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long detalheGrupoConteudoId;
	private Long detalheGrupoId;
	private String conteudo;
	private String nomeAtributo;
	private Long dominioAtributoDetalheGrupoId;

	public static DetalheGrupoConteudo of(Row row) {
		DetalheGrupoConteudo detalheGrupoConteudo = new DetalheGrupoConteudo();
		detalheGrupoConteudo.setDetalheGrupoConteudoId(row.getLong(0));
		detalheGrupoConteudo.setDetalheGrupoId(row.getLong(1));
		detalheGrupoConteudo.setDominioAtributoDetalheGrupoId (row.getLong(2));
		detalheGrupoConteudo.setConteudo(row.getString(3));
		detalheGrupoConteudo.setNomeAtributo(row.getString(4));
		return detalheGrupoConteudo;
	}

	public Long getDominioAtributoDetalheGrupoId() {
		return dominioAtributoDetalheGrupoId;
	}

	public void setDominioAtributoDetalheGrupoId(Long dominioAtributoDetalheGrupoId) {
		this.dominioAtributoDetalheGrupoId = dominioAtributoDetalheGrupoId;
	}

	public Long getDetalheGrupoId() {
		return detalheGrupoId;
	}

	public void setDetalheGrupoId(Long detalheGrupoId) {
		this.detalheGrupoId = detalheGrupoId;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public Long getDetalheGrupoConteudoId() {
		return detalheGrupoConteudoId;
	}

	public void setDetalheGrupoConteudoId(Long detalheGrupoConteudoId) {
		this.detalheGrupoConteudoId = detalheGrupoConteudoId;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
