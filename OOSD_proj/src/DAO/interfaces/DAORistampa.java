package DAO.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Ristampa;

public interface DAORistampa {

	public void inserisciRistampa( int idUtente, LocalDate dataRistampa, int numero, int idPubb );
	
	public void eliminaRistampa( int idUtente, int idRistampa );
	
	public boolean modificaRistampa(int idRistampa, String campo, String nuovoValore );
	
	public ArrayList<Ristampa> getListaRistampe( int idPubb );
	
	public Ristampa getUltimaRistampa( int idPubb );
	
	public ArrayList<Ristampa> visualizzaListaRistampe( int idPubb, int numeroPagina );
	
}
