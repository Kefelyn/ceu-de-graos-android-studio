package com.example.ceudegraos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RedefinirSenha extends AppCompatActivity {

    // Declaração dos componentes de interface
    private EditText campoNovaSenha, campoConfirmarSenha;
    private Button btnRedefinirSenha;

    // Variável para armazenar o email do usuário, recebido da tela anterior
    private String emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        // Inicializando os componentes da interface
        campoNovaSenha = findViewById(R.id.campoNovaSenha);
        campoConfirmarSenha = findViewById(R.id.campoConfirmarSenha);
        btnRedefinirSenha = findViewById(R.id.btnRedefinirSenha);

        // Obtém o email do usuário passado pela Intent
        emailUsuario = getIntent().getStringExtra("emailUsuario");

        // Valida se o email foi recebido corretamente
        if (emailUsuario == null || emailUsuario.isEmpty()) {
            Toast.makeText(this, "Erro ao recuperar o email do usuário.", Toast.LENGTH_SHORT).show();
            finish(); // Fecha a tela caso o email não seja válido
            return;
        }

        // Configuração do botão "Redefinir Senha"
        btnRedefinirSenha.setOnClickListener(v -> {
            String novaSenha = campoNovaSenha.getText().toString().trim();
            String confirmarSenha = campoConfirmarSenha.getText().toString().trim();

            // Valida os campos antes de atualizar a senha
            if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
                Toast.makeText(RedefinirSenha.this, "Por favor, preencha ambos os campos.", Toast.LENGTH_SHORT).show();
            } else if (!novaSenha.equals(confirmarSenha)) {
                Toast.makeText(RedefinirSenha.this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
            } else if (novaSenha.length() < 6) {
                Toast.makeText(RedefinirSenha.this, "A senha deve ter no mínimo 6 caracteres.", Toast.LENGTH_SHORT).show();
            } else {
                // Atualiza a senha no banco de dados
                redefinirSenhaNoBanco(novaSenha);
            }
        });
    }

    // Método para atualizar a senha no banco de dados
    private void redefinirSenhaNoBanco(String novaSenha) {
        new Thread(() -> {
            try {
                // Obtém a conexão com o banco de dados
                Connection connection = DatabaseHelper.getConnection();

                // Query para atualizar a senha do usuário
                String sql = "UPDATE BDusuario SET Senha = ? WHERE Email = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, novaSenha);
                statement.setString(2, emailUsuario);

                // Executa a atualização
                int rowsUpdated = statement.executeUpdate();
                connection.close();

                // Verifica se a atualização foi bem-sucedida
                runOnUiThread(() -> {
                    if (rowsUpdated > 0) {
                        Toast.makeText(RedefinirSenha.this, "Senha redefinida com sucesso!", Toast.LENGTH_SHORT).show();
                        // Redireciona para a tela de login após redefinir a senha
                        Intent intent = new Intent(RedefinirSenha.this, FormLogin.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RedefinirSenha.this, "Erro ao redefinir a senha.", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(RedefinirSenha.this, "Erro ao conectar ao banco de dados: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
