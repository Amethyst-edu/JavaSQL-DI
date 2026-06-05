package src;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConn() {
        try {
            String url = "jdbc:mysql://localhost:3306/defaultdb";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password); // Alterem de acordo com as configurações de vcs :D
        } 
        catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}

// A classe Conexao é responsável por estabelecer a conexão com o banco de dados MySQL. O método getConn() utiliza o DriverManager para obter uma conexão com o banco de dados, utilizando a URL, o nome de usuário e a senha fornecidos. Se ocorrer um erro durante a conexão, uma RuntimeException é lançada com uma mensagem de erro detalhada.