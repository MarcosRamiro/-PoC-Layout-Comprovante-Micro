package com.ramiro.poclayoutcomprovantemicro.form;

public class TipoVersao {

    private final String tipo;
    private final String versao;

    public TipoVersao(String tipo, String versao) {
        this.tipo = tipo;
        this.versao = versao;
    }

    public String getTipo() {
        return tipo;
    }

    public String getVersao() {
        return versao;
    }
}
