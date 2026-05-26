package src;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Conexao.conectar()) {System.out.println("Conexão aberta: " + conn);}
         catch (SQLException e) {e.printStackTrace(); System.out.println("erro de conexao");}

        Jsql.atualizar();
    }
}