package com.example.ceudegraos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FormCadastro extends AppCompatActivity {

    // Declaração dos componentes da interface
    private EditText campoNome, campoEmail, campoCpfCnpj, campoEndereco, campoSenha;
    private Button btnCadastrar;
    private TextView acessarConta, lgpdTermosAvisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro2);

        // Inicializando os componentes da interface com os IDs do layout
        campoNome = findViewById(R.id.campoNome);
        campoEmail = findViewById(R.id.campoEmailCadastro);
        campoCpfCnpj = findViewById(R.id.campoCpfCnpj);
        campoEndereco = findViewById(R.id.campoEndereco);
        campoSenha = findViewById(R.id.campoSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastro);
        acessarConta = findViewById(R.id.acessarConta);
        lgpdTermosAvisos = findViewById(R.id.lgpdTermosAvisos);

        // Configuração do botão "Cadastrar"
        btnCadastrar.setOnClickListener(v -> {
            // Obtém os valores dos campos preenchidos
            String nome = campoNome.getText().toString().trim();
            String email = campoEmail.getText().toString().trim();
            String cpfCnpj = campoCpfCnpj.getText().toString().trim();
            String endereco = campoEndereco.getText().toString().trim();
            String senha = campoSenha.getText().toString().trim();

            // Valida os dados antes de enviar para o banco de dados
            if (validarDados(nome, email, cpfCnpj, endereco, senha)) {
                realizarCadastro(nome, email, cpfCnpj, endereco, senha);
            }
        });

        // Configuração do texto "Já tem uma conta? Acessar conta"
        acessarConta.setOnClickListener(v -> {
            Intent intent = new Intent(FormCadastro.this, FormLogin.class);
            startActivity(intent);
            finish();
        });

        // Configuração do texto "Termos de Uso e Aviso de Privacidade"
        lgpdTermosAvisos.setOnClickListener(v -> {
            Intent intent = new Intent(FormCadastro.this, TermosAvisos.class);
            startActivity(intent);
        });
    }

    // Método para validar os dados preenchidos pelo usuário
    private boolean validarDados(String nome, String email, String cpfCnpj, String endereco, String senha) {
        // Verifica se algum campo está vazio
        if (nome.isEmpty() || email.isEmpty() || cpfCnpj.isEmpty() || endereco.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Verifica se o e-mail possui um formato válido
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.contains("@") || !email.split("@")[1].contains(".")) {
            Toast.makeText(this, "Email inválido.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Verifica se o CPF possui exatamente 11 dígitos ou o CNPJ possui 14 dígitos
        if (cpfCnpj.length() == 11 && cpfCnpj.matches("\\d+")) {
            // CPF válido
        } else if (cpfCnpj.length() == 14 && cpfCnpj.matches("\\d+")) {
            // CNPJ válido
        } else {
            Toast.makeText(this, "CPF/CNPJ inválido. Certifique-se de usar apenas números.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Verifica se a senha possui pelo menos 6 caracteres
        if (senha.length() < 6) {
            Toast.makeText(this, "A senha deve ter pelo menos 6 caracteres.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // Método para realizar o cadastro no banco de dados
    private void realizarCadastro(String nome, String email, String cpfCnpj, String endereco, String senha) {
        new Thread(() -> {
            try {
                // Obtém uma conexão com o banco de dados
                Connection connection = DatabaseHelper.getConnection();

                // Define o tipo de usuário com base no CPF/CNPJ
                String tipoUsuario = (cpfCnpj.length() == 11) ? "Pessoa Física" : "Pessoa Jurídica";

                // Query para inserir o usuário no banco de dados
                String sql = "INSERT INTO BDusuario (Nome, Email, CPF_CNPJ, Senha, DataCadastro, Endereco, TipoUsuario, Ativo) " +
                        "VALUES (?, ?, ?, ?, GETDATE(), ?, ?, 1)";

                // Prepara a instrução SQL
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nome);
                statement.setString(2, email);
                statement.setString(3, cpfCnpj);
                statement.setString(4, senha);
                statement.setString(5, endereco);
                statement.setString(6, tipoUsuario);

                // Executa a instrução e obtém o número de linhas afetadas
                int rowsInserted = statement.executeUpdate();
                connection.close();

                // Verifica se o cadastro foi realizado com sucesso
                runOnUiThread(() -> {
                    if (rowsInserted > 0) {
                        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FormCadastro.this, TelaInicial.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Erro ao cadastrar o usuário.", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e) {
                // Trata erros de conexão ou SQL
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Erro ao conectar ao banco de dados: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
