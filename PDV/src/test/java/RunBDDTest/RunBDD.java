package RunBDDTest;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.sikuli.script.FindFailed;

import abrirModulos.AbrirManagerSincronismo;
import classesAuxiliares.AfterTeste;
import classesAuxiliares.PrepararPDV;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import sincronismoUpdateManager.AcoesPDV;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/"},
		glue = "steps",
		tags = {"@executavel"},
		plugin = {"pretty", "html:target/report-html", "json:target/report.json,"
				+ "junit:target/cucumber-reports/Cucumber.xml"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false
		)

public class RunBDD {
	static PrepararPDV verificaPDV = PrepararPDV.getInstance();
	private AcoesPDV statusPDV = AcoesPDV.getInstance();
	private AfterTeste validaFinal = AfterTeste.getInstance();
	private AbrirManagerSincronismo sincronismo = AbrirManagerSincronismo.getInstance();
	static String docFiscalParametro;

	@BeforeClass
	public static void prepararPDV() throws FindFailed, ClassNotFoundException, InterruptedException, IOException, SQLException{
		docFiscalParametro = System.getProperty("docFiscal");
		String docFiscal = "SWEDA";

		if(docFiscalParametro != null)
			docFiscal = docFiscalParametro;

		System.out.println("PDV configurado para:" + docFiscal);
		verificaPDV.verificaSituacaoPDV(docFiscal);
	}	

	@After
	public void finalizando() throws ClassNotFoundException, FindFailed, InterruptedException, SQLException, IOException{
		finalizando();
		sincronismo.sincronizar();
		statusPDV.fecharPDV();
	}


	public void finalizando(Scenario scenario) throws ClassNotFoundException, FindFailed, InterruptedException, SQLException, IOException{
		System.out.println(scenario);
		System.out.println(this.getClass().getName());
		validaFinal.validaExecucao(scenario, this.getClass().getName());
	}
}
