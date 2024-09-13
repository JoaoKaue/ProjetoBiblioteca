package usuarios;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdmRepositorio {
	private List<Adm> adms;
	
	public AdmRepositorio() {
		this.adms = new ArrayList<>();
		carregarAdms();
	}
	
	public void adicionarAdm(Adm adm) {
		if(adm.validarUserName() && adm.validarSenha() && adm.validarEmail()) {
			adms.add(adm);
			salvarAdms();
	    }else {
	    	System.out.println("Nome ou senha ou email inválidos");
	        System.out.println("Validar Nome: " + adm.validarUserName());
	        System.out.println("Validar Senha: " + adm.validarSenha());
	        System.out.println("Validar Email: " + adm.validarEmail());
	}
}		
	public Adm autenticarAdms(String nome, String senha, String email) {
		for(Adm adm: adms) {
			if(adm.getNome().equals(nome) && adm.getSenha().equals(senha) && adm.getEmail().equals(email)) {
				return adm;
			}
		}
	return null;
  }
	
	public void salvarAdms() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("adms.dat"))){
			oos.writeObject(adms);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarAdms() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("adms.dat"))){
			adms = (List<Adm>) ois.readObject();
		}catch(FileNotFoundException e) {
			System.out.println("Arquivo não encontrado, iniciando com lista vazia.");
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}