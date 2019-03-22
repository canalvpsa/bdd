package br.com.vpsa.APIautomatizado;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import classesAuxiliaresGeral.Itens;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresJson.API;
import classesAuxiliaresJson.Data;
import classesAuxiliaresJson.JsonFormatar;
import classesAuxiliaresPedidoVenda.PedidoVenda;
import cucumber.api.CucumberOptions;
import cucumber.api.java.pt.Dado;
import cucumber.api.junit.Cucumber;
import static com.jayway.restassured.RestAssured.given;
/*
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:caracteristicas", tags = "@API_PedidoVenda", 
glue = "br.com.vpsa.APIautomatizado.teste.passos", monochrome = true, dryRun = false)*/
public class API_PedidoVenda {

	PreCondicao preCondicao = new PreCondicao();
	ObjectMapper mapper = new ObjectMapper();
	JsonFormatar formatar  = new JsonFormatar();
	Resposta resposta = new Resposta();
	Data data = new Data();
	API api = new API();
	Response response;
	PedidoVenda postFinal, putFinal, postDescontoItem;
	String token, URL, idPedidoVenda, jsonGet;


	@Before
	public void preCondicoes() {

		System.out.println("\n\nIniciando execucao da API");
		preCondicao.parametros("pedidos");
		URL = preCondicao.getURLcompleta();
		token = preCondicao.getToken();
	}
	  @Dado("^que eu já preenchi o cabeçalho da venda")
      public void que_as_contas_sao_do(String nome, List<Conta> listaDeContas) throws Throwable {
            // Definição do banco e associando as contas
            banco = new Banco(nome, listaDeContas);
 
      }

	@Test
	public void apiPedidoVendaAvista() {

		// POST
		postFinal = postPadraoPedido();
		idPedidoVenda = api.efetuarPost(postFinal, URL, token, "pedidos");

		// GET
		efetuarGetPedidoVenda(URL, token, idPedidoVenda, postFinal, "Ativo");

		// CANCELAR
		cancelarPedidoVenda(URL, token, idPedidoVenda);

		// GET
		efetuarGetPedidoVenda(URL, token, idPedidoVenda, postFinal, "Cancelado");
	}

	@Test
	public void apiPedidoVendaDescontoItem() {

		// POST
		postFinal = postPadraoPedido();
		postFinal = descontoItem(postFinal);
		idPedidoVenda = api.efetuarPost(postFinal, URL, token, "pedidos");

		// GET
		efetuarGetPedidoVenda(URL, token, idPedidoVenda, postFinal, "Ativo");

		// CANCELAR
		cancelarPedidoVenda(URL, token, idPedidoVenda);

		// GET
		efetuarGetPedidoVenda(URL, token, idPedidoVenda, postFinal, "Cancelado");


		/*
		// POST
		valorLiquido = 62.38f;
		valorTotal = 65.88f;
		descontoUnitarioItem1 = (int) 1f;
		idPedidoVenda = postPedidoVenda.post(URL, token);

		// GET
		cancelado = false;
		getPedidoVenda.get(URL, token, idPedidoVenda);

		// CANCELAR
		cancelarPedidoVenda.cancelar(URL, token, idPedidoVenda);

		// GET
		cancelado = true;
		getPedidoVenda.get(URL, token, idPedidoVenda);
		 */
	}

	@Test
	public void apiPedidoVendaDescontoDoisItens() {
		/*
		// POST
		valorLiquido = 62.18f;
		valorTotal = 65.88f;
		descontoUnitarioItem1 = 1.20f;
		descontoUnitarioItem2 = 2.50f;
		idPedidoVenda = postPedidoVenda.post(URL, token);

		// GET
		cancelado = false;
		getPedidoVenda.get(URL, token, idPedidoVenda);

		// CANCELAR
		cancelarPedidoVenda.cancelar(URL, token, idPedidoVenda);

		// GET
		cancelado = true;
		getPedidoVenda.get(URL, token, idPedidoVenda);
		 */
	}

	public PedidoVenda postPadraoPedido(){
		PedidoVenda pedidoVenda = new PedidoVenda();
		Itens itens1 = new Itens();
		Itens itens2 = new Itens();
		pedidoVenda.setData(data.calcularData(0, false));
		pedidoVenda.setHorario(data.retornaHorario());
		pedidoVenda.setItens(new ArrayList<Itens>());
		
		itens1.pedidoVendaItem1();
		itens2.pedidoVendaItem2();
		pedidoVenda.getItens().add(itens1);
		pedidoVenda.getItens().add(itens2);

		return pedidoVenda;
	}

	public void cancelarPedidoVenda(String URL, String token, String idRecurso) {

		System.out.println("\n CANCELAR PEDIDO VENDA");
		System.out.println("POST:" + URL + "/" + idRecurso + "/cancelar?token=" + token);
		resposta.cancelar(URL, token, idRecurso, "pedidoVenda");
	}
	
	public PedidoVenda descontoItem(PedidoVenda postFinal){
		postDescontoItem = postFinal;
		postDescontoItem.getItens().clear();
		
		Itens itens1 = new Itens();
		Itens itens2 = new Itens();
		
		itens1.pedidoVendaItem1();
		itens1.setValorDesconto(1.00);
		
		itens2.pedidoVendaItem2();
		
		postDescontoItem.getItens().add(itens1);
		postDescontoItem.getItens().add(itens2);

		return postDescontoItem;
	}
	

	public void efetuarGetPedidoVenda(String URL, String token, String idPedidoVenda, PedidoVenda stringEnviada, String statusPedido){
		System.out.println("\n GET");
		System.out.println("Get:" + URL + "/" + idPedidoVenda + "?token=" + token);

		response = (Response) given().when().get(URL + "/" + idPedidoVenda + "?token=" + token);
		jsonGet = response.asString();
		formatar.formatarJson(jsonGet);
		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
			String Json = response.asString();
			JsonPath jp = new JsonPath(Json);

			Assert.assertEquals(data.calcularData(0, false),jp.get("data"));
		//	Assert.assertEquals(data.retornaHorario(), jp.get("horario"));
			Assert.assertEquals(stringEnviada.getIdEntidade(),jp.getInt("idEntidade"));
			Assert.assertEquals("A VISTA",jp.get("planoPagamento"));
			Assert.assertEquals(stringEnviada.getIdRepresentante(),jp.getInt("idRepresentante"));
			Assert.assertEquals(stringEnviada.getIdTerceiro(),jp.getInt("idTerceiroCliente"));
			Assert.assertEquals(stringEnviada.getVendaConsumidorFinal(),jp.get("vendaConsumidorFinal"));

			Assert.assertEquals(Arrays.asList(stringEnviada.getItens().get(0).getQuantidade(),
					stringEnviada.getItens().get(1).getQuantidade()),jp.get("itens.quantidade"));

			Assert.assertEquals(Arrays.asList(),jp.get("itens.valorTotal"));

			Assert.assertEquals(Arrays.asList(stringEnviada.getItens().get(0).getProduto().getId(),
					stringEnviada.getItens().get(1).getProduto().getId()), jp.get("itens.produto.id"));

			Assert.assertEquals(Arrays.asList(stringEnviada.getItens().get(0).getProduto().getCodigoSistema(),
					stringEnviada.getItens().get(1).getProduto().getCodigoSistema()),jp.get("itens.produto.codigoSistema"));

			System.out.println("GET realizado com sucesso.");
			System.out.println("Validacao no GET: \n - Data emissao \n - Data validade \n - ID Entidade"
					+ " \n - ID plano pagamento \n - ID representante \n - ID terceiro \n - Venda consumidor final"
					+ " \n - Quantidade do item no pedido de venda \n - ID produto \n - Codigo do produto");


			/*
			 assertEquals(dataAtualSemHora, jp.get("data"));
				assertEquals(API_PedidoVenda.getCancelado(), jp.get("cancelado"));
				assertEquals(API_PedidoVenda.getValorLiquido(), jp.get("valorLiquido"));
				assertEquals(0, jp.get("valorOutros"));
				assertEquals(0, jp.get("valorSeguro"));
				assertEquals(4, jp.get("idTerceiroCliente"));
				assertEquals(1, jp.get("idRepresentante"));
				assertEquals(true, jp.get("vendaConsumidorFinal"));
				assertEquals(API_PedidoVenda.getValorLiquido(), jp.get("valorTotal"));
				assertEquals("A VISTA", jp.get("planoPagamento"));
				assertEquals(valorParcela, jp.get("parcelas.valor"));
				assertEquals(Arrays.asList(dataAtualSemHora), jp.get("parcelas.dataVencimento"));
				assertEquals(1, jp.get("idEntidade"));
				equals(Arrays.asList(descontoUnitarioItem1, descontoUnitarioItem2, jp.get("itens.valorDesconto")));
				assertEquals(Arrays.asList(API_PedidoVenda.getTotalProduto1(), API_PedidoVenda.getTotalProduto2()), jp.get("itens.valorTotal"));
				assertEquals(Arrays.asList(5, 11), jp.get("itens.produto.id"));
				assertEquals(Arrays.asList("1.00014.016.014840", "1.00013.014.014264"), jp.get("itens.produto.codigoSistema"));
				assertEquals(Arrays.asList(2, 3), jp.get("itens.quantidade"));
				assertEquals(Arrays.asList("00014 - BLUSA MALHA HIGH COTTON RIB 16 AMARELO PINA COLADA",
						"00013 - BLUSA MALHA COTTON THIRTY 14 SALMAO WEEKEND"), jp.get("itens.produto.descricao"));
				assertEquals(Arrays.asList(0, 0), jp.get("itens.valorICMSST"));
				assertEquals(Arrays.asList(0, 0), jp.get("itens.valorCOFINS"));
				assertEquals(Arrays.asList(0, 0), jp.get("itens.valorIPI"));
				assertEquals(Arrays.asList(0, 0), jp.get("itens.valorICMS"));
				assertEquals(Arrays.asList(0, 0), jp.get("itens.valorPIS"));
				assertEquals(Arrays.asList("PC", "PC"), jp.get("itens.unidade"));
			 */
		}else{
			Assert.fail("sem json");
		}
	}
}