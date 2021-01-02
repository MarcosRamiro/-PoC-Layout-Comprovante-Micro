package com.ramiro.poclayoutcomprovantemicro.service;


import com.google.gson.GsonBuilder;
import com.ramiro.poclayoutcomprovantemicro.dto.ComprovanteDto;
import com.ramiro.poclayoutcomprovantemicro.dto.DetalheGrupoDto;
import com.ramiro.poclayoutcomprovantemicro.dto.GrupoDto;
import com.ramiro.poclayoutcomprovantemicro.form.ComprovanteT3;
import com.ramiro.poclayoutcomprovantemicro.mapper.ComprovanteMapper;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;

import java.util.List;

// @RequestScope // necessario pois guarde estado (json)

public class ComprovanteBinder {


	private ServiceBind serviceBind;

	private ComprovanteMapper comprovanteMapper;

	public ComprovanteDto bind(ComprovanteT3 comprovanteT3, Comprovante comprovante) {

		String json = new GsonBuilder().setPrettyPrinting().create().toJson(comprovanteT3);

		ComprovanteDto comprovanteDto = comprovanteMapper.transformar(comprovante);

		comprovanteDto.setId(comprovanteT3.getId());

		comprovanteDto.setTitulo(serviceBind.bind(comprovanteDto.getTitulo(), json));
		comprovanteDto.setId(serviceBind.bind(comprovanteDto.getId(), json));
		comprovanteDto.setTipo(serviceBind.bind(comprovanteDto.getTipo(), json));
		comprovanteDto.setVersao(serviceBind.bind(comprovanteDto.getVersao(), json));

		comprovanteDto.getGrupos()
				.stream()
				.forEach(detalhe -> tratarGrupos(detalhe, json));

		return comprovanteDto;
	}

	private void tratarGrupos(GrupoDto grupo, String json) {

		grupo.setTitulo(serviceBind.bind(grupo.getTitulo(), json));
		tratarDetalhesGrupos(grupo.getDetalhesGrupos(), json);

	}

	private void tratarDetalhesGrupos(List<DetalheGrupoDto> detalhes, String json) {

		if(detalhes == null) return;

		for (DetalheGrupoDto detalhe : detalhes) {
			detalhe.tratarAtributos(serviceBind, json);
		}
	}
}
