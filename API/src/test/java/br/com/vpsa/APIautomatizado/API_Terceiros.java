package br.com.vpsa.APIautomatizado;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import classesAuxiliaresGeral.BancoDadosERP;
import classesAuxiliaresGeral.BancoDadosRelatorios;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresJson.API;
import classesAuxiliaresJson.JsonFormatar;
import classesAuxiliaresTerceiros.Endereco;
import classesAuxiliaresTerceiros.Telefone;
import classesAuxiliaresTerceiros.Terceiro;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class API_Terceiros {

	PreCondicao preCondicao = new PreCondicao();
	BancoDadosRelatorios BD_rel = new BancoDadosRelatorios();
	BancoDadosERP BD_ERP = new BancoDadosERP();
	ObjectMapper mapper = new ObjectMapper();
	JsonFormatar formatar  = new JsonFormatar();
	API api = new API();
	Resposta resposta = new Resposta();
	Response response;
	String idTerceiro, nome, documento, documentoFormatado, classe1, classe2, tipoTerceiro, jsonGet;
	Terceiro postFinal, putFinal;
	public static String token, URL;

	@Before
	public void preCondicoes() {

		System.out.println("\n\nIniciando execucao da API");
		preCondicao.parametros("terceiros");
		URL = preCondicao.getURLcompleta();
		token = preCondicao.getToken();
	}

	@Test
	@FileParameters(value = "src/test/resources/massaDeDados_Terceiro.csv", mapper = classesAuxiliaresGeral.ParametersMapper.class)
	public void cadastroTerceiro(Map<String, String> map) throws JsonProcessingException {

		documentoFormatado = map.get("documento");
		nome = map.get("nome");
		tipoTerceiro = map.get("tipoTerceiro");
		classe1 = map.get("classe1");
		classe2 = map.get("classe2");
		documento = documentoFormatado.replaceAll("[^0-9]", "");

		// POST
		BD_ERP.deleteTerceiro(documento);
		postFinal = postTerceiro(documentoFormatado, nome, classe1, classe2);
		idTerceiro = api.efetuarPost(postFinal, URL, token, "Terceiros");

		// GET
		efetuarGetTerceiro(URL, token, idTerceiro, postFinal, tipoTerceiro);

		// PUT
		nome = nome + idTerceiro;
		putFinal = alterarTerceiro(nome);

		// GET
		efetuarGetTerceiro(URL, token, idTerceiro, putFinal, tipoTerceiro);

	}

	public Terceiro postTerceiro(String documento, String nome, String classe1, String classe2) {

		Terceiro terceiro = new Terceiro();
		Endereco enderecos = new Endereco();
		Telefone telefones = new Telefone();

		terceiro.setNome(nome);
		terceiro.setDocumento(documento);

		terceiro.setEnderecos(new ArrayList<Endereco>());
		enderecos.setTipo("RUA");
		enderecos.setLogradouro("AUBE");
		enderecos.setNumero("1504");
		enderecos.setBairro("Centro");
		enderecos.setCep("8920600");
		enderecos.setCodigoIBGECidade("4209102");
		enderecos.setTipoEndereco("ENDERECO_CORRESPONDENCIA");

		terceiro.setTelefones(new ArrayList<Telefone>());
		telefones.setDdi("55");
		telefones.setDdd("47");
		telefones.setNumero("33330000");

		terceiro.setClasses(new ArrayList<String>());
		List<String> classes = new ArrayList<String>();
		classes.add(classe1);
		classes.add(classe2);

		terceiro.getEnderecos().add(enderecos);
		terceiro.getTelefones().add(telefones);
		terceiro.getClasses().addAll(classes);

		return terceiro;
	}

	public void efetuarGetTerceiro(String URL, String token, String idTerceiro, Terceiro stringEnviada, String tipoTerceiro){

		System.out.println("\n GET");
		System.out.println("Get:" + URL + "/" + idTerceiro + "?token=" + token);

		response = (Response) given().when().get(URL + "/" + idTerceiro + "?token=" + token);
		jsonGet = response.asString();
		formatar.formatarJson(jsonGet);
		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
			String Json = response.asString();
			JsonPath jp = new JsonPath(Json);

			assertEquals(stringEnviada.getNome(), jp.get("nome"));
			assertEquals(tipoTerceiro, jp.get("tipoPessoa"));
			assertEquals(stringEnviada.getDocumento(), jp.get("documento"));
			assertEquals(Arrays.asList(stringEnviada.getClasses().get(0), stringEnviada.getClasses().get(1)), jp.get("classes"));

			System.out.println("GET realizado com sucesso.");
			System.out.println("Validacao no GET: \n - Nome \n - Tipo pessoa \n - Documento \n - Classe");

		}else{
			Assert.fail("sem json");
		}
	}

	public Terceiro alterarTerceiro(String nomeAlterado){
		putFinal = postFinal;

		putFinal.setNome(nomeAlterado);

		return putFinal;
	}


	@After
	public void mensagemFinal() {

		System.out.println("\n---- FIM -----");
	}
}