package br.com.caelum.livraria.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.service.AutorService;
import br.com.caelum.livraria.service.exception.LivrariaException;

@Model
public class AutorBean {
	
	private Autor autor = new Autor();
	
	@Inject//Nao precisa instanciar o meu dao, pois o dao é uma dependencia que sera injetada pelo container.
	private AutorService service;
	
	public Autor getAutor() {
		return autor;
	}
	
	public void cadastra() {
		this.service.adicionar(autor);
		this.autor = new Autor();
	}
	
	public List<Autor> getAutores() {
		return this.service.todosAutores();
	}
}
