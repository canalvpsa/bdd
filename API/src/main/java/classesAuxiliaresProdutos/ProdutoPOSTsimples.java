package classesAuxiliaresProdutos;

import java.util.List;

public class ProdutoPOSTsimples {

	private String descricao;
	private String metodoControle;
	private String codigoNcm;
	private int origem;
	private String codigoInterno;
	private String unidade;
	private String classificacao;
	private float custoReferencial;
	private List<PrecoCusto> listPrecoCusto;
	private float preco;
	private float descontoMaximo;
	private float comissao;
	private int estoqueMinimo;
	private int estoqueMaximo;
	private boolean ativo;
	private List<Categoria> categorias;

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

	public List<PrecoCusto> getListPrecoCusto() {

		return listPrecoCusto;
	}

	public void setListPrecoCusto(List<PrecoCusto> listPrecoCusto) {

		this.listPrecoCusto = listPrecoCusto;
	}

	public float getPreco() {

		return preco;
	}

	public void setPreco(float preco) {

		this.preco = preco;
	}

	public float getDescontoMaximo() {

		return descontoMaximo;
	}

	public void setDescontoMaximo(float descontoMaximo) {

		this.descontoMaximo = descontoMaximo;
	}

	public float getComissao() {

		return comissao;
	}

	public void setComissao(float comissao) {

		this.comissao = comissao;
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

	public List<Categoria> getCategorias() {

		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {

		this.categorias = categorias;
	}

	public String getCodigoNcm() {

		return codigoNcm;
	}

	public void setCodigoNcm(String codigoNcm) {

		this.codigoNcm = codigoNcm;
	}

	public boolean isAtivo() {

		return ativo;
	}

	public void setAtivo(boolean ativo) {

		this.ativo = ativo;
	}

	public String getDescricao() {

		return descricao;
	}

	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	public String getCodigoInterno() {

		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {

		this.codigoInterno = codigoInterno;
	}
}