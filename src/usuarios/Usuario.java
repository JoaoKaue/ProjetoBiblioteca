package usuarios;
import java.io.Serializable;

public class Usuario extends UsuarioModelo implements Serializable{
	private static final long serialVersionUID = 1l;
	
	public Usuario(String nome, String senha, String email) {
		super(nome, senha, email);
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSenha() {
		return senha;
	}
	public String getEmail() {
		return email;
	}
	
	public boolean validarUserName() {
		return nome != null && !nome.trim().isEmpty();
	}
	
	public boolean validarSenha() {
		return senha != null && senha.length() >= 8;
	}
	public boolean validarEmail() {
		return email != null && email.contains("@");
	}
}
