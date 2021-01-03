package com.ramiro.poclayoutcomprovantemicro.repository;

import com.ramiro.poclayoutcomprovantemicro.model.Comprovante;

import io.reactivex.Maybe;
import io.vertx.reactivex.mysqlclient.MySQLPool;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ComprovanteRepository {

    private MySQLPool clientSql;

    @Inject
    public ComprovanteRepository(MySQLPool clientSql){
        this.clientSql = clientSql;
    }

    public Maybe<Comprovante> pesquisarPorId(Long id) {
        return clientSql.query("select * from comprovante where comprovante_id = " + id.toString()).rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet -> Comprovante.of(rowSet.iterator().next()));
    }

    public Maybe<Comprovante> obterComprovantePorTipoEVersao(String tipo, String versao){

        return clientSql.query("select * from comprovante where tipo = '" + tipo + "' and versao = '" + versao + "' limit 1;").rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet -> Comprovante.of(rowSet.iterator().next()));
    }

}
