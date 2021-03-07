package com.ramiro.poclayoutcomprovantemicro.mapper;

import com.ramiro.poclayoutcomprovantemicro.dto.GrupoDto;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GrupoMapper {

    private final DetalheGrupoMapper detalheGrupoMapper;

    @Inject
    public GrupoMapper(DetalheGrupoMapper detalheGrupoMapper) {
        this.detalheGrupoMapper = detalheGrupoMapper;
    }

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
        grupoDto.setVisibilidade(grupo.isVisibilidade());
        grupoDto.setDetalhesGrupos(detalheGrupoMapper.transformar(grupo));
        return grupoDto;

    }
}
