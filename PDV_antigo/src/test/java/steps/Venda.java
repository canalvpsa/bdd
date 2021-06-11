package steps;

import java.net.URL;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

import abrirModulos.AbrirManagerSincronismo;
import classesAuxiliares.AbrirFecharRotinas;
import classesAuxiliares.ImportarOrcamento;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;


public class Venda {
	private Screen s = new Screen();
	private String imageString;
	private static Venda instancia = new Venda();
	private ImportarOrcamento importarOrcamento = ImportarOrcamento.getInstance();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private AbrirManagerSincronismo sincronismo = new AbrirManagerSincronismo();
	private Pattern m_iconePDV = new Pattern(getImage("imgUpManager/iconePDV.png")).similar(0.98f);
	
	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static Venda getInstance(){
		if (instancia ==null){
			instancia = new Venda();
		}
		return instancia;
	}

	@Dado("^que o usuário inicia uma venda$")
	public void que_o_usuário_inicia_uma_venda() throws Throwable {
		Settings.ActionLogs = false;
		cadastro.abrirRotina("venda");
	}

	@Quando("^o vendedor importa o orçamento na venda$")
	public void o_vendedor_importa_o_orçamento_na_venda() throws Throwable {
		cadastro.abrirRotina("venda");
		importarOrcamento.importaOrcamento();
	}


	@Então("^a venda é finalizada com sucesso$")
	public void a_venda_é_finalizada_com_sucesso() throws Throwable {
		Settings.ActionLogs = false;
		cadastro.finalizarPedido(false);
	}


	@Então("^a venda é finalizada com sucesso e são exibidas as parcelas$")
	public void a_venda_é_finalizada_com_sucesso_e_são_exibidas_as_parcelas() throws Throwable {
		Settings.ActionLogs = false;
		cadastro.finalizarPedido(true);
		sincronismo.sincronizar();
		s.find(m_iconePDV).click(m_iconePDV);
	}
}