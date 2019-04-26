package JsonProdutos;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.hamcrest.Matcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import br.com.vpsa.APIautomatizado.API_Produtos;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresJson.JsonFormatar;

public class JSON_ProdutosGet {

	PreCondicao preCondicao = new PreCondicao();
	JsonFormatar jsonClasse = new JsonFormatar();
	String token, URL, baseURI, basePath, descricao, idProduto, codigoInterno;
	boolean grade, minMaxPorEntidade, unidadeEquivalente;
	static String codigoSistema;
	Resposta resposta = new Resposta();
	Matcher<? super Integer> Status;
	public static String jsonAsString;
	String jsonProduto, prettyJson;
	ObjectMapper mapper;

	public void get(String idProduto, String descricao, String codigoInterno, boolean grade, boolean minMaxPorEntidade, boolean unidadeEquivalente) {

		this.idProduto = idProduto;
		this.descricao = descricao;
		this.codigoInterno = codigoInterno;
		this.grade = grade;
		this.minMaxPorEntidade = minMaxPorEntidade;
		this.unidadeEquivalente = unidadeEquivalente;

		// Dados acesso
		URL = API_Produtos.getURL();
		token = API_Produtos.getToken();

		System.out.println("\n GET PRODUTO - Pesquisa produto");
		System.out.println("Get:" + URL + "/" + idProduto + "?token=" + token);
		Response response = (Response) given().when().get(URL + "/" + idProduto + "?token=" + token);

		jsonAsString = response.asString();
		prettyJson = jsonClasse.formatarJson(jsonAsString);

		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {

			String Json = response.asString();
			JsonPath jp = new JsonPath(Json);

			assertEquals(descricao, jp.get("descricao"));
			assertEquals(50.12f, jp.get("custoReferencial"));
			assertEquals("ESTOCAVEL", jp.get("metodoControle"));
			assertEquals("2522.20.00", jp.get("codigoNcm"));
			assertEquals("REVENDA", jp.get("classificacao"));
			assertEquals(2, jp.get("estoqueMinimo"));
			assertEquals(10, jp.get("estoqueMaximo"));
			codigoSistema = jp.get("codigoSistema");
			System.out.println("Validacao no GET: \n - Descricao \n - Custo referencial \n - Metodo controle"
					+ " \n - Codigo NCM \n - Classificacao \n - Estoque min e max \n - Codigo sistema");

			if (minMaxPorEntidade == true) {
				equals(Arrays.asList(15, 16, jp.get("dadosPorEntidade.estoqueMaximo")));
				equals(Arrays.asList(5, 6, jp.get("dadosPorEntidade.estoqueMinimo")));
				System.out.println(" - Estoque min e max por produto \n - Estoque min e max por entidade");
			}

			if (unidadeEquivalente == true) {
				equals(Arrays.asList(15, 16, jp.get("dadosPorEntidade.estoqueMaximo")));
				equals(Arrays.asList(5, 6, jp.get("dadosPorEntidade.estoqueMinimo")));
				System.out.println(" - Unidades equivalentes");
			}
		} else {
			fail("Status diferente de 200 ou 201");
		}
	}

	public static String getCodigoSistema() {

		return codigoSistema;
	}
}
