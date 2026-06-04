package src;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

        try (Connection conn = Conexao.getConn()) {
            System.out.println("Conexão sucedida: " + conn);
            Registro(scanner);
        }
         catch (Exception e) {
            System.out.println("ERRO DE CONEXÃO");
            System.out.println("Deseja processar os dados localmente para avaliar a POO? (s/n)");
            char resposta = scanner.nextLine().charAt(0);
            if (resposta == 's' || resposta == 'S') {
                System.out.println("Processando dados sem banco...");
                Registro(scanner);
            } else {
                System.out.println(e.getMessage());
                System.err.println("\nTambém foi encontrado erro(s):");
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    public static void Registro(Scanner scanner){
        boolean on = true;
        while (on) {
            System.out.print("\nSelecione a opção desejada:\n0 - Fechar programa\n1 - Cadastrar cliente\n2 - Realizar pedido\n3 - Adicionar produto");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (opcao) {
                case 0 ->  on = false;
                // Trabalhando nisso agora
            }
           
        }
    }

}