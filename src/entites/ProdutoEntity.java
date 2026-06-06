package src.entites;

public class ProdutoEntity {
    private final int id;
    private final String nome;
    private final double preco;
    private final int emEstoque;
    private final categoria categoria;
    public enum categoria {ALIMENTOS, ELETRONICOS, LIVROS}

    public ProdutoEntity(int id, String nome, double preco, int emEstoque, categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.emEstoque = emEstoque;
        this.categoria = categoria;
    }

    public int getId() {return id;}
    public String getNome() {return nome;}
    public double getPreco() {return preco;}
    public int getQuantidadeEstoque() {return emEstoque;}
    public categoria getCategoria() {return categoria;}
}
