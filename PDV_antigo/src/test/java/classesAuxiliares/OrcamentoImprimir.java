package classesAuxiliares;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class OrcamentoImprimir {
	private static OrcamentoImprimir instancia = new OrcamentoImprimir();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_imprimirOrcamento = new Pattern(getImage("imgOrcamentoPedido/orcamentoImprimir.png")).similar(0.98f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static OrcamentoImprimir getInstance(){
		if (instancia ==null){
			instancia = new OrcamentoImprimir();
		}
		return instancia;
	}

	public void imprime(String tipoEntrada) throws InterruptedException, FindFailed {

			if (tipoEntrada == "teclado") {
				s.type(Key.F6);
				System.out.println("OK - Impressao orcamento pelo atalho F6");
			}

			if (tipoEntrada == "mouse") {
				s.click(m_imprimirOrcamento);
				System.out.println("OK - Impressao orcamento pelo clique na opcao");
			}

			s.type(Key.ENTER);
			s.wait(3.0);
	}
}