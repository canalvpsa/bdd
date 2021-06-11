package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class FecharJasperViewer {

	private static FecharJasperViewer instancia = new FecharJasperViewer();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_fecharJasperViewer = new Pattern(getImage("imgGeral/fecharJasperViewer.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static FecharJasperViewer getInstance(){
		if (instancia ==null){
			instancia = new FecharJasperViewer();
		}
		return instancia;
	}

	public void fecharJasperViewer() throws FindFailed {
		if (s.exists(m_fecharJasperViewer) != null) {
			s.click(m_fecharJasperViewer);
			System.out.println("OK - Fechou tela jasper viewer");
		} else {
			assertFalse("NOK nao foi possivel focar no jasper viewer", true);
		}
	}
}
