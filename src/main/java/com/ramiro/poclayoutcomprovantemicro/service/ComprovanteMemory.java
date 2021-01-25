package com.ramiro.poclayoutcomprovantemicro.service;

import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;

@Singleton
public class ComprovanteMemory{

    private final HashMap<String, Comprovante> comprovanteMap;
    private final HashMap<String, Instant> comprovanteInstant;

    public ComprovanteMemory() {
        this.comprovanteMap = new HashMap();
        this.comprovanteInstant = new HashMap();
    }

    public void guardar(Comprovante comprovante) {
        set(comprovante);
    }

    public Comprovante getComprovante(String tipo, String versao) {
        System.out.println(Thread.currentThread().getName());
        Comprovante comprovante = get(tipo, versao);

        if(comprovante != null)
            return validarTimeout(comprovante);

        return null;
    }

    private Comprovante validarTimeout(Comprovante comprovante) {

        Instant agora = Instant.now();
        Instant instantComprovante = getInstantComprovante(comprovante);

        if(agora.isAfter(instantComprovante)){
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

    private Comprovante get(String tipo, String versao){
        return comprovanteMap.get(construirChave(tipo, versao));
    }
    private Comprovante get(Comprovante comprovante) {
        return get(comprovante.getTipo(), comprovante.getVersao());
    }
    private void set(Comprovante comprovante) {

        if(get(comprovante) == null)
        {
            String comprovanteChave = construirChave(comprovante);
            comprovanteMap.put(comprovanteChave, comprovante);
            comprovanteInstant.put(comprovanteChave, Instant.now().plusSeconds(10));
        }

    }

    private String construirChave(Comprovante comprovante){
        return construirChave(comprovante.getTipo(), comprovante.getVersao());
    }

    private String construirChave(String tipo, String versao){
        return tipo.toLowerCase(Locale.ROOT) + "." + versao.toLowerCase(Locale.ROOT);
    }

}
