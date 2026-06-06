package src.persistenciaLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.entities.ProdutoEntity;

public class ProdutoLocal {
    private static final List<ProdutoEntity> produtos = new ArrayList<>();
    private static int nextId = 1;

    public static void ProdutoCad(Scanner scanner) {
        System.out.print("\nNome do Produto: ");
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
        ProdutoEntity nextProduto = new ProdutoEntity(nextId, nome, preco, emEstoque, categoria);
        produtos.add(nextProduto);
        nextId++;
        System.out.println("Produto cadastrado com sucesso! ID: " + nextProduto.getId());
    }

    public static void ProdutoCheck(Scanner scanner) {
        if (produtos.isEmpty()) {
            System.out.println("\nNão há produtos.");
            return;
        } else {
            System.out.println("\nLista de Produtos:");
            for (int i = 0; i < produtos.size(); i++) {
                ProdutoEntity produto = produtos.get(i);
                System.out.println("\nID: " + produto.getId() + "\nNome: " + produto.getNome() + "\nPreço: " + produto.getPreco() + "\nEm Estoque: " + produto.getemEstoque() + "\nCategoria: " + produto.getCategoria());
            }
        }
    }
}
