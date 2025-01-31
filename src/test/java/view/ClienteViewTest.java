package view;

import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.Scanner;
import dao.ClienteDAO;
import model.Cliente;
import org.junit.jupiter.api.Test;

class ClienteViewTest {

    @Test
    void testGerenciarClientesAdicionar() throws SQLException {
        Scanner scannerMock = mock(Scanner.class);
        ClienteDAO clienteDAOMock = mock(ClienteDAO.class);

        when(scannerMock.nextInt()).thenReturn(1, 0);  // Escolhe opção 1 (Adicionar Cliente) e depois sai
        when(scannerMock.next()).thenReturn("Carlos", "carlos@email.com", "777777777");

        ClienteView.gerenciarClientes(scannerMock);

        verify(clienteDAOMock, times(1)).addCliente(any(Cliente.class));
    }
}
