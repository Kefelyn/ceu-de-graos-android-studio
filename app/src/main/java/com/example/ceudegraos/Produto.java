package com.example.ceudegraos;

// Classe que representa os produtos exibidos ao usuário
public class Produto {

    private String nome;
    private double preco200g;
    private double preco500g;
    private int estoque;
    private int id;
    private int quantidade;
    private double precoPorUnidade;

    // Construtor para inicializar os dados do produto
    public Produto(String nome, double preco200g, double preco500g, int estoque) {
        this.nome = nome;
        this.preco200g = preco200g;
        this.preco500g = preco500g;
        this.estoque = estoque;
    }

    // Getters para acessar os dados do produto
    public String getNome() {
        return nome;
    }

    public double getPreco200g() {
        return preco200g;
    }

    public double getPreco500g() {
        return preco500g;
    }

    public int getEstoque() {
        return estoque;
    }

    // Adicione getters e setters para os novos campos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoPorUnidade() {
        return precoPorUnidade;
    }

    public void setPrecoPorUnidade(double precoPorUnidade) {
        this.precoPorUnidade = precoPorUnidade;
    }

}
