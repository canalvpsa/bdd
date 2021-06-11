package steps;

import classesAuxiliares.VendedorCliente;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class Cliente_vendedor {
	private static Cliente_vendedor instancia = new Cliente_vendedor();

	private VendedorCliente vendedorCliente = VendedorCliente.getInstance();
	
	public static Cliente_vendedor getInstance(){
		if (instancia ==null){
			instancia = new Cliente_vendedor();
		}
		return instancia;
	}

	@Quando("^o usuário altera para vendedor \"([^\"]*)\"$")
	public void o_usuário_altera_para_vendedor(String vendedor) throws Throwable {
		vendedorCliente.vincularVendedor(vendedor);
	}

	@Quando("^o usuário altera o cliente para \"([^\"]*)\"$")
	public void o_usuário_altera_o_cliente_para(String cliente) throws Throwable {
		vendedorCliente.vincularCliente(cliente);
	}

	@Quando("^o usuário altera o cliente para \"([^\"]*)\" o vendedor \"([^\"]*)\"$")
	public void o_usuário_altera_o_cliente_para_o_vendedor(String cliente, String vendedor) throws Throwable {
		vendedorCliente.vinculaVendedorCliente(vendedor, cliente);
	}


	@Então("^a venda fica registrada para o vendedor \"([^\"]*)\" e cliente \"([^\"]*)\"$")
	public void a_venda_fica_registrada_para_o_vendedor_e_cliente(String arg1, String arg2) throws Throwable {
		vendedorCliente.validarVendedor();
	}
}