package br.com.caelum.financas.teste;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;
public class TesteConsulta {

	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();

		// Criando uma Conta
		
			Conta conta = new Conta();
			conta.setId(7);
		
		// Usamos via objeto, recebe um JPQL consulta através da classe do modelo.
		
		// Position , pois usamos a posição para passar o paametro
		// Query query = manager.createQuery("select m from Movimentacao m where m.conta=?1");
		// query.setParameter(7, conta);
		
		// Name, pois utilizamos um alias para passar a informação
		Query query = manager.createQuery("select m from Movimentacao m where m.conta=:pConta" 
				+ " and m.tipoMovimentacao =:pTipo"
				+ " order by m.valor desc");
		
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
				
		List<Movimentacao> movimentacoes = query.getResultList();
		
		for (Movimentacao m: movimentacoes){
			System.out.println("\nDescricao: " + m.getDescricao());
			System.out.println("\nValor: " + m.getValor());
		}		
	}
}
