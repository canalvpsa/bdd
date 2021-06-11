package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;

public class EnderecoAtualizarPDV {

	public void atualizarEnderecoPDV() throws ClassNotFoundException, SQLException {
		
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"UPDATE atualizacao SET urldownload = REPLACE (urldownload, 'http://qa3.varejonline.com.br:8989/' , "
				+ "'https://qa3.varejonline.com.br:8449/') WHERE urldownload LIKE 'http://qa3.varejonline.com.br:8989/%' AND status = 1;");
		stmt.executeUpdate();
		stmt.close();
	}
}
