package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.ItemPedidoDAO;
import model.ItemPedido;

public class ItempedidoView {
    public static void gerenciarItensPedido(Scanner scanner) throws SQLException {
        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==============================");
            System.out.println("     Gerenciar Itens Pedido    ");
            System.out.println("==============================");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Listar Itens");
            System.out.println("3.? Atualizar Item");
            System.out.println("4. Excluir Item");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("==============================");
            System.out.print("Escolha uma op��o: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ItemPedido novoItem = new ItemPedido();
                    System.out.print("ID do Pedido: ");
                    novoItem.setPedidoId(scanner.nextInt());
                    System.out.print("ID do Produto: ");
                    novoItem.setProdutoId(scanner.nextInt());
                    System.out.print("Quantidade: ");
                    novoItem.setQuantidade(scanner.nextInt());
                    System.out.print("Pre�o Total: ");
                    novoItem.setPrecoTotal(scanner.nextBigDecimal());
                    itemPedidoDAO.addItemPedido(novoItem);
                    System.out.println("Item adicionado com sucesso!");
                    break;
                case 2:
                    List<ItemPedido> itens = itemPedidoDAO.getAllItensPedido();
                    if (itens.isEmpty()) {
                        System.out.println("Nenhum item encontrado.");
                    } else {
                        for (ItemPedido item : itens) {
                            System.out.println(item);
                        }
                    }
                    break;
                case 3:
                    ItemPedido itemAtualizado = new ItemPedido();
                    System.out.print("ID do Item a atualizar: ");
                    itemAtualizado.setId(scanner.nextInt());
                    System.out.print("ID do Pedido: ");
                    itemAtualizado.setPedidoId(scanner.nextInt());
                    System.out.print("ID do Produto: ");
                    itemAtualizado.setProdutoId(scanner.nextInt());
                    System.out.print("Quantidade: ");
                    itemAtualizado.setQuantidade(scanner.nextInt());
                    System.out.print("Pre�o Total: ");
                    itemAtualizado.setPrecoTotal(scanner.nextBigDecimal());
                    itemPedidoDAO.updateItemPedido(itemAtualizado);
                    System.out.println(" Item atualizado com sucesso!");
                    break;
                case 4:
                    System.out.print("ID do Item a excluir: ");
                    int idItemExcluir = scanner.nextInt();
                    itemPedidoDAO.deleteItemPedido(idItemExcluir);
                    System.out.println(" Item exclu�do com sucesso!");
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Op��o inv�lida! Tente novamente.");
            }
        }
    }
}
