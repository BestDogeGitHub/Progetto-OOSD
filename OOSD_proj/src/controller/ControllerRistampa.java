package controller;

import java.util.ArrayList;
import DAO.MySQLImplementation.MySQLDAORistampaImpl;
import DAO.interfaces.DAORistampa;
import model.*;

public class ControllerRistampa {
	private static DAORistampa daoRistampa = new MySQLDAORistampaImpl();
	/**
	 * conserva la lista di ristampe visualizzate
	 */
	private static ArrayList<Ristampa> listaRistampe;
	
	public static ArrayList<Ristampa> getListaRistampe() { return listaRistampe; }
	
	public static Ristampa getElementoLista(int posizione) { return listaRistampe.get(posizione); }
	
	/**
	 * inserisce la ristampa in input alla pubblicazione
	 * @param ristampa
	 * @param pubb
	 */
	public static void insRistampa( Ristampa ristampa, Pubblicazione pubb )
	{
		daoRistampa.inserisciRistampa( ControllerUtente.getUtente().getID(), ristampa.getDataRistampa(), ristampa.getNumero(), pubb.getID() );
	}
	
	/**
	 * elimina una ristampa
	 * 
	 * @param ristampa
	 */
	public static void delRistampa( Ristampa ristampa )
	{
		daoRistampa.eliminaRistampa( ControllerUtente.getUtente().getID(), ristampa.getID() );
	}
	
	/**
	 * permette di modificare una ristampa
	 * 
	 * @param ristampa la ristampa da modificare
	 * @param campo il campo da modificare
	 * @param nuovoValore il nuovo valore
	 * @return
	 */
	public static boolean modRistampa( Ristampa ristampa, String campo, String nuovoValore )
	{
		return daoRistampa.modificaRistampa( ristampa.getID(), campo, nuovoValore );
	}
	
	/**
	 * visualizza la lista ristampe di una pubblicazione
	 * 
	 * @param pubb
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaListaRistampe( Pubblicazione pubb, int numeroPagina ) 
	{
		listaRistampe = daoRistampa.visualizzaListaRistampe( pubb.getID(), numeroPagina ); 
	}
	
	/**
	 * restituisce l'ultima ristampa di una pubblicazione dato il suo id
	 * @param idPubb
	 * @return
	 */
	public static Ristampa getUltimaRistampa( int idPubb )
	{
		return daoRistampa.getUltimaRistampa(idPubb);
	}
	
	public static ArrayList<Ristampa> getListaRistampe( int idPubb )
	{
		return daoRistampa.getListaRistampe(idPubb);
	}
	
}