package classesAuxiliaresGeral;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDadosERP {

	int rsCount = 0;
	String resultado = null;
	Connection conn = null;
	ResultSet resultSet = null;
	String baseERP = "qa_vpsa_aut";
	String baseEstoque = "qa_estoque_aut";

	public Statement iniciaConexaoERP() {

		try {
			String databaseURL = "jdbc:oracle:thin:@192.168.25.100:1521:vpsa";
			String usuario = "vpsa";
			String senha = "vpsa";
			String driverName = "oracle.jdbc.driver.OracleDriver";

			Class.forName(driverName).newInstance();
			Connection conn = DriverManager.getConnection(databaseURL, usuario, senha);
			return conn.createStatement();
		} catch (Exception e) {
			System.out.println("Problemas ao conectar com o banco de dados: " + e);
			fail("Problema com banco de dados");
		}
		return null;
	}

	public String verificaProduto(String idProdutoBanco) {
		if(idProdutoBanco == null)
			return resultado;

		System.out.println("Produto:" +idProdutoBanco);
		resultSet = null;
		resultado = null;
		Statement stmt = iniciaConexaoERP();
		try {
			System.out.println("\n");
			System.out.println("Procurando produto no banco de dados...");

			String SQL_select_produto = "SELECT * FROM "+baseEstoque+".mercadoria_dna WHERE id = \'" + idProdutoBanco + "\'";
			ResultSet resultSet = stmt.executeQuery(SQL_select_produto);

			if (resultSet != null) {
				if (resultSet.next()) {
					resultado = resultSet.getString("id");
				}
				stmt.close();
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			fail("Problema com banco de dados");
		}
		return resultado;
	}

	public String deleteProduto(String codigoInterno) {

		resultSet = null;
		resultado = null;
		Statement stmt = iniciaConexaoERP();
		try {
			System.out.println("\n");
			System.out.println("Procurando produto igual no banco de dados...");

			String SQL_select_produto = "SELECT * FROM "+baseEstoque+".mercadoria_dna " + "WHERE codigointerno = \'" + codigoInterno + "\'";
			ResultSet resultSet = stmt.executeQuery(SQL_select_produto);

			if (resultSet != null) {
				if (resultSet.next()) {
					resultado = resultSet.getString("id");
				}

				if (resultado != null) {
					System.out.println("Encontrou produto com codigo interno " + codigoInterno + " ID:" + resultado);
					String SQL_update_produto = "UPDATE "+baseEstoque+".mercadoria_dna "
							+ "SET deletado = 1, nome = 'API produtos revenda - excluido', codigoInterno = null " + "WHERE id = \'" + resultado
							+ "\'";
					System.out.println("Deletando produto ID " + resultado);
					stmt.executeQuery(SQL_update_produto);
				}
				System.out.println("Nao existe produto ja cadastrado com estes dados...");
				stmt.close();
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			fail("Problema com banco de dados");
		}
		return resultado;
	}

	public String deleteServico(String codigoInterno) {

		resultSet = null;
		resultado = null;
		Statement stmt = iniciaConexaoERP();
		try {
			System.out.println("\n");
			System.out.println("Procurando servico igual no banco de dados...");

			String SQL_select_produto = "SELECT * FROM "+baseEstoque+".servico_dna " + "WHERE codigointerno = \'" + codigoInterno + "\'";
			ResultSet resultSet = stmt.executeQuery(SQL_select_produto);

			if (resultSet != null) {
				if (resultSet.next()) {
					resultado = resultSet.getString("id");
				}

				if (resultado != null) {
					System.out.println("Encontrou produto com codigo interno " + codigoInterno + " ID:" + resultado);
					String SQL_update_produto = "UPDATE "+baseEstoque+".servico_dna "
							+ "SET deletado = 1, ativo = 0, nome = 'API servico - excluido', codigoInterno = null " + "WHERE id = \'" + resultado
							+ "\'";
					System.out.println("Deletando servico ID " + resultado);
					stmt.executeQuery(SQL_update_produto);
				}
				System.out.println("Nao existe servico ja cadastrado com estes dados...");
				stmt.close();
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			fail("Problema com banco de dados");
		}
		return resultado;
	}

	public String deleteTerceiro(String documento) {

		resultSet = null;
		resultado = null;
		Statement stmt = iniciaConexaoERP();
		try {
			System.out.println("\n");
			System.out.println("Procurando terceiro a ser cadastrado no banco VPSA e ESTOQUE");
			System.out.println("Procurando no VPSA");

			// PROCURA O TELEFONE NO BANCO VPSA
			String SQL_select_telefone = "SELECT "+baseERP+".TELEFONE.ID FROM QA_VPSA.TELEFONE " + "INNER JOIN QA_VPSA.TERCEIRO "
					+ "ON QA_VPSA.TELEFONE.telefone_terceiro = QA_VPSA.TERCEIRO.id " + "WHERE QA_VPSA.TERCEIRO.DOCUMENTOTERCEIRO = \'" + documento
					+ "\'";
			resultSet = stmt.executeQuery(SQL_select_telefone);

			while (resultSet.next()) {
				resultado = resultSet.getString("ID");

				if (resultado != null) {
					System.out.println("Encontrou telefone do terceiro " + documento + " ID: " + resultado + " no banco de dados: VPSA");
					String SQL_delete_telefone = "DELETE FROM '"+baseERP+".TELEFONE " + "WHERE ID = " + resultado;
					System.out.println("Deletando telefone...");
					stmt.executeQuery(SQL_delete_telefone);
				}
				System.out.println("Este terceiro nao tem telefone");
			}

			// PROCURA O ENDERECO DO TERCEIRO NO BANCO VPSA
			String SQL_select_endereco = "SELECT "+baseERP+".ENDERECO.ID FROM QA_VPSA.ENDERECO " + "INNER JOIN QA_VPSA.TERCEIRO "
					+ "ON QA_VPSA.ENDERECO.endereco_terceiro = '"+baseERP+".TERCEIRO.id " + "WHERE QA_VPSA.TERCEIRO.DOCUMENTOTERCEIRO = \'" + documento
					+ "\'";
			resultSet = stmt.executeQuery(SQL_select_endereco);

			while (resultSet.next()) {
				resultado = resultSet.getString("ID");

				if (resultado != null) {
					System.out.println("Encontrou endereco do terceiro " + documento + " ID: " + resultado + " no banco de dados: VPSA");
					String SQL_delete_endereco = "DELETE FROM "+baseERP+".ENDERECO " + "WHERE ID = " + resultado;

					System.out.println("Deletando endereco...");
					stmt.executeQuery(SQL_delete_endereco);
				}
				System.out.println("Este terceiro nao tem telefone");
			}

			// PROCURA TERCEIRO NO BANCO VPSA
			String SQL_select_VPSA = "SELECT ID FROM "+baseERP+".terceiro " + "WHERE DOCUMENTOTERCEIRO = \'" + documento + "\'";
			resultSet = stmt.executeQuery(SQL_select_VPSA);
			System.out.println(SQL_select_VPSA);

			while (resultSet.next()) {
				resultado = resultSet.getString("ID");

				if (resultado != null) {
					System.out.println("Encontrou terceiro " + documento + " ID: " + resultado + " no banco de dados: VPSA");
					System.out.println("Deletando terceiro...");
					String SQL_delete_vpsa = "DELETE FROM "+baseERP+".terceiro " + "WHERE ID = " + resultado;
					stmt.executeQuery(SQL_delete_vpsa);
					System.out.println(SQL_delete_vpsa);
				}
			}

			// PROCURA TERCEIRO NO BANCO DO ESTOQUE
			System.out.println("Procurando no ESTOQUE");
			resultSet = null;
			resultado = null;
			String SQL_select_estoque = "SELECT REFERENCIAID FROM "+baseEstoque+".vpsa_terceiro " + "WHERE NUMERODOCUMENTO = \'" + documento + "\'";
			resultSet = stmt.executeQuery(SQL_select_estoque);

			while (resultSet.next()) {
				resultado = resultSet.getString("REFERENCIAID");

				if (resultado != null) {
					System.out.println("Encontrou terceiro " + documento + " ID: " + resultado + " no banco de dados: estoque");
					String SQL_delete_estoque = "DELETE FROM "+baseEstoque+".vpsa_terceiro " + "WHERE REFERENCIAID = \'" + resultado + "\'";
					System.out.println("Deletando terceiro " + documento + " no banco de dados: estoque");
					stmt.executeQuery(SQL_delete_estoque);
				}
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


	public String parametroFluxoAprovacao(int statusParametro) {

		resultSet = null;
		resultado = null;
		Statement stmt = iniciaConexaoERP();
		try {
			System.out.println("\n");
			System.out.println("Alteracao parametro fluxo de aprovacao para: "+statusParametro);

			String SQL_updateParametroBoolean = "UPDATE "+baseERP+".PARAMETROBOOLEAN set valor= "+statusParametro+" where id = 40";
			stmt.executeQuery(SQL_updateParametroBoolean);

			String SQL_update_parametro = "UPDATE "+baseERP+".PARAMETRO set DATAALTERACAO = sysdate";
			stmt.executeQuery(SQL_update_parametro);

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