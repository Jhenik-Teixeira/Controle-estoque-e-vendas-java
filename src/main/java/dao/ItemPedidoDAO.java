package dao;

import conexao.ConexaoMySql;
import model.ItemPedido;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {
    private Connection connection;

    public ItemPedidoDAO() throws SQLException {
        // Obt�m a conex�o atrav�s da ConnectionFactory
        connection = ConexaoMySql.getConnection();
    }

    public void addItemPedido(ItemPedido itemPedido) throws SQLException {
        // Primeiro, verificar a quantidade dispon�vel no estoque
        String sqlProduto = "SELECT quantidade_estoque, preco FROM Produto WHERE id = ?";
        PreparedStatement stmtProduto = connection.prepareStatement(sqlProduto);
        stmtProduto.setInt(1, itemPedido.getProdutoId());
        ResultSet rsProduto = stmtProduto.executeQuery();
        
        if (rsProduto.next()) {
            int quantidadeEstoque = rsProduto.getInt("quantidade_estoque");
            BigDecimal precoProduto = rsProduto.getBigDecimal("preco");
            
            if (quantidadeEstoque >= itemPedido.getQuantidade()) {
                // Atualiza o pre�o total no ItemPedido
                BigDecimal precoTotal = precoProduto.multiply(new BigDecimal(itemPedido.getQuantidade()));
                itemPedido.setPrecoTotal(precoTotal);

                // Inserir o item no pedido
                String sql = "INSERT INTO ItemPedido (pedido_id, produto_id, quantidade, preco_total) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, itemPedido.getPedidoId());
                stmt.setInt(2, itemPedido.getProdutoId());
                stmt.setInt(3, itemPedido.getQuantidade());
                stmt.setBigDecimal(4, itemPedido.getPrecoTotal());
                stmt.executeUpdate();

                // Atualizar a quantidade em estoque
                String sqlUpdateEstoque = "UPDATE Produto SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
                PreparedStatement stmtUpdateEstoque = connection.prepareStatement(sqlUpdateEstoque);
                stmtUpdateEstoque.setInt(1, itemPedido.getQuantidade());
                stmtUpdateEstoque.setInt(2, itemPedido.getProdutoId());
                stmtUpdateEstoque.executeUpdate();
                
            } else {
                throw new SQLException("Quantidade insuficiente em estoque.");
            }
        } else {
            throw new SQLException("Produto n�o encontrado.");
        }
    }

    public List<ItemPedido> getAllItensPedido() throws SQLException {
        List<ItemPedido> itensPedido = new ArrayList<>();
        String sql = "SELECT * FROM ItemPedido";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(rs.getInt("id"));
            itemPedido.setPedidoId(rs.getInt("pedido_id"));
            itemPedido.setProdutoId(rs.getInt("produto_id"));
            itemPedido.setQuantidade(rs.getInt("quantidade"));
            itemPedido.setPrecoTotal(rs.getBigDecimal("preco_total"));
            itensPedido.add(itemPedido);
        }
        
        return itensPedido;
    }

    public void updateItemPedido(ItemPedido itemPedido) throws SQLException {
        String sql = "UPDATE ItemPedido SET pedido_id = ?, produto_id = ?, quantidade = ?, preco_total = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, itemPedido.getPedidoId());
        stmt.setInt(2, itemPedido.getProdutoId());
        stmt.setInt(3, itemPedido.getQuantidade());
        stmt.setBigDecimal(4, itemPedido.getPrecoTotal());
        stmt.setInt(5, itemPedido.getId());
        stmt.executeUpdate();
    }

    public void deleteItemPedido(int id) throws SQLException {
        String sql = "DELETE FROM ItemPedido WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
