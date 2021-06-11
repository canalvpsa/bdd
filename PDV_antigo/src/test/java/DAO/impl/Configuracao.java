package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;

public class Configuracao {

	public String getConfiguracao(String config_chave) throws ClassNotFoundException, SQLException {
		
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT config_valor FROM configuracao WHERE config_chave = ?");
				
		stmt.setString(1, config_chave);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		String valor_configuracao = rs.getString(1);
		rs.close();
		stmt.close();
		return valor_configuracao;
	}
	
	public void atualizarConfiguracao(String config_chave, String valor) throws ClassNotFoundException, SQLException {
	
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"UPDATE configuracao SET config_valor = ? WHERE config_chave = ?");
		
		stmt.setString(1, valor);
		stmt.setString(2, config_chave);
		stmt.executeUpdate();
		stmt.close();
	}
}