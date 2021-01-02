package com.ramiro.poclayoutcomprovantemicro.dto;

import com.ramiro.poclayoutcomprovantemicro.service.ServiceBind;

public class DetalheGrupoTipoTextoDto extends DetalheGrupoDto {

    private String texto;

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public void tratarAtributos(ServiceBind serviceBind, String json) {
        this.setTexto(serviceBind.bind(this.getTexto(), json));
    }
}
