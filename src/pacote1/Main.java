package pacote1;
import java.util.InputMismatchException;
import java.util.Scanner;
import menu.Menu;
import usuarios.AdmRepositorio;
import livros.SistemaGestaoLivros;
import usuarios.UsuarioRepositorio;
public class Main {
	public static void main(String[] args) {
		Scanner perguntar = new Scanner(System.in);
		UsuarioRepositorio repositorio = new UsuarioRepositorio();
		AdmRepositorio repositorioAdm = new AdmRepositorio();
		SistemaGestaoLivros livroRepositorio = new SistemaGestaoLivros();
		Menu menu = new Menu(livroRepositorio);
		repositorioAdm.carregarAdms();
		repositorio.carregarUsuarios();
		livroRepositorio.carregarLivros();
        while(true) {
        	try {
        	System.out.println("------Bem Vindo------");
			System.out.println("Você é: \nUsuário 1\nAdministrador 2\nFechar programa 3");
			System.out.println("Digite uma opcão: ");
			int numero = Integer.parseInt(perguntar.nextLine());
			if(numero == 1) {
				menu.menuUsuario();
			} else if (numero == 2) {
				menu.menuAdm();
            } else if (numero == 3) {
                System.out.println("Encerrando...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }catch(InputMismatchException | NumberFormatException e) {
        	System.out.println("Erro: entrada inválida. Por favor, digite um número.");
        }
    } 
        menu.salvarAdms();
        menu.salvarUsuarios();
        livroRepositorio.salvarLivros();  
  }	
}	