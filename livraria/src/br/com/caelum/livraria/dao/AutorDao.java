package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.caelum.livraria.interceptador.LogInterceptador;
import br.com.caelum.livraria.modelo.Autor;

//@Stateless: Transformar uma classe em um EJB.
@Stateless				 
@TransactionManagement(TransactionManagementType.CONTAINER)//para definirmos explicitamente que quem controla nossas transações é o container.
//@Interceptors({LogInterceptador.class})//Informar que vai usar o interceptador da classe LogInterceptador
public class AutorDao { //Para nao precisar utilizar a anotação do interceptador em todas as classes, foi criado um aquivo chamado ejb-jar

	@PersistenceContext//Faz com que o EJB container injete uma entityManager.
	private EntityManager manager;

	@PostConstruct//tambem chamado de callback - É chamado pelo proprio EJB container
	void aposCriacao() {
		System.out.println("AutorDao foi criado");
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)//padrão de configuração pra cada metodo. REQUIRED - sera aberto automaticamente uma nova transação
	public void salva(Autor autor) {
		System.out.println("salvando Autor " + autor.getNome());
		
//		try {//Apenas uma thread pode usar o dao ao mesmo tempo. ex: se eu cadastro um autor e depois cadastro 
//					outro ele primeiro termina um apra depois ir para o segundo.	
//		Thread.sleep(20000);//20s
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		//O EJB automaticamente ja abre e fecha a transação com o banco. Isso ocorre no jta do persistence. E apenas se nao USAR O BEAN na classe
		manager.persist(autor);
			
		System.out.println("salvou Autor " + autor.getNome());
			
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class, autorId);
		return autor;
	}
	
}
