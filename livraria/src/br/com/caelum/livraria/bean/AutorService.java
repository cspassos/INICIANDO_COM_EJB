package br.com.caelum.livraria.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

//É criado um service para ter acesso ao DAO atreves do Bean
//@Stateless: Transformar uma classe em um EJB.
@Stateless
public class AutorService {
	
	@Inject
	AutorDao dao;
	
	//required -> ao chamar o metodo adicinar sera aberto automaticamente uma nova transação
	public void adicionar(Autor autor) {
		dao.salva(autor);
	}

	public List<Autor> todosAutores() {
		return dao.todosAutores();
	}


}
