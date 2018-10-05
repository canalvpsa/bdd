package classesAuxiliares;

import static org.junit.Assert.fail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import classesAuxiliares.BancoDadosERP;

public class BuscaRecebimentoEstoque {

	public String pesquisarRecebimento(String numeroNota, String serieNota) {
		
		BancoDadosERP ConexaoBanco = new BancoDadosERP();
		String resultado = null;
		Statement stmt = ConexaoBanco.iniciaConexaoERP();
		int numeroPesquisar = Integer.parseInt(numeroNota);
		try {
			System.out.println("\n");
			System.out.println("Procurando entrada no banco de dados ...");
			String SQL_select_entrada = "select enf.ENTRADA_ID "
			+"from qa_estoque.ENTRADA "
			+"inner join qa_estoque.ENTRADA_NOTA_FISCAL enf on (ENTRADA.id = enf.entrada_id)"
			+"inner join qa_estoque.NOTA_FISCAL nf on (nf.id = enf.notasfiscais_id)"
			+" where nf.STATUS = 'EMITIDO'"
			+" and ENTRADA.NUMERODOCUMENTO = '" + String.valueOf(numeroPesquisar) + "'";
			ResultSet resultSet1 = stmt.executeQuery(SQL_select_entrada);
			System.out.println(SQL_select_entrada);

			if (resultSet1 != null) {
				if (resultSet1.next()) {
					resultado = resultSet1.getString("ENTRADA_ID");
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
}