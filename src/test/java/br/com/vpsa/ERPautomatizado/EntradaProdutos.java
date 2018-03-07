package br.com.vpsa.ERPautomatizado;

import static org.junit.Assert.fail;

import java.net.URL;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import classesAuxiliares.BancoDadosERP;
import classesAuxiliares.LoginERP;

public class EntradaProdutos {
	public LoginERP chrome = new LoginERP();
	public BancoDadosERP bd = new BancoDadosERP();
	private Boolean entradaAtiva = false;
	private Screen s = new Screen(); 
	private String imageString, url;
	private String linkEntradaProdutos = "/erp/web/migracao/1014";

	private Pattern m_compraComercializacao = new Pattern(getImage("imgEntradaProdutos/compraComercializacao.png")).similar(0.90f);
	private Pattern m_selecioneOperacao = new Pattern(getImage("imgEntradaProdutos/selecioneOperacao.png")).similar(0.90f);
	private Pattern m_tipoNotaFiscal = new Pattern(getImage("imgEntradaProdutos/tipoNotaFiscal.png")).similar(0.90f);
	private Pattern m_campoCodigo = new Pattern(getImage("imgEntradaProdutos/campoCodigo.png")).similar(0.90f);
	private Pattern m_fornecedorPibi = new Pattern(getImage("imgEntradaProdutos/fornecedorPibi.png")).similar(0.90f);
	private Pattern m_botaoAdicionar = new Pattern(getImage("imgEntradaProdutos/botaoAdicionar.png")).similar(0.90f);
	private Pattern m_produtoAdicao = new Pattern(getImage("imgEntradaProdutos/produtoAdicao.png")).similar(0.90f);
	private Pattern m_produtoListagem = new Pattern(getImage("imgEntradaProdutos/produtoListagem.png")).similar(0.90f);
	private Pattern m_mensagemEntradaProduto = new Pattern(getImage("imgEntradaProdutos/mensagemEntradaProduto.png")).similar(0.90f);
	private Pattern m_mensagemEntradaExcluido = new Pattern(getImage("imgEntradaProdutos/mensagemEntradaExcluido.png")).similar(0.90f);
	
	private Pattern m_entidadeGuaramirim = new Pattern(getImage("imgRecebimentoXML/entidadeGuaramirimFlex.png")).similar(0.90f);
	private Pattern m_confirmar = new Pattern(getImage("imgRecebimentoXML/botaoConfirmarFlex.png")).similar(0.90f);
	private Pattern m_botaoExcluir = new Pattern(getImage("imgRecebimentoXML/botaoExcluirFlex.png")).similar(0.80f);
	private Pattern m_botaoSim = new Pattern(getImage("imgRecebimentoXML/botaoSimFlex.png")).similar(0.90f);
	//PESQUISA
	private Pattern m_lupaPesquisar = new Pattern(getImage("imgRecebimentoXML/lupaPesquisarFlex.png")).similar(0.90f);
	private Pattern m_botaoPesquisar = new Pattern(getImage("imgRecebimentoXML/botaoPesquisarFlex.png")).similar(0.90f);
	private Pattern m_notaFiscal12557 = new Pattern(getImage("imgEntradaProdutos/notaFiscal12557.png")).similar(0.90f);
	private Pattern m_pesquisarListagem = new Pattern(getImage("imgEntradaProdutos/pesquisarListagem.png")).similar(0.90f);
	
	private String getImage(String path) {
		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	@Rule
	//	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);
	//Settings.ActionLogs = false;

	@Before
	public void verificaLogin(){
		entradaAtiva = bd.pesquisarEntradaProdutos();
		url = chrome.getUrl();
		if(entradaAtiva != null){
			excluirEntradaProdutos();
		}
	}

	@Test
	
	public void cadastrarEntradaProdutos(){
		try {
			if(chrome.driver.getCurrentUrl() != url+linkEntradaProdutos){
				chrome.driver.navigate().to(url+linkEntradaProdutos);
				System.out.println("-- Abrindo Entrada de Produtos --");
			}

			if (s.exists(m_tipoNotaFiscal) == null)
				s.wait(m_tipoNotaFiscal,5000.0);
			System.out.println("OK - Rotina carregada");

			s.exists(s.click(m_selecioneOperacao));
			s.type("COMPRA P");
			s.exists(s.click(m_compraComercializacao));
			s.type(Key.TAB);
			s.exists(s.click(m_entidadeGuaramirim));
			s.type(Key.TAB);
			s.type("COMERCIAL PIBI");
			s.wait(m_fornecedorPibi,10.0);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type("12557");
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type("2300");
			s.type(Key.TAB);
			s.type("2300");
			s.exists(s.click(m_campoCodigo));
			s.type("g2.001");
			s.type(Key.TAB);
			s.wait(m_produtoAdicao);
			s.type("1");
			s.type(Key.TAB);
			s.type("2300");
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.click(m_botaoAdicionar);
			s.wait(m_produtoListagem);
			s.click(m_confirmar);
			s.wait(m_mensagemEntradaProduto,15.0);
			excluirEntradaProdutos();
		} catch (FindFailed e) {
			e.printStackTrace();
			fail("Erro na entrada de produtos");
		}
	}

	public void excluirEntradaProdutos(){
		try {
			if(chrome.driver.getCurrentUrl() != url+linkEntradaProdutos){
				chrome.driver.navigate().to(url+linkEntradaProdutos);
				System.out.println("-- Abrindo entrada de produtos --");
			}
			s.wait(m_lupaPesquisar, 15.0);
			s.exists(s.click(m_lupaPesquisar));
			s.exists(s.click(m_botaoPesquisar));
			s.exists(s.click(m_pesquisarListagem));
			s.type("12557");
			s.type(Key.ENTER);
			s.exists(s.click(m_notaFiscal12557));
			s.click(s.getRegionFromPSRM(m_botaoExcluir));
			s.wait(m_botaoSim,15.0);
			s.exists(s.click(m_botaoSim));
			s.wait(m_mensagemEntradaExcluido,30.0);
		} catch (FindFailed e) {
			e.printStackTrace();
			fail("Erro na pesquisa de entrada de produtos");
		}
	}
}