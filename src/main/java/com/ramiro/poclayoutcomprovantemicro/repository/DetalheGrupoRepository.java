package com.ramiro.poclayoutcomprovantemicro.repository;

import com.ramiro.poclayoutcomprovantemicro.dto.DetalheGrupoDto;
import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;
import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupo;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.reactivex.mysqlclient.MySQLPool;

import io.vertx.reactivex.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DetalheGrupoRepository {

    private MySQLPool clientSql;

    @Inject
    public DetalheGrupoRepository(MySQLPool clientSql) {
        this.clientSql = clientSql;
    }

    public Maybe<List<DetalheGrupo>> obterDetalheGrupoPorListaDeGrupoId(String listaIdsGrupos) {
        System.out.println("Entrou Repo.obterDetalheGrupoPorListaDeGrupoId: " + Thread.currentThread().getName());

        return clientSql.query("select * from detalhe_grupo where detalhe_grupo_id in (" + listaIdsGrupos + ");").rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet -> {
                    List<DetalheGrupo> lista = new ArrayList<>();

                    for(Row row : rowSet){
                        lista.add(DetalheGrupo.of(row));
                    }
                    return lista;
                });
    }
}
