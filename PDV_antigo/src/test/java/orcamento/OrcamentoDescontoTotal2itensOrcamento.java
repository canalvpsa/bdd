package orcamento;

public class OrcamentoDescontoTotal2itensOrcamento {/*

	private InserirItem_BrincoEtnico inserirItemBrinco = new InserirItem_BrincoEtnico();
	private ValidaBrincoListagemProdutos validaBrinco = new ValidaBrincoListagemProdutos();

	private InserirItem_CalcaJeans inserirItemCalcaJeans = new InserirItem_CalcaJeans();
	private ValidaCalcaListagemProdutos validaCalca = new ValidaCalcaListagemProdutos();
	
	private OrcamentoImprimir orcamentoImprimir = new OrcamentoImprimir();
	private VendedorCliente vendedorCliente = new VendedorCliente();
	private ValidaTotal validaTotal = new ValidaTotal();
	private RealizarDesconto desconto = new RealizarDesconto();
	private String rotina, valorTotal, qtde1 = "1", qtde2 = "2", tipoEntrada;
	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);


	@Test
	public void descontoTotalReais() {

		rotina = "orcamento";
		tipoEntrada = "mouse";
		valorTotal = "314,24";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaDescontoTotalReais ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemCalcaJeans.informarCodigo(qtde1);
			validaCalca.itensInseridos(qtde1);
			
			inserirItemBrinco.informarCodigo(qtde2);
			validaBrinco.itensInseridos(qtde2);
			
			desconto.descontoPermitidoTotalEmReais("8,36");
			validaTotal.validaTotalAcumulado(valorTotal);
			orcamentoImprimir.imprime(tipoEntrada);

		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaDescontoTotalReais");
		}
	}

	@Test
	public void descontoTotalPercentual() {

		rotina = "orcamento";
		tipoEntrada = "mouse";
		valorTotal = "314,24";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaDescontoTotalPercentual ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemCalcaJeans.informarCodigo(qtde1);
			validaCalca.itensInseridos(qtde1);
			
			inserirItemBrinco.informarCodigo(qtde2);
			validaBrinco.itensInseridos(qtde2);
			
			desconto.descontoPermitidoTotalEmPercentual("2,59");
			validaTotal.validaTotalAcumulado(valorTotal);
			orcamentoImprimir.imprime(tipoEntrada);

		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaDescontoTotalPercentual");
		}
	}*/
}