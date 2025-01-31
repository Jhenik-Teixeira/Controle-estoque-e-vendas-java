package dao;

import model.Cliente;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteDAOTest {

    private ClienteDAO clienteDAO;

    @BeforeAll
    void setup() throws SQLException {
        clienteDAO = new ClienteDAO(); // Conectar ao banco de testes
    }

    @BeforeEach
    void limparBanco() throws SQLException {
        List<Cliente> clientes = clienteDAO.getAllClientes();
        for (Cliente c : clientes) {
            clienteDAO.deleteCliente(c.getId());
        }
    }

    @Test
    @Order(1)
    void testAddCliente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        cliente.setEmail("teste@email.com");
        cliente.setTelefone("123456789");

        clienteDAO.addCliente(cliente);
        List<Cliente> clientes = clienteDAO.getAllClientes();

        assertTrue(clientes.stream().anyMatch(c -> c.getNome().equals("Teste")));
    }

    @Test
    @Order(2)
    void testGetAllClientes() throws SQLException {
        List<Cliente> clientes = clienteDAO.getAllClientes();
        assertNotNull(clientes);
    }

    @Test
    @Order(3)
    void testUpdateCliente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste Update");
        cliente.setEmail("update@email.com");
        cliente.setTelefone("987654321");

        clienteDAO.addCliente(cliente);
        List<Cliente> clientes = clienteDAO.getAllClientes();
        Cliente clienteParaAtualizar = clientes.get(0);
        
        clienteParaAtualizar.setNome("Nome Atualizado");
        clienteDAO.updateCliente(clienteParaAtualizar);

        List<Cliente> clientesAtualizados = clienteDAO.getAllClientes();
        assertTrue(clientesAtualizados.stream().anyMatch(c -> c.getNome().equals("Nome Atualizado")));
    }

    @Test
    @Order(4)
    void testDeleteCliente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste Delete");
        cliente.setEmail("delete@email.com");
        cliente.setTelefone("000000000");

        clienteDAO.addCliente(cliente);
        List<Cliente> clientes = clienteDAO.getAllClientes();
        Cliente clienteParaDeletar = clientes.get(0);

        clienteDAO.deleteCliente(clienteParaDeletar.getId());

        List<Cliente> clientesAposDelecao = clienteDAO.getAllClientes();
        assertFalse(clientesAposDelecao.stream().anyMatch(c -> c.getNome().equals("Teste Delete")));
    }
}
