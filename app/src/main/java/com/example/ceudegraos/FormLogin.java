package com.example.ceudegraos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FormLogin extends AppCompatActivity {

    // Declaração dos componentes da interface
    private EditText campoEmail, campoSenha;
    private CheckBox lembrarSenha;
    private Button btnLogin, btnCadastrar;
    private TextView esqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        // Inicializando os componentes da interface com os IDs do layout
        campoEmail = findViewById(R.id.Email);
        campoSenha = findViewById(R.id.Senha);
        lembrarSenha = findViewById(R.id.LembrarSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        esqueciSenha = findViewById(R.id.EsqueciSenha);

        // Ação do botão de login
        btnLogin.setOnClickListener(v -> {
            String email = campoEmail.getText().toString().trim();
            String senha = campoSenha.getText().toString().trim();

            // Verifica se os campos estão preenchidos
            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(FormLogin.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                // Chama o método de login
                realizarLogin(email, senha);
            }
        });

        // Ação para o botão de cadastro
        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(FormLogin.this, FormCadastro.class);
            startActivity(intent);
        });

        // Ação para o texto "Esqueci a Senha"
        esqueciSenha.setOnClickListener(v -> {
            Intent intent = new Intent(FormLogin.this, EsqueciSenha.class);
            startActivity(intent);
        });
    }

    // Método para realizar o login
    private void realizarLogin(String email, String senha) {
        new Thread(() -> {
            try {
                // Obtém a conexão com o banco de dados
                Connection connection = DatabaseHelper.getConnection();

                // Query SQL para validar o email e senha
                String sql = "SELECT * FROM BDusuario WHERE Email = ? AND Senha = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, email); // Define o email na query
                statement.setString(2, senha); // Define a senha na query

                // Executa a consulta
                ResultSet resultSet = statement.executeQuery();

                // Verifica se o resultado contém dados (login válido)
                if (resultSet.next()) {
                    // Login realizado com sucesso
                    runOnUiThread(() -> {
                        Toast.makeText(FormLogin.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        // Navega para a tela inicial
                        Intent intent = new Intent(FormLogin.this, TelaInicial.class);
                        startActivity(intent);
                        finish(); // Fecha a tela de login
                    });
                } else {
                    // Login inválido
                    runOnUiThread(() -> Toast.makeText(FormLogin.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show());
                }

                // Fecha a conexão
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                // Exibe uma mensagem de erro caso ocorra falha na conexão ou execução
                runOnUiThread(() -> Toast.makeText(FormLogin.this, "Erro ao conectar ao banco de dados: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
