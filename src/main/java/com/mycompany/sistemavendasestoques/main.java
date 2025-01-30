package com.mycompany.sistemavendasestoques;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;
import view.*;
public class main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\n==============================");
                System.out.println("       SISTEMA DE VENDAS       ");
                System.out.println("==============================");
                System.out.println("1. Gerenciar Produtos");
                System.out.println("2. Gerenciar Clientes");
                System.out.println("3. Gerenciar Pedidos");
                System.out.println("4. Gerenciar Itens de Pedido");
                System.out.println("5. Gerenciar Fornecedores");
                System.out.println("0. Sair");
                System.out.println("==============================");
                System.out.print("Escolha uma op��o: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        ProdutoView.gerenciarProdutos(scanner);
                        break;
                    case 2:
                        ClienteView.gerenciarClientes(scanner);
                        break;
                    case 3:
                        PedidoView.gerenciarPedidos(scanner);
                        break;
                    case 4:
                        ItempedidoView.gerenciarItensPedido(scanner);
                        break;
                    case 5:
                        FornecedorView.gerenciarFornecedores(scanner);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema... At� mais!");
                        exit = true;
                        break;
                    default:
                        System.out.println("?? Op��o inv�lida! Tente novamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}   