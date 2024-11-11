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

public class Pagamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        // Configurar a seta de voltar para retornar à tela anterior
        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> onBackPressed());

        // Configurar botão de copiar código Pix
        TextView codigoPix = findViewById(R.id.codigoPix);
        Button botaoCopiarPix = findViewById(R.id.botaoCopiarPix);

        botaoCopiarPix.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Pix Copia e Cola", codigoPix.getText().toString());
            clipboard.setPrimaryClip(clip);

// Aguardar 2 segundos antes de redirecionar para a tela de redefinição de senha
            new Handler().postDelayed(() -> {
                // Redirecionar para a tela de redefinição de senha
                Intent intent = new Intent(Pagamento.this, DetalhesPedidos.class);
                startActivity(intent);
                finish(); // Finaliza a Activity atual para evitar que o usuário retorne para esta
            }, 2000); // 2000 milissegundos = 2 segundos

        });
    }
}
