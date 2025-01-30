package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.FornecedorDAO;
import model.Fornecedor;

public class FornecedorView {
    public static void gerenciarFornecedores(Scanner scanner) throws SQLException {
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


