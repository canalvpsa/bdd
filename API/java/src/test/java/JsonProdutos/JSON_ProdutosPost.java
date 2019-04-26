package JsonProdutos;

import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vpsa.APIautomatizado.API_Produtos;
import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresJson.JsonFormatar;
import classesAuxiliaresProdutos.DadosPorEntidade;
import classesAuxiliaresProdutos.UnidadesProporcao;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class JSON_ProdutosPost {

	JsonFormatar jsonClasse = new JsonFormatar();

	static String codigoSistema;
	String baseURI, basePath, idRecurso, url, token;
	String descricaoBase, descricaoGrade, codigoInternoBase, codigoInternoGrade;
	boolean grade, minMaxPorEntidade, unidadeEquivalente;
	JSON_ProdutoPreenchimentoPadrao preencher = new JSON_ProdutoPreenchimentoPadrao();

	Resposta resposta = new Resposta();
	Matcher<? super Integer> Status;
	public static String jsonAsString;
	String jsonProduto, prettyJson;
	ObjectMapper mapper;

	public String[] post(String descricaoBase, String descricaoGrade, String codigoInternoBase, String codigoInternoGrade, boolean grade, boolean minMaxPorEntidade, boolean unidadeEquivalente) {

		// Dados acesso
		url = API_Produtos.getURL();
		token = API_Produtos.getToken();

		this.descricaoBase = descricaoBase;
		this.descricaoGrade = descricaoGrade;
		this.codigoInternoBase = codigoInternoGrade;
		this.codigoInternoGrade = codigoInternoGrade;
		this.grade = grade;
		this.minMaxPorEntidade = minMaxPorEntidade;
		this.unidadeEquivalente = unidadeEquivalente;

		preencher.produtoSimples(descricaoBase, codigoInternoBase);

		if (grade == true) {
			preencher.produtoGrade(descricaoBase, codigoInternoBase, descricaoGrade, codigoInternoGrade);
			preencher.produto.setDescricao(descricaoGrade);
			preencher.produto.setCodigoInterno(codigoInternoGrade);
		}

		if (minMaxPorEntidade == true) {
			DadosPorEntidade dadosPorEntidade1 = new DadosPorEntidade();
			DadosPorEntidade dadosPorEntidade2 = new DadosPorEntidade();

			preencher.produto.setDadosPorEntidade(new ArrayList<DadosPorEntidade>());
			dadosPorEntidade1.setEntidade(1);
			dadosPorEntidade1.setEstoqueMaximo(15);
			dadosPorEntidade1.setEstoqueMinimo(5);

			dadosPorEntidade2.setEntidade(6);
			dadosPorEntidade2.setEstoqueMaximo(16);
			dadosPorEntidade2.setEstoqueMinimo(6);

			preencher.produto.getDadosPorEntidade().add(dadosPorEntidade1);
			preencher.produto.getDadosPorEntidade().add(dadosPorEntidade2);

		}

		if (unidadeEquivalente == true) {
			UnidadesProporcao unidadesProporcao = new UnidadesProporcao();
			preencher.produto.setUnidadesProporcao(new ArrayList<UnidadesProporcao>());
			unidadesProporcao.setUnidade("CX");
			unidadesProporcao.setProporcao(5);
			preencher.produto.getUnidadesProporcao().add(unidadesProporcao);
		}
		efetuarPost();

		int recursoInt = Integer.parseInt(resposta.idRecurso);
		String idRecursoBase = Integer.toString(recursoInt);
		String idRecursoGrade = resposta.idRecurso;
		String[] retornos = { idRecursoBase, idRecursoGrade };
		return retornos;
	}

	public void efetuarPost() {

		try {
			mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			jsonProduto = mapper.writeValueAsString(preencher.produto);
			resposta.post(jsonClasse.formatarJson(jsonProduto), url, token, "Produtos");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
