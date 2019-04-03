package steps;

import static org.junit.Assert.assertFalse;

import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import DAO.impl.Configuracao;
import classesAuxiliares.BotaoOK;
import classesAuxiliares.CancelarItem;
import classesAuxiliares.InserirItem_BrincoEtnico;
import classesAuxiliares.InserirItem_CalcaJeans;
import classesAuxiliares.InserirItem_Regata_SemSaldo;
import classesAuxiliares.InserirItem_ServicoFolheacao;
import classesAuxiliares.ValidaBrincoListagemProdutos;
import classesAuxiliares.ValidaCalcaListagemProdutos;
import classesAuxiliares.ValidaFolheacaoListagemProdutos;
import classesAuxiliares.ValidaRegataListagemProdutos;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class Venda_itens {
	private static Venda_itens instancia = Venda_itens.getInstance();
	private static  BotaoOK botaoOK = BotaoOK.getInstance();
	private Screen s = new Screen();
	private String imageString;

	private Configuracao BDconfiguracao = new Configuracao();

	private InserirItem_BrincoEtnico inserirItemBrinco = InserirItem_BrincoEtnico.getInstance();
	private ValidaBrincoListagemProdutos validaBrinco = ValidaBrincoListagemProdutos.getInstance();

	private InserirItem_CalcaJeans inserirItemCalcaJeans = InserirItem_CalcaJeans.getInstance();
	private ValidaCalcaListagemProdutos validaCalca = ValidaCalcaListagemProdutos.getInstance();

	private InserirItem_ServicoFolheacao inserirItemServico = InserirItem_ServicoFolheacao.getInstance();
	private ValidaFolheacaoListagemProdutos validaServico = ValidaFolheacaoListagemProdutos.getInstance();

	private InserirItem_Regata_SemSaldo inserirRegata = InserirItem_Regata_SemSaldo.getInstance();
	private ValidaRegataListagemProdutos validaRegata = ValidaRegataListagemProdutos.getInstance();
	private CancelarItem cancelarItem = CancelarItem.getInstance();
	private Pattern m_produtoSemSaldo_msg1 = new Pattern(getImage("imgOrcamentoPedido/produtoSemSaldo_msg1.png")).similar(0.98f);
	private Pattern m_produtoSemSaldo_msg2 = new Pattern(getImage("imgOrcamentoPedido/produtoSemSaldo_msg2.png")).similar(0.98f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static Venda_itens getInstance(){
		if (instancia ==null){
			instancia = new Venda_itens();
		}
		return instancia;
	}

	@Quando("^adiciona o item \"([^\"]*)\" com quantidade \"([^\"]*)\" com valor unitário de R\\$ \"([^\"]*)\"$")
	public void adiciona_o_item_com_quantidade_com_valor_unitário_de_R$(String produtoInformado, String qtde, String valorItemInformado) throws Throwable {

		switch(produtoInformado){

		case "brinco":
			inserirItemBrinco.informarCodigo(qtde);
			validaBrinco.itensInseridos(qtde, false);
			break;

		case "calca":
			inserirItemCalcaJeans.informarCodigo(qtde);
			validaCalca.itensInseridos(qtde);
			break;

		case "regata":
			String permiteSaldoNegativo = BDconfiguracao.getConfiguracao("PDV_PERMITE_ESTOQUE_NEGATIVO");
			inserirRegata.informarCodigo(qtde, permiteSaldoNegativo);
			validaRegata.itensInseridos(qtde, permiteSaldoNegativo);
			break;

		case "servico":
			inserirItemServico.informarCodigo(qtde);
			validaServico.itensInseridos(qtde);
		}
	}

	@Então("^será exibida mensagem de saldo insuficiente$")
	public void será_exibida_mensagem_de_saldo_insuficiente() throws Throwable {

		if(BDconfiguracao.getConfiguracao("PDV_PERMITE_ESTOQUE_NEGATIVO").equals("N")){
			s.wait(2.0);
			if ((s.exists(m_produtoSemSaldo_msg1) != null) || (s.exists(m_produtoSemSaldo_msg2) != null)){
				System.out.println("Encontrou imagem");
				s.type(Key.ENTER);
				s.type(Key.ESC);
			}else{
				assertFalse("NOK - Mensagem de saldo insuficiente nao exibida", true);
			}
		}
	}

	// Desconto percentual
	@Quando("^adiciona o item \"([^\"]*)\" com quantidade \"([^\"]*)\" com valor unitário de R\\$ \"([^\"]*)\" e desconto unitário de \"([^\"]*)\" %$")
	public void adiciona_o_item_com_quantidade_com_valor_unitário_de_R$_e_desconto_unitário_de(String produtoInformado, String qtde, String valorItemInformado, String descontoUnitario) throws Throwable {
		switch(produtoInformado){

		case "brinco":
			inserirItemBrinco.informarCodigoDescontoEmPercentual(qtde,descontoUnitario);
			validaBrinco.itensInseridos(qtde, true);
			break;

		case "calca":

			break;

		case "regata":
			break;

		case "servico":
			break;
		}	
	}


	//Desconto em reais
	@Quando("^adiciona o item \"([^\"]*)\" com quantidade \"([^\"]*)\" com valor unitário de R\\$ \"([^\"]*)\" e desconto unitário de R\\$ \"([^\"]*)\"$")
	public void adiciona_o_item_com_quantidade_com_valor_unitário_de_R$_e_desconto_unitário_de_R$(String produtoInformado, String qtde, String valorItemInformado, String descontoUnitario) throws Throwable {
		switch(produtoInformado){

		case "brinco":
			inserirItemBrinco.informarCodigoDescontoEmReais(qtde, descontoUnitario);
			validaBrinco.itensInseridos(qtde, true);
			break;

		case "calca":

			break;

		case "regata":
			break;

		case "servico":
			break;
		}

	}

	@Então("^o item \"([^\"]*)\" é carregado na venda com quantidade \"([^\"]*)\"$")
	public void o_item_é_carregado_na_venda_com_quantidade(String produto, String qtde) throws Throwable {
		switch(produto){

		case "brinco":
			validaBrinco.itensInseridos(qtde,false);
			break;

		case "calca":
			validaCalca.itensInseridos(qtde);
			break;

		case "regata":
			validaRegata.itensInseridos(qtde, "Y");
			break;
		}
	}


	@Quando("^adiciona o item informando o nome completo do item \"([^\"]*)\" com quantidade \"([^\"]*)\" no valor de R\\$ \"([^\"]*)\"$")
	public void adiciona_o_item_informando_o_nome_completo_do_item_com_quantidade_no_valor_de_R$(String produto, String qtde, String valor) throws Throwable {
		inserirItemBrinco.informarNomeInteiro(qtde);
	}

	@Quando("^adiciona o item utilizando a pesquisa informando o item \"([^\"]*)\" com quantidade \"([^\"]*)\" no valor de R\\$ \"([^\"]*)\"$")
	public void adiciona_o_item_utilizando_a_pesquisa_informando_o_item_com_quantidade_no_valor_de_R$(String produto, String qtde, String valor) throws Throwable {
		inserirItemBrinco.informarNomeParcial(produto, qtde);
	}

	//Generico
	@Quando("^adiciona um item$")
	public void adiciona_um_item() throws Throwable {
		inserirItemBrinco.informarCodigo("1");
		validaBrinco.itensInseridos("1",false);
	}

	@Quando("^o item \"([^\"]*)\" é excluído da venda$")
	public void o_item_é_excluído_da_venda(String produto) throws Throwable {
		cancelarItem.cancelaItemVenda(produto);
	}
}