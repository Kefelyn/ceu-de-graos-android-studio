package com.example.ceudegraos;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.Button;


public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder> {

    private List<Produto> listaDeProdutos;
    private Button btnComprar;

    public ProdutosAdapter(List<Produto> listaDeProdutos, Button btnComprar) {
        this.listaDeProdutos = listaDeProdutos;
        this.btnComprar = btnComprar;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_produtos, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = listaDeProdutos.get(position);
        holder.nomeProduto.setText(produto.getNome());

        holder.radioGroupPesos.setOnCheckedChangeListener((group, checkedId) -> holder.atualizarPrecoTotal());

        holder.quantidadeProduto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                holder.atualizarPrecoTotal();
            }
        });

        holder.atualizarPrecoTotal();

        // Habilitar o botão Comprar quando ao menos um produto for selecionado
        btnComprar.setVisibility(View.VISIBLE);
        btnComprar.setEnabled(true);
    }

    @Override
    public int getItemCount() {
        return listaDeProdutos.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        TextView nomeProduto, precoTotalProduto;
        RadioGroup radioGroupPesos;
        RadioButton radio200g, radio500g;
        EditText quantidadeProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoTotalProduto = itemView.findViewById(R.id.precoTotalProduto);
            radioGroupPesos = itemView.findViewById(R.id.pesos);
            radio200g = itemView.findViewById(R.id.peso200g);
            radio500g = itemView.findViewById(R.id.peso500g);
            quantidadeProduto = itemView.findViewById(R.id.quantidadeProduto);
        }

        public void atualizarPrecoTotal() {
            int quantidade = Integer.parseInt(quantidadeProduto.getText().toString());
            int precoPorUnidade = radio200g.isChecked() ? 4 : 10;
            int precoTotal = quantidade * precoPorUnidade;
            precoTotalProduto.setText("Preço: R$ " + precoTotal);
        }
    }
}
