package DAO.MySQLImplementation;

import DAO.interfaces.DAOUtente;
import java.util.ArrayList;
import model.TipiEnum.*;
import model.UtenteBase;
import model.UtenteModeratore;
import DAO.connectors.MySQLDBConnector;
import java.sql.*;
import java.time.*;

public class MySQLDAOUtenteImpl implements DAOUtente{
	
	private UtenteBase ritornaUtente( ResultSet rs, Connection connessione ) throws SQLException
	{
		PreparedStatement stmt2 = null;
		ResultSet rsMod = null;
		
		try
		{
			if( !rs.next() ) { return null; }

			int ruolo = rs.getInt("Ruolo");
			int id = rs.getInt("ID");
			
			if( ruolo < 3) { return new UtenteBase(rs); }
			else
			{
				String query2 = "SELECT datimoderatore.*\r\n" + 
								"FROM datimoderatore\r\n" + 
								"WHERE IDUtente = ?";
				stmt2 = connessione.prepareStatement( query2 );
				stmt2.setInt( 1, id );
				rsMod = stmt2.executeQuery();
				rsMod.next();
				return new UtenteModeratore( rs, rsMod );
			}
		} catch (SQLException ex) {} finally {
			try { rsMod.close(); } catch (Exception e) { /* ignored */ }
			try { stmt2.close(); } catch (Exception e) { /* ignored */ }
	} return null;
	}
	
	/**data un email ritorna l'utente con quella email, torna null se l'email non è presente nel Database
	 * @param email
	 * @return
	 */
	public UtenteBase getUtenteDaEmail( String email )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			String query =  "SELECT * "
						  + "FROM datiutente "
						  + "INNER JOIN utente "
						  + "ON utente.ID = datiutente.IDUtente "
						  + "WHERE datiutente.Email=?";
			
			stmt = connessione.prepareStatement( query );
		
			stmt.setString( 1, email );
		
			rs = stmt.executeQuery();

			return ritornaUtente( rs, connessione );
			
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**dato l'id di un utente torna un oggetto in base al tipo di utente, UtenteBase oppure UtenteModeratore
	 * @param idUtente
	 * @return
	 */
	public UtenteBase getUtenteDaID( int idUtente )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			String query =  "SELECT u.*,d.* " + 
							"FROM utente u " + 
							"LEFT JOIN datiutente d " + 
							"ON u.ID = d.IDUtente " + 
							"WHERE u.ID = ? ";
			
			stmt = connessione.prepareStatement( query );
			stmt.setInt( 1, idUtente );
			rs = stmt.executeQuery();

			return ritornaUtente( rs, connessione );
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**data un nickname ritorna un oggetto in base al tipo di utente, UtenteBase oppure UtenteModeratore
	 * @param username
	 * @return
	 */
	public UtenteBase getUtenteDaUsername( String username )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			String query =  "SELECT u.*,d.* " + 
							"FROM utente u " + 
							"LEFT JOIN datiutente d " + 
							"ON u.ID = d.IDUtente " + 
							"WHERE u.Nickname = ?";
			
			stmt = connessione.prepareStatement( query );
			stmt.setString( 1, username );
			rs = stmt.executeQuery();
			
			return ritornaUtente( rs, connessione );
			
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**inserisce un utente nel database come Utente Base
	 * @param nickname
	 * @param password
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param luogoNascita
	 * @param residenza
	 * @param email
	 * @return
	 */
	public boolean registraUtente( String nickname, String password, String nome, String cognome, LocalDate dataNascita, String luogoNascita, String residenza, String email )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();

			stmt = connessione.prepareCall("{call inserimentoUtente(?,?,?,?,?,?,?,?)}"); 
			stmt.setString(1, nickname);
			stmt.setString(2, email);
			stmt.setString(3, password);
			stmt.setString(4, nome);
			stmt.setString(5, cognome);
			stmt.setObject(6, dataNascita);
			stmt.setString(7, luogoNascita);
			stmt.setString(8, residenza);
			stmt.execute();
			
			return true;
		} catch (SQLException ex) { ex.printStackTrace(); } finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return false;
	}
	
	/**ritorna la lista dei 30 utenti che hanno inserito più pubblicazioni nell'Database
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<UtenteModeratore> visualizzaListaUtentiCollaborativi(int numeroPagina)
	{
		ArrayList<UtenteModeratore> lista = new ArrayList<UtenteModeratore>();
		String nickname, password, nome, cognome, luogoNascita, residenza,email;
		boolean utenteEliminato = false;
		int id, numPubb, promotore;
		LocalDate dataUltimaRecensione, dataNascita, dataPromozione;
		RuoloUtente ruolo;
		int entrySaltate = numeroPagina * 30;
		
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = 			"SELECT u.*,d.*,m.*\r\n" + 
									"FROM utente u\r\n" + 
									"INNER JOIN datiutente d\r\n" + 
									"ON u.ID = d.IDUtente\r\n" + 
									"INNER JOIN datimoderatore m\r\n" + 
									"ON u.ID = m.IDUtente\r\n" + 
									"ORDER BY NumPubb DESC\r\n" + 
									"LIMIT ?,30";
			
			stmt = connessione.prepareStatement( query );
			
			stmt.setInt( 1, entrySaltate );
			
			rs = stmt.executeQuery();
			
			while( rs.next() )
	    	{
				id = rs.getInt("ID");
				nickname = rs.getString("Nickname");
				password = rs.getString("Password");
				ruolo = RuoloUtente.values()[ rs.getInt("Ruolo") ];
				if ( rs.getInt("UtenteEliminato") == 1 ) utenteEliminato = true;
				dataUltimaRecensione = rs.getObject("DataUltimaRecensione", LocalDate.class );
				nome = rs.getString("Nome");
				cognome = rs.getString("Cognome");
				dataNascita = rs.getObject("DataNascita", LocalDate.class );
				luogoNascita = rs.getString("LuogoNascita");
				residenza = rs.getString("Residenza");
				email = rs.getString("Email");
				numPubb = rs.getInt("NumPubb");
				dataPromozione = rs.getObject("DataPromozione", LocalDate.class );
				promotore = rs.getInt("Promotore");
				UtenteModeratore a = new UtenteModeratore
				(id,nickname,ruolo,utenteEliminato,dataUltimaRecensione,nome,cognome,dataNascita,luogoNascita,residenza,email,password,promotore,dataPromozione,numPubb);
				lista.add(a);
	    	}
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	    return lista;
	}
	
	/**modifica il ruolo dell'utente da moderatore a Utente Base
	 * @param idUtente
	 */
	public void recUtenteAUtenteBase( int idUtente )
	{
		Connection connessione = null;
		PreparedStatement stmt = null, stmt2 = null;
		
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			String query =  " UPDATE utente SET Ruolo = 2 WHERE ID = ? ";
			
			stmt = connessione.prepareStatement( query );
			stmt.setInt( 1, idUtente );
			stmt.executeUpdate();
			
			query =  " DELETE FROM datimoderatore WHERE IDUtente = ? ";
			
			stmt2 = connessione.prepareStatement( query );
			stmt2.setInt( 1, idUtente );
			stmt2.executeUpdate();
			
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**modifica il ruolo dell'utente da Utente Base a Utente Moderatore
	 * @param UtentePromotore
	 * @param idUtentePromosso
	 */
	public void promuoviUtenteBaseAModeratore( int UtentePromotore, int idUtentePromosso )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call promModer(?,?)}"); 
			stmt.setInt(1, UtentePromotore);
			stmt.setInt(2, idUtentePromosso);
			stmt.execute();
			
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**modifica il ruolo dell'utente da Moderatore a Utente Amministratore
	 * @param UtentePromotore
	 * @param idUtentePromosso
	 */
	public void promuoviModeratoreAdAmministratore( int UtentePromotore, int idUtentePromosso )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call promAmministratore(?,?)}"); 
			stmt.setInt(1, UtentePromotore);
			stmt.setInt(2, idUtentePromosso);
			stmt.execute();
			
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**modifica il ruolo dell'utente da Utente Amministratore a Utente Moderatore
	 * @param idUtente
	 */
	public void recAmministratoreAdModeratore( int idUtente )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			String query =  " UPDATE utente SET Ruolo = 3 WHERE ID = ? ";
			
			stmt = connessione.prepareStatement( query );
			stmt.setInt( 1, idUtente );
			stmt.executeUpdate();
			
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	/**elimina l'utente dal Database
	 * @param idUtente
	 */
	public void eliminazioneUtente( int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try
		{
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt = connessione.prepareCall("{call delUtente(?)}"); 
			stmt.setInt(1, idUtente);
			stmt.execute();
			
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	/**ritorna la lista di tutti gli utenti come utenti base in pagine, ogni pagina contiene 30 utenti
	 * @param numeroPagina
	 * @return
	 */
	public ArrayList<UtenteBase> visualizzaListaUtentiOrdinatiPerUsername( int numeroPagina )
	{
		ArrayList<UtenteBase> pagina = new ArrayList<UtenteBase>();
		int entrySaltate = numeroPagina * 30;
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT * FROM utente INNER JOIN datiutente ON ID=IDUtente ORDER BY Nickname LIMIT ?,30";
		
			stmt = connessione.prepareStatement( query );
		
			stmt.setInt( 1, entrySaltate );
		
			rs = stmt.executeQuery();

			while( rs.next() )
			{
				UtenteBase utente = new UtenteBase( rs );
				pagina.add( utente );
			}

		} catch (SQLException ex) { ex.printStackTrace();
		} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return pagina;
	}

	// 
	/**permette di modificare i campi di un utente torna falso se non è stato possibile fare la modifica
	 * @param idUtente
	 * @param campo
	 * @param nuovoValore
	 */
	public void modificaDatiUtente( int idUtente, String campo, String nuovoValore)
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		String query;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			switch(campo) {
			  case "username":
				  
				    if( !(getUtenteDaUsername( nuovoValore ) == null) ) System.out.println("il nickname è già stato preso");
				  
				    query ="UPDATE utente SET Nickname = ? WHERE ID = ?";
				    stmt = connessione.prepareStatement( query );
				    stmt.setString(1, nuovoValore);
				    stmt.setInt(2, idUtente);
				    stmt.executeUpdate();
					
				  break;
				   
			  case "password":
				    query ="UPDATE utente SET Password = ? WHERE ID = ?";
				    stmt = connessione.prepareStatement( query );
					stmt.setString(1, nuovoValore);
					stmt.setInt(2, idUtente);
					stmt.executeUpdate();
					
				  break;
				  
			  case "nome":
				    query ="UPDATE datiutente SET Nome = ? WHERE IDUtente = ?";
				    stmt = connessione.prepareStatement( query );
					stmt.setString(1, nuovoValore);
					stmt.setInt(2, idUtente);
					stmt.executeUpdate();
				  
				  break;
				  
			  case "cognome":
				    query ="UPDATE datiutente SET Cognome = ? WHERE IDUtente = ?";
				    stmt = connessione.prepareStatement( query );
					stmt.setString(1, nuovoValore);
					stmt.setInt(2, idUtente);
					stmt.executeUpdate();
					
				  break;
				  
			  case "datanascita":
				    query ="UPDATE datiutente SET Datanascita = ? WHERE IDUtente = ?";
				    
				    LocalDate localDate = LocalDate.parse(nuovoValore);
				    
				    stmt = connessione.prepareStatement( query );
					stmt.setObject(1, localDate);
					stmt.setInt(2, idUtente);
					stmt.executeUpdate();
				  
				  break;
				  
			  case "luogonascita":
				    query ="UPDATE datiutente SET Luogonascita = ? WHERE IDUtente = ?";
				    stmt = connessione.prepareStatement( query );
					stmt.setString(1, nuovoValore);
					stmt.setInt(2, idUtente);
					stmt.executeUpdate();
					
				  break;
				  
			  case "residenza":
				    query ="UPDATE datiutente SET Residenza = ? WHERE IDUtente = ?";
				    stmt = connessione.prepareStatement( query );
					stmt.setString(1, nuovoValore);
					stmt.setInt(2, idUtente);
					stmt.executeUpdate();
					
				  break;
				  
			  case "email":
				  
				    if( esisteEmail( nuovoValore ) ) System.out.println("questa email è già utilizzata");
				  
				    query ="UPDATE datiutente SET Email = ? WHERE IDUtente = ?";
				    stmt = connessione.prepareStatement( query );
					stmt.setString(1, nuovoValore);
					stmt.setInt(2, idUtente);
					stmt.executeUpdate();
					
				  break;
				  
			  default:
			    System.out.println("il campo non corrisponde con nessun attributo");
			}
			} catch (SQLException ex) {} finally {
				try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    	try { connessione.close(); } catch (Exception e) { /* ignored */ }
			}	
	}

	/**torna true se il nickname e' presente
	 * @param username
	 * @return
	 */
	public boolean esisteUsername( String username )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = "SELECT utente.id FROM utente WHERE nickname = ?";
		
			stmt = connessione.prepareStatement( query );
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if( rs.next() ) { return true; }
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return false;
	}
	
	/**torna true se l'email e' presente
	 * @param email
	 * @return
	 */
	public boolean esisteEmail( String email )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = "SELECT datiutente.id FROM datiutente WHERE Email = ?";
		
			stmt = connessione.prepareStatement( query );
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if( rs.next() ) { return true; }
			
		} catch (SQLException ex) {} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return false;
	}

	/**torna la lista di città presenti nel database
	 * @return
	 */
	public ArrayList<String> getListaCittà()
	{
		ArrayList<String> lista = new ArrayList<String>();
		Connection connessione = null;
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt = connessione.createStatement();
			rs = stmt.executeQuery( " SELECT DISTINCT d.Residenza FROM datiutente d UNION SELECT d.LuogoNascita FROM datiutente d ");
			while( rs.next() ) { lista.add( rs.getString(1)  ); }
			return lista;
			
		} catch (SQLException ex) {} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
}