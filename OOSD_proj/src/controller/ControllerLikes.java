package controller;

import DAO.interfaces.DAOLike;

public class ControllerLikes {
	
	private static DAOLike daoLike;
	public static void setDao( DAOLike dao )
	{
		daoLike = dao;
	}
	/**
	 * controlla se l'utente connesso ha messo like alla pubblicazione visualizzata
	 * 
	 * @return torna true se l'utente ha messo like alla pubblicazione
	 */
	public static boolean controllaUtenteLikePubb()
	{
		return daoLike.utenteLikePubb( ControllerUtente.getUtente().getID(), ControllerPubblicazione.getPubb().getID() );
	}

	/**
	 * l'utente connesso inserisce il like alla pubblicazione visualizzata
	 * 
	 * @return torna true se va a buon fine, false viceversa
	 */
	public static boolean insLikeAPubb()
	{
		return daoLike.aggiungiLikeAPubb( ControllerUtente.getUtente().getID(), ControllerPubblicazione.getPubb().getID() );
	}
	
	/**
	 * l'utente connesso elimina il like alla pubblicazione visualizzata 
	 */
	public static void delLikeAPubb()
	{
		daoLike.rimuoviLikeAPubb( ControllerUtente.getUtente().getID(), ControllerPubblicazione.getPubb().getID() );
	}
}
