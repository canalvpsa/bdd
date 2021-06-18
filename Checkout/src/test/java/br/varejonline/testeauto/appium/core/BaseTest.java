package br.varejonline.testeauto.appium.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.varejonline.testeauto.appium.page.LoginPage;

public class BaseTest {

	private LoginPage login = new LoginPage();
	
	@Rule
	public ScreenShotOnFailure failure = new ScreenShotOnFailure(DriverFactory.getDriver());
	

	@ClassRule
	public static TestName testName = new TestName();

	@Before
	public void selecionarLoja() {
		login.selecionarLoja();
	}

	@After
	public void tearDown() {
		DriverFactory.getDriver().resetApp();
	}

	@AfterClass
	public static void finalizaClasse() {
		// gerarScreenShot();
		DriverFactory.killDriver();
	}

	public static void gerarScreenShot() {
		try {
			File imagem = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(imagem, new File("target/screenshots/" + testName.getMethodName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void esperar(long tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
