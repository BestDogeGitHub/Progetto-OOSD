package controller;

import java.util.ArrayList;

import DAO.MySQLImplementation.MySQLDAOSorgenteImpl;
import DAO.interfaces.DAOSorgente;
import model.*;

public class ControllerSorgente {
	private static DAOSorgente daoSorgente = new MySQLDAOSorgenteImpl();
	/**
	 * conserva la lista di sorgenti visualizzate
	 */
	private static ArrayList<Sorgente> listaSorgenti;
	
	public static ArrayList<Sorgente> getListaSorgenti() { return listaSorgenti; }
	
	/**
	 * permette di inserire una sorgente ad una data pubblicazione
	 * 
	 * @param pubb
	 * @param s la sorgente da inserire
	 */
	public static void insSorgente( Pubblicazione pubb, Sorgente s )
	{
		daoSorgente.insSorgente( pubb.getID(), s.getURI(), s.getTipo(), s.getFormato(), s.getDescrizione(),  ControllerUtente.getUtente().getID() );
	}
	
	/**
	 * permette di eliminare una sorgente dalla pubblicazione visualizzata
	 * 
	 * @param s la sorgente da eliminare
	 */
	public static void delSorgente( Sorgente s )
	{
		daoSorgente.delSorgente( s.getID(), ControllerUtente.getUtente().getID() );
	}
	
	/**
	 * permette di modificare una sorgente presente nel database
	 * 
	 * @param s la sorgente da modificare
	 * @param campo il campo da modificare
	 * @param nuovoValore il nuovo valore
	 */
	public static void modSorgente( Sorgente s, String campo, String nuovoValore )
	{
		daoSorgente.modSorgente( s.getID(), campo, nuovoValore);
	}
	
	/**
	 * permette di visualizzare la lista sorgenti di una data pubblicazione
	 * 
	 * @param pubb
	 * @param numeroPagina la pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaSorgenti( Pubblicazione pubb, int numeroPagina ) 
	{
		listaSorgenti = daoSorgente.visualizzaListaSorgentiDiPubb( pubb.getID(), numeroPagina); 
	}
	
	public static ArrayList<Sorgente> getListaSorgentiDiPubb( int idPubb )
	{
		return daoSorgente.getListaSorgentiDiPubb(idPubb);
	}
	
}
