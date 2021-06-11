package steps;

import classesAuxiliares.InserirItem_BrincoEtnico;
import classesAuxiliares.ValidaBrincoListagemProdutos;
import cucumber.api.java.pt.Quando;

public class Venda_troca {
	private static Venda_troca instancia = new Venda_troca();
	private InserirItem_BrincoEtnico inserirItemBrinco = InserirItem_BrincoEtnico.getInstance();
	private ValidaBrincoListagemProdutos validaBrinco = ValidaBrincoListagemProdutos.getInstance();

	public static Venda_troca getInstance(){
		if (instancia ==null){
			instancia = new Venda_troca();
		}
		return instancia;
	}
	
	@Quando("^adiciona o item \"([^\"]*)\" para troca com quantidade \"([^\"]*)\" e valor unitário de R\\$ \"([^\"]*)\"$")
	public void adiciona_o_item_para_troca_com_quantidade_e_valor_unitário_de_R$(String produto, String qtde, String valor) throws Throwable {
	
		inserirItemBrinco.inserir1ItemTroca();
		validaBrinco.itemTrocado(qtde);
	}
}
