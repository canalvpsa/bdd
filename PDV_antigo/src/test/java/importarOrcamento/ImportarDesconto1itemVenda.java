package importarOrcamento;

public class ImportarDesconto1itemVenda {/*
	private Screen s = new Screen();
	OrcamentoDescontos1itemOrcamento orcamentoDesconto1item = new OrcamentoDescontos1itemOrcamento();
	private ImportarOrcamento importarOrcamento = new ImportarOrcamento();
	private ValidaBrincoListagemProdutos validaBrinco = new ValidaBrincoListagemProdutos();

	private ValidaTotal validaTotal = new ValidaTotal();
	private PedidoPagamento pedidoPagamento = new PedidoPagamento();
	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();
	@SuppressWarnings("unused")
	private String rotina, valorTotal, codigoProduto1, codigoProduto2, qtde1 = "1", formaPagamento, tipoEntrada, valorPago;


	@Test
	public void descontoUnitarioReais() {

		Settings.ActionLogs = false;

		rotina = "pedido";
		tipoEntrada = "teclado";
		valorTotal = "37,62";

		try {
			orcamentoDesconto1item.descontoUnitarioReais();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoUnitarioReais ----");

		//	login.verificaTerminalFechado();
			cadastro.abrirRotina(rotina);
			s.wait(4.0);
			importarOrcamento.importaOrcamento(valorTotal, tipoEntrada);
			s.wait(3.0);
			validaBrinco.itensInseridosComDesconto(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);

		} catch (FindFailed | InterruptedException e) {
			fail("erro no descontoUnitarioReais");
		}
	}


	@Test
	public void descontoUnitarioPercentual() {

		Settings.ActionLogs = false;

		rotina = "pedido";
		tipoEntrada = "teclado";
		valorTotal = "37,62";

		try {
			orcamentoDesconto1item.descontoUnitarioPercentual();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoUnitarioReais ----");

		//	login.verificaTerminalFechado();
			cadastro.abrirRotina(rotina);
			s.wait(4.0);
			importarOrcamento.importaOrcamento(valorTotal, tipoEntrada);
			s.wait(3.0);
			validaBrinco.itensInseridosComDesconto(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);

		} catch (FindFailed | InterruptedException e) {
			fail("erro no descontoUnitarioReais");
		}
	}

	@Test
	public void descontoTotalReais() {

		Settings.ActionLogs = false;

		rotina = "pedido";
		tipoEntrada = "teclado";
		valorTotal = "37,62";

		try {
			orcamentoDesconto1item.descontoTotalReais();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoTotalReais ----");

		//	login.verificaTerminalFechado();
			cadastro.abrirRotina(rotina);
			s.wait(4.0);
			importarOrcamento.importaOrcamento(valorTotal, tipoEntrada);
			s.wait(3.0);
			validaBrinco.itensInseridos(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);

		} catch (FindFailed | InterruptedException e) {
			fail("erro no descontoTotalReais");
		}
	}

	@Test
	public void descontoTotalPercentual() {

		Settings.ActionLogs = false;

		rotina = "pedido";
		tipoEntrada = "teclado";
		valorTotal = "37,62";

		try {
			orcamentoDesconto1item.descontoTotalPercentual();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoTotalPercentual ----");

	//		login.verificaTerminalFechado();
			cadastro.abrirRotina(rotina);
			s.wait(4.0);
			importarOrcamento.importaOrcamento(valorTotal, tipoEntrada);
			s.wait(3.0);
			validaBrinco.itensInseridos(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);

		} catch (FindFailed | InterruptedException e) {
			fail("erro no descontoTotalPercentual");
		}
	}
	*/
	
}
