package classesAuxiliaresProdutos;

import java.util.List;

public class Produto {
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
	private ProdutoBase produtoBase;
	private List<ValorAtributos> valorAtributos;
	private List<DadosPorEntidade> dadosPorEntidade;
	private List<UnidadesProporcao> unidadesProporcao;
	
	public Produto(){
		this.getDescricao();
		this.getMetodoControle();
		this.getCodigoNcm();
		
	}
	
	public String getDescricao() {
	
		return descricao;
	}
	
	public void setDescricao(String descricao) {
	
		this.descricao = descricao;
	}
	
	public String getMetodoControle() {
	
		return metodoControle;
	}
	
	public void setMetodoControle(String metodoControle) {
	
		this.metodoControle = metodoControle;
	}
	
	public String getCodigoNcm() {
	
		return codigoNcm;
	}
	
	public void setCodigoNcm(String codigoNcm) {
	
		this.codigoNcm = codigoNcm;
	}
	
	public int getOrigem() {
	
		return origem;
	}
	
	public void setOrigem(int origem) {
	
		this.origem = origem;
	}
	
	public String getCodigoInterno() {
	
		return codigoInterno;
	}
	
	public void setCodigoInterno(String codigoInterno) {
	
		this.codigoInterno = codigoInterno;
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
	
	public boolean isAtivo() {
	
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
	
		this.ativo = ativo;
	}
	
	public List<Categoria> getCategorias() {
	
		return categorias;
	}
	
	public void setCategorias(List<Categoria> categorias) {
	
		this.categorias = categorias;
	}
	
	public ProdutoBase getProdutoBase() {
	
		return produtoBase;
	}
	
	public void setProdutoBase(ProdutoBase produtoBase) {
	
		this.produtoBase = produtoBase;
	}
	
	public List<ValorAtributos> getValorAtributos() {
	
		return valorAtributos;
	}
	
	public void setValorAtributos(List<ValorAtributos> valorAtributos) {
	
		this.valorAtributos = valorAtributos;
	}
	
	public List<DadosPorEntidade> getDadosPorEntidade() {
	
		return dadosPorEntidade;
	}
	
	public void setDadosPorEntidade(List<DadosPorEntidade> dadosPorEntidade) {
	
		this.dadosPorEntidade = dadosPorEntidade;
	}

	public List<UnidadesProporcao> getUnidadesProporcao() {

		return unidadesProporcao;
	}

	public void setUnidadesProporcao(List<UnidadesProporcao> unidadesProporcao) {

		this.unidadesProporcao = unidadesProporcao;
	}

}
