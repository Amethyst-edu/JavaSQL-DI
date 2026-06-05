package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteLocal {
    private static final List<ClienteEntity> clientes = new ArrayList<>();
    private static int nextId = 1;

    public static void clienteCad(Scanner scanner) {
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
    
    ClienteEntity nextCliente = new ClienteEntity(nextId, nome, email);
    clientes.add(nextCliente);
    nextId++;
    System.out.println("Cliente cadastrado com sucesso! ID: " + nextCliente.getId());
    }
}