package br.com.cardosobritzburger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.cardosobritzburger.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FloatingActionButton btnCarrinho;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RealmUtil.dataBaseInit(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnCarrinho = findViewById(R.id.carrinho);
        navView = findViewById(R.id.nav_view);

        navView.setOnClickListener(v -> {

        });

        btnCarrinho.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarrinhoActivity.class);
            startActivity(intent);
        });

        LinearLayout classicBurgersLayout = findViewById(R.id.classic_burger_layout);
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Kids", "Carne e queijo", "R$ 12,00", 12.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Bacon", "Bacon e queijo", "R$ 16,00", 16.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Carne", "Um bife de carne com queijo", "R$ 15,00", 15.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Dobro Carne", "Dois bifes de carne com queijo", "R$ 19,00", 19.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Calabresa", "Calabresa com queijo", "R$ 16,00", 16.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Frango", "Um bife de Frango com queijo", "R$ 17,00", 17.00));
        insereProdutoNoLayout(classicBurgersLayout, new Produto("Dobro Frango", "Dois bifes de Frango com queijo", "R$ 23,00", 23.00));

        LinearLayout specialBurgersLayout = findViewById(R.id.special_burgers_layout);
        insereProdutoNoLayout(specialBurgersLayout, new Produto("Burger Chocolate", "Recheio de chocolate preto", "R$ 16,00", 16.00));
        insereProdutoNoLayout(specialBurgersLayout, new Produto("Burger Chocolate Branco", "Recheio de chocolate branco", "R$ 16,00", 16.00));

        LinearLayout drinksLayout = findViewById(R.id.drinks_layout);
        insereProdutoNoLayout(drinksLayout, new Produto("Refri 2 Litros", "Coca-Cola, Sprite ou Pepsi", "R$ 10,00", 10.00));
        insereProdutoNoLayout(drinksLayout, new Produto("Refri 600ml", "Coca-Cola, Sprite ou Pepsi", "R$ 6,00", 6.00));
        insereProdutoNoLayout(drinksLayout, new Produto("Suco Del Valle - Lata", "Laranja ou Uva", "R$ 8,00", 8.00));


    }

    private void insereProdutoNoLayout(LinearLayout layout, Produto produto) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View convertView;
        ViewHolder holder;
        convertView = layoutInflater.inflate(R.layout.list_buttons_style, null);
        holder = new ViewHolder();
        holder.nome = (TextView) convertView.findViewById(R.id.nome_produto);
        holder.descricao = (TextView) convertView.findViewById(R.id.descricao_produto);
        holder.valor = (TextView) convertView.findViewById(R.id.text_valor);
        holder.nome.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
        holder.valor.setText(produto.getTextoValor());
        convertView.setClickable(true);
        convertView.setTag(holder);
        convertView.setOnClickListener(v -> {
            System.out.println("PRODUTO SELECIONADO: " + produto.getNome());
            // adiciona no carrinho
            new AlertDialog.Builder(this)
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