package src.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

public class PedidoEntity {
    private final int id;
    private final int clienteId;
    private final Timestamp data_Pedido;

    public enum Status {
        ABERTO, FILA, PROCESSANDO, FINALIZADO
    }
    private Status status;
    private final List<ItemEntity> itens;

    public PedidoEntity(int id, int clienteId, Timestamp data_Pedido) {
        this.id = id;
        this.clienteId = clienteId;
        this.data_Pedido = data_Pedido;
        this.status = Status.ABERTO; 
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public int getClienteId() {
        return clienteId;
    }
    public Timestamp getData_Pedido() {
        return data_Pedido;
    }
    public Status getStatus() {
        return status;
    }
    public List<ItemEntity> getItens() {
        return itens;
    }

    public void addItem(ItemEntity item) {
        itens.add(item);
    }

    public void nextStatus(Status status) {
        this.status = status;
    }
    
}
