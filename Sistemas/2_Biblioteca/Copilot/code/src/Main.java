package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSistema de Gerenciamento de Biblioteca");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Deletar Livro");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Finalizar Empréstimo");
            System.out.println("6. Calcular Multa");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    biblioteca.cadastrarLivro(new Livro(titulo, autor, isbn));
                    break;
                case 2:
                    System.out.print("ISBN do livro a ser deletado: ");
                    String isbnDeletar = scanner.nextLine();
                    biblioteca.deletarLivro(isbnDeletar);
                    break;
                case 3:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    biblioteca.cadastrarUsuario(new Usuario(nome, cpf));
                    break;
                case 4:
                    System.out.print("CPF do usuário: ");
                    String cpfEmprestimo = scanner.nextLine();
                    System.out.print("ISBN do livro: ");
                    String isbnEmprestimo = scanner.nextLine();
                    biblioteca.realizarEmprestimo(cpfEmprestimo, isbnEmprestimo);
                    break;
                case 5:
                    System.out.print("CPF do usuário: ");
                    String cpfDevolucao = scanner.nextLine();
                    System.out.print("ISBN do livro: ");
                    String isbnDevolucao = scanner.nextLine();
                    biblioteca.finalizarEmprestimo(cpfDevolucao, isbnDevolucao);
                    break;
                case 6:
                    System.out.print("CPF do usuário: ");
                    String cpfMulta = scanner.nextLine();
                    System.out.print("ISBN do livro: ");
                    String isbnMulta = scanner.nextLine();
                    biblioteca.calcularMulta(cpfMulta, isbnMulta);
                    break;
                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
