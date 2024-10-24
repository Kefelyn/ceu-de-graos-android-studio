package com.example.ceudegraos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.view.View;

public class esqueci_senha extends AppCompatActivity {

    private EditText campoEmailRecuperacao;
    private Button btnEnviarLink;
    private TextView linkEnviado, acessarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        // Inicializando os componentes
        campoEmailRecuperacao = findViewById(R.id.campoEmailRecuperacao);
        btnEnviarLink = findViewById(R.id.btnEnviarLink);
        linkEnviado = findViewById(R.id.linkEnviado);
        acessarConta = findViewById(R.id.acessarConta);

        // Ação do botão "Enviar link"
        btnEnviarLink.setOnClickListener(v -> {
            String email = campoEmailRecuperacao.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(esqueci_senha.this, "Por favor, insira o seu e-mail", Toast.LENGTH_SHORT).show();
            } else {
                // Exibe a mensagem de "link enviado"
                linkEnviado.setVisibility(View.VISIBLE);

                // Aguardar 2 segundos antes de redirecionar para a tela de redefinição de senha
                new Handler().postDelayed(() -> {
                    // Redirecionar para a tela de redefinição de senha
                    Intent intent = new Intent(esqueci_senha.this, redefinir_senha.class);
                    startActivity(intent);
                    finish(); // Finaliza a Activity atual para evitar que o usuário retorne para esta
                }, 2000); // 2000 milissegundos = 2 segundos
            }
        });

        // Ação do texto "Acessar conta"
        acessarConta.setOnClickListener(v -> {
            // Redirecionar para a tela de login
            Intent intent = new Intent(esqueci_senha.this, form_login.class);
            startActivity(intent);
            finish(); // Finaliza a Activity atual
        });
    }
}
