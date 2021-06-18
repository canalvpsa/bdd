package br.varejonline.testeauto.appium.page;

import org.openqa.selenium.By;

import br.varejonline.testeauto.appium.core.BasePage;

public class InicialPage extends BasePage {
	
	
	public void acessarIniciarAtendimento() {
		clicarPorTexto("Iniciar Atendimento");
	}
	
	
	public void acessarMinhasVendas() {
		clicarPorTexto("Minhas Vendas");
	}

	
	public void acessarConsultarProduto() {
		clicarPorTexto("Consultar Produto");
	}

	
	public void acessarConsultarCliente() {
		clicarPorTexto("Consultar Cliente");
	}
	
	public void acessarMovimentacoes() {
		clicarPorTexto("Movimentações");
	}

	
	public void acessarRankingVendedor() {
		clicarPorTexto("Ranking Vendedor");
	}
	
	public void logout() {
		clicar(By.id("br.com.varejonline.staging:id/action_settings"));
		clicar(By.id("br.com.varejonline.staging:id/md_buttonDefaultPositive"));
	}
}
