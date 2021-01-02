package com.ramiro.poclayoutcomprovantemicro.controller;

import com.ramiro.poclayoutcomprovantemicro.form.ComprovanteT3;
import com.ramiro.poclayoutcomprovantemicro.infra.Connection;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;
import com.ramiro.poclayoutcomprovantemicro.repository.ComprovanteRepository;
import com.ramiro.poclayoutcomprovantemicro.repository.GrupoRepository;
import com.ramiro.poclayoutcomprovantemicro.service.ComprovanteService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller("/comprovante")
public class ComprovanteController {

    private final ComprovanteService comprovanteService;
    private final Connection connection;
    private final GrupoRepository grupoRepository;
    private final ComprovanteRepository comprovanteRepository;

    @Inject
    public ComprovanteController(ComprovanteService comprovanteService, Connection connection, GrupoRepository grupoRepository,
                                 ComprovanteRepository comprovanteRepository){
        this.comprovanteService = comprovanteService;
        this.connection = connection;
        this.grupoRepository = grupoRepository;
        this.comprovanteRepository = comprovanteRepository;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public Maybe<Comprovante> obterComprovante(@Body ComprovanteT3 comprovanteT3){

        return comprovanteService.obterComprovantePorTipoEVersao(comprovanteT3.getTipo(), comprovanteT3.getVersao());

    }

}
