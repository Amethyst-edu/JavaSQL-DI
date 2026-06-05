package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class ClienteDAO {
    public static void clienteCad(Scanner scanner){
        System.out.print("\nNome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (nome.strip().isEmpty() || 
        email.strip().isEmpty() || 
        !email.contains("@") || 
        email.strip().contains(" ") ||
        email.endsWith("@") ||
        email.startsWith("@"))
        {
            System.out.println("Nome e/ou email inválidos. Cadastro cancelado.");
            return;
        }

        String insert = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (Connection conn = Conexao.getConn();
        PreparedStatement stmt = conn.prepareStatement(insert)) {
                stmt.setString(1, nome);
                stmt.setString(2, email);
                int changed = stmt.executeUpdate();
                if (changed > 0) {
                    System.out.println("Cliente cadastrado com sucesso!");
                } else {
                    System.out.println("Falha ao cadastrar cliente.");
                }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}