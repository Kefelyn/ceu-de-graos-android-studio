package com.example.ceudegraos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.widget.Button;

public class tela_inicial extends AppCompatActivity {

        private RecyclerView recyclerViewProdutos;
        private ProdutosAdapter produtosAdapter;
        private List<Produto> listaDeProdutos;
        private Button btnComprar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tela_inicial);

            recyclerViewProdutos = findViewById(R.id.listaProdutos);
            btnComprar = findViewById(R.id.btnComprar);

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
        }
    }
