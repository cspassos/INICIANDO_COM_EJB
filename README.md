# INICIANDO_COM_EJB #

 
		* Gerenciar os serviços do servidor de aplicação:
		* Pool de conexões: conexões com o banco;
		* Persistencia: frameworks de mapeamento. ex. hibernate;
		* Tratamento de exceções;
		* Segurança: ex: segurança do webservie do mesmo modo que sua aplicação.
		* Serviços de Transação;
 * É pelo do EJB que temos acesso que o servidor nos oferece sem se preocupar como cada um deles foi inicializado.
 
  # Anotações: 
	* Transformar uma classe em um EJB: @Stateless.
	* @Singleton - faz com que eu tenha apenas uma instancia dessa classe, assim sem precisa mexer no xml, ou seja, vai carregar o banco apenas umas vez
	* @PostConstruct - tambem chamado de callback - É chamado pelo proprio EJB container.
	* @Startup - Faz com que o singleton seja usado desde o inicio da aplicação, ou seja, assim que startar a aplicação.
	* @TransactionManagement(TransactionManagementType.BEAN) - Bean faz com que o session bean gerencie a transação, assim aceitando begin e commit, mesmo com ejb. E em seguida utilizar o UseTransaction do jpa
	* Quem faz o gerenciamento do Bean é o EJB.
	* nao precisa instanciar o meu dao, pois o dao é uma dependencia que sera injetada pelo container.
	* Os Sesssion Bean são Thread safe, ou seja, apenas uma thread pode usar o dao ao mesmo tempo. ex: se eu cadastro um autor e depois cadastro outro ele primeiro termina um apra depois ir para o segundo.
	* @PersistenceContext - Faz com que o EJB container injete uma entityManager.
	* O EJB automaticamente ja abre e fecha a transação com o banco. Isso ocorre no jta do persistence.
	
	
	
