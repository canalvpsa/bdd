package br.com.vpsa.ERPautomatizado;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import classesAuxiliares.BancoDadosERP;
import classesAuxiliares.LoginERP;
import classesAuxiliares.ScreenshotRule;

public class RecebimentoXML {
	public LoginERP chrome = new LoginERP();
	public BancoDadosERP bd = new BancoDadosERP();
	private Screen s = new Screen(); 
	private String imageString, caminhoProjeto, pedidocompra = null, dataRelatorio, url = null;
	private String linkRecebimentoNotas = "/erp/web/migracao/4012";
	private Pattern m_botaoAlterar = new Pattern(getImage("imgRecebimentoXML/botaoAlterarFlex.png")).similar(0.90f);
	private Pattern m_botaoSelecionar = new Pattern(getImage("imgRecebimentoXML/botaoSelecionarFlex.png")).similar(0.90f);
	private Pattern m_entidadeGuaramirim = new Pattern(getImage("imgRecebimentoXML/entidadeGuaramirimFlex.png")).similar(0.90f);
	private Pattern m_selecionarXML = new Pattern(getImage("imgRecebimentoXML/selecionarXMLflex.png")).similar(0.90f);
	private Pattern m_EAN = new Pattern(getImage("imgRecebimentoXML/EAN.png")).similar(0.90f);
	private Pattern m_confirmar = new Pattern(getImage("imgRecebimentoXML/botaoConfirmarFlex.png")).similar(0.90f);
	private Pattern m_importarXML = new Pattern(getImage("imgRecebimentoXML/botaoImportarflex.png")).similar(0.90f);
	private Pattern m_quantidadesRecebimento = new Pattern(getImage("imgRecebimentoXML/quantidadesRecebimento.png")).similar(0.90f);
	private Pattern m_aVista = new Pattern(getImage("imgRecebimentoXML/aVistaFlex.png")).similar(0.90f);
	private Pattern m_selecionarTexto = new Pattern(getImage("imgRecebimentoXML/selecionarTexto.png")).similar(0.90f);
	private Pattern m_mensagemRecebimentoProcessando = new Pattern(getImage("imgRecebimentoXML/mensagemRecebimentoProcessando.png")).similar(0.95f);
	//PESQUISA
	private Pattern m_lupaPesquisar = new Pattern(getImage("imgRecebimentoXML/lupaPesquisarFlex.png")).similar(0.90f);
	private Pattern m_nroPedido = new Pattern(getImage("imgRecebimentoXML/nroPedidoFlex.png")).similar(0.90f);
	private Pattern m_botaoPesquisar = new Pattern(getImage("imgRecebimentoXML/botaoPesquisarFlex.png")).similar(0.90f);
	private Pattern m_calendarioAno = new Pattern(getImage("imgRecebimentoXML/calendarioAno.png")).similar(0.98f);
	private Pattern m_checkboxMarcado = new Pattern(getImage("imgRecebimentoXML/checkboxMarcado.png")).similar(0.90f);
	//EXCLUSÃO
	private Pattern m_notaFiscal = new Pattern(getImage("imgRecebimentoXML/notaFiscal.png")).similar(0.99f);
	private Pattern m_botaoExcluir = new Pattern(getImage("imgRecebimentoXML/botaoExcluirFlex.png")).similar(0.80f);
	private Pattern m_botaoSim = new Pattern(getImage("imgRecebimentoXML/botaoSimFlex.png")).similar(0.90f);
	private Pattern m_mensagemRecebimentoExcluido = new Pattern(getImage("imgRecebimentoXML/mensagemRecebimentoExcluido.png")).similar(0.95f);
	private Pattern m_mensagemPedCompraExcluido = new Pattern(getImage("imgRecebimentoXML/mensagemPedCompraExcluido.png")).similar(0.95f);

	private String getImage(String path) {
		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	public void dataRelatorio(){
		SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy"); 
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DAY_OF_MONTH, -3); 
		dataRelatorio = sd.format(c.getTime()); 
	}

	@Before
	public void verificaLogin(){
		chrome.abriERP();
		url = chrome.getUrl();
		
		pedidocompra = bd.pesquisarRecebimento();
		while(pedidocompra != null){
			excluirRecebimentoNotas();
			pedidocompra = bd.pesquisarRecebimento();
		}
		
		File f = new File(".");
		try {
			caminhoProjeto = f.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void efetuarRecebimento(){
		try {
			if(chrome.driver.getCurrentUrl() != url+linkRecebimentoNotas){
				chrome.driver.navigate().to(url+linkRecebimentoNotas);
				System.out.println("-- Abrindo Recebimento de Notas --");
			}

			if (s.exists(m_botaoAlterar) == null){
				s.wait(m_botaoAlterar,5000.0);
				System.out.println("OK - Rotina carregada");
			}
			s.exists(s.click(m_botaoSelecionar));
			s.wait(2.0);
			s.exists(s.click(m_entidadeGuaramirim));
			s.wait(1.0);
			s.exists(s.click(m_selecionarXML));
			s.exists(s.click(m_importarXML));
			s.paste(caminhoProjeto+"\\src\\test\\resources\\3658.xml");
			s.type(Key.ENTER);
			s.wait(1.0);
			s.exists(s.click(m_EAN));
			s.wait(1.0);
			s.exists(s.click(m_confirmar.similar(0.90f)));

			if (s.exists(m_quantidadesRecebimento) == null){
				s.wait(m_quantidadesRecebimento,500.0);
			}
			s.exists(s.click(m_selecionarTexto));
			s.type("A VISTA");
			s.exists(s.click(m_aVista));
			s.wait(2.0);
			s.exists(s.click(m_confirmar.similar(0.80f)));
			s.wait(m_mensagemRecebimentoProcessando,30);

			excluirRecebimentoNotas();
		} catch (FindFailed e) {
			e.printStackTrace();
			fail("Erro no recebimento de notas");
		}
	}

	public void pesquisarRecebimentoNotas(){
		try {
			if(chrome.driver.getCurrentUrl() != url+linkRecebimentoNotas){
				chrome.driver.navigate().to(url+linkRecebimentoNotas);
				System.out.println("-- Abrindo Recebimento de Notas --");

				if (s.exists(m_botaoAlterar) == null)
					s.wait(m_botaoAlterar,5000.0);
				System.out.println("OK - Rotina carregada");
			}
			s.wait(m_lupaPesquisar, 15.0);
			s.exists(s.click(m_lupaPesquisar));
			s.wait(3.0);
			s.exists(s.click(m_calendarioAno));
			s.type("A", KeyModifier.CTRL);

			int i = 0;
			while (i < 10){
				s.type(Key.BACKSPACE);
				i++;
			}
			s.type(dataRelatorio);
			s.exists(s.click(m_checkboxMarcado));
			s.exists(s.click(m_nroPedido));
			s.exists(s.type(pedidocompra));
			s.exists(s.click(m_botaoPesquisar));

		} catch (FindFailed e) {
			e.printStackTrace();
			fail("Erro na pesquisa de recebimento de notas");
		}
	}

	public void excluirRecebimentoNotas(){
		dataRelatorio();
		if(pedidocompra == null){
			pedidocompra = bd.pesquisarRecebimento();
		}
		pesquisarRecebimentoNotas();

		try {
			s.wait(m_notaFiscal,15.0);
			s.exists(s.click(m_notaFiscal));
			s.click(s.getRegionFromPSRM(m_botaoExcluir));
			s.wait(m_botaoSim,15.0);
			s.exists(s.click(m_botaoSim));
			s.wait(m_mensagemRecebimentoExcluido,1000);
			s.wait(m_botaoSim,15.0);
			s.exists(s.click(m_botaoSim));
			s.wait(m_mensagemPedCompraExcluido,1000);
			pedidocompra = null;
		} catch (FindFailed e) {
			e.printStackTrace();
			fail("Erro na exclusao de recebimento de notas");
		}
	}
}