package usuarios;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
	private List<Usuario> usuarios;
	
	public UsuarioRepositorio() {
		this.usuarios = new ArrayList<>();
		carregarUsuarios();
	}
	
	public void adicionarUsuario(Usuario usuario) {
		if(usuario.validarUserName() && usuario.validarSenha() && usuario.validarEmail()) {
			usuarios.add(usuario);
			salvarUsuarios();
		}else {
			System.out.println("Nome ou senha ou email inválidos");
			System.out.println("Validar Nome: " + usuario.validarUserName());
	        System.out.println("Validar Senha: " + usuario.validarSenha());
	        System.out.println("Validar Email: " + usuario.validarEmail());
		}
	}
	
	public Usuario autenticarUsuarios(String nome, String senha, String email) {
		for(Usuario usuario: usuarios) {
			if(usuario.getNome().equals(nome) && usuario.getSenha().equals(senha) && usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		return null;
	}
	public void salvarUsuarios() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))){
			oos.writeObject(usuarios);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void carregarUsuarios() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.dat"))){
			usuarios = (List<Usuario>) ois.readObject();
		}catch(FileNotFoundException e) {
			System.out.println("Arquivo não encontrado, iniciando com lista vazia.");
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
