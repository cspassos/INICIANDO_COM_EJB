package br.com.caelum.livraria.webservice;

import java.rmi.RemoteException;

public class TesteRequestSoapComJava {

	public static void main(String[] args) throws RemoteException {
		
		//objeto para fazer a chamada remota e gerar SOAP, esse objeto é chamado de proxy.
		LivrariaWS cliente = new LivrariaWSProxy();
		
		Livro[] livros = cliente.getLivrosPeloNome("EJB");
		
		for (Livro livro : livros) {
			System.out.println(livro.getTitulo());
			System.out.println(livro.getAutor().getNome());
		}
	}
}
