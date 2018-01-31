package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {
	
	@AroundInvoke//informa que o metodo realmente intercepta o fluxo
	public Object intercepta(InvocationContext context) throws Exception {
		
		long millis = System.currentTimeMillis();
		
		//chamada do metodo do dao
		Object o = context.proceed();
		
		//atraves do context conseguimos saber qual metodo foi invocado
		
		String metodo = context.getMethod().getName();
		String nomeClass = context.getTarget().getClass().getSimpleName();
		
		System.out.println("nome Classe: " + nomeClass + " Nome metodo: " + metodo);
		System.out.println("Tempo gasto: " + (System.currentTimeMillis() - millis));
		
		return o;
	}
}
