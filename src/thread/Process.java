package src.thread;

import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;

import src.Conexao;
import src.persistenciaDB.PedidoDAO;

public class Process implements Runnable{
    @Override public void run(){
        while (true) {
            try {
                try (Connection conn = Conexao.getConn()){
                    List<Integer> pedidosEmFila = PedidoDAO.buscarPedidosEmFila(conn);
                    for (int id : pedidosEmFila) {
                        PedidoDAO.atualizarStatus(conn, id, "PROCESSANDO");
                        Thread.sleep(3000);
                        PedidoDAO.atualizarStatus(conn, id, "FINALIZADO");
                        System.out.println("Pedido encontrado.");
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Houve um problema na thread de processamento: " + e.getMessage());
                break;
            }
        }
    }

}
