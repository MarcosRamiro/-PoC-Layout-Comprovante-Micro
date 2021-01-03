package com.ramiro.poclayoutcomprovantemicro.service;

import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;
import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupo;
import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupoConteudo;
import com.ramiro.poclayoutcomprovantemicro.model.Grupo;
import com.ramiro.poclayoutcomprovantemicro.repository.ComprovanteRepository;
import com.ramiro.poclayoutcomprovantemicro.repository.DetalheGrupoConteudoRepository;
import com.ramiro.poclayoutcomprovantemicro.repository.DetalheGrupoRepository;
import com.ramiro.poclayoutcomprovantemicro.repository.GrupoRepository;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class ComprovanteServiceImp implements ComprovanteService {

    private final ComprovanteRepository comprovanteRepository;
    private final GrupoRepository grupoRepository;
    private final DetalheGrupoRepository detalheGrupoRepository;
    private final DetalheGrupoConteudoRepository detalheGrupoConteudoRepository;

    @Inject
    public ComprovanteServiceImp(ComprovanteRepository comprovanteRepository,
                                 GrupoRepository grupoRepository,
                                 DetalheGrupoRepository detalheGrupoRepository,
                                 DetalheGrupoConteudoRepository detalheGrupoConteudoRepository
                                ){
        this.comprovanteRepository = comprovanteRepository;
        this.grupoRepository = grupoRepository;
        this.detalheGrupoRepository = detalheGrupoRepository;
        this.detalheGrupoConteudoRepository = detalheGrupoConteudoRepository;
    }

    public Maybe<Comprovante> obterComprovantePorTipoEVersao(String tipo, String versao) {
         return comprovanteRepository.obterComprovantePorTipoEVersao(tipo, versao)
                 .observeOn(Schedulers.io())
                 .map( comprovante -> {
                     List<Grupo> grupos = grupoRepository.obterGrupoPorComprovanteId(comprovante.getComprovanteId()).blockingFirst();
                     comprovante.setGrupos(grupos);
                     return comprovante;
                 })
                 .map(comprovante -> {
                     List<Grupo> grupos = comprovante.getGrupos();
                     List<DetalheGrupo> detalheGrupos = detalheGrupoRepository.obterDetalheGrupoPorListaDeGrupoId(obterListaDeIds(e -> e.getGrupoId(), grupos)).blockingGet();
                     associarDetalheGruposEmGrupo(grupos, detalheGrupos);

                     return comprovante;
                 })
                 .map(comprovante -> {

                     List<DetalheGrupo> detalheGrupos = comprovante.getGrupos().stream()
                             .flatMap(grupo -> grupo.getDetalhes().stream().map(detalheGrupo -> detalheGrupo)
                             )
                             .filter( det -> det != null)
                             .collect(Collectors.toList());
                     if(detalheGrupos != null && detalheGrupos.size() > 0) {
                         List<DetalheGrupoConteudo> detalheGrupoConteudos = detalheGrupoConteudoRepository.obterDetalheGrupoConteudoPorListaDeDetalheGrupoId(obterListaDeIds(e -> e.getDetalheGrupoId(), detalheGrupos)).blockingGet();
                         associarDetalheGruposConteudoEmDetalheGrupo(detalheGrupos, detalheGrupoConteudos);
                     }
                     return comprovante;
                 })
                 .observeOn(Schedulers.computation())
                 ;
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
        StringBuilder listaDeGrupos = new StringBuilder();

        for(E item : lista){
            listaDeGrupos.append(funcao.apply(item).toString() + ",");
        }

        listaDeGrupos.deleteCharAt(listaDeGrupos.lastIndexOf(","));

        return listaDeGrupos.toString();

    }

}
