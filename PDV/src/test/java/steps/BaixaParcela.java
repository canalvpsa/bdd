package steps;

import org.sikuli.script.Settings;

import DAO.impl.ParcelaEmAberto;
import classesAuxiliares.AbrirFecharRotinas;
import classesAuxiliares.BaixaParcela_pagamento;
import classesAuxiliares.BaixarParcela;
import classesAuxiliares.ValidaTotal;
import classesAuxiliares.VendedorCliente;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class BaixaParcela {
	private ParcelaEmAberto BDparcela = new ParcelaEmAberto();
	private BaixarParcela baixarParcela = BaixarParcela.getInstance();
	private Venda_pagamento pagamento_venda = Venda_pagamento.getInstance();
	private BaixaParcela_pagamento pagamento_baixa = BaixaParcela_pagamento.getInstance();
	private VendedorCliente vendedorCliente = VendedorCliente.getInstance();
	private Venda venda = Venda.getInstance();
	private Venda_itens itens = Venda_itens.getInstance();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private ValidaTotal validaTotal = ValidaTotal.getInstance();

	@Dado("^que o cliente já possui parcela de crediário em aberto$")
	public void que_o_cliente_já_possui_parcela_de_crediário_em_aberto() throws Throwable {
		if(BDparcela.getparcela(1, 140.40) == false){
			venda.que_o_usuário_inicia_uma_venda();
			vendedorCliente.vincularCliente("ANA MARIA");
			itens.adiciona_o_item_com_quantidade_com_valor_unitário_de_R$("brinco", "1", "41,80");
			itens.adiciona_o_item_com_quantidade_com_valor_unitário_de_R$("calca", "1", "239,00");
			pagamento_venda.adiciona_o_plano_de_pagamento_crediário();
			venda.a_venda_é_finalizada_com_sucesso_e_são_exibidas_as_parcelas();
			cadastro.sairPedido();
		}
	}


	@Dado("^que o cliente já possui parcela de crediário em aberto no valor de R\\$ \"([^\"]*)\" gerada por baixa parcial$")
	public void que_o_cliente_já_possui_parcela_de_crediário_em_aberto_no_valor_de_R$_gerada_por_baixa_parcial(String arg1) throws Throwable {

	}

	@Dado("^o vendedor lista as parcelas do cliente$")
	public void o_vendedor_lista_as_parcelas_do_cliente() throws Throwable {
		Settings.ActionLogs = false;
		cadastro.abrirRotina("BaixaParcela");
	}

	@Quando("^o vendedor seleciona a parcela \"([^\"]*)\" para pagamento no valor de R\\$ \"([^\"]*)\"$")
	public void o_vendedor_seleciona_a_parcela_para_pagamento_no_valor_de_R$(String parcela, String valorParcela) throws Throwable {
		baixarParcela.selecionarParcela(parcela, valorParcela);
		validaTotal.validaTotalAcumulado(valorParcela);
	}

	@Quando("^o vendedor seleciona as parcelas \"([^\"]*)\" e \"([^\"]*)\" para pagamento no valor de R\\$ \"([^\"]*)\" cada$")
	public void o_vendedor_seleciona_as_parcelas_e_para_pagamento_no_valor_de_R$_cada(String numParcela1, String numParcela2, String valorParcela) throws Throwable {

	}

	@Quando("^adiciona o plano de pagamento dinheiro informando valor parcial R\\$ \"([^\"]*)\" na baixa de parcela$")
	public void adicionaOPlanoDePagamentoDinheiroInformandoValorParcialR$NaBaixaDeParcela(String valor) throws Throwable {
		pagamento_baixa.realizaPagamentoParcialBaixaParcela(valor);
	}


	@Quando("^adiciona o plano de pagamento dinheiro na baixa de parcela informando R\\$ \"([^\"]*)\"$")
	public void adicionaOPlanoDePagamentoDinheiroNaBaixaDeParelaInformandoR$(String valorInformado) throws Throwable {
		pagamento_baixa.efetivaPagamentoDinheiro();
		pagamento_baixa.validaTotais(valorInformado);
	}

	@Então("^a baixa parcial é realizada com sucesso$")
	public void a_baixa_parcial_é_realizada_com_sucesso() throws Throwable {
		cadastro.finalizarBaixaParcelaParcial();
	}


	@Então("^a baixa é realizada com sucesso$")
	public void a_baixa_é_realizada_com_sucesso() throws Throwable {
		cadastro.finalizarBaixaParcelaTotal();
	}

	@Então("^uma nova parcela é criada no valor de R\\$ \"([^\"]*)\"$")
	public void uma_nova_parcela_é_criada_no_valor_de_R$(String arg1) throws Throwable {

	}
}