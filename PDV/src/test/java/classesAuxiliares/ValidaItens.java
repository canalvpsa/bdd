package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ValidaItens {
	private static ValidaItens instancia = new ValidaItens();
	private Screen s = new Screen();
	private String imageString;

	private Pattern m_brincoTrocado = new Pattern(getImage("imgItens/listagemBrincoTrocado.png")).similar(0.98f);
	private Pattern m_valorLista4180Trocado = new Pattern(getImage("imgItens/valorLista41.80Trocado.png")).similar(0.98f);
	private Pattern m_valorLista8360Trocado = new Pattern(getImage("imgItens/valorLista83.60Trocado.png")).similar(0.98f);

	private Pattern m_itemListaNome, m_itemListaQtde1, m_itemListaQtde2;
	private String nomeProduto;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static ValidaItens getInstance(){
		if (instancia ==null){
			instancia = new ValidaItens();
		}
		return instancia;
	}

	public void validaItemTrocado(String produto, String qtde) throws FindFailed {

		switch (produto) {
		case "BR00014":
			m_itemListaNome = m_brincoTrocado;
			nomeProduto = "BRINCO ETNICO";
			m_itemListaQtde1 = m_valorLista4180Trocado;
			m_itemListaQtde2 = m_valorLista8360Trocado;
			break;

		default:
			System.out.println("Produto nao encontrado!");
			s.type(Key.ESC);
			break;
		}

		if (s.exists(m_itemListaNome) != null) {
			System.out.println("OK - Item trocado na listagem " + nomeProduto);

			if (qtde == "1") {
				if (s.exists(m_itemListaQtde1) != null) {
					System.out.println("OK - Valores item: " + nomeProduto + " qtde 1");
				} else {
					System.out.println("NOK - Valores item: " + nomeProduto + " qtde 1");
					assertFalse("NOK - Listagem valor item: " + nomeProduto + "qtde 1", true);
				}

			} else if (qtde == "2") {
				if (s.exists(m_itemListaQtde2) != null) {
					System.out.println("OK - Valores item: " + nomeProduto + " qtde 2");
				} else {
					System.out.println("NOK - Valores item: " + nomeProduto + " qtde 2");
					assertFalse("NOK - Listagem valor item: " + nomeProduto + " qtde 2", true);
				}
			}

		} else {
			System.out.println("NOK - Item trocado nao esta na listagem: " + nomeProduto);
			assertFalse("NOK - Item trocado nao esta na listagem: " + nomeProduto, true);
		}
	}
}