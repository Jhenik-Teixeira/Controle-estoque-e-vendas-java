package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import dao.*;

import model.Pedido;

public class PedidoView {
  public static void gerenciarPedidos(Scanner scanner) throws SQLException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==============================");
            System.out.println("        Gerenciar Pedidos      ");
            System.out.println("==============================");
            System.out.println("1. Adicionar Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3.? Atualizar Pedido");
            System.out.println("4. Excluir Pedido");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("==============================");
            System.out.print("Escolha uma op��o: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Pedido novoPedido = new Pedido();
                    System.out.print("ID do Cliente: ");
                    novoPedido.setClienteId(scanner.nextInt());
                    System.out.print("Data do Pedido (AAAA-MM-DD): ");
                    novoPedido.setDataPedido(java.sql.Date.valueOf(scanner.next()));
                    pedidoDAO.addPedido(novoPedido);
                    System.out.println("Pedido adicionado com sucesso!");
                    break;
                case 2:
                    List<Pedido> pedidos = pedidoDAO.getAllPedidos();
                    if (pedidos.isEmpty()) {
                        System.out.println(" Nenhum pedido encontrado.");
                    } else {
                        for (Pedido pedido : pedidos) {
                            System.out.println(pedido);
                        }
                    }
                    break;
                case 3:
                    Pedido pedidoAtualizado = new Pedido();
                    System.out.print("ID do Pedido a atualizar: ");
                    pedidoAtualizado.setId(scanner.nextInt());
                    System.out.print("ID do Cliente: ");
                    pedidoAtualizado.setClienteId(scanner.nextInt());
                    System.out.print("Data do Pedido (AAAA-MM-DD): ");
                    pedidoAtualizado.setDataPedido(java.sql.Date.valueOf(scanner.next()));
                    pedidoDAO.updatePedido(pedidoAtualizado);
                    System.out.println("Pedido atualizado com sucesso!");
                    break;
                case 4:
                    System.out.print("ID do Pedido a excluir: ");
                    int idPedidoExcluir = scanner.nextInt();
                    pedidoDAO.deletePedido(idPedidoExcluir);
                    System.out.println(" Pedido exclu�do com sucesso!");
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("? Op��o inv�lida! Tente novamente.");
            }
        }
    }
}