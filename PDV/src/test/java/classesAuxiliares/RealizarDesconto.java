package classesAuxiliares;

import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class RealizarDesconto {
	private static RealizarDesconto instancia = new RealizarDesconto();

	private Screen s = new Screen();
	private BotaoOK botaoOK = new BotaoOK();
	private String imageString;
	private Pattern m_autorizacaoGerente = new Pattern(getImage("imgGeral/autorizacaoGerente.png")).similar(0.95f);
	private Pattern m_descontoVendedor = new Pattern(getImage("imgOrcamentoPedido/descontoVendedor.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static RealizarDesconto getInstance(){
		if (instancia ==null){
			instancia = new RealizarDesconto();
		}
		return instancia;
	}

	public void descontoTotalEmReais(Boolean permitido, String desconto) throws FindFailed {
		s.type(Key.F7);
		s.type(Key.TAB);

		s.type(Key.DELETE);
		s.type(desconto);
		s.type(Key.TAB);

		s.type(Key.ENTER);

	}

	public void descontoTotalEmPercentual(Boolean permitido, String desconto) throws FindFailed {
		s.type(Key.F7);

		s.type(Key.DELETE);
		s.type(desconto);
		s.type(Key.TAB);

		s.type(Key.TAB);

		s.type(Key.ENTER);
		validaPermissaoDesconto(permitido);
	}

	public void validaPermissaoDesconto(Boolean permitido) throws FindFailed{
		if (permitido == false){
			if (s.exists(m_descontoVendedor) != null) {
				s.wait(3.0);
				botaoOK.clicarOK(botaoOK.procurarOK());
				s.wait(5.0);

				if (s.exists(m_autorizacaoGerente) != null) {
					s.type("usuario");
					s.type(Key.TAB);
					s.type("vpsa");
					s.type(Key.ENTER);
					s.wait(3.0);
				}
			}
		}
	}

	// Desconto em reais ou percentual
	public void descontoMaiorQuePermitidoVendedor(String desconto, String tipoDesconto) throws InterruptedException, FindFailed {
		s.type(Key.F7);

		if (tipoDesconto == "reais") {
			s.type(Key.TAB);
		}
		s.type(Key.DELETE);
		s.type(desconto);
		botaoOK.clicarOK(botaoOK.procurarOK());
		System.out.println("OK - Informou desconto R$ " + desconto + " " + tipoDesconto);
		s.wait(5.0);

		if (s.exists(m_descontoVendedor) != null) {
			s.wait(3.0);
			botaoOK.clicarOK(botaoOK.procurarOK());
			System.out.println("OK - Desconto n�o permitido para o vendedor.");
			s.wait(5.0);

			if (s.exists(m_autorizacaoGerente) != null) {
				System.out.println("OK - Pediu autorizacao do gerente.");
				s.type("usuario");
				s.type(Key.TAB);
				s.type("vpsa");
				s.type(Key.ENTER);
				System.out.println("OK - Informou usuario e senha do gerente");
				s.wait(3.0);
			} else {
				fail("NOK - Nao solicitou usuario e senha do gerente");
			}
		} else {
			fail("NOK - Nao reclamou do desconto concedido pelo vendedor");
		}
	}
}
