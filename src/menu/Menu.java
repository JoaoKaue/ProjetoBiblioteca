package menu;
import java.util.Scanner;
import usuarios.Adm;
import usuarios.AdmRepositorio;
import usuarios.Usuario;
import usuarios.UsuarioRepositorio;
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
			System.out.println("Cadastrar novo adm 1\n Entrar em uma conta 2\n Sair do programa 3");
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
				System.out.println("Digite o nome de usuario: ");
				String nome = perguntar.nextLine();
				System.out.println("Digite a senha do usuario: ");
				String senha = perguntar.nextLine();
				System.out.println("Digite o email do usuario: ");
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
}	



