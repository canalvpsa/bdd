package classesAuxiliaresPedidoCompra;

import java.util.List;

import classesAuxiliaresGeral.Produtos;

public class PedidoCompra {

	private String dataCompra;
	private String dataLimiteEntrega;
	private int idEntidade;
	private int idFornecedor;
	private float valorSeguro;
	private float valorOutros;
	private float valorFrete;
	private List<Parcelas> parcelas;
	private int tipoPlanoPagamento;
	private List<Produtos> produtos;


	public PedidoCompra(String dataAtual, String dataEntrega){
		this.dataCompra = dataAtual;
		this.dataLimiteEntrega = dataEntrega;
		this.idEntidade = 10; //Santa Catarina
		this.idFornecedor = 22; //CHARME AVIAMENTOS
		this.tipoPlanoPagamento = 2;
	}


	public String getDataCompra() {

		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {

		this.dataCompra = dataCompra;
	}

	public String getDataLimiteEntrega() {

		return dataLimiteEntrega;
	}

	public void setDataLimiteEntrega(String dataLimiteEntrega) {

		this.dataLimiteEntrega = dataLimiteEntrega;
	}

	public int getIdEntidade() {

		return idEntidade;
	}

	public void setIdEntidade(int i) {

		this.idEntidade = i;
	}

	public int getIdFornecedor() {

		return idFornecedor;
	}

	public void setIdFornecedor(int i) {

		this.idFornecedor = i;
	}


	public List<Parcelas> getParcelas() {

		return parcelas;
	}

	public void setParcelas(List<Parcelas> parcelas) {

		this.parcelas = parcelas;
	}

	public int getTipoPlanoPagamento() {

		return tipoPlanoPagamento;
	}

	public void setTipoPlanoPagamento(int i) {

		this.tipoPlanoPagamento = i;
	}

	public List<Produtos> getProdutos() {

		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {

		this.produtos = produtos;
	}


	public float getValorSeguro() {

		return valorSeguro;
	}


	public void setValorSeguro(float valorSeguro) {

		this.valorSeguro = valorSeguro;
	}


	public float getValorOutros() {

		return valorOutros;
	}


	public void setValorOutros(float valorOutros) {

		this.valorOutros = valorOutros;
	}


	public float getValorFrete() {

		return valorFrete;
	}


	public void setValorFrete(float valorFrete) {

		this.valorFrete = valorFrete;
	}
}
