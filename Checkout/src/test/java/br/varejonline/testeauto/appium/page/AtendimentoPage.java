package br.varejonline.testeauto.appium.page;

import org.openqa.selenium.By;
import br.varejonline.testeauto.appium.core.BasePage;

public class AtendimentoPage extends BasePage {
	
	
	public void setApelido(String apelido) {
		apagarTexto(By.id("br.com.varejonline.staging:id/activity_new_call__edittext_name"));
		escrever(By.id("br.com.varejonline.staging:id/activity_new_call__edittext_name"), apelido);
	}
	
	public void salvar() {
		clicarPorTexto("Salvar");
	}
	
	public String ultimoAtendimento() {
			return obterStatusUltimoAtendimento(By.id(""));
		}
	}
	


