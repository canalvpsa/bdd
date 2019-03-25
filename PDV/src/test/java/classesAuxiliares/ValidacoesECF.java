package classesAuxiliares;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ValidacoesECF {
	private static ValidacoesECF instancia = new ValidacoesECF();
	private Screen s = new Screen();
	String imageString;

	private Pattern m_reducaoZsucesso = new Pattern(getImage("imgImpressora/reducaoZsucesso.png")).similar(0.99f);
	private Pattern m_MD5 = new Pattern(getImage("imgImpressora/MD5.png")).similar(0.90f);

	private Pattern m_autorizacaoGerente = new Pattern(getImage("imgGeral/autorizacaoGerente.png")).similar(0.90f);
	private Pattern m_OK = new Pattern(getImage("imgGeral/ok2.png")).similar(0.95f);

	private Pattern m_reducaoZtitulo = new Pattern(getImage("imgImpressora/reducaoZtitulo.png")).similar(0.95f);
	private Pattern m_reducaoZtituloCortado = new Pattern(getImage("imgImpressora/reducaoZtituloCortado.png")).similar(0.99f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static ValidacoesECF getInstance(){
		if (instancia ==null){
			instancia = new ValidacoesECF();
		}
		return instancia;
	}

	public void validaECFaberturaPDV() throws FindFailed{

		s.wait(m_MD5, 500);
		System.out.println("OK - Gerando MD5");

		while (s.exists(m_MD5) != null) {
		}
	}


	public void verificaReducaoZPendente() throws FindFailed {

		System.out.println("Verifica se existe reducao Z pendente.");

		if (s.exists(m_reducaoZtitulo) != null) {
			System.out.println("Existe reducao Z pendente.");
			s.type(Key.ENTER);
			s.wait(2.0);
			s.type(Key.ENTER);

			s.wait(m_autorizacaoGerente, 200);
			s.type("usuario");
			s.type(Key.TAB);
			s.type("varejonline");
			s.type(Key.ENTER);
			System.out.println("Confirmada reducao Z");
			s.wait(8.0);

			reducaoZandamento();
			s.wait(3.0);

			reducaoZandamento();
			s.wait(3.0);

			reducaoZandamento();
			s.wait(3.0);

			reducaoZandamento();
			s.wait(3.0);
		}
	}

	public void reducaoZandamento() throws FindFailed {

		while (s.exists(m_reducaoZtituloCortado) != null) {

			if (s.exists(m_reducaoZsucesso) != null) {
				s.type(Key.ENTER);
				System.out.println("OK - Reducao Z realizada com sucesso.");
			}
			
			if (s.exists(m_OK) != null) {
				s.type(Key.ENTER);
				System.out.println("NOK - Mensagem indevida");
				s.wait(2.0);

				System.out.println("Nao tem mais Reducao Z no titulo");
			}
			if (s.exists(m_reducaoZsucesso) != null) {
				s.type(Key.ENTER);
				System.out.println("OK - Reducao Z realizada com sucesso.");
				System.out.println("Nao tem mais Reducao Z no titulo");
			}

			if (s.exists(m_OK) != null) {
				s.type(Key.ENTER);
				System.out.println("NOK - Mensagem indevida");
				s.wait(2.0);
			}
		}
	}
}
