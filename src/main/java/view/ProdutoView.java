package view;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import dao.ProdutoDAO;
import model.Produto;

public class ProdutoView {
    public static void gerenciarProdutos(Scanner scanner) throws SQLException {
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
}





