package br.com.vpsa.APIautomatizado;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import classesAuxiliaresGeral.BancoDadosERP;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresJson.API;
import classesAuxiliaresJson.JsonFormatar;
import classesAuxiliaresProdutos.Categoria;
import classesAuxiliaresProdutos.Produto;

public class API_ProdutosInformarProduto {

	PreCondicao preCondicao = new PreCondicao();
	BancoDadosERP BD_ERP = new BancoDadosERP();
	Resposta resposta = new Resposta();
	ObjectMapper mapper = new ObjectMapper();
	API api = new API();
	Response response;
	JsonFormatar formatar  = new JsonFormatar();
	Produto produtoPut, putFinal, produtoConvertido;
	String idProdutoGrade, idProdutoBase, jsonStringGet, json, idProdutoBanco;
	String[] retorno;
	public static String token, URL;

	@Before
	public void preCondicoes() {

		System.out.println("\n\nIniciando execucao da API");
		preCondicao.parametros("produtos");
		URL = preCondicao.getURLcompleta();
		token = preCondicao.getToken();
		idProdutoBanco = preCondicao.getIdProduto();
		idProdutoBase = null;
		idProdutoGrade = null;
	}

	@Test
	public void putProdutoExistente() {
		if (BD_ERP.verificaProduto(idProdutoBanco) == null){
			// GET
			jsonStringGet = efetuarGetProduto(URL, token, idProdutoBanco);
			produtoPut = converterJson(jsonStringGet);
			// GET
			putFinal = putProduto(idProdutoBanco,produtoPut);
			api.efetuarPut(URL, token, idProdutoBanco, "Produtos", putFinal);

			// GET
			efetuarGetProdutoDepoisPut(URL, token, idProdutoBanco, putFinal);

		}else{
			fail("Produto nao localizado");
		}
	}

	public Produto converterJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			produtoConvertido = mapper.readValue(json, Produto.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return produtoConvertido;
	}


	public String efetuarGetProduto(String URL, String token, String idProduto){
		System.out.println("\n GET");
		System.out.println("Get:" + URL + "/" + idProduto + "?token=" + token);

		response = (Response) given().when().get(URL + "/" + idProduto + "?token=" + token);
		jsonStringGet = response.asString();
		formatar.formatarJson(jsonStringGet);
		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
			json = response.asString();
			@SuppressWarnings("unused")
			JsonPath jp = new JsonPath(json);
			System.out.println("GET realizado com sucesso.");
		}
		return json;
	}

	public Produto putProduto(String idProduto, Produto produtoGet){

		Produto produto = new Produto();

		Categoria categorias1 = new Categoria();
		Categoria categorias2 = new Categoria();
		Categoria categorias3 = new Categoria();
		Categoria categorias4 = new Categoria();
		Categoria categorias5 = new Categoria();
		Categoria categorias6 = new Categoria();
		Categoria categorias7 = new Categoria();
		Categoria categorias8 = new Categoria();
		Categoria categorias9 = new Categoria();
		Categoria categorias10 = new Categoria();
		Categoria categorias11 = new Categoria();

		produto.setDescricao(produtoGet.getDescricao() + "ALTERADO");
		produto.setMetodoControle(produtoGet.getMetodoControle());
		produto.setCodigoNcm(produtoGet.getCodigoNcm());
		produto.setCodigoInterno(produtoGet.getCodigoInterno());
		produto.setOrigem(produtoGet.getOrigem());
		produto.setUnidade(produtoGet.getUnidade());
		produto.setClassificacao(produtoGet.getClassificacao());
		produto.setCustoReferencial (produtoGet.getCustoReferencial());

		produto.setPreco(produtoGet.getPreco());
		produto.setDescontoMaximo(produtoGet.getDescontoMaximo());
		produto.setComissao(produtoGet.getComissao());
		produto.setAtivo(true);

		produto.setCategorias(new ArrayList<Categoria>());
		categorias1.setNome(produtoGet.getCategorias().get(0).getNome());
		categorias1.setNivel(produtoGet.getCategorias().get(0).getNivel());

		categorias2.setNome("VERAO");
		categorias2.setNivel("ESTACAO_DE_USO");

		categorias3.setNome("DIA A DIA");
		categorias3.setNivel("LINHA");

		categorias4.setNome("FITNESS");
		categorias4.setNivel("SUB_LINHA");

		categorias5.setNome("FEMININO");
		categorias5.setNivel("GENERO");

		categorias6.setNome("NAO INFORMADO");
		categorias6.setNivel("GRUPO_DO_ARTIGO");

		categorias7.setNome("NAO INFORMADO");
		categorias7.setNivel("ARTIGO_PRODUTO");

		categorias8.setNome("NAO INFORMADO");
		categorias8.setNivel("COMPL_ARTIGO");

		categorias9.setNome("NAO INFORMADO");
		categorias9.setNivel("SUB_COMPL_ARTIGO");

		categorias10.setNome("GRAPHENE");
		categorias10.setNivel("DESCRICAO_MARCA");

		categorias11.setNome("980");
		categorias11.setNivel("COD_UN_FATURAMENTO");

		produto.getCategorias().add(categorias1);
		produto.getCategorias().add(categorias2);
		produto.getCategorias().add(categorias3);
		produto.getCategorias().add(categorias4);
		produto.getCategorias().add(categorias5);
		produto.getCategorias().add(categorias6);
		produto.getCategorias().add(categorias7);
		produto.getCategorias().add(categorias8);
		produto.getCategorias().add(categorias9);
		produto.getCategorias().add(categorias10);
		produto.getCategorias().add(categorias11);

		return produto;
	}

	public void efetuarGetProdutoDepoisPut(String URL, String token, String idProduto, Produto StringEnviada){
		System.out.println("\n GET");
		System.out.println("Get:" + URL + "/" + idProduto + "?token=" + token);

		response = (Response) given().when().get(URL + "/" + idProduto + "?token=" + token);
		String jsonGet = response.asString();
		formatar.formatarJson(jsonGet);
		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
			String Json = response.asString();
			JsonPath jp = new JsonPath(Json);

			Assert.assertEquals(StringEnviada.getDescricao(),jp.get("descricao"));
			assertEquals(StringEnviada.getCustoReferencial(), jp.get("custoReferencial"));
			assertEquals("ESTOCAVEL", jp.get("metodoControle"));
			assertEquals("2522.20.00", jp.get("codigoNcm"));
			assertEquals("REVENDA", jp.get("classificacao"));
			assertEquals(2, jp.get("estoqueMinimo"));
			assertEquals(10, jp.get("estoqueMaximo"));

			System.out.println("GET realizado com sucesso.");
			System.out.println("Validacao no GET: \n - Descricao \n - Custo referencial \n - Metodo controle"
					+ " \n - Codigo NCM \n - Classificacao \n - Estoque min e max \n - Codigo sistema");
		}else{
			fail("Status diferente de 200 ou 201");
		}
	}

	@After
	public void mensagemFinal() {

		System.out.println("\n---- FIM -----");
	}

}