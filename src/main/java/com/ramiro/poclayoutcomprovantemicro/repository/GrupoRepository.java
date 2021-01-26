package com.ramiro.poclayoutcomprovantemicro.repository;

import com.ramiro.poclayoutcomprovantemicro.model.Grupo;

import io.reactivex.*;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.mysqlclient.MySQLPool;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;


@Singleton
public class GrupoRepository {

    private MySQLPool clientSql;

    @Inject
    public GrupoRepository(MySQLPool clientSql){
        this.clientSql = clientSql;
    }

    public Maybe<List<Grupo>> obterGrupoPorComprovanteId(long comprovanteId) {

        final String sql = "select * from grupo where comprovante_id = " + comprovanteId + ";";

        return clientSql.query(sql).rxExecute()
                .filter(rows -> rows.size() > 0)
                .map(rowSet -> {
                    List<Grupo> grupos = new ArrayList();
                    for(Row row : rowSet){
                        grupos.add(Grupo.of(row));
                    }
                    return grupos;
                }).doFinally(() -> System.out.println(sql));

    }
}
