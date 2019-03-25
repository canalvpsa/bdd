package steps;

import org.sikuli.script.Settings;

import classesAuxiliares.AbrirFecharRotinas;
import classesAuxiliares.Funcoes;
import classesAuxiliares.InserirItem_BrincoEtnico;
import classesAuxiliares.InserirItem_CalcaJeans;
import classesAuxiliares.PedidoPagamento;
import classesAuxiliares.RelatorioVendas;
import classesAuxiliares.ValidaBrincoListagemProdutos;
import classesAuxiliares.ValidaCalcaListagemProdutos;
import classesAuxiliares.ValidaTotal;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class Venda_cancelamento {
	private static Venda_cancelamento instancia = Venda_cancelamento.getInstance();
	private InserirItem_BrincoEtnico inserirItemBrinco = InserirItem_BrincoEtnico.getInstance();
	private ValidaBrincoListagemProdutos validaBrinco = ValidaBrincoListagemProdutos.getInstance();
	private InserirItem_CalcaJeans inserirItemCalcaJeans = InserirItem_CalcaJeans.getInstance();
	private ValidaCalcaListagemProdutos validaCalca = ValidaCalcaListagemProdutos.getInstance();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private ValidaTotal validarTotal = ValidaTotal.getInstance();
	private PedidoPagamento pedidoPagamento = PedidoPagamento.getInstance();
	private RelatorioVendas relVendas = RelatorioVendas.getInstance();
	private Funcoes funcoes = Funcoes.getInstance();

	public static Venda_cancelamento getInstance(){
		if (instancia ==null){
			instancia = new Venda_cancelamento();
		}
		return instancia;
	}

	@Quando("^cancela o último CF emitido$")
	public void cancela_o_último_CF_emitido() throws Throwable {
		cadastro.abrirRotina("funcoes");
		funcoes.cancelarUltimoCF();
		cadastro.sairFuncoes();
	}

	@Dado("^que o usuário realizou uma venda$")
	public void que_o_usuário_realizou_uma_venda() throws Throwable {
		Settings.ActionLogs = false;
		cadastro.abrirRotina("venda");
		inserirItemBrinco.informarCodigo("1");
		validaBrinco.itensInseridos("1",false);
		inserirItemCalcaJeans.informarCodigo("1");
		validaCalca.itensInseridos("1");
		validarTotal.validaTotalAcumulado("280,80");
		pedidoPagamento.realizarPagamentoDinheiro("mouse","280,80");
		cadastro.finalizarPedido(false);
	}

	@Dado("^que o usuário iniciou uma venda e já informou pagamento em dinheiro$")
	public void que_o_usuário_iniciou_uma_venda_e_já_informou_pagamento_em_dinheiro() throws Throwable {
		Settings.ActionLogs = false;
		cadastro.abrirRotina("venda");
		inserirItemBrinco.informarCodigo("1");
		validaBrinco.itensInseridos("1",false);
		validarTotal.validaTotalAcumulado("41,80");
		pedidoPagamento.realizarPagamentoDinheiro("mouse","41,80");
	}

	@Quando("^sai da venda sem finaliza-la$")
	public void sai_da_venda_sem_finaliza_la() throws Throwable {
		cadastro.sairPedido();
	}


	@Quando("^pesquisa pela venda efetuada$")
	public void pesquisa_pela_venda_efetuada() throws Throwable {
		cadastro.abrirRotina("vendas");
		relVendas.pesquisarVenda();
	}

	@Quando("^cancela a venda$")
	public void cancela_a_venda() throws Throwable {
		relVendas.cancelarVendaRelatorio();
		relVendas.sairRelatorioVenda();
	}

	@Então("^a venda é cancelada com sucesso$")
	public void a_venda_é_cancelada_com_sucesso() throws Throwable {

	}
}
