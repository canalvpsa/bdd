package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BaixaParcela_pagamento {
	private static BaixaParcela_pagamento instancia = new BaixaParcela_pagamento();
	private Screen s = new Screen();
	private Pattern m_pagamentoBaixa4040 = new Pattern(getImage("imgBaixaParcela/pagamentoBaixa40.40.png")).exact();
	private Pattern m_pagamentoBaixa10000 = new Pattern(getImage("imgBaixaParcela/pagamentoBaixa100.00.png")).exact();
	private Pattern m_pagamentoBaixa14040 = new Pattern(getImage("imgBaixaParcela/pagamentoBaixa140.40.png")).exact();
	private String imageString;


	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static BaixaParcela_pagamento getInstance(){
		if (instancia ==null){
			instancia = new BaixaParcela_pagamento();
		}
		return instancia;
	}


	public void realizarPagamentoDinheiroBaixaParcela(String valor) throws InterruptedException, FindFailed {
		efetivaPagamentoDinheiro();
		validaTotais(valor);
	}

	public void realizarPagamentoDinheiroComTrocoBaixaParcela(String valor, String valorPago) throws InterruptedException, FindFailed {
		efetivaPagamentoDinheiroDiferenteTotal(valorPago);
		validaTotaisComTroco(valor);
	}

	public void realizaPagamentoParcialBaixaParcela(String valorPago) throws FindFailed{
		efetivaPagamentoDinheiroDiferenteTotal(valorPago);
		validaTotaisBaixaParcial(valorPago);
	}

	public void efetivaPagamentoDinheiro() throws FindFailed{
		s.type(Key.F8);
		s.type(Key.ENTER);
		s.wait(2.0);
	}

	public void efetivaPagamentoDinheiroDiferenteTotal(String valorPago) throws FindFailed{

		valorPago = valorPago.replace(",", ".");
		s.wait(3.0);
		s.type(Key.F8);
		s.type("a", KeyModifier.CTRL);
		s.type(Key.BACKSPACE);
		s.type(valorPago);
		s.wait(3.0);
		s.type(Key.ENTER);
	}

	public void validaTotais(String valor){
		switch (valor) {

		case "140,40":
			if(s.exists(m_pagamentoBaixa14040) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "40,40":
			if(s.exists(m_pagamentoBaixa4040) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;
		}
	}

	public void validaTotaisComTroco(String valor){
		switch (valor) {

		case "280,80":
			if(s.exists(m_pagamentoBaixa10000) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
		}
	}

	public void validaTotaisBaixaParcial(String valor){
		switch (valor) {

		//BAIXA DE PARCELA PARCIAL
		case "100,00":
			if(s.exists(m_pagamentoBaixa10000) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;
		}
	}
}
