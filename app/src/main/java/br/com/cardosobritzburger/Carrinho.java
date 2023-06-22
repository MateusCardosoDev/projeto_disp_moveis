package br.com.cardosobritzburger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable {
    private List<Produto> produtos;
    private boolean entrega; // true = tele-entrega; false = retirada

    public Carrinho() {
        produtos = new ArrayList<>();
        entrega = false; // por padrão a entrega é retirada
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }
}
