package classesAuxiliares;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BotaoSim {
	private static BotaoSim instancia = new BotaoSim();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_simBorda = new Pattern(getImage("imgGeral/simBorda.png")).similar(0.95f);
	private Pattern m_simSelecionado = new Pattern(getImage("imgGeral/simSelecionado.png")).similar(0.95f);
	private Pattern m_simAzul = new Pattern(getImage("imgGeral/simAzul.png")).similar(0.95f);

	private String getImage(String path) {
		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static BotaoSim getInstance(){
		if (instancia ==null){
			instancia = new BotaoSim();
		}
		return instancia;
	}

	public String procurarSim(){
		if(s.exists(m_simBorda) != null){
			return "m_simBorda";
		}
		else{
			if(s.exists(m_simSelecionado) != null){
				return "m_simSelecionado";
			}
			else{
				if(s.exists(m_simAzul) != null){
					return "m_simAzul";
				}
				else{
					return null;
				}
			}
		}
	}

	public void clicarSim() throws FindFailed{

		if(s.exists(m_simBorda) != null){
			s.click(m_simBorda);
		}
		if(s.exists(m_simSelecionado) != null){
			s.click(m_simSelecionado);
		}

		if(s.exists(m_simAzul) != null){
			s.click(m_simAzul);
		}
	}
}