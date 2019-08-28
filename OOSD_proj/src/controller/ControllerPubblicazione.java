package controller;


import java.util.ArrayList;
import DAO.MySQLImplementation.*;
import DAO.interfaces.DAOPubblicazione;
import model.*;

public class ControllerPubblicazione {
	private static DAOPubblicazione daoPubblicazione = new MySQLDAOPubblicazioneImpl();
	/**
	 * conserva la pubblicazione visualizzata dall'utente connesso
	 */
	private static Pubblicazione pubb;
	/**
	 * conserva la lista di pubblicazioni visualizzate dall'utente
	 */
	private static ArrayList<ObservablePubblicazioni> listaPubb;
	
	public static Pubblicazione getPubb() { return pubb; }
	public static ArrayList<ObservablePubblicazioni> getListaPubblicazioni() { return listaPubb; }
	
	/**
	 * inserisce una pubblicazione nel database, associata alle liste di: autori,sorgenti,parole chiave e ristampe
	 * 
	 * @param p
	 * @param listaAutori
	 * @param listaSorgenti
	 * @param listaParoleChiave
	 * @param listaRistampe
	 * @return torna l'id della pubblicazione una volta inserita
	 */
	public static int insPubb( Pubblicazione p, ArrayList<Autore> listaAutori, ArrayList<Sorgente> listaSorgenti,
							   ArrayList<ParolaChiave> listaParoleChiave, ArrayList<Ristampa> listaRistampe)
	{			
		p.setID( daoPubblicazione.inserisciPubb( ControllerUtente.getUtente().getID(), p.getISBN(), p.getTitolo(), p.getEditore(), p.getDataPubblicazione(), 
														  p.getNpag(), p.getLingua(), p.getDescrizione(), p.getIndice()));

		for( Ristampa r : listaRistampe ) { ControllerRistampa.insRistampa( r, p ); }
		for( Sorgente s : listaSorgenti ) { ControllerSorgente.insSorgente( p, s); }
				
		for( Autore a : listaAutori )
		{
			a.setID( ControllerAutore.esisteAutore(a) );			 
			if( a.getID() < 0 ){ a.setID( ControllerAutore.insAutore(a) ); }		 
			ControllerAutore.insScritto(a, p);
		}
		for( ParolaChiave pc : listaParoleChiave )
		{
			pc.setID( ControllerParolaChiave.esisteParolaChiave(pc) );			 
			if( pc.getID() < 0 ){ pc.setID( ControllerParolaChiave.insParolaChiave(pc) ); }			 
			ControllerParolaChiave.insTag(pc, p);
		}
		return p.getID();
	}
	
	/**
	 * modifica un campo della pubblicazione visualizzata
	 * 
	 * @param campo il campo da modificare
	 * @param nuovoValore il nuovo valore
	 * @return
	 */
	public static boolean modPubb( String campo, String nuovoValore )
	{
		return daoPubblicazione.modificaPubblicazione( pubb.getID(), campo, nuovoValore, ControllerUtente.getUtente().getID() );
	}
	
	/**
	 * elimina la pubblicazione visualizzata dal database
	 */
	public static void delPubb()
	{
		daoPubblicazione.rimuoviPubblicazione( pubb.getID(), ControllerUtente.getUtente().getID() );
		pubb = null;
	}

	/**
	 * inizializza la variabile pubb con la pubblicazione che si vuole visualizzare
	 * 
	 * @param id l'id della pubblicazione che si vuole visualizzare
	 */
	public static void setPubbByID( int id )
	{
		pubb = daoPubblicazione.getPubb( id );
		
		pubb.setListaAutori( ControllerAutore.ListaAutoriPubb(id) );
		pubb.setListaparolechiave( ControllerParolaChiave.getListaParoleChiave(id) );
		pubb.setListaRistampe( ControllerRistampa.getListaRistampe(id) );
		pubb.setListaSorgenti( ControllerSorgente.getListaSorgentiDiPubb(id) );
	}
	
	/**
	 * permette di visualizzare l'elenco completo delle pubblicazioni ordinate per inserimento
	 * 
	 * @param pagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaElencoPubblicazioniOrdinatePerInserimento( int pagina )
	{
		listaPubb = daoPubblicazione.visualizzaElencoPubblicazioniOrdinatePerInserimento( pagina );
	}
	
	/**
	 * permette di visualizzare l'elenco completo delle pubblicazioni ordinate per ultimo aggiornamento
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaElencoPubblicazioniAggiornate( int numeroPagina )
	{
		listaPubb = daoPubblicazione.visualizzaElencoPubblicazioniAggiornate( numeroPagina );
	}
	
	/**
	 * permette di visualizzare l'elenco delle pubblicazioni con un download presente
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaElencoPubbConDownload( int numeroPagina )
	{
		listaPubb = daoPubblicazione.visualizzaElencoPubbConDownload( numeroPagina );
	}
	
	/**
	 * permette di visualizzare l'elenco completo delle pubblicazioni in ordine alfabetico per titolo
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaElencoCatalogo( int numeroPagina )
	{
		listaPubb = daoPubblicazione.visualizzaElencoCatalogo( numeroPagina );
	}
	
	/**
	 * permette di visualizzare l'elenco delle pubblicazioni inserite da un utente
	 * 
	 * @param utente
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaElencoPubbInsDaUtente( UtenteBase utente, int numeroPagina )
	{
		listaPubb = daoPubblicazione.visualizzaElencoPubbInsDaUtente( utente.getID(), numeroPagina );
	}
	
	/**
	 * permette di visualizzare l'elenco delle pubblicazioni con gli stessi autori della pubblicazione passata come parametro
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaElencoPubbConStessiAutori( int numeroPagina )
	{
		listaPubb = daoPubblicazione.visualizzaElencoPubbConStessiAutori( pubb.getID(), numeroPagina );
	}
	
	/**
	 * ricerca la pubblicazione con un dato isbn
	 * 
	 * @param isbn l'isbn passato in input
	 */
	public static void ricercaPerISBN( String isbn )
	{
		listaPubb = daoPubblicazione.ricercaPerISBN( isbn );
	}
	
	/**
	 * ricerca la pubblicazione tramite il titolo
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 * @param titolo il titolo passato in input
	 */
	public static void ricercaPerTitolo( int numeroPagina, String titolo )
	{
		listaPubb = daoPubblicazione.ricercaPerTitolo( numeroPagina, titolo );
	}
	
	/**
	 * restituisce le pubblicazioni con una data parola chiave
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 * @param pc
	 */
	public static void ricercaPerParolaChiave( int numeroPagina, String pc )
	{
		listaPubb = daoPubblicazione.ricercaPerParolaChiave( numeroPagina, pc );
	}
	
	/**
	 * restituisce le pubblicazioni scritte da un autore
	 * 
	 * @param numeroPagina la pagina della lista che si vuole visualizzare
	 * @param nome il nome dell'autore
	 * @param cognome il cognome dell'autore
	 */
	public static void ricercaPerAutore( int numeroPagina, String nome, String cognome )
	{
		listaPubb = daoPubblicazione.ricercaPerAutore( numeroPagina, nome, cognome );
	}

	/**
	 * controlla se esiste una pubblicazione con l'isbn in input nel database
	 * 
	 * @param isbn
	 * @return torna true se esiste l'isbn
	 */
	public static boolean esisteIsbn(String isbn)
	{
		return daoPubblicazione.esisteISBN( isbn );
	}
}
