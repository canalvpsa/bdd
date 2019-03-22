package classesAuxiliaresGeral;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.fail;

import com.jayway.restassured.response.Response;

public class Resposta {

	public String idRecurso;
	public static String jsonAsString;

	public String post(String json, String URL, String token, String API) {

		System.out.println("\n");
		System.out.println("POST - Cadastro de " + API);
		System.out.println("POST:" + URL + "?token=" + token);
		System.out.println("JSON:" + json);
		System.out.println("Retorno da API:");
		jsonAsString = null;

		Response responsePost = (Response) given().contentType("application/json")
				.when()
				.body(json)
				.with()
				.when()
				.post(URL + "?token=" + token)
				.then()
				.assertThat()
				.log()
				.body()
				.extract();
		jsonAsString = responsePost.asString();

		if (responsePost.getStatusCode() == 200 || responsePost.getStatusCode() == 201) {

			switch (API) {
				case "Produtos":
					idRecurso = jsonAsString.replaceAll("\"mensagem\":\"Operacao realizada com sucesso\"" + ",\"codigoMensagem\":0,\"idRecurso\"",
							"");
					idRecurso = idRecurso.replaceAll("[^0-9]", "");
					break;

				case "Servicos":
					idRecurso = jsonAsString.replaceAll("\"mensagem\":\"Operacao realizada com sucesso\"" + ",\"codigoMensagem\":0,\"idRecurso\"",
							"");
					idRecurso = idRecurso.replaceAll("[^0-9]", "");
					break;

				case "PedidoCompra":
					idRecurso = jsonAsString;
					System.out.println("Retorno:" + jsonAsString);
					break;

				case "Orcamentos":
					idRecurso = jsonAsString.replaceAll("\"mensagem\":\"Operacao realizada com sucesso\"" + ",\"codigoMensagem\":0,\"idRecurso\"",
							"");
					idRecurso = idRecurso.replaceAll("[^0-9]", "");
					break;

				case "pedidos":
					idRecurso = jsonAsString;
					System.out.println("Retorno:" + jsonAsString);
					break;

				case "Terceiros":
					idRecurso = jsonAsString.replaceAll("[^0-9]", "");
					System.out.println("Retorno:" + jsonAsString);
					break;

				default:
					System.out.println("API nao prevista no codigo");
					break;
			}

		} else {
			System.out.println("Retorno:" + jsonAsString);
			fail("Status diferente de 200 ou 201");
		}
		return idRecurso;
	}

	public void put(String json, String URL, String token, String idRecurso, String API) {

		System.out.println("\n");
		System.out.println("PUT - Cadastro de " + API);
		System.out.println("PUT:" + URL + "?token=" + token);
		System.out.println("JSON:" + json);
		System.out.println("Retorno da API:");
		jsonAsString = null;
		
		System.out.println("\n");
		Response responsePut = given().contentType("application/json").when().body(json).when().put(URL + "/" + idRecurso + "?token=" + token);

		System.out.println(json);
		
		jsonAsString = responsePut.asString();
		System.out.println("Retorno:" + jsonAsString);

		if (responsePut.getStatusCode() == 200 || responsePut.getStatusCode() == 201) {
			System.out.println("PUT realizado com sucesso");
		} else {
			fail("Status diferente de 200 ou 201");
		}
	}

	public void cancelar(String URL, String token, String idRecurso, String API) {

		Response responsePost = (Response) given().contentType("application/json")
				.when()
				.with()
				.when()
				.post(URL + "/" + idRecurso + "/cancelar?token=" + token)
				.then()
				.assertThat()
				.log()
				.body()
				.extract();
		jsonAsString = responsePost.asString();

		if (responsePost.getStatusCode() == 200 || responsePost.getStatusCode() == 201) {

			switch (API) {

				case "pedidoVenda":
					System.out.println("Pedido venda cancelado com sucesso");
					break;

				default:
					System.out.println("Retorno para API não encontrada");
					break;
			}

		} else {
			System.out.println("Retorno:" + jsonAsString);
			fail("Status diferente de 200 ou 201");
		}
	}

	public String getIdRecurso() {

		return idRecurso;
	}

	public void setIdRecurso(String idRecurso) {

		this.idRecurso = idRecurso;
	}
}