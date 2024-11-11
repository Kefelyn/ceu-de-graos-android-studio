package com.example.ceudegraos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Configuracoes extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_configuracoes);

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

            // Botão editar dados
            Button btnEditarDados = findViewById(R.id.btnEditarDados);
            btnEditarDados.setOnClickListener(v -> {
                Intent intent = new Intent(Configuracoes.this, Perfil.class);
                startActivity(intent);
            });

            // Botão detalhes pediddos
            Button btnDetalhesPedido = findViewById(R.id.btnDetalhesPedido);
            btnDetalhesPedido.setOnClickListener(v -> {
                Intent intent = new Intent(Configuracoes.this, DetalhesPedidos.class);
                startActivity(intent);
            });

            // Botão Termos de Uso
            Button btnTermosUso = findViewById(R.id.btnTermosUso);
            btnTermosUso.setOnClickListener(v -> {
                Intent intent = new Intent(Configuracoes.this, TermosAvisos.class);
                startActivity(intent);
            });

            // Botão Política de Privacidade
            Button btnPoliticaPrivacidade = findViewById(R.id.btnPoliticaPrivacidade);
            btnPoliticaPrivacidade.setOnClickListener(v -> {
                Intent intent = new Intent(Configuracoes.this, TermosAvisos.class);
                startActivity(intent);
            });

            // Botão Entrar em Contato (envia e-mail)
            Button btnEntrarContato = findViewById(R.id.btnEntrarContato);
            btnEntrarContato.setOnClickListener(v -> {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ceudegraos@fazenda.com"));
                startActivity(Intent.createChooser(emailIntent, "Enviar e-mail"));
            });

            // Botão para sair da conta
            Button btnSair = findViewById(R.id.btnSair);
            btnSair.setOnClickListener(v -> {
                // Redireciona para a tela de login
                Intent intent = new Intent(Configuracoes.this, FormLogin.class);
                startActivity(intent);
                finish(); // Finaliza a Activity de perfil
            });

            // Definindo o listener para o BottomNavigationView
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.menu_produtos) {
                        // Quando o item "Produtos" for selecionado, navega para a TelaInicial
                        Intent intent = new Intent(Configuracoes.this, TelaInicial.class);
                        startActivity(intent);
                        finish(); // Finaliza a activity de configurações (opcional)
                        return true;
                    } else if (itemId == R.id.menu_configuracoes) {
                        // Se já estiver na tela de configurações, não faça nada
                        return true;
                    }
                    return false;
                }
            });
        }
}
