package src.entities;

public class ItemEntity {
    private final int id;
    private final int pedidoId;
    private final int produtoId;
    private final int quantidade;
    private final double precoUnitario;

    public ItemEntity(int id, int pedidoId, int produtoId, int quantidade, double precoUnitario) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getId() {
        return id;
    }
    public int getPedidoId() {
        return pedidoId;
    }
    public int getProdutoId() {
        return produtoId;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
}
