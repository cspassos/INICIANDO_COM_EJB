package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.modelo.Autor;

//@Stateless: Transformar uma classe em um EJB.
@Stateless				//Bean faz com que o session bean gerencie a transação, assim aceitando begin e commit, mesmo com ejb. 
@TransactionManagement(TransactionManagementType.BEAN)//e em seguida utilizar o UseTransaction do jpa
public class AutorDao_EX_BEAN_BEGIN_COMMIT { //Se tivesse usando o CONTAINER no lugar o BEAN, nao precisava utilizar begin e commit, pois o ejb ja gerenciava.

	@PersistenceContext//Faz com que o EJB container injete uma entityManager.
	private EntityManager manager;
	
	@Inject
	UserTransaction tx;

	@PostConstruct//tambem chamado de callback - É chamado pelo proprio EJB container
	void aposCriacao() {
		System.out.println("AutorDao foi criado");
	}
	
	//Como estou usando o @TransactionManagement(TransactionManagementType.BEAN) nao preciso usar o required, agora, caso usasse o 
	//@TransactionManagement(TransactionManagementType.CONTAINER) ai precisaria usar o required.
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)//padrão de configuração pra cada metodo. REQUIRED - sera aberto automaticamente uma nova transação
	public void salva(Autor autor) {
		System.out.println("salvando Autor " + autor.getNome());
		
//		try {//Apenas uma thread pode usar o dao ao mesmo tempo. ex: se eu cadastro um autor e depois cadastro 
//					outro ele primeiro termina um apra depois ir para o segundo.	
//		Thread.sleep(20000);//20s
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		try {
			tx.begin();
			//O EJB automaticamente ja abre e fecha a transação com o banco. Isso ocorre no jta do persistence. E apenas se nao USAR O BEAN na classe
			manager.persist(autor);
			tx.commit();
			
			System.out.println("salvou Autor " + autor.getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}
	
}
