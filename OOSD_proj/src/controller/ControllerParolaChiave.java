package controller;

import java.util.ArrayList;
import DAO.MySQLImplementation.MySQLDAOParolaChiaveImpl;
import DAO.interfaces.DAOParolaChiave;
import model.*;

public class ControllerParolaChiave {
	private static DAOParolaChiave daoParola = new MySQLDAOParolaChiaveImpl();
	/**
	 * conserva la lista di parole chiave visualizzate
	 */
	private static ArrayList<ParolaChiave> listaParole;
	
	public static ArrayList<ParolaChiave> getListaParole() { return listaParole; }
	
	/**
	 * controlla se una parola chiave di cui non si conosce l'id esiste all'interno del database
	 * 
	 * @param pc la parola chiave da controllare
	 * @return torna -1 se non è presente, in caso contrario torna il suo id
	 */
	public static int esisteParolaChiave( ParolaChiave pc)
	{
		return daoParola.controllaParolaChiave( pc.getParolaChiave() );
	}
	
	/**
	 * inserisce la parola chiave nel database
	 * 
	 * @param pc la parola chiave da inserire
	 * @return torna -1 se è presente,altrimenti torna il suo id
	 */
	public static int insParolaChiave( ParolaChiave pc )
	{
		return daoParola.inserisciParolaChiave( pc.getParolaChiave() );
	}
	
	/**
	 * aggiunge la parola chiave alla pubblicazione
	 * 
	 * @param pc
	 * @param pubb
	 * @return ritorna vero se va a buon fine, falso in caso contrario
	 */
	public static boolean insTag( ParolaChiave pc, Pubblicazione pubb )
	{
		if( daoParola.parolaChiaveTagPubb( pc.getID(), pubb.getID() ) ) return false;
		return daoParola.aggiungiParolaChiaveAPubb( pc.getID(), pubb.getID(), ControllerUtente.getUtente().getID() );
	}
	
	/**
	 * elimina la parola chiave dalla pubblicazione
	 * 
	 * @param pc
	 * @param pubb
	 */
	public static void delTag( ParolaChiave pc, Pubblicazione pubb )
	{
		daoParola.rimuoviParolaChiaveAPubb( pc.getID(), pubb.getID(), ControllerUtente.getUtente().getID() );
	}

	/**
	 * modifica la parola chiave
	 * 
	 * @param pc la parola chiave da modificare
	 * @param nuovoValore il nuovo valore della parola chiave
	 */
	public static void modParolaChiave( ParolaChiave pc, String nuovoValore )
	{
		daoParola.modificaParolaChiave( pc.getID(), nuovoValore );
	}
	
	/**
	 * elimina la parola chiave dal database
	 * 
	 * @param pc la parola chiave da eliminare
	 */
	public static void delParolaChiave( ParolaChiave pc )
	{
		daoParola.cancellaParolaChiave( pc.getID(), ControllerUtente.getUtente().getID());
	}
	
	/**
	 * permette di visualizzare la lista di parole chiave di una pubblicazione
	 * 
	 * @param pubb
	 * @param numeroPagina la pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaParoleChiavediPubb( Pubblicazione pubb, int numeroPagina )
	{
		listaParole =  daoParola.visualizzaListaParolaChiavediPubb( pubb.getID(), numeroPagina );
	}
	
	/**
	 * permette di visualizzare la lista completa di parole chiave
	 * 
	 * @param numeroPagina la pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaParoleChiave( int numeroPagina )
	{
		listaParole =  daoParola.visualizzaListaParolaChiaveCompleta(numeroPagina);
	}
	
	public static ArrayList<ParolaChiave> getListaParoleChiave( int idPubb )
	{
		return daoParola.getListaParoleChiave(idPubb);
	}
}
