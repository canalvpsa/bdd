package br.com.vpsa.APIautomatizado;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import classesAuxiliaresGeral.BancoDadosERP;
import classesAuxiliaresGeral.PreCondicao;
import classesAuxiliaresGeral.Produto;
import classesAuxiliaresGeral.Produtos;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresJson.API;
import classesAuxiliaresJson.Data;
import classesAuxiliaresJson.JsonFormatar;
import classesAuxiliaresJson.ValorDecimais;
import classesAuxiliaresPedidoCompra.Parcelas;
import classesAuxiliaresPedidoCompra.PedidoCompra;

public class API_PedidoCompra {

	PreCondicao preCondicao = new PreCondicao();
	BancoDadosERP BD = new BancoDadosERP();
	ValorDecimais valor = new ValorDecimais();
	ObjectMapper mapper = new ObjectMapper();
	JsonFormatar formatar  = new JsonFormatar();
	Resposta resposta = new Resposta();
	Data data = new Data();
	API api = new API();
	PedidoCompra putFinal,postFinal;
	String token, URL, idPedidoCompra, jsonGet;

	@Before
	public void preCondicoes() {
		System.out.println("\n\nIniciando execucao da API");
		preCondicao.parametros("pedidos-compra");
		URL = preCondicao.getURLcompleta();
		token = preCondicao.getToken();
	}

	@Test
	public void apiPedidoCompraAvista() {
		BD.parametroFluxoAprovacao(0);
		postFinal = postPadrao();
		idPedidoCompra = api.efetuarPost(postFinal, URL, token, "PedidoCompra");

		// GET
		efetuarGet(URL, token, idPedidoCompra, postFinal, "ABERTO");

		// PUT
		putFinal = alterarPedidoCompra(postFinal);
		api.efetuarPut(URL, token, idPedidoCompra, "PedidoCompra", putFinal);

		// GET
		efetuarGet(URL, token, idPedidoCompra, postFinal, "ABERTO");

	}

	@Test
	public void apiPedidoCompraFluxoAprovacao() {
		BD.parametroFluxoAprovacao(1);
		// POST
		postFinal = postPadrao();
		idPedidoCompra = api.efetuarPost(postFinal, URL, token, "PedidoCompra");

		// GET
		efetuarGet(URL, token, idPedidoCompra, postFinal, "PENDENTE_APROVACAO");
	}

	public PedidoCompra postPadrao() {

		PedidoCompra pedidoCompra = new PedidoCompra(data.calcularData(0, false)+ " 00:00:00", data.calcularData(3, false));

		Parcelas parcelas = new Parcelas(data.calcularData(3, false), 21.98);
		pedidoCompra.setParcelas(new ArrayList<Parcelas>());
		pedidoCompra.getParcelas().add(parcelas);
		pedidoCompra.setTipoPlanoPagamento(2);

		Produtos produtos = new Produtos();
		Produto produto = new Produto(660, "BACF02.0003");

		pedidoCompra.setProdutos(new ArrayList<Produtos>());
		produtos.setProduto(produto);
		produtos.setQuantidade(2);
		produtos.setValorDesconto(0);
		produtos.setValorUnitario(10.99);
		produtos.setIdOperacao(20);

		// LISTA DO PRODUTO
		produto.setId(660);
		produto.setCodigoSistema("BACF02.0003");

		// LISTA DE PRODUTOS
		pedidoCompra.getProdutos().add(produtos);

		return pedidoCompra;
	}


	public void efetuarGet(String URL, String token, String idPedidoCompra, PedidoCompra stringEnviada, String statusPedido) {

		System.out.println("\n GET");
		System.out.println("Get:" + URL + "/" + idPedidoCompra + "?token=" + token);


		Response response = (Response) given().when().get(URL + "/" + idPedidoCompra + "?token=" + token);

		jsonGet = response.asString();
		formatar.formatarJson(jsonGet);

		if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
			String Json = response.asString();
			JsonPath jp = new JsonPath(Json);

			Assert.assertEquals(stringEnviada.getDataCompra(),jp.get("dataPedidoCompra"));
			Assert.assertEquals(stringEnviada.getDataLimiteEntrega(),jp.get("previsaoEntrega"));
			Assert.assertEquals(stringEnviada.getIdFornecedor(),jp.get("idFornecedor"));
			Assert.assertEquals(valor.tirarDecimais(stringEnviada.getValorSeguro()),jp.get("valorSeguro"));
			Assert.assertEquals(valor.tirarDecimais(stringEnviada.getValorOutros()),jp.get("valorOutros"));
			Assert.assertEquals(valor.tirarDecimais(stringEnviada.getValorFrete()),jp.get("valorFrete"));
			Assert.assertEquals(Arrays.asList(stringEnviada.getParcelas().get(0).getDataVencimento() +" "+ data.retornaHorario()),jp.get("parcelas.dataVencimento"));
			Assert.assertEquals(Arrays.asList((float)stringEnviada.getParcelas().get(0).getValor()),jp.get("parcelas.valor"));
			Assert.assertEquals(stringEnviada.getTipoPlanoPagamento(),jp.get("idPlanoPagamento"));
			Assert.assertEquals(Arrays.asList(stringEnviada.getProdutos().get(0).getProduto().getId()),jp.get("itens.produto.id"));
			Assert.assertEquals(Arrays.asList(stringEnviada.getProdutos().get(0).getProduto().getCodigoSistema()),jp.get("itens.produto.codigoSistema"));
			Assert.assertEquals(Arrays.asList(valor.tirarDecimais(stringEnviada.getProdutos().get(0).getQuantidade())),jp.get("itens.quantidade"));
			Assert.assertEquals(Arrays.asList(valor.tirarDecimais(stringEnviada.getProdutos().get(0).getValorDesconto())),jp.get("itens.valorDesconto"));
			Assert.assertEquals(Arrays.asList((float)stringEnviada.getProdutos().get(0).getValorUnitario()),jp.get("itens.valorUnitario"));
			Assert.assertEquals(Arrays.asList(valor.tirarDecimais((float)stringEnviada.getProdutos().get(0).getIdOperacao())),jp.get("itens.idOperacao"));
			Assert.assertEquals(statusPedido,jp.get("status"));

			System.out.println("Validacao no GET: \n - Data pedido de compra \n - Previsao de entrega \n - ID Entidade"
					+ " \n - Valor seguro \n - Valor outros \n - Valor frete \n - Data vencimento parcela"
					+ " - Valor da parcela \n - ID plano pagamento \n - ID produto \n - Codigo do produto \n - Nome do produto"
					+ " \n - Quantidade do produto \n - Valor desconto \n - Valor unitario \n - ID operacao");

		} else {
			fail("Status diferente de 200 ou 201");
		}
	}

	public PedidoCompra alterarPedidoCompra(PedidoCompra postFinal){
		putFinal = postFinal;

		// MUDANÇAS NO PEDIDO COMPRA - ALTERADA QUANTIDADE DE 2 PARA 5
		putFinal.getProdutos().get(0).setQuantidade(5);

		// AJUSTADO VALOR TOTAL DA PARCELA
		putFinal.getParcelas().clear();

		Parcelas parcelas = new Parcelas(data.calcularData(3, false), 54.95);
		putFinal.setParcelas(new ArrayList<Parcelas>());
		putFinal.getParcelas().add(parcelas);

		return putFinal;
	}
}