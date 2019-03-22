package steps;

public class VendaServicoNaoEnviaImpressora {/*

	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();
	
	private InserirItem_ServicoFolheacao inserirItemFolheacao = new InserirItem_ServicoFolheacao();
	private ValidaFolheacaoListagemProdutos validaFolheadao = new ValidaFolheacaoListagemProdutos();
	
	
	private ValidaTotal validarTotal = new ValidaTotal();
	private PedidoPagamento pedidoPagamento = new PedidoPagamento();
	private String rotina, qtde1 = "1", tipoEntrada, valorTotal;

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();

	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	public void verificaLogin() {

		Settings.ActionLogs = false;
	//	login.verificaPDVfechado();
	//	login.verificaReducaoZPendente();
	}

	
	@Test
	public void vendaServico() {
		rotina = "pedido";
		tipoEntrada = "mouse";
		valorTotal = "50,00";

		try {
			System.out.println("\n\n---- Inicio CadastroPedidoVendaServico ----");
			this.verificaLogin();
			cadastro.abrirRotina(rotina);

			
			inserirItemFolheacao.informarCodigo(qtde1);
			validaFolheadao.itensInseridos(qtde1);

			validarTotal.validaTotalAcumulado(valorTotal);
			pedidoPagamento.realizarPagamentoDinheiro(tipoEntrada, valorTotal);
			cadastro.finalizarPedido(false);
		} catch (Exception e) {
			fail("erro no CadastroPedidoVendaServico");
		}
	}*/
}
