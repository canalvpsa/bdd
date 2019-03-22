package classesAuxiliares;

import static org.junit.Assert.fail;

import java.net.URL;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SairPDV {
	private static SairPDV instancia = new SairPDV();
	private AbrirFecharRotinas cadastro = AbrirFecharRotinas.getInstance();
	
	private String imageString;
	private Screen s = new Screen();

	private Pattern m_emAtendimento = new Pattern(getImage("imgOrcamentoPedido/emAtendimento.png")).similar(0.98f);
	private Pattern m_saidaAplicacao = new Pattern(getImage("imgGeral/saidaAplicacao.png")).similar(0.95f);
	private Pattern m_loginSenha = new Pattern(getImage("imgGeral/loginSenha.png")).similar(0.85f);
	private Pattern m_loginUsuario = new Pattern(getImage("imgGeral/loginUsuario.png")).similar(0.85f);
	private Pattern m_loginUsuarioVazio = new Pattern(getImage("imgGeral/loginUsuarioVazio.png")).similar(0.55f);
	private Pattern m_abrirCaixa = new Pattern(getImage("imgGeral/abrirCaixa.png")).similar(0.90f);
	private Pattern m_nao = new Pattern(getImage("imgGeral/nao.png")).similar(0.95f);
	
	
	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}
	
	public static SairPDV getInstance(){
		if (instancia ==null){
			instancia = new SairPDV();
		}
		return instancia;
	}

	public void verificaSairAplicacao() throws FindFailed {
			if (s.exists(m_saidaAplicacao) != null) {

				if (s.exists(m_nao) != null) {
					s.click(m_nao);
				}
			}
	}

	public void verificaTerminalFechado() {

		System.out.println("Verificando se o Terminal esta fechado");
		try {
			if (App.focus("PDV 4.0") != null) {

				// LOGAR SE O TERMINAL ESTIVER FECHADO
				if (s.exists(m_loginSenha) != null) {

					if (App.focus("PDV 4.0") != null) {
						verificaSairAplicacao();

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
						//		this.verificaReducaoZPendente();


						// SE ESTIVER NA TELA DE VENDA, CANCELA O PEDIDO
						if (s.exists(m_emAtendimento) != null) {
							cadastro.sairPedido();
						}
					}
				}
			}
		} catch (FindFailed e) {
			fail("Erro ao verificar PDV fechado.");
		}
	}
}
