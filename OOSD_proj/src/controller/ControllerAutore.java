package controller;

import java.util.ArrayList;
import DAO.MySQLImplementation.MySQLDAOAutoreImpl;
import DAO.interfaces.DAOAutore;
import model.*;

/**
 * @author elful
 *
 */
public class ControllerAutore {
	private static DAOAutore daoAutore = new MySQLDAOAutoreImpl();
	/**
	 *  array list in cui verranno conservate le lista di autori cercate
	 */
	private static ArrayList<Autore> listaAutori;
	
	public static ArrayList<Autore> getListaAutori() { return listaAutori; }
	
	/**
	 * dato un autore controlla se è presente nel database, se l'autore non ha un cognome bisogna inizializzarlo a 'null'
	 * 
	 * @param a l'autore da controllare
	 * @return  torna il suo id se è presente,altrimenti torna -1
	 */
	public static int esisteAutore( Autore a )
	{
		if( a.getCognome() == null ) return daoAutore.controllaAutore( a.getNome() ); 
		else return daoAutore.controllaAutore( a.getNome(), a.getCognome() );
	}
		
	/**
	 * dato un autore lo inserisce nel database, se l'autore non ha un cognome bisogna inizializzarlo a 'null'
	 * 
	 * @param a l'autore da inserire nel database
	 * @return torna -1 se è presente,altrimenti torna il suo id
	 */
	public static int insAutore( Autore a )
	{
		if ( esisteAutore(a) >= 0 ) return -1;
			
		if( a.getCognome() == null ) { return daoAutore.inserisciAutore( a.getNome() ); }
		else { return daoAutore.inserisciAutore( a.getNome(), a.getCognome() ); }
	}
	
	/**
	 * Aggiunge l'autore alla pubblicazione
	 * 
	 * @param a
	 * @param pubb
	 * @return ritorna vero se va a buon fine, falso in caso contrario
	 */
	public static boolean insScritto( Autore a, Pubblicazione pubb )
	{
		if ( daoAutore.autoreScrittoPubb( a.getID(), pubb.getID() ) ) return false;
		return daoAutore.aggiungiAutoreAPubb( a.getID(), pubb.getID(), ControllerUtente.getUtente().getID() );
	}
	
	/**
	 * elimina la connessione tra l'autore e una pubblicazione 
	 * 
	 * @param a
	 * @param pubb
	 */
	public static void delScritto( Autore a, Pubblicazione pubb )
	{
		daoAutore.rimuoviAutoreAPubb( a.getID(), pubb.getID(), ControllerUtente.getUtente().getID() );
	}

	/**
	 * modifica un campo di un autore
	 * 
	 * @param a
	 * @param campo il campo da modificare
	 * @param nuovoValore il nuovo valore
	 * @return
	 */
	public static boolean modAutore( Autore a, String campo, String nuovoValore )
	{
		return daoAutore.modificaAutore( a.getID(), campo, nuovoValore);
	}
	
	/**
	 * elimina l'autore dal database
	 * 
	 * @param a
	 */
	public static void delAutore( Autore a )
	{
		daoAutore.cancellaAutore( a.getID(), ControllerUtente.getUtente().getID() );
	}
	
	/**
	 * permette di visualizzare la lista di autori di una pubblicazione
	 * 
	 * @param pubb
	 * @param numeroPagina
	 */
	public static void visualizzaListaAutoridiPubb( Pubblicazione pubb, int numeroPagina )
	{
		listaAutori =  daoAutore.visualizzaListaAutoridiPubb( pubb.getID(), numeroPagina );
	}
	
	/**
	 * permette di visualizzare la lista di autori completa
	 * 
	 * @param numeroPagina
	 */
	public static void visualizzaListaAutori( int numeroPagina )
	{
		listaAutori =  daoAutore.visualizzaListaAutoriCompleta(numeroPagina);
	}

	/**
	 * ritorna la lista completa degli autori di una pubblicazione
	 * @param idPubb
	 * @return
	 */
	public static ArrayList<Autore> ListaAutoriPubb( int idPubb )
	{
		return daoAutore.getListaAutori( idPubb );
	}
}