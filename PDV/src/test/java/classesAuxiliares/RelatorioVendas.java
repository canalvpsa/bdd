package classesAuxiliares;

import static org.junit.Assert.assertFalse;

import java.net.URL;
import java.sql.SQLException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import DAO.impl.UltimoCupom;



public class RelatorioVendas {
	private static RelatorioVendas instancia = new RelatorioVendas();
	private Screen s = new Screen();

	private DAO.impl.UltimoCupom consultaUltimoCF = new UltimoCupom();
	String resultado;
	private String imageString;
	private Pattern m_cancelarVenda = new Pattern(getImage("imgVendas/cancelarVenda.png")).similar(0.95f);
	private Pattern m_cancelarCupom = new Pattern(getImage("imgVendas/cancelarCupom.png")).similar(0.95f);
	private Pattern m_numeroPedido = new Pattern(getImage("imgVendas/numeroPedido.png")).similar(0.95f);
	private Pattern m_autorizar = new Pattern(getImage("imgVendas/autorizar.png")).similar(0.95f);
	private Pattern m_pesquisar = new Pattern(getImage("imgVendas/pesquisar.png")).similar(0.95f);
	private Pattern m_valor28000 = new Pattern(getImage("imgVendas/valor280.80.png")).similar(0.95f);
	private Pattern m_ativo = new Pattern(getImage("imgVendas/ativo.png")).similar(0.95f);
	private Pattern m_informacoesVenda = new Pattern(getImage("imgVendas/informacoesVenda.png")).similar(0.95f);
	private Pattern m_pesquisaMovimentacoes = new Pattern(getImage("imgVendas/pesquisaMovimentacoes.png")).similar(0.95f);
	private Pattern m_confirmar = new Pattern(getImage("imgGeral/confirmar.png")).similar(0.90f);
	private Pattern m_loginGerente = new Pattern(getImage("imgGeral/loginGerente.png")).similar(0.90f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static RelatorioVendas getInstance(){
		if (instancia ==null){
			instancia = new RelatorioVendas();
		}
		return instancia;
	}

	public void pesquisarVenda() throws ClassNotFoundException, SQLException, FindFailed {
		resultado = consultaUltimoCF.getUltimoCupom();
		s.wait(m_numeroPedido, 10);
		s.type(resultado);
		s.wait(3.0);
		s.click(m_pesquisar);

		if (s.exists(m_valor28000) != null) {
			if (s.exists(m_ativo) != null) {
				s.wait(3.0);
				s.doubleClick(m_valor28000);
			} else {
				assertFalse("NOK - Venda nao esta ativa", true);
			}
		} else {
			assertFalse("NOK - Venda nao encontrada para detalhamento",
					true);
		}
		s.wait(m_informacoesVenda, 30.0);
	}


	public void cancelarVendaRelatorio() throws FindFailed {
		s.click(m_cancelarVenda);
		s.click(m_confirmar);
		s.wait(m_loginGerente, 30.0);
		s.type("usuario");
		s.type(Key.TAB);
		s.type("varejonline");
		s.wait(3.0);
		s.click(m_autorizar);
		s.wait(m_cancelarCupom,5.0);
		s.type(Key.ENTER);
		s.wait(2.0);
	}

	public void sairRelatorioVenda() throws FindFailed {
		while (s.exists(m_pesquisaMovimentacoes) != null) {
			s.click(m_pesquisaMovimentacoes);
			s.type(Key.ESC);
			s.wait(3.0);
		}
	}
}