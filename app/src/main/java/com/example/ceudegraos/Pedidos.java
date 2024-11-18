package com.example.ceudegraos;

/**
 * Classe representando os pedidos realizados pelos usuários.
 */
public class Pedidos {

    // ID do pedido no banco de dados
    private int pedidoID;

    // ID do produto relacionado ao pedido
    private int produtoID;

    // Nome do produto
    private String nomeProduto;

    // Status do pedido (ex.: Em andamento, Finalizado)
    private String status;

    //Construtor para inicializar os atributos do pedido.
    public Pedidos(int pedidoID, int produtoID, String nomeProduto, String status) {
        this.pedidoID = pedidoID;
        this.produtoID = produtoID;
        this.nomeProduto = nomeProduto;
        this.status = status;
    }

    // Método para obter o ID do pedido
    public int getPedidoID() {
        return pedidoID;
    }

    // Método para obter o ID do produto
    public int getProdutoID() {
        return produtoID;
    }

    // Método para obter o nome do produto
    public String getNomeProduto() {
        return nomeProduto;
    }

    // Método para obter o status do pedido
    public String getStatus() {
        return status;
    }

    // Verifica se o status do pedido é "Finalizado"
    public boolean isFinalizado() {
        return "Finalizado".equalsIgnoreCase(status);
    }
}
