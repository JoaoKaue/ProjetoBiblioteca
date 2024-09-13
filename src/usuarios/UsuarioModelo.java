package usuarios;
import java.io.Serializable;

public abstract class UsuarioModelo implements Serializable {
	protected String nome;
	protected String senha;
	protected String email;
	
	public UsuarioModelo(String nome, String senha, String email) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}
	
	public abstract boolean validarUserName();
	public abstract boolean validarSenha();
	public abstract boolean validarEmail(); 

}
