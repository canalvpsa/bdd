package classesAuxiliares;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class OrcamentoSalvar {
	private static OrcamentoSalvar instancia = new OrcamentoSalvar();

	private Screen s = new Screen();

	public void salvar() throws InterruptedException, FindFailed {

		s.type(Key.F4);
		s.type(Key.ENTER);
		s.wait(3.0);
	}

	public static OrcamentoSalvar getInstance(){
		if (instancia ==null){
			instancia = new OrcamentoSalvar();
		}
		return instancia;
	}
}
