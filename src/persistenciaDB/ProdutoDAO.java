package src.persistenciaDB;
import src.Conexao;
import src.entities.ProdutoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProdutoDAO {
    public static void ProdutoCad(Scanner scanner) {
    System.out.print("/nNome do Produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade em Estoque: ");
        int emEstoque = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Selecione a Categoria:\n1 - ALIMENTOS\n2 - ELETRONICOS\n3 - LIVROS");
        System.out.print(">> ");
        int opcaoCad = scanner.nextInt();
        scanner.nextLine();

        ProdutoEntity.categoria categoria;
        switch (opcaoCad) {
            case 1 -> categoria = ProdutoEntity.categoria.ALIMENTOS;
            case 2 -> categoria = ProdutoEntity.categoria.ELETRONICOS;
            case 3 -> categoria = ProdutoEntity.categoria.LIVROS;
            default -> {
                System.out.println("Opção inválida. Cadastro cancelado.");
                return;
            }
        }

        if (nome.strip().isEmpty() || preco <= 0 || emEstoque < 0) {
            System.out.println("Dados inválidos! O preço não pode ser nulo e o estoque não pode ser negativo. Cadastro cancelado.");
            return;
        }

        String insert = "INSERT INTO produtos (nome, preco, em_estoque, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConn();
             PreparedStatement stmt = conn.prepareStatement(insert)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, emEstoque);
            stmt.setString(4, categoria.name());
            int changed = stmt.executeUpdate();
            if (changed > 0) {
                System.out.println("Produto cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar produto.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    public static void ProdutoCheck(Scanner scanner) {
        String query = "SELECT id, nome, preco, em_estoque, categoria FROM produtos";
        try (Connection conn = Conexao.getConn();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nLista de Produtos:");
            boolean has = false;

            while (rs.next()) {
                has = true;
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int emEstoque = rs.getInt("em_estoque");
                String categoriaStr = rs.getString("categoria");
                ProdutoEntity.categoria categoria = ProdutoEntity.categoria.valueOf(categoriaStr);
                ProdutoEntity produto = new ProdutoEntity(id, nome, preco, emEstoque, categoria);
                System.out.println("\nID: " + produto.getId() + "\nNome: " + produto.getNome() + "\nPreço: " + produto.getPreco() + "\nEm Estoque: " + produto.getemEstoque() + "\nCategoria: " + produto.getCategoria());
            }
            if (!has) {
                System.out.println("\nNão há produtos.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}