package importarOrcamento;

public class ImportarItemCancelado {/*
	private Screen s = new Screen();
	private OrcamentoItemCancelado orcamentoItemCancelado = new OrcamentoItemCancelado();
	OrcamentoDescontos1itemOrcamento orcamentoDesconto1item = new OrcamentoDescontos1itemOrcamento();
	private ImportarOrcamento importarOrcamento = new ImportarOrcamento();
	private ValidaBrincoListagemProdutos validaBrinco = new ValidaBrincoListagemProdutos();

	private ValidaCalcaListagemProdutos validaCalca = new ValidaCalcaListagemProdutos();
	private ValidaTotal validaTotal = new ValidaTotal();
	private PedidoPagamento pedidoPagamento = new PedidoPagamento();
	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();
	@SuppressWarnings("unused")
	private String rotina, valorTotal, codigoProduto1, codigoProduto2, qtde1 = "1", formaPagamento, tipoEntrada, valorPago;

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	@Test
	public void itemCancelado() throws InterruptedException {

		Settings.ActionLogs = false;

		qtde1 = "1";
		rotina = "pedido";
		tipoEntrada = "teclado";

		valorTotal = "280,80";

		try {
			orcamentoItemCancelado.percentualComItemCancelado();
			cadastro.sairOrcamento();

			System.out.println("\n ---- Inicio itemCancelado ----");

		//	login.verificaTerminalFechado();
			cadastro.abrirRotina(rotina);
			s.wait(4.0);
			importarOrcamento.importaOrcamento(valorTotal, tipoEntrada);
			s.wait(3.0);
			validaBrinco.itensInseridos(qtde1);
			validaCalca.itensInseridos(qtde1);
			validaTotal.validaTotalAcumulado(valorTotal);

			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);

		} catch (FindFailed e) {
			fail("erro no itemCancelado");
		}
	}
*/
}
