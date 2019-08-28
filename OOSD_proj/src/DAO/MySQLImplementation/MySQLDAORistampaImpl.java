package DAO.MySQLImplementation;

import java.time.*;
import java.sql.*;
import java.util.ArrayList;

import DAO.connectors.MySQLDBConnector;
import DAO.interfaces.DAORistampa;
import model.Ristampa;

public class MySQLDAORistampaImpl implements DAORistampa{
	
	/**inserisce una ristampa
	 * @param idUtente
	 * @param dataRistampa
	 * @param numero
	 * @param idPubb
	 */
	public void inserisciRistampa( int idUtente, LocalDate dataRistampa, int numero, int idPubb )
	{
		Connection connessione = null;
		CallableStatement stmt = null;

		try {
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call inserimentoRistampa(?,?,?,?)}"); 
			
			stmt.setInt(1, idPubb);
			stmt.setObject(2, dataRistampa);
			stmt.setInt(3, numero);
			stmt.setInt(4, idUtente);
	    
			stmt.execute();
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**elimina una ristampa
	 * @param idUtente
	 * @param idRistampa
	 */
	public void eliminaRistampa( int idUtente, int idRistampa )
	{
		Connection connessione = null;
		CallableStatement stmt = null;

		try {
			connessione = MySQLDBConnector.creaConnessione();
		
			stmt=connessione.prepareCall("{call delRistampa(?,?)}"); 
		
			stmt.setInt(1, idRistampa);
			stmt.setInt(2, idUtente);
	    
			stmt.execute();
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	/**modificare una ristampa
	 * @param idRistampa
	 * @param campo
	 * @param nuovoValore
	 * @return
	 */
	public boolean modificaRistampa(int idRistampa, String campo, String nuovoValore )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		String query;
		try {
		connessione = MySQLDBConnector.creaConnessione();
		
		if( campo.equalsIgnoreCase("dataristampa") ) 
		{ 
			query = "UPDATE ristampa SET DataRistampa = ? WHERE ID = ?"; 
			stmt = connessione.prepareStatement( query );
			LocalDate localDate = LocalDate.parse( nuovoValore ) ;
			stmt.setObject( 1, localDate );
		}
		else if( campo.equalsIgnoreCase("numero") ) 
		{ 
			query = "UPDATE ristampa SET Numero = ? WHERE ID = ?"; 
			stmt = connessione.prepareStatement( query );
			int x = Integer.parseInt( nuovoValore );
			stmt.setInt( 1, x );
		}
		else return false;
		
		stmt.setInt( 2, idRistampa );
		stmt.executeUpdate();
		
		return true;
		} catch (SQLException ex) { ex.printStackTrace(); return false; } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**restituisce la lista delle ristampe di una pubblicazione dato il suo id
	 * @param idPubb
	 * @return
	 */
	public ArrayList<Ristampa> getListaRistampe( int idPubb )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT ristampa.* FROM ristampa WHERE IDPubblicazione = ?";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			rs = stmt.executeQuery();
	    
			return ritornaListaRistampe(rs);
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}return null;
	}

	/**un metodo per restituire l'ultima ristampa di una pubblicazione dato il suo id
	 * @param idPubb
	 * @return
	 */
	public Ristampa getUltimaRistampa( int idPubb )
	{
		Ristampa ultimaRistampa = null;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT * FROM ristampa WHERE IDPubblicazione = ? ORDER BY DataRistampa DESC LIMIT 1";
		
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			rs = stmt.executeQuery();
	    
			if( rs.next() ) { ultimaRistampa = new Ristampa( rs ); }
		
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return ultimaRistampa;
	}

	/**metodo per visualizzare le ristampe di una pubblicazione 30 alla volta
	 * @param idPubb
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<Ristampa> visualizzaListaRistampe( int idPubb, int numeroPagina )
	{
		int entrySaltate = numeroPagina * 30;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT ristampa.* FROM ristampa WHERE IDPubblicazione = ? LIMIT ?,30";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			stmt.setInt(2, entrySaltate);
			rs = stmt.executeQuery();
	    
			return ritornaListaRistampe(rs);
	    
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;		
	}
	
	private ArrayList<Ristampa> ritornaListaRistampe(ResultSet rs) throws SQLException
	{
		ArrayList<Ristampa> lista = new ArrayList<Ristampa>();
		int numero,id;
		LocalDate dataRistampa;
		
		 while( rs.next() )
		 {
		    id = rs.getInt("ID");
		    numero = rs.getInt("Numero");
		    dataRistampa = rs.getObject("DataRistampa", LocalDate.class);
		    Ristampa a = new Ristampa(id,dataRistampa,numero);
		    lista.add(a);
		 }
		 return lista;
	}
}