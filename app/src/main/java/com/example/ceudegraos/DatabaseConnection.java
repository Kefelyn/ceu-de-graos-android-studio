package com.example.ceudegraos;

import java.sql.Connection;

public class DatabaseConnection {
    Connection con;
    String nome, senha, ip, porta, database;

    public void connectionbanco()
    {
        ip = "179.130.246.40";
        database = "ceudegraos-integrado";
        nome = "";
        senha = "";
        porta = "";
    }
}
