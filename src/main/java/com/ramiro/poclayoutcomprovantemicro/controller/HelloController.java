package com.ramiro.poclayoutcomprovantemicro.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.HttpMethodMapping;
import io.micronaut.http.annotation.Produces;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

@Controller("/hello")
public class HelloController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public Maybe<String> obterComprovante(){

        System.out.println(Thread.currentThread().getName());

        return Maybe.just("Ola mundo ")
                .map(a -> a + Thread.currentThread().getName())
                .map(a -> a + " | testar --> " + testar(a).blockingGet());

    }

    private Maybe<String> testar(String entrada){

        String retorno;

        retorno = Maybe.just(entrada)
                .map(a -> a.replace('n', 'N') + " | Thread: "  + Thread.currentThread().getName())
                .subscribeOn(Schedulers.computation())
                .doOnSuccess(b -> System.out.println("Sucesso: " + b)).blockingGet();

        System.out.println(retorno);

        return Maybe.just(entrada)
                .map(a -> a.replace('n', 'N') + " | Thread: "  + Thread.currentThread().getName())
                .subscribeOn(Schedulers.computation())
                .doOnSuccess(b -> System.out.println("Sucesso: " + b));

    }

}
