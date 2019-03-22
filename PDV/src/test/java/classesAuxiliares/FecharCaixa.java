package classesAuxiliares;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

public class FecharCaixa {
	private static FecharCaixa instancia = new FecharCaixa();
	private Screen s = new Screen();
	private BotaoOK botaoOK = new BotaoOK();
	private BotaoSim botaoSim = new BotaoSim();
	private String imageString;

	private Pattern m_fecharCaixa = new Pattern(getImage("imgGeral/fecharCaixa.png")).similar(0.95f);
	private Pattern m_fechamentoAutomatico = new Pattern(getImage("imgGeral/fechamentoAutomatico.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static FecharCaixa getInstance(){
		if (instancia ==null){
			instancia = new FecharCaixa();
		}
		return instancia;
	}

	public void fechamentoCaixaAutomatico() {

		Settings.ActionLogs = false;
		try {
		//	login.verificaPDVfechado();
			if (s.exists(m_fecharCaixa) != null) {
				s.click(m_fecharCaixa);

				if (botaoSim.procurarSim() != null) {
					botaoSim.clicarSim();
				} else {
					assertFalse("NOK - Nao encontrado botao confirmacao", true);
				}

				if (s.exists(m_fechamentoAutomatico) != null) {
					botaoOK.clicarOK(botaoOK.procurarOK());
				}
			} else {
				assertFalse("NOK - Nao encontrado botao fechar caixa", true);
			}
		} catch (FindFailed e) {
			fail("Erro ao abrir orcamento");
		}
	}
}