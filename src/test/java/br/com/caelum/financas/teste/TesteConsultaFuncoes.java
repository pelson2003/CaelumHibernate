package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaFuncoes {
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Conta conta = new Conta();
		conta.setId(7);
		
		TypedQuery<Double> query = 
				manager.createNamedQuery("mediaDaContaPeloTipodeMovimentacao", Double.class);
		
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
				
		Double media = query.getSingleResult();
		System.out.println(media);
	}
}
