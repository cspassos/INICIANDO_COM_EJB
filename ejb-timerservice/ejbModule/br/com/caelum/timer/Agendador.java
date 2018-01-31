package br.com.caelum.timer;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Agendador {
	
	@Schedule(hour = "*", minute="*", second="*/10")//Mostrar a hr que vai executar
	void agendador() {
		System.out.println("verificando servico externo periodicamente");
	}
}
