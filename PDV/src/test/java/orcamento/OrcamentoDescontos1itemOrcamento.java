package orcamento;

public class OrcamentoDescontos1itemOrcamento {/*

	private InserirItem_BrincoEtnico inserirItemBrinco = new InserirItem_BrincoEtnico();
	private ValidaBrincoListagemProdutos validaBrinco = new ValidaBrincoListagemProdutos();

	private OrcamentoImprimir orcamentoImprimir = new OrcamentoImprimir();
	private VendedorCliente vendedorCliente = new VendedorCliente();
	private ValidaTotal validaTotal = new ValidaTotal();
	private RealizarDesconto desconto = new RealizarDesconto();
	private String rotina, valorTotal, qtde1 = "1", tipoEntrada;
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
		valorTotal = "37,62";

		try {
			System.out.println("\n\n---- Inicio descontoUnitarioReais ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemBrinco.informarCodigoDescontoEmReais(rotina, qtde1, "4,18");
			validaBrinco.itensInseridosComDesconto(qtde1);

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
		valorTotal = "37,62";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaDescontoUnitarioReais ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemBrinco.informarCodigoDescontoEmPercentual(rotina, qtde1);
			validaBrinco.itensInseridosComDesconto(qtde1);

			validaTotal.validaTotalAcumulado(valorTotal);
			orcamentoImprimir.imprime(tipoEntrada);

		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaDescontoUnitarioReais");
		}
	}

	@Test
	public void descontoTotalReais() {

		rotina = "orcamento";
		tipoEntrada = "mouse";
		valorTotal = "37,62";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaDescontoTotalReais ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemBrinco.informarCodigo(qtde1);
			validaBrinco.itensInseridos(qtde1);
			desconto.descontoPermitidoTotalEmReais("4,18");
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
		valorTotal = "37,62";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaDescontoTotalPercentual ----");

			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
	//		vendedorCliente.vincularCliente();

			inserirItemBrinco.informarCodigo(qtde1);
			validaBrinco.itensInseridos(qtde1);
			desconto.descontoPermitidoTotalEmPercentual("10,00");
			validaTotal.validaTotalAcumulado(valorTotal);
			orcamentoImprimir.imprime(tipoEntrada);

		} catch (Exception e) {
			fail("Erro no CadastroPedidoVendaDescontoTotalPercentual");
		}
	}*/
}