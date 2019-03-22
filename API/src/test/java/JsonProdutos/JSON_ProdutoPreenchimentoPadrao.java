package JsonProdutos;

import java.util.ArrayList;

import org.hamcrest.Matcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import classesAuxiliaresGeral.Resposta;
import classesAuxiliaresProdutos.Categoria;
import classesAuxiliaresProdutos.DadosPorEntidade;
import classesAuxiliaresProdutos.PrecoCusto;
import classesAuxiliaresProdutos.Produto;
import classesAuxiliaresProdutos.ProdutoBase;
import classesAuxiliaresProdutos.ValorAtributos;

public class JSON_ProdutoPreenchimentoPadrao {

	Produto produto = new Produto();
	PrecoCusto listPrecoCusto1 = new PrecoCusto(2,50);
	PrecoCusto listPrecoCusto2 = new PrecoCusto(14,50.50f);

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

	Resposta resposta = new Resposta();
	Matcher<? super Integer> Status;
	public static String jsonAsString;
	String jsonProduto, prettyJson;
	ObjectMapper mapper;

	public void produtoSimples(String descricaoBase, String codigoInternoBase) {

		produto.setDescricao(descricaoBase);
		produto.setMetodoControle("ESTOCAVEL");
		produto.setCodigoNcm("2522.20.00");
		produto.setCodigoInterno(codigoInternoBase);
		produto.setOrigem(0);
		produto.setUnidade("UN");
		produto.setClassificacao("REVENDA");
		produto.setCustoReferencial(50.12f);

		produto.setListPrecoCusto(new ArrayList<PrecoCusto>());

		produto.getListPrecoCusto().add(listPrecoCusto1);
		produto.getListPrecoCusto().add(listPrecoCusto2);

		produto.setPreco(115.40f);
		produto.setDescontoMaximo(99.99f);
		produto.setComissao(3);
		produto.setEstoqueMinimo(2);
		produto.setEstoqueMaximo(10);
		produto.setAtivo(true);

		produto.setCategorias(new ArrayList<Categoria>());
		categorias1.setNome("PERMANENTE");
		categorias1.setNivel("COLECAO");

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

		categorias11.setNome("CATEGORIA COM CLASSIFICAÇÃO FISCAL");
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
	}

	public void produtoGrade(String descricaoBase, String codigoInternoBase, String descricaoGrade, String codigoInternoGrade) {

		produtoSimples(descricaoBase, codigoInternoBase);
		produto.setDescricao(descricaoGrade);
		produto.setCodigoInterno(codigoInternoGrade);

		ValorAtributos atributos1 = new ValorAtributos();
		ValorAtributos atributos2 = new ValorAtributos();
		produto.setValorAtributos(new ArrayList<ValorAtributos>());

		atributos1.setNome("cor");
		atributos1.setValor("AZUL");
		atributos1.setCodigo("");

		atributos2.setNome("tamanho");
		atributos2.setValor("P");
		atributos2.setCodigo("");

		produto.getValorAtributos().add(atributos1);
		produto.getValorAtributos().add(atributos2);

		DadosPorEntidade dadosPorEntidade1 = new DadosPorEntidade();
		DadosPorEntidade dadosPorEntidade2 = new DadosPorEntidade();

		produto.setDadosPorEntidade(new ArrayList<DadosPorEntidade>());

		dadosPorEntidade1.setEntidade(1);
		dadosPorEntidade1.setEstoqueMaximo(15);
		dadosPorEntidade1.setEstoqueMinimo(5);

		dadosPorEntidade2.setEntidade(6);
		dadosPorEntidade2.setEstoqueMaximo(16);
		dadosPorEntidade2.setEstoqueMinimo(6);

		produto.getDadosPorEntidade().add(dadosPorEntidade1);
		produto.getDadosPorEntidade().add(dadosPorEntidade2);

		ProdutoBase produtoBase = new ProdutoBase(0, codigoInternoBase, descricaoBase);
		produto.setProdutoBase(produtoBase);
	}
}