package classesAuxiliares;

import static org.junit.Assert.fail;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class AbrirOrcamento {

	private Screen s = new Screen();

	public void abreOrcamentoDescontoPorcentagem() {

		try {
			s.type("A", Key.CTRL);

		} catch (FindFailed e) {
			fail("Erro ao abrir orcamento");
		}
	}
}