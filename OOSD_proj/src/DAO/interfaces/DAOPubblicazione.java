package DAO.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import model.ObservablePubblicazioni;
import model.Pubblicazione;

public interface DAOPubblicazione {
	
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubblicazioniOrdinatePerInserimento( int pagina );
	
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubblicazioniAggiornate( int pagina );
	
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubbConDownload( int pagina );
	
	public ArrayList<ObservablePubblicazioni> visualizzaElencoCatalogo( int pagina );
	
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubbInsDaUtente( int idUtente, int pagina );
	
	public Pubblicazione getPubb( int idPubb );
	
	public ArrayList<ObservablePubblicazioni> ricercaPerISBN( String ISBN );
	
	public ArrayList<ObservablePubblicazioni> ricercaPerTitolo( int pagina, String titolo );
	
	public ArrayList<ObservablePubblicazioni> ricercaPerParolaChiave( int pagina, String parola );
	
	public ArrayList<ObservablePubblicazioni> ricercaPerAutore( int pagina, String nome, String cognome );
	
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubbConStessiAutori( int idPubb, int pagina );
	
	public int inserisciPubb(int idUtente, String iSBN, String titolo, String editore, LocalDate dataPubblicazione, 
			int npag, String lingua, String descrizione,String indice);
	
	public boolean modificaPubblicazione( int idPubb,String campo, String nuovoValore, int idUtente );
	
	public void rimuoviPubblicazione( int idPubb, int idUtente );
	
	public boolean esisteISBN( String ISBN );
}
