package DAO.connectors;

import java.sql.Connection;
import java.sql.DriverManager;

import DAO.MySQLImplementation.MySQLDAOAutoreImpl;
import DAO.MySQLImplementation.MySQLDAOLikeImpl;
import DAO.MySQLImplementation.MySQLDAOParolaChiaveImpl;
import DAO.MySQLImplementation.MySQLDAOPubblicazioneImpl;
import DAO.MySQLImplementation.MySQLDAORecensioneImpl;
import DAO.MySQLImplementation.MySQLDAORistampaImpl;
import DAO.MySQLImplementation.MySQLDAOSorgenteImpl;
import DAO.MySQLImplementation.MySQLDAOStoriaImpl;
import DAO.MySQLImplementation.MySQLDAOUtenteImpl;
import controller.ControllerAutore;
import controller.ControllerLikes;
import controller.ControllerParolaChiave;
import controller.ControllerPubblicazione;
import controller.ControllerRecensione;
import controller.ControllerRistampa;
import controller.ControllerSorgente;
import controller.ControllerStorico;
import controller.ControllerUtente;

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
	
	/**
	 * questo metodo viene chiamato nel main in modo da inizializzare i controller per lavorare con un database di tipo mysql caricando i rispettivi dao
	 */
	public static void mysqlInit()
	{
		ControllerAutore.setDao(new MySQLDAOAutoreImpl());
		ControllerLikes.setDao(new MySQLDAOLikeImpl());
		ControllerParolaChiave.setDao(new MySQLDAOParolaChiaveImpl());
		ControllerRecensione.setDao(new MySQLDAORecensioneImpl());
		ControllerRistampa.setDao(new MySQLDAORistampaImpl());
		ControllerSorgente.setDao(new MySQLDAOSorgenteImpl());
		ControllerStorico.setDao(new MySQLDAOStoriaImpl());
		ControllerUtente.setDao(new MySQLDAOUtenteImpl());
		ControllerPubblicazione.setDao(new MySQLDAOPubblicazioneImpl());
	}
}
