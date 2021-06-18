package br.varejonline.testeauto.appium.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.varejonline.testeauto.appium.core.BaseTest;
import br.varejonline.testeauto.appium.page.AlertaPage;
import br.varejonline.testeauto.appium.page.AtendimentoPage;
import br.varejonline.testeauto.appium.page.CarrinhoPage;
import br.varejonline.testeauto.appium.page.InicialPage;
import br.varejonline.testeauto.appium.page.LoginPage;
import br.varejonline.testeauto.appium.page.MenuHorizontalPage;
import br.varejonline.testeauto.appium.page.ProdutoPage;

public class LoginTeste extends BaseTest{

	private static LoginPage login = new LoginPage();
	private AlertaPage alerta = new AlertaPage();
	private InicialPage menu = new InicialPage();
	private AtendimentoPage atendimento = new AtendimentoPage();
	private ProdutoPage produto = new ProdutoPage();
	private MenuHorizontalPage menuhorizontal = new MenuHorizontalPage();
	private CarrinhoPage carrinho = new CarrinhoPage();
	   
	@After
	public void logout(){
		menu.logout();
	}
	
	
	public void cancelarAtendimento() {
	//	atendimento.ultimoAtendimento
		//atendimento.clicarCancelar
		//atendimento.motivo
		//atendimento.observacao
		//atendimento.confirmarCancelamento
		//atendimento.OK
	}
	
	
	@Test
	public void LoginNaoPermitido() {
		login.setUsuario("comprador");
		login.setSenha("varejonline");
		login.entrar();
		Assert.assertEquals("Atenção", alerta.obterTextoTitulo());
		Assert.assertEquals("Login ou senha inválidos", alerta.obterTextoMensagem());
		alerta.clicarOK();
	}
	
	
	@Test
	public void LoginVendedorInativo() {
		login.setUsuario("vendedorinativo");
		login.setSenha("varejonline");
		login.entrar();
		Assert.assertEquals("Atenção", alerta.obterTextoTitulo());
		Assert.assertEquals("Login ou senha inválidos", alerta.obterTextoMensagem());
		alerta.clicarOK();
	}
	
	
	@Test
	public void LoginVendedorNaoAutorizadoVenda() {
		login.setUsuario("naovendepdv");
		login.setSenha("varejonline");
		login.entrar();
	//	Assert.assertEquals("Confirmado", page.obterMensagemAlerta());
		
		//PODE LOGAR? OU SÓ NÃO PODE SER VINCULADO???
		
	}
	
	@Test
	public void LoginVendedorPerfilVendedor() {
		login.setUsuario("realizado");
		login.setSenha("varejonline");
		login.entrar();
		
		atendimento.setApelido("login vendedor");
		atendimento.salvar();
		produto.pesquisarProdutoCodigo("");
		produto.incluirCarrinho();
		menuhorizontal.acessarCarrinho();
		Assert.assertFalse(carrinho.botaoPagamento());
		cancelarAtendimento();
	}

	@Test
	public void LoginVendedorPerfilGerente() {
		login.setUsuario("gerente");
		login.setSenha("varejonline");
		login.entrar();
		
		atendimento.setApelido("login gerente");
		atendimento.salvar();
		produto.pesquisarProdutoCodigo("");
		produto.incluirCarrinho();
		menuhorizontal.acessarCarrinho();
		Assert.assertTrue(carrinho.botaoPagamento());
		cancelarAtendimento();
	}
	
	@Test
	public void LoginVendedorPerfilCaixa() {
		login.setUsuario("pdv1");
		login.setSenha("varejonline");
		login.entrar();
		menu.logout();
		
		atendimento.setApelido("login caixa");
		atendimento.salvar();
		produto.pesquisarProdutoCodigo("");
		produto.incluirCarrinho();
		menuhorizontal.acessarCarrinho();
		Assert.assertTrue(carrinho.botaoPagamento());
		cancelarAtendimento();
	}
}
