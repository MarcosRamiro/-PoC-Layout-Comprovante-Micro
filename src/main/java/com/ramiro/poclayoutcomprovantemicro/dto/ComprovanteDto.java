package com.ramiro.poclayoutcomprovantemicro.dto;

import java.util.List;

public class ComprovanteDto {
	
	private String id;
	private String titulo;
	private String descricao;
	private String tipo;
	private String versao;
	private List<GrupoDto> grupos;
	
	
	public List<GrupoDto> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<GrupoDto> grupos) {
		this.grupos = grupos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	

}
