package classesAuxiliaresServicos;

public class Servico {

	private String descricao;
	private String codigoInterno;
	private float descontoMaximo;
	private float comissaoVenda;
	private double tempoExecucaoMinimo;
	private TipoServicoJson tipoServicoJson;
	private TipoServicoMunicipioJson tipoServicoMunicipioJson;
	private boolean ativo;
	private String unidade;
	private String classificacao;
	private classesAuxiliaresServicos.Categoria categoria;
	private boolean precoVariavel;
	private float precoVenda;

	public Servico(){
		this.descricao = "API SERVICO";
		this.codigoInterno = "SV001";
		this.descontoMaximo = 0.0f;
		this.comissaoVenda = 0.0f;
		this.tempoExecucaoMinimo = 60;
		this.ativo = true;
		this.unidade = "UN";
		this.classificacao = "SERVICO_ISS";
		this.precoVariavel = false;
		this.precoVenda = 50f;

		TipoServicoJson tipoServicoJson1 = new TipoServicoJson();
		TipoServicoMunicipioJson tipoServicoMunicipioJson1 = new TipoServicoMunicipioJson();

		tipoServicoJson1.setId(129);
		tipoServicoJson1.setCodigo(14.01);
		tipoServicoJson1.setDescricao(
				"Lubrificação, limpeza, lustração, revisão, carga e recarga, conserto, restauração, blindagem, manutenção e conservação de máquinas, veículos, aparelhos, equipamentos, motores, elevadores ou de qualquer objeto (exceto peças e partes empregadas, que ficam sujeitas ao ICMS).s");

		tipoServicoMunicipioJson1.setId(363);
		tipoServicoMunicipioJson1.setCodigo(7390);
		tipoServicoMunicipioJson1.setDescricao("Lubrificação, lavagem e limpeza de veículos, inclusive automáticas, em postos de gasolina.");

		Categoria categoria1 = new Categoria();
		categoria1.setId(6);
		categoria1.setCodigoSistema(254);
		categoria1.setNome("SERVIÇO DE MANUTENÇÃO");

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

	public float getDescontoMaximo() {

		return descontoMaximo;
	}

	public void setDescontoMaximo(float descontoMaximo) {

		this.descontoMaximo = descontoMaximo;
	}

	public float getComissaoVenda() {

		return comissaoVenda;
	}

	public void setComissaoVenda(float comissaoVenda) {

		this.comissaoVenda = comissaoVenda;
	}

	public double getTempoExecucaoMinimo() {

		return tempoExecucaoMinimo;
	}

	public void setTempoExecucaoMinimo(double tempoExecucaoMinimo) {

		this.tempoExecucaoMinimo = tempoExecucaoMinimo;
	}

	public boolean isAtivo() {

		return ativo;
	}

	public void setAtivo(boolean ativo) {

		this.ativo = ativo;
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

	public float getPrecoVenda() {

		return precoVenda;
	}

	public void setPrecoVenda(float precoVenda) {

		this.precoVenda = precoVenda;
	}

	public boolean isPrecoVariavel() {

		return precoVariavel;
	}

	public void setPrecoVariavel(boolean precoVariavel) {

		this.precoVariavel = precoVariavel;
	}

	public TipoServicoJson getTipoServicoJson() {

		return tipoServicoJson;
	}

	public void setTipoServicoJson(TipoServicoJson tipoServicoJson) {

		this.tipoServicoJson = tipoServicoJson;
	}

	public TipoServicoMunicipioJson getTipoServicoMunicipioJson() {

		return tipoServicoMunicipioJson;
	}

	public void setTipoServicoMunicipioJson(TipoServicoMunicipioJson tipoServicoMunicipioJson) {

		this.tipoServicoMunicipioJson = tipoServicoMunicipioJson;
	}

	public classesAuxiliaresServicos.Categoria getCategoria() {

		return categoria;
	}

	public void setCategoria(classesAuxiliaresServicos.Categoria categoria) {

		this.categoria = categoria;
	}
}
