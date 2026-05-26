package src;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Jsql {
    public static void atualizar(){

        String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" // EXEMPLO DE INSERÇÃO DE COMANDOS SQL
            + "id_cliente INT AUTO_INCREMENT PRIMARY KEY, "
            + "nome VARCHAR(60) NOT NULL, "
            + "email VARCHAR(60) NOT NULL UNIQUE, "
            + ")";

        try (Connection conn = Conexao.conectar();
         Statement stmt = conn.createStatement()) {
        
        stmt.executeUpdate(sqlClientes); // ⬅️ A CADA STRING SQL, TEM QUE FAZER OUTRO CÓDIGO DESSE

        System.out.println("Banco atualizado!");

    } catch (SQLException e) {
        System.out.println("Erro de atualização: " + e.getMessage());
    }
    }
}

// A classe Jsql é responsável por executar comandos SQL para criar ou atualizar a estrutura raiz do banco de dados.