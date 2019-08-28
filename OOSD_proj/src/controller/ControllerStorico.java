package controller;

import java.util.ArrayList;
import DAO.interfaces.DAOStoria;
import model.EntryStoria;
import model.Pubblicazione;

public class ControllerStorico {
	private static DAOStoria daoStoria;
	public static void setDao( DAOStoria dao )
	{
		daoStoria = dao;
	}
	/**
	 * conserva la pagina dello storico al momento visualizzata
	 */
	private static ArrayList<EntryStoria> storico;
	
	public static ArrayList<EntryStoria> getStorico() { return storico; }
	
	/**
	 * usato per ottenere una determinata pagina del log completo
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 */
	public static void visualizzaLog( int numeroPagina )
	{
		storico = daoStoria.visualizzaLog(numeroPagina);
	}
	
	/**
	 * usato per ottenere una determinata pagina del log di modifiche di una particolare pubblicazione
	 * 
	 * @param numeroPagina il numero della pagina della lista che si vuole visualizzare
	 * @param pubb
	 */
	public static void visualizzaLogModifichePubb( int numeroPagina, Pubblicazione pubb)
	{
		storico = daoStoria.visualizzaLogModifichePubb(numeroPagina, pubb.getID());
	}
}
