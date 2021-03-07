package com.ramiro.poclayoutcomprovantemicro.mapper;


import com.ramiro.poclayoutcomprovantemicro.dto.ComprovanteDto;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

@MicronautTest
public class ComprovanteMapperTeste {

    private final ComprovanteMapper comprovanteMapper;

    public static String VERSAO = "v1";
    public static String TIPO = "rfb";
    public static String TITULO = "titulo rfb";
    public static String DESCRICAO = "descricao rfb";
    public static Long ID = 1L;

    @Inject
    public ComprovanteMapperTeste(ComprovanteMapper comprovanteMapper) {
        this.comprovanteMapper = comprovanteMapper;
    }

    @Test
    public void deveGerarComprovanteDto(){

        ComprovanteDto comprovanteDto = comprovanteMapper.transformar(montarComprovante());

        Assertions.assertEquals(ComprovanteMapperTeste.VERSAO, comprovanteDto.getVersao());
        Assertions.assertEquals(ComprovanteMapperTeste.TIPO, comprovanteDto.getTipo());
        Assertions.assertEquals(ComprovanteMapperTeste.TITULO, comprovanteDto.getTitulo());
        Assertions.assertEquals(ComprovanteMapperTeste.DESCRICAO, comprovanteDto.getDescricao());
        Assertions.assertNull(comprovanteDto.getId());
        Assertions.assertEquals(new ArrayList<>(), comprovanteDto.getGrupos());

    }

    private Comprovante montarComprovante(){
        Comprovante comprovante = new Comprovante();
        comprovante.setComprovanteId(ComprovanteMapperTeste.ID);
        comprovante.setVersao(ComprovanteMapperTeste.VERSAO);
        comprovante.setTipo(ComprovanteMapperTeste.TIPO);
        comprovante.setTitulo(ComprovanteMapperTeste.TITULO);
        comprovante.setDescricao(ComprovanteMapperTeste.DESCRICAO);
        comprovante.setGrupos(new ArrayList<>());
        return comprovante;

    }

}
