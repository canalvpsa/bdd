package classesAuxiliaresProdutos;

import java.util.List;

public class ProdutoPUT {

	private String codigoSistema;
	private String codigoInterno;
	private String descricao;
	private String metodoControle;
	private String codigoNcm;
	private int origem;
	private String unidade;
	private String classificacao;
	private float custoReferencial;
	private float preco;
	private int estoqueMinimo;
	private int estoqueMaximo;
	private List<CustoReferencial> listCustoReferencial;
	private List<Categoria> categorias;
	private List<UnidadesProporcao> unidadesProporcao;

	public String getMetodoControle() {

		return metodoControle;
	}

	public void setMetodoControle(String metodoControle) {

		this.metodoControle = metodoControle;
	}

	public int getOrigem() {

		return origem;
	}

	public void setOrigem(int origem) {

		this.origem = origem;
	}

	public String getUnidade() {

		return unidade;
	}

	public void setUnidade(String unidade) {

		this.unidade = unidade;
	}

	public String getClassificacao() {

		return classificacao;
	}

	public void setClassificacao(String classificacao) {

		this.classificacao = classificacao;
	}

	public float getCustoReferencial() {

		return custoReferencial;
	}

	public void setCustoReferencial(float custoReferencial) {

		this.custoReferencial = custoReferencial;
	}

	public String getCodigoNcm() {

		return codigoNcm;
	}

	public void setCodigoNcm(String codigoNcm) {

		this.codigoNcm = codigoNcm;
	}

	public String getDescricao() {

		return descricao;
	}

	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	public List<CustoReferencial> getListCustoReferencial() {

		return listCustoReferencial;
	}

	public void setListCustoReferencial(List<CustoReferencial> listCustoReferencial) {

		this.listCustoReferencial = listCustoReferencial;
	}

	public List<Categoria> getCategorias() {

		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {

		this.categorias = categorias;
	}

	public float getPreco() {

		return preco;
	}

	public void setPreco(float preco) {

		this.preco = preco;
	}

	public String getCodigoSistema() {

		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {

		this.codigoSistema = codigoSistema;
	}

	public int getEstoqueMinimo() {

		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {

		this.estoqueMinimo = estoqueMinimo;
	}

	public int getEstoqueMaximo() {

		return estoqueMaximo;
	}

	public void setEstoqueMaximo(int estoqueMaximo) {

		this.estoqueMaximo = estoqueMaximo;
	}

	public String getCodigoInterno() {

		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {

		this.codigoInterno = codigoInterno;
	}

	public List<UnidadesProporcao> getUnidadesProporcao() {

		return unidadesProporcao;
	}

	public void setUnidadesProporcao(List<UnidadesProporcao> unidadesProporcao) {

		this.unidadesProporcao = unidadesProporcao;
	}
}