package com.mycompany.sistemavendasestoques;

import dao.ClienteDAO;
import dao.FornecedorDAO;
import dao.ItemPedidoDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import model.Cliente;
import model.Fornecedor;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SistemaVendasEstoques {

    public static void main(String[] args) {
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
                        gerenciarProdutos(scanner);
                        break;
                    case 2:
                        gerenciarClientes(scanner);
                        break;
                    case 3:
                        gerenciarPedidos(scanner);
                        break;
                    case 4:
                        gerenciarItensPedido(scanner);
                        break;
                    case 5:
                        gerenciarFornecedores(scanner);
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

    private static void gerenciarProdutos(Scanner scanner) throws SQLException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==============================");
            System.out.println("       Gerenciar Produtos      ");
            System.out.println("==============================");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3.? Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("==============================");
            System.out.print("Escolha uma op��o: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Produto novoProduto = new Produto();
                    System.out.print("Nome: ");
                    novoProduto.setNome(scanner.next());
                    System.out.print("Descri��o: ");
                    novoProduto.setDescricao(scanner.next());
                    System.out.print("Pre�o: ");
                    novoProduto.setPreco(scanner.nextBigDecimal());
                    System.out.print("Quantidade em Estoque: ");
                    novoProduto.setQuantidadeEstoque(scanner.nextInt());
                    System.out.print("ID do Fornecedor: ");
                    novoProduto.setFornecedorId(scanner.nextInt());
                    produtoDAO.addProduto(novoProduto);
                    System.out.println(" Produto adicionado com sucesso!");
                    break;
                case 2:
                    List<Produto> produtos = produtoDAO.getAllProdutos();
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                    } else {
                        for (Produto produto : produtos) {
                            System.out.println(produto);
                        }
                    }
                    break;
                case 3:
                    Produto produtoAtualizado = new Produto();
                    System.out.print("ID do Produto a atualizar: ");
                    produtoAtualizado.setId(scanner.nextInt());
                    System.out.print("Nome: ");
                    produtoAtualizado.setNome(scanner.next());
                    System.out.print("Descri��o: ");
                    produtoAtualizado.setDescricao(scanner.next());
                    System.out.print("Pre�o: ");
                    produtoAtualizado.setPreco(scanner.nextBigDecimal());
                    System.out.print("Quantidade em Estoque: ");
                    produtoAtualizado.setQuantidadeEstoque(scanner.nextInt());
                    System.out.print("ID do Fornecedor: ");
                    produtoAtualizado.setFornecedorId(scanner.nextInt());
                    produtoDAO.updateProduto(produtoAtualizado);
                    System.out.println("Produto atualizado com sucesso!");
                    break;
                case 4:
                    System.out.print("ID do Produto a excluir: ");
                    int idProdutoExcluir = scanner.nextInt();
                    produtoDAO.deleteProduto(idProdutoExcluir);
                    System.out.println("Produto exclu�do com sucesso!");
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("? Op��o inv�lida! Tente novamente.");
            }
        }
    }

    private static void gerenciarClientes(Scanner scanner) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==============================");
            System.out.println("        Gerenciar Clientes      ");
            System.out.println("==============================");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3.? Atualizar Cliente");
            System.out.println("4. Excluir Cliente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("==============================");
            System.out.print("Escolha uma op��o: ");
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
                    System.out.println(" Cliente exclu�do com sucesso!");
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println(" Op��o inv�lida! Tente novamente.");
            }
        }
    }

    private static void gerenciarPedidos(Scanner scanner) throws SQLException {
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

    private static void gerenciarItensPedido(Scanner scanner) throws SQLException {
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
            
         }


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

    private static void gerenciarFornecedores(Scanner scanner) throws SQLException {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n==============================");
            System.out.println("    Gerenciar Fornecedores     ");
            System.out.println("==============================");
            System.out.println("1. Adicionar Fornecedor");
            System.out.println("2. Listar Fornecedores");
            System.out.println("3.? Atualizar Fornecedor");
            System.out.println("4. Excluir Fornecedor");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("==============================");
            System.out.print("Escolha uma op��o: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Fornecedor novoFornecedor = new Fornecedor();
                    System.out.print("Nome: ");
                    novoFornecedor.setNome(scanner.next());
                    System.out.print("Contato: ");
                    novoFornecedor.setContato(scanner.next());
                    fornecedorDAO.addFornecedor(novoFornecedor);
                    System.out.println("Fornecedor adicionado com sucesso!");
                    break;
                case 2:
                    List<Fornecedor> fornecedores = fornecedorDAO.getAllFornecedores();
                    if (fornecedores.isEmpty()) {
                        System.out.println("Nenhum fornecedor encontrado.");
                    } else {
                        for (Fornecedor fornecedor : fornecedores) {
                            System.out.println(fornecedor);
                        }
                    }
                    break;
                case 3:
                    Fornecedor fornecedorAtualizado = new Fornecedor();
                    System.out.print("ID do Fornecedor a atualizar: ");
                    fornecedorAtualizado.setId(scanner.nextInt());
                    System.out.print("Nome: ");
                    fornecedorAtualizado.setNome(scanner.next());
                    System.out.print("Contato: ");
                    fornecedorAtualizado.setContato(scanner.next());
                    fornecedorDAO.updateFornecedor(fornecedorAtualizado);
                    System.out.println("Fornecedor atualizado com sucesso!");
                    break;
                case 4:
                    System.out.print("ID do Fornecedor a excluir: ");
                    int idFornecedorExcluir = scanner.nextInt();
                    fornecedorDAO.deleteFornecedor(idFornecedorExcluir);
                    System.out.println(" Fornecedor exclu�do com sucesso!");
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
