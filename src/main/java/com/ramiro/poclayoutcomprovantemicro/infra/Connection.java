package com.ramiro.poclayoutcomprovantemicro.infra;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.RowSet;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.RowIterator;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Connection {

    private MySQLPool client;

    @Inject
    public Connection(MySQLPool conn){
        this.client = conn;
    }

    public Single<String> acessar(){



        return client.query("select * from comprovante").rxExecute().map(rowSet -> {
                RowIterator<Row> iterator = rowSet.iterator();
                int id = iterator.next().getInteger("comprovante_id");
                return "id: " + id;
        });
/*
        client.query("select * from comprovante").execute(ar -> {
            if (ar.succeeded()) {
                RowSet<Row> result = ar.result();
                System.out.println("Got " + result.size() + " rows ");
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }

            // Now close the pool
            // client.close();
        });
        System.out.println("Saiu Connection.acessar()");
        */

    }

}
