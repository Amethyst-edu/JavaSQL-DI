package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import src.persistenciaDB.*;
import src.persistenciaLocal.*;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        boolean usingdb = false;
        try (Connection conn = Conexao.getConn()) {
            System.out.println("Conexão sucedida: " + conn);
            usingdb = true;
            registro(scanner, usingdb, conn);
        }
         catch (SQLException e) {
            System.out.println("ERRO DE CONEXÃO");
            System.out.println("Deseja processar os dados localmente para avaliar a POO? (s/n)");
            char resposta = scanner.nextLine().charAt(0);
            if (resposta == 's' || resposta == 'S') {
                System.out.println("Processando dados sem conexão...");
                registro(scanner, usingdb, null);
            } else if(resposta == 'n' || resposta == 'N') { 
                System.out.println(e.getMessage());
                System.err.println("\nTambém foi encontrado erro(s):");
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    public static void registro(Scanner scanner, boolean usingdb, Connection conn) {
        boolean on = true;
        while (on) {
            System.out.print("\nSelecione a opção desejada:\n0 - Fechar programa\n1 - Cadastrar cliente\n2 - Ver lista de clientes\n3 - Adicionar produto\n4 - Ver todos os produtos\n5 - Novo pedido\n>> ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 0 ->  {on = false; System.out.println("Encerrando programa...");}
                case 1 -> {
                    if (usingdb) {
                        ClienteDAO.clienteCad(scanner);
                    } else {
                        ClienteLocal.clienteCad(scanner);
                    }
                }
                case 2 -> {
                    if (usingdb) {
                        ClienteDAO.clienteCheck(scanner); 
                    } else {
                        ClienteLocal.clienteCheck(scanner);
                    }
                }
                case 3 -> {
                    if (usingdb) {
                        ProdutoDAO.ProdutoCad(scanner);
                    } else {
                        ProdutoLocal.ProdutoCad(scanner);
                    }
                }
                case 4 -> {
                    if (usingdb) {
                        ProdutoDAO.ProdutoCheck(scanner);
                    } else {
                        ProdutoLocal.ProdutoCheck(scanner);
                    }
                }
                case 5 -> {
                    if (usingdb) {
                        carrinho(scanner, conn);
                    } else {
                        //PedidoLocal.PedidoCad(scanner);  // cancelado :(
                        System.out.println("Modo local desativado para pedidos.");
                    }
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
           
        }
    }

    public static void carrinho(Scanner scanner, Connection conn) {
        System.out.print("Digite o ID do cliente (não inserir nome ou email): ");
        int clienteId = scanner.nextInt();
        scanner.nextLine();
        src.entities.PedidoEntity pedido = new src.entities.PedidoEntity(0, clienteId, null);

        String sqlFindPrice = "SELECT preco FROM produtos WHERE id = ?";
        while (true) {
            System.out.print("\nDigite o ID do produto (ou 0 para finalizar o carrinho): ");
            int produtoId = scanner.nextInt();
            scanner.nextLine();
            if (produtoId == 0) {
            break;
            } else {
                System.out.print("Digite a quantidade desejada: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                double precoUnitario = 0.0;

                try (PreparedStatement stmtPreco = conn.prepareStatement(sqlFindPrice)) {
                    stmtPreco.setInt(1, produtoId);
                    try (ResultSet rs = stmtPreco.executeQuery()) {
                        if (rs.next()) {
                            precoUnitario = rs.getDouble("preco");
                            System.out.println("-> Produto localizado via SQL direto! Preço: R$ " + precoUnitario);
                        } else {
                            System.out.println("Produto não encontrado no banco de dados. Tente outro ID.");
                            continue;
                        }
                    }
                } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                        continue;
                }

                src.entities.ItemEntity item = new src.entities.ItemEntity(0, 0, produtoId, quantidade, precoUnitario);
                pedido.addItem(item);
                System.out.println("-> Item adicionado ao carrinho com sucesso!");
            }
        }
    pedido.nextStatus(src.entities.PedidoEntity.Status.FILA);
    System.out.println("\nEnviando pedido para o banco de dados...");
    PedidoDAO.PedidoCad(conn, pedido);
    }
}               