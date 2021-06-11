package steps;

import org.sikuli.script.Settings;

import classesAuxiliares.AbrirFecharRotinas;
import classesAuxiliares.CancelarItem;
import classesAuxiliares.OrcamentoImprimir;
import classesAuxiliares.OrcamentoSalvar;
import classesAuxiliares.ValidaTotal;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Quando;

public class Importar_orcamento {
	private static Importar_orcamento instancia = new Importar_orcamento();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private ValidaTotal validarTotal = ValidaTotal.getInstance();
	private OrcamentoImprimir orcamentoImprimir = OrcamentoImprimir.getInstance();
	private CancelarItem cancelarItem = CancelarItem.getInstance();
	private OrcamentoSalvar salvarOrcamento = OrcamentoSalvar.getInstance();
	private String tipoEntrada = "mouse", valorTotal;

	public static Importar_orcamento getInstance(){
		if (instancia ==null){
			instancia = new Importar_orcamento();
		}
		return instancia;
	}

	@Dado("^que o usuário inicia um orcamento$")
	public void que_o_usuário_inicia_um_orcamento() throws Throwable {
		Settings.ActionLogs = false;
		cadastro.abrirRotina("orcamento");
	}

	@Quando("^o valor total do orçamento é de R\\$ \"([^\"]*)\"$")
	public void o_valor_total_do_orçamento_é_de_R$(String valorTotalInformado) throws Throwable {
		valorTotal = valorTotalInformado;
		validarTotal.validaTotalAcumulado(valorTotal);
	}

	@Quando("^o orçamento é impresso$")
	public void o_orçamento_é_impresso() throws Throwable {
		orcamentoImprimir.imprime(tipoEntrada);
	}

	@Quando("^o orçamento é salvo$")
	public void o_orçamento_é_salvo() throws Throwable {
		salvarOrcamento.salvar();
		cadastro.sairOrcamento();
	}

	@Quando("^o item \"([^\"]*)\" é excluído do orcamento$")
	public void o_item_é_excluído_do_orcamento(String produto) throws Throwable {
		cancelarItem.cancelaItemOrcamento(produto);
	}

	/*
	@After
	public void after(Scenario scenario) throws FindFailed {
		if(scenario.getStatus().equals("passed")){
			if (App.focus("") != null) {
				if (s.exists(m_popup) != null) {
					s.click(m_popup);
					s.wait(5.0);
					s.type(Key.ESC);
					System.out.println("OK - Fechando popup");
					s.wait(3.0);
				}
			}
			cadastro.sairOrcamento();
		}

		if(scenario.isFailed()){

			screenshot.tiraScreenshot(scenario, this.getClass().getName());

			if (s.exists(m_sincronismo) != null) {
				if (s.exists(m_fecharSincronismo) != null) {
					s.click(m_fecharSincronismo);
					System.out.println("Fechando sincronismo");
				}
			}
			if (s.exists(m_fecharBotao) != null) {
				s.click(m_fecharBotao);
			}
			cadastro.sairOrcamento();
		}
	}
	 */
}
