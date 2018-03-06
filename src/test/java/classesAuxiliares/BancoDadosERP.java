package classesAuxiliares;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDadosERP {
	int rsCount = 0;
	String resultado = null;
	Boolean resultado2 = false;
	Connection conn = null;
	ResultSet resultSet = null;
	public Statement iniciaConexaoERP(){
		try {
			String databaseURL = "jdbc:oracle:thin:@192.168.25.100:1521:vpsa";
			String usuario = "system";
			String senha = "oracle";
			String driverName = "oracle.jdbc.driver.OracleDriver";

			Class.forName(driverName).newInstance();
			Connection conn = DriverManager.getConnection(databaseURL, usuario, senha);
			return conn.createStatement();
		}	
		catch (Exception e) {
			System.out.println("Problemas ao conectar com o banco de dados: " + e);
			fail("Problema com banco de dados");
		}
		return null;
	}

	public String pesquisarRecebimento(){
		resultSet = null;
		resultado = null;
		Statement stmt = iniciaConexaoERP();
		try {
			System.out.println("\n");
			System.out.println("Procurando entrada no banco de dados ...");
			String SQL_select_entrada = "SELECT NUMINTERNO FROM QA_COMPRAS.ENTRADAPEDIDOCOMPRA "
					+ "INNER JOIN QA_COMPRAS.ENTRADAPEDCOMPRA_PEDCOMPRA "
					+ "ON QA_COMPRAS.ENTRADAPEDIDOCOMPRA.PEDIDOCOMPRA = QA_COMPRAS.ENTRADAPEDCOMPRA_PEDCOMPRA.PEDIDOCOMPRA_ID "
					+ "INNER JOIN QA_COMPRAS.PEDIDOCOMPRA "
					+ "ON QA_COMPRAS.ENTRADAPEDCOMPRA_PEDCOMPRA.PEDIDOCOMPRA_ID  = QA_COMPRAS.PEDIDOCOMPRA.ID "
					+ "WHERE NUMERODOCUMENTO = '3658'";
					
					;
			ResultSet resultSet = stmt.executeQuery(SQL_select_entrada);

			if(resultSet != null){
				if(resultSet.next()){
					resultado = resultSet.getString("NUMINTERNO");
					System.out.println("Encontrou recebimento de notas");
				}

				stmt.close();
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			fail("Problema com banco de dados");
		}
		return resultado;
	}
	
	
	public Boolean pesquisarEntradaProdutos(){
		resultSet = null;
		resultado2 = null;
		Statement stmt = iniciaConexaoERP();
		try {
			System.out.println("\n");
			System.out.println("Procurando entrada no banco de dados ...");
			String SQL_select_entrada = "SELECT * from QA_ESTOQUE.ENTRADA where NUMERODOCUMENTO = '12557' and cancelado = 0";
			ResultSet resultSet = stmt.executeQuery(SQL_select_entrada);

			if(resultSet != null){
				if(resultSet.next()){
					resultado2 = true;
					System.out.println("Encontrou recebimento de notas");
				}

				stmt.close();
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			fail("Problema com banco de dados");
		}
		return resultado2;
	}
	
	
}