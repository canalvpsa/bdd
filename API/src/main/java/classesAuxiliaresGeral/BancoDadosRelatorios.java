package classesAuxiliaresGeral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.fail;

public class BancoDadosRelatorios {

	int rsCount = 0;
	String resultado;

	public Statement iniciaConexaoRelatorios() {

		try {
			// strings de conexao
			String databaseURL = "jdbc:mysql://192.168.25.100/compras";
			String usuario = "root";
			String senha = "vpsa";
			String driverName = "com.mysql.jdbc.Driver";

			Class.forName(driverName).newInstance();
			Connection conn = DriverManager.getConnection(databaseURL, usuario, senha);
			return conn.createStatement();
		} catch (Exception e) {
			System.out.println("Problemas ao conectar com o banco de dados: " + e);
			fail("Problema com banco de dados");
		}
		return null;
	}

	public String pegarToken(String id_user, String login) {

		Statement stmt = iniciaConexaoRelatorios();
		// id_portal = 2097152 and login = 'Jose';
		try {
			String SQL = "SELECT access_token FROM relatorios2.vpsa_user " + "WHERE id_user = \'" + id_user + "\'";

			ResultSet rs = stmt.executeQuery(SQL);

			if (rs.next()) {
				resultado = rs.getString("access_token");
			}
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			fail("Problema com banco de dados");
		}
		return resultado;
	}
}