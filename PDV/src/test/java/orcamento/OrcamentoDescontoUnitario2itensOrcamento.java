package orcamento;

public class OrcamentoDescontoUnitario2itensOrcamento {/*

	private InserirItem_BrincoEtnico inserirItemBrinco = new InserirItem_BrincoEtnico();
	private ValidaBrincoListagemProdutos validaBrinco = new ValidaBrincoListagemProdutos();

	private InserirItem_CalcaJeans inserirItemCalcaJeans = new InserirItem_CalcaJeans();
	private ValidaCalcaListagemProdutos validaCalca = new ValidaCalcaListagemProdutos();

	private OrcamentoImprimir orcamentoImprimir = new OrcamentoImprimir();
	private VendedorCliente vendedorCliente = new VendedorCliente();	
	private ValidaTotal validaTotal = new ValidaTotal();
	private String rotina, valorTotal, qtde1 = "1", qtde2 = "2", tipoEntrada;
	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);



	@Test
	public void descontoUnitarioReais() {

		Settings.ActionLogs = false;
		rotina = "orcamento";
		tipoEntrada = "mouse";
		valorTotal = "314,24";

		try {
			System.out.println("\n\n---- Inicio descontoUnitarioReais ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemCalcaJeans.informarCodigo(qtde1);
			validaCalca.itensInseridos(qtde1);

			inserirItemBrinco.informarCodigoDescontoEmReais(rotina, qtde2, "8,36");
			validaBrinco.itensInseridosComDesconto(qtde2);

			validaTotal.validaTotalAcumulado(valorTotal);
			orcamentoImprimir.imprime(tipoEntrada);

		} catch (Exception e) {
			fail("erro no descontoUnitarioReais");
		}
	}

	@Test
	public void descontoUnitarioPercentual() {

		rotina = "orcamento";
		tipoEntrada = "mouse";
		valorTotal = "314,24";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaDescontoUnitarioReais ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemCalcaJeans.informarCodigo(qtde1);
			validaCalca.itensInseridos(qtde1);

			inserirItemBrinco.informarCodigoDescontoEmPercentual(rotina, qtde2);
			validaBrinco.itensInseridosComDesconto(qtde2);

			validaTotal.validaTotalAcumulado(valorTotal);
			orcamentoImprimir.imprime(tipoEntrada);

		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaDescontoUnitarioReais");
		}
	}*/
}