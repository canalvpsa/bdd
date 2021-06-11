package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;

public class UltimoOrcamento {

	public String getUltimoOrcamento() throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM comanda ORDER BY cmd_codigo desc LIMIT 1");

		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		String ultimoOrcamento = rs.getString(1);
		rs.close();
		stmt.close();
		return ultimoOrcamento;
	}
}
