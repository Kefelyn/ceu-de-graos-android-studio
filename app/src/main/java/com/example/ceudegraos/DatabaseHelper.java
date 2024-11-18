package com.example.ceudegraos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    // String de conexão para o banco de dados SQL Server
    private static final String CONNECTION_URL =
            "jdbc:jtds:sqlserver://DESKTOP-AGU3OAL:1433/SistemasFazenda;user=seu_usuario;password=sua_senha;";

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC para o SQL Server
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            // Retorna uma conexão estabelecida com o banco de dados
            return DriverManager.getConnection(CONNECTION_URL);
        } catch (ClassNotFoundException e) {
            // Lança uma exceção caso o driver não seja encontrado
            e.printStackTrace();
            throw new SQLException("Erro ao carregar o driver JDBC.");
        }
    }
}
