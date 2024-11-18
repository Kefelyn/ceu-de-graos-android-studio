package com.example.ceudegraos;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class Pagamento extends AppCompatActivity {

    private List<Produto> produtosSelecionados; // Lista de produtos selecionados no pedido
    private int usuarioID = 1; // ID do usuário (ajuste conforme a lógica do login)
    private double valorTotal; // Valor total do pedido

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        // Configura a seta de voltar
        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> onBackPressed());

        // Configura o botão de copiar o código Pix
        TextView codigoPix = findViewById(R.id.codigoPix);
        Button botaoCopiarPix = findViewById(R.id.botaoCopiarPix);

        // Recebe os produtos selecionados e o valor total (passe pela Intent ao abrir esta tela)
        produtosSelecionados = (List<Produto>) getIntent().getSerializableExtra("produtosSelecionados");
        valorTotal = getIntent().getDoubleExtra("valorTotal", 0);

        botaoCopiarPix.setOnClickListener(v -> {
            // Copiar o código Pix para a área de transferência
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Pix Copia e Cola", codigoPix.getText().toString());
            clipboard.setPrimaryClip(clip);

            Toast.makeText(Pagamento.this, "Código Pix copiado!", Toast.LENGTH_SHORT).show();

            // Simula processamento do pagamento
            new Handler().postDelayed(() -> {
                // Após o "pagamento", registra o pedido no banco
                registrarPedido();
            }, 2000); // 2 segundos
        });
    }

    private void registrarPedido() {
        new Thread(() -> {
            try {
                // Conecta ao banco de dados
                Connection connection = DatabaseHelper.getConnection();

                // Insere o pedido na tabela de pedidos
                String sqlPedido = "INSERT INTO BDPedidos (UsuarioID, DataPedido, StatusID, ValorTotal) VALUES (?, GETDATE(), 1, ?)";
                PreparedStatement statementPedido = connection.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
                statementPedido.setInt(1, usuarioID);
                statementPedido.setDouble(2, valorTotal);
                statementPedido.executeUpdate();

                // Recupera o ID do pedido recém-criado
                int pedidoID = -1;
                var generatedKeys = statementPedido.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pedidoID = generatedKeys.getInt(1);
                }

                if (pedidoID != -1) {
                    // Insere os itens do pedido e atualiza o estoque
                    for (Produto produto : produtosSelecionados) {
                        // Insere o item na tabela ItensPedido
                        String sqlItem = "INSERT INTO BDItensPedido (PedidoID, ProdutoID, Quantidade, Preco) VALUES (?, ?, ?, ?)";
                        PreparedStatement statementItem = connection.prepareStatement(sqlItem);
                        statementItem.setInt(1, pedidoID);
                        statementItem.setInt(2, produto.getId()); // Adicione o ID no modelo Produto
                        statementItem.setInt(3, produto.getQuantidade()); // Adicione a quantidade no modelo Produto
                        statementItem.setDouble(4, produto.getPrecoPorUnidade()); // Preço por unidade
                        statementItem.executeUpdate();

                        // Atualiza o estoque na tabela de Produtos
                        String sqlEstoque = "UPDATE Produtos SET Estoque = Estoque - ? WHERE ProdutoID = ?";
                        PreparedStatement statementEstoque = connection.prepareStatement(sqlEstoque);
                        statementEstoque.setInt(1, produto.getQuantidade());
                        statementEstoque.setInt(2, produto.getId());
                        statementEstoque.executeUpdate();
                    }
                }

                connection.close();

                // Exibe mensagem de sucesso na UI thread
                runOnUiThread(() -> {
                    Toast.makeText(this, "Pedido registrado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Redireciona para a tela de Detalhes dos Pedidos
                    Intent intent = new Intent(Pagamento.this, DetalhesPedidos.class);
                    startActivity(intent);
                    finish();
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Erro ao registrar pedido: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
