package br.com.cardosobritzburger;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoHistorico {

    private Integer id;
    private List<Produto> produtos;
    private LocalDateTime data;
    private boolean entrega; // true = tele-entrega; false = retirada

    public PedidoHistorico(Integer id, List<Produto> produtos, LocalDateTime data, boolean entrega) {
        this.id = id;
        this.produtos = produtos;
        this.data = data;
        this.entrega = entrega;
    }

    public PedidoHistorico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }
}
