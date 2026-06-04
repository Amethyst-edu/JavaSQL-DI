package src;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

        try (Connection conn = Conexao.getConn()) {System.out.println("Conexão aberta: " + conn);}
         catch (SQLException e) {e.printStackTrace(); System.out.println("erro de conexao");}
        Jsql.atualizar();

        boolean on = true;
        while (on) {
        }
    
    scanner.close();
    }
}