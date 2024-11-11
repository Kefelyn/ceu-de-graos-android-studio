package com.example.ceudegraos;

public class Pedidos {

    private String nomeProduto;
    private String status;


    public Pedidos(String nomeProduto, String status) {
        this.nomeProduto = nomeProduto;
        this.status = status;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getStatus() {
        return status;
    }

    public boolean isFinalizado() {
        return "Finalizado".equals(status);
    }
}

