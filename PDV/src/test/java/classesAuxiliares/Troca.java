package classesAuxiliares;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Troca {
	private static Troca instancia = new Troca();
	private Screen s = new Screen();
	private String imageString;
	private InserirItem inserirProduto = InserirItem.getInstance();
	private Pattern m_trocaCodigo = new Pattern(getImage("imgOrcamentoPedido/trocaCodigo.png")).similar(0.99f);
	private Pattern m_trocaAssociacao = new Pattern(getImage("imgOrcamentoPedido/trocaAssociacao.png")).similar(0.98f);
	private Pattern m_colunaItens = new Pattern(getImage("imgOrcamentoPedido/colunaItens.png")).similar(0.95f);
	private Pattern m_documento = new Pattern(getImage("imgOrcamentoPedido/documento.png")).similar(0.90f);
	private Pattern m_valor4180 = new Pattern(getImage("imgOrcamentoPedido/valor41.80.png")).similar(0.98f);
	private Pattern m_valor8360 = new Pattern(getImage("imgOrcamentoPedido/valor83.60.png")).similar(0.98f);
	private Pattern m_filtroListagem = new Pattern(getImage("imgOrcamentoPedido/filtroListagem.png")).similar(0.98f);
	private Pattern m_valorTrocaTitulo = new Pattern(getImage("imgOrcamentoPedido/valorTrocaTitulo.png")).similar(0.98f);
	private Pattern m_confirmar = new Pattern(getImage("imgGeral/confirmar.png")).similar(0.90f);
	private Pattern m_telaBranca = new Pattern(getImage("imgGeral/telaBranca.png")).exact().similar(0.90f);
	private Pattern m_ok2 = new Pattern(getImage("imgGeral/ok2.png")).similar(0.98f);
	private Pattern valorProduto = null;
	private String valorProdutoTexto = null;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static Troca getInstance(){
		if (instancia ==null){
			instancia = new Troca();
		}
		return instancia;
	}

	public void troca(String rotina, String produto, String qtde) throws Exception {

		switch (produto) {

		case "brinco":
			if (qtde == "1") {
				valorProduto = m_valor4180;
				valorProdutoTexto = "41,80";
			}
			if (qtde == "2") {
				valorProduto = m_valor8360;
				valorProdutoTexto = "83,60";
			}
			break;

		default:
			System.out.println("Valor do produto de troca nao encontrado");
			break;
		}

		s.wait(m_colunaItens, 100.0);
		s.type("t", KeyModifier.CTRL);

			if (rotina == "pedido") {
				if (s.exists(m_trocaCodigo) != null) {
					inserirProduto.insereItemCodigo(produto, qtde);

					while (m_trocaAssociacao == null) {
						s.exists(m_telaBranca);
					}

					if (s.exists(m_documento) != null) {

						if (s.exists(m_filtroListagem) != null) {
							s.click(m_filtroListagem);
							s.type(valorProdutoTexto);
							s.type(Key.ENTER);
						}

						if (s.exists(valorProduto) != null) {
							s.doubleClick(valorProduto);
							s.wait(m_confirmar, 10.0);
							s.click(m_confirmar);
							s.wait(8.0);
						} else {
							assertFalse("NOK - Valor produto nao encontrado", true);
						}
					} else {
						fail("Erro na tela de associa��o de item na troca");
					}

					if (s.exists(m_valorTrocaTitulo) != null) {
						s.click(m_ok2);
					}
				}
				s.type("t", KeyModifier.CTRL);
			}
			if (rotina == "orcamento") {
				s.exists(m_trocaCodigo);
			}
	}
}