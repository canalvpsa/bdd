package classesAuxiliaresTerceiros;

import java.util.List;

public class Terceiro {

	private String nome;
	private String documento;
	private List<Endereco> enderecos;
	private List<Telefone> telefones;
	private List<String> classes;

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public String getDocumento() {

		return documento;
	}

	public void setDocumento(String documento) {

		this.documento = documento;
	}

	public List<Endereco> getEnderecos() {

		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {

		this.enderecos = enderecos;
	}

	public List<Telefone> getTelefones() {

		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {

		this.telefones = telefones;
	}

	public List<String> getClasses() {

		return classes;
	}

	public void setClasses(List<String> classes) {

		this.classes = classes;
	}
}