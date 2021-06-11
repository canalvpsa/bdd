package importarOrcamento;

public class ImportarDescontoTotal2itens {/*

	OrcamentoDescontoUnitario2itensOrcamento orcamentoDescontoUnitario = new OrcamentoDescontoUnitario2itensOrcamento();
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
	public void descontoUnitarioReais() {

		Settings.ActionLogs = false;
		rotina = "pedido";
		tipoEntrada = "teclado";

		valorTotal = "314,24";

		try {
			orcamentoDescontoUnitario.descontoUnitarioReais();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoUnitarioReais ----");

			//login.verificaTerminalFechado();
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
			fail("erro no descontoUnitarioReais");
		}
	}
	
	@Test
	public void descontoUnitarioPercentual() {

		Settings.ActionLogs = false;

		rotina = "pedido";
		tipoEntrada = "teclado";

		valorTotal = "314,24";

		try {
			orcamentoDescontoUnitario.descontoUnitarioPercentual();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio descontoUnitarioPercentual ----");

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
			fail("erro no descontoUnitarioPercentual");
		}
	}*/
}
