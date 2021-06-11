package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;

public class TipoImpressora {

	public boolean getTipoImpressora(String tipoImpressora) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT tipoimpressoratype FROM maquina WHERE identificacao = 'qa_vpsa_aut' and tipoimpressoratype = ?");

		stmt.setString(1, tipoImpressora);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return false;
		rs.close();
		stmt.close();
		return true;
	}


	public void atualizarTipoImpressora(String tipoImpressora) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"UPDATE maquina SET tipoimpressoratype = ?");

		stmt.setString(1, tipoImpressora);
		stmt.executeUpdate();
		stmt.close();
	}
}
