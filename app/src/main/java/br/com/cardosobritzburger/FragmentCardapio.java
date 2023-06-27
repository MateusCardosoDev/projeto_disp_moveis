package br.com.cardosobritzburger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentCardapio extends Fragment {

    private FloatingActionButton btnCarrinho;

    public FragmentCardapio(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cardapio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        carregaCardapio();
    }

    private void carregaCardapio(){
        btnCarrinho = getView().findViewById(R.id.carrinho);
        btnCarrinho.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CarrinhoActivity.class);
            startActivity(intent);
        });

        LinearLayout classicBurgersLayout = getView().findViewById(R.id.classic_burger_layout);
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Kids", "Carne e queijo", "R$ 12,00", 12.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Bacon", "Bacon e queijo", "R$ 16,00", 16.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Carne", "Um bife de carne com queijo", "R$ 15,00", 15.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Dobro Carne", "Dois bifes de carne com queijo", "R$ 19,00", 19.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Calabresa", "Calabresa com queijo", "R$ 16,00", 16.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Frango", "Um bife de Frango com queijo", "R$ 17,00", 17.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Dobro Frango", "Dois bifes de Frango com queijo", "R$ 23,00", 23.00));

        LinearLayout specialBurgersLayout = getView().findViewById(R.id.special_burgers_layout);
        insereProdutoNoLayout(specialBurgersLayout, new Produto("Burger Chocolate", "Recheio de chocolate preto", "R$ 16,00", 16.00));
        insereProdutoNoLayout(specialBurgersLayout, new Produto("Burger Chocolate Branco", "Recheio de chocolate branco", "R$ 16,00", 16.00));

        LinearLayout drinksLayout = getView().findViewById(R.id.drinks_layout);
        insereProdutoNoLayout(drinksLayout, new Produto("Refri 2 Litros", "Coca-Cola, Sprite ou Pepsi", "R$ 10,00", 10.00));
        insereProdutoNoLayout(drinksLayout, new Produto("Refri 600ml", "Coca-Cola, Sprite ou Pepsi", "R$ 6,00", 6.00));
        insereProdutoNoLayout(drinksLayout, new Produto("Suco Del Valle - Lata", "Laranja ou Uva", "R$ 8,00", 8.00));
    }

    private void insereProdutoNoLayout(LinearLayout layout, Produto produto) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View convertView;
        ViewHolder holder;
        convertView = layoutInflater.inflate(R.layout.list_buttons_style, null);
        holder = new ViewHolder();
        holder.nome = convertView.findViewById(R.id.nome_produto);
        holder.descricao = convertView.findViewById(R.id.descricao_produto);
        holder.valor = convertView.findViewById(R.id.text_valor);
        holder.nome.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
        holder.valor.setText(produto.getTextoValor());
        convertView.setClickable(true);
        convertView.setTag(holder);
        convertView.setOnClickListener(v -> {
            System.out.println("PRODUTO SELECIONADO: " + produto.getNome());
            // adiciona no carrinho
            new AlertDialog.Builder(getContext())
                    .setTitle("Adicionar ao carrinho?")
                    .setMessage("Adicionar " + produto.getNome() + " ao carrinho?")
                    .setPositiveButton(R.string.add, (dialog, which) -> adicionaNoCarrinho(produto))
                    .setNegativeButton(R.string.cancelar, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });
        layout.addView(convertView);
    }

    private void adicionaNoCarrinho(Produto produto) {
        Carrinho.getInstance().getProdutos().add(produto);
        btnCarrinho.setVisibility(View.VISIBLE);
    }
}
