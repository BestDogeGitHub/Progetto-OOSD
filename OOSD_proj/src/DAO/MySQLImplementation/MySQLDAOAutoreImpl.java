package DAO.MySQLImplementation;


import DAO.connectors.MySQLDBConnector;
import DAO.interfaces.DAOAutore;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class MySQLDAOAutoreImpl implements DAOAutore {
	
    /**controlla se l'autore è presente nel database, torna il suo id se è presente,altrimenti torna -1
     * @param nome
     * @param cognome
     * @return
     */
    public int controllaAutore( String nome, String cognome )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int id;
		String query;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			if ( cognome == null ) query = "SELECT autore.* FROM autore WHERE nome = ? AND cognome IS NULL " ;
			else query = "SELECT autore.* FROM autore WHERE nome = ? AND cognome = ?";
		
			stmt = connessione.prepareStatement( query );
		
			stmt.setString( 1, nome );
			if ( !(cognome == null) ) stmt.setString( 2, cognome );
		
			rs = stmt.executeQuery();
	     
			if( !(rs.next()) ) { return -1; }
			id = rs.getInt("ID");
			return id;
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return -1;
	}
	
	/**controllo simile nel caso in cui l'autore non abbia un cognome ma sono un nome, torna il suo id se è presente,altrimenti torna -1
	 * @param nome
	 * @return
	 */
	public int controllaAutore( String nome )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int id;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
		
			String query = "SELECT autore.* FROM autore WHERE nome = ? AND cognome IS NULL";
		
			stmt = connessione.prepareStatement( query );
		
			stmt.setString(1, nome);
		
			rs = stmt.executeQuery();
	     
			if( !(rs.next()) ) { return -1; }
			id = rs.getInt("ID");
			return id;
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return -1;
	}
	
	/** inserisce un autore nel database e ritorna il suo id, torna -1 se l'inserimento non ha successo
	 * @param nome
	 * @param cognome
	 * @return
	 */
	public int inserisciAutore( String nome, String cognome )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
				connessione = MySQLDBConnector.creaConnessione();
				String query ="INSERT INTO autore (nome,cognome) VALUES (?,?)";
				stmt = connessione.prepareStatement(query, Statement.RETURN_GENERATED_KEYS );
				stmt.setString(1, nome);
				stmt.setString(2, cognome);
				stmt.executeUpdate();
				rs = stmt.getGeneratedKeys() ;
				
			    if (rs.next())  return rs.getInt(1);
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return -1;
	}
	
	/**inserisci autore con solo il nome in cui è contenuto il nome d'arte e ritorna il suo id, torna -1 se l'inserimento non ha successo
	 * @param nome
	 * @return
	 */
	public int inserisciAutore( String nome )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();

				String query ="INSERT INTO autore (nome) VALUES (?)";
				stmt = connessione.prepareStatement( query,  Statement.RETURN_GENERATED_KEYS );
				stmt.setString(1, nome);
				stmt.executeUpdate();
				rs = stmt.getGeneratedKeys() ;
				
			    if (rs.next())  return rs.getInt(1);

		} catch (SQLException ex) {} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return -1;
	}

	/**aggiunge un autore ad una pubblicazione torna falso se è già presente
	 * @param idAutore
	 * @param idPubb
	 * @param idUtente
	 * @return
	 */
	public boolean aggiungiAutoreAPubb( int idAutore, int idPubb, int idUtente ) 
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();

			stmt = connessione.prepareCall("{call inserimentoAutore(?,?,?)}"); 
			stmt.setInt(1, idPubb);
			stmt.setInt(2, idAutore);
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
	 * @param idAutore
	 * @param idPubb
	 * @param idUtente
	 */
	public void rimuoviAutoreAPubb( int idAutore, int idPubb, int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt=connessione.prepareCall("{call delAutore(?,?,?)}"); 

			stmt.setInt(1, idPubb);
			stmt.setInt(2, idAutore);
			stmt.setInt(3, idUtente);
			stmt.execute();
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**modifico o il nome o il cognome di un autore dato il suo id
	 * @param idAutore
	 * @param campo
	 * @param nuovoValore
	 * @return
	 */
	public boolean modificaAutore( int idAutore, String campo, String nuovoValore )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			if( campo.equalsIgnoreCase("nome") )
			{
				String query ="UPDATE autore SET nome = ? WHERE ID = ?;";
				stmt = connessione.prepareStatement( query );
				stmt.setString(1, nuovoValore);
				stmt.setInt(2, idAutore);
				stmt.executeUpdate();
				return true;
			}
			else if ( campo.equalsIgnoreCase("cognome") )
			{
				String query ="UPDATE autore SET cognome = ? WHERE ID = ?;";
				stmt = connessione.prepareStatement( query );
				stmt.setString(1, nuovoValore);
				stmt.setInt(2, idAutore);
		    
				return true;
			}
			} catch (SQLException ex) {} finally {
				try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    	try { connessione.close(); } catch (Exception e) { /* ignored */ }
			}
			return false;
	}

	/**cancello un autore dal database
	 * @param idAutore
	 * @param idUtente
	 */
	public void cancellaAutore( int idAutore, int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt = connessione.prepareCall("{call delCascadeAutore(?,?)}"); 
			stmt.setInt(1, idAutore);
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
	public ArrayList<Autore> getListaAutori( int idPubb )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT a.*\r\n" + 
							"FROM pubblicazione p\r\n" + 
							"INNER JOIN scritto s\r\n" + 
							"ON p.ID = s.IDPubblicazione\r\n" + 
							"INNER JOIN autore a\r\n" + 
							"ON s.IDAutore = a.ID\r\n" + 
							"WHERE p.ID = ?";
		
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			rs = stmt.executeQuery();
			
			return ritornaListaAutori(rs);
	    
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**dati gli id di un autore e l'id di una pubblicazione controlla se l'autore a scritto quella pubblicazione
	 * @param idAutore
	 * @param idPubb
	 * @return
	 */
	public boolean autoreScrittoPubb( int idAutore, int idPubb)
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = "SELECT scritto.* FROM scritto WHERE IDAutore = ? AND IDPubblicazione = ?";
		
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idAutore);
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

	/**visualizza tutti gli autori della pubblicazione 30 alla volta
	 * @param idPubb
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<Autore> visualizzaListaAutoridiPubb( int idPubb, int numeroPagina )
	{
		int entrySaltate = numeroPagina * 30;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();

			String query =  "SELECT a.* " + 
							"FROM pubblicazione p " + 
							"INNER JOIN scritto s " + 
							"ON p.ID = s.IDPubblicazione " + 
							"INNER JOIN autore a " + 
							"ON s.IDAutore = a.ID " + 
							"WHERE p.ID = ? " +
							"LIMIT ?,30 ";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			stmt.setInt(2, entrySaltate);
			rs = stmt.executeQuery();
			
			return ritornaListaAutori(rs);
			
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
	public ArrayList<Autore> visualizzaListaAutoriCompleta( int numeroPagina )
	{
		int entrySaltate = numeroPagina * 30;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();

			String query = "SELECT * FROM autore LIMIT ?,30";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, entrySaltate);
			rs = stmt.executeQuery();
	    
			return ritornaListaAutori(rs);
			
		} catch (SQLException ex) { ex .printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}return null;
	}

	private ArrayList<Autore> ritornaListaAutori( ResultSet rs ) throws SQLException
	{
		ArrayList<Autore> lista = new ArrayList<Autore>();
		String nome,cognome;
		int id;
		
		while( rs.next() )
    	{
			id = rs.getInt("ID");
			nome = rs.getString("nome");
			cognome = rs.getString("cognome");
			Autore a = new Autore(id,nome,cognome);
			lista.add(a);
    	}
		return lista;
	}
}