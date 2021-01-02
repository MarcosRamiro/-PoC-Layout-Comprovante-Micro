package com.ramiro.poclayoutcomprovantemicro.model;

import io.vertx.reactivex.sqlclient.Row;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public class DetalheGrupo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long detalheGrupoId;
	private Long grupoId;
	private List<DetalheGrupoConteudo> detalheGrupoConteudo;
	private boolean visibilidade;
	private int ordenacao;

    public static DetalheGrupo of(Row row) {
		DetalheGrupo detalheGrupo = new DetalheGrupo();
		detalheGrupo.setDetalheGrupoId(row.getLong("detalhe_grupo_id"));
		detalheGrupo.setGrupoId(row.getLong("grupo_id"));
		detalheGrupo.setOrdenacao(row.getInteger("ordenacao"));
		detalheGrupo.setVisibilidade(row.getBoolean("visibilidade"));
		return detalheGrupo;
    }

	public Long getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Long grupoId) {
		this.grupoId = grupoId;
	}

    public List<DetalheGrupoConteudo> getDetalheGrupoConteudo() {
		return detalheGrupoConteudo;
	}
	public void setDetalheGrupoConteudo(List<DetalheGrupoConteudo> detalheGrupoConteudo) {
		this.detalheGrupoConteudo = detalheGrupoConteudo;
	}
	public Long getDetalheGrupoId() {
		return detalheGrupoId;
	}
	public void setDetalheGrupoId(Long detalheGrupoId) {
		this.detalheGrupoId = detalheGrupoId;
	}
	public boolean isVisibilidade() {
		return visibilidade;
	}
	public void setVisibilidade(boolean visibilidade) {
		this.visibilidade = visibilidade;
	}
	public int getOrdenacao() {
		return ordenacao;
	}
	public void setOrdenacao(int ordenacao) {
		this.ordenacao = ordenacao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String obterConteudoDoAtributo(String atributo) {

		Optional<DetalheGrupoConteudo> findFirst = detalheGrupoConteudo.stream()
				.filter(c -> c.getNomeAtributo().equals(atributo)).findFirst();
		return findFirst.isPresent() ? findFirst.get().getConteudo() : "";

	}

	@Override
	public String toString() {
		return "DetalheGrupo{" +
				"detalheGrupoId=" + detalheGrupoId +
				", grupoId=" + grupoId +
				", detalheGrupoConteudo=" + detalheGrupoConteudo +
				", visibilidade=" + visibilidade +
				", ordenacao=" + ordenacao +
				'}';
	}
}
