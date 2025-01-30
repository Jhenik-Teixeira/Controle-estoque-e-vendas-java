package dao;

import conexao.ConexaoMySql;
import model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO() throws SQLException {
        // Obtém a conexão através da ConnectionFactory
        connection = ConexaoMySql.getConnection();
    }

    public void addPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (cliente_id, data_pedido) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, pedido.getClienteId());
        stmt.setDate(2, pedido.getDataPedido());
        stmt.executeUpdate();
    }

    public List<Pedido> getAllPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Pedido pedido = new Pedido();
            pedido.setId(rs.getInt("id"));
            pedido.setClienteId(rs.getInt("cliente_id"));
            pedido.setDataPedido(rs.getDate("data_pedido"));
            pedidos.add(pedido);
        }
        
        return pedidos;
    }

    public void updatePedido(Pedido pedido) throws SQLException {
        String sql = "UPDATE Pedido SET cliente_id = ?, data_pedido = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, pedido.getClienteId());
        stmt.setDate(2, pedido.getDataPedido());
        stmt.setInt(3, pedido.getId());
        stmt.executeUpdate();
    }

    public void deletePedido(int id) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
