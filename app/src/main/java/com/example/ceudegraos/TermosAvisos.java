package com.example.ceudegraos;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TermosAvisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_avisos);

        // Configurar a seta de voltar para retornar à tela anterior
        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> onBackPressed());
    }
}
