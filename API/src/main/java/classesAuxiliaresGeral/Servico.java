package classesAuxiliaresGeral;

public class Servico {
	private int idServico;
	private int quantidade;
	private double valorUnitario;
	
	public Servico(int idServico, int quantidade, double valorUnitario){
		this.idServico = idServico;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public int getIdServico() {

		return idServico;
	}

	public void setIdServico(int idServico) {

		this.idServico = idServico;
	}

	public int getQuantidade() {

		return quantidade;
	}

	public void setQuantidade(int quantidade) {

		this.quantidade = quantidade;
	}

	public double getValorUnitario() {

		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {

		this.valorUnitario = valorUnitario;
	}
}
