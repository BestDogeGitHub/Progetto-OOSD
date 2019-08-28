package DAO.interfaces;

public interface DAOLike {

	public boolean utenteLikePubb( int idUtente, int idPubb);
	
	public boolean aggiungiLikeAPubb( int idUtente, int idPubb );
	
	public void rimuoviLikeAPubb( int idUtente, int idPubb );
	
}
