package br.com.cardosobritzburger;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pedido extends RealmObject {

    @PrimaryKey
    private Integer id;
    private RealmList<Produto> produtos;
    private String data;
    private String endereco;
    private String total;

    public Pedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(RealmList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
