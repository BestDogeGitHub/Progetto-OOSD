package DAO.interfaces;

import java.util.ArrayList;

import model.Recensione;

public interface DAORecensione {

	public void inserisciRecensione( int idUtente, int idPubb, String Testo);
	
	public void eliminaRecensione ( int idUtente, int idPubb, int idUtenteCheElimina);
	
	public void approvaRecensione( int idApprovatore, int idPubb, int idUtente);
	
	public boolean utenteInseritoRecensione( int idUtente, int idPubb );
	
	public ArrayList<Recensione> visualizzaListaRecensioniApprovatediPubb( int idPubb, int numeroPagina );
	
	public ArrayList<Recensione> visualizzaListaRecensioniNonApprovate( int numeroPagina  );
	
	public ArrayList<Recensione> visualizzaListaRecensionidiUtente(int idUtente, int numeroPagina);
	
}
