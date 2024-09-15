package menu;
import java.util.Scanner;
import java.util.Random;
import livros.Livros;
import livros.SistemaGestaoLivros;
import usuarios.Adm;

public class menuAdmLivro {
	private Scanner perguntar = new Scanner(System.in);
	//private SistemaGestaoLivros livroRepositorio = new SistemaGestaoLivros();
	private SistemaGestaoLivros livroRepositorio;
	private Adm adm;
	Random random = new Random();
	
	public menuAdmLivro(Adm adm, SistemaGestaoLivros livroRepositorio) {
		this.adm = adm;
		this.livroRepositorio = livroRepositorio;
	}

	public void menuAdm() {
		while(true) {
			System.out.println("--- Sistema de Gestão de Livros ---");
			System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Exibir Todos os Livros");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = Integer.parseInt(perguntar.nextLine());
            
            if(opcao == 1) {
            	adicionarLivro();
            }else if(opcao == 2) {
                removerLivro();            	
            }else if(opcao == 3) {
            	editarLivro();
            }else if(opcao == 4) {
            	buscarLivros();
            }else if(opcao == 5) {
            	exibirTodosOsLivros();
            }else if(opcao == 6) {
            	break;
            }else {
            	System.out.println("Opção inválida. Tente novamente.");
            }
		}
	}
	private void adicionarLivro() {
        System.out.println("Digite o nome do livro: ");
        String titulo = perguntar.nextLine();
        System.out.println("Digite o nome do autor: ");
        String autor = perguntar.nextLine();
        int isbn = random.nextInt(100);
        System.out.println("Digite a quantidade de livros que deseja adicionar: ");
        int quantidadeEstoque = perguntar.nextInt();
        perguntar.nextLine(); // Consumir a nova linha
        System.out.println("Digite o nome da categoria do livro: ");
        String categoria = perguntar.nextLine();
        Livros livro = new Livros(titulo, autor, isbn, quantidadeEstoque, categoria);
        livroRepositorio.adicionarLivros(livro);
    }
	
	private void removerLivro() {
		System.out.println("Digite o nome do livro que deseja remover: ");
		String titulo = perguntar.nextLine();
		if(livroRepositorio.removerLivro(titulo)) {
			System.out.println("livro removido com sucesso");
		}else {
			System.out.println("livro não encontrado");
		}
	}
	
   private void editarLivro() {
	   System.out.println("Digite o nome do livro que deseja editar: ");
	   String titulo = perguntar.nextLine();
	   Livros livro = livroRepositorio.buscarLivros(titulo);
	   if(livro != null) {
		   System.out.println("Digite o novo nome do livro (ou pressione Enter para manter o atual):");
		   String novoTitulo = perguntar.nextLine();
		   if(!novoTitulo.trim().isEmpty()) {
			   livro.setTitulo(novoTitulo);
		   }
		   System.out.println("Digite o novo nome do autor (ou pressione Enter para manter o atual): ");
		   String novoAutor = perguntar.nextLine();
           if (!novoAutor.trim().isEmpty()) {
               livro.setAutor(novoAutor);
           }
           System.out.println("Digite a nova quantidade de livros (ou pressione Enter para manter a atual): ");
           String novaQuantidade = perguntar.nextLine();
           if (!novaQuantidade.trim().isEmpty()) {
               livro.setQuantidadeEstoque(Integer.parseInt(novaQuantidade));
           }
           System.out.println("Digite a nova categoria do livro (ou pressione Enter para manter a atual): ");
           String novaCategoria = perguntar.nextLine();
           if (!novaCategoria.trim().isEmpty()) {
               livro.setCategoria(novaCategoria);
           }
           System.out.println("Livro editado com sucesso.");
       } else {
           System.out.println("Livro não encontrado.");
       }
	}
   
   private void buscarLivros() {
	   System.out.println("Digite o nome do livro que deseja buscar: ");
	   String titulo = perguntar.nextLine();
	   Livros livro = livroRepositorio.buscarLivros(titulo);
	   if(livro != null) {
		   System.out.println(livro);
	   }else {
		   System.out.println("Livro não encontrado");
	   }
   }
   
   private void exibirTodosOsLivros() {
       livroRepositorio.exibirTodosOsLivros();
   }
}
