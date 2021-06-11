package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;


public class Maquina {

	public String getAtualizaecf() throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT atualizaecf FROM terminal WHERE caixa_id = 1"
				);

		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		String valor_configuracao = rs.getString(1);
		rs.close();
		stmt.close();
		return valor_configuracao;
	}

	public void atualizarAtualizaecf() throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"UPDATE terminal SET atualizaecf = '1' WHERE id = 1");

		stmt.executeUpdate();
		stmt.close();
		System.out.println(stmt.getMoreResults());
	}
}