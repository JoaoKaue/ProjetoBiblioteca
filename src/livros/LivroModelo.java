package livros;
import java.io.Serializable;
public abstract class LivroModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String titulo;
	protected String autor;
	protected int isbn;
	protected int quantidadeEstoque;
	protected String categoria;
	
	public LivroModelo() {
    }
    public LivroModelo(String titulo, String autor, int isbn, int quantidadeEstoque, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }
    public abstract String toString();     
}