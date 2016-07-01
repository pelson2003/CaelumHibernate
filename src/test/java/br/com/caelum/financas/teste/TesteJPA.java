package br.com.caelum.financas.teste;
import javax.persistence.EntityManager;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;
public class TesteJPA {

	EntityManager em;
	Conta conta;

	public void testHibernate() {

		// Criando um EntityManager
		em = new JPAUtil().getEntityManager();

		// Criando uma Conta
		conta = new Conta();
		conta.setTitular("Fabio Alameida");
		conta.setBanco("HSBC");
		conta.setNumero("123345");
		conta.setAgencia("321");

		em.getTransaction().begin();

		ExecutaTeste("remove");

		em.getTransaction().commit();
		em.close();
	}

	public void ExecutaTeste(String objetivo) {

		// M�todo Salvar
		if (objetivo == "save") {
			em.persist(conta);
		
		// M�todo Buscar onde podemos alterar tamb�m.
		} else if (objetivo == "find") {
			Conta conta = em.find(Conta.class, 10);
			// Neste ponto podemos alterar titular -> Conta.setTitular("Pedro
			// Ferreira");
			System.out.println(conta);
		
		// M�todo Merge para efetuar update, eliminando o problema do detached.
		} else if (objetivo == "merge") {
			conta.setId(14);
			String nomeatual = conta.getTitular();
			conta.setTitular("New " + nomeatual);
			em.merge(conta);
		
		// M�todo remove, temos que colocar como manager para depois remover
		} else if(objetivo == "remove"){
			Conta conta = em.find(Conta.class, 14);
			em.remove(conta);
		}
	}
}
