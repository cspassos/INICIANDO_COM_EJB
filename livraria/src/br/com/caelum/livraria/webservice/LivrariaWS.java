package br.com.caelum.livraria.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

@WebService//para o padrão ejb publicar a classe com os padroes soap e wsdl
@Stateless
public class LivrariaWS {

	@Inject
	LivroDao dao;
	
	@WebResult(name="autores")//nome do elemento que representa o retorno									
	public List<Livro> getLivrosPeloNome(@WebParam(name="titulo")String nome){//@WebParam -> na msg de apresentação do webservice via ficar titulo: nomeDoLivro
		System.out.println("LivrariaWS: procurando pelo nome: " + nome);
		return dao.livrosPeloNome(nome);
	}
}
