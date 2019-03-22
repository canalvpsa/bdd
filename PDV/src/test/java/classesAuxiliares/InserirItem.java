package classesAuxiliares;

import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class InserirItem {
	private static InserirItem instancia = new InserirItem();
	private Screen s = new Screen();

	private String nomeItemInt, imageString;
	private Pattern m_emAtendimento = new Pattern(getImage("imgOrcamentoPedido/emAtendimento.png")).similar(0.85f);
	private Pattern m_pesqmercadoria = new Pattern(getImage("imgTroca/pesquisaMercadoria.png")).similar(0.90f);

	Pattern m_nomeItem, m_valorUnitario, m_valorTotalItem1, m_valorTotalItem2, itemPesquisa;
	String nomeItem, valorUni, valorTotal1, valorTotal2, descontoUnitario = "0,00", tipoDesconto = "%";
	String resultado;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static InserirItem getInstance(){
		if (instancia ==null){
			instancia = new InserirItem();
		}
		return instancia;
	}

	public void adicionaQuantidade(String qtde) throws FindFailed{
		s.type(qtde);
		s.wait(1.0);
		s.type(Key.ENTER);
	}

	public void adicionaItemListagem() throws FindFailed{
		s.type(Key.ENTER);
	}


	public void adicionaDescontoReais(String desconto) throws FindFailed {
		s.type("r", KeyModifier.CTRL);
		s.type(desconto);
	}

	public void adicionaDescontoPercentual(String desconto) throws FindFailed {
		s.type(desconto);
	}

	public void insereItemCodigo(String item, String qtde) throws Exception {

		s.wait(m_emAtendimento, 200);
		if (s.exists(m_emAtendimento) != null) {
			s.type(item);
			s.type(Key.ENTER);
		}
	}

	public void insereItemNomeInteiro(String item, String qtde) throws Exception {
		s.wait(2.0);

		if (s.exists(m_emAtendimento) != null) {
			System.out.println("OK - PDV em atendimento");
			s.wait(5.0);
			s.type(item);
			s.type(Key.ENTER);

		} else {
			fail("PDV nao esta em atendimento");
		}
	}

	public void insereItemNomeParcial( String item, Pattern itemListagem, String qtde) throws Exception {
		s.wait(2.0);

		if (s.exists(m_emAtendimento) != null) {
			System.out.println("OK - Iniciando insercao de itens");
			s.type(item);
			s.type(Key.ENTER);
			s.wait(10.0);

			if (s.exists(m_pesqmercadoria) != null) {
				s.doubleClick(itemListagem);
			} else {
				fail("NOK - Titulo de pesquisa nao exibido.");
			}
		} else {
			fail("PDV nao esta em atendimento");
		}
	}

	public void insereItemPesquisa(String rotina, String item, String qtde, Boolean troca) throws Exception {
		s.wait(2.0);

		if (s.exists(m_emAtendimento) != null) {
			s.type(Key.ENTER);
			s.wait(10.0);

			if (s.exists(m_pesqmercadoria) != null) {
				s.type(Key.F1);
				s.type(nomeItemInt);
				s.type(Key.ENTER);

				System.out.println("OK - Pesquisa de produto");
				if (s.exists(itemPesquisa) != null) {
					s.doubleClick(itemPesquisa);
				}
				s.wait(8.0);
			} else {
				s.wait(8.0);
				fail("NOK - Titulo de pesquisa nao exibido.");
			}
		} else {
			fail("PDV nao esta em atendimento");
		}
	}
}