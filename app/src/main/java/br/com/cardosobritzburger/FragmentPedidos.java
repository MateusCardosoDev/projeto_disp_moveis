package br.com.cardosobritzburger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPedidos extends Fragment {

    public FragmentPedidos(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        carregaPedidos();
    }

    private void carregaPedidos() {
        LinearLayout pedidosLayout = getView().findViewById(R.id.pedidos_layout);
        for(Pedido pedido: RealmUtil.getAll()){
            inserePedidoNoLayout(pedidosLayout, pedido);
        }
    }

    private void inserePedidoNoLayout(LinearLayout layout, Pedido pedido) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View convertView;
        ViewHolderPedidos holder;
        convertView = layoutInflater.inflate(R.layout.list_pedidos_style, null);
        holder = new ViewHolderPedidos();
        holder.id = convertView.findViewById(R.id.id_pedido);
        holder.produtos = convertView.findViewById(R.id.produtos_pedido);
        holder.data = convertView.findViewById(R.id.data_pedido);
        holder.endereco = convertView.findViewById(R.id.endereco_pedido);
        holder.total = convertView.findViewById(R.id.total_pedido);
        holder.id.setText("#"+pedido.getId());
        String produtos = "";
        for(int i = 0; i < pedido.getProdutos().size(); i++){
            produtos += pedido.getProdutos().get(i).toString() + "; ";
        }
        holder.produtos.setText("Produtos: "+produtos);
        holder.data.setText("Data: "+pedido.getData());
        holder.endereco.setText("Endereço: "+pedido.getEndereco());
        holder.total.setText("Total: "+pedido.getTotal());
        convertView.setClickable(false);
        convertView.setTag(holder);
        layout.addView(convertView);
    }
}
