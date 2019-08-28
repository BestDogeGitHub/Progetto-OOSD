package DAO.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import model.UtenteBase;
import model.UtenteModeratore;

public interface DAOUtente {
	
	public UtenteBase getUtenteDaEmail( String email );
	
	public UtenteBase getUtenteDaID( int idUtente );
	
	public UtenteBase getUtenteDaUsername( String username );
	
	public boolean registraUtente( String nickname, String password, String nome, String cognome, LocalDate 
			dataNascita, String luogoNascita, String residenza, String email );
	
	public ArrayList<UtenteModeratore> visualizzaListaUtentiCollaborativi(int numeroPagina);
	
	public void recUtenteAUtenteBase( int idUtente );
	
	public void promuoviUtenteBaseAModeratore( int UtentePromotore, int idUtentePromosso );
	
	public void promuoviModeratoreAdAmministratore( int UtentePromotore, int idUtentePromosso );
	
	public void recAmministratoreAdModeratore( int idUtente );
	
	public void eliminazioneUtente( int idUtente );
	
	public ArrayList<UtenteBase> visualizzaListaUtentiOrdinatiPerUsername( int numeroPagina );
	
	public void modificaDatiUtente( int idUtente, String campo, String nuovoValore);
	
	public boolean esisteUsername( String username );
	
	public boolean esisteEmail( String email );
	
	public ArrayList<String> getListaCittà();
	
}
