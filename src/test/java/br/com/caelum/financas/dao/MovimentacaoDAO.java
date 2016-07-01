package br.com.caelum.financas.dao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
public class MovimentacaoDAO {
	private EntityManager manager;
	public MovimentacaoDAO(EntityManager manager){
		this.manager = manager;
	}
	public Double mediaDaContaPeloTipo(Conta conta, TipoMovimentacao entrada) {
		TypedQuery<Double> query = manager.createQuery("select avg (m.valor) from Movimentacao m where m.conta=:pConta" 
				+ " and m.tipoMovimentacao =:pTipo", Double.class);	
			query.setParameter("pConta", conta);
			query.setParameter("pTipo", TipoMovimentacao.SAIDA);
			Double media = query.getSingleResult();
		return media;
	}
}
