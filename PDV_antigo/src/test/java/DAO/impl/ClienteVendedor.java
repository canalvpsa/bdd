package DAO.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.utils.ConnectionFactory;

public class ClienteVendedor {
	
public int[] getClienteVendedor() throws ClassNotFoundException, SQLException {
		
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT representante_codigo, terceiro_codigo from CUPOM order by cupom_codigo desc limit 1");
				
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		int idVendedor  = rs.getInt(1);
		int idTerceiro  = rs.getInt(2);
		rs.close();
		stmt.close();
		return new int[] {idVendedor, idTerceiro};
	}

}
