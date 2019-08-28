package DAO.connectors;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDBConnector {

	// Implementazione della connessione con MySQL
	public static Connection creaConnessione() 
	{	
		String url = "jdbc:mysql://localhost:3306/progettodb?serverTimezone=CET";
		String user = "root";
		String password = "r00t";
		
		try
		{
			return DriverManager.getConnection(url, user, password);
		}catch(Exception ex) { ex.printStackTrace(); }
		return null;
	}
}
