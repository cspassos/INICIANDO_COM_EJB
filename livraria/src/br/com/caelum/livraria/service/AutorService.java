package br.com.caelum.livraria.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.service.exception.LivrariaException;

//É criado um service para ter acesso ao DAO atreves do Bean
//@Stateless: Transformar uma classe em um EJB.
@Stateless
public class AutorService {
	
	@Inject
	AutorDao dao;
	
	//required do autorDao-> ao chamar o metodo adicinar sera aberto automaticamente uma nova transação
	public void adicionar(Autor autor){
		dao.salva(autor);
		
		throw new LivrariaException();
	}

	public List<Autor> todosAutores() {
		return dao.todosAutores();
	}


}
