package DAO.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection conn;
	
	private static String host = "127.0.0.1"; 
	private static String porta = "5432"; 
	private static String schema = "pdv"; 
	private static String user = "root"; 
	private static String senha = "vpsa2012"; 
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if(conn == null) {
			conn = DriverManager.getConnection(
				"jdbc:postgresql://" + host + ":" + porta + "/" + schema + "?user="+user+"&password="+senha);
		}
		return conn;
	}
	
	public static void closeConnection() throws SQLException {
		if(conn != null) {
			conn.close();
		}
		conn = null;
	}
}