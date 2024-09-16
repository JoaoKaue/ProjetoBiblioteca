package menu;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import livros.Livros;
import livros.SistemaGestaoLivros;
import usuarios.Usuario;

public class MenuUsuarioLivro {
	    private Scanner perguntar = new Scanner(System.in);
	    private SistemaGestaoLivros livroRepositorio;
	    private Usuario usuario;
	    
	    public MenuUsuarioLivro(Usuario usuario, SistemaGestaoLivros livroRepositorio) {
	        this.usuario = usuario;
	        this.livroRepositorio = livroRepositorio;
	    }
	    
	    public void menuUsuarioLivros() {
	    	while(true) {
	    		System.out.println("--- Menu do Usuário ---");
	    		System.out.println("Livros disponíeis: ");
            	livroRepositorio.exibirTodosOsLivros();
	            System.out.println("1. Alugar Livro");
	            System.out.println("2. Devolver Livro");
	            System.out.println("3. Exibir Livros Alugados");
	            System.out.println("4. Exibir filtros");
	            System.out.println("5. Exibir Histórico");
	            System.out.println("6. Sair");
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
	            	exibirLivrosAlugados();
	            }else if(opcao == 4) {
	            	 System.out.println("Escolha um filtro: ");
	                 System.out.println("1. Título");
	                 System.out.println("2. Autor");
	                 System.out.println("3. Categoria");
	                 System.out.print("Escolha uma opção: ");
	                 int opcaoDois = Integer.parseInt(perguntar.nextLine());
	                 String atributo = "";
	                 switch (opcaoDois) {
	                    case 1:
	                        atributo = "titulo";
	                        break;
	                    case 2:
	                        atributo = "autor";
	                        break;
	                    case 3:
	                        atributo = "categoria";
	                        break;
	                    default:
	                        System.out.println("Opção inválida.");
	                        continue;
	                }
	                 System.out.print("Digite o valor para o filtro escolhido: ");
	                 String valor = perguntar.nextLine();
	                 List<Livros> livrosFiltrados = livroRepositorio.filtrarLivros(atributo, valor);
	                 System.out.println("Livros encontrados:");
	                 for (Livros livro : livrosFiltrados) {
	                     System.out.println(livro);
	                 }
	            }else if(opcao == 5) {
	            	exibirHistorico();
	            }else if(opcao == 6){
	            	break;
	            }else {
	            	System.out.println("Opção inválida. Tente novamente.");
	            }
	    	}
	  }
	    
	    private void exibirLivrosAlugados() {
	    	List<Livros> livrosAlugados = usuario.getLivrosAlugados();
	        Map<Livros, LocalDate> dataAluguel = usuario.getDataAluguel();
	        LocalDate hoje = LocalDate.now();
	        
	        for (Livros livro : livrosAlugados) {
	        	 LocalDate dataAluguelLivro = dataAluguel.get(livro);
	             LocalDate dataDevolucao = dataAluguelLivro.plusDays(3);
	             long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(hoje, dataDevolucao);
	             System.out.println("Livro: " + livro.getTitulo() + " - Data de devolução: " + dataDevolucao);
	             if (diasRestantes > 0) {
	                 System.out.println("Faltam " + diasRestantes + " dias para devolver o livro.");
	             } else {
	                 System.out.println("O prazo de devolução expirou! Devolva o livro imediatamente.");
	             }
	        }
	    }
	    
	    private void exibirHistorico() {
	        List<String> historico = usuario.getHistorico();
	        System.out.println("--- Histórico de Ações ---");
	        for (String acao : historico) {
	            System.out.println(acao);
	        }
	    }
}



