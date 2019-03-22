package classesAuxiliares;

import java.net.URL;
import java.sql.SQLException;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import DAO.impl.ClienteVendedor;

public class VendedorCliente {
	private static VendedorCliente instancia = new VendedorCliente();
	private Screen s = new Screen();
	private ClienteVendedor BD = new ClienteVendedor();
	private String imageString;
	private Pattern m_emAtendimento = new Pattern(getImage("imgOrcamentoPedido/emAtendimento.png")).similar(0.85f);
	private Pattern m_usuarioPadrao = new Pattern(getImage("imgGeral/usuarioPadrao.png")).similar(0.95f);
	private Pattern m_selecaoVendedorCliente = new Pattern(getImage("imgGeral/selecaoVendedorCliente.png")).similar(0.95f);
	private Pattern m_vendedor = new Pattern(getImage("imgGeral/vendedor.png")).similar(0.95f);
	private Pattern m_validaVendedorUsuarioPadrao = new Pattern(getImage("imgGeral/validaVendedorUsuarioPadrao.png")).similar(0.95f);
	private Pattern m_validaClienteAnaMaria = new Pattern(getImage("imgGeral/validaClienteAnaMaria.png")).similar(0.95f);
	private Pattern m_clienteAnaMaria = new Pattern(getImage("imgGeral/clienteAnaMaria.png")).similar(0.97f);
	private Pattern m_confirmarVerde = new Pattern(getImage("imgGeral/confirmarVerde.png")).similar(0.95f);

	private String getImage(String path) {

		URL url = getClass().getClassLoader().getResource(path);
		imageString = url.toString();
		return imageString;
	}

	public static VendedorCliente getInstance(){
		if (instancia ==null){
			instancia = new VendedorCliente();
		}
		return instancia;
	}

	public void AbrirVincularVendedor() throws FindFailed {

		if (s.exists(m_emAtendimento) != null)
			s.type(Key.F2);
		s.wait(m_selecaoVendedorCliente, 15);
	}

	public void realizarVinculoVendedor(String vendedor) throws FindFailed {

		if(vendedor.equals("USUARIO PADRAO")){

			s.wait(m_vendedor, 5.0);
			s.type("usu");
			s.wait(3.0);

			s.click(m_usuarioPadrao);
		}
	}

	public void realizarVinculoCliente(String cliente) throws FindFailed {

		if(cliente.equals("ANA MARIA")){
			s.type(Key.TAB);
			s.type("562");
			s.click(m_clienteAnaMaria);
		}
	}

	public void confirmar() {
		try {
			s.click(m_confirmarVerde);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public void validarVendedor() {
		s.exists(m_validaVendedorUsuarioPadrao);
	}

	public void validarCliente() {
		s.exists(m_validaClienteAnaMaria);
	}

	public void vinculaVendedorCliente(String vendedor, String cliente) throws FindFailed {

		AbrirVincularVendedor();
		realizarVinculoVendedor(vendedor);
		realizarVinculoCliente(cliente);
		confirmar();
		validarVendedor();
		validarCliente();
	}

	public void vincularCliente(String cliente) throws FindFailed {

		AbrirVincularVendedor();
		realizarVinculoCliente(cliente);
		confirmar();
		validarCliente();
	}

	public void vincularVendedor(String vendedor) throws FindFailed {

		AbrirVincularVendedor();
		realizarVinculoVendedor(vendedor);
		confirmar();
		validarVendedor();
	}

	public void validaVendedorClienteBD(String vendedor, String cliente) throws ClassNotFoundException, SQLException{
		int idVendedor = 0, idCliente = 0;

		switch (vendedor) {

		case "USUARIO PADRAO":
			idVendedor = 20;
			break;

		case "CAIXA BARBARELLA - SÃO ROQUE":
			idVendedor = 14;
			break;

		default:
			break;
		}

		switch (cliente) {

		case "DIVERSOS":
			idCliente = 1;
			break;

		case "ANA MARIA":
			idCliente = 627;
			break;

		default:
			break;
		}

		int clienteVendedor[] = {idVendedor, idCliente};
		Assert.assertArrayEquals(clienteVendedor, BD.getClienteVendedor());
	}
}