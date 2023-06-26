package br.com.cardosobritzburger;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Produto extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String nome;
    private String descricao;
    private String textoValor; //"R$12,00"
    private Double valor; // a ser usado no carrinho

    public Produto(String nome, String descricao, String textoValor, Double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.textoValor = textoValor;
        this.valor = valor;
    }

    public Produto() {    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTextoValor() {
        return textoValor;
    }

    public void setTextoValor(String textoValor) {
        this.textoValor = textoValor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
