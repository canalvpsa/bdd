package classesAuxiliares;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class PedidoPagamento {
	private static PedidoPagamento instancia = new PedidoPagamento();
	private Screen s = new Screen();
	private String imageString;
	private Pattern m_totalReceber = new Pattern(getImage("imgTotais/totalReceber.png")).similar(0.99f);

	private Pattern m_pagamentoVenda3762 = new Pattern(getImage("imgTotais/pagamentoVenda37.62.png")).exact();
	private Pattern m_pagamentoVenda4180 = new Pattern(getImage("imgTotais/pagamentoVenda41.80.png")).exact();
	private Pattern m_pagamentoVenda5000 = new Pattern(getImage("imgTotais/pagamentoVenda50.00.png")).exact();
	private Pattern m_pagamentoVenda5990 = new Pattern(getImage("imgTotais/pagamentoVenda59.90.png")).exact();
	private Pattern m_pagamentoVenda8360 = new Pattern(getImage("imgTotais/pagamentoVenda83.60.png")).exact();
	private Pattern m_pagamentoVenda9180 = new Pattern(getImage("imgTotais/pagamentoVenda91.80.png")).exact();
	private Pattern m_pagamentoVenda28080 = new Pattern(getImage("imgTotais/pagamentoVenda280.80.png")).exact();
	private Pattern m_pagamentoVenda28080comTroco = new Pattern(getImage("imgTotais/pagamentoVenda280.80comTroco.png")).exact();
	private Pattern m_pagamentoVenda28080crediario = new Pattern(getImage("imgTotais/pagamentoVenda280.80crediario.png")).exact();
	private Pattern m_pagamentoVenda28080crediarioSelecionado = new Pattern(getImage("imgTotais/pagamentoVenda280.80crediarioSelecionado.png")).exact();
	private Pattern m_pagamentoBaixa10000parcialDinheiro = new Pattern(getImage("imgTotais/pagamentoBaixa100.00parcialDinheiro.png")).exact();
	private Pattern m_pagamentoVenda31424 = new Pattern(getImage("imgTotais/pagamentoVenda314.24.png")).exact();

	private Pattern m_crediario2x = new Pattern(getImage("imgGeral/crediario2x.png")).similar(0.98f);
	private Pattern m_confirmar = new Pattern(getImage("imgGeral/confirmar.png")).similar(0.99f);

	private Pattern m_pagamentoPedido = new Pattern(getImage("imgOrcamentoPedido/pedidoPagamento.png")).similar(0.98f);
	@SuppressWarnings("unused")
	private Pattern totalReceber, saldoFinalAntPagamento, saldoFinal, totalDinheiro, listagemTroca, totalRecebido, listagemDinheiro,
	listagemValorPago = null, listagemComTroco = null;

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static PedidoPagamento getInstance(){
		if (instancia ==null){
			instancia = new PedidoPagamento();
		}
		return instancia;
	}

	// Calendario
	Calendar now = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("ddhhmmMMyyy");
	String numeroCheque = formatter.format(now.getTime());


	public void mouseOuTecladoIrParaPagamento(String tipoEntrada) throws FindFailed{

		if (tipoEntrada == "teclado") {
			s.type(Key.F4);
			s.wait(3.0);
		}

		if (tipoEntrada == "mouse") {
			s.find(m_pagamentoPedido).click(m_pagamentoPedido);
			s.wait(3.0);
		}

		// VERIFICA SE ESTA NA TELA DE PAGAMENTO
		if (s.exists(m_totalReceber) != null) {
		} else {
			System.out.println("NOK - Nao exibido o total a receber");
		}
	}

	public void realizaPagamentoCrediario() throws FindFailed{
		s.type(Key.F4);
		s.type(Key.F3);
		s.wait(m_crediario2x, 6.0);
		if (s.exists(m_crediario2x) != null) {
			s.click(m_crediario2x);
			s.wait(m_confirmar, 50);
			s.click(m_confirmar);
			s.wait(2.0);
			validaTotaisCrediario("280,80");
		}
	}

	public void realizarPagamentoSemValorAcumulado(String valor) throws InterruptedException {
		validaTotais(valor);
	}

	public void realizarPagamentoDinheiro(String tipoEntrada, String valor) throws InterruptedException, FindFailed {
		mouseOuTecladoIrParaPagamento(tipoEntrada);
		efetivaPagamentoDinheiro(valor);
		validaTotais(valor);
	}


	public void realizaPagamentoParcialBaixaParcela(String valor) throws FindFailed{
		efetivaPagamentoDinheiroDiferenteTotal(valor);
		validaTotaisBaixaParcela(valor);
	}
	public void realizarPagamentoDinheiroComTroco(String tipoEntrada, String valor, String valorPago) throws InterruptedException, FindFailed {

		mouseOuTecladoIrParaPagamento(tipoEntrada);
		efetivaPagamentoDinheiroDiferenteTotal(valorPago);
		validaTotaisComTroco(valor);
	}


	public void validaTotais(String valor){
		switch (valor) {

		case "00,00":
			if(s.exists(m_pagamentoVenda8360) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "37,62":
			if(s.exists(m_pagamentoVenda3762) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "41,80":
			if(s.exists(m_pagamentoVenda4180) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "50,00":
			if(s.exists(m_pagamentoVenda5000) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "59,90":
			if(s.exists(m_pagamentoVenda5990) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "83,60":
			if(s.exists(m_pagamentoVenda8360) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "91,80":
			if(s.exists(m_pagamentoVenda9180) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "314,24":
			if(s.exists(m_pagamentoVenda31424) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		case "280,80":
			if(s.exists(m_pagamentoVenda28080) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;

		default:
			fail("Valor nao incluido para validacao");
			break;
		}
	}

	public void validaTotaisBaixaParcela(String valor){
		switch (valor) {

		//BAIXA DE PARCELA PARCIAL
		case "100,00":
			if(s.exists(m_pagamentoBaixa10000parcialDinheiro) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
			break;
		}
	}

	public void validaTotaisCrediario(String valor){
		switch (valor) {

		case "280,80":
			if((s.exists(m_pagamentoVenda28080crediario) != null) || (s.exists(m_pagamentoVenda28080crediarioSelecionado) != null)) {
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
		}
	}

	public void validaTotaisComTroco(String valor){
		switch (valor) {

		case "280,80":
			if(s.exists(m_pagamentoVenda28080comTroco) != null){
			}else{
				assertFalse("NOK divergencia no pagamento", true);
			}
		}
	}

	public void efetivaPagamentoDinheiro(String valor) throws FindFailed{
		s.type(Key.F8);
		s.type(Key.ENTER);
		s.wait(2.0);
	}

	public void efetivaPagamentoDinheiroDiferenteTotal(String valorPago) throws FindFailed{

		valorPago = valorPago.replace(",", ".");
		s.type(Key.F8);
		s.type("a", KeyModifier.CTRL);
		s.type(Key.BACKSPACE);
		s.type(valorPago);
		s.type(Key.ENTER);
	}

	public void efetivaPagamentoTroca(boolean pagamentoDiferenca){

		if(pagamentoDiferenca == true){

		}else{

		}
	}

	public void validarTroca(){
	}

	/*
			// Se tiver pagamento com dinheiro junto da troca
			if (trocaComDinheiro == true) {
				s.type(Key.F8);
				botaoOK.clicarOK(botaoOK.procurarOK());

				if (s.exists(listagemValorPago) != null) {
				} else {
					assertFalse("NOK valor pago", true);
				}

				if (s.exists(listagemDinheiro) != null) {
					if (s.exists(totalDinheiro) != null) {
					}
				} else {
					assertFalse("NOK - Listagem pagamento em dinheiro", true);
				}
			}

			// Valida ambos os casos da troca
			if (s.exists(listagemTroca) != null) {
			} else {
				assertFalse("NOK - Listagem pagamento e valor troca", true);
			}

			if (s.exists(totalRecebido) != null) {
			} else {
				assertFalse("NOK - Total recebido", true);
			}

			if (s.exists(saldoFinal) != null) {
			} else {
				assertFalse("NOK - Saldo final", true);
			}
		}
	}*/
}