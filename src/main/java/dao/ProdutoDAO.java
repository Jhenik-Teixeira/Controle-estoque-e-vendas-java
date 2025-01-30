package dao;

import conexao.ConexaoMySql;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() throws SQLException {
        // Obtém a conexão através da ConnectionFactory
        connection = ConexaoMySql.getConnection();
    }

    public void addProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produto (nome, descricao, preco, quantidade_estoque, fornecedor_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setBigDecimal(3, produto.getPreco());
        stmt.setInt(4, produto.getQuantidadeEstoque());
        stmt.setInt(5, produto.getFornecedorId());
        stmt.executeUpdate();
    }

    public List<Produto> getAllProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getBigDecimal("preco"));
            produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
            produto.setFornecedorId(rs.getInt("fornecedor_id"));
            produtos.add(produto);
        }
        
        return produtos;
    }

    public void updateProduto(Produto produto) throws SQLException {
        String sql = "UPDATE Produto SET nome = ?, descricao = ?, preco = ?, quantidade_estoque = ?, fornecedor_id = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setBigDecimal(3, produto.getPreco());
        stmt.setInt(4, produto.getQuantidadeEstoque());
        stmt.setInt(5, produto.getFornecedorId());
        stmt.setInt(6, produto.getId());
        stmt.executeUpdate();
    }

    public void deleteProduto(int id) throws SQLException {
        String sql = "DELETE FROM Produto WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
