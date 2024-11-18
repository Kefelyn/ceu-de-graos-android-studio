package com.example.ceudegraos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EsqueciSenha extends AppCompatActivity {

    // Declaração dos componentes de interface
    private EditText campoEmailRecuperacao;
    private Button btnEnviarLink;
    private TextView linkEnviado, acessarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        // Inicializando os componentes da interface
        campoEmailRecuperacao = findViewById(R.id.campoEmailRecuperacao);
        btnEnviarLink = findViewById(R.id.btnEnviarLink);
        linkEnviado = findViewById(R.id.linkEnviado);
        acessarConta = findViewById(R.id.acessarConta);

        // Configuração do botão "Enviar link"
        btnEnviarLink.setOnClickListener(v -> {
            String email = campoEmailRecuperacao.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(EsqueciSenha.this, "Por favor, insira o seu e-mail.", Toast.LENGTH_SHORT).show();
            } else if (!validarEmail(email)) {
                Toast.makeText(EsqueciSenha.this, "Formato de e-mail inválido.", Toast.LENGTH_SHORT).show();
            } else {
                verificarEmailNoBanco(email);
            }
        });

        // Configuração do texto "Acessar conta"
        acessarConta.setOnClickListener(v -> {
            // Redirecionar para a tela de login
            Intent intent = new Intent(EsqueciSenha.this, FormLogin.class);
            startActivity(intent);
            finish(); // Finaliza a Activity atual
        });
    }

    // Método para validar o formato do email
    private boolean validarEmail(String email) {
        return email.contains("@") && email.split("@")[1].contains(".");
    }

    // Método para verificar se o email está cadastrado no banco de dados
    private void verificarEmailNoBanco(String email) {
        new Thread(() -> {
            try {
                // Obtém a conexão com o banco
                Connection connection = DatabaseHelper.getConnection();

                // Query para verificar a existência do email
                String sql = "SELECT Email FROM BDusuario WHERE Email = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, email);

                // Executa a consulta
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // Email encontrado, simula envio de link
                    runOnUiThread(() -> {
                        linkEnviado.setVisibility(View.VISIBLE);
                        Toast.makeText(EsqueciSenha.this, "Link de redefinição enviado!", Toast.LENGTH_SHORT).show();

                        // Redireciona para a tela de redefinição de senha após 2 segundos
                        new Handler().postDelayed(() -> {
                            Intent intent = new Intent(EsqueciSenha.this, RedefinirSenha.class);
                            startActivity(intent);
                            finish();
                        }, 2000);
                    });
                } else {
                    // Email não encontrado
                    runOnUiThread(() -> Toast.makeText(EsqueciSenha.this, "Email não cadastrado.", Toast.LENGTH_SHORT).show());
                }

                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(EsqueciSenha.this, "Erro ao conectar ao banco de dados: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
