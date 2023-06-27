package br.com.cardosobritzburger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.cardosobritzburger.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RealmUtil.dataBaseInit(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentCardapio fragmentCardapio = new FragmentCardapio();
        FragmentPedidos fragmentPedidos = new FragmentPedidos();

        navView = findViewById(R.id.bottomNavigationView);

        navView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.navegacao_cardapio){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, fragmentCardapio)
                        .commit();
            } else if(item.getItemId() == R.id.navegacao_pedidos) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, fragmentPedidos)
                        .commit();
            }
            return true;
        });

        navView.setSelectedItemId(R.id.navegacao_cardapio);
    }

}