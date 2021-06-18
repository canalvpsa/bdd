package br.varejonline.testeauto.appium.page;

import org.openqa.selenium.By;

import br.varejonline.testeauto.appium.core.BasePage;

public class MenuHorizontalPage extends BasePage{
	
	
	public void acessarCarrinho() {
		clicar(By.id("br.com.varejonline.staging:id/bottom_navigation_item_icon"));
	}

}
