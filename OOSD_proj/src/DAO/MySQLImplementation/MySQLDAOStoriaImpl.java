package DAO.MySQLImplementation;

import java.util.*;
import java.sql.*;
import java.time.*;
import DAO.connectors.MySQLDBConnector;
import DAO.interfaces.DAOStoria;
import model.EntryStoria;

public class MySQLDAOStoriaImpl implements DAOStoria{
	
	/**restituisce una pagina del log di modifiche a tutte le pubblicazioni, ogni pagina contiene 30 entry
	 * @param numeroPaginadelLog
	 * @return
	 */
	public ArrayList<EntryStoria> visualizzaLog ( int numeroPaginadelLog )
	{
		ArrayList<EntryStoria> log = new ArrayList<EntryStoria>();
		int entrySaltate = numeroPaginadelLog * 30;
		LocalDateTime timestamp;
		String descrizione;
		int ID, idPubb, idUtente, tipo;
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT storia.* " + 
							"FROM storia " + 
							"ORDER BY ID DESC " + 
							"LIMIT ?,30";
		
			stmt = connessione.prepareStatement( query );
			stmt.setInt( 1, entrySaltate );
			rs = stmt.executeQuery();
	    
			while( rs.next() )
			{
				ID = rs.getInt("ID");
				idPubb = rs.getInt("IDPubblicazione");
				idUtente = rs.getInt("IDUtente");
				timestamp = rs.getObject("Timestamp", LocalDateTime.class);
				descrizione = rs.getString("Descrizione");
				tipo = rs.getInt("Tipo");
				EntryStoria a = new EntryStoria( ID, idPubb, idUtente, timestamp, descrizione, tipo );
				log.add(a);
			}
		} catch (SQLException ex) {
		} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return log;
	}

	/**restituisce una pagina del log di modifiche ad una pubblicazione, ogni pagina contiene 30 entry
	 * @param numeroPaginadelLog
	 * @param idPubb
	 * @return
	 */
	public ArrayList<EntryStoria> visualizzaLogModifichePubb ( int numeroPaginadelLog, int idPubb ) 
	{
		ArrayList<EntryStoria> log = new ArrayList<EntryStoria>();
		int entrySaltate = numeroPaginadelLog * 30;
		LocalDateTime timestamp;
		String descrizione;
		int ID, idUtente, tipo;
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT * FROM storia WHERE ( Tipo = 3 || Tipo = 1 ) && IDPubblicazione = ? ORDER BY ID DESC LIMIT ?,30;";
			
			stmt = connessione.prepareStatement( query );
			stmt.setInt( 1, idPubb );
			stmt.setInt( 2, entrySaltate );
			rs = stmt.executeQuery();
	    
			while( rs.next() )
			{
				ID = rs.getInt("ID");
				idUtente = rs.getInt("IDUtente");
				timestamp = rs.getObject("Timestamp", LocalDateTime.class);
				descrizione = rs.getString("Descrizione");
				tipo = rs.getInt("Tipo");
				EntryStoria a = new EntryStoria( ID, idPubb, idUtente, timestamp, descrizione, tipo );
				log.add(a);
			}
		} catch (SQLException ex) {
		} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return log;
	}
}
