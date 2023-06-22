package br.com.cardosobritzburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CarrinhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        Intent intent = getIntent();
        Carrinho carrinho = (Carrinho) intent.getSerializableExtra("carrinho");

        System.out.println("Produtos no carrinho:");
        for(Produto produto : carrinho.getProdutos()){
            System.out.println(produto.getNome());
        }

    }
}