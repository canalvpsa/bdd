package sincronismoUpdateManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;

import org.junit.Rule;
import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

import classesAuxiliares.ScreenshotRule;

public class AbrirManagerSincronismo {
	private static AbrirManagerSincronismo instancia = new AbrirManagerSincronismo();
	private Screen s = new Screen();
	private AcoesPDV abrirPDV = AcoesPDV.getInstance();
	private String imageString, url;
	
	// Up manager
	private Pattern m_iconeUpdateManager = new Pattern(getImage("imgUpManager/iconeUpdateManager.png")).similar(0.80f);
	private Pattern m_iconeFaturamentoWeb = new Pattern(getImage("imgUpManager/iconeFaturamentoWeb.png")).similar(0.80f);
	private Pattern m_instalarAtualizacoes = new Pattern(getImage("imgUpManager/instalarAtualizacoes.png")).similar(0.97f);
	private Pattern m_atualizar = new Pattern(getImage("imgUpManager/atualizar.png")).similar(0.97f);
	private Pattern m_fecharBotao = new Pattern(getImage("imgUpManager/fecharBotao.png")).similar(0.80f);
	private Pattern m_fecharMenu = new Pattern(getImage("imgUpManager/fecharMenu.png")).similar(0.97f);
	private Pattern m_simBotao = new Pattern(getImage("imgUpManager/simBotao.png")).similar(0.97f);
	private Pattern m_fecharBotaoAtualizacao = new Pattern(getImage("imgUpManager/fecharBotaoAtualizacao.png")).similar(0.97f);
	private Pattern m_erroAtualizacao = new Pattern(getImage("imgUpManager/erroAtualizacao.png")).similar(0.97f);

	// Sincronismo
	private Pattern m_iconeSincronismo = new Pattern(getImage("imgSincronismo/iconeSincronismo.png")).similar(0.80f);
	private Pattern m_sincronismo = new Pattern(getImage("imgSincronismo/sincronismo.png")).similar(0.80f);
	private Pattern m_sincronizando = new Pattern(getImage("imgSincronismo/sincronizando.png")).similar(0.99f);
	private Pattern m_sincronizar = new Pattern(getImage("imgSincronismo/sincronizar.png")).similar(0.80f);
	private Pattern m_sincronizacaoFalha = new Pattern(getImage("imgSincronismo/sincronizacaoFalha.png")).similar(0.98f);
	private Pattern m_sincronismoAtualizacao = new Pattern(getImage("imgSincronismo/sincronismoAtualizacao.png")).similar(0.95f);
	private Pattern m_sairSincronismo = new Pattern(getImage("imgSincronismo/sairSincronismo.png")).similar(0.90f);
	private Pattern m_fecharSincronismo = new Pattern(getImage("imgSincronismo/fecharSincronismo.png")).similar(0.97f);
	private Pattern m_fecharMensagemSincronismo = new Pattern(getImage("imgSincronismo/fecharMensagemSincronismo.png")).similar(0.97f);

	// Atualização
	//private Pattern m_atualizacao = new Pattern(getImage("imgUpManager/atualizacao.png")).similar(0.99f);
	private Pattern m_buscarAtualizacao = new Pattern(getImage("imgSincronismo/buscarAtualizacao.png")).similar(0.99f);
	private Pattern m_PDVatualizado = new Pattern(getImage("imgSincronismo/PDVatualizado.png")).similar(0.99f);

	// PDV
	private Pattern m_ok1 = new Pattern(getImage("imgGeral/ok1.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static AbrirManagerSincronismo getInstance(){
		if (instancia ==null){
			instancia = new AbrirManagerSincronismo();
		}
		return instancia;
	}

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();

	public void preCondicoes(){
	System.out.println("Executando pré-condições");

	url = System.getProperty("url");
	if (url == null)
		url = "https://qa.varejonline.com.br:7443/releases/";
	}
	
	public void sincronizar() throws InterruptedException, IOException {

		Settings.ActionLogs = false;
		System.out.println("\n\n---- Inicio Sincronismo ----");

		abrirPDV.minimizarPDV();

		try {
			if (s.exists(m_iconeSincronismo) != null) {
				s.find(m_iconeSincronismo).doubleClick(m_iconeSincronismo);
				s.wait(5.0);

				if (s.exists(m_sincronismo) != null) {
					s.click(m_sincronizar);
				} else {
					s.doubleClick(m_iconeSincronismo);
				}

				if (s.exists(m_sincronismo) != null) {
					s.click(m_sincronizar);

					while (s.exists(m_sincronizando) != null) {
						System.out.println("Validando status sincronismo");

						// IDENTIFICA FALHA NO SINCRONISMO
						if (s.exists(m_sincronizacaoFalha) != null) {
							System.out.println("Falha sincronismo.");
							assertFalse("Falha sincronismo.", true);
						} else {
							System.out.println("OK - Sincronismo");
						}
					}

					// ATUALIZA PDV
					if (s.exists(m_sincronismoAtualizacao) != null) {
						System.out.println("Sincronizacao pendente");
						atualizarPDV(url);
					}
				}
			}
		} catch (FindFailed e) {
			fail("Erro no Sincronismo");
		}
	}

	@Test
	public void atualizarPDV(String url) throws IOException {

		System.out.println("Início de atualizacao do PDV");
		try {
			Runtime.getRuntime().exec("taskkill /f /im CaixaVpsa.exe");
			System.out.println("Fechou PDV");
		} catch (IOException e) {
			fail("Erro ao fechar PDV");
			e.printStackTrace();
		}

		try {
			opcoesSincronismo();
			if (buscarAtualizacao() == true) {
			//	bancoDados.atualizaPDV(url);
				fecharSincronismoFW();

				if (s.exists(m_iconeUpdateManager) != null) {
					s.rightClick(m_iconeUpdateManager);
					s.click(m_instalarAtualizacoes);
					System.out.println("Baixando atualizações");

					s.wait(m_atualizar, 2000);
					s.wait(3.0);
					s.click(m_atualizar);
					System.out.println("Instalando atualizações");

					if (s.exists(m_simBotao) != null) {
						s.click(m_simBotao);
					}

					//	atualizar.executar();

					s.wait(m_fecharBotaoAtualizacao, 2000);
					s.wait(10.0);

					if (s.exists(m_erroAtualizacao) != null) {
						assertFalse("ERRO na atualizacao", true);
					}
					s.click(m_fecharBotao);

					iniciarSincronismo();
					abrirPDV.abrePDV();
				}
			}
		} catch (FindFailed | InterruptedException e) {
			fail("Erro ao atualizar PDV");
			e.printStackTrace();
		}
	}

	public void opcoesSincronismo() {

		try {
			if (s.exists(m_fecharSincronismo) != null) {
				s.click(m_fecharSincronismo);
			}
			s.find(m_iconeSincronismo).rightClick(m_iconeSincronismo);
		} catch (FindFailed e) {
			fail("Problema ao abrir opcoes do sincronismo");
			e.printStackTrace();
		}
	}

	public boolean buscarAtualizacao() {

		try {
			s.find(m_buscarAtualizacao);
			s.click(m_buscarAtualizacao);

			if (s.exists(m_PDVatualizado) != null) {
				System.out.println("O PDV ja esta atualizado com a ultima versao");
				return false;
			}

			if (s.exists(m_fecharMensagemSincronismo) != null) {
				s.click(m_fecharMensagemSincronismo);
			}
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		return true;
	}

	public void fecharSincronismoFW() {

		try {
			opcoesSincronismo();
			if (s.exists(m_sairSincronismo) != null) {
				s.click(m_sairSincronismo);
				System.out.println("Fechou o sincronismo");
				s.wait(3.0);
			}
			if (s.exists(m_fecharMensagemSincronismo) != null) {
				s.click(m_fecharMensagemSincronismo);
			}
			if (s.exists(m_iconeFaturamentoWeb) != null) {
				s.rightClick(m_iconeFaturamentoWeb);
				s.click(m_fecharMenu);
				System.out.println("Fechou o faturamento web");
				s.wait(3.0);
			}
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public void abrirUpdateManager() throws InterruptedException {

		try {
			System.out.println("\n\n---- Inicio AbrirUpdateManager ----");
			App.open("C:\\VPSA\\UpdateManager\\UpdateManager.bat");
			s.wait(m_iconeUpdateManager, 20.0);

			if (s.exists(m_iconeUpdateManager) != null) {
				s.click(m_ok1);
				System.out.println("OK - Update Manager aberto");
			}
		} catch (FindFailed e) {
			fail("Erro ao abrir Update Manager");
		}
	}

	public void iniciarSincronismo() throws InterruptedException {

		System.out.println("---- IniciarSincronismo ----");
		try {
			String comando = "cmd /c C:/VPSA/Sincronismo/Sincronismo.bat";
			Runtime.getRuntime().exec(comando);
			System.out.println("OK - Abrindo sincronismo");

			s.wait(30.0);

			if (s.exists(m_ok1) != null) {
				s.click(m_ok1);
				System.out.println("OK - Sincronismo ja estava em execucao");
			} else {
				s.wait(m_sincronismo, 15.0);
				System.out.println("OK - Sincronismo foi aberto");
			}
			sincronizar();
			s.wait(5.0);
			Runtime.getRuntime().gc();
		} catch (FindFailed e) {
			fail("Erro ao abrir Sincronismo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}