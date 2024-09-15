package livros;
import java.util.ArrayList;
import java.util.List;
public class SistemaGestaoLivros {
	
	private List<Livros> livro;
	
	public SistemaGestaoLivros() {
		this.livro = new ArrayList<>();	
	}
	
	public void adicionarLivros(Livros livros) {
		if(livros.validarTitulo() && livros.validarAutor() && livros.validarQuantidade() && livros.validarCategoria()) {
			livro.add(livros);
			System.out.println("Livro adicionado: " + livros.getTitulo());
		}else {
			System.out.println("Titulo ou autor ou quantidade ou categoria inválidos");
			System.out.println("Validar titulo: " + livros.validarTitulo());
	        System.out.println("Validar autor: " + livros.validarAutor());
	        System.out.println("Validar quantidade: " + livros.validarQuantidade());
	        System.out.println("Validar categoria: " + livros.validarCategoria());
		}
	}
	
	public boolean retirarLivro(String titulo) {
        for (Livros l : livro) {
        	System.out.println("Verificando livro: " + l.getTitulo() + " com quantidade: " + l.getQuantidadeEstoque());
            if (l.getTitulo().equals(titulo) && l.getQuantidadeEstoque() > 0) {
                l.setQuantidadeEstoque(l.getQuantidadeEstoque() - 1);
                System.out.println("Livro retirado: " + titulo);
                return true;
            }
        }
        return false;
    }
	public boolean removerLivro(String titulo) {
		return livro.removeIf(l -> l.getTitulo().equals(titulo));
	}
	
	public Livros buscarLivros(String titulo) {
		for(Livros l: livro) {
			if(l.getTitulo().equals(titulo)) {
				return l;
			}
		}
		return null;
	}
	
	public List<Livros> getLivros() {
        return livro;
    }
	
	public void exibirTodosOsLivros() {
        for (Livros l : livro) {
            System.out.println(l);
        }
    }
	
}	

