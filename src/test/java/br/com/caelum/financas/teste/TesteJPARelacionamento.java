package br.com.caelum.financas.teste;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;
public class TesteJPARelacionamento {

	EntityManager em;
	Conta conta;
	Movimentacao movimentacao;

	public void testHibernate() {

		// Criando um EntityManager
		em = new JPAUtil().getEntityManager();

		// Criando uma Conta
		conta = new Conta();
		conta.setTitular("Maria dos Santos");
		conta.setBanco("001 - BANCO DO BRASIL");
		conta.setNumero("16987-8");
		conta.setAgencia("6543");
		
		// Criando uma movimenta��o
		movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Conta de luz - ABRIL/2012 ");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("135.0"));
		
		// Associando Movimenta��o e Conta
		movimentacao.setConta(conta);
		

		em.getTransaction().begin();

		//ExecutaTesteConta("save");
		ExecutaTesteMovimentacao("save");
		
		em.getTransaction().commit();
		em.close();
	}

	
	public void ExecutaTesteMovimentacao(String objetivo) {
		// M�todo Salvar
		if (objetivo == "save") {
			em.persist(conta);
			em.persist(movimentacao);
		}
	}
	
	
	
	public void ExecutaTesteConta(String objetivo) {

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
