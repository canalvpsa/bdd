package br.varejonline.testeauto.appium.page;

import org.openqa.selenium.By;

import br.varejonline.testeauto.appium.core.BasePage;

public class CarrinhoPage extends BasePage {
	
	public boolean botaoPagamento() {
		return existeElemento(By.id(""));
	}

}
