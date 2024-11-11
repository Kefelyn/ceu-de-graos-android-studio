package com.example.ceudegraos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FormCadastro extends AppCompatActivity {

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
                Toast.makeText(FormCadastro.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            } else {
                // Lógica de cadastro (exemplo)
                realizarCadastro(nome, email, cpfCnpj, endereco, senha);
            }
        });

        // Redirecionar para a tela inicial
        acessarConta.setOnClickListener(v -> {
            Intent intent = new Intent(FormCadastro.this, FormLogin.class);
            startActivity(intent);
            finish();
        });

        // Redireciona para a tela de termos e avisos
        lgpdTermosAvisos.setOnClickListener(v -> {
                Intent intent = new Intent(FormCadastro.this, TermosAvisos.class);
                startActivity(intent); // Não precisa fechar a tela de login aqui
        });
    }

    private void realizarCadastro(String nome, String email, String cpfCnpj, String endereco, String senha) {
        // Aqui você pode adicionar a lógica para salvar o usuário no banco de dados ou autenticação com Firebase.
        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
        // Após o cadastro, você pode redirecionar o usuário para a tela de login ou a tela principal
        Intent intent = new Intent(FormCadastro.this, TelaInicial.class);
        startActivity(intent);
        finish();
    }
}
