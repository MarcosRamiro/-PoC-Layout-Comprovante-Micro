package com.ramiro.poclayoutcomprovantemicro.mapper;


import com.ramiro.poclayoutcomprovantemicro.dto.ComprovanteDto;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;

public class ComprovanteMapper {


    private GrupoMapper grupoMapper;

    public ComprovanteDto transformar(Comprovante comprovante) {
        ComprovanteDto comprovanteDto = new ComprovanteDto();
        comprovanteDto.setTipo(comprovante.getTipo());
        comprovanteDto.setTitulo(comprovante.getTitulo());
        comprovanteDto.setDescricao(comprovante.getDescricao());
        comprovanteDto.setVersao(comprovante.getVersao());
        comprovanteDto.setGrupos(grupoMapper.transformar(comprovante.getGrupos()));

        return comprovanteDto;
    }


}
