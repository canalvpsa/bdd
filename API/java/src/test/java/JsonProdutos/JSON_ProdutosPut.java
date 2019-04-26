package JsonProdutos;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vpsa.APIautomatizado.API_Produtos;
import classesAuxiliaresGeral.Resposta;

public class JSON_ProdutosPut {

	JSON_ProdutosGet getProduto = new JSON_ProdutosGet();
	JSON_ProdutoPreenchimentoPadrao preencherProdutos = new JSON_ProdutoPreenchimentoPadrao();
	Resposta resposta = new Resposta();
	String token, URL, idRecurso, jsonAsString, novaDescricao, codigoInterno;
	static String codigoSistema;

	public void putDescricaoProduto(String idRecurso, String codigoInterno, String novaDescricao) {

		// Dados acesso
		URL = API_Produtos.getURL();
		token = API_Produtos.getToken();

		this.idRecurso = idRecurso;
		this.codigoInterno = codigoInterno;
		this.novaDescricao = novaDescricao;

		this.novaDescricao = novaDescricao;
		System.out.println("\n PUT PRODUTO - Alterando descricao do produto");
		System.out.println("PUT:" + URL + "/" + idRecurso + "?token=" + token);

		codigoSistema = JSON_ProdutosGet.getCodigoSistema();

		preencherProdutos.produtoSimples(novaDescricao, codigoInterno);
		efetuarPut();
	}

	public void efetuarPut() {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			String jsonProduto;
			jsonProduto = mapper.writeValueAsString(preencherProdutos.produto);

			Object jsonObject = null;
			jsonObject = mapper.readValue(jsonProduto, Object.class);
			String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
			System.out.println(prettyJson);
			resposta.put(jsonProduto, URL, token, idRecurso, "Produtos");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
