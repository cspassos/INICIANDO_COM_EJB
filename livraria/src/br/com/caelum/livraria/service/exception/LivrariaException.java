package br.com.caelum.livraria.service.exception;

import javax.ejb.ApplicationException;

//Possui atributos para definir o comportamento referente a transação
@ApplicationException(rollback = true)//rollback se ocorrer uma exception ele nao vai salvar nada no banco, pois vai voltar tudo que foi feito.
public class LivrariaException extends RuntimeException {//RuntimeException - Assim o compilador nao obriga o desenvolvedor a fzr um tratamento explicito da exeção
								// ou seja, nao precisa colocar declaração nos metodos "throws LivrariaException".
}
