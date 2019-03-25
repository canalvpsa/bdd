package classesAuxiliares;

import static org.junit.Assert.fail;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import DAO.impl.UltimoOrcamento;

public class ImportarOrcamento {

	private static ImportarOrcamento instancia = new ImportarOrcamento();
	private Screen s = new Screen();
	private String imageString;
	private DAO.impl.UltimoOrcamento consultaUltimoOrcamento = new UltimoOrcamento();

	// ImgOrcamentoPedido
	private Pattern m_importar = new Pattern(getImage("imgOrcamentoPedido/importar.png")).similar(0.95f);
	private Pattern m_importarDAV = new Pattern(getImage("imgOrcamentoPedido/importarDav-PreVenda.png")).similar(0.95f);

	//private Pattern m_pesqValor0 = new Pattern(getImage("imgOrcamentoPedido/pesqValor0.png")).similar(0.99f);
	private Pattern m_pesqValor3762 = new Pattern(getImage("imgOrcamentoPedido/pesqValor37.62.png")).similar(0.99f);
	private Pattern m_pesqValor5990 = new Pattern(getImage("imgOrcamentoPedido/pesqValor59.90.png")).similar(0.99f);
	private Pattern m_pesqValor28080 = new Pattern(getImage("imgOrcamentoPedido/pesqValor280.80.png")).similar(0.99f);
	private Pattern m_pesqValor4180 = new Pattern(getImage("imgOrcamentoPedido/pesqValor41.80.png")).similar(0.99f);
	private Pattern m_orcamentoImportar;
	// ImgGeral
	private Pattern m_telaBranca = new Pattern(getImage("imgGeral/telaBranca.png")).similar(0.90f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static ImportarOrcamento getInstance(){
		if (instancia ==null){
			instancia = new ImportarOrcamento();
		}
		return instancia;
	}

	public void importaOrcamento() throws FindFailed, ClassNotFoundException, SQLException {

		String ultimoOrcamento = consultaUltimoOrcamento.getUltimoOrcamento();
		s.type(Key.F6);
		s.wait(m_importarDAV,10.0);

		s.type(ultimoOrcamento);
		s.type(Key.ENTER);
		s.wait(3.0);
		importarOrcamento(ultimoOrcamento);
	}

	public void importarOrcamento(String ultimoOrcamento) throws FindFailed {

		while (m_telaBranca == null) {
		}

		valorOrcamento();
		if(m_orcamentoImportar != null){
			s.click(m_orcamentoImportar);
			s.type(Key.PAGE_DOWN);
			if (s.exists(m_importar) != null) {
				s.click(m_importar);

				while (m_telaBranca == null) {
				}
			}
		}else{
			finaliza();
		}
	}

	public void valorOrcamento(){
		ArrayList<Pattern> valorOrcamento = new ArrayList<Pattern>();
		valorOrcamento.add(m_pesqValor3762);
		valorOrcamento.add(m_pesqValor28080);
		valorOrcamento.add(m_pesqValor4180);
		valorOrcamento.add(m_pesqValor5990);

		for (int i = 0; i < valorOrcamento.size(); i++) {
			if(s.exists(valorOrcamento.get(i)) != null) {
				m_orcamentoImportar = valorOrcamento.get(i);
			}
		}
	}


public void finaliza() throws FindFailed {

	System.out.println("Nao foi possivel encontrar o orcamento");
	s.type(Key.ESC);
	s.type(Key.ESC);
	fail("Nao foi possivel encontrar o orcamento");
}
}