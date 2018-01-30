package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.com.caelum.livraria.modelo.Livro;

//@Stateless: Transformar uma classe em um EJB.
@Stateless
public class LivroDao {

	private Banco banco = new Banco();
	
	public void salva(Livro livro) {
		banco.save(livro);
	}
	
	public List<Livro> todosLivros() {
		return banco.listaLivros();
	}
	
}
