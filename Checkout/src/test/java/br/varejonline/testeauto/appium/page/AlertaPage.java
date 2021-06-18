package br.varejonline.testeauto.appium.page;


import org.openqa.selenium.By;

import br.varejonline.testeauto.appium.core.BasePage;
import br.varejonline.testeauto.appium.core.DriverFactory;

public class AlertaPage extends BasePage {
	
	public String obterTextoTitulo(){
		return DriverFactory.getDriver().findElement(By.id("(br.com.varejonline.staging:id/md_title")).getText();
	}
	
	public String obterTextoMensagem(){
		return DriverFactory.getDriver().findElement(By.id("(br.com.varejonline.staging:id/md_content")).getText();
	}
	
	public void clicarOK(){
		clicar(By.id("br.com.varejonline.staging:id/md_buttonDefaultPositive"));
	}
}
