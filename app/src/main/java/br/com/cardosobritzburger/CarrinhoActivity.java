package br.com.cardosobritzburger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import io.realm.RealmList;

public class CarrinhoActivity extends AppCompatActivity {

    private FloatingActionButton btnCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        setTitle("Carrinho");

        btnCarrinho = findViewById(R.id.carrinho);

        LinearLayout carrinhoLinearLayout = findViewById(R.id.carrinho_linear_layout);
        for(Produto produto : Carrinho.getInstance().getProdutos()){
            insereProdutoNoLayout(carrinhoLinearLayout, produto);
        }

        Button limparBtn = findViewById(R.id.limpar_pedido_btn);
        limparBtn.setOnClickListener(v -> {
            Carrinho.getInstance().getProdutos().clear();
            new AlertDialog.Builder(this)
                    .setTitle("Carrinho limpo!")
                    .setMessage("Todos os produtos do carrinho foram removidos!")
                    .setPositiveButton(R.string.ok, null)
                    .setOnDismissListener(dialog -> {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });

        Button finalizarBtn = findViewById(R.id.finalizar_pedido_btn);
        finalizarBtn.setOnClickListener(v -> {
            EditText enderecoText = findViewById(R.id.endereco_entrega);
            if(enderecoText.getText() == null || enderecoText.getText().toString().isEmpty()){
                new AlertDialog.Builder(this)
                        .setTitle("Atenção!")
                        .setMessage("É necessário preencher o campo de endereço!")
                        .setPositiveButton(R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                criaSalvaPedido(enderecoText.getText().toString());
                new AlertDialog.Builder(this)
                        .setTitle("Pedido realizado!")
                        .setMessage("O seu pedido foi realizado com sucesso!")
                        .setPositiveButton(R.string.ok, null)
                        .setOnDismissListener(dialog -> {
                            Carrinho.getInstance().getProdutos().clear();
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    private void criaSalvaPedido(String endereco){
        Pedido pedido = new Pedido();
        RealmList realmList = new RealmList<>();
        realmList.addAll(Carrinho.getInstance().getProdutos());
        pedido.setProdutos(realmList);
        pedido.setData((new Date()).toString());
        pedido.setEndereco(endereco);
        RealmUtil.insert(pedido);
    }

    private void insereProdutoNoLayout(LinearLayout layout, Produto produto) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View convertView;
        ViewHolder holder;
        convertView = layoutInflater.inflate(R.layout.list_buttons_style_carrinho, null);
        holder = new ViewHolder();
        holder.nome = convertView.findViewById(R.id.nome_produto);
        holder.valor = convertView.findViewById(R.id.text_valor);
        holder.nome.setText(produto.getNome());
        holder.valor.setText(produto.getTextoValor());
        convertView.setClickable(true);
        convertView.setTag(holder);
        convertView.setOnClickListener(v -> {
            // remove do carrinho
            new AlertDialog.Builder(this)
                    .setTitle("Remover do carrinho?")
                    .setMessage("Remover " + produto.getNome() + " do carrinho?")
                    .setPositiveButton(R.string.remover, (dialog, which) -> {
                        Carrinho.getInstance().getProdutos().remove(produto);
                        if(Carrinho.getInstance().getProdutos().isEmpty()){
                            btnCarrinho.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            recreate();
                        }
                    })
                    .setNegativeButton(R.string.cancelar, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });
        layout.addView(convertView);
    }
}