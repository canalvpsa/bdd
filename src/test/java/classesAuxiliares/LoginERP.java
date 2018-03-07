package classesAuxiliares;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginERP {

	public WebDriver driver;
	public String url = null;
	
	public void iniciandoChrome() {
		ChromeOptions options = new ChromeOptions();
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("profile.default_content_setting_values.plugins", 1);
	    prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
	    prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
	    // Enable Flash for this site
	    prefs.put("PluginsAllowedForUrls", "https://qa.varejonline.com.br:7443");
	    options.setExperimentalOption("prefs", prefs);

		
		url = System.getProperty("url");
		if (url == null) url = "https://qa.varejonline.com.br:7443";
		
		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
		System.out.println("Iniciando o Chrome");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
	}

	@Test
	public void abriERP(){
		iniciandoChrome();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.navigate().to(url+"/erp");
		String strPageTitle = driver.getTitle();
		System.out.println("Pagina: - "+strPageTitle);
		
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("varejonline");
		driver.findElement(By.id("cnpj")).sendKeys("qa_vpsa");
		driver.findElement(By.id("login-submit")).click();
		
	}
	
//	@After
	public void sairERP() {
		if(driver!=null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}

	
	public String getUrl() {
	
		return url;
	}

	
	public void setUrl(String url) {
	
		this.url = url;
	}
}