package src;

import java.util.Properties; // leem e interpretam o db.properties
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection; // fazem a conexao
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConn() {
        try {
            Properties props = loadProperties();
            String url = props.getProperty("db.url");
            return DriverManager.getConnection(url, props);
        } 
        catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fis);
            return props;
        } 
        catch (IOException e) {
            throw new RuntimeException("Erro no carregamento de db.properties: " + e.getMessage());
        }
    }
}
    // A classe Conexao é responsável por estabelecer a conexão com o banco de dados MySQL. O primeiro método, getConn(), tenta criar uma conexão usando as propriedades carregadas do arquivo db.properties. O segundo método, loadProperties(), lê o arquivo de propriedades e retorna um objeto Properties contendo as configurações necessárias para a conexão. Há um try-catch para lidar com possíveis exceções, como problemas de conexão ou erros ao carregar o arquivo de propriedades.