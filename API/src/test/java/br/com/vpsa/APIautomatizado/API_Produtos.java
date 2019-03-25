package br.com.vpsa.APIautomatizado;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.core.JsonProcessingException;

import JsonProdutos.JSON_ProdutosGet;
import JsonProdutos.JSON_ProdutosPost;
import JsonProdutos.JSON_ProdutosPut;
import classesAuxiliaresGeral.BancoDadosERP;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Resposta;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class API_Produtos {

	PreCondicao preCondicao = new PreCondicao();
	JSON_ProdutosGet produtosGet = new JSON_ProdutosGet();
	JSON_ProdutosPost produtosPost = new JSON_ProdutosPost();
	JSON_ProdutosPut produtosPut = new JSON_ProdutosPut();
	Resposta resposta = new Resposta();
	BancoDadosERP BD_ERP = new BancoDadosERP();
	String descricaoBase, descricaoGrade, codigoInternoBase, codigoInternoGrade, idProdutoGrade, idProdutoBase;
	boolean grade, minMaxPorEntidade, unidadeEquivalente;
	String[] retorno;
	public static String token, URL;

	@Before
	public void preCondicoes() {

		System.out.println("\n\nIniciando execucao da API");
		preCondicao.parametros("produtos");
		URL = preCondicao.getURLcompleta();
		token = preCondicao.getToken();
		idProdutoBase = null;
		idProdutoGrade = null;
	}

	@Test
	@FileParameters(value = "src/test/resources/massaDeDados_Produto.csv", mapper = classesAuxiliaresGeral.ParametersMapper.class)
	public void cadastroProduto(Map<String, String> map) throws JsonProcessingException {

		descricaoBase = map.get("descricaoBase");
		descricaoGrade = map.get("descricaoGrade");
		codigoInternoBase = map.get("codigoInternoBase");
		codigoInternoGrade = map.get("codigoInternoGrade");
		grade = Boolean.getBoolean(map.get("grade"));
		minMaxPorEntidade = Boolean.getBoolean(map.get("minMaxPorEntidade"));
		unidadeEquivalente = Boolean.getBoolean(map.get("unidadeEquivalente"));

		BD_ERP.deleteProduto(codigoInternoGrade);
		BD_ERP.deleteProduto(codigoInternoBase);

		// POST
		retorno = produtosPost.post(descricaoBase, descricaoGrade, codigoInternoBase, codigoInternoGrade, grade, minMaxPorEntidade,
				unidadeEquivalente);
		idProdutoBase = retorno[0];
		idProdutoGrade = retorno[1];

		// GET
		produtosGet.get(idProdutoBase, descricaoBase, codigoInternoBase, grade, minMaxPorEntidade, unidadeEquivalente);

		if (!idProdutoGrade.equals(idProdutoBase)) {
			produtosGet.get(idProdutoGrade, descricaoGrade, codigoInternoGrade, grade, minMaxPorEntidade, unidadeEquivalente);

		}

		// PUT
		descricaoBase = descricaoBase + "- Alterado " + idProdutoBase;
		produtosPut.putDescricaoProduto(idProdutoBase, codigoInternoBase, descricaoBase);

		// GET
		produtosGet.get(idProdutoBase, descricaoBase, codigoInternoBase, grade, minMaxPorEntidade, unidadeEquivalente);

		// DELETE NO ERP
		BD_ERP.deleteProduto(codigoInternoBase);
	}

	
	@After
	public void mensagemFinal() {

		System.out.println("\n---- FIM -----");
	}

	public static String getToken() {

		return token;
	}

	public static void setToken(String token) {

		API_Produtos.token = token;
	}

	public static String getURL() {

		return URL;
	}

	public static void setURL(String uRL) {

		URL = uRL;
	}
}