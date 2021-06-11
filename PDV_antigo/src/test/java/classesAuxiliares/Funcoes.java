package classesAuxiliares;

import java.net.URL;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Funcoes {
	private static Funcoes instancia = new Funcoes();
	private Screen s = new Screen();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private String imageString;

	// imgGeral
	private Pattern m_cancelarUltimoCF = new Pattern(getImage("imgFuncoes/cancelarUltimoCF.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static Funcoes getInstance(){
		if (instancia ==null){
			instancia = new Funcoes();
		}
		return instancia;
	}

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	public void cancelarUltimoCF() throws FindFailed {
		s.click(m_cancelarUltimoCF);
		cadastro.loginSenhaGerente();
	}
}