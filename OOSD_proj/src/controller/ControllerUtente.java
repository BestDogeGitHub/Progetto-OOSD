package controller;

import model.*;

import java.util.*;
import DAO.interfaces.DAOUtente;

public class ControllerUtente {
	private static DAOUtente daoUtente;
	public static void setDao( DAOUtente dao )
	{
		daoUtente = dao;
	}
	/**
	 *  conserva le informazioni dell'utente connesso al software
	 */
	private static UtenteBase utente;
	
	/**
	 * il profilo di un utente qualsiasi conservato nel database viene salvato qui mentre viene visualizzato
	 */
	private static UtenteBase profiloUtente;
	
	/**
	 * inizializza il profilo di un utente dato il suo id dal suo ID, viene inserito in profiloUtente per poi poter essere visualizzato
	 * @param idUtente
	 */
	public static void setProfiloUtenteByID( int idUtente ) { profiloUtente = daoUtente.getUtenteDaID( idUtente ); }
	
	private static ArrayList<UtenteBase> listaUtenti;
	private static ArrayList<UtenteModeratore> listaModeratori;

	/**
	 * @return ritorna una lista di utenti
	 */
	public static ArrayList<UtenteBase> getListaUtente() { return listaUtenti; }
	public static ArrayList<UtenteModeratore> getListaModeratori() { return listaModeratori; }
	
	/**
	 * @return ritorna i dati dell'utente che si vuole visualizzare
	 */
	public static UtenteBase getProfiloUtente() { return profiloUtente; }
	 
	/**
	 * @return ritorna i dati dell'utente connesso che ha effettuato il login
	 */
	public static UtenteBase getUtente() { return utente; }
	public static void setUtente(UtenteBase u) { utente = u; }
	
	/**
	 * inizializza l'utente connesso al database tramite il suo id
	 * @param idUtente
	 */
	public static void setUtenteConnessoByID( int idUtente ) 
	{
		utente = daoUtente.getUtenteDaID( idUtente );
	}
	
	/**
	 * ritorna un utente con l'username passato in input
	 * 
	 * @param username l'username cercato
	 * @return
	 */
	public static UtenteBase getUtenteByUsername(String username)
	{
		return daoUtente.getUtenteDaUsername( username );
	}

	/** 
	 * inserisce l'utente nel database
	 * @param utente
	 * @return torna true se la registrazione va a buon fine,false viceversa
	 */
	public static boolean registrazione( UtenteBase utente )
	{
		return daoUtente.registraUtente( utente.getUsername(), utente.getPass(), utente.getNome(), utente.getCognome(), utente.getDataNascita(),
												  utente.getLuogoNascita(), utente.getResidenza(), utente.getEmail());
	}
	
	/**
	 * abbassa di grado l'utente visualizzato da moderatore a utente base
	 */
	public static void recUtenteAUtenteBase()
	{
		daoUtente.recUtenteAUtenteBase( profiloUtente.getID() );
	}
	
	/**
	 * promuove l'utente visualizzato da utente base a moderatore
	 */
	public static void promuoviUtenteBaseAModeratore()
	{
		daoUtente.promuoviUtenteBaseAModeratore( utente.getID(), profiloUtente.getID() );
	}
	
	/**
	 * promuove l'utente visualizzato da moderatore ad amministratore
	 */
	public static void promuoviModeratoreAdAmministratore()
	{
		daoUtente.promuoviModeratoreAdAmministratore( utente.getID(), profiloUtente.getID() );
	}
	
	/**
	 * abbassa di grado l'utente visualizzato da amministratore a moderatore
	 */
	public static void recAmministratoreAdModeratore()
	{
		daoUtente.recAmministratoreAdModeratore( profiloUtente.getID() );
	}
	
	/**
	 * elimina l'utente visualizzato
	 */
	public static void delUtente()
	{
		daoUtente.eliminazioneUtente( profiloUtente.getID() );
	}
	
	/**
	 * permette all'utente connesso di modificare informazioni sul suo profilo
	 * 
	 * @param campo il campo da modificare
	 * @param nuovoValore il nuovo valore che il campo deve assumere
	 */
	public static void modDatiUtente( String campo, String nuovoValore)
	{
		daoUtente.modificaDatiUtente( utente.getID(), campo, nuovoValore);
	}
	
	/**
	 * permette di visualizzare la lista di moderatori in ordine di numero di pubblicazioni inserite
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaUtentiCollaborativi(int numeroPagina)
	{
		listaModeratori = daoUtente.visualizzaListaUtentiCollaborativi(numeroPagina);
	}
	
	/**
	 * permette di visualizzare la lista completa di utenti iscritta al sito in ordine alfabetico di username
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaUtentiOrdinatiPerUsername( int numeroPagina )
	{
		listaUtenti = daoUtente.visualizzaListaUtentiOrdinatiPerUsername(numeroPagina);
	}
	
	/**
	 * permette all'utente connesso di uscire
	 */
	public static void logout()
	{
		utente = null;
	}
	
	/**
	 * controlla se è presente un utente con un dato username
	 * 
	 * @param username
	 * @return torna true se esiste,false viceversa
	 */
	public static boolean esisteUsername( String username )
	{
		return daoUtente.esisteUsername(username);
	}
	
	/**
	 * controlla se è presente un utente con una data email
	 * @param email
	 * @return torna true se esiste,false viceversa
	 */
	public static boolean esisteEmail( String email )
	{
		return daoUtente.esisteEmail(email);
	}
	
	/**
	 * @return torna la lista di città salvate nel database
	 */
	public static ArrayList<String> getListaCittà()
	{
		return daoUtente.getListaCittà();
	}
}