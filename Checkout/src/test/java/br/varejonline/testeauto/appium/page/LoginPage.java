package br.varejonline.testeauto.appium.page;

import org.openqa.selenium.By;

import br.varejonline.testeauto.appium.core.BasePage;


public class LoginPage extends BasePage {
	
	
	public void selecionarLoja() {
		clicar(By.id("br.com.varejonline.staging:id/row_shop__swipeLayout"));
		clicar(By.id("br.com.varejonline.staging:id/activity_setup__button_continue"));
	}
	

	public void setUsuario(String usuario) {
		escrever(By.id("br.com.varejonline.staging:id/activity_login__edittext_email"), usuario);
	} 
	
	public void setSenha(String senha) {
		escrever(By.id("br.com.varejonline.staging:id/activity_login__edittext_password"), senha);
	} 
	
	public void entrar() {
		clicar(By.id("br.com.varejonline.staging:id/activity_login__button_login"));
	}
}
