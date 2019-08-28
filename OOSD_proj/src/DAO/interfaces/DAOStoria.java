package DAO.interfaces;

import java.util.ArrayList;

import model.EntryStoria;

public interface DAOStoria {

	public ArrayList<EntryStoria> visualizzaLog ( int numeroPaginadelLog );
	
	public ArrayList<EntryStoria> visualizzaLogModifichePubb ( int numeroPaginadelLog, int idPubb );
	
}
