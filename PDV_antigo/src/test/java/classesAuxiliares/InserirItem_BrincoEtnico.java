package classesAuxiliares;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class InserirItem_BrincoEtnico {
	private static InserirItem_BrincoEtnico instancia = new InserirItem_BrincoEtnico();
	public InserirItem inserirItem = InserirItem.getInstance();
	private Screen s = new Screen();
	private String imageString;
	Pattern itemPesquisa;

	private Pattern m_ultimoPedido = new Pattern(getImage("imgOrcamentoPedido/ultimoPedido.png")).similar(0.99f);
	//TROCA
	private Pattern m_trocaCodigo = new Pattern(getImage("imgTroca/trocaCodigo.png")).similar(0.99f);
	private Pattern m_documento = new Pattern(getImage("imgTroca/documento.png")).similar(0.90f);
	private Pattern m_valorTrocaTitulo = new Pattern(getImage("imgTroca/valorTrocaTitulo.png")).similar(0.98f);
	private Pattern m_valor4180 = new Pattern(getImage("imgTroca/valor41.80.png")).similar(0.98f);
	private Pattern m_valor8360 = new Pattern(getImage("imgTroca/valor83.60.png")).similar(0.98f);
	private Pattern m_confirmar = new Pattern(getImage("imgGeral/confirmar.png")).similar(0.90f);
	private Pattern m_ok2 = new Pattern(getImage("imgGeral/ok2.png")).similar(0.98f);

	//Produto BRINCO
	private Pattern m_nomeProdutoBrinco = new Pattern(getImage("imgItens/nomeProdutoBrinco.png")).similar(0.98f);
	private Pattern m_valorUni4180 = new Pattern(getImage("imgItens/valorUni41.80.png")).similar(0.98f);
	private Pattern m_valorTotalItem3762 = new Pattern(getImage("imgItens/valorTotalItem37.62.png")).similar(0.98f);
	private Pattern m_valorTotalItem4180 = new Pattern(getImage("imgItens/valorTotalItem41.80.png")).similar(0.98f);
	private Pattern m_valorTotalItem8360 = new Pattern(getImage("imgItens/valorTotalItem83.60.png")).similar(0.98f);
	private Pattern m_pesqRapidaBrinco = new Pattern(getImage("imgItens/pesqRapidaBrinco.png")).similar(0.98f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static InserirItem_BrincoEtnico getInstance(){
		if (instancia ==null){
			instancia = new InserirItem_BrincoEtnico();
		}
		return instancia;
	}
	
	public void informarCodigo(String qtde) throws Exception {

		inserirItem.insereItemCodigo("BR00014", qtde);
		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores(qtde);
		inserirItem.adicionaItemListagem();
	}

	public void informarCodigoDescontoEmReais(String qtde, String desconto) throws Exception{

		inserirItem.insereItemCodigo("BR00014", qtde);
		validaNome();
		inserirItem.adicionaQuantidade(qtde);
		inserirItem.adicionaDescontoReais(desconto);
		validaValoresComDesconto(qtde);
		inserirItem.adicionaItemListagem();	
	}

	public void informarCodigoDescontoEmPercentual(String qtde, String desconto) throws Exception{

		inserirItem.insereItemCodigo("BR00014", qtde);
		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		inserirItem.adicionaDescontoPercentual(desconto);
		validaValoresComDesconto(qtde);
		inserirItem.adicionaItemListagem();	
	}

	public void informarNomeInteiro(String qtde) throws Exception {
		inserirItem.insereItemNomeInteiro("BRINCO ETNICO", qtde);

		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores(qtde);
		inserirItem.adicionaItemListagem();

	}

	public void informarNomeParcial(String item, String qtde) throws Exception {
		inserirItem.insereItemNomeParcial("BRINCO E", m_pesqRapidaBrinco, qtde);
	}

	public void inserir1ItemTroca() throws Exception{
			s.type("t", KeyModifier.CTRL);

			if (s.exists(m_trocaCodigo) != null) {
				inserirItem.insereItemCodigo("BR00014", "1");
				inserirItem.adicionaQuantidade("1");

				s.wait(m_documento,20);
				if (s.exists(m_valor4180) != null) {
					s.doubleClick(m_valor4180);
					System.out.println("OK - Duplo clique no produto a ser trocado");
					s.wait(m_confirmar, 10.0);
					s.click(m_confirmar);
					s.wait(8.0);
				} else {
					assertFalse("NOK - Valor produto nao encontrado", true);
				}
			} else {
				fail("Erro na tela de associacao de item na troca");
			}

			if (s.exists(m_valorTrocaTitulo) != null) {
				System.out.println("OK - Tela de confirmar valor troca");
				s.click(m_ok2);
			}
			s.type("t", KeyModifier.CTRL);
	}

	public void inserir2ItemTroca() throws Exception{
			s.type("t", KeyModifier.CTRL);

			if (s.exists(m_trocaCodigo) != null) {
				inserirItem.insereItemCodigo("BR00014", "2");
				inserirItem.adicionaQuantidade("2");

				s.wait(m_documento,20);
				if (s.exists(m_valor8360) != null) {
					s.doubleClick(m_valor8360);
					s.wait(m_confirmar, 10.0);
					s.click(m_confirmar);
					s.wait(8.0);
				} else {
					assertFalse("NOK - Valor produto nao encontrado", true);
				}
			} else {
				fail("Erro na tela de associacao de item na troca");
			}

			if (s.exists(m_valorTrocaTitulo) != null) {
				System.out.println("OK - Tela de confirmar valor troca");
				s.click(m_ok2);
			}
			s.type("t", KeyModifier.CTRL);
	}

	public void validaNome(){
		if(s.exists(m_ultimoPedido) != null ){

			//VALIDA NOME DO PRODUTO
			if (s.exists(m_nomeProdutoBrinco) != null) {
			} else {
				assertFalse("NOK - Nome do item: Brinco", true);
			}
		}
	}

	public void validaValores(String qtde){
		// VALIDA VALOR UNITARIO
		if (s.exists(m_valorUni4180) != null) {
		} else {
			assertFalse("NOK - Valor unitario 41,80", true);
		}

		switch(qtde){
		// VALIDA TOTAL COM QUANTIDADE 1
		case "1":
			if (s.exists(m_valorTotalItem4180) != null) {
			} else {
				assertFalse("NOK - Valor total item 41,80", true);
			}
			break;
			// VALIDA TOTAL COM QUANTIDADE 2
		case "2":
			if (s.exists(m_valorTotalItem8360) != null) {
			} else {
				assertFalse("NOK - Valor total do item 83,60", true);
			}
			break;
		}
	}

	public void validaValoresComDesconto(String qtde){

		if (s.exists(m_valorUni4180) != null) {
		} else {
			assertFalse("NOK - Valor unitario 41,80", true);
		}

		switch(qtde){
		// VALIDA TOTAL COM QUANTIDADE 1
		case "1":
			if (s.exists(m_valorTotalItem3762) != null) {
			} else {
				assertFalse("NOK - Valor total item 37,62", true);
			}
			break;
		}
	}
}