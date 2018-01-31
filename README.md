# INICIANDO_COM_EJB #

 
		* Gerenciar os serviços do servidor de aplicação:
		* Pool de conexões: conexões com o banco;
		* Persistencia: frameworks de mapeamento. ex. hibernate;
		* Tratamento de exceções;
		* Segurança: ex: segurança do webservie do mesmo modo que sua aplicação.
		* Serviços de Transação;
 * É pelo do EJB que temos acesso que o servidor nos oferece sem se preocupar como cada um deles foi inicializado.
 
  ## Anotações: 
	* Transformar uma classe em um EJB: @Stateless.
	* @Singleton - faz com que eu tenha apenas uma instancia dessa classe, assim sem precisa mexer no xml, ou seja, vai carregar o banco apenas umas vez
	* @PostConstruct - tambem chamado de callback - É chamado pelo proprio EJB container.
	* @Startup - Faz com que o singleton seja usado desde o inicio da aplicação, ou seja, assim que startar a aplicação.
	* @TransactionManagement(TransactionManagementType.BEAN) - Bean faz com que o session bean gerencie a transação, assim aceitando begin e commit, mesmo com ejb. E em seguida utilizar o UseTransaction do jpa
	* @TransactionAttribute(TransactionAttributeType.REQUIRED)- padrão de configuração pra cada metodo. REQUIRED - sera aberto automaticamente uma nova transação
	* @TransactionManagement(TransactionManagementType.CONTAINER) - para definirmos explicitamente que quem controla nossas transações é o container.
	* @ApplicationException(rollback = true) - rollback se ocorrer uma exception ele nao vai salvar nada no banco, pois vai voltar tudo que foi feito.
	* @Interceptors({LogInterceptador.class})- Informar que vai usar o interceptador da classe LogInterceptador. //Para nao precisar utilizar a anotação do interceptador em todas as classes, foi criado um aquivo chamado ejb-jar
	* @WebService - para o padrão ejb publicar a classe com os padroes soap e wsdl.
	* @WebParam - na msg de apresentação do webservice via ficar o name: nomeDoLivro.
	* @WebResult(name="autores") - nome do elemento que representa o retorno
	* LivrariaWSProxy() - objeto para fazer a chamada remota e gerar SOAP, esse objeto é chamado de proxy.
	* @Schedule(hour = "*", minute="*", second="*/10")//Mostrar a hr que vai executar
	
	* extends RuntimeException {//RuntimeException - Assim o compilador nao obriga o desenvolvedor a fzr um tratamento explicito da exeção, ou seja, nao precisa colocar declaração nos metodos "throws LivrariaException".
	* Quem faz o gerenciamento do Bean é o EJB.
	* nao precisa instanciar o meu dao, pois o dao é uma dependencia que sera injetada pelo container.
	* Os Sesssion Bean são Thread safe, ou seja, apenas uma thread pode usar o dao ao mesmo tempo. ex: se eu cadastro um autor e depois cadastro outro ele primeiro termina um apra depois ir para o segundo.
	* @PersistenceContext - Faz com que o EJB container injete uma entityManager.
	* O EJB automaticamente ja abre e fecha a transação com o banco. Isso ocorre no jta do persistence.
	* O EJB container usa o JTA(Java Transaction API ) para controle de transações.
	
 ## Perguntas:
 
	1. Qual a diferença entre mandatory e requered? 
		R= É obrigatorio ter uma transação, então quem o chamou deve fornecer uma transação. O requered informa que precisa de uma transação,
			se quando chamado houver uma transacao, ele utiliza esta transacao ja criada, se nao houver cria uma transacao.

	2. Para que serve o REQUIRES_NEW?
	R= Sempre abre uma nova transacao.
	
	3. Para que serve o NOT_SUPPORTED?
		R= Indica que nao havera uma transação.
	
	4. Para que serve o SUPPORTED?
		R= Indica que pode haver transacao.
	
	5. Se não tiver CDI como eu utilizo o EJB na Classe para injeção, com qual anotação?
		R= @EJB, pois essa anotação serve para fazermos a injeção de dependência do nosso EJB,
	
	
	
