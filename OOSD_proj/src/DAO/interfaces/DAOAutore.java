package DAO.interfaces;

import java.util.ArrayList;

import model.Autore;

public interface DAOAutore {
	
	public int controllaAutore( String nome, String cognome );
	
	public int controllaAutore( String nome );
	
	public int inserisciAutore( String nome, String cognome );
	
	public int inserisciAutore( String nome );
	
	public boolean aggiungiAutoreAPubb( int idAutore, int idPubb, int idUtente );
	
	public void rimuoviAutoreAPubb( int idAutore, int idPubb, int idUtente );
	
	public boolean modificaAutore( int idAutore, String campo, String nuovoValore );
	
	public void cancellaAutore( int idAutore, int idUtente );
	
	public ArrayList<Autore> getListaAutori( int idPubb );
	
	public boolean autoreScrittoPubb( int idAutore, int idPubb);
	
	public ArrayList<Autore> visualizzaListaAutoridiPubb( int idPubb, int numeroPagina );
	
	public ArrayList<Autore> visualizzaListaAutoriCompleta( int numeroPagina );

}
