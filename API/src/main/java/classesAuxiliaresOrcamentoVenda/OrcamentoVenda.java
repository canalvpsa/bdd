package classesAuxiliaresOrcamentoVenda;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import classesAuxiliaresGeral.Itens;
import classesAuxiliaresGeral.Servico;


public class OrcamentoVenda {

	private int validade;
	private int idEntidade;
	private int idTerceiro;
	private int idRepresentante;
	private List<Itens> itens;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Servico> servicos;

	private float valorFrete;
	private float valorOutros;
	private float valorSeguro;
	private int idPlanoPagamento;
	private Boolean vendaConsumidorFinal;
	private Boolean reservarEstoque;


	public OrcamentoVenda(){
		this.validade = 5;
		this.idEntidade = 10; // santa catarina
		this.idTerceiro = 42; //ABNER
		this.idRepresentante = 1;
		this.valorFrete = 0;
		this.valorOutros = 0;
		this.valorSeguro = 0;
		this.idPlanoPagamento = 1;
		this.vendaConsumidorFinal = true;
	}

	public int getValidade() {

		return validade;
	}

	public void setValidade(int validade) {

		this.validade = validade;
	}

	public int getIdEntidade() {

		return idEntidade;
	}

	public void setIdEntidade(int idEntidade) {

		this.idEntidade = idEntidade;
	}

	public int getIdTerceiro() {

		return idTerceiro;
	}

	public void setIdTerceiro(int idTerceiro) {

		this.idTerceiro = idTerceiro;
	}

	public int getIdRepresentante() {

		return idRepresentante;
	}

	public void setIdRepresentante(int idRepresentante) {

		this.idRepresentante = idRepresentante;
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

	public int getIdPlanoPagamento() {

		return idPlanoPagamento;
	}

	public void setIdPlanoPagamento(int idPlanoPagamento) {

		this.idPlanoPagamento = idPlanoPagamento;
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

	public List<Servico> getServicos() {

		return servicos;
	}

	public void setServicos(List<Servico> servicos) {

		this.servicos = servicos;
	}

	public Boolean getReservarEstoque() {

		return reservarEstoque;
	}

	public void setReservarEstoque(Boolean reservarEstoque) {

		this.reservarEstoque = reservarEstoque;
	}
}