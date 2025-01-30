package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {
    private static final String URL = "jdbc:mysql://localhost/vendas_estoques";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    private static Connection connection = null;

    private ConexaoMySql() {
        // Construtor privado para evitar instanciamento
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
