package br.com.cardosobritzburger;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmUtil {

    private static Realm realm = null;

    public static void dataBaseInit(Context context) {
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("realm.db")
                .schemaVersion(1)
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();
    }

    public static List<Pedido> getAll(){
        return realm.where(Pedido.class).findAll();
    }

    public static void insert(Pedido pedido){
        Number maxId = realm.where(Pedido.class).max("id"); //pegar o id de maior valor dos pedidos
        Double totalPedido = 0.0;
        int id = maxId == null ? 1 : maxId.intValue() + 1;
        pedido.setId(id);
        maxId = realm.where(Produto.class).max("id"); //pegar o id de maior valor dos produtos
        id = maxId == null ? 0 : maxId.intValue();
        for(int i = 0; i < pedido.getProdutos().size(); i++){
            Produto produto = pedido.getProdutos().get(i);
            produto.setId(id + 1);
            id = id + 1;
            totalPedido += produto.getValor();
        }
        pedido.setTotal("R$"+totalPedido);
        realm.beginTransaction();
        realm.insert(pedido);
        realm.commitTransaction();
    }

}
