package com.ramiro.poclayoutcomprovantemicro.repository;

import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupo;
import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupoConteudo;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.Row;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DetalheGrupoConteudoRepository {

    private final MySQLPool clientSql;
    private static final String CONSULTA = "SELECT det_gru_con.detalhe_grupo_conteudo_id,det_gru_con.detalhe_grupo_id,det_gru_con.dominio_atributo_detalhe_grupo_id,det_gru_con.conteudo,dominio.nome_atributo FROM detalhe_grupo_conteudo as det_gru_con INNER JOIN dominio_atributo_detalhe_grupo as dominio on det_gru_con.dominio_atributo_detalhe_grupo_id = dominio.dominio_atributo_detalhe_grupo_id where detalhe_grupo_id in ( ";

    @Inject
    public DetalheGrupoConteudoRepository(MySQLPool clientSql) {
        this.clientSql = clientSql;
    }

    public  Maybe<List<DetalheGrupoConteudo>> obterDetalheGrupoConteudoPorListaDeDetalheGrupoId(String listaIds) {

        return clientSql.query(CONSULTA + listaIds + ");").rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet -> {
                    List<DetalheGrupoConteudo> lista = new ArrayList<>();

                    for(Row row : rowSet){
                        lista.add(DetalheGrupoConteudo.of(row));
                    }

                    return lista;

                });
    }
}
