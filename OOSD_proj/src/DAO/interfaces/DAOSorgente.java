package DAO.interfaces;

import java.util.ArrayList;

import model.Sorgente;

public interface DAOSorgente {

	public void insSorgente( int idPubb, String URI, String tipo, String formato, String  descrizione, int idUtente );
	
	public void delSorgente( int idSorgente, int idUtente );
	
	public void modSorgente( int idSorgente, String campo, String nuovoValore );
	
	public ArrayList<Sorgente> getListaSorgentiDiPubb( int idPubb );
	
	public ArrayList<Sorgente> visualizzaListaSorgentiDiPubb( int idPubb , int numeroPagina );
	
}
