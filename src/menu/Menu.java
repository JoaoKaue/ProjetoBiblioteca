package menu;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import usuarios.Adm;
import usuarios.AdmRepositorio;
import usuarios.Usuario;
import usuarios.UsuarioRepositorio;
import livros.Livros;
import livros.SistemaGestaoLivros;
public class Menu {
	Scanner perguntar = new Scanner(System.in);
	UsuarioRepositorio repositorio = new UsuarioRepositorio();
	AdmRepositorio repositorioAdm = new AdmRepositorio();
	private SistemaGestaoLivros livroRepositorio;
	public Menu(SistemaGestaoLivros livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }
	public void menuUsuario() {
		while(true) {
			System.out.println("1. Cadastrar novo usuário \n2. Entrar em uma conta \n3. Retornar ");
			System.out.println("Digite uma opcão: ");
			
			int numero = Integer.parseInt(perguntar.nextLine());
			if(numero == 1) {
				System.out.println("digite o seu nome: ");
				String nome = perguntar.nextLine();
				System.out.println("digite a sua senha: ");
				String senha = perguntar.nextLine();
				System.out.println("digite o seu email: ");
				String email = perguntar.nextLine();
				Usuario usuario = new Usuario(nome, senha, email);
				repositorio.adicionarUsuario(usuario);
			}else if(numero == 2) {
				System.out.println("Digite o nome de usuario: ");
				String nome = perguntar.nextLine();
				System.out.println("Digite a senha do usuario: ");
				String senha = perguntar.nextLine();
				System.out.println("Digite o email do usuario: ");
				String email = perguntar.nextLine();
				Usuario usuario = repositorio.autenticarUsuarios(nome, senha, email);
				if(usuario != null) {
					System.out.println("autenticacão concluida");
					verificarPrazoDevolucao(usuario);
					MenuUsuarioLivro menuUsuarioLivros = new MenuUsuarioLivro(usuario, livroRepositorio);
					menuUsuarioLivros.menuUsuarioLivros();
				}else {
					System.out.println("Erro de autenticação: senha ou usuario ou email incorretos");
				}
			}else if(numero == 3) {
					break;
				}else {
					System.out.println("opcao invalida. tente novamente");
				}
			}
	}
	public void menuAdm() {
		while(true) {
			System.out.println("1. Cadastrar novo adm \n2. Entrar em uma conta \n3. Sair do programa ");
			System.out.println("Digite uma opção");
			int numero = Integer.parseInt(perguntar.nextLine());
			if(numero == 1) {
				System.out.println("digite o seu nome: ");
				String nome = perguntar.nextLine();
				System.out.println("digite a sua senha: ");
				String senha = perguntar.nextLine();
				System.out.println("digite o seu email: ");
				String email = perguntar.nextLine();
				
				Adm adm = new Adm(nome, senha, email);
				repositorioAdm.adicionarAdm(adm);
			}else if(numero == 2) {
				System.out.println("Digite o nome de adm: ");
				String nome = perguntar.nextLine();
				System.out.println("Digite a senha do adm: ");
				String senha = perguntar.nextLine();
				System.out.println("Digite o email do adm: ");
				String email = perguntar.nextLine();
				Adm adm = repositorioAdm.autenticarAdms(nome, senha, email);
				if(adm != null) {
					System.out.println("Autenticação concluida");
					menuAdmLivro admlivro = new menuAdmLivro(adm, livroRepositorio);
					admlivro.menuAdm();
				}else {
					System.out.println("Erro de autenticação: senha ou usuario ou email incorretos");
				}
			}else if(numero == 3){
					break;
				}else {
					System.out.println("opção inválida. tente novamente");
				}
		}
	}
	public void salvarAdms() {
        repositorioAdm.salvarAdms();
    }
	public void salvarUsuarios() {
		repositorio.salvarUsuarios();
	}
    public void carregarAdms() {
        repositorioAdm.carregarAdms();
    }
    public void carregarUsuarios() {
    	repositorio.carregarUsuarios();
    }
    
    private void verificarPrazoDevolucao(Usuario usuario) {
        List<Livros> livrosAlugados = usuario.getLivrosAlugados();
        Map<Livros, LocalDate> dataAluguel = usuario.getDataAluguel();
        LocalDate hoje = LocalDate.now();

        for (Livros livro : livrosAlugados) {
            LocalDate dataAluguelLivro = dataAluguel.get(livro);
            LocalDate dataDevolucao = dataAluguelLivro.plusDays(3);
            long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(hoje, dataDevolucao);

            if (diasRestantes <= 0) {
                System.out.println("Aviso: O prazo de devolução do livro '" + livro.getTitulo() + "' expirou!");
                System.out.println("Um email foi enviado para " + usuario.getEmail() + " notificando sobre a devolução.");
            } else {
                System.out.println("Aviso: Faltam " + diasRestantes + " dias para devolver o livro '" + livro.getTitulo() + "'.");
                System.out.println("Um email foi enviado para " + usuario.getEmail() + " notificando sobre o prazo.");
            }
        }
    }
}	