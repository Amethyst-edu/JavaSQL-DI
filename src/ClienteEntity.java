package src;

public class ClienteEntity {
    private final int id;
    private final String nome;
    private final String email;

    public ClienteEntity(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}

// A classes Entity são responsáveis por representar os dados das entidades do sistema de forma imutável. Cada classe possui atributos privados e finais, e um construtor para inicializá-los.