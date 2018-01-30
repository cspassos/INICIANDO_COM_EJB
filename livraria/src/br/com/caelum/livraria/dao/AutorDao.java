package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.modelo.Autor;

//@Stateless: Transformar uma classe em um EJB.
@Stateless
public class AutorDao {

	@Inject
	private Banco banco;

	@PostConstruct//tambem chamado de callback - É chamado pelo proprio EJB container
	void aposCriacao() {
		System.out.println("AutorDao foi criado");
	}
	
	public void salva(Autor autor) {
		System.out.println("salvando Autor " + autor.getNome());
		
//		try {//Apenas uma thread pode usar o dao ao mesmo tempo. ex: se eu cadastro um autor e depois cadastro 
//					outro ele primeiro termina um apra depois ir para o segundo.	
//		Thread.sleep(20000);//20s
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		banco.save(autor);
		System.out.println("salvou Autor " + autor.getNome());
	}
	
	public List<Autor> todosAutores() {
		return banco.listaAutores();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.banco.buscaPelaId(autorId);
		return autor;
	}
	
}