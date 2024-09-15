package livros;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Classe Livro que contém todas as informações sobre o livro
public abstract class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private int quantidadeEstoque;
    private String categoria;

    public Livro(String titulo, String autor, String isbn, int quantidadeEstoque, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + 
               ", Quantidade em estoque: " + quantidadeEstoque + ", Categoria: " + categoria;
    }
}

// Classe de gestão de livros
class GestaoLivros {
    private List<Livro> livros = new ArrayList<>();

    // Adicionar livro
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    // Remover livro pelo ISBN
    public void removerLivro(String isbn) {
        livros.removeIf(livro -> livro.getIsbn().equals(isbn));
        System.out.println("Livro removido com sucesso!");
    }

    // Editar informações do livro pelo ISBN
    public void editarLivro(String isbn, String novoTitulo, String novoAutor, int novaQuantidade, String novaCategoria) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                livro.setTitulo(novoTitulo);
                livro.setAutor(novoAutor);
                livro.setQuantidadeEstoque(novaQuantidade);
                livro.setCategoria(novaCategoria);
                System.out.println("Livro editado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado!");
    }

    // Busca por título, autor ou categoria
    public List<Livro> buscarLivro(String filtro, String valor) {
        return livros.stream()
                .filter(livro -> {
                    switch (filtro.toLowerCase()) {
                        case "titulo":
                            return livro.getTitulo().equalsIgnoreCase(valor);
                        case "autor":
                            return livro.getAutor().equalsIgnoreCase(valor);
                        case "categoria":
                            return livro.getCategoria().equalsIgnoreCase(valor);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toList());
    }

    // Exibir todos os livros
    public void exibirLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro disponível.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}

// Classe principal para interação com o usuário
public class SistemaGestaoLivro {
    public static void main(String[] args) {
        GestaoLivros gestao = new GestaoLivros();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Sistema de Gestão de Livros ---");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Exibir Todos os Livros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Quantidade em estoque: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();
                    Livro livro = new Livro(titulo, autor, isbn, quantidade, categoria);
                    gestao.adicionarLivro(livro);
                    break;

                case 2:
                    System.out.print("Digite o ISBN do livro a ser removido: ");
                    String isbnRemover = scanner.nextLine();
                    gestao.removerLivro(isbnRemover);
                    break;

                case 3:
                    System.out.print("Digite o ISBN do livro a ser editado: ");
                    String isbnEditar = scanner.nextLine();
                    System.out.print("Novo Título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Novo Autor: ");
                    String novoAutor = scanner.nextLine();
                    System.out.print("Nova Quantidade em Estoque: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Nova Categoria: ");
                    String novaCategoria = scanner.nextLine();
                    gestao.editarLivro(isbnEditar, novoTitulo, novoAutor, novaQuantidade, novaCategoria);
                    break;

                case 4:
                    System.out.print("Buscar por (titulo/autor/categoria): ");
                    String filtro = scanner.nextLine();
                    System.out.print("Digite o valor da busca: ");
                    String valor = scanner.nextLine();
                    List<Livro> livrosEncontrados = gestao.buscarLivro(filtro, valor);
                    if (livrosEncontrados.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        livrosEncontrados.forEach(System.out::println);
                    }
                    break;

                case 5:
                    gestao.exibirLivros();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}