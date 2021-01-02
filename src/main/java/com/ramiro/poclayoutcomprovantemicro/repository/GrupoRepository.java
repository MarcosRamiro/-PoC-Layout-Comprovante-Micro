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

/*
    public Maybe<Grupo> pesquisarPorId(Long id) {

        return clientSql.query("select * from grupo where grupo_id = " + id.toString()).rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet -> Grupo.of(rowSet.iterator().next()));
    }

    public List<Grupo> selecionarTodos() {
        return clientSql.query("select * from grupo").rxExecute()
                .map(rowSet -> {
                    if(rowSet.size() ==0)
                        throw new RuntimeException("not found Ramiro");
                    List<Grupo> grupos = new ArrayList<Grupo>();
                    for(Row row : rowSet){
                        grupos.add(Grupo.of(row));
                    }
                    return grupos;
                }).blockingGet();

    }

 */

    public Observable<List<Grupo>> obterGrupoPorComprovanteId(long comprovanteId) {
        System.out.println("Entrou GrupoRepository.obterGrupoPorComprovanteId: " + Thread.currentThread().getName());
        return clientSql.query("select * from grupo where comprovante_id = " + comprovanteId).rxExecute()
                .filter(rows -> rows.size() > 0)
                .map(rowSet -> {
                    List<Grupo> grupos = new ArrayList<Grupo>();
                    for(Row row : rowSet){
                        grupos.add(Grupo.of(row));
                    }
                    return grupos;
                }).toObservable();

    }
}
