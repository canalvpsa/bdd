package steps;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.sikuli.script.FindFailed;

import abrirModulos.AbrirManagerSincronismo;
import classesAuxiliares.AfterTeste;
import classesAuxiliares.PrepararPDV;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import sincronismoUpdateManager.AcoesPDV;


public class Hooks {
	static PrepararPDV verificaPDV = PrepararPDV.getInstance();
	private AcoesPDV statusPDV = AcoesPDV.getInstance();
	private AfterTeste validaFinal = AfterTeste.getInstance();
	private AbrirManagerSincronismo sincronismo = AbrirManagerSincronismo.getInstance();
	static String docFiscalParametro;

	@Before
	public static void prepararPDV() throws FindFailed, ClassNotFoundException, InterruptedException, IOException, SQLException{
		docFiscalParametro = System.getProperty("docFiscal");
		String docFiscal = "DEMONSTRACAO";

		if(docFiscalParametro != null)
			docFiscal = docFiscalParametro;

		System.out.println("PDV configurado para:" + docFiscal);
		verificaPDV.verificaSituacaoPDV(docFiscal);
	} 

	@After
	public void finalizando(Scenario scenario) throws ClassNotFoundException, FindFailed, InterruptedException, SQLException, IOException{
		validaFinal.validaExecucao(scenario, this.getClass().getName());
	}

	@AfterClass
	public void depoisCenarios() throws ClassNotFoundException, FindFailed, InterruptedException, SQLException, IOException{
		sincronismo.sincronizar();
		statusPDV.fecharPDV();
	}
}