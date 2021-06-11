package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;

public class UltimoCupom {

	public String getUltimoCupom() throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM cupom ORDER BY cupom_codigo desc LIMIT 1");

		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		String ultimoCupom = rs.getString(1);
		rs.close();
		stmt.close();
		return ultimoCupom;
	}
}
