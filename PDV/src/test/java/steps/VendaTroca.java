package steps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.sikuli.script.Settings;

import classesAuxiliares.ScreenshotRule;

public class VendaTroca {


	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	public void verificaLogin() {

		Settings.ActionLogs = false;
		//	login.verificaPDVfechado();
		//	login.verificaReducaoZPendente();
	}

	@Test
	public void valoresIguais() {
		/*
		rotina = "pedido";
		tipoEntrada = "teclado";
		valorTotal = "0,00";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaTrocaValoresIguais ----");
			this.verificaLogin();

			cadastro.abrirRotina(rotina);

			inserirItemBrinco.informarCodigo(qtde1);
			validaBrinco.itensInseridos(qtde1);

			inserirItemBrinco.inserir1ItemTroca();
			validaBrinco.itemTrocado(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);
			pedidoPagamento.realizarPagamentoSemValorAcumulado(valorTotal);
			cadastro.finalizarPedido(false);
		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaTrocaValoresIguais");
		}
	}

	@Test
	public void valorMaiorQueItemTrocado() {

		rotina = "pedido";
		tipoEntrada = "teclado";
		valorTotal = "41,80";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaTrocaValorMaiorQueItemTrocado ----");
			this.verificaLogin();

			cadastro.abrirRotina(rotina);
			inserirItemBrinco.informarCodigo(qtde1);
			validaBrinco.itensInseridos(qtde1);

			inserirItemBrinco.informarCodigo(qtde1);
			validaBrinco.itensInseridos(qtde1);

			inserirItemBrinco.inserir1ItemTroca();
			validaBrinco.itemTrocado(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);
		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaTrocaValorMaiorQueItemTrocado");
		}
	}

	@Test
	public void valorMenorQueItemTrocado() {

		rotina = "pedido";
		tipoEntrada = "teclado";
		valorTotal = "-41,80";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaTrocaValorMenorQueItemTrocado ----");
			this.verificaLogin();

			cadastro.abrirRotina(rotina);
			inserirItemBrinco.informarCodigo(qtde1);
			validaBrinco.itensInseridos(qtde1);

			inserirItemBrinco.inserir2ItemTroca();
			validaBrinco.itemTrocado(qtde2);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoSemValorAcumulado(valorTotal);
			cadastro.finalizarPedido(false);
		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaTrocaValorMenorQueItemTrocado");
		}
	}*/
	}
}