package classesAuxiliares;

import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

public class Impressora {
	private static Impressora instancia = new Impressora();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_poucoPapel = new Pattern(getImage("imgImpressora/poucoPapel.png")).similar(0.95f);
	private Pattern m_ok = new Pattern(getImage("imgGeral/ok2.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static Impressora getInstance(){
		if (instancia ==null){
			instancia = new Impressora();
		}
		return instancia;
	}

	public void verificaProblemaImpressora() {

		Settings.ActionLogs = false;
		try {
			// POUCO PAPEL
			if (s.exists(m_poucoPapel) != null) {
				System.out.println("Impressora com pouco papel.");
				s.click(m_ok);
				System.out.println("Confirmado pouco papel");
				s.wait(3.0);
			}

		} catch (FindFailed e) {
			fail("Erro ao verificar impressora.");
		}
	}
}
