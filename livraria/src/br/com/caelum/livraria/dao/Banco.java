package br.com.caelum.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Usuario;

//@Singleton - faz com que eu tenha apenas uma instancia/objeto dessa classe, assim sem precisa mexer no xml;
//ou seja, vai carregar o banco apenas umas vez
@Singleton
@Startup//Faz com que o singleton seja usado desde o inicio da aplicação, ou seja, assim que startar a aplicação.
public class Banco {
	
	//pool de objetos [livros, autores e usuarios]
	public static List<Livro> livros = new ArrayList<Livro>();
	public static List<Autor> autores = new ArrayList<Autor>();
	public static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private static int chave = 1;
	
	static {
		inicializaBanco();
	}
	
	@PostConstruct//tambem chamado de callback - É chamado pelo proprio EJB container
	void aposCriacao() {
		System.out.println("acabou de criar o banco ");
	}
	
	public void save(Livro livro) {
		livro.setId(chave++);
		livros.add(livro);
	}
	
	public List<Livro> listaLivros() {
		return livros;
	}
	
	public void save(Autor autor) {
		autor.setId(chave++);
		autores.add(autor);
	}
	
	public List<Autor> listaAutores() {
		return autores;
	}

	public Autor buscaPelaId(Integer autorId) {

		for (Autor autor : autores) {
			if(autor.getId().equals(autorId)) {
				return autor;
			}
		}
		return null;
	}
	
	public Usuario buscaPeloNome(String nome) {
		for (Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(nome)) {
				return usuario;
			}
		}
		
		return null;
	}
	
	private static void inicializaBanco() {
		Autor silveira = new Autor(chave++, "Paulo Silveira");
		Autor cordeiro = new Autor(chave++, "Gilliard Cordeiro");
		Autor coelho = new Autor(chave++, "Hébert Coelho ");
		
		autores.add(silveira);
		autores.add(cordeiro);
		autores.add(coelho);
		
		livros.add(new Livro("Java 8 prático",silveira));
		livros.add(new Livro("Lógica de Programação",silveira));

		livros.add(new Livro("CDI: Integre as dependências",cordeiro));
		livros.add(new Livro("JSF e JPA",cordeiro));

		livros.add(new Livro("JPA Efficaz",coelho));
		livros.add(new Livro("JSF Efficaz",coelho));
		
		usuarios.add(new Usuario("admin", "pass"));
	}

}
