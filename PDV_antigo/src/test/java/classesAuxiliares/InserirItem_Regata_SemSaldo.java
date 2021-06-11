package classesAuxiliares;

import java.net.URL;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class InserirItem_Regata_SemSaldo {
	private static InserirItem_Regata_SemSaldo instancia = new InserirItem_Regata_SemSaldo();
	public InserirItem inserirItem =InserirItem.getInstance();
	private Screen s = new Screen();
	Pattern itemPesquisa;
	private int quantidade;
	private String imageString;

	private Pattern m_ultimoPedido = new Pattern(getImage("imgOrcamentoPedido/ultimoPedido.png")).similar(0.99f);

	// Produto REGATA FITNESS - sem saldo
	private Pattern m_nomeProdutoRegataFitness = new Pattern(getImage("imgItens/nomeProdutoRegataFitness.png")).similar(0.98f);
	private Pattern m_pesqRapidaRegataFitness = new Pattern(getImage("imgItens/pesqRapidaRegataFitness.png")).similar(0.98f);
	private Pattern m_valorUni5990 = new Pattern(getImage("imgItens/valorUni59.90.png")).similar(0.98f);
	private Pattern m_valorTotalItem5990 = new Pattern(getImage("imgItens/valorTotalItem59.90.png")).similar(0.98f);
	private Pattern m_valorTotalItem11980 = new Pattern(getImage("imgItens/valorTotalItem119.80.png")).similar(0.98f);


	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static InserirItem_Regata_SemSaldo getInstance(){
		if (instancia ==null){
			instancia = new InserirItem_Regata_SemSaldo();
		}
		return instancia;
	}

	public void informarCodigo(String qtde, String permiteSaldoNegativo) throws Exception {
		quantidade = Integer.parseInt(qtde);

		inserirItem.insereItemCodigo("BAF001.0003", qtde);
		validaNome();

		if(permiteSaldoNegativo.equals("Y")){
			inserirItem.adicionaQuantidade(qtde);
			validaValores();
			inserirItem.adicionaItemListagem();	
		}

	}


	public void informarNomeInteiro(String rotina, String qtde, String desconto) throws Exception {
		inserirItem.insereItemNomeInteiro("REGATA FITNESS VERDE GG", qtde);

		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores();
		inserirItem.adicionaDescontoReais(desconto);
	}

	public void informarNomeParcial(String rotina, String qtde) throws Exception {
		inserirItem.insereItemNomeParcial("REGATA FITNESS VERDE", m_pesqRapidaRegataFitness, qtde);
		validaNome();

		inserirItem.adicionaQuantidade(qtde);
		validaValores();
		inserirItem.adicionaItemListagem();
	}


	public void validaNome() {
		if (s.exists(m_ultimoPedido) != null) {

			// VALIDA NOME DO PRODUTO
			s.exists(m_nomeProdutoRegataFitness);
		}
	}

	public void validaValores() {
		// VALIDA VALOR UNITARIO
		s.exists(m_valorUni5990);

		// VALIDA TOTAL COM QUANTIDADE 1
		if (quantidade == 1) {
			s.exists(m_valorTotalItem5990);

			// VALIDA TOTAL COM QUANTIDADE 2
		} else if (quantidade == 2) {
			s.exists(m_valorTotalItem11980);
		}
	}
}
