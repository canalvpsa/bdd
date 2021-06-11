package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class InserirItem_CalcaJeans {
	private static InserirItem_CalcaJeans instancia = new InserirItem_CalcaJeans();
	public InserirItem inserirItem = InserirItem.getInstance();
	private Screen s = new Screen();
	Pattern itemPesquisa;
	private int quantidade;
	private String imageString;

	private Pattern m_ultimoPedido = new Pattern(
			getImage("imgOrcamentoPedido/ultimoPedido.png")).similar(0.99f);

	// Produto CALCA JEANS
	private Pattern m_nomeProdutoCalcaJeans = new Pattern(getImage("imgItens/nomeProdutoCalcaJeans.png")).similar(0.98f);
	private Pattern m_valorUni23900 = new Pattern(getImage("imgItens/valorUni239.00.png")).similar(0.98f);
	private Pattern m_valorTotalItem23900 = new Pattern(getImage("imgItens/valorTotalItem239.00.png")).similar(0.98f);
	private Pattern m_valorTotalItem47800 = new Pattern(getImage("imgItens/valorTotalItem478.00.png")).similar(0.98f);
	private Pattern m_pesqRapidaCalcaJeans = new Pattern(getImage("imgItens/pesqRapidaCalcaJeans.png")).similar(0.98f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static InserirItem_CalcaJeans getInstance(){
		if (instancia ==null){
			instancia = new InserirItem_CalcaJeans();
		}
		return instancia;
	}

	public void informarCodigo(String qtde) throws Exception {
		quantidade = Integer.parseInt(qtde);

		inserirItem.insereItemCodigo("BACF02.0003", qtde);
		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores();
		inserirItem.adicionaItemListagem();
	}

	public void informarNomeInteiro(String rotina, String qtde) throws Exception {
		inserirItem.insereItemNomeInteiro("CALCA JEANS SKINNY JEANS 36", qtde);

		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores();
	}

	public void informarNomeParcial(String rotina, String qtde) throws Exception {
		inserirItem.insereItemNomeParcial("CALCA JEANS SKINNY JEANS", m_pesqRapidaCalcaJeans, qtde);
		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores();
		inserirItem.adicionaItemListagem();
	}


	public void validaNome() {
		if (s.exists(m_ultimoPedido) != null) {

			// VALIDA NOME DO PRODUTO
			if (s.exists(m_nomeProdutoCalcaJeans) != null) {
			} else {
				assertFalse("NOK - Nome do item: Calca Jeans", true);
			}
		}
	}

	public void validaValores() {
		// VALIDA VALOR UNITARIO
		if (s.exists(m_valorUni23900) != null) {
		} else {
			assertFalse("NOK - Valor unitario 239,00", true);
		}

		// VALIDA TOTAL COM QUANTIDADE 1
		if (quantidade == 1) {
			if (s.exists(m_valorTotalItem23900) != null) {
			} else {
				assertFalse("NOK - Valor total item 239,00", true);
			}
			// VALIDA TOTAL COM QUANTIDADE 2
		} else if (quantidade == 2) {
			if (s.exists(m_valorTotalItem47800) != null) {
			} else {
				assertFalse("NOK - Valor total do item 478,00", true);
			}
		}
	}
}
