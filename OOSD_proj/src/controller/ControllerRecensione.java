package controller;

import java.util.ArrayList;
import DAO.interfaces.DAORecensione;
import model.*;

public class ControllerRecensione {
	private static DAORecensione daoRecensione;
	public static void setDao( DAORecensione dao )
	{
		daoRecensione = dao;
	}
	/**
	 * conserva la lista di recensioni visualizzata 
	 */
	private static ArrayList<Recensione> listaRecensioni;
	
	public static ArrayList<Recensione> getListaRecensioni() { return listaRecensioni; }
	
	/**
	 * conserva tutte le informazioni della recensione visualizzata 
	 */
	private static Recensione dettagliRecensione;
	public static void setDettagliRecensione( Recensione rec ) { dettagliRecensione = rec; }
	public static Recensione getDettagliRecensione() { return dettagliRecensione; }
	
	/**
	 * inserisce una recensione nel database da parte dell'utente connesso alla pubblicazione visualizzata
	 * 
	 * @param Testo il testo della recensione
	 */
	public static void insRecensione( String Testo)
	{
		daoRecensione.inserisciRecensione( ControllerUtente.getUtente().getID(), ControllerPubblicazione.getPubb().getID(), Testo);
	}
	
	/**
	 * elimina la recensione visualizzata
	 */
	public static void delRecensione ()
	{
		daoRecensione.eliminaRecensione( dettagliRecensione.getIDUtente(), dettagliRecensione.getIDPubblicazione(), ControllerUtente.getUtente().getID() );
	}
	
	/**
	 * controlla se l'utente connesso ha inserito una recensione per la pubblicazione visualizzata
	 * 
	 * @return torna true se l'utente connesso ha inserito una recensione per la pubblicazione visualizzata
	 */
	public static boolean utenteInseritoRecensione()
	{
		return daoRecensione.utenteInseritoRecensione( ControllerUtente.getUtente().getID(), ControllerPubblicazione.getPubb().getID());
	}
	
	/**
	 * permette all'utente connesso di approvare la recensione visualizzata
	 */
	public static void approvaRecensione( int idPubb , int idUtente )
	{
		daoRecensione.approvaRecensione( ControllerUtente.getUtente().getID(), idPubb, idUtente );
	}
	
	/**
	 * permette di visualizzare la lista di recensioni approvate di una pubblicazione
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaRecensioniApprovatediPubb( int numeroPagina)
	{
		listaRecensioni = daoRecensione.visualizzaListaRecensioniApprovatediPubb( ControllerPubblicazione.getPubb().getID(), numeroPagina );
	}
	
	/**
	 * permette di visualizzare la lista di recensioni non approvate
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaRecensioniNonApprovate( int numeroPagina )
	{
		listaRecensioni = daoRecensione.visualizzaListaRecensioniNonApprovate( numeroPagina );
	}
	
	/**
	 * permette di visualizzare la lista di recensioni scritte da un utente
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 * @param utente
	 */
	public static void visualizzaListaRecensionidiUtente( int numeroPagina, UtenteBase utente )
	{
		listaRecensioni = daoRecensione.visualizzaListaRecensionidiUtente( utente.getID(), numeroPagina );
	}

}
