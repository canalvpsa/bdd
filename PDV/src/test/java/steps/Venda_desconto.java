package steps;

import classesAuxiliares.RealizarDesconto;
import cucumber.api.java.pt.Quando;

public class Venda_desconto {
	private static Venda_desconto instancia = new Venda_desconto();
	private RealizarDesconto desconto = RealizarDesconto.getInstance();

	public static Venda_desconto getInstance(){
		if (instancia ==null){
			instancia = new Venda_desconto();
		}
		return instancia;
	}

	@Quando("^informa desconto no total de R\\$ \"([^\"]*)\"$")
	public void informa_desconto_no_total_de_R$(String descontoInformado) throws Throwable {
		desconto.descontoTotalEmReais(true, descontoInformado);
	}

	@Quando("^informa desconto no total de \"([^\"]*)\" %$")
	public void informa_desconto_no_total_de(String descontoInformado) throws Throwable {
		desconto.descontoTotalEmPercentual(true, descontoInformado);
	}
}
