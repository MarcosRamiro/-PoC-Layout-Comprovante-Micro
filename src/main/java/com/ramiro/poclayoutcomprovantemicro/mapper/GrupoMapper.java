package com.ramiro.poclayoutcomprovantemicro.mapper;

import com.ramiro.poclayoutcomprovantemicro.dto.GrupoDto;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;

import java.util.List;
import java.util.stream.Collectors;


public class GrupoMapper {


    private DetalheGrupoMapper detalheGrupoMapper;

    public List<GrupoDto> transformar(List<Grupo> grupos) {

        return grupos.stream()
                .map(this::preencher)
                .collect(Collectors.toList());
    }

    private GrupoDto preencher(Grupo grupo) {

        GrupoDto grupoDto = new GrupoDto();
        grupoDto.setTitulo(grupo.getTitulo());
        grupoDto.setTipo(grupo.getTipo());
        grupoDto.setOrdenacao(grupo.getOrdenacao());
        grupoDto.setDetalhesGrupos(detalheGrupoMapper.transformar(grupo));
        return grupoDto;

    }
}
