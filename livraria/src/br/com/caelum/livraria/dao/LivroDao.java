package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Livro;

//@Stateless: Transformar uma classe em um EJB.
@Stateless
public class LivroDao {

	@PersistenceContext//Faz com que o EJB container injete uma entityManager.
	private EntityManager manager;
	
	public void salva(Livro livro) {
		manager.persist(livro);
	}
	
	public List<Livro> todosLivros() {
		return manager.createQuery("select li from Livro li", Livro.class).getResultList();
	}

	public List<Livro> livrosPeloNome(String nome) {
		
		return manager.createQuery("from Livro li where li.titulo like :nome", Livro.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
}
