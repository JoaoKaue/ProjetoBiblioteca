package pacote1;
import java.util.InputMismatchException;
import java.util.Scanner;
import menu.Menu;
import usuarios.AdmRepositorio;
import usuarios.Usuario;
import usuarios.UsuarioRepositorio;

public class Main {

	public static void main(String[] args) {
		Scanner perguntar = new Scanner(System.in);
		UsuarioRepositorio repositorio = new UsuarioRepositorio();
		AdmRepositorio repositorioAdm = new AdmRepositorio();
		Menu menu = new Menu();
		
		repositorioAdm.carregarAdms();
		repositorio.carregarUsuarios();

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
                System.out.println("CABO");
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
        
  }	
}	
	

