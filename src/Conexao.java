package src;

import java.util.Properties; // leem e interpretam o db.properties
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.sql.Connection; // fazem a conexao
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String PROPERTIES_FILE = "db.properties";
    private static final Properties CONFIG = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(PROPERTIES_FILE)) {
            props.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Não foi possível carregar " + PROPERTIES_FILE + ": " + e.getMessage());
        }
        return props;
    }

    private static String getConfig(String key) {
        String envKey = key.toUpperCase().replace('.', '_');
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }
        return CONFIG.getProperty(key);
    }

    public static Connection conectar() throws SQLException {
        String url = String.format(
            "jdbc:mysql://%s:%s/%s?sslmode=REQUIRED",
            getConfig("db.host"),
            getConfig("db.port"),
            getConfig("db.database")
        );
        String user = getConfig("db.user");
        String pass = getConfig("db.password");
        return DriverManager.getConnection(url, user, pass);
    }
}

// A classe Conexao é responsável por estabelecer a conexão com o banco de dados MySQL.