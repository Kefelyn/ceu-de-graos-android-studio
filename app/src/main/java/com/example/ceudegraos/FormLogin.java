package com.example.ceudegraos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormLogin extends AppCompatActivity {

    private EditText email, senha;
    private CheckBox lembrarSenha;
    private Button btnLogin, btnCadastrar;
    private TextView esqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        // Inicializando os componentes
        email = findViewById(R.id.Email);
        senha = findViewById(R.id.Senha);
        lembrarSenha = findViewById(R.id.LembrarSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        esqueciSenha = findViewById(R.id.EsqueciSenha);

        Button loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela inicial após login
                Intent intent = new Intent(FormLogin.this, TelaInicial.class);
                startActivity(intent);
                finish(); // Fechar a tela de login
            }
        });

        // Ação para o botão de login
        btnLogin.setOnClickListener(v -> {
            String emailTexto = email.getText().toString().trim();
            String senhaTexto = senha.getText().toString().trim();

            if (emailTexto.isEmpty() || senhaTexto.isEmpty()) {
                Toast.makeText(FormLogin.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                // Lógica de autenticação
                realizarLogin(emailTexto, senhaTexto);
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

    private void realizarLogin(String email, String senha) {
        // Lógica de login: validar email e senha e navegar para a tela principal se estiver correto
        Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
        // Navegar para outra tela após sucesso (por exemplo, a tela principal do app)
        Intent intent = new Intent(FormLogin.this, TelaInicial.class);
        startActivity(intent);
        finish();
    }
}

