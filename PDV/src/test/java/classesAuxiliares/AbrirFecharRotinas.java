package classesAuxiliares;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class AbrirFecharRotinas {
	private static AbrirFecharRotinas instancia = new AbrirFecharRotinas();

	private Screen s = new Screen();
	private BotaoSim botaoSim = BotaoSim.getInstance();
	private BotaoOK botaoOK = BotaoOK.getInstance();
	private String imageString;

	// imgGeral
	private Pattern m_menuGeral = new Pattern(getImage("imgGeral/menuGeral.png")).similar(0.98f);
	private Pattern m_caixaFechado = new Pattern(getImage("imgGeral/caixaFechado.png")).similar(0.95f);
	private Pattern m_abrirCaixa = new Pattern(getImage("imgGeral/abrirCaixa.png")).similar(0.95f);
	private Pattern m_aberturaCaixa = new Pattern(getImage("imgGeral/aberturaCaixa.png")).similar(0.90f);
	private Pattern m_autorizacaoGerente = new Pattern(getImage("imgGeral/autorizacaoGerente.png")).similar(0.95f);

	private Pattern m_loginGerente = new Pattern(getImage("imgGeral/loginGerente.png")).similar(0.95f);
	private Pattern m_loginGerenteJava = new Pattern(getImage("imgGeral/loginGerenteJava.png")).similar(0.95f);

	// ImgOrcamentoPedido
	private Pattern m_orcamentoAbrir = new Pattern(getImage("imgMenu/orcamentoAbrir.png")).similar(0.98f);
	private Pattern m_orcamento = new Pattern(getImage("imgOrcamentoPedido/orcamento.png")).similar(0.98f);
	private Pattern m_pedidoVenda = new Pattern(getImage("imgOrcamentoPedido/pedidoDeVenda.png")).similar(0.98f);
	private Pattern m_pagamentos = new Pattern(getImage("imgOrcamentoPedido/pagamentos.png")).similar(0.99f);
	private Pattern m_ultimoPedido = new Pattern(getImage("imgOrcamentoPedido/ultimoPedido.png")).similar(0.99f);
	private Pattern m_cancelar = new Pattern(getImage("imgOrcamentoPedido/cancelar.png")).similar(0.99f);
	private Pattern m_finalizandoVenda = new Pattern(getImage("imgOrcamentoPedido/finalizandoVenda.png")).similar(0.90f);
	private Pattern m_pedidoAbrir = new Pattern(getImage("imgMenu/pedidoAbrir.png")).similar(0.99f);
	private Pattern m_emAtendimento = new Pattern(getImage("imgOrcamentoPedido/emAtendimento.png")).similar(0.95f);
	private Pattern m_erroOperacional = new Pattern(getImage("imgOrcamentoPedido/pedidoErroOperacional.png")).similar(0.95f);
	private Pattern m_funcoesTela = new Pattern(getImage("imgFuncoes/funcoes.png")).similar(0.95f);

	// ImgFuncoes
	private Pattern m_funcoes = new Pattern(getImage("imgMenu/funcoes.png")).similar(0.98f);

	// ImgVendas
	private Pattern m_movimentacoes = new Pattern(getImage("imgMenu/movimentacoes.png")).similar(0.98f);

	// ImgBaixaParcela
	private Pattern m_baixaParcela = new Pattern(getImage("imgMenu/baixaParcela.png")).similar(0.98f);
	private Pattern m_rotinaBaixaParcela = new Pattern(getImage("imgBaixaParcela/rotinaBaixaParcela.png")).similar(0.98f);
	private Pattern m_pesquisaParcelas = new Pattern(getImage("imgBaixaParcela/pesquisaParcelas.png")).similar(0.98f);
	private Pattern m_confirmaValorInferior = new Pattern(getImage("imgBaixaParcela/confirmacaoValorInferior.png")).exact();
	private Pattern m_confirmacaoPagamento = new Pattern(getImage("imgBaixaParcela/confirmacaoPagamento.png")).exact();

	// Fechar tela crediario
	private Pattern m_fecharJasperViewer = new Pattern(getImage("imgGeral/fecharJasperViewer.png")).similar(0.95f);


	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static AbrirFecharRotinas getInstance(){
		if (instancia ==null){
			instancia = new AbrirFecharRotinas();
		}
		return instancia;
	}

	public void abrirRotina(String rotina) throws FindFailed {
		if (App.focus("PDV 4.0") != null) {
			switch (rotina) {

			case "funcoes":
				verificaPDVemAtendimento();
				s.find(m_funcoes).click(m_funcoes);
				break;

			case "orcamento":
				verificaPDVemAtendimento();
				s.find(m_orcamentoAbrir).click(m_orcamentoAbrir);
				break;

			case "venda":
				verificaPDVemAtendimento();

				if (s.exists(m_caixaFechado) != null) {
					System.out.println("O caixa esta fechado.");
					s.click(m_abrirCaixa);
					s.wait(m_aberturaCaixa, 1000);
					s.type("52450");
					s.type(Key.ENTER);
					s.type("22490");
					s.type(Key.ENTER);
					s.type(Key.ENTER);
					s.find(m_menuGeral);
				}

				s.find(m_pedidoAbrir).click(m_pedidoAbrir);

				if (m_pedidoAbrir != null) {
					s.wait(m_emAtendimento, 25.0);
				}
				break;

			case "BaixaParcela":
				verificaPDVemAtendimento();
				s.find(m_baixaParcela).click(m_baixaParcela);
				break;

			case "movimentacoes":
				verificaPDVemAtendimento();
				s.find(m_movimentacoes).click(m_movimentacoes);
				break;
			}
		}
	}

	public void sairOrcamento() throws FindFailed {

		if (App.focus("PDV 4.0") != null) {

			while (s.exists(m_emAtendimento) != null) {
				s.type(Key.ESC);

				if(s.exists(m_cancelar) != null){
					cancelarOrcamento();
				}
			}
		}else {
			fail("PDV esta fechado");
		}
	}

	public void clicaFinalizarVenda() throws FindFailed{
		s.type(Key.F4);
	}

	public void finalizarPedidoComTroco() throws FindFailed {

		clicaFinalizarVenda();
		s.type(Key.ENTER);
		s.type(Key.ENTER);
		s.type(Key.ENTER);
		s.type(Key.ENTER);
	}

	public void finalizarPedido(Boolean jasperViewer) throws FindFailed {
		s.wait(m_pagamentos, 10);
		clicaFinalizarVenda();

		if(jasperViewer == true){
			s.wait(m_fecharJasperViewer, 15.0);
			if(App.focus("JasperViewer") != null){
				s.click(m_fecharJasperViewer);
			}
			validaFinalizacaoVenda();
		}else{
			validaFinalizacaoVenda();
		}
	}


	public void validaFinalizacaoVenda() throws FindFailed{
		s.wait(m_finalizandoVenda, 10.0);
		while (s.exists(m_finalizandoVenda) != null) {
		}

		s.wait(3.0);
		s.wait(m_ultimoPedido, 500.0);
		s.wait(5.0);
		s.type(Key.ESC);

		sairPedido();
	}

	public void validaFinalizacaoVendaCrediario() throws FindFailed{
		if (App.focus("JasperViewer") != null) {

			if (s.exists(m_fecharJasperViewer) != null) {
				s.click(m_fecharJasperViewer);
			}

			s.wait(3.0);
			s.wait(m_ultimoPedido, 500.0);
			s.wait(5.0);
			s.type(Key.ESC);
			System.out.println("OK - Pedido de venda finalizado");

			sairPedido();
		}
	}

	public void sairPedido() throws FindFailed {

		if (App.focus("PDV 4.0") != null) {
			while ((s.exists(m_emAtendimento) != null) || (s.exists(m_pagamentos) != null)) {

				if (s.exists(m_cancelar) != null) {
					cancelarPedido();
				}
				if (s.exists(m_pagamentos) != null) {
					s.type(Key.ESC);
				}

				if (s.exists(m_menuGeral) == null) {
					s.type(Key.ESC);
				}
			}
		}
	}

	public void finalizarBaixaParcelaTotal() throws FindFailed {

		s.type(Key.F4);
		if (s.exists(m_confirmacaoPagamento) != null) {
			s.type(Key.ENTER);
			System.out.println("OK - Mensagem finalizar baixa");
		}
		s.wait(3.0);
	}

	public void finalizarBaixaParcelaParcial() throws FindFailed {

		s.type(Key.F4);
		if (s.exists(m_confirmaValorInferior) != null) {
			s.type(Key.ENTER);
			System.out.println("OK - Mensagem valor inferior");
		} else {
			assertFalse("NOK nao exibiu mensagem valor inferior", true);
		}
		s.wait(3.0);
	}

	public void fecharBaixaParcela() throws FindFailed {

		if (s.exists(m_rotinaBaixaParcela) != null) {
			s.click(m_rotinaBaixaParcela);
			s.type(Key.ESC);
			s.wait(4.0);
		} else {
			if (s.exists(m_pesquisaParcelas) != null) {
				s.type(Key.ESC);
			}
		}
	}

	public void sairFuncoes() throws FindFailed {

		if (App.focus("PDV 4.0") != null) {
			if (s.exists(m_funcoesTela) != null) {
				s.type(Key.ENTER);
			}
			if (botaoOK.procurarOK() != null) {
				botaoOK.clicarOK(botaoOK.procurarOK());
			}
			if (botaoOK.procurarOK() != null) {
				botaoOK.clicarOK(botaoOK.procurarOK());
			}

			if (botaoOK.procurarOK() != null) {
				botaoOK.clicarOK(botaoOK.procurarOK());
			}
		}
	}

	public void loginSenhaGerente() throws FindFailed {

		s.wait(m_autorizacaoGerente, 30.0);

		if (s.exists(m_loginGerenteJava) != null) {
			s.click(m_loginGerenteJava);
			preencheCancelamento();
		}

		if (s.exists(m_loginGerente) != null) {
			s.click(m_loginGerente);
			preencheCancelamento();
		}
	}

	public void preencheCancelamento() throws FindFailed{
		s.type("usuario");
		s.type(Key.TAB);
		s.type("varejonline");
		s.type(Key.ENTER);
	}

	public void cancelarOrcamento() throws FindFailed{

		if(s.exists(m_cancelar) != null){
			s.type(Key.ENTER);
			s.wait(2.0);
		}
	}

	public void cancelarPedido() throws FindFailed {

		if(s.exists(m_cancelar) != null){
			botaoSim.clicarSim();
			loginSenhaGerente();

			if (s.exists(m_erroOperacional) != null) {
				s.type(Key.ENTER);
			}else{
				assertFalse("NOK - Nao exibiu tipo de erro", true);
			}
		}
	}


	public void verificaPDVemAtendimento() throws FindFailed {

		if (s.exists(m_emAtendimento) != null) {
			if (s.exists(m_orcamento) != null) {
				sairOrcamento();
			} else {
				if (s.exists(m_pedidoVenda) != null) {
					sairPedido();
				} else {
					if (s.exists(m_pagamentos) != null) {
						s.type(Key.ESC);
						s.type(Key.ESC);
						sairPedido();
					}
				}
			}
		}
	}
}