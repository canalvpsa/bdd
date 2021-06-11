package orcamento;

import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Settings;

import classesAuxiliares.AbrirFecharRotinas;
import classesAuxiliares.InserirItem_CalcaJeans;
import classesAuxiliares.ScreenshotRule;
import classesAuxiliares.VendedorCliente;

public class OrcamentoProdutoSemSaldo {

	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();
	
	private InserirItem_CalcaJeans inserirItemCalcaJeans = new InserirItem_CalcaJeans();
	private VendedorCliente vendedorCliente = new VendedorCliente();
	@SuppressWarnings("unused")
	private String rotina, codigoItem1, codigoItem2, qtde1, qtde2, tipoEntrada, formaPagamento, valorTotal, valorDesconto, tipoDesconto;

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	public void setarPedido(String p_rotina, String p_codigoItem1, String p_codigoItem2, String p_qtde1, String p_qtde2, String p_tipoEntrada, String p_formaPagamento, String p_valorTotal, String p_valorDesconto, String p_tipoDesconto) {

		rotina = p_rotina;
		codigoItem1 = p_codigoItem1;
		codigoItem2 = p_codigoItem2;
		qtde1 = p_qtde1;
		qtde2 = p_qtde2;
		tipoEntrada = p_tipoEntrada;
		// vale, adiantamento, dinheiro, cheque, cartao
		formaPagamento = p_formaPagamento;
		valorTotal = p_valorTotal;
		valorDesconto = p_valorDesconto;
		tipoDesconto = p_tipoDesconto;
	}

	@Test
	public void cadastroOrcamentoProdutoSemSaldoInsercaoRapidaNomeInteiro() throws FindFailed {

		Settings.ActionLogs = false;
		this.setarPedido("orcamento", "BAF001.0003", "", "1", "", "mouse", "dinheiro", "59,90", "", "");
		try {
			System.out.println("\n\n---- Inicio CadastroOrcamentoProdutoSemSaldoInsercaoRapidaNomeInteiro ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();
			inserirItemCalcaJeans.informarNomeInteiro(rotina, qtde1);

		} catch (Exception e) {
			fail("erro no CadastroOrcamentoProdutoSemSaldoInsercaoRapidaNomeInteiro");
		}
	}

	@Test
	public void cadastroOrcamentoProdutoSemSaldoInsercaoRapidaNomeParcial() throws FindFailed {

		Settings.ActionLogs = false;
		this.setarPedido("orcamento", "BAF001.0003", "", "1", "", "mouse", "dinheiro", "59,90", "", "");
		try {
			System.out.println("\n\n---- Inicio CadastroOrcamentoProdutoSemSaldoInsercaoRapidaNomeParcial ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();
			inserirItemCalcaJeans.informarNomeParcial(rotina, qtde1);

		} catch (Exception e) {
			fail("erro no CadastroOrcamentoProdutoSemSaldoInsercaoRapidaNomeParcial");
		}
	}

	@Test
	public void cadastroOrcamentoProdutoSemSaldoPesqRapida() throws FindFailed {

		Settings.ActionLogs = false;
		this.setarPedido("orcamento", "BAF001.0003", "", "1", "", "mouse", "dinheiro", "59,90", "", "");
		try {
			System.out.println("\n\n---- Inicio CadastroOrcamentoProdutoSemSaldoPesqRapida ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();
		//	inserirItem.insereItemPesquisa(rotina, codigoItem1, qtde1, false);
//
		} catch (Exception e) {
			fail("erro no CadastroOrcamentoProdutoSemSaldoPesqRapida");
		}
	}
}