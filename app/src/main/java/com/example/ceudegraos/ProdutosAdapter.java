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

//Adapter para gerenciar a exibição dos produtos no RecyclerView.
public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder> {

    // Lista de produtos que será exibida no RecyclerView
    private List<Produto> listaDeProdutos;

    //Construtor do Adapter.
    public ProdutosAdapter(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item do produto
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_produtos, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        // Obtém o produto da posição atual
        Produto produto = listaDeProdutos.get(position);

        // Define o nome do produto no TextView
        holder.nomeProduto.setText(produto.getNome());

        // Configura um listener para atualizar o preço total ao alterar o peso selecionado
        holder.radioGroupPesos.setOnCheckedChangeListener((group, checkedId) -> holder.atualizarPrecoTotal(produto));

        // Configura um TextWatcher para atualizar o preço total ao alterar a quantidade
        holder.quantidadeProduto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                holder.atualizarPrecoTotal(produto);
            }
        });

        // Atualiza o preço total pela primeira vez
        holder.atualizarPrecoTotal(produto);
    }

    @Override
    public int getItemCount() {
        // Retorna o tamanho da lista de produtos
        return listaDeProdutos.size();
    }

    //ViewHolder que gerencia os elementos de um item do RecyclerView.
    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        // Elementos da interface do item
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

        //Método para atualizar o preço total com base no peso e quantidade.
        public void atualizarPrecoTotal(Produto produto) {
            try {
                // Obtém a quantidade inserida
                int quantidade = Integer.parseInt(quantidadeProduto.getText().toString());
                produto.setQuantidade(quantidade);

                // Define o preço por unidade com base no peso selecionado
                double precoPorUnidade = radio200g.isChecked() ? produto.getPreco200g() : produto.getPreco500g();
                produto.setPrecoPorUnidade(precoPorUnidade);

                // Calcula o preço total
                double precoTotal = quantidade * precoPorUnidade;
                precoTotalProduto.setText("Preço: R$ " + precoTotal);
            } catch (NumberFormatException e) {
                // Mostra R$ 0.00 se a quantidade não for um número válido
                precoTotalProduto.setText("Preço: R$ 0.00");
            }
        }
    }
}
