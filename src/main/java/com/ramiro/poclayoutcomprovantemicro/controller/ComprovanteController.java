package com.ramiro.poclayoutcomprovantemicro.controller;

import com.ramiro.poclayoutcomprovantemicro.dto.ComprovanteDto;
import com.ramiro.poclayoutcomprovantemicro.form.ComprovanteT3;
import com.ramiro.poclayoutcomprovantemicro.form.TipoVersao;
import com.ramiro.poclayoutcomprovantemicro.service.ComprovanteBinder;
import com.ramiro.poclayoutcomprovantemicro.service.ComprovanteMemory;
import com.ramiro.poclayoutcomprovantemicro.service.ComprovanteService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

@Controller("/comprovante")
public class ComprovanteController {

    private final ComprovanteService comprovanteService;
    private final ComprovanteBinder comprovanteBinder;

    @Inject
    public ComprovanteController(ComprovanteService comprovanteService,
                                 ComprovanteBinder comprovanteBinder){
        this.comprovanteService = comprovanteService;
        this.comprovanteBinder = comprovanteBinder;
    }

    @Post("/detalhe")
    @Produces(MediaType.APPLICATION_JSON)
    public Maybe<ComprovanteDto> obterComprovante(@Body ComprovanteT3 comprovanteT3){

        return comprovanteService.obterComprovantePorTipoEVersao( new TipoVersao(comprovanteT3.getTipo(), comprovanteT3.getVersao()))
                .observeOn(Schedulers.computation())
                .map(comprovante -> comprovanteBinder.bind(comprovanteT3, comprovante));

    }

}
