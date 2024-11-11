package com.example.ceudegraos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;
    private ProdutosAdapter produtosAdapter;
    private List<Produto> listaDeProdutos;
    private Button btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        // Inicializa o RecyclerView e o botão de compra
        recyclerViewProdutos = findViewById(R.id.listaProdutos);
        btnComprar = findViewById(R.id.btnComprar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configurando o RecyclerView
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));

        // Populando a lista de produtos
        listaDeProdutos = new ArrayList<>();
        listaDeProdutos.add(new Produto("Feijão"));
        listaDeProdutos.add(new Produto("Grão-de-bico"));
        listaDeProdutos.add(new Produto("Ervilha"));
        listaDeProdutos.add(new Produto("Lentilha"));

        // Configurando o adapter
        produtosAdapter = new ProdutosAdapter(listaDeProdutos, btnComprar);
        recyclerViewProdutos.setAdapter(produtosAdapter);

        // Ação do botão de compra
        btnComprar.setOnClickListener(v -> {
            Intent intent = new Intent(TelaInicial.this, Pagamento.class);
            startActivity(intent);
        });

        // Definindo o listener para o BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_produtos) {
                    recyclerViewProdutos.setVisibility(RecyclerView.VISIBLE);
                    return true;
                } else if (itemId == R.id.menu_configuracoes) {
                    recyclerViewProdutos.setVisibility(RecyclerView.GONE);
                    Intent intent = new Intent(TelaInicial.this, Configuracoes.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


    }
}
