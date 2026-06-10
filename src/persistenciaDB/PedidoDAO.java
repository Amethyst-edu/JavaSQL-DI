package src.persistenciaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.entities.ItemEntity;
import src.entities.PedidoEntity;

public class PedidoDAO {
    public static void PedidoCad(Connection conn, PedidoEntity pedido) {

        String sqlPedido = "INSERT INTO pedidos (cliente_id, status) VALUES (?, ?)";

        String sqlItem = """
        INSERT INTO itens_pedido 
        (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)""";

        String sqlEstoque = """
        UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? 
        WHERE id = ? AND quantidade_estoque >= ?
        """;

        try {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)){
                int pedidoId;
                stmtPedido.setInt(1, pedido.getClienteId());
                stmtPedido.setString(2, pedido.getStatus().name());
                stmtPedido.executeUpdate();

                try (ResultSet generetedKeys = stmtPedido.getGeneratedKeys()) {
                    if (generetedKeys.next()) {
                        pedidoId = generetedKeys.getInt(1);
                    } else {
                        throw new SQLException("Falha ao criar pedido: ID não foi gerado.");
                    }
                }

                try (PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque); PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {
                    for (ItemEntity item : pedido.getItens()) {
                        stmtEstoque.setInt(1, item.getQuantidade());
                        stmtEstoque.setInt(2, item.getProdutoId());
                        stmtEstoque.setInt(3, item.getQuantidade());
                        int affectedLines = stmtEstoque.executeUpdate();

                        if (affectedLines == 0) {
                            throw new SQLException("Estoque insuficiente para o Produto ID: " + item.getProdutoId());
                        }

                        stmtItem.setInt(1, pedidoId);
                        stmtItem.setInt(2, item.getProdutoId());
                        stmtItem.setInt(3, item.getQuantidade());
                        stmtItem.setDouble(4, item.getPrecoUnitario());
                        stmtItem.executeUpdate();
                    }
                }
            }   
            conn.commit();
            System.out.println("Pedido " + pedido.getId() + " enviado para a fila.");
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Erro na realização do pedido: " + e.getMessage() + "\nO banco foi restaurado.");
            } catch (SQLException rollbackEx) {
                System.out.println("Erro crítico ao tentar realizar o pedido e retroceder a ação no banco de dados: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Erro ao restaurar auto-commit: " + e.getMessage());
            }
        }
    }
}