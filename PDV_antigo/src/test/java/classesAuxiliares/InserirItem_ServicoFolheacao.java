package classesAuxiliares;
import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class InserirItem_ServicoFolheacao {
	private static InserirItem_ServicoFolheacao instancia = new InserirItem_ServicoFolheacao();
	public InserirItem inserirItem = InserirItem.getInstance();
	private Screen s = new Screen();
	private String imageString;
	Pattern itemPesquisa;
	private int quantidade;
	
	private Pattern m_ultimoPedido = new Pattern(getImage("imgOrcamentoPedido/ultimoPedido.png")).similar(0.99f);

	// Servico FOLHEACAO A OURO
		private Pattern m_pesqRapidaFolheacao = new Pattern(getImage("imgItens/pesqRapidaFolheacao.png")).similar(0.98f);
		private Pattern m_nomeServicoFolheacao = new Pattern(getImage("imgItens/nomeServicoFolheacao.png")).similar(0.98f);
		private Pattern m_valorUni5000 = new Pattern(getImage("imgItens/valorUni50.00.png")).similar(0.98f);
		private Pattern m_valorTotalItem5000 = new Pattern(getImage("imgItens/valorTotalItem50.00.png")).similar(0.98f);
		private Pattern m_valorTotalItem10000 = new Pattern(getImage("imgItens/valorTotalItem100.00.png")).similar(0.98f);


	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static InserirItem_ServicoFolheacao getInstance(){
		if (instancia ==null){
			instancia = new InserirItem_ServicoFolheacao();
		}
		return instancia;
	}
	
	public void informarCodigo(String qtde) throws Exception {
		quantidade = Integer.parseInt(qtde);

		inserirItem.insereItemCodigo("68.0001", qtde);
		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores();
		inserirItem.adicionaItemListagem();
	}

	public void informarNomeInteiro(String qtde) throws Exception {
		inserirItem.insereItemNomeInteiro("FOLHEACAO A OURO", qtde);

			validaNome();

			inserirItem.adicionaQuantidade(qtde);
			validaValores();
			inserirItem.adicionaItemListagem();
	}

	public void informarNomeParcial(String rotina, String item, String qtde) throws Exception {
		inserirItem.insereItemNomeParcial("FOLH", m_pesqRapidaFolheacao, qtde);
	}

	public void validaNome(){
		if(s.exists(m_ultimoPedido) != null ){
			
			//VALIDA NOME DO PRODUTO
			if (s.exists(m_nomeServicoFolheacao) != null) {
			} else {
				assertFalse("NOK - Nome do item: folheacao", true);
			}
		}
	}

	public void validaValores(){
		// VALIDA VALOR UNITARIO
		if (s.exists(m_valorUni5000) != null) {
		} else {
			assertFalse("NOK - Valor unitario 50,00", true);
		}

		// VALIDA TOTAL COM QUANTIDADE 1
		if (quantidade == 1) {
			if (s.exists(m_valorTotalItem5000) != null) {
			} else {
				assertFalse("NOK - Valor total item 50,00", true);
			}
			// VALIDA TOTAL COM QUANTIDADE 2
		} else if (quantidade == 2) {
			if (s.exists(m_valorTotalItem10000) != null) {
			} else {
				assertFalse("NOK - Valor total do item 100,00", true);
			}
		}
	}
}
