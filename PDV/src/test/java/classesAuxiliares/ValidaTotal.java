package classesAuxiliares;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ValidaTotal {
	private static ValidaTotal instancia = new ValidaTotal();
	private String imageString;
	private Screen s = new Screen();
	private Pattern m_totalAcumulado4180negativo = new Pattern(getImage("imgTotais/totalAcumulado-41.80.png")).similar(0.99f);
	private Pattern m_totalAcumulado0 = new Pattern(getImage("imgTotais/totalAcumulado0.00.png")).similar(0.99f);
	private Pattern m_totalAcumulado3762 = new Pattern(getImage("imgTotais/totalAcumulado37.62.png")).similar(0.99f);
	private Pattern m_totalAcumulado4040 = new Pattern(getImage("imgTotais/totalAcumulado40.40.png")).similar(0.99f);
	private Pattern m_totalAcumulado4180 = new Pattern(getImage("imgTotais/totalAcumulado41.80.png")).similar(0.99f);
	private Pattern m_totalAcumulado5000 = new Pattern(getImage("imgTotais/totalAcumulado50.00.png")).similar(0.99f);
	private Pattern m_totalAcumulado5990 = new Pattern(getImage("imgTotais/totalAcumulado59.90.png")).similar(0.99f);
	private Pattern m_totalAcumulado8360 = new Pattern(getImage("imgTotais/totalAcumulado83.60.png")).similar(0.99f);
	private Pattern m_totalAcumulado9180 = new Pattern(getImage("imgTotais/totalAcumulado91.80.png")).similar(0.99f);
	private Pattern m_totalAcumulado14040 = new Pattern(getImage("imgTotais/totalAcumulado140.40.png")).similar(0.99f);
	private Pattern m_totalAcumulado28080 = new Pattern(getImage("imgTotais/totalAcumulado280.80.png")).similar(0.99f);
	private Pattern m_totalAcumulado23900 = new Pattern(getImage("imgTotais/totalAcumulado239.00.png")).similar(0.99f);
	private Pattern m_totalAcumulado31424 = new Pattern(getImage("imgTotais/totalAcumulado314.24.png")).similar(0.99f);
	private Pattern m_totalAcumulado;
	String totalAcumulado;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static ValidaTotal getInstance(){
		if (instancia ==null){
			instancia = new ValidaTotal();
		}
		return instancia;
	}

	public void validaTotalAcumulado(String valor) {

		switch (valor) {

		//valorMenorQueItemTrocado
		case "-41,80":
			m_totalAcumulado = m_totalAcumulado4180negativo;
			totalAcumulado = valor;
			break;

			//valoresIguais
		case "0,00":
			m_totalAcumulado = m_totalAcumulado0;
			totalAcumulado = valor;
			break;

			//COM DESCONTO
		case "37,62":
			m_totalAcumulado = m_totalAcumulado3762;
			totalAcumulado = valor;
			break;

			// BAIXA DE PARCELA
		case "40,40":
			m_totalAcumulado = m_totalAcumulado4040;
			totalAcumulado = valor;
			break;

			//BRINCO
		case "41,80":
			m_totalAcumulado = m_totalAcumulado4180;
			totalAcumulado = valor;
			break;

			//SERVICO
		case "50,00":
			m_totalAcumulado = m_totalAcumulado5000;
			totalAcumulado = valor;
			break;

			//REGATA
		case "59,90":
			m_totalAcumulado = m_totalAcumulado5990;
			totalAcumulado = valor;
			break;

			//vendaCancelarItem
		case "83,60":
			m_totalAcumulado = m_totalAcumulado8360;
			totalAcumulado = valor;
			break;

		case "91,80":
			m_totalAcumulado = m_totalAcumulado9180;
			totalAcumulado = valor;
			break;

			// BAIXA DE PARCELA
		case "140,40":
			m_totalAcumulado = m_totalAcumulado14040;
			totalAcumulado = valor;
			break;

			//vendaSimples
		case "280,80":
			m_totalAcumulado = m_totalAcumulado28080;
			totalAcumulado = valor;
			break;

			//CALCA JEANS
		case "239,00":
			m_totalAcumulado = m_totalAcumulado23900;
			totalAcumulado = valor;
			break;

			//descontoUnitarioReais2itens
		case "314,24":
			m_totalAcumulado = m_totalAcumulado31424;
			totalAcumulado = valor;
			break;

		default:
			fail("NOK - Valor total nao foi validado");
			break;
		}

		if(s.exists(m_totalAcumulado) == null){
			assertFalse("NOK valor total acumulado "+totalAcumulado, true);
		}
	}
}