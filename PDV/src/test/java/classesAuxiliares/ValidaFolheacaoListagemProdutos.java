package classesAuxiliares;

import static org.junit.Assert.assertFalse;
import java.net.URL;
import static org.junit.Assert.fail;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ValidaFolheacaoListagemProdutos {
	private static ValidaFolheacaoListagemProdutos instancia = new ValidaFolheacaoListagemProdutos();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_colunaItens = new Pattern(getImage("imgOrcamentoPedido/colunaItens.png")).similar(0.95f);
	private Pattern m_folheacao = new Pattern(getImage("imgItens/listagemFolheacao.png")).similar(0.98f);
	private Pattern m_valorLista5000 = new Pattern(getImage("imgItens/valorLista50.00.png")).similar(0.98f);
	private Pattern m_servicoECF = new Pattern(getImage("imgOrcamentoPedido/servicoECF.png")).similar(0.98f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static ValidaFolheacaoListagemProdutos getInstance(){
		if (instancia ==null){
			instancia = new ValidaFolheacaoListagemProdutos();
		}
		return instancia;
	}

	public void itensInseridos(String qtde) throws FindFailed {
		App.focus("PDV 4.0");


		if(s.exists(m_servicoECF) != null){
			System.out.println("Impressora nao permite venda de servico");
			s.type(Key.ENTER);
			cadastro.sairPedido();
			fail("Este ECF nao aceita servico");
		}else{

			s.wait(m_colunaItens, 100.0);
			s.wait(m_folheacao,10);

			// VALIDA O NOME DO PRODUTO NA LISTAGEM DE ITENS
			if (s.exists(m_folheacao) != null) {
				// VALIDA O VALOR TOTAL PARA O ITEM COM QUANTDADE 1
				if (qtde == "1") {
					if (s.exists(m_valorLista5000) != null) {
					} else {
						assertFalse("NOK - Listagem valor item: folheacao qtde 1", true);
					}
				}
			} else {
				assertFalse("NOK - Item na listagem folheacao", true);
			}
		}
	}
}
