package classesAuxiliares;

import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BotaoOK {

	private static BotaoOK instancia = new BotaoOK();
	private Screen s = new Screen();
	String imageString;
	private Pattern m_ok2 = new Pattern(getImage("imgGeral/ok2.png")).similar(0.95f);
	private Pattern m_okSelecionado = new Pattern(getImage("imgGeral/okSelecionado.png")).similar(0.95f);
	private Pattern m_okSelecionadoBorda = new Pattern(getImage("imgGeral/okSelecionadoBorda.png")).similar(0.95f);
	private Pattern m_okAzul = new Pattern(getImage("imgGeral/okAzul.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static BotaoOK getInstance(){
		if (instancia ==null){
			instancia = new BotaoOK();
		}
		return instancia;
	}

	public String procurarOK() {

		if (s.exists(m_okAzul) != null) {
			return "m_okAzul";
		}
		if (s.exists(m_ok2) != null) {
			return "m_ok2";
		}

		if (s.exists(m_okSelecionado) != null) {
			return "m_okSelecionado";
		}

		if (s.exists(m_okSelecionadoBorda) != null) {
			return "m_okSelecionadoBorda";
		}
		return null;
	}

	public void clicarOK(String botao) throws FindFailed {

		switch (botao) {

		case "m_okAzul":
			s.click(m_okAzul);
			break;

		case "m_ok2":
			s.click(m_ok2);
			break;

		case "m_okSelecionado":
			s.click(m_okSelecionado);
			break;

		case "m_okSelecionadoBorda":
			s.click(m_okSelecionadoBorda);
			break;

		default:
			fail("NOK - Nao foi possivel clicar em OK");
		}
	}
}
