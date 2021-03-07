package com.ramiro.poclayoutcomprovantemicro.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class GrupoDto {
	
	private int ordenacao;
	private String titulo;
	private String tipo;
	private boolean visibilidade;
	private List<DetalheGrupoDto> detalhesGrupos;

	public GrupoDto(){}
		
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	//@JsonGetter("detalhes")
	//@JsonSetter("detalhes")
	public List<DetalheGrupoDto> getDetalhesGrupos() {
		return detalhesGrupos;
	}

	public void setDetalhesGrupos(List<DetalheGrupoDto> detalhesGrupos) {
		this.detalhesGrupos = detalhesGrupos;
	}

	public boolean isVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(boolean visibilidade) {
		this.visibilidade = visibilidade;
	}

}
