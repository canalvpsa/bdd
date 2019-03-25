package classesAuxiliaresProdutos;

public class ProdutoBase {

	private int id;
	private String codigoSistema;
	private String nome;

	public ProdutoBase(int id, String codigoSistema, String nome) {
		this.id = id;
		this.codigoSistema = codigoSistema;
		this.nome = nome;
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

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	@Override
	public String toString() {

		return "produtoBase [id=" + codigoSistema + ", codigoSistema=" + codigoSistema + ", nome=" + nome + "]";
	}
}
