package classesAuxiliares;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class CancelarItem {
	private static CancelarItem instancia = new CancelarItem();
	private AbrirFecharRotinas gerente =AbrirFecharRotinas.getInstance();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_listaBrinco = new Pattern(getImage("imgItens/listagemBrinco.png")).similar(0.98f);
	private Pattern m_listaCalcaJeans = new Pattern(getImage("imgItens/listagemCalcaJeans.png")).similar(0.98f);
	private Pattern m_confirmaDesconZerado = new Pattern(getImage("imgOrcamentoPedido/confirmaDescontoZerado.png")).similar(0.98f);
	private Pattern m_produtoCancelar;
	private String nomeProduto;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static CancelarItem getInstance(){
		if (instancia ==null){
			instancia = new CancelarItem();
		}
		return instancia;
	}

	public void cancelaItemComDescontoTotal(String produto, String qtde, String rotina) throws FindFailed {
		
		if(rotina == "pedido"){
		cancelaItemVenda(produto);
	}
		if(rotina == "orcamento"){
			cancelaItemOrcamento(produto);
		}
	
		//CONFIRMA MENSAGEM PARA PERDER DESCONTO
		if (m_confirmaDesconZerado != null) {
			s.type(Key.ENTER);
			System.out.println("OK - Exibiu confirmacao de zeramento do desconto total");
		} else {
			assertFalse("NOK - Nao exibiu confirmacao de zeramento do desconto total", true);
		}		
	}

	public void localizaProdutoCancelamento(String produto){
		switch (produto) {

		case "calca":
			m_produtoCancelar = m_listaCalcaJeans;
			nomeProduto = "Calca Jeans";
			break;

		case "brinco":
			m_produtoCancelar = m_listaBrinco;
			nomeProduto = "Brinco";
			break;

		default:
			fail("Produto nao previsto para cancelamento");
			break;
		}
	}


	public void cancelaItemVenda(String produto) throws FindFailed {
		localizaProdutoCancelamento(produto);

		// CONFIRMA CANCELAMENTO
		if (s.exists(m_produtoCancelar) != null) {
			s.find(m_produtoCancelar);
			s.click(m_produtoCancelar);
			s.type(Key.DELETE);
			s.type(Key.ENTER);

			gerente.loginSenhaGerente();
		}
	}


	public void cancelaItemOrcamento(String produto) throws FindFailed {
		localizaProdutoCancelamento(produto);

		if (s.exists(m_produtoCancelar) != null) {
			s.find(m_produtoCancelar);
			s.click(m_produtoCancelar);
			s.type(Key.DELETE);
			s.type(Key.ENTER);
		}
	}
}