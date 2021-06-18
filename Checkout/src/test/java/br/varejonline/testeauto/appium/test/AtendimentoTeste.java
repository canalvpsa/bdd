package br.varejonline.testeauto.appium.test;

import org.junit.Before;
import org.junit.Test;

import br.varejonline.testeauto.appium.page.AtendimentoPage;
import br.varejonline.testeauto.appium.page.InicialPage;
import br.varejonline.testeauto.appium.page.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AtendimentoTeste {

	@SuppressWarnings("unused")
	private AndroidDriver<MobileElement> driver;
	private InicialPage menu = new InicialPage();
	private LoginPage login = new LoginPage();
	private AtendimentoPage atendimento = new AtendimentoPage();

	@Before
	public void setup() {
		login.setUsuario("admin");
		login.setSenha("varejonline");
		login.entrar();
	}


	@Test
	public void criarAtendimentoIdentificandoCliente() {
		menu.acessarIniciarAtendimento();
		atendimento.setApelido("Com identificacao do cliente");
		
		//FALTA VINCULAR CLIENTE E SALVAR
	}
	
	
	@Test
	public void criarAtendimentoCadastrandoCliente() {
		menu.acessarIniciarAtendimento();
		atendimento.setApelido("Atendimento com cliente recem cadastrado");
	//	FALTA CRIAR O CLIENTE E SALVAR
	}
}
