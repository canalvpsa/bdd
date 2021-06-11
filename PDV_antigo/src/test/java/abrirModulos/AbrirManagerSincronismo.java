package abrirModulos;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

import DAO.impl.EnderecoAtualizarPDV;
import sincronismoUpdateManager.AcoesPDV;

public class AbrirManagerSincronismo {

	private static AbrirManagerSincronismo instancia = new AbrirManagerSincronismo();
	private Screen s = new Screen();
	
	private AcoesPDV abrirPDV = AcoesPDV.getInstance();
	private EnderecoAtualizarPDV BD = new EnderecoAtualizarPDV();
	private String imageString;
	// Up manager
	private Pattern m_iconeUpdateManager = new Pattern(getImage("imgUpManager/iconeUpdateManager.png")).similar(0.80f);
	//	private Pattern m_iconeUpdateManagerAntigo = new Pattern(getImage("imgUpManager/iconeUpdateManagerAntigo.png")).similar(0.80f);
	private Pattern m_iconeFaturamentoWeb = new Pattern(getImage("imgUpManager/iconeFaturamentoWeb.png")).similar(0.80f);
	private Pattern m_instalarAtualizacoes = new Pattern(getImage("imgUpManager/instalarAtualizacoes.png")).similar(0.97f);
	private Pattern m_atualizar = new Pattern(getImage("imgUpManager/atualizar.png")).similar(0.97f);
	//	private Pattern m_sim2 = new Pattern(getImage("imgUpManager/sim2.png")).similar(0.90f);
	//	private Pattern m_atualizacaoFim = new Pattern(getImage("imgUpManager/atualizacaoFim.png")).similar(0.90f);
	//	private Pattern m_fechar = new Pattern(getImage("imgUpManager/fechar.png")).similar(0.80f);
	//	private Pattern m_fechar2 = new Pattern(getImage("imgUpManager/fechar2.png")).similar(0.97f);

	// Sincronismo
	private Pattern m_iconeSincronismo = new Pattern(getImage("imgSincronismo/iconeSincronismo.png")).similar(0.80f);
	private Pattern m_sincronismo = new Pattern(getImage("imgSincronismo/sincronismo.png")).similar(0.80f);
	private Pattern m_sincronizando = new Pattern(getImage("imgSincronismo/sincronizando.png")).similar(0.99f);
	private Pattern m_sincronizar = new Pattern(getImage("imgSincronismo/sincronizar.png")).similar(0.80f);
	private Pattern m_sincronizacaoFalha = new Pattern(getImage("imgSincronismo/sincronizacaoFalha.png")).similar(0.98f);
	private Pattern m_sincronismoAtualizacao = new Pattern(getImage("imgSincronismo/sincronismoAtualizacao.png")).similar(0.95f);
	private Pattern m_sairSincronismo = new Pattern(getImage("imgSincronismo/sairSincronismo.png")).similar(0.90f);
	private Pattern m_fecharSincronismo = new Pattern(getImage("imgSincronismo/fecharSincronismo.png")).similar(0.97f);

	// PDV
	private Pattern m_ok1 = new Pattern(getImage("imgGeral/ok1.png")).similar(0.95f);
	private Pattern m_minimizar = new Pattern(getImage("imgGeral/minimizar.png")).similar(0.95f);
	private Pattern m_logo = new Pattern(getImage("imgGeral/logo.png")).similar(0.95f);

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

	public void sincronizarSemValidar() throws FindFailed{
		// SE O PDV ESTIVER VISÌVEL, MINIMIZA

		if (s.exists(m_logo) != null) {
			s.click(m_minimizar);
		}


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
				}
			}
		}
	}

	public void sincronizar() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed {

		Settings.ActionLogs = false;

		sincronizarSemValidar();

		// IDENTIFICA FALHA NO SINCRONISMO
		if (s.exists(m_sincronizacaoFalha) != null) {
			System.out.println("Falha sincronismo.");
			assertFalse("Falha sincronismo.", true);
		} 


		// ATUALIZA PDV
		if (s.exists(m_sincronismoAtualizacao) != null) {
			System.out.println("Sincronizacao pendente");

			Runtime.getRuntime().exec("taskkill /f /im CaixaVpsa.exe");
			System.out.println("Fechou PDV");
			Runtime.getRuntime().gc();


			// FECHA SINCRONISMO E FATURAMENTO WEB
			System.out.println("Inicio de atualizacao do PDV");
			s.click(m_fecharSincronismo);
			s.find(m_iconeSincronismo).rightClick(m_iconeSincronismo);
			s.click(m_sairSincronismo);

			System.out.println("Fechou o sincronismo");
			s.wait(3.0);

			BD.atualizarEnderecoPDV();

			s.rightClick(m_iconeFaturamentoWeb);
			//	s.click(m_fechar2);
			System.out.println("Fechou o faturamento web");
			s.wait(3.0);

			if (s.exists(m_iconeUpdateManager) != null) {
				s.rightClick(m_iconeUpdateManager);
			} 
			s.click(m_instalarAtualizacoes);
			System.out.println("Baixando atualizacoes");

			s.wait(m_atualizar, 2000);
			s.wait(3.0);
			s.click(m_atualizar);
			System.out.println("Instalando atualizacoes");

			//	s.wait(m_atualizacaoFim, 2000);
			s.wait(10.0);
			//	s.click(m_fechar);

			IniciarSincronismo();
			abrirPDV.abrePDV();
		}

		if (s.exists(m_sincronismo) != null) {
			s.click(m_fecharSincronismo);
			System.out.println("Fechando sincronismo");
		}
	}

	public void AbrirUpdateManager() throws InterruptedException, FindFailed {

		System.out.println("\n\n---- Inicio AbrirUpdateManager ----");
		App.open("C:\\VPSA\\UpdateManager\\UpdateManager.bat");
		s.wait(m_iconeUpdateManager, 20.0);

		if (s.exists(m_iconeUpdateManager) != null) {
			s.click(m_ok1);
			System.out.println("OK - Update Manager aberto");
		}
	}

	public void IniciarSincronismo() throws InterruptedException, ClassNotFoundException, SQLException, IOException, FindFailed {
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
	}
}