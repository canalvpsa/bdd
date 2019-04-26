package classesAuxiliaresGeral;

public class PessoaFisica {

	private String nome, documento;
	private String tipoLogradouro, logradouro, numero, bairro, cep, tipoEndereco;
	private String telefoneDDD, telefoneNumero, classes;

	public PessoaFisica() {

		nome = "API pessoa fisica";
		documento = "023.168.132-00";
		tipoLogradouro = "RUA";
		logradouro = "AUBÉ";
		numero = "1021";
		bairro = "Centro";
		cep = "8920600";
		tipoEndereco = "ENDERECO_CORRESPONDENCIA";
		telefoneDDD = "47";
		telefoneNumero = "30000000";
		classes = "CLIENTE";
	}

	public String getNome() {

		return nome;
	}

	public String getDocumento() {

		return documento;
	}

	public String getTipoLogradouro() {

		return tipoLogradouro;
	}

	public String getLogradouro() {

		return logradouro;
	}

	public String getNumero() {

		return numero;
	}

	public String getBairro() {

		return bairro;
	}

	public String getCep() {

		return cep;
	}

	public String getTipoEndereco() {

		return tipoEndereco;
	}

	public String getTelefoneDDD() {

		return telefoneDDD;
	}

	public String getTelefoneNumero() {

		return telefoneNumero;
	}

	public String getClasses() {

		return classes;
	}
}