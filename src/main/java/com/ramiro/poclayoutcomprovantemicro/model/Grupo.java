package com.ramiro.poclayoutcomprovantemicro.model;

import io.vertx.reactivex.sqlclient.Row;

import java.io.Serializable;
import java.util.List;


public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;
	private long grupoId;
	private String titulo;
	private String tipo;
	private int ordenacao;
	private boolean visibilidade;
	private List<DetalheGrupo> detalhes;

	public static Grupo copiar(Grupo grupo){
		Grupo grupoNovo = new Grupo();
		grupoNovo.setGrupoId(grupo.getGrupoId());
		grupoNovo.setDetalhes(grupo.getDetalhes());
		grupoNovo.setTipo(grupo.getTipo());
		grupoNovo.setTitulo(grupo.getTitulo());
		grupoNovo.setVisibilidade(grupo.isVisibilidade());
		return grupoNovo;
	}

    public static Grupo of(Row row) {
		Grupo grupo = new Grupo();
    	grupo.setGrupoId(row.getInteger("grupo_id"));
		grupo.setOrdenacao(row.getShort("ordenacao"));
		grupo.setTipo(row.getString("tipo"));
		grupo.setTitulo(row.getString("titulo"));
		grupo.setVisibilidade(row.getBoolean("visibilidade"));
		return grupo;
    }

    public boolean isVisibilidade() {
		return visibilidade;
	}
	public void setVisibilidade(boolean visibilidade) {
		this.visibilidade = visibilidade;
	}
	public List<DetalheGrupo> getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(List<DetalheGrupo> detalhes) {
		this.detalhes = detalhes;
	}
	public long getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(long grupoId) {
		this.grupoId = grupoId;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	@Override
	public String toString() {
		return "Grupo{" +
				"grupoId=" + grupoId +
				", titulo='" + titulo + '\'' +
				", tipo='" + tipo + '\'' +
				", ordenacao=" + ordenacao +
				", visibilidade=" + visibilidade +
				", detalhes=" + detalhes +
				'}';
	}
}
