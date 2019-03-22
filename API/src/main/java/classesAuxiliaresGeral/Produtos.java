package classesAuxiliaresGeral;

public class Produtos {
	private Produto produto;
	private int quantidade;
	private float valorDesconto;
	private double valorUnitario;
	private long idOperacao;

	public int getQuantidade() {

		return quantidade;
	}

	public void setQuantidade(int quantidade) {

		this.quantidade = quantidade;
	}

	public float getValorDesconto() {

		return valorDesconto;
	}

	public void setValorDesconto(float valorDesconto) {

		this.valorDesconto = valorDesconto;
	}

	public double getValorUnitario() {

		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {

		this.valorUnitario = valorUnitario;
	}

	public long getIdOperacao() {

		return idOperacao;
	}

	public void setIdOperacao(long idOperacao) {

		this.idOperacao = idOperacao;
	}

	public Produto getProduto() {

		return produto;
	}

	public void setProduto(Produto produto) {

		this.produto = produto;
	}
}
