package com.ramiro.poclayoutcomprovantemicro.mapper;

import com.ramiro.poclayoutcomprovantemicro.dto.GrupoDto;
import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupo;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MicronautTest
public class GrupoMapperTeste {

    private static final Long ID = 1L;
    private static final boolean VISIBILIDADE = true;
    private static final String TITULO = "titulo grupo";
    private static final String TIPO = "bloco";
    private static final int ORDENACAO = 0;
    private static final List<DetalheGrupo> DETALHES = new ArrayList<>();

    private final GrupoMapper grupoMapper;

    @Inject
    public GrupoMapperTeste(GrupoMapper grupoMapper) {
        this.grupoMapper = grupoMapper;
    }

    @Test
    public void deveTransformarGrupo(){


        List<GrupoDto> grupos = grupoMapper.transformar(obterListaGrupo());

        Assertions.assertEquals(1, grupos.size());
        Optional<GrupoDto> grupoDtoOptional = grupos.stream().findFirst();
        Assertions.assertTrue(grupoDtoOptional.isPresent());

        GrupoDto grupoDto = grupoDtoOptional.get();

        Assertions.assertEquals(GrupoMapperTeste.DETALHES, grupoDto.getDetalhesGrupos());
        Assertions.assertEquals(GrupoMapperTeste.ORDENACAO, grupoDto.getOrdenacao());
        Assertions.assertEquals(GrupoMapperTeste.TIPO, grupoDto.getTipo());
        Assertions.assertEquals(GrupoMapperTeste.TITULO, grupoDto.getTitulo());
        Assertions.assertEquals(GrupoMapperTeste.VISIBILIDADE, grupoDto.isVisibilidade());

    }

    private List<Grupo> obterListaGrupo() {

        List<Grupo> listaGrupos = new ArrayList<>();

        Grupo grupo = new Grupo();
        grupo.setGrupoId(GrupoMapperTeste.ID);
        grupo.setVisibilidade(GrupoMapperTeste.VISIBILIDADE);
        grupo.setTitulo(GrupoMapperTeste.TITULO);
        grupo.setTipo(GrupoMapperTeste.TIPO);
        grupo.setOrdenacao(GrupoMapperTeste.ORDENACAO);
        grupo.setDetalhes(GrupoMapperTeste.DETALHES);

        listaGrupos.add(grupo);

        return listaGrupos;


    }
}
