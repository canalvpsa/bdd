package classesAuxiliaresPedidoVenda;

import java.util.List;

import classesAuxiliaresGeral.Itens;

public class PedidoVenda {

	private String data;
	private String horario;
	private int idEntidade;
	private int idPlanoPagamento;
	private int idRepresentante;
	private int idTerceiro;
	private float valorFrete;
	private float valorOutros;
	private float valorSeguro;
	private Boolean vendaConsumidorFinal;
	private List<Itens> itens;
	
	public PedidoVenda(){
		this.idEntidade = 10; //SANTA CATARINA
		this.idPlanoPagamento = 1;
		this.idRepresentante = 1;
		this.idTerceiro = 42; //ABNER
		this.valorFrete = 0;
		this.valorOutros = 0;
		this.valorSeguro = 0;
		this.vendaConsumidorFinal = true;
	}

	public String getData() {

		return data;
	}

	public void setData(String data) {

		this.data = data;
	}

	public String getHorario() {

		return horario;
	}

	public void setHorario(String horario) {

		this.horario = horario;
	}

	public int getIdEntidade() {

		return idEntidade;
	}

	public void setIdEntidade(int idEntidade) {

		this.idEntidade = idEntidade;
	}

	public int getIdPlanoPagamento() {

		return idPlanoPagamento;
	}

	public void setIdPlanoPagamento(int idPlanoPagamento) {

		this.idPlanoPagamento = idPlanoPagamento;
	}

	public int getIdRepresentante() {

		return idRepresentante;
	}

	public void setIdRepresentante(int idRepresentante) {

		this.idRepresentante = idRepresentante;
	}

	public int getIdTerceiro() {

		return idTerceiro;
	}

	public void setIdTerceiro(int idTerceiro) {

		this.idTerceiro = idTerceiro;
	}

	public float getValorFrete() {

		return valorFrete;
	}

	public void setValorFrete(float valorFrete) {

		this.valorFrete = valorFrete;
	}

	public float getValorOutros() {

		return valorOutros;
	}

	public void setValorOutros(float valorOutros) {

		this.valorOutros = valorOutros;
	}

	public float getValorSeguro() {

		return valorSeguro;
	}

	public void setValorSeguro(float valorSeguro) {

		this.valorSeguro = valorSeguro;
	}

	public Boolean getVendaConsumidorFinal() {

		return vendaConsumidorFinal;
	}

	public void setVendaConsumidorFinal(Boolean vendaConsumidorFinal) {

		this.vendaConsumidorFinal = vendaConsumidorFinal;
	}

	public List<Itens> getItens() {

		return itens;
	}

	public void setItens(List<Itens> itens) {

		this.itens = itens;
	}
}
