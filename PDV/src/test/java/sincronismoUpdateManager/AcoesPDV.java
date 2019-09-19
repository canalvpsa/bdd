package sincronismoUpdateManager;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

public class AcoesPDV {

	private static AcoesPDV instancia = AcoesPDV.getInstance();
	private Screen s = new Screen();
	String imageString;

	private String PDVexecucao = null;
	private Pattern m_minimizar = new Pattern(getImage("imgGeral/minimizar.png")).similar(0.95f);
	private Pattern m_logo = new Pattern(getImage("imgGeral/logo.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static AcoesPDV getInstance(){
		if (instancia ==null){
			instancia = new AcoesPDV();
		}
		return instancia;
	}

	public void abrePDV() throws InterruptedException, IOException {

		Settings.ActionLogs = false;
		App.open("C:\\VPSA\\PDV\\CaixaVpsa.bat");
	}


	public String processoPDVandamento() {

		// LISTA DE PROCESSOS
		try {
			Process p = Runtime.getRuntime().exec("tasklist.exe /nh");
			InputStream is = p.getInputStream();
			InputStreamReader readerInput = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(readerInput);
			String processos = "";

			// VERIFICA SE O PDV ESTA ABERTO, SENAO ABRE
			while ((processos = reader.readLine()) != null) {
				if ((processos.trim().contains("CaixaVpsa")) || (processos.trim().contains("CaixaVpsa *32"))) {
					String[] PID = processos.split(" ");
					PDVexecucao = PID[0];
					break;
				}
			}
			is.close();
			readerInput.close();
			reader.close();
			Runtime.getRuntime().gc();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return PDVexecucao;
	}

	public void minimizarPDV() {

		// SE O PDV ESTIVER VIS�VEL, MINIMIZA
		try {
			if (s.exists(m_logo) != null) {
				s.click(m_minimizar);
				System.out.println("Minimizando PDV");
			}
		} catch (FindFailed e1) {
			fail("Erro ao minimizar PDV");
		}
	}

	public void fecharPDV() throws IOException {
		if (processoPDVandamento() != null) {
			Runtime.getRuntime().exec("taskkill /f /pid " + PDVexecucao);
			s.wait(5.0);
		}
	}
}