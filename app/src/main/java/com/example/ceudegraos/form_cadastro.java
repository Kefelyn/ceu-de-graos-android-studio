package com.example.ceudegraos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class form_cadastro extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoCpfCnpj, campoEndereco, campoSenha;
    private Button btnCadastrar;
    private TextView acessarConta, lgpdTermosAvisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro2);

        // Inicializando os componentes
        campoNome = findViewById(R.id.campoNome);
        campoEmail = findViewById(R.id.campoEmailCadastro);
        campoCpfCnpj = findViewById(R.id.campoCpfCnpj);
        campoEndereco = findViewById(R.id.campoEndereco);
        campoSenha = findViewById(R.id.campoSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastro);
        acessarConta = findViewById(R.id.acessarConta);
        lgpdTermosAvisos = findViewById(R.id.lgpdTermosAvisos);

        // Ação para o botão de "Cadastrar"
        btnCadastrar.setOnClickListener(v -> {
            String nome = campoNome.getText().toString().trim();
            String email = campoEmail.getText().toString().trim();
            String cpfCnpj = campoCpfCnpj.getText().toString().trim();
            String endereco = campoEndereco.getText().toString().trim();
            String senha = campoSenha.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || cpfCnpj.isEmpty() || endereco.isEmpty() || senha.isEmpty()) {
                Toast.makeText(form_cadastro.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            } else {
                // Lógica de cadastro (exemplo)
                realizarCadastro(nome, email, cpfCnpj, endereco, senha);
            }
        });

        // Redirecionar para a tela de login
        acessarConta.setOnClickListener(v -> {
            Intent intent = new Intent(form_cadastro.this, form_login.class);
            startActivity(intent);
            finish();
        });

        // Termos de uso e Aviso de Privacidade
        lgpdTermosAvisos.setOnClickListener(v -> {
            // Redirecionar para uma Activity que exibe os Termos de Uso e o Aviso de Privacidade
            Intent intent = new Intent(form_cadastro.this, termos_avisos.class);
            startActivity(intent);
        });
    }

    private void realizarCadastro(String nome, String email, String cpfCnpj, String endereco, String senha) {
        // Aqui você pode adicionar a lógica para salvar o usuário no banco de dados ou autenticação com Firebase.
        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
        // Após o cadastro, você pode redirecionar o usuário para a tela de login ou a tela principal
        Intent intent = new Intent(form_cadastro.this, form_login.class);
        startActivity(intent);
        finish();
    }
}
