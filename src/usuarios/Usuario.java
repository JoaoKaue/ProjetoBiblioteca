package usuarios;
import livros.Livros;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Usuario extends UsuarioModelo implements Serializable{
	private static final long serialVersionUID = 1l;
	private List<Livros> livrosAlugados;
	private Map<Livros, LocalDate> dataAluguel;
	private List<String> historico;
	
	public Usuario(String nome, String senha, String email) {
		super(nome, senha, email);
		this.livrosAlugados = new ArrayList<>();
		this.dataAluguel = new HashMap<>();
		this.historico = new ArrayList<>();
	}
	
	public Map<Livros, LocalDate> getDataAluguel() {
        return dataAluguel;
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
	
	public List<String> getHistorico() {
        return historico;
    }
	
	public void alugarLivro(Livros livro) {
        livrosAlugados.add(livro);
        dataAluguel.put(livro, LocalDate.now());
        historico.add("Alugou: " + livro.getTitulo() + " em " + LocalDateTime.now());
    }
	
	public void devolverLivro(Livros livro) {
        livrosAlugados.remove(livro);
        LocalDate dataDevolucao = LocalDate.now();
        LocalDate dataAluguelLivro = dataAluguel.remove(livro);
        historico.add("Devolveu: " + livro.getTitulo() + " em " + LocalDateTime.now() + (dataDevolucao.isAfter(dataAluguelLivro.plusDays(3)) ? " (Atrasado)" : ""));
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
