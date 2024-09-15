package livros;
import java.io.Serializable;
public class Livros extends LivroModelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Livros() {

    }
    
	public Livros(String titulo, String autor, int isbn, int quantidadeEstoque, String categoria) {
		super(titulo, autor, isbn, quantidadeEstoque, categoria);
	}
	
	public String getTitulo() {
        return titulo;
    }
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
        return autor;
    }
	public void setAutor(String autor) {
		this.autor = autor;
		
	}
	
	 public int getIsbn() {
	        return isbn;
	    }
	 
	 public int getQuantidadeEstoque() {
	        return quantidadeEstoque;
	    }
	 public void setQuantidadeEstoque(int quantidadeEstoque) {
	        this.quantidadeEstoque = quantidadeEstoque;
	    }
	 
	 public String getCategoria() {
	        return categoria;
	    }
	 public void setCategoria(String categoria) {
			this.categoria = categoria;		
		}
	 
	 @Override
	 public String toString() {
	        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + 
	               ", Quantidade em estoque: " + quantidadeEstoque + ", Categoria: " + categoria;
	    }
	 public boolean validarTitulo() {
			return titulo != null && !titulo.trim().isEmpty();
		}
	 public boolean validarAutor() {
			return autor != null && !autor.trim().isEmpty();
		}
	 public boolean validarQuantidade() {
		 return quantidadeEstoque > 0;
	 }
	 public boolean validarCategoria() {
		        return categoria.matches("[a-zA-Z]+");
		    }

	

	

}