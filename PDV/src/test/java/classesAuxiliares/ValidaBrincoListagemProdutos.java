package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ValidaBrincoListagemProdutos {
	private static ValidaBrincoListagemProdutos instancia = new ValidaBrincoListagemProdutos();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_colunaItens = new Pattern(getImage("imgOrcamentoPedido/colunaItens.png")).similar(0.95f);

	private Pattern m_brinco = new Pattern(getImage("imgItens/listagemBrinco.png")).similar(0.98f);
	private Pattern m_brincoTrocado = new Pattern(getImage("imgItens/listagemBrincoTrocado.png")).similar(0.98f);
	private Pattern m_valorLista4180Trocado = new Pattern(getImage("imgItens/valorLista41.80Trocado.png")).similar(0.98f);
	private Pattern m_valorLista8360Trocado = new Pattern(getImage("imgItens/valorLista83.60Trocado.png")).similar(0.98f);

	private Pattern m_itemBrincoQtd1SemDesconto = new Pattern(getImage("imgItens/itemBrincoQtd1SemDesconto.png")).similar(0.98f);
	private Pattern m_itemBrincoQtd1ComDesconto = new Pattern(getImage("imgItens/itemBrincoQtd1ComDesconto.png")).similar(0.98f);
	private Pattern m_itemBrincoQtd2ComDesconto = new Pattern(getImage("imgItens/itemBrincoQtd2ComDesconto.png")).similar(0.98f);
	private Pattern m_itemBrincoQtd2SemDesconto = new Pattern(getImage("imgItens/itemBrincoQtd2SemDesconto.png")).similar(0.98f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static ValidaBrincoListagemProdutos getInstance(){
		if (instancia ==null){
			instancia = new ValidaBrincoListagemProdutos();
		}
		return instancia;
	}

	public void itensInseridos(String qtde, Boolean desconto) throws FindFailed {

		App.focus("PDV 4.0");
		s.wait(m_colunaItens, 100.0);
		s.wait(m_brinco,10);

		// VALIDA O NOME DO PRODUTO NA LISTAGEM DE ITENS
		if (s.exists(m_brinco) != null) {


			switch(qtde){

			case "1":

				if(desconto == true){
					s.exists(m_itemBrincoQtd1ComDesconto);
				}

				if(desconto == false){
					s.exists(m_itemBrincoQtd1SemDesconto);
				}

				break;

			case "2":
				if(desconto == true){
					s.exists(m_itemBrincoQtd2ComDesconto);
				}

				if(desconto == false){
					s.exists(m_itemBrincoQtd2SemDesconto);
				}

				break;
			}
		} else {
			assertFalse("NOK - Item na listagem brinco", true);
		}
	}


	public void itemTrocado(String qtde){
		if (s.exists(m_brincoTrocado) != null) {

			if (qtde == "1") {
				if (s.exists(m_valorLista4180Trocado) != null) {
				} else {
					assertFalse("NOK - Listagem valor item: BRINCO ETNICO qtde 1", true);
				}

			} else if (qtde == "2") {
				if (s.exists(m_valorLista8360Trocado) != null) {
				} else {
					assertFalse("NOK - Listagem valor item: BRINCO ETNICO qtde 2", true);
				}
			}

		} else {
			assertFalse("NOK - Item trocado nao esta na listagem: BRINCO ETNICO", true);
		}
	}
}