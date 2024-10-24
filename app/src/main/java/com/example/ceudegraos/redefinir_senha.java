package com.example.ceudegraos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class redefinir_senha extends AppCompatActivity {

    private EditText campoNovaSenha, campoConfirmarSenha;
    private Button btnRedefinirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        // Inicializando os componentes
        campoNovaSenha = findViewById(R.id.campoNovaSenha);
        campoConfirmarSenha = findViewById(R.id.campoConfirmarSenha);
        btnRedefinirSenha = findViewById(R.id.btnRedefinirSenha);

        // Ação do botão "Redefinir Senha"
        btnRedefinirSenha.setOnClickListener(v -> {
            String novaSenha = campoNovaSenha.getText().toString().trim();
            String confirmarSenha = campoConfirmarSenha.getText().toString().trim();

            if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
                Toast.makeText(redefinir_senha.this, "Por favor, preencha ambos os campos.", Toast.LENGTH_SHORT).show();
            } else if (!novaSenha.equals(confirmarSenha)) {
                Toast.makeText(redefinir_senha.this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
            } else {
                // Exibe a mensagem de sucesso
                Toast.makeText(redefinir_senha.this, "A senha foi redefinida com sucesso!", Toast.LENGTH_SHORT).show();

                // Redirecionar para a tela inicial de produtos
                Intent intent = new Intent(redefinir_senha.this, tela_inicial.class);
                startActivity(intent);
                finish(); // Finaliza a Activity atual
            }
        });
    }
}
