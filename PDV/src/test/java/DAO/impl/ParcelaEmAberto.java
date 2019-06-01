package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;

public class ParcelaEmAberto {

	public Boolean getparcela(int numero, double valor) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * from PARCELA WHERE TERCEIRO_CODIGO = '627' "
						+ "AND PARCELA_STATUS = 1 "
						+ "AND PARCELA_NUMERO = ? "
						+ "AND PARCELA_VALOR = ?");

		stmt.setInt(1, numero);
		stmt.setDouble(2, valor);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return false;
		rs.close();
		stmt.close();
		return true;
	}

}
