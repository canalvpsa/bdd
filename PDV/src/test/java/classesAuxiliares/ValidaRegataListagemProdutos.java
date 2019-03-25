package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ValidaRegataListagemProdutos {
	private static ValidaRegataListagemProdutos instancia = new ValidaRegataListagemProdutos();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_colunaItens = new Pattern(
			getImage("imgOrcamentoPedido/colunaItens.png")).similar(0.95f);

	private Pattern m_regata = new Pattern(getImage("imgItens/listagemRegata.png")).similar(0.98f);
	private Pattern m_valorLista5990 = new Pattern(getImage("imgItens/valorLista59.90.png")).similar(0.98f);
	private Pattern m_valorLista11980 = new Pattern(getImage("imgItens/valorLista119.80.png")).similar(0.98f);


	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static ValidaRegataListagemProdutos getInstance(){
		if (instancia ==null){
			instancia = new ValidaRegataListagemProdutos();
		}
		return instancia;
	}

	public void itensInseridos(String qtde, String permiteSaldoNegativo) throws FindFailed {

		if(permiteSaldoNegativo.equals("Y")){
			App.focus("PDV 4.0");
			s.wait(m_colunaItens, 100.0);
			s.wait(m_regata,10);

			// VALIDA O NOME DO PRODUTO NA LISTAGEM DE ITENS
			if (s.exists(m_regata) != null) {

				// VALIDA O VALOR TOTAL PARA O ITEM COM QUANTDADE 1
				if (qtde == "1") {
					if (s.exists(m_valorLista5990) != null) {
					} else {
						assertFalse("NOK - Listagem valor item: regata qtde 1", true);
					}
					// VALIDA O VALOR TOTAL PARA O ITEM COM QUANTDADE 2
				} else if (qtde == "2") {
					if (s.exists(m_valorLista11980) != null) {
					} else {
						assertFalse("NOK - Listagem valor item: regata qtde 2", true);
					}
				}
			} else {
				assertFalse("NOK - Item na listagem brinco", true);
			}
		}
	}
}
