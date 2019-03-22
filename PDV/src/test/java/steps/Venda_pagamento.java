package steps;

import classesAuxiliares.AbrirFecharRotinas;
import classesAuxiliares.PedidoPagamento;
import classesAuxiliares.ValidaTotal;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class Venda_pagamento {
	private static Venda_pagamento instancia = new Venda_pagamento();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private ValidaTotal validarTotal = ValidaTotal.getInstance();
	private PedidoPagamento pedidoPagamento = PedidoPagamento.getInstance();
	private String tipoEntrada = "mouse", valorTotal;


	public static Venda_pagamento getInstance(){
		if (instancia ==null){
			instancia = new Venda_pagamento();
		}
		return instancia;
	}
	
	@Quando("^o valor total da venda é de R\\$ \"([^\"]*)\"$")
	public void o_valor_total_da_venda_é_de_R$(String valorTotalInformado) throws Throwable {
		valorTotal = valorTotalInformado;
		validarTotal.validaTotalAcumulado(valorTotal);
	}


	@Quando("^adiciona o plano de pagamento dinheiro$")
	public void adiciono_o_plano_de_pagamento_dinheiro() throws Throwable {
		pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
	}

	@Quando("^adiciona o plano de pagamento dinheiro informando R\\$ \"([^\"]*)\"$")
	public void adiciono_o_plano_de_pagamento_dinheiro_informando_R$(String valorPago) throws Throwable {
		pedidoPagamento.realizarPagamentoDinheiroComTroco(tipoEntrada, valorTotal, valorPago);
	}

	@Quando("^adiciona o plano de pagamento crediário$")
	public void adiciona_o_plano_de_pagamento_crediário() throws Throwable {
		pedidoPagamento.realizaPagamentoCrediario();
	}

	@Então("^o usuário confirma o valor do troco$")
	public void eu_confirmo_o_valor_do_troco() throws Throwable {
		cadastro.finalizarPedidoComTroco();
	}

	//generico
	@Quando("^adiciona o plano de pagamento$")
	public void adiciona_o_plano_de_pagamento() throws Throwable {
		pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, "41,80");
	}
	
	@Quando("^adiciona o plano de pagamento dinheiro informando valor parcial R\\$ \"([^\"]*)\"$")
	public void adiciona_o_plano_de_pagamento_dinheiro_informando_valor_parcial_R$(String arg1) throws Throwable {
	   
	}
	
	@Quando("^será carregado o plano de pagamento \"([^\"]*)\" no valor de R\\$ \"([^\"]*)\"$")
	public void seráCarregadoOPlanoDePagamentoNoValorDeR$(String arg1, String valorTroca) throws Throwable {
	    pedidoPagamento.efetivaPagamentoTroca(false);
	}
}
