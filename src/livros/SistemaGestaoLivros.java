package livros;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class SistemaGestaoLivros implements Serializable{
	private static final long serialVersionUID = 1L;
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
                System.out.println("Livro retirado: " + titulo + ". Quantidade restante: " + l.getQuantidadeEstoque());
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
	public void salvarLivros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("livros.dat"))) {
            oos.writeObject(livro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
	 public void carregarLivros() {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("livros.dat"))) {
	            livro = (List<Livros>) ois.readObject();
	        } catch (FileNotFoundException e) {
	            System.out.println("Arquivo de livros não encontrado. Um novo arquivo será criado.");
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    } 
	 public List<Livros> filtrarLivros(String atributo, String valor) {
	        return livro.stream()
	                .filter(l -> {
	                    switch (atributo.toLowerCase()) {
	                        case "titulo":
	                            return l.getTitulo().equalsIgnoreCase(valor);
	                        case "autor":
	                            return l.getAutor().equalsIgnoreCase(valor);
	                        case "categoria":
	                            return l.getCategoria().equalsIgnoreCase(valor);
	                        default:
	                            return false;
	                    }
	                })
	                .collect(Collectors.toList());
	    }
}	