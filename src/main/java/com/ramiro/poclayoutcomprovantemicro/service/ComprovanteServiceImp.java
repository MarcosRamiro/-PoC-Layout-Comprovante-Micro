package com.ramiro.poclayoutcomprovantemicro.service;

import com.ramiro.poclayoutcomprovantemicro.form.TipoVersao;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;
import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupo;
import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupoConteudo;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;
import com.ramiro.poclayoutcomprovantemicro.repository.ComprovanteRepository;
import com.ramiro.poclayoutcomprovantemicro.repository.DetalheGrupoConteudoRepository;
import com.ramiro.poclayoutcomprovantemicro.repository.DetalheGrupoRepository;
import com.ramiro.poclayoutcomprovantemicro.repository.GrupoRepository;

import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class ComprovanteServiceImp implements ComprovanteService {

    private final ComprovanteRepository comprovanteRepository;
    private final GrupoRepository grupoRepository;
    private final DetalheGrupoRepository detalheGrupoRepository;
    private final DetalheGrupoConteudoRepository detalheGrupoConteudoRepository;
    private final ComprovanteMemory comprovanteMemory;

    @Inject
    public ComprovanteServiceImp(ComprovanteRepository comprovanteRepository,
                                 GrupoRepository grupoRepository,
                                 DetalheGrupoRepository detalheGrupoRepository,
                                 DetalheGrupoConteudoRepository detalheGrupoConteudoRepository,
                                 ComprovanteMemory comprovanteMemory
                                ){
        this.comprovanteRepository = comprovanteRepository;
        this.grupoRepository = grupoRepository;
        this.detalheGrupoRepository = detalheGrupoRepository;
        this.detalheGrupoConteudoRepository = detalheGrupoConteudoRepository;
        this.comprovanteMemory = comprovanteMemory;
    }

    public Maybe<Comprovante> obterComprovantePorTipoEVersao(TipoVersao tipoVersao) {


       return Maybe.just(tipoVersao)
               .observeOn(Schedulers.single())
               .flatMap(tipoVersaoComprovante -> {
                   return comprovanteMemory.getComprovante(tipoVersao);
               })
               .doOnError(e -> System.out.println("Erro aqui " + e.toString()))
               .observeOn(Schedulers.io())
               .flatMap(a -> {

                   // TODO: corrigir a gambiarra
                   if (a.getTipo() != null)
                       return Maybe.just(a);

                   return comprovanteRepository.obterComprovantePorTipoEVersao(tipoVersao)
                           .observeOn(Schedulers.io())
                           .flatMap(comprovante -> grupoRepository.obterGrupoPorComprovanteId(comprovante.getComprovanteId())
                                            .map(grupos -> {
                                                comprovante.setGrupos(grupos);
                                                return comprovante;
                                            })
                           )
                           .flatMap(comprovante ->
                                detalheGrupoRepository.obterDetalheGrupoPorListaDeGrupoId(obterListaDeIds(e -> e.getGrupoId(), comprovante.getGrupos()))
                                .map(detalheGrupos -> {
                                   associarDetalheGruposEmGrupo(comprovante.getGrupos(), detalheGrupos);
                                   return comprovante;
                                })
                           )
                           .flatMap(comprovante -> {
                               List<DetalheGrupo> detalheGrupos = comprovante.getGrupos().stream()
                                       .flatMap(grupo -> grupo.getDetalhes().stream())
                                       .filter(Objects::nonNull)
                                       .collect(Collectors.toList());

                               if (detalheGrupos.size() > 0) {
                                    return detalheGrupoConteudoRepository.obterDetalheGrupoConteudoPorListaDeDetalheGrupoId(obterListaDeIds(e -> e.getDetalheGrupoId(), detalheGrupos))
                                           .map(detalheGrupoConteudos -> {
                                               associarDetalheGruposConteudoEmDetalheGrupo(detalheGrupos, detalheGrupoConteudos);
                                               return comprovante;
                                           });

                               }
                               return Maybe.just(comprovante);

                           })
                           .doOnSuccess(comprovanteMemory::guardar)
                           ;
               });

    }

    private void associarDetalheGruposEmGrupo(List<Grupo> grupos, List<DetalheGrupo> detalheGrupos) {
        grupos.stream()
                .forEach(grupo ->
                        grupo.setDetalhes(
                                detalheGrupos.stream()
                                        .filter(detalheGrupo -> detalheGrupo.getGrupoId().equals(grupo.getGrupoId()))
                                        .collect(Collectors.toList()))
                );
    }

    private void associarDetalheGruposConteudoEmDetalheGrupo(List<DetalheGrupo> detalheGrupos, List<DetalheGrupoConteudo> detalheGrupoConteudos) {

        detalheGrupos.stream()
                .forEach(detalheGrupo ->
                        detalheGrupo.setDetalheGrupoConteudo(
                                detalheGrupoConteudos.stream()
                                        .filter(det -> det.getDetalheGrupoId().equals(detalheGrupo.getDetalheGrupoId()))
                                        .collect(Collectors.toList()))
                );
    }

    private <E, F extends Number> String obterListaDeIds(Function<E, F> funcao, List<E> lista){

        StringBuilder listaDeIds = new StringBuilder();

        for(E item : lista)
            listaDeIds.append(funcao.apply(item).toString() + ",");

        return listaDeIds
                .deleteCharAt(listaDeIds.lastIndexOf(","))
                .toString();

    }

}
