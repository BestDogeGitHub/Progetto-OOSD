package DAO.MySQLImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DAO.interfaces.DAOParolaChiave;
import DAO.connectors.MySQLDBConnector;
import model.ParolaChiave;

public class MySQLDAOParolaChiaveImpl implements DAOParolaChiave {
	
	/** controlla che nel database ci sia la parola chiave
	 * @param parolaChiave
	 * @return
	 */
	public int controllaParolaChiave( String parolaChiave )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
				
			String query = "SELECT parolachiave.* FROM parolachiave WHERE ParolaChiave = ?";
				
			stmt = connessione.prepareStatement( query );
				
			stmt.setString(1, parolaChiave);
				
			rs = stmt.executeQuery();
			     
			if( !(rs.next()) )  return -1; 
			return rs.getInt("ID");

		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return -1;
	}
			
	/**inserisce la parola chiave nel database  e ritorna il suo id, torna -1 se l'inserimento non ha successo
	 * @param parolaChiave
	 * @return
	 */
	public int inserisciParolaChiave( String parolaChiave )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
		
			connessione = MySQLDBConnector.creaConnessione();
				
			if( controllaParolaChiave(parolaChiave) >= 0 )  { return -1; }
			else
			{
				String query ="INSERT INTO parolachiave (ParolaChiave) VALUES (?)";
				stmt = connessione.prepareStatement( query,  Statement.RETURN_GENERATED_KEYS );
				stmt.setString(1, parolaChiave);
				stmt.executeUpdate();
				
				rs = stmt.getGeneratedKeys() ;
			    if (rs.next())  return rs.getInt(1);
			}
		} catch (SQLException ex) {} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return -1;
	}
			
	/**aggiunge un autore ad una pubblicazione torna falso se è già presente
	 * @param idParolaChiave
	 * @param idPubb
	 * @param idUtente
	 * @return
	 */
	public boolean aggiungiParolaChiaveAPubb( int idParolaChiave, int idPubb, int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;

		try {
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt=connessione.prepareCall("{call inserimentoParolaChiave(?,?,?)}"); 
			stmt.setInt(1, idPubb);
			stmt.setInt(2, idParolaChiave);
			stmt.setInt(3, idUtente);
			stmt.execute();
			return true;
			
		} catch (SQLException ex) {} finally {
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { connessione.close(); } catch (Exception e) { /* ignored */ }
			}
		return false;
	}
			
	/**rimuove un autore da una pubblicazione
	 * @param idParolaChiave
	 * @param idPubb
	 * @param idUtente
	 */
	public void rimuoviParolaChiaveAPubb( int idParolaChiave, int idPubb, int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;

		try {
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt=connessione.prepareCall("{call delParolaChiave(?,?,?)}"); 

			stmt.setInt(1, idPubb);
			stmt.setInt(2, idParolaChiave);
			stmt.setInt(3, idUtente);
	
			stmt.execute();
		} catch (SQLException ex) {} finally {
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
		
	/**modifico o il nome o il cognome di un autore dato il suo id
	 * @param idParolaChiave
	 * @param nuovoValore
	 */
	public void modificaParolaChiave( int idParolaChiave, String nuovoValore )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		
		try 
		{
		connessione = MySQLDBConnector.creaConnessione();
				
		String query ="UPDATE parolachiave SET ParolaChiave = ? WHERE ID = ?;";
		stmt = connessione.prepareStatement( query );
		stmt.setString(1, nuovoValore);
		stmt.setInt(2, idParolaChiave);
		stmt.executeUpdate();
		
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	/**cancello un autore dal database
	 * @param idParolaChiave
	 * @param idUtente
	 */
	public void cancellaParolaChiave( int idParolaChiave, int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt = connessione.prepareCall("{call delCascadeTag(?,?)}"); 
			stmt.setInt(1, idParolaChiave);
			stmt.setInt(2, idUtente);
			stmt.execute();
			
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	/**dato l'id di una pubblicazione ottengo la sua lista di autori
	 * @param idPubb
	 * @return
	 */
	public ArrayList<ParolaChiave> getListaParoleChiave( int idPubb )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();

			String query =  "SELECT pc.*\r\n" + 
							"FROM pubblicazione p\r\n" + 
							"INNER JOIN tag t\r\n" + 
							"ON p.ID = t.IDPubblicazione\r\n" + 
							"INNER JOIN parolachiave pc\r\n" + 
							"ON t.IDParolaChiave = pc.ID\r\n" + 
							"WHERE p.ID = ?";
			
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			rs = stmt.executeQuery();

			return ritornaListaParoleChiave(rs);
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}

	/**dati gli id di un autore e l'id di una pubblicazione controlla se l'autore a scritto quella pubblicazione
	 * @param idParolaChiave
	 * @param idPubb
	 * @return
	 */
	public boolean parolaChiaveTagPubb( int idParolaChiave, int idPubb)
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();

			String query = "SELECT tag.* FROM tag WHERE IDParolaChiave = ? AND IDPubblicazione = ?";

			stmt = connessione.prepareStatement( query );

			stmt.setInt(1, idParolaChiave);
			stmt.setInt(2, idPubb);

			rs = stmt.executeQuery();

			if( rs.isBeforeFirst() ) { return true; }
			return false;
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return false;
	}

	/**visualizza tutti gli autori della pubblicazione 30 alla volta
	 * @param idPubb
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<ParolaChiave> visualizzaListaParolaChiavediPubb( int idPubb, int numeroPagina )
		{
			int entrySaltate = numeroPagina * 30;
			
			Connection connessione = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try 
			{
				connessione = MySQLDBConnector.creaConnessione();
			
				String query =  "SELECT pc.* " + 
								"FROM pubblicazione p " + 
								"INNER JOIN tag t " + 
								"ON p.ID = t.IDPubblicazione " + 
								"INNER JOIN parolachiave pc " + 
								"ON t.IDParolaChiave = pc.ID " + 
								"WHERE p.ID = ? " +
								"LIMIT ?,30 ";
				stmt = connessione.prepareStatement( query );
				stmt.setInt(1, idPubb);
				stmt.setInt(2, entrySaltate);
				
				rs = stmt.executeQuery();
		    
				return ritornaListaParoleChiave(rs);
				
			} catch (SQLException ex) { ex .printStackTrace();} finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { stmt.close(); } catch (Exception e) { /* ignored */ }
			    try { connessione.close(); } catch (Exception e) { /* ignored */ }
			} return null;
		}
		
	/**visualizza tutti gli autori del database 30 alla volta
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<ParolaChiave> visualizzaListaParolaChiaveCompleta( int numeroPagina )
	{
		int entrySaltate = numeroPagina * 30;
			
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = "SELECT * FROM parolachiave LIMIT ?,30";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, entrySaltate);
			
			rs = stmt.executeQuery();
		    
			return ritornaListaParoleChiave(rs);
			
		} catch (SQLException ex) { ex .printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	private ArrayList<ParolaChiave> ritornaListaParoleChiave( ResultSet rs ) throws SQLException
	{
		ArrayList<ParolaChiave> lista = new ArrayList<ParolaChiave>();
		String parolaChiave;
		int id;
		
		while( rs.next() )
	    {
			id = rs.getInt("ID");
			parolaChiave = rs.getString("ParolaChiave");
			ParolaChiave a = new ParolaChiave(id, parolaChiave );
			lista.add(a);
	    }
		
		return lista;
	}
}
