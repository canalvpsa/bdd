package classesAuxiliares;

import java.io.IOException;

import sincronismoUpdateManager.AcoesPDV;

public class FecharPDV {
	private static FecharPDV instancia = new FecharPDV();
	private AcoesPDV PDV = AcoesPDV.getInstance();

	public void finalizaPDV() throws IOException {
		PDV.fecharPDV();
	}
	
	public static FecharPDV getInstance(){
		if (instancia ==null){
			instancia = new FecharPDV();
		}
		return instancia;
	}
}
