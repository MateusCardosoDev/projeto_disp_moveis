package br.com.cardosobritzburger;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private String descricao;
    private String textoValor; //"R$12,00"
    private Double valor; // a ser usado no carrinho
    private int quantidade; // a ser usado no carrinho
    private String observacao; // a ser usado no carrinho

    public Produto(String nome, String descricao, String textoValor, Double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.textoValor = textoValor;
        this.valor = valor;
    }

    public Produto() {    }

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "<b>" + nome + "<\\b>\n" + descricao + "\n" + valor;
    }
}
