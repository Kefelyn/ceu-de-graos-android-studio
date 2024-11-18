package com.example.ceudegraos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;
    private ProdutosAdapter produtosAdapter;
    private List<Produto> listaDeProdutos;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        // Inicializa componentes da interface
        recyclerViewProdutos = findViewById(R.id.listaProdutos);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configura o layout do RecyclerView
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));

        // Lista de produtos inicializada
        listaDeProdutos = new ArrayList<>();
        carregarProdutosDoBanco();

        // Configura o adapter para o RecyclerView
        produtosAdapter = new ProdutosAdapter(listaDeProdutos, findViewById(R.id.btnComprar));
        recyclerViewProdutos.setAdapter(produtosAdapter);

        // Configura navegação pelo BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_produtos) {
                recyclerViewProdutos.setVisibility(RecyclerView.VISIBLE);
                return true;
            } else if (item.getItemId() == R.id.menu_configuracoes) {
                Intent intent = new Intent(this, Configuracoes.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void carregarProdutosDoBanco() {
        new Thread(() -> {
            try {
                Connection connection = DatabaseHelper.getConnection();
                String query = "SELECT NomeProduto, Preco200g, Preco500g, Estoque FROM Produtos";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String nome = rs.getString("NomeProduto");
                    double preco200g = rs.getDouble("Preco200g");
                    double preco500g = rs.getDouble("Preco500g");
                    int estoque = rs.getInt("Estoque");
                    listaDeProdutos.add(new Produto(nome, preco200g, preco500g, estoque));
                }
                connection.close();

                runOnUiThread(() -> produtosAdapter.notifyDataSetChanged());
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(this, "Erro ao carregar produtos: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
