package src;

import java.sql.Connection;
import java.util.Scanner;

import src.persistenciaDB.ClienteDAO;
import src.persistenciaLocal.ClienteLocal;
import src.persistenciaDB.ProdutoDAO;
import src.persistenciaLocal.ProdutoLocal;

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
            } else if(resposta == 'n' || resposta == 'N') { 
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
            System.out.print("\nSelecione a opção desejada:\n0 - Fechar programa\n1 - Cadastrar cliente\n2 - Ver lista de clientes\n3 - Adicionar produto\n>> ");
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
            }
           
        }
    }

}