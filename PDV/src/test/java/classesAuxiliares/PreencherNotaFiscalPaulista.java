package classesAuxiliares;

import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class PreencherNotaFiscalPaulista {
	private static PreencherNotaFiscalPaulista instancia = new PreencherNotaFiscalPaulista();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_adicionar = new Pattern(getImage("imgGeral/adicionar.png")).similar(0.90f);
	private Pattern m_notaPaulista = new Pattern(getImage("imgGeral/notaFiscalPaulista.png")).similar(0.90f);
	private Pattern m_cpfPreenchido = new Pattern(getImage("imgGeral/cpfPreenchido.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static PreencherNotaFiscalPaulista getInstance(){
		if (instancia ==null){
			instancia = new PreencherNotaFiscalPaulista();
		}
		return instancia;
	}

	public void preencher() throws FindFailed{

		if (s.exists(m_notaPaulista) != null) {
			System.out.println("OK - Nota fiscal paulista");

			if (s.exists(m_cpfPreenchido) == null) {
				s.type("01234567890");
				if (s.exists(m_adicionar) != null) {
					s.click(m_adicionar);
					System.out.println("OK - Dados da nota fiscal paulista");
					s.wait(3.0);
					App.focus("PDV 4.0");
				}
			}
		}
	}
}