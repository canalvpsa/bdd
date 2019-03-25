package br.com.vpsa.APIautomatizado;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import classesAuxiliaresGeral.Itens;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresGeral.Servico;
import classesAuxiliaresJson.API;
import classesAuxiliaresJson.Data;
import classesAuxiliaresJson.JsonFormatar;
import classesAuxiliaresOrcamentoVenda.OrcamentoVenda;

public class API_OrcamentoVenda {

	PreCondicao preCondicao = new PreCondicao();
	ObjectMapper mapper = new ObjectMapper();
	JsonFormatar formatar  = new JsonFormatar();
	Resposta resposta = new Resposta();
	API api = new API();
	Data data = new Data();
	OrcamentoVenda postFinal, putFinal;
	Response response;
	String token, URL, jsonGet, idOrcamentoVenda;

	@Before
	public void preCondicoes() {

		System.out.println("\n\nIniciando execucao da API");
		preCondicao.parametros("orcamentos");
		URL = preCondicao.getURLcompleta();
		token = preCondicao.getToken();
	}

	@Test
	public void postOrcamentoProduto() throws JsonProcessingException{
		// POST
		postFinal = postPadraoProduto();
		idOrcamentoVenda = api.efetuarPost(postFinal, URL, token, "Orcamentos");
		// GET
		efetuarGetOrcamento(URL, token, idOrcamentoVenda, postFinal);

		//PUT
		putFinal = alterarOrcamento(postFinal);
		api.efetuarPut(URL, token, idOrcamentoVenda, "Orcamentos", putFinal);

		// GET APÓS EFETUAR MUDANÇAS
		efetuarGetOrcamento(URL, token, idOrcamentoVenda, postFinal);
	}


	@Test
	public void postOrcamentoServico(){
		postFinal = postPadraoServico();
		idOrcamentoVenda = api.efetuarPost(postFinal, URL, token, "Orcamentos");
	}

	@Test
	public void postServicoDecidirPrecoVenda(){
		postFinal = postPadraoServico();
		postFinal.getServicos().clear();
		Servico servico = new Servico(1002, 1, 12.65);
		postFinal.getServicos().add(servico);
		idOrcamentoVenda = api.efetuarPost(postFinal, URL, token, "Orcamentos");
	}

	@Test
	public void postReservaEstoqueProdutoSaldo(){
		postFinal = postPadraoProduto();
		postFinal.setReservarEstoque(true);
		idOrcamentoVenda = api.efetuarPost(postFinal, URL, token, "Orcamentos");
	}

	@Test
	public void postReservaEstoqueProdutoSemSaldo(){
		postFinal = postPadraoProduto();
		postFinal.setReservarEstoque(true);
		idOrcamentoVenda = api.efetuarPost(postFinal, URL, token, "Orcamentos");
	}


	public void efetuarGetOrcamento(String URL, String token, String idOrcamento, OrcamentoVenda stringEnviada){

		System.out.println("\n GET");
		System.out.println("Get:" + URL + "/" + idOrcamento + "?token=" + token);

		response = (Response) given().when().get(URL + "/" + idOrcamento + "?token=" + token);
		jsonGet = response.asString();
		formatar.formatarJson(jsonGet);
		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
			String Json = response.asString();
			JsonPath jp = new JsonPath(Json);

			Assert.assertEquals(data.calcularData(0, false),jp.get("dataEmissao"));
			Assert.assertEquals(data.calcularData(stringEnviada.getValidade(), false),jp.get("dataValidade"));
			Assert.assertEquals(stringEnviada.getIdEntidade(),jp.getInt("idEntidade"));
			Assert.assertEquals(stringEnviada.getIdPlanoPagamento(),jp.getInt("idPlanoPagamento"));
			Assert.assertEquals(stringEnviada.getIdRepresentante(),jp.getInt("idRepresentante"));
			Assert.assertEquals(stringEnviada.getIdTerceiro(),jp.getInt("idTerceiro"));
			Assert.assertEquals(stringEnviada.getVendaConsumidorFinal(),jp.get("vendaConsumidorFinal"));
			Assert.assertEquals(stringEnviada.getReservarEstoque(), jp.get("reservarEstoque"));

			Assert.assertEquals(Arrays.asList(stringEnviada.getItens().get(0).getQuantidade(),
					stringEnviada.getItens().get(1).getQuantidade()),jp.get("itens.quantidade"));

			Assert.assertEquals(Arrays.asList(stringEnviada.getItens().get(0).getValorUnitario().floatValue(),
					stringEnviada.getItens().get(1).getValorUnitario().floatValue()),jp.get("itens.valorUnitario"));

			Assert.assertEquals(Arrays.asList(stringEnviada.getItens().get(0).getProduto().getId(),
					stringEnviada.getItens().get(1).getProduto().getId()), jp.get("itens.produto.id"));

			Assert.assertEquals(Arrays.asList(stringEnviada.getItens().get(0).getProduto().getCodigoSistema(),
					stringEnviada.getItens().get(1).getProduto().getCodigoSistema()),jp.get("itens.produto.codigoSistema"));

			System.out.println("GET realizado com sucesso.");
			System.out.println("Validacao no GET: \n - Data emissao \n - Data validade \n - ID Entidade"
					+ " \n - ID plano pagamento \n - ID representante \n - ID terceiro \n - Venda consumidor final"
					+ " \n - Quantidade do item no orcamento \n - ID produto \n - Codigo do produto");

		}else{
			Assert.fail("sem json");
		}
	}


	public OrcamentoVenda postPadraoProduto(){
		OrcamentoVenda orcamento = new OrcamentoVenda();
		Itens itens1 = new Itens();
		itens1.orcamentoItem1();

		orcamento.setItens(new ArrayList<Itens>());
		orcamento.getItens().add(itens1);

		Itens itens2 = new Itens();
		itens2.orcamentoItem2();
		orcamento.getItens().add(itens2);

		return orcamento;
	}

	public OrcamentoVenda postPadraoServico(){
		OrcamentoVenda orcamento = new OrcamentoVenda();
		Servico servico = new Servico(1002, 1, 12.50);
		orcamento.setServicos(new ArrayList<Servico>());
		orcamento.getServicos().add(servico);

		return orcamento;
	}	

	public OrcamentoVenda alterarOrcamento(OrcamentoVenda postFinal){
		putFinal = postFinal;

		//MUDANÇAS NO ORÇAMENTO
		putFinal.setValidade(10);
		putFinal.getItens().clear();

		Itens itemNovo1 = new Itens();
		itemNovo1.orcamentoItem1();

		itemNovo1.setQuantidade(10);

		putFinal.setItens(new ArrayList<Itens>());
		putFinal.getItens().add(itemNovo1);

		Itens itemNovo2 = new Itens();
		itemNovo2.orcamentoItem1();

		putFinal.setItens(new ArrayList<Itens>());
		putFinal.getItens().add(itemNovo2);

		return putFinal;
	}
}