package br.com.vpsa.ERPautomatizado;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import classesAuxiliares.BancoDadosERP;
import classesAuxiliares.BuscaRecebimentoEstoque;
import classesAuxiliares.LoginERP;

public class EntradaNotaAngular {
	
	public LoginERP chrome = new LoginERP();
	public BancoDadosERP bd = new BancoDadosERP();

	@SuppressWarnings("deprecation")
	public Timeout globalTimeout = new Timeout(600000);
	

	@Before
	public void dadosNecessarios() throws Exception{	
		chrome.abriERP();
	}
	
	@Test
	public void EfetuarEntradaComConciliacaoAutomatica() throws Exception{
		InserirXML("42181075552133001303550040000004431110520775", 9, 1);
	}
	
	@Test
	public void EfetuarEntradaConciliandoCincoItensManualmente() throws Exception{
		InserirXML("42181075552133001303550040000004431110520775", 9, 1);
	}
	
	@Test
	public void EfetuarEntradaConciliandoNoveItensManualmente() throws Exception{
		InserirXML("42181075552133001303550040000004431110520775", 9, 1);
	}
	
	public void InserirXML(String chave, int quantidadeConciliar, int tempoTransicaoTela) throws Exception {
		WebDriverWait wait = new WebDriverWait(chrome.driver, 20);
		String notaNumero = chave.substring(25, 34);
		String notaSerie = chave.substring(22, 25);
		System.out.println("Nota - Número: "+notaNumero+" - Série: "+notaSerie);
		verificarExistenciaDeNota(notaNumero, notaSerie);
		chrome.driver.navigate().to(chrome.url+"/server/erp/estoque/entradas");
		chrome.driver.findElement(By.xpath("//button[@class='btn btn-secondary btn-primary']/span[1]")).click();
		chrome.driver.findElement(By.xpath("//div[@class='form-group col-sm-4 offset-2']/button[@class='btn btn-default btn-block' and 1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='close pull-right']/span")));
		chrome.driver.findElement(By.xpath("//div/input[@class='form-control' and 1]")).click();
		TimeUnit.SECONDS.sleep(3);
		String libPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		libPath = libPath.replace("/C:/", "C:/");
		libPath = libPath.replace("/", "\\");
		StringSelection ss = new StringSelection(libPath + "..\\..\\lib\\" + chave + ".xml");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	    Robot robot = new Robot();
	    boolean success = false;
	    while(success == false){
		    try{
			    robot.keyPress(KeyEvent.VK_CONTROL);
			    robot.keyPress(KeyEvent.VK_V);
			    robot.keyRelease(KeyEvent.VK_V);
			    robot.keyRelease(KeyEvent.VK_CONTROL);
			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);
			    WebElement btn_codigo_nota = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/label[2]/input[1]")));
			    btn_codigo_nota.click();
			    WebElement btn_codigo_sistema = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[4]/input[1]")));
			    btn_codigo_sistema.click();
				WebElement btn_importar_xml = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='modal-footer']/button[@class='btn btn-primary']")));
				btn_importar_xml.click();
				success = true;
		    }catch (TimeoutException t){
		    	
		    }
	    }
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Escolher arquivo']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span/button[@class='btn btn-primary' and 1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='modal-open']")));
		WebElement btn_operacao = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-vo-filtro-operacoes[1]/app-vo-select[1]/span[1]/span[1]/span[1]/span[2]/b[1]")));
		btn_operacao.click();
	    chrome.driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("compra p/ comercia");
		WebElement compra_comercializacao = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Compra p/ comercialização']")));
		compra_comercializacao.click();
		WebElement btn_entidade = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/app-vo-select[1]/span[@class='select2 select2-container select2-container--default' and 1]/span[@class='selection' and 1]/span[@class='select2-selection select2-selection--single' and 1]/span[2]")));
		btn_entidade.click();
		chrome.driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("TUAREG - BRUSQUE");
		chrome.driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(Keys.ENTER);
		WebElement btn_salvar_etapa_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
		btn_salvar_etapa_1.click();
		assertTrue(true);
	    //chrome.driver.findElement(By.xpath("")).click();
		TimeUnit.SECONDS.sleep(tempoTransicaoTela);
	    this.conciliarItens(quantidadeConciliar);
		TimeUnit.SECONDS.sleep(tempoTransicaoTela);
	    this.dadosAdicionais();
		TimeUnit.SECONDS.sleep(tempoTransicaoTela);
	    this.resumoProdutos();
		TimeUnit.SECONDS.sleep(tempoTransicaoTela);
	    this.visaoGeral();
		TimeUnit.SECONDS.sleep(tempoTransicaoTela);
	    this.Pagamento();
	}
	
	public void conciliarItens(int quantidadeConciliar){
		System.out.println("conciliarItens() called");
		WebDriverWait wait = new WebDriverWait(chrome.driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='block-ui-wrapper-custom']")));
		
		int item_linha = 0;
		String item_atual;
		while (item_linha != 1){ //Vai verificar se a "lixeira" existe, se existir vai clicar
			item_linha++;
			item_linha++;
			item_atual = "//tr["+item_linha+"]/td[@class='text-center' and 5]/button[@class='btn btn-default' and 1]";
			try {
				if(quantidadeConciliar != 0){
					if((item_linha/2) > quantidadeConciliar){
						break;
					}else{
						if(chrome.driver.findElement(By.xpath(item_atual)).getLocation() != null){
							System.out.println("O produto "+item_linha+" está conciliado.");
							chrome.driver.findElement(By.xpath(item_atual)).click();
						}
						System.out.println("Busca de conciliações automáticas, iteração "+item_linha/2+" de "+quantidadeConciliar);
					}
				}else{
					break;
				}
			}catch (NoSuchElementException e){
				System.out.println("Não foi possível desconciliar o "+item_linha+"---: "+item_atual);
				//e.printStackTrace();
			}
			finally{
			}
		}
		
		item_linha = 0;
		int item_linha_desc = 0;
		String desc_prod;
		while (item_linha != 1){ //Vai verificar se o campo para "digitar" o nome do produto existe, se existir vai clicar
			item_linha++;
			item_linha++;
			item_atual = "//*["+item_linha+"]/td[1]/div[1]/app-vo-filtro-produtos[1]/app-vo-select[1]/span[@class='select2 select2-container select2-container--default' and 1]/span[@class='selection' and 1]/span[@class='select2-selection select2-selection--single' and 1]/span[@class='select2-selection__rendered' and 1]";
			try {
				if(quantidadeConciliar != 0){
					if((item_linha/2) > quantidadeConciliar){
						break;
					}else{
						if(chrome.driver.findElement(By.xpath(item_atual)).getLocation() != null){
							item_linha_desc = item_linha-1;
							desc_prod = chrome.driver.findElement(By.xpath("//tr["+item_linha_desc+"]/td[2]")).getText();
							System.out.println("O item "+item_linha+" não está conciliado: "+desc_prod);
							chrome.driver.findElement(By.xpath(item_atual)).click();
							desc_prod = "TESTE "+desc_prod.split("TESTE ")[1];
							chrome.driver.findElement(By.xpath("//*[@class='select2-search__field']")).sendKeys(desc_prod);
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*["+item_linha+"]/td[5]/button[1]")));
						}
						System.out.println("Tentativa de conciliação, iteração "+item_linha/2+" de "+quantidadeConciliar);
					}
				}else{
					break;
				}

			}catch (NoSuchElementException e){
				System.out.println("Não foi possível conciliar o item: "+item_linha+"---: "+item_atual);
				//e.printStackTrace();
			}
			finally{
			}
		}
		
		WebElement btn_salvar_etapa_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
		btn_salvar_etapa_1.click();
	}
	
	public void dadosAdicionais(){
		System.out.println("dadosAdicionais() called");
		WebDriverWait wait = new WebDriverWait(chrome.driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='block-ui-wrapper-custom']")));
		WebElement btn_salvar_etapa_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
		btn_salvar_etapa_1.click();

	}
	
	public void resumoProdutos(){
		System.out.println("resumoProdutos() called");
		WebDriverWait wait = new WebDriverWait(chrome.driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='block-ui-wrapper-custom']")));
		WebElement btn_salvar_etapa_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
		btn_salvar_etapa_1.click();
	
	}
	
	public void visaoGeral(){
		System.out.println("visaoGeral() called");
		WebDriverWait wait = new WebDriverWait(chrome.driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='block-ui-wrapper-custom']")));
		WebElement btn_salvar_etapa_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
		btn_salvar_etapa_1.click();

	}
	
	public void Pagamento(){
		System.out.println("Pagamento() called");
		WebDriverWait wait = new WebDriverWait(chrome.driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='block-ui-wrapper-custom']")));
		WebElement btn_salvar_etapa_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary']")));
		btn_salvar_etapa_1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='noty_body']")));
	}
	
	public void excluirEntrada(String numeronota){
		int numeroExcluir = Integer.parseInt(numeronota);
		WebDriverWait wait = new WebDriverWait(chrome.driver, 20);
		chrome.driver.navigate().to(chrome.url+"/server/erp/estoque/entradas");
		WebElement btn_filtros = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='fa fa-filter']")));
		btn_filtros.click();
		WebElement filtro_avancado= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-search-plus']")));
		filtro_avancado.click();
		WebElement filtra_doc = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control ng-untouched ng-pristine ng-valid']")));
		filtra_doc.sendKeys(String.valueOf(numeroExcluir));
		chrome.driver.findElement(By.xpath("//button[@class='btn btn-secondary btn-block']")).click();
		chrome.driver.findElement(By.xpath("//*[@class='text-right']")).click();
		chrome.driver.findElement(By.xpath("//button[3]")).click();
		chrome.driver.findElement(By.xpath("//*[text()='Sim']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='noty_body']")));
	}

	public void verificarExistenciaDeNota(String numeroNota, String serieNota) {
		BuscaRecebimentoEstoque Buscador = new BuscaRecebimentoEstoque();
		String resultado = Buscador.pesquisarRecebimento(numeroNota, serieNota);
		if(resultado != null){
			System.out.println("Encontrou o recebimento ID: "+resultado);
			excluirEntrada(numeroNota);
		}
	}
	
	@After
	public void efetuarRecebimento() {
		chrome.sairERP();
	}
}