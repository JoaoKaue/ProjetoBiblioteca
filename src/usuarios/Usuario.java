package usuarios;
import livros.Livros;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends UsuarioModelo implements Serializable{
	private static final long serialVersionUID = 1l;
	private List<Livros> livrosAlugados;
	
	public Usuario(String nome, String senha, String email) {
		super(nome, senha, email);
		this.livrosAlugados = new ArrayList<>();
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
	
	public List<Livros> getLivrosAlugados() {
        return livrosAlugados;
    }
	
	public void alugarLivro(Livros livro) {
        livrosAlugados.add(livro);
    }
	
	public void devolverLivro(Livros livro) {
        livrosAlugados.remove(livro);
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
