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
        System.out.println("Entrou obterComprovantePorTipoEVersao: " + Thread.currentThread().getName());
         Comprovante comp = comprovanteRepository.obterComprovantePorTipoEVersao(tipo, versao)
                .blockingGet()

                ;
        comp = complementarComprovante(comp);

         if(comp == null) return Maybe.empty();

        System.out.println("Saiu obterComprovantePorTipoEVersao: " + Thread.currentThread().getName());

        return

                Maybe.just(comp);

    }

    private Comprovante complementarComprovante(Comprovante comprovante) {
        System.out.println("Entrou complementarComprovante: " + Thread.currentThread().getName());

        Comprovante comprovanteNovo  = new Comprovante();

        comprovanteNovo.setComprovanteId(comprovante.getComprovanteId());
        comprovanteNovo.setTipo(comprovante.getTipo());
        comprovanteNovo.setVersao(comprovante.getVersao());
        comprovanteNovo.setDescricao(comprovante.getDescricao() + " Ramiro");
        comprovanteNovo.setTitulo(comprovante.getTitulo());

        List<Grupo> grupos = grupoRepository.obterGrupoPorComprovanteId(comprovante.getComprovanteId()).blockingFirst();
        List<DetalheGrupo> detalheGrupos = detalheGrupoRepository.obterDetalheGrupoPorListaDeGrupoId(obterListaDeIds(e -> e.getGrupoId(), grupos)).blockingGet();
        List<DetalheGrupoConteudo> detalheGrupoConteudos = detalheGrupoConteudoRepository.obterDetalheGrupoConteudoPorListaDeDetalheGrupoId(obterListaDeIds(e -> e.getDetalheGrupoId() , detalheGrupos)).blockingGet();

        associarDetalheGruposConteudoEmDetalheGrupo(detalheGrupos, detalheGrupoConteudos);
        associarDetalheGruposEmGrupo(grupos, detalheGrupos);

        System.out.println("Sai complementarComprovante: " + Thread.currentThread().getName());

        comprovanteNovo.setGrupos(grupos);
        return comprovanteNovo;

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
