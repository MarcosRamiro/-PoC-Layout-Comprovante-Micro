package com.ramiro.poclayoutcomprovantemicro.dto;

import com.ramiro.poclayoutcomprovantemicro.service.ServiceBind;

import java.util.function.Function;

public class DetalheGrupoTipoTextoDto extends DetalheGrupoDto {

    private String texto;

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public void tratarAtributos(Function<String, String> funcao) {
        this.setTexto(funcao.apply(this.getTexto()));
    }
}
