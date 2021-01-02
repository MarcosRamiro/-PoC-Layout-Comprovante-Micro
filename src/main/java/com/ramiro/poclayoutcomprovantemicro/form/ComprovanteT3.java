package com.ramiro.poclayoutcomprovantemicro.form;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class ComprovanteT3 {
	
	private String id;
	private String titulo;
	private String tipo;
	private String versao;
	private List<DetalheComprovanteT3> detalhes;

	@JsonGetter("id") 	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonGetter("titulo") public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@JsonGetter("tipo") public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@JsonGetter("versao") public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	@JsonGetter("detalhes") public List<DetalheComprovanteT3> getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(List<DetalheComprovanteT3> detalhes) {
		this.detalhes = detalhes;
	}

}
