package orcamento;

public class OrcamentoCenariosPositivosProduto {/*

	private InserirItem_BrincoEtnico inserirItemBrinco = new InserirItem_BrincoEtnico();
	private ValidaBrincoListagemProdutos validaBrinco = new ValidaBrincoListagemProdutos();
	
	private ValidaTotal validaTotal = new ValidaTotal();
	private OrcamentoSalvar orcamentoSalvar = new OrcamentoSalvar();
	private VendedorCliente vendedorCliente = new VendedorCliente();
	private AbrirFecharRotinas cadastro = new AbrirFecharRotinas();
	private String rotina, qtde1, tipoEntrada, valorTotal;

	@Rule
	public ScreenshotRule screenshotRule = new ScreenshotRule();
	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);

	@Test
	public void CadastroOrcamentoInsercaoRapida1item() {

		Settings.ActionLogs = false;

		qtde1 = "1";
		rotina = "orcamento";
		tipoEntrada = "teclado";
		valorTotal = "41,80";

		try {
			System.out.println("\n\n---- Inicio CadastroOrcamentoInsercaoRapida1item ----");
			//login.verificaPDVfechado();
			cadastro.abrirRotina(rotina);
		//	vendedorCliente.vincularCliente();

			inserirItemBrinco.informarCodigo(qtde1);
			validaBrinco.itensInseridos(qtde1);
			
			validaTotal.validaTotalAcumulado(valorTotal);
			orcamentoSalvar.salva(tipoEntrada);

		} catch (Exception e) {
			fail("Erro no CadastroOrcamentoInsercaoRapida1item");
		}
	}*/
}