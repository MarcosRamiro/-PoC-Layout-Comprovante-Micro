package com.ramiro.poclayoutcomprovantemicro.repository;

import com.ramiro.poclayoutcomprovantemicro.model.DetalheGrupo;

import io.reactivex.Maybe;

import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.Row;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DetalheGrupoRepository {

    private final MySQLPool clientSql;

    @Inject
    public DetalheGrupoRepository(MySQLPool clientSql) {
        this.clientSql = clientSql;
    }

    public Maybe<List<DetalheGrupo>> obterDetalheGrupoPorListaDeGrupoId(String listaIdsGrupos) {

        final String sql = "select * from detalhe_grupo where grupo_id in (" + listaIdsGrupos + ");";

        return clientSql.query(sql).rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet -> {
                    List<DetalheGrupo> lista = new ArrayList<>();

                    for(Row row : rowSet){
                        lista.add(DetalheGrupo.of(row));
                    }
                    return lista;
                }).doFinally(() -> System.out.println(sql));
    }
}
