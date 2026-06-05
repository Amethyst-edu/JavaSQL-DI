package src;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        boolean usingdb = false;
        try (Connection conn = Conexao.getConn()) {
            System.out.println("Conexão sucedida: " + conn);
            usingdb = true;
            registro(scanner, usingdb);
        }
         catch (Exception e) {
            System.out.println("ERRO DE CONEXÃO");
            System.out.println("Deseja processar os dados localmente para avaliar a POO? (s/n)");
            char resposta = scanner.nextLine().charAt(0);
            if (resposta == 's' || resposta == 'S') {
                System.out.println("Processando dados sem conexão...");
                registro(scanner, usingdb);
            } else if(resposta == 'n' || resposta == 'N') { // agora para negar tem que começar com N, nao qualquer texto
                System.out.println(e.getMessage());
                System.err.println("\nTambém foi encontrado erro(s):");
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    public static void registro(Scanner scanner, boolean usingdb) {
        boolean on = true;
        while (on) {
            System.out.print("\nSelecione a opção desejada:\n0 - Fechar programa\n1 - Cadastrar cliente\n2 - Realizar pedido\n3 - Adicionar produto\n > ");
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
                        PedidoDAO.realizarPedido(scanner); // preciso do modelo logico para criar a entidade aq 
                    } else {
                        Pedido.realizarPedido(scanner);
                    }
                }
                case 3 -> {
                    if (usingdb) {
                        ProdutoDAO.adicionarProduto(scanner); // preciso do modelo logico para criar a entidade aq
                    } else {
                        Produto.adicionarProduto(scanner);
                    }
                }
            }
           
        }
    }

}