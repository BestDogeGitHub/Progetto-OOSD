package DAO.MySQLImplementation;

import DAO.connectors.MySQLDBConnector;
import DAO.interfaces.DAOLike;

import java.sql.*;

public class MySQLDAOLikeImpl implements DAOLike {
	
	/**dato l'id di un utente e di una pubblicazione torna true se l'utente ha messo like alla pubblicazione
	 * @param idUtente
	 * @param idPubb
	 * @return
	 */
	public boolean utenteLikePubb( int idUtente, int idPubb)
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
		
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = "SELECT `like`.* FROM `like`WHERE IDUtente = ? AND IDPubblicazione = ?";
		
			stmt = connessione.prepareStatement( query );
		
			stmt.setInt(1, idUtente);
			stmt.setInt(2, idPubb);
		
			rs = stmt.executeQuery();
			if( rs.isBeforeFirst() ) { return true; }
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return false;
	}
	
	/**aggiungo un like alla pubblicazione se non è già presente
	 * @param idUtente
	 * @param idPubb
	 * @return
	 */
	public  boolean aggiungiLikeAPubb( int idUtente, int idPubb )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try 
		{
		
		if( utenteLikePubb( idUtente, idPubb ) ) return false;
		
			connessione = MySQLDBConnector.creaConnessione();
		
			stmt=connessione.prepareCall("{call inserimentoLike(?,?)}"); 
		
			stmt.setInt(1, idUtente);
			stmt.setInt(2, idPubb);
		
			stmt.execute();
			return true;
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return false;
	}
	
	/**rimuovo il like dell'utente dalla pubblicazione
	 * @param idUtente
	 * @param idPubb
	 */
	public  void rimuoviLikeAPubb( int idUtente, int idPubb )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			stmt = connessione.prepareCall("{call delLike(?,?)}"); 

			stmt.setInt(1, idUtente);
			stmt.setInt(2, idPubb);
	
			stmt.execute();
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
}
