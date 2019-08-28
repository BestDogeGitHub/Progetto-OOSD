package DAO.interfaces;

import java.util.ArrayList;

import model.ParolaChiave;

public interface DAOParolaChiave {
		
	public int controllaParolaChiave( String parolaChiave );
	
	public int inserisciParolaChiave( String parolaChiave );
	
	public boolean aggiungiParolaChiaveAPubb( int idParolaChiave, int idPubb, int idUtente );
	
	public void rimuoviParolaChiaveAPubb( int idParolaChiave, int idPubb, int idUtente );
	
	public void modificaParolaChiave( int idParolaChiave, String nuovoValore );
	
	public void cancellaParolaChiave( int idParolaChiave, int idUtente );
	
	public ArrayList<ParolaChiave> getListaParoleChiave( int idPubb );
	
	public boolean parolaChiaveTagPubb( int idParolaChiave, int idPubb);
	
	public ArrayList<ParolaChiave> visualizzaListaParolaChiavediPubb( int idPubb, int numeroPagina );
	
	public ArrayList<ParolaChiave> visualizzaListaParolaChiaveCompleta( int numeroPagina );
		
}
