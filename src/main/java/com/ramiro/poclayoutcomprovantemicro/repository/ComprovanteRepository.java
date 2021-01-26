package com.ramiro.poclayoutcomprovantemicro.repository;

import com.ramiro.poclayoutcomprovantemicro.form.TipoVersao;
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
        final String sql = "select * from comprovante where comprovante_id = " + id.toString();

        return clientSql.query(sql).rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet -> Comprovante.of(rowSet.iterator().next()))
                .doFinally(() -> System.out.println(sql));
    }

    public Maybe<Comprovante> obterComprovantePorTipoEVersao(final TipoVersao tipoVersao){

        final String sql = "select * from comprovante where tipo = '" + tipoVersao.getTipo() + "' and versao = '" + tipoVersao.getVersao() + "' limit 1;";

        return clientSql.query(sql).rxExecute()
                .filter(rowSet -> rowSet.size() > 0)
                .map(rowSet ->Comprovante.of(rowSet.iterator().next()))
                .doFinally(() -> System.out.println(sql));
    }

}
