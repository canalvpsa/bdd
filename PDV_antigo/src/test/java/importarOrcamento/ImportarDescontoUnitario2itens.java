package importarOrcamento;

public class ImportarDescontoUnitario2itens {/*

	OrcamentoDescontoTotal2itensOrcamento orcamentoDescontoTotal = new OrcamentoDescontoTotal2itensOrcamento();
	private ImportarOrcamento importarOrcamento = new ImportarOrcamento();
	private ValidaBrincoListagemProdutos validaBrinco = new ValidaBrincoListagemProdutos();

	private ValidaCalcaListagemProdutos validaCalca = new ValidaCalcaListagemProdutos();
	private ValidaTotal validaTotal = new ValidaTotal();
	private PedidoPagamento pedidoPagamento = new PedidoPagamento();
	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();
	private Screen s = new Screen();
	@SuppressWarnings("unused")
	private String rotina, valorTotal, codigoProduto1, codigoProduto2, qtde1 = "1",  qtde2 = "2", formaPagamento, tipoEntrada, valorPago;

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	@Test
	public void descontoTotalReais() {

		Settings.ActionLogs = false;

		rotina = "pedido";
		tipoEntrada = "teclado";

		valorTotal = "314,24";

		try {
			orcamentoDescontoTotal.descontoTotalReais();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoTotalReais ----");

		//	login.verificaTerminalFechado();
			cadastro.abrirRotina(rotina);
			s.wait(4.0);
			importarOrcamento.importaOrcamento(valorTotal, tipoEntrada);
			s.wait(3.0);
			validaBrinco.itensInseridos(qtde2);
			validaCalca.itensInseridos(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);

		} catch (FindFailed | InterruptedException e) {
			fail("erro no itemCancelado");
		}
	}
	
	@Test
	public void descontoTotalPercentual() {

		Settings.ActionLogs = false;

		rotina = "pedido";
		tipoEntrada = "teclado";

		valorTotal = "314,24";

		try {
			orcamentoDescontoTotal.descontoTotalPercentual();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoTotalReais ----");

		//	login.verificaTerminalFechado();
			cadastro.abrirRotina(rotina);
			s.wait(4.0);
			importarOrcamento.importaOrcamento(valorTotal, tipoEntrada);
			s.wait(3.0);
			validaBrinco.itensInseridos(qtde2);
			validaCalca.itensInseridos(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);

		} catch (FindFailed | InterruptedException e) {
			fail("erro no itemCancelado");
		}
	}*/
}
