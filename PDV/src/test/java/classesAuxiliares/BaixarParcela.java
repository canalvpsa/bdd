package classesAuxiliares;

import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BaixarParcela {

	private static BaixarParcela instancia = new BaixarParcela();
	private Screen s = new Screen();
	private AbrirFecharRotinas abrirFecharRotinas = AbrirFecharRotinas.getInstance();
	private String imageString;
	private Pattern m_parcela1de4040 = new Pattern(getImage("imgBaixaParcela/parcela1de40.40.png")).similar(0.98f);
	private Pattern m_parcela1de14040 = new Pattern(getImage("imgBaixaParcela/parcela1de140.40.png")).similar(0.98f);
	private Pattern m_parcela2de14040 = new Pattern(getImage("imgBaixaParcela/parcela2de140.40.png")).similar(0.98f);
	private Pattern m_parcela1de4040selecionada = new Pattern(getImage("imgBaixaParcela/parcela1de40.40selecionada.png")).similar(0.98f);
	private Pattern m_parcela1de14040selecionada = new Pattern(getImage("imgBaixaParcela/parcela1de140.40selecionada.png")).similar(0.98f);
	private Pattern m_parcela2de14040selecionada = new Pattern(getImage("imgBaixaParcela/parcela2de140.40selecionada.png")).similar(0.98f);
	private Pattern m_rotinaBaixaParcela = new Pattern(getImage("imgBaixaParcela/rotinaBaixaParcela.png")).similar(0.98f);
	private Pattern m_pesquisaParcelas = new Pattern(getImage("imgBaixaParcela/pesquisaParcelas.png")).similar(0.98f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static BaixarParcela getInstance(){
		if (instancia ==null){
			instancia = new BaixarParcela();
		}
		return instancia;
	}

	public void selecionarParcela(String numeroParcela, String valorParcela) throws FindFailed {
		s.type("ANA MARIA");
		s.type(Key.ENTER);

		switch (numeroParcela) {
		case "1":
			if (valorParcela.contains("140,40")) {
				if (s.exists(m_parcela1de14040selecionada) != null) {
					s.doubleClick(m_parcela1de14040selecionada);
				} else {
					s.doubleClick(m_parcela1de14040);
				}
			}else{
				if (valorParcela.contains("40,40")) {
					if (s.exists(m_parcela1de4040selecionada) != null) {
						s.doubleClick(m_parcela1de4040selecionada);
					} else {
						s.doubleClick(m_parcela1de4040);
					}
				}
			}
			break;

		case "2":
			if (s.exists(m_parcela2de14040selecionada) != null) {
				s.doubleClick(m_parcela2de14040selecionada);
			} else {
				s.doubleClick(m_parcela2de14040);
			}
			break;

		default:
			fail("Numero de parcela nao prevista");
			break;
		}

		s.wait(m_rotinaBaixaParcela, 10.0);

		if (s.exists(m_pesquisaParcelas) != null) {
			abrirFecharRotinas.fecharBaixaParcela();
		}
	}

	public void selecionarDuasParcelas(String numeroParcela1, String numeroParcela2, String valorParcela) throws FindFailed {
		s.type("ANA MARIA");
		s.type(Key.ENTER);

		if (valorParcela.contains("140,40")) {
			if (s.exists(m_parcela1de14040selecionada) != null) {
				s.click(m_parcela1de14040selecionada);
			}

			s.type(Key.CTRL);

			if (s.exists(m_parcela2de14040selecionada) != null) {
				s.doubleClick(m_parcela2de14040selecionada);
			} 
		}
	}
}