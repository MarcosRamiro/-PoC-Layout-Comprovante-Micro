package com.ramiro.poclayoutcomprovantemicro.service;

import com.ramiro.poclayoutcomprovantemicro.form.TipoVersao;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;
import io.reactivex.Maybe;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;

@Singleton
public class ComprovanteMemory{

    private final HashMap<String, Comprovante> comprovanteMap;
    private final HashMap<String, Instant> comprovanteInstant;

    public ComprovanteMemory() {
        this.comprovanteMap = new HashMap<>();
        this.comprovanteInstant = new HashMap<> ();
    }

    public void guardar(Comprovante comprovante) {
        set(comprovante);
    }

    public Maybe<Comprovante> getComprovante(TipoVersao tipoVersao) {

        Comprovante comprovante = get(tipoVersao);

        if(comprovante != null) {
            comprovante = validarTimeout(comprovante);
        }

        if (comprovante == null)
            return Maybe.just(new Comprovante());

        return Maybe.just(comprovante);

    }

    private Comprovante validarTimeout(Comprovante comprovante) {

        if(Instant.now().isAfter(getInstantComprovante(comprovante))){
            String chave = construirChave(comprovante);
            comprovanteMap.remove(chave);
            comprovanteInstant.remove(chave);
            return null;
        }

        return  comprovante;

    }

    private Instant getInstantComprovante(Comprovante comprovante) {
         return comprovanteInstant.get(construirChave(comprovante));
    }

    private Comprovante get(TipoVersao tipoVersao){
        return comprovanteMap.get(construirChave(tipoVersao.getTipo(), tipoVersao.getVersao()));
    }
    private Comprovante get(Comprovante comprovante) {
        return get(new TipoVersao(comprovante.getTipo(), comprovante.getVersao()));
    }
    private void set(Comprovante comprovante) {

        if(get(comprovante) == null)
        {
            String chave = construirChave(comprovante);
            comprovanteMap.put(chave, comprovante);
            comprovanteInstant.put(chave, Instant.now().plusSeconds(30));
        }

    }

    private String construirChave(Comprovante comprovante){
        return construirChave(comprovante.getTipo(), comprovante.getVersao());
    }

    private String construirChave(String tipo, String versao){
        return tipo.toLowerCase(Locale.ROOT) + "." + versao.toLowerCase(Locale.ROOT);
    }

}
