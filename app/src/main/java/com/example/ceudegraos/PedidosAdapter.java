package com.example.ceudegraos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidoViewHolder> {

    private List<Pedidos> listaDePedidos;
    private Context context;

    public PedidosAdapter(List<Pedidos> listaDePedidos, Context context) {
        this.listaDePedidos = listaDePedidos;
        this.context = context;  // Guardando o contexto
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_pedidos, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedidos pedido = listaDePedidos.get(position);
        holder.nomePedido.setText(pedido.getNomeProduto());
        holder.statusPedido.setText("Status: " + pedido.getStatus());

        // Verifica se o pedido está finalizado para mostrar o botão "Avaliar"
        if (pedido.isFinalizado()) {
            holder.btnAvaliarPedido.setVisibility(View.VISIBLE);
            holder.btnAvaliarPedido.setOnClickListener(v -> {
                // Usa o contexto da Activity para iniciar o envio do e-mail
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ceudegraos@fazenda.com"));
                context.startActivity(Intent.createChooser(emailIntent, "Enviar e-mail"));
            });
        } else {
            holder.btnAvaliarPedido.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaDePedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView nomePedido, statusPedido;
        Button btnAvaliarPedido;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePedido = itemView.findViewById(R.id.nomePedido);
            statusPedido = itemView.findViewById(R.id.statusPedido);
            btnAvaliarPedido = itemView.findViewById(R.id.btnAvaliarPedido);
        }
    }
}
