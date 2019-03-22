package classesAuxiliaresGeral;

public class Produto {
	private int id;
	private String codigoSistema;

	public Produto(int id, String codigoSistema){
		this.id = id;
		this.codigoSistema = codigoSistema;
	}


	public int getId() {

		return id;
	}


	public void setId(int id) {

		this.id = id;
	}


	public String getCodigoSistema() {

		return codigoSistema;
	}


	public void setCodigoSistema(String codigoSistema) {

		this.codigoSistema = codigoSistema;
	}
}
