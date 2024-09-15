package menu;
import java.util.Scanner;
import livros.Livros;
import livros.SistemaGestaoLivros;
import usuarios.Usuario;

public class MenuUsuarioLivro {
	    private Scanner perguntar = new Scanner(System.in);
	    //private SistemaGestaoLivros livroRepositorio = new SistemaGestaoLivros();
	    private SistemaGestaoLivros livroRepositorio;
	    private Usuario usuario;
        
	    public MenuUsuarioLivro(Usuario usuario, SistemaGestaoLivros livroRepositorio) {
	        this.usuario = usuario;
	        this.livroRepositorio = livroRepositorio;
	    }
	    
	    public void menuUsuarioLivros() {
	    	while(true) {
	    		System.out.println("--- Menu do Usuário ---");
	            System.out.println("1. Alugar Livro");
	            System.out.println("2. Devolver Livro");
	            System.out.println("3. Exibir Livros Alugados");
	            System.out.println("4. Sair");
	            System.out.print("Escolha uma opção: ");
	            
	            int opcao = Integer.parseInt(perguntar.nextLine());
	            
	            if(opcao == 1) {
	            	 System.out.print("Digite o título do livro que deseja alugar: ");
	            	 String titulo = perguntar.nextLine();
	            	 if (livroRepositorio.retirarLivro(titulo)) {
	            		 for(Livros livro: livroRepositorio.getLivros()) {
	            			 if(livro.getTitulo().equals(titulo)) {
	            				 usuario.alugarLivro(livro);
	            				 System.out.println("Livro alugado com sucesso!");
	                             break;
	            			 }
	            		 }
	            	 }else {
	            		 System.out.println("Livro não disponível ou não encontrado.");
	            	 }
	            }else if (opcao == 2) {
	            	System.out.print("Digite o título do livro que deseja devolver: ");
	            	String titulo = perguntar.nextLine();
	            	Livros livroDevolver = null;
	            	for(Livros livro: usuario.getLivrosAlugados()) {
	            		if(livro.getTitulo().equals(titulo)) {
	            			livroDevolver = livro;
	            			break;
	            		}
	            	}
	            	if(livroDevolver != null) {
	            		usuario.devolverLivro(livroDevolver);
	            		livroDevolver.setQuantidadeEstoque(livroDevolver.getQuantidadeEstoque() + 1);
	            		System.out.println("Livro devolvido com sucesso");
	            	}else {
	            		System.out.println("você não alugou esse livro");
	            	}
	            }else if(opcao == 3) {
	            	System.out.println("Livros alugados:");
	            	for(Livros livro: usuario.getLivrosAlugados()) {
	            		System.out.println(livro);
	            	}
	            }else if(opcao == 4) {
	            	break;
	            }else {
	            	System.out.println("Opção inválida. Tente novamente.");
	            }
	    	}
	  }
}



