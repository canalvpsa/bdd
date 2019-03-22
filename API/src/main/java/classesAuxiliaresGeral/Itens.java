package classesAuxiliaresGeral;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Itens {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Produto produto;
	private int idProduto;
	private int quantidade;
	private Double valorUnitario;
	private Double valorDesconto;
	

	public void orcamentoItem1(){
		this.produto = new Produto(660, "BACF02.0003");
		this.quantidade = 2;
		this.valorUnitario = 9.99;
	}

	public void orcamentoItem2(){
		this.produto = new Produto(2876, "BR00014");
		this.quantidade = 3;
		this.valorUnitario = 15.30;
	}
	
	public void pedidoVendaItem1(){
		this.idProduto = 660;
		this.quantidade = 2;
		this.valorUnitario = 9.99;
		this.valorDesconto = 0.0;
	}

	public void pedidoVendaItem2(){
		this.idProduto = 2876;
		this.quantidade = 3;
		this.valorUnitario = 15.30;
		this.valorDesconto = 0.0;
	}

	public Produto getProduto() {

		return produto;
	}

	public void setProduto(Produto produto) {

		this.produto = produto;
	}

	public int getQuantidade() {

		return quantidade;
	}

	public void setQuantidade(int quantidade) {

		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {

		return valorUnitario;
	}


	public void setValorUnitario(Double valorUnitario) {

		this.valorUnitario = valorUnitario;
	}

	public Double getValorDesconto() {

		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {

		this.valorDesconto = valorDesconto;
	}

	public int getIdProduto() {

		return idProduto;
	}

	public void setIdProduto(int idProduto) {

		this.idProduto = idProduto;
	}
}
