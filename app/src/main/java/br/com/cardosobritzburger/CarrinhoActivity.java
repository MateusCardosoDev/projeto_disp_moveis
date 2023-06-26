package br.com.cardosobritzburger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import io.realm.RealmList;

public class CarrinhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        setTitle("Carrinho");

        LinearLayout carrinhoLinearLayout = findViewById(R.id.carrinho_linear_layout);
        for(Produto produto : Carrinho.getInstance().getProdutos()){
            insereProdutoNoLayout(carrinhoLinearLayout, produto);
        }

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
            }
        });

    }

    private void criaSalvaPedido(String endereco){
        Pedido pedido = new Pedido();
        RealmList realmList = new RealmList<>(Carrinho.getInstance().getProdutos());
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
        holder.nome = (TextView) convertView.findViewById(R.id.nome_produto);
        holder.valor = (TextView) convertView.findViewById(R.id.text_valor);
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
                        recreate();
                    })
                    .setNegativeButton(R.string.cancelar, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });
        layout.addView(convertView);
    }
}