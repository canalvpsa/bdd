package classesAuxiliaresPedidoCompra;

public class Parcelas {

	private String dataVencimento;
	private double valor;
	
	
	
	public Parcelas(String dataVencimento, Double valor){
		this.dataVencimento = dataVencimento;
		this.valor = valor;
	}

	public String getDataVencimento() {

		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {

		this.dataVencimento = dataVencimento;
	}

	public double getValor() {

		return valor;
	}

	public void setValor(double d) {

		this.valor = d;
	}
}
