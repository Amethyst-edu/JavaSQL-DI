package src.persistenciaDB;
import src.entites.ProdutoEntity;
import src.Conexao;


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
    }
}