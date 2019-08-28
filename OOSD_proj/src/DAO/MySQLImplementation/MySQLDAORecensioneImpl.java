package DAO.MySQLImplementation;

import DAO.connectors.MySQLDBConnector;
import DAO.interfaces.DAORecensione;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import model.Recensione;

public class MySQLDAORecensioneImpl implements DAORecensione {
	
	/**inserisci una recensione nel database
	 * @param idUtente
	 * @param idPubb
	 * @param Testo
	 */
	public void inserisciRecensione( int idUtente, int idPubb, String Testo) 
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call inserimentoRecensione(?,?,?)}"); 
			stmt.setInt(1, idUtente);
			stmt.setInt(2, idPubb);
			stmt.setString(3, Testo);
			stmt.execute();
			
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/** elimina una recensione dal database
	 * @param idUtente
	 * @param idPubb
	 * @param idUtenteCheElimina
	 */
	public void eliminaRecensione ( int idUtente, int idPubb, int idUtenteCheElimina)
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call delRecensione(?,?,?)}"); 
			stmt.setInt(1, idUtente);
			stmt.setInt(2, idPubb);
			stmt.setInt(3, idUtenteCheElimina);
			stmt.execute();
			
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**permette di approvare una recensione da parte di un moderatore
	 * @param idApprovatore
	 * @param idPubb
	 * @param idUtente
	 */
	public void approvaRecensione( int idApprovatore, int idPubb, int idUtente) 
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call ApprovazioneRecensione(?,?,?)}"); 
			stmt.setInt(1, idApprovatore);
			stmt.setInt(2, idPubb);
			stmt.setInt(3, idUtente);
			stmt.execute();
			
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		
	}
	
	/** torna true se l'utente ha scritto una recensione per quella pubblicazione
	 * @param idUtente
	 * @param idPubb
	 * @return
	 */
	public boolean utenteInseritoRecensione( int idUtente, int idPubb )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = " SELECT IDUtente FROM recensione WHERE IDPubblicazione = ? && IDUtente = ? ";
	    
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb );
			stmt.setInt(2, idUtente );
			rs = stmt.executeQuery();
			
			if ( rs.next() ) return true;
			else return false;
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return false;
	}
	
	private ArrayList<Recensione> ritornaListaRecensioni( ResultSet rs ) throws SQLException
	{
		ArrayList<Recensione> lista = new ArrayList<Recensione>();
		int iDUtente,idPubb, approvatoDa;
		String descrizione,nickname;
		boolean flagApprovazione; 
		LocalDateTime timestamp;
		
		while( rs.next() )
    	{
			idPubb = rs.getInt("IDPubblicazione");
			iDUtente = rs.getInt("IDUtente");
			approvatoDa = rs.getInt("ApprovatoDa");
			nickname = rs.getString("Nickname");
			descrizione = rs.getString("Descrizione");
			timestamp = rs.getObject("Timestamp", LocalDateTime.class );
			flagApprovazione = rs.getBoolean("FlagApprovazione");
			Recensione a = new Recensione( idPubb, iDUtente, nickname, descrizione, flagApprovazione, approvatoDa, timestamp);
			lista.add(a);
    	}
		
		return lista;
	}
	
	/**ritorna la lista di recensioni di una pubblicazione dato il suo id
	 * 
	 * @param idPubb
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<Recensione> visualizzaListaRecensioniApprovatediPubb( int idPubb, int numeroPagina )
	{
		int entrySaltate = numeroPagina * 30;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  " SELECT r.*,u.Nickname FROM recensione r INNER JOIN utente u ON u.ID = r.IDUtente "
						  + " WHERE IDPubblicazione = ? && FlagApprovazione = 1 ORDER BY `Timestamp` LIMIT ?,30; ";
		
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			stmt.setInt(2, entrySaltate);
			rs = stmt.executeQuery();
	    
			return ritornaListaRecensioni(rs);
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}

	/**ritorna la lista completa delle recensioni non approvate
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<Recensione> visualizzaListaRecensioniNonApprovate( int numeroPagina  )
	{
		int entrySaltate = numeroPagina * 30;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = " SELECT r.*,u.Nickname FROM recensione r INNER JOIN utente u ON u.ID = r.IDUtente WHERE FlagApprovazione = 0 LIMIT ?,30";
	    
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, entrySaltate);
			rs = stmt.executeQuery();
			
			return ritornaListaRecensioni(rs);
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}

	public ArrayList<Recensione> visualizzaListaRecensionidiUtente(int idUtente, int numeroPagina)
	{
		int entrySaltate = numeroPagina * 30;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			String query =  "SELECT r.*,u.Nickname FROM recensione r INNER JOIN utente u ON u.ID = r.IDUtente WHERE IDUtente = ? ORDER BY `Timestamp` LIMIT ?,30;";
		
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idUtente);
			stmt.setInt(2, entrySaltate);
			rs = stmt.executeQuery();
	    
			return ritornaListaRecensioni(rs);
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
}
