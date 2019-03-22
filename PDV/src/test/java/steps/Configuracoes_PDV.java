package steps;

import java.io.IOException;
import java.sql.SQLException;

import org.sikuli.script.FindFailed;

import DAO.impl.Configuracao;
import classesAuxiliares.PrepararPDV;
import cucumber.api.java.pt.Dado;
import sincronismoUpdateManager.AcoesPDV;

public class Configuracoes_PDV {
	private static Configuracoes_PDV instancia = new Configuracoes_PDV();
	private AcoesPDV statusPDV = AcoesPDV.getInstance();
	private Configuracao BDconfiguracao = new Configuracao();
	private PrepararPDV prepararPDV = PrepararPDV.getInstance();

	public static Configuracoes_PDV getInstance(){
		if (instancia ==null){
			instancia = new Configuracoes_PDV();
		}
		return instancia;
	}

	@Dado("^que o parâmetro de enviar serviço para documento fiscal está habilitado$")
	public void que_o_parâmetro_de_enviar_serviço_para_documento_fiscal_está_habilitado() throws Throwable {
		verificaParametro("ENVIA_SERVICO_PARA_DOCUMENTO_FISCAL", "Y");
	}  

	@Dado("^que o parâmetro para permitir saldo negativo está habilitado$")
	public void queOParâmetroParaPermitirSaldoNegativoEstáHabilitado() throws Throwable {
		verificaParametro("PDV_PERMITE_ESTOQUE_NEGATIVO", "Y");
	}

	@Dado("^que o parâmetro para permitir saldo negativo está desabilitado$")
	public void que_o_parâmetro_para_permitir_saldo_negativo_está_desabilitado() throws Throwable {
		verificaParametro("PDV_PERMITE_ESTOQUE_NEGATIVO", "N");
	}

	public void verificaParametro(String configuracao, String valorEsperado) throws ClassNotFoundException, FindFailed, SQLException, InterruptedException, IOException{
		if(!BDconfiguracao.getConfiguracao(configuracao).equals(valorEsperado)){
			BDconfiguracao.atualizarConfiguracao(configuracao, valorEsperado);
			System.out.println(configuracao+ ": Atualizado para:" +valorEsperado);

			statusPDV.fecharPDV();
			prepararPDV.abrePDVnovamente();
		}else{
			System.out.println(configuracao+ ": ja configurado para:" +valorEsperado);
		}
	}
}
