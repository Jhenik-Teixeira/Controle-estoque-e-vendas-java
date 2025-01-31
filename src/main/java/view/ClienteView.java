package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteView {
    public static void gerenciarClientes(Scanner scanner) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==============================");
            System.out.println("        Gerenciar Clientes      ");
            System.out.println("==============================");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Excluir Cliente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("==============================");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Cliente novoCliente = new Cliente();
                    System.out.print("Nome: ");
                    novoCliente.setNome(scanner.next());
                    System.out.print("Email: ");
                    novoCliente.setEmail(scanner.next());
                    System.out.print("Telefone: ");
                    novoCliente.setTelefone(scanner.next());
                    clienteDAO.addCliente(novoCliente);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;
                case 2:
                    List<Cliente> clientes = clienteDAO.getAllClientes();
                    if (clientes.isEmpty()) {
                        System.out.println(" Nenhum cliente encontrado.");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente);
                        }
                    }
                    break;
                case 3:
                    Cliente clienteAtualizado = new Cliente();
                    System.out.print("ID do Cliente a atualizar: ");
                    clienteAtualizado.setId(scanner.nextInt());
                    System.out.print("Nome: ");
                    clienteAtualizado.setNome(scanner.next());
                    System.out.print("Email: ");
                    clienteAtualizado.setEmail(scanner.next());
                    System.out.print("Telefone: ");
                    clienteAtualizado.setTelefone(scanner.next());
                    clienteDAO.updateCliente(clienteAtualizado);
                    System.out.println("Cliente atualizado com sucesso!");
                    break;
                case 4:
                    System.out.print("ID do Cliente a excluir: ");
                    int idClienteExcluir = scanner.nextInt();
                    clienteDAO.deleteCliente(idClienteExcluir);
                    System.out.println(" Cliente excluído com sucesso!");
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println(" Opçãoo inválida! Tente novamente.");
            }
        }
    }
}
