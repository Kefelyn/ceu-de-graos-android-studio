package com.example.ceudegraos;

public class Usuario {
    private String nome;
    private String email;
    private String cpf_Cnpj;
    private String endereco;
    private String senha;
    private String dataCadastro;
    private String tipoUsuario;

    public Usuario(String nome, String email, String cpfCnpj, String endereco, String senha, String dataCadastro, String tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.cpf_Cnpj = cpfCnpj;
        this.endereco = endereco;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.tipoUsuario = tipoUsuario;
    }
// Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf_Cnpj() {
        return cpf_Cnpj;
    }

    public void setCpf_Cnpj(String cpf_Cnpj) {
        this.cpf_Cnpj = cpf_Cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

