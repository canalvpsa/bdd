package classesAuxiliares;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.Settings;

import DAO.impl.Maquina;
import DAO.impl.TipoImpressora;
import sincronismoUpdateManager.AcoesPDV;

public class PrepararPDV {
	private static PrepararPDV instancia = new PrepararPDV();
	private AcoesPDV statusPDV = AcoesPDV.getInstance();
	private ValidacoesECF ECF = ValidacoesECF.getInstance();

	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	private TipoImpressora BDtipoImpressora = new TipoImpressora();
	private Maquina BDmaquina = new Maquina();
	private Screen s = new Screen();

	private Pattern m_loginPDV = new Pattern(getImage("imgGeral/loginPDV.png")).similar(0.90f);
	private Pattern m_emAtendimento = new Pattern(getImage("imgOrcamentoPedido/emAtendimento.png")).similar(0.98f);
	private Pattern m_PDVabrindo = new Pattern(getImage("imgGeral/PDVabrindo.png")).similar(0.98f);
	private Pattern m_loginSenha = new Pattern(getImage("imgGeral/loginSenha.png")).similar(0.85f);
	private Pattern m_loginUsuario = new Pattern(getImage("imgGeral/loginUsuario.png")).similar(0.85f);
	private Pattern m_loginUsuarioVazio = new Pattern(getImage("imgGeral/loginUsuarioVazio.png")).similar(0.55f);
	private Pattern m_abrirCaixa = new Pattern(getImage("imgGeral/abrirCaixa.png")).similar(0.90f);
	String imageString, docFiscalParametro, docFiscal;
	boolean PDVaberto;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static PrepararPDV getInstance(){
		if (instancia ==null){
			instancia = new PrepararPDV();
		}
		return instancia;
	}


	public void verificaSituacaoPDV(String docFiscal) throws InterruptedException, FindFailed, IOException, ClassNotFoundException, SQLException{
		Settings.ActionLogs = false;

		if(BDtipoImpressora.getTipoImpressora(docFiscal) == false){
			BDtipoImpressora.atualizarTipoImpressora(docFiscal);
			BDmaquina.atualizarAtualizaecf();
			statusPDV.fecharPDV();
			File diretorio = new File("C:/VPSA/PDV/config"); 
			FileFilter ff = new FileFilter() { 
				public boolean accept(File arquivo){ 
					return arquivo.getName().endsWith(".txt");
				} 
			}; 
			File[] arquivos = diretorio.listFiles(ff); 
			if(arquivos != null){ 
				for(File arquivo : arquivos){ 
					arquivo.delete();  
				} 
			}
		}

		if(s.exists(m_abrirCaixa) == null){	

			//Verifica se tem PDV em andamento
			if (statusPDV.processoPDVandamento() != null) {
				PDVaberto = true;

				//Se nao conseguir focar no PDV, deve finalizar o processo dele
				if (App.focus("PDV 4.0") == null) {
					statusPDV.fecharPDV();
					PDVaberto = false;
				}
			}

			if (PDVaberto == false) {
				iniciarPDV();
			}

			// SE ESTIVER NA TELA DE VENDA, CANCELA O PEDIDO
			if (s.exists(m_emAtendimento) != null) {
				cadastro.sairPedido();
			}
		}
	}



	public void iniciarPDV() throws InterruptedException, FindFailed, IOException, ClassNotFoundException, SQLException{
		Settings.ActionLogs = false;
		s.wait(5.0);
		//Inicia PDV
		statusPDV.abrePDV();
		
		while (s.exists(m_PDVabrindo) != null) {
		}

		//Se trabalhar com ECF, deve validar mensagens da impressora
		if(BDtipoImpressora.getTipoImpressora("SWEDA") == true)
			ECF.validaECFaberturaPDV();

		s.wait(m_loginPDV, 300);
		login();
	}



	public void login() throws FindFailed, ClassNotFoundException, SQLException{
		if (App.focus("PDV 4.0") != null) {

			// LOGAR SE O TERMINAL ESTIVER FECHADO
			if (s.exists(m_loginSenha) != null) {
				s.exists(m_loginSenha);

				if (App.focus("PDV 4.0") != null) {

					if (s.exists(m_loginUsuario) != null) {
						s.type(Key.TAB);
					} else {
						s.click(m_loginUsuarioVazio);
						s.type("cxsp");
					}

					s.type(Key.ENTER);
					s.type("varejonline");
					s.type(Key.ENTER);

					// VALIDA SE ESTA NA TELA PRINCIPAL DO PDV
					s.wait(m_abrirCaixa, 10.0);

					// VERIFICA SE TEM REDCAO Z PENDENTE
					s.wait(2.0);
					if(BDtipoImpressora.getTipoImpressora("SWEDA") == true)
						ECF.verificaReducaoZPendente();
				}
			}
		}
	}
}
