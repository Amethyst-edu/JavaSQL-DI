package src.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

public class PedidoEntity {
    public enum StatusEnum {
        ABERTO,
        FILA,
        PROCESSANDO,
        FINALIZADO
    }

    private final int id;
    private final int clienteId;
    private final Timestamp dataPedido; 
    private StatusEnum status;
    private final List<ItemPedidoEntity> itens;

    public PedidoEntity(int id, int clienteId, Timestamp dataPedido, StatusEnum status) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.status = status;
        this.itens = new ArrayList<>();
    }

    public int getId() { return id; }
    public int getClienteId() { return clienteId; }
    public Timestamp getDataPedido() { return dataPedido; }
    public StatusEnum getStatus() { return status; }
    public List<ItemPedidoEntity> getItens() { return itens; }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void addItem(ItemPedidoEntity item) {
        this.itens.add(item);
    }
    
}
