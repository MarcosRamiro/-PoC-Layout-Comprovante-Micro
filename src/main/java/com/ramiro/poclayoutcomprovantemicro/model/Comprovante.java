package com.ramiro.poclayoutcomprovantemicro.model;

import io.vertx.reactivex.sqlclient.Row;

import java.io.Serializable;
import java.util.List;


public class Comprovante implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long comprovanteId;
	private String titulo;
	private String descricao;
	private String tipo;
	private String versao;

	private List<Grupo> grupos;

    public static Comprovante of(Row row) {
    	Comprovante comprovante = new Comprovante();
		comprovante.setComprovanteId(row.getLong(0));
		comprovante.setTitulo(row.getString("titulo"));
		comprovante.setDescricao(row.getString("descricao"));
		comprovante.setTipo(row.getString("tipo"));
		comprovante.setVersao(row.getString("versao"));
    	return comprovante;
    }

    public void setComprovanteId(long comprovanteId) {
		this.comprovanteId = comprovanteId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public long getComprovanteId() {
		return comprovanteId;
	}

	@Override
	public String toString() {
		return "Comprovante{" +
				"comprovanteId=" + comprovanteId +
				", titulo='" + titulo + '\'' +
				", descricao='" + descricao + '\'' +
				", tipo='" + tipo + '\'' +
				", versao='" + versao + '\'' +
				", grupos=" + grupos +
				'}';
	}
}
