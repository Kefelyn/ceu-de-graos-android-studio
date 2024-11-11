package com.example.ceudegraos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DetalhesPedidos extends AppCompatActivity {

    private RecyclerView recyclerViewPedidos;
    private PedidosAdapter pedidosAdapter;
    private List<Pedidos> listaDePedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pedidos);

        // Configurar a seta de voltar para retornar à tela anterior
        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> onBackPressed());

        // Inicializar RecyclerView
        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));

        // Simulação de dados de pedidos
        listaDePedidos = new ArrayList<>();
        listaDePedidos.add(new Pedidos("Feijão", "Em andamento"));
        listaDePedidos.add(new Pedidos("Grão-de-bico", "Finalizado"));
        listaDePedidos.add(new Pedidos("Ervilha", "Finalizado"));
        listaDePedidos.add(new Pedidos("Lentilha", "Em andamento"));

        // Configurar o adapter com a lista de pedidos
        PedidosAdapter pedidosAdapter = new PedidosAdapter(listaDePedidos, this); // Passando o contexto 'this'
        recyclerViewPedidos.setAdapter(pedidosAdapter);

    }
}
