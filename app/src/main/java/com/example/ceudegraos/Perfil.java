package com.example.ceudegraos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {

    private EditText editNome, editEmail, editCpfCnpj, editEndereco, editSenha;
    private Button btnEditarSalvar;
    private SharedPreferences sharedPreferences;
    private boolean isEditing = false;  // Indica se está em modo de edição

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Configurar a seta de voltar para retornar à tela anterior
        ImageView setaVoltar = findViewById(R.id.setaVoltar);
        setaVoltar.setOnClickListener(v -> onBackPressed());

        // Inicializa os componentes
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editCpfCnpj = findViewById(R.id.editCpfCnpj);
        editEndereco = findViewById(R.id.editEndereco);
        editSenha = findViewById(R.id.editSenha);
        btnEditarSalvar = findViewById(R.id.btnEditarSalvar);

        // Carrega os dados do SharedPreferences
        sharedPreferences = getSharedPreferences("UsuarioPrefs", MODE_PRIVATE);
        carregarDados();

        // Configura o botão para alternar entre editar e salvar
        btnEditarSalvar.setOnClickListener(v -> {
            if (isEditing) {
                salvarAlteracoes();
            } else {
                habilitarEdicao();
            }
        });
    }

    private void carregarDados() {
        // Carrega os dados e exibe nos campos
        editNome.setText(sharedPreferences.getString("nome", ""));
        editEmail.setText(sharedPreferences.getString("email", ""));
        editCpfCnpj.setText(sharedPreferences.getString("cpf_cnpj", ""));
        editEndereco.setText(sharedPreferences.getString("endereco", ""));
        editSenha.setText(sharedPreferences.getString("senha", ""));
    }

    private void habilitarEdicao() {
        // Torna os campos editáveis e altera o botão para "Salvar Alterações"
        editNome.setEnabled(true);
        editEmail.setEnabled(true);
        editCpfCnpj.setEnabled(true);
        editEndereco.setEnabled(true);
        editSenha.setEnabled(true);
        btnEditarSalvar.setText("Salvar Alterações");
        isEditing = true;
    }

    private void salvarAlteracoes() {
        // Salva os dados e desabilita a edição
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nome", editNome.getText().toString());
        editor.putString("email", editEmail.getText().toString());
        editor.putString("cpf_cnpj", editCpfCnpj.getText().toString());
        editor.putString("endereco", editEndereco.getText().toString());
        editor.putString("senha", editSenha.getText().toString());
        editor.apply();

        Toast.makeText(this, "Alterações salvas com sucesso!", Toast.LENGTH_SHORT).show();

        // Desabilita edição e altera o botão de volta para "Editar Dados"
        editNome.setEnabled(false);
        editEmail.setEnabled(false);
        editCpfCnpj.setEnabled(false);
        editEndereco.setEnabled(false);
        editSenha.setEnabled(false);
        btnEditarSalvar.setText("Editar Dados");
        isEditing = false;
    }
}
