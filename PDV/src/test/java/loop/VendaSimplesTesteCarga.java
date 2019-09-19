package loop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

import classesAuxiliares.AbrirFecharRotinas;
import classesAuxiliares.BotaoSim;

public class VendaSimplesTesteCarga {

	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private BotaoSim botaoSim = BotaoSim.getInstance();

	boolean pdvAberto;
	private String imageString;
	private Screen s = new Screen();
	private Pattern m_loginGerente = new Pattern(getImage("imgGeral/loginGerente.png")).similar(0.95f);
	private Pattern m_loginGerenteJava = new Pattern(getImage("imgGeral/loginGerenteJava.png")).similar(0.95f);
	private Pattern m_menuGeral = new Pattern(getImage("imgGeral/menuGeral.png")).similar(0.98f);
	private Pattern m_emAtendimento = new Pattern(getImage("imgOrcamentoPedido/emAtendimento.png")).similar(0.98f);
	private Pattern m_saidaAplicacao = new Pattern(getImage("imgGeral/saidaAplicacao.png")).similar(0.95f);
	private Pattern m_loginSenha = new Pattern(getImage("imgGeral/loginSenha.png")).similar(0.85f);
	private Pattern m_loginUsuario = new Pattern(getImage("imgGeral/loginUsuario.png")).similar(0.85f);
	private Pattern m_loginUsuarioVazio = new Pattern(getImage("imgGeral/loginUsuarioVazio.png")).similar(0.55f);
	private Pattern m_abrirCaixa = new Pattern(getImage("imgGeral/abrirCaixa.png")).similar(0.90f);
	private Pattern m_autorizacaoGerente = new Pattern(getImage("imgGeral/autorizacaoGerente.png")).similar(0.90f);
	private Pattern m_nao = new Pattern(getImage("imgGeral/nao.png")).similar(0.95f);

	// ImgOrcamentoPedido
	private Pattern m_pedidoVenda = new Pattern(getImage("imgOrcamentoPedido/pedidoDeVenda.png")).similar(0.98f);
	private Pattern m_pagamentos = new Pattern(getImage("imgOrcamentoPedido/pagamentos.png")).similar(0.99f);
	private Pattern m_ultimoPedido = new Pattern(getImage("imgOrcamentoPedido/ultimoPedido.png")).similar(0.99f);
	private Pattern m_cancelar = new Pattern(getImage("imgOrcamentoPedido/cancelar.png")).similar(0.99f);
	private Pattern m_finalizandoVenda = new Pattern(getImage("imgOrcamentoPedido/finalizandoVenda.png")).similar(0.90f);
	private Pattern m_pedidoAbrir = new Pattern(getImage("imgMenu/pedidoAbrir.png")).similar(0.99f);
	private Pattern m_pedidoCancelamento = new Pattern(getImage("imgOrcamentoPedido/motivoCancelamento.png")).similar(0.95f);
	static String docFiscalParametro;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	@Rule
	public SimpleRepeatRule repeatRule = new SimpleRepeatRule();


	@Test
	public void vendaDinheiro() throws FindFailed, IOException{
		Settings.ActionLogs = false;
		verificaPDVfechado();
		
		System.out.println("\n\n---- Inicio vendaSimples ----");
		int dataSize = 1024 * 1024;
		Runtime rt = Runtime.getRuntime();  
		System.out.println("Bites:");
		System.out.println("Maximo memoria: " + (rt.maxMemory() + " B "));  
		System.out.println("Total memoria: " + (rt.totalMemory() + " B "));  
		System.out.println("Memoria livre " + (rt.freeMemory() + " B "));   
		System.out.println("Memoria usada " + (rt.totalMemory() - rt.freeMemory()) + " B ");  

		System.out.println("\nMB:");
		System.out.println("Maximo memoria: " + rt.maxMemory() / dataSize + " MB");  
		System.out.println("Total memoria: " + rt.totalMemory() / dataSize + " MB");  
		System.out.println("Memoria livre " + rt.freeMemory() / dataSize + " MB");   
		System.out.println("Memoria usada " + (rt.totalMemory() - rt.freeMemory()) /dataSize + " MB");  
		
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		System.out.println(data +" "+ hora);

		//VERIFICA EM QUE TELA ESTA ANTES DE TENTAR INICIAR UMA VENDA
		if (s.exists(m_emAtendimento) != null) {

			if (s.exists(m_pedidoVenda) != null) {
				sairPedido();
			} else {
				if (s.exists(m_pagamentos) != null) {
					s.type(Key.ESC);
					sairPedido();
				}
			}
		}

		//ABRE A NOVA VENDA
		if (App.focus("PDV 4.0") != null) {
			s.find(m_pedidoAbrir).click(m_pedidoAbrir);

			if (m_pedidoAbrir != null) {
				s.wait(m_emAtendimento, 25.0);
			}
		}


		//INSECAO DE ITENS
		s.wait(m_emAtendimento, 200);
		if (s.exists(m_emAtendimento) != null) {
			s.type("BR00014");
			s.type(Key.ENTER);

			s.type("1");
			s.wait(1.0);
			s.type(Key.ENTER);
			s.type(Key.ENTER);

			s.type("BR00014");
			s.type(Key.ENTER);

			s.type("1");
			s.wait(1.0);
			s.type(Key.ENTER);
			s.type(Key.ENTER);


			//REALIZA PAGAMENTO
			s.type(Key.F4);
			s.wait(3.0);

			s.type(Key.F8);
			s.type(Key.ENTER);

			//FINALIZA A VENDA
			clicaFinalizarVenda();
			validaFinalizacaoVenda();
			 
		
		} else {
			fail("PDV nao esta em atendimento");
		}
	}


	public void verificaPDVfechado() throws FindFailed, IOException {
		App.focus("PDV 4.0");
		Settings.ActionLogs = false;
		verificaTerminalFechado();
	}


	public void verificaTerminalFechado() throws FindFailed {
		if (App.focus("PDV 4.0") != null) {

			// LOGAR SE O TERMINAL ESTIVER FECHADO
			if (s.exists(m_loginSenha) != null) {
				System.out.println("Realizar login.");

				if (App.focus("PDV 4.0") != null) {
					verificaSairAplicacao();

					if (s.exists(m_loginUsuario) != null) {
						s.type(Key.TAB);
					} else {
						s.click(m_loginUsuarioVazio);
						s.type("cxsp");
					}

					s.type(Key.ENTER);
					s.type("varejonline");
					s.type(Key.ENTER);

					// VALIDA SE ESTÁ NA TELA PRINCIPAL DO PDV
					s.wait(m_abrirCaixa, 10.0);
					System.out.println("OK - Login realizado.");


					// SE ESTIVER NA TELA DE VENDA, CANCELA O PEDIDO
					if (s.exists(m_emAtendimento) != null) {
						System.out.println("NOK - Nao esta na tela inicial");
						cadastro.sairPedido();
					}
				}
			}
		}
	}

	public void verificaSairAplicacao() throws FindFailed {

		if (s.exists(m_saidaAplicacao) != null) {
			System.out.println("Exibindo Mensagem para sair da aplicacao");

			if (s.exists(m_nao) != null) {
				s.click(m_nao);
			}
		}
	}

	public void clicaFinalizarVenda() throws FindFailed{
		System.out.println("-- finalizarPedido --");
		s.type(Key.F4);
	}

	public void cancelarPedido() throws FindFailed {
		System.out.println("-- cancelarPedido --");
		if(s.exists(m_cancelar) != null){
			botaoSim.clicarSim();
			loginSenhaGerente();

			if (s.exists(m_pedidoCancelamento) != null) {
				s.type(Key.ENTER);
			}else{
				assertFalse("NOK - Nao mensagem cancelamento", true);
			}
			System.out.println("OK -Cancelamento realizado");
		}
	}

	public void sairPedido() throws FindFailed {


		if (App.focus("PDV 4.0") != null) {
			while ((s.exists(m_emAtendimento) != null) || (s.exists(m_pagamentos) != null)) {
				System.out.println("-- sairPedido --");

				if (s.exists(m_cancelar) != null) {
					cancelarPedido();
				}
				if (s.exists(m_pagamentos) != null) {
					s.type(Key.ESC);
				}

				if (s.exists(m_menuGeral) == null) {
					s.type(Key.ESC);
					System.out.println("OK - Fechando rotina de pedido de venda");
				}
			}
		}
	}

	public void loginSenhaGerente() throws FindFailed {

		System.out.println("OK - Inserindo usuario e senha do gerente");
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
		System.out.println("OK -Informado usuario e senha");
	}

	public void validaFinalizacaoVenda() throws FindFailed{
		s.wait(m_finalizandoVenda, 20.0);
		while (s.exists(m_finalizandoVenda) != null) {
		}
		s.wait(m_ultimoPedido, 500.0);
		s.wait(5.0);
		s.type(Key.ESC);

		sairPedido();
	}
}
