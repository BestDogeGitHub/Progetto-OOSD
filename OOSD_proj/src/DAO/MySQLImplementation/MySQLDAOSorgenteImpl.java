package DAO.MySQLImplementation;

import java.util.*;

import model.Sorgente;
import java.sql.*;
import DAO.connectors.MySQLDBConnector;
import DAO.interfaces.DAOSorgente;

public class MySQLDAOSorgenteImpl implements DAOSorgente{	
	
	/**inserimento di una sorgente
	 * @param idPubb
	 * @param URI
	 * @param tipo
	 * @param formato
	 * @param descrizione
	 * @param idUtente
	 */
	public void insSorgente( int idPubb, String URI, String tipo, String formato, String  descrizione, int idUtente )
	{
		
		Connection connessione = null;
		CallableStatement stmt = null;
		try {
		
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call inserimentoSorgente(?,?,?,?,?,?)}"); 
		
			stmt.setInt( 1, idPubb );
			stmt.setString( 2, URI );
			stmt.setString( 3, tipo );
			stmt.setString( 4, formato );
			stmt.setString( 5, descrizione );
			stmt.setInt( 6, idUtente );
			
			stmt.execute();
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/** eliminazione di una sorgente
	 * @param idSorgente
	 * @param idUtente
	 */
	public void delSorgente( int idSorgente, int idUtente )
	{
		
		Connection connessione = null;
		CallableStatement stmt = null;
		try {
			connessione = MySQLDBConnector.creaConnessione();
		
			stmt = connessione.prepareCall("{call delSorgente(?,?)}"); 
			stmt.setInt( 1, idSorgente );
			stmt.setInt( 2, idUtente );
		
			stmt.execute();
			
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**modifica di una sorgente
	 * @param idSorgente
	 * @param campo
	 * @param nuovoValore
	 */
	public void modSorgente( int idSorgente, String campo, String nuovoValore )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		try {
		
		connessione = MySQLDBConnector.creaConnessione();
		String query;
		
		if( campo.equalsIgnoreCase("URI") ) { query = "UPDATE sorgente SET URI = ? WHERE ID = ?"; }
		else if( campo.equalsIgnoreCase("Tipo") ) { query = "UPDATE sorgente SET Tipo = ? WHERE ID = ?"; }
		else if( campo.equalsIgnoreCase("Formato") ) { query = "UPDATE sorgente SET Formato = ? WHERE ID = ?"; }
		else if( campo.equalsIgnoreCase("Descrizione") ) { query = "UPDATE sorgente SET Descrizione = ? WHERE ID = ?"; }
		else return;
		
		stmt = connessione.prepareStatement( query );
		
		stmt.setString( 1, nuovoValore);
		stmt.setInt( 2, idSorgente );
		
		
		stmt.executeUpdate();
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/** restituisce l'intera lista delle sorgenti
	 * @param idPubb
	 * @return
	 */
	public ArrayList<Sorgente> getListaSorgentiDiPubb( int idPubb )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT sorgente.* FROM sorgente WHERE IDpubblicazione = ?";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			rs = stmt.executeQuery();
	    
			return ritornaListaSorgenti(rs);
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}return null;
	}

	/**visualizza le sorgenti di una pubblicazione 30 alla volta
	 * @param idPubb
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<Sorgente> visualizzaListaSorgentiDiPubb( int idPubb , int numeroPagina )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int entrySaltate = numeroPagina * 30;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT sorgente.* FROM sorgente WHERE IDPubblicazione = ? LIMIT ?,30";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			stmt.setInt(2, entrySaltate);
			rs = stmt.executeQuery();
	    
			return ritornaListaSorgenti(rs);
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}return null;
	}
	
	private ArrayList<Sorgente> ritornaListaSorgenti( ResultSet rs ) throws SQLException
	{
		ArrayList<Sorgente> lista = new ArrayList<Sorgente>();
		
		while( rs.next() )
	    {
			Sorgente a = new Sorgente( rs );
	    	lista.add(a);
	    }
		return lista;
	}
}