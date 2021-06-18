package br.varejonline.testeauto.appium.page;

import org.openqa.selenium.By;

import br.varejonline.testeauto.appium.core.BasePage;

public class ProdutoPage extends BasePage {
	
	public void pesquisarProdutoCodigo(String codigo) {
		clicar(By.id("br.com.varejonline.staging:id/fragment_products_search__imageview_search"));
		escrever(By.id("br.com.varejonline.staging:id/fragment_products_search__edittext_searchterm"), codigo);
	}
	
	public void incluirCarrinho() {
		clicar(By.id("br.com.varejonline.staging:id/row_product__imageview_addcart"));
	}
}
