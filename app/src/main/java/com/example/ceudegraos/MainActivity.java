package com.example.ceudegraos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // exibe a tela de loading por 2 segundos e depois vai para a tela de login
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // redireciona para a tela de login
                    Intent intent = new Intent(MainActivity.this, FormLogin.class);
                    startActivity(intent);
                    finish(); // fecha a tela de loading (MainActivity)
                }
            }, 2000); // 2000 milissegundos = 2 segundos
        }
    }
