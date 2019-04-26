package br.com.vpsa.APIautomatizado;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import classesAuxiliaresGeral.BancoDadosERP;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresJson.API;
import classesAuxiliaresJson.JsonFormatar;
import classesAuxiliaresServicos.Servico;

public class API_Servicos {

	PreCondicao preCondicao = new PreCondicao();
	Resposta resposta = new Resposta();
	BancoDadosERP BD_ERP = new BancoDadosERP();
	API api = new API();
	Response response;
	ObjectMapper mapper = new ObjectMapper();
	JsonFormatar formatar  = new JsonFormatar();

	String token, URL, idServico, jsonGet,codigoInterno;
	Servico postFinal, putFinal;

	@Before
	public void preCondicoes() {

		System.out.println("\n\nIniciando execucao da API");
		preCondicao.parametros("servicos");
		URL = preCondicao.getURLcompleta();
		token = preCondicao.getToken();
		codigoInterno = ("SV001");
		BD_ERP.deleteServico(codigoInterno);
	}

	@Test
	public void apiServicoComPreco() throws JsonProcessingException {

		// POST
		postFinal = postPadraoServico();
		idServico = api.efetuarPost(postFinal, URL, token, "Servicos");
		// GET
		efetuarGetServicos(URL, token, idServico, postFinal);

		// PUT
		alterarDescricaoServico(postFinal);
		api.efetuarPut(URL, token, idServico, "Servicos", putFinal);

		// GET
		efetuarGetServicos(URL, token, idServico, postFinal);

		// DELETE NO ERP
		BD_ERP.deleteServico(codigoInterno);
	}

	@After
	public void mensagemFinal() {

		System.out.println("\n---- FIM -----");
	}

	public Servico postPadraoServico(){
		Servico servico = new Servico();
		return servico;
	}

	public Servico alterarDescricaoServico(Servico postFinal){
		Servico servico = new Servico();
		servico.setDescricao(servico.getDescricao()+ " - Alterado");
		return servico;
	}

	public void efetuarGetServicos(String URL, String token, String idServico, Servico stringEnviada){

		System.out.println("\n GET");
		System.out.println("Get:" + URL + "/" + idServico + "?token=" + token);

		response = (Response) given().when().get(URL + "/" + idServico + "?token=" + token);
		jsonGet = response.asString();
		formatar.formatarJson(jsonGet);
		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
			String Json = response.asString();
			JsonPath jp = new JsonPath(Json);

			assertEquals(stringEnviada.getDescricao(), jp.get("descricao"));
			assertEquals(stringEnviada.getCodigoInterno(), jp.get("codigoInterno"));
			assertEquals(stringEnviada.getClassificacao(), jp.get("classificacao"));
			assertEquals(stringEnviada.getPrecoVenda(), jp.get("precoVenda"));
			assertEquals(stringEnviada.getTipoServicoJson().getCodigo(), jp.get("tipoServicoJson.codigo"));
			assertEquals(stringEnviada.getTipoServicoMunicipioJson().getCodigo(), jp.get("tipoServicoMunicipioJson.codigo"));
			assertEquals(stringEnviada.getCategoria().getId(), jp.get("categoria.id"));

			System.out.println("Validacao no GET: \n - Descricao \n - Codigo Interno \n - Classificacao \n Preco Venda"
					+ "\n - Codigo tipo servico \n - Codigo tipo servico municipio \n - ID categoria");
		}
	}
}