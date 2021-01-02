package com.ramiro.poclayoutcomprovantemicro.dto;


import com.ramiro.poclayoutcomprovantemicro.service.ServiceBind;

public abstract class DetalheGrupoDto {

    private int ordenacao;
    private boolean visibilidade;

    public int getOrdenacao() {
        return ordenacao;
    }
    public void setOrdenacao(int ordenacao) {
        this.ordenacao = ordenacao;
    }
    public boolean isVisibilidade() {
        return visibilidade;
    }
    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public abstract void tratarAtributos(ServiceBind serviceBind, String json);

}
