package classesAuxiliaresGeral;

public class PreCondicao {

	BancoDadosRelatorios BD = new BancoDadosRelatorios();
	String token, url = null, urlCompleta, urlCancelar, baseURI, basePath;
	String idProduto = null;

	public void parametros(String API) {

		System.out.println("Executando pré-condições");

		url = System.getProperty("url");
		if (url == null)
			url = "http://qa.varejonline.com.br:8787";

		idProduto = System.getProperty("idProduto");
		if (idProduto == null)
			idProduto = "112719";

		//id_user = 98 teste automatizado - Admin
		//id_user = 101 teste automatizado - usuario
		//id_user = 1 qa
		String id_user = "101";
		String login = "admin";
		token = BD.pegarToken(id_user, login);
		urlCompleta = url + "/apps/api/" + API;
		baseURI = url + "/apps/api";

		basePath = "/" + API;

		System.out.println("Token:" + token);
	}

	public BancoDadosRelatorios getBD() {

		return BD;
	}

	public void setBD(BancoDadosRelatorios bD) {

		BD = bD;
	}

	public String getToken() {

		return token;
	}

	public void setToken(String token) {

		this.token = token;
	}

	public String getURL() {

		return url;
	}

	public void setURL(String uRL) {

		url = uRL;
	}

	public String getURLcompleta() {

		return urlCompleta;
	}

	public void setURLcompleta(String uRLcompleta) {

		urlCompleta = uRLcompleta;
	}

	public String getBaseURI() {

		return baseURI;
	}

	public void setBaseURI(String baseURI) {

		this.baseURI = baseURI;
	}

	public String getBasePath() {

		return basePath;
	}

	public void setBasePath(String basePath) {

		this.basePath = basePath;
	}

	public String getIdProduto() {

		return idProduto;
	}

	public void setIdProduto(String idProduto) {

		this.idProduto = idProduto;
	}
}