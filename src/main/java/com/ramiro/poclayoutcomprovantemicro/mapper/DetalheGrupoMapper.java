package com.ramiro.poclayoutcomprovantemicro.mapper;


import com.ramiro.poclayoutcomprovantemicro.dto.DetalheGrupoDto;
import com.ramiro.poclayoutcomprovantemicro.dto.DetalheGrupoTipoBlocoDto;
import com.ramiro.poclayoutcomprovantemicro.dto.DetalheGrupoTipoTextoDto;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DetalheGrupoMapper {

    private static final String TITULO_ATRIBUTO = "tituloAtributo";
    private static final String VALOR_ATRIBUTO = "valorAtributo";
    private static final String TEXTO = "texto";


    public List<DetalheGrupoDto> transformar(Grupo grupo) {

        if (grupo.getTipo().equalsIgnoreCase("bloco"))
                return obterDetalheGrupoTipoBloco(grupo);
        if (grupo.getTipo().equalsIgnoreCase("texto"))
             return obterDetalheGrupoTipoTexto(grupo);
        if (grupo.getTipo().equalsIgnoreCase("linha_horizontal"))
             return null;

        return null;
    }

    private  List<DetalheGrupoDto> obterDetalheGrupoTipoBloco(Grupo grupo){

        return grupo.getDetalhes()
                .stream()
                .map( detalhe -> {
                                DetalheGrupoTipoBlocoDto detalheGrupoTipoBlocoDto = new DetalheGrupoTipoBlocoDto();
                                detalheGrupoTipoBlocoDto.setOrdenacao(detalhe.getOrdenacao());
                                detalheGrupoTipoBlocoDto.setVisibilidade(detalhe.isVisibilidade());
                                detalheGrupoTipoBlocoDto.setTituloAtributo(detalhe.obterConteudoDoAtributo(TITULO_ATRIBUTO));
                                detalheGrupoTipoBlocoDto.setValorAtributo(detalhe.obterConteudoDoAtributo(VALOR_ATRIBUTO));
                                return detalheGrupoTipoBlocoDto;
                 }).collect(Collectors.toList());
    }

    private  List<DetalheGrupoDto> obterDetalheGrupoTipoTexto(Grupo grupo){

        return grupo.getDetalhes()
                .stream()
                .map( detalhe -> {
                                DetalheGrupoTipoTextoDto detalheGrupoTipoTextoDto = new DetalheGrupoTipoTextoDto();
                                detalheGrupoTipoTextoDto.setOrdenacao(detalhe.getOrdenacao());
                                detalheGrupoTipoTextoDto.setVisibilidade(detalhe.isVisibilidade());
                                detalheGrupoTipoTextoDto.setTexto(detalhe.obterConteudoDoAtributo(TEXTO));
                                return detalheGrupoTipoTextoDto;
                }).collect(Collectors.toList());
    }
}
