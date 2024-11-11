package com.example.ceudegraos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

    public class AvaliacaoPedido extends AppCompatActivity {

        private RatingBar estrelaNota;
        private EditText campoFeedback;
        private TextView limiteCaracteres;
        private Button btnEnviarAvaliacao;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_avaliacao_pedido);  // Referencie o layout que você criou

            // Configurar a seta de voltar para retornar à tela anterior
            ImageView setaVoltar = findViewById(R.id.setaVoltar);
            setaVoltar.setOnClickListener(v -> onBackPressed());

            // Inicializar os componentes
            estrelaNota = findViewById(R.id.estrelaNota);
            campoFeedback = findViewById(R.id.campoFeedback);
            limiteCaracteres = findViewById(R.id.limiteCaracteres);
            btnEnviarAvaliacao = findViewById(R.id.btnEnviarAvaliacao);

            // Monitorar a quantidade de caracteres no EditText
            campoFeedback.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int after) {
                    int charsRemaining = 500 - charSequence.length();
                    limiteCaracteres.setText(charsRemaining + " caracteres restantes");

                    // Desabilitar o botão de enviar se ultrapassar o limite de caracteres
                    if (charsRemaining < 0) {
                        btnEnviarAvaliacao.setEnabled(false);
                    } else {
                        btnEnviarAvaliacao.setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {}
            });

            // Configurar o clique no botão de enviar
            btnEnviarAvaliacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Coletar os dados de avaliação
                    float rating = estrelaNota.getRating();  // Pega a nota do RatingBar
                    String feedback = campoFeedback.getText().toString();  // Pega o texto do EditText

                    // Verifique se a avaliação foi feita
                    if (rating == 0) {
                        // Alerta ou ação para avisar que o usuário precisa avaliar
                        Toast.makeText(AvaliacaoPedido.this, "Por favor, avalie o produto", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Aqui você pode enviar a avaliação para o servidor ou armazená-la
                    // Exemplo: enviarAvaliacãoParaServidor(rating, feedback);

                    Toast.makeText(AvaliacaoPedido.this, "Avaliação enviada com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AvaliacaoPedido.this, DetalhesPedidos.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }