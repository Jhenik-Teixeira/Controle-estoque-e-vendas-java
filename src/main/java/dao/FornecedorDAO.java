package dao;

import conexao.ConexaoMySql;
import model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private Connection connection;

    public FornecedorDAO() throws SQLException {
        // Obtém a conexão através da ConnectionFactory
        connection = ConexaoMySql.getConnection();
    }

    public void addFornecedor(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO Fornecedor (nome, contato) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, fornecedor.getNome());
        stmt.setString(2, fornecedor.getContato());
        stmt.executeUpdate();
    }

    public List<Fornecedor> getAllFornecedores() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setContato(rs.getString("contato"));
            fornecedores.add(fornecedor);
        }
        
        return fornecedores;
    }

    public void updateFornecedor(Fornecedor fornecedor) throws SQLException {
        String sql = "UPDATE Fornecedor SET nome = ?, contato = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, fornecedor.getNome());
        stmt.setString(2, fornecedor.getContato());
        stmt.setInt(3, fornecedor.getId());
        stmt.executeUpdate();
    }

    public void deleteFornecedor(int id) throws SQLException {
        String sql = "DELETE FROM Fornecedor WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
