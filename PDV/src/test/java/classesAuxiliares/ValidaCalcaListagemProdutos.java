package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ValidaCalcaListagemProdutos {
	private static ValidaCalcaListagemProdutos instancia = new ValidaCalcaListagemProdutos();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_colunaItens = new Pattern(
			getImage("imgOrcamentoPedido/colunaItens.png")).similar(0.95f);

	private Pattern m_calcaJeans = new Pattern(getImage("imgItens/listagemCalcaJeans.png")).similar(0.98f);
	private Pattern m_valorLista23900 = new Pattern(getImage("imgItens/valorLista239.00.png")).similar(0.98f);
	private Pattern m_valorLista47800 = new Pattern(getImage("imgItens/valorLista478.00.png")).similar(0.98f);


	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static ValidaCalcaListagemProdutos getInstance(){
		if (instancia ==null){
			instancia = new ValidaCalcaListagemProdutos();
		}
		return instancia;
	}

	public void itensInseridos(String qtde) throws FindFailed {

		App.focus("PDV 4.0");
		s.wait(m_colunaItens, 100.0);
		s.wait(m_calcaJeans,10);

		// VALIDA O NOME DO PRODUTO NA LISTAGEM DE ITENS
		if (s.exists(m_calcaJeans) != null) {

			// VALIDA O VALOR TOTAL PARA O ITEM COM QUANTDADE 1
			if (qtde == "1") {
				if (s.exists(m_valorLista23900) != null) {
				} else {
					assertFalse("NOK - Listagem valor item: calca qtde 1", true);
				}
				// VALIDA O VALOR TOTAL PARA O ITEM COM QUANTDADE 1
			} else if (qtde == "2") {
				if (s.exists(m_valorLista47800) != null) {
				} else {
					assertFalse("NOK - Listagem valor item: calca qtde 2", true);
				}
			}
		} else {
			assertFalse("NOK - Item na listagem calca", true);
		}
	}
}
