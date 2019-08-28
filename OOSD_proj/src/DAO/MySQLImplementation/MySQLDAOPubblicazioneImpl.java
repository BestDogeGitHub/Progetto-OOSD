package DAO.MySQLImplementation;

import java.util.*;
import DAO.interfaces.DAOPubblicazione;
import controller.ControllerAutore;
import controller.ControllerRistampa;
import controller.ControllerUtility;
import model.*;
import java.time.*;
import DAO.connectors.MySQLDBConnector;
import java.sql.*;

/**************************************************************************
	 * per tutti i metodi che ritornano un elenco di pubblicazioni è
	 * stato creato un costruttore che riempie solo parzialmente gli
	 * oggetti pubblicazione con le loro informazioni, inserisce solo ID,
	 * titolo, la lista di autori, il numero di like e recensioni,l'editore, e 
	 * le date di pubblicazione di ultima modifica e dell'ultima ristampa
	 *************************************************************************/

public class MySQLDAOPubblicazioneImpl implements DAOPubblicazione {
	
	/**metodo di estrazione generica di un elenco a cui si deve passare una query senza variabili
	 * @param query
	 * @param pagina
	 * @return
	 */
	private ArrayList<ObservablePubblicazioni> getElencoSenzaParametri ( String query, int pagina)
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int pubbsaltate = pagina * 10;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, pubbsaltate);
		    rs = stmt.executeQuery();
			
		    return ritornaListaPubblicazioni(rs);
		    
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**metodo di estrazione generico di un elenco con una query con una variabile di binding di tipo String
	 * @param query
	 * @param pagina
	 * @param var
	 * @return
	 */
	private ArrayList<ObservablePubblicazioni> getElencoConParametroString ( String query, int pagina, String var )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		int pubbsaltate = pagina * 10;		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt = connessione.prepareStatement( query );
			stmt.setString(1, var );
			stmt.setInt(2, pubbsaltate );
		    rs = stmt.executeQuery();
		   
		    return ritornaListaPubblicazioni(rs);
		
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/** estrazione elenco delle ultime dieci pubblicazioni inserite
	 * @param pagina
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubblicazioniOrdinatePerInserimento( int pagina )
	{
		String query =  "SELECT p.*" + 
						"FROM Pubblicazione p " + 
						"INNER JOIN Storia s " + 
						"ON p.ID = s.IDPubblicazione " + 
						"WHERE Tipo = 1 " + 
						"ORDER BY ID DESC " + 
						"LIMIT ?,10 ";
			
		return getElencoSenzaParametri( query, pagina );
	}
	
	/**estrazione elenco pubblicazioni aggiornate negli ultimi 30 giorni, restituite in pagine, ogni pagina contiene 10 pubblicazioni
	 * @param pagina
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubblicazioniAggiornate( int pagina )
	{
		String query =  "SELECT DISTINCT p.* " + 
						"FROM Pubblicazione p " + 
						"WHERE DATEDIFF( CURDATE(), p.DataUltimaModifica) < 30 " + 
						"ORDER BY p.DataUltimaModifica " + 
						"LIMIT ?,10";
			
		return getElencoSenzaParametri( query, pagina );
	}
	
	/** estrazione pubblicazioni con download disponibile
	 * @param pagina
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubbConDownload( int pagina )
	{
		String query =  "SELECT p.* " + 
						"FROM Pubblicazione p " + 
						"INNER JOIN sorgente s " + 
						"ON s.IDPubblicazione = p.ID " + 
						"WHERE s.Tipo = \"download\"" +
						"LIMIT ?,10";
			
		return getElencoSenzaParametri( query, pagina );
	}
	
	/**estrazione di tutte le pubblicazioni con titolo, autore, editore, la data di ultima ristampa e la data ultima modifica, ordinati per titolo, restituite in pagine
	 * @param pagina
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> visualizzaElencoCatalogo( int pagina )
	{
		String query =  "SELECT p.* " + 
						"FROM Pubblicazione p " + 
						"ORDER BY p.Titolo " + 
						"LIMIT ?,10";
			
		return getElencoSenzaParametri( query, pagina );
	}
	
	/**estrazione elenco delle pubblicazioni inserite da un utente, restituite in pagine, ogni pagina contiene 10 pubblicazioni
	 * @param idUtente
	 * @param pagina
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubbInsDaUtente( int idUtente, int pagina )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int pubbsaltate = pagina * 10;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT p.* " + 
							"FROM pubblicazione p " + 
							"INNER JOIN Storia s " + 
							"ON p.ID = s.IDPubblicazione " + 
							"WHERE s.IDUtente = ? AND s.Tipo = 1 " +
							"LIMIT ?,10";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idUtente);
			stmt.setInt(2, pubbsaltate);
		    rs = stmt.executeQuery();
			
		    return ritornaListaPubblicazioni(rs);
	    
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**estrazione di tutti i dati di una pubblicazione dato il suo id
	 * @param idPubb
	 * @return
	 */
	public Pubblicazione getPubb( int idPubb )
	{	
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query =  "SELECT * " + 
							"FROM pubblicazione p " + 
							"INNER JOIN metadati m " + 
							"ON p.ID = m.IDPubblicazione " + 
							"WHERE p.ID = ?";
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
		    rs = stmt.executeQuery();
		    
		    if( ! rs.next() ) { return null; }	
	    	idPubb = rs.getInt("ID");

	    	Pubblicazione pubb = new Pubblicazione( rs );
	    	return pubb;

		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;	
	}
	
	/**ricerca di publicazione per ISBN, restituisce 'null' se non ci sono elementi trovati
	 * @param ISBN
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> ricercaPerISBN( String ISBN )
	{
		String query =  "SELECT p.* " + 
						"FROM pubblicazione p " + 
						"WHERE p.ISBN = ? " +
						"LIMIT ?,10";
			
		return getElencoConParametroString( query, 0, ISBN );
	}
	
	/**ricerca di publicazione per titolo, restituisce 'null' se non ci sono elementi trovati
	 * @param pagina
	 * @param titolo
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> ricercaPerTitolo( int pagina, String titolo )
	{
		String query =  "SELECT p.* " + 
						"FROM pubblicazione p " + 
						"WHERE p.Titolo = ? " + 
						"LIMIT ?,10";
			
		return getElencoConParametroString( query, pagina, titolo );
	}
	
	/**ricerca di publicazione per parola chiave, restituisce 'null' se non ci sono elementi trovati
	 * @param pagina
	 * @param parola
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> ricercaPerParolaChiave( int pagina, String parola )
	{
		String query =  "SELECT p.* " + 
						"FROM pubblicazione p " + 
						"INNER JOIN tag t " + 
						"ON t.IDPubblicazione = p.ID " + 
						"INNER JOIN parolachiave pc " + 
						"ON pc.ID = t.IDParolaChiave " + 
						"WHERE pc.ParolaChiave = ? " + 
						"LIMIT ?,10";
			
		return getElencoConParametroString( query, pagina, parola );
	}
	
	/** ricerca di publicazione per autore se nome o cognome sono assenti, si deve passare 'null' al loro posto, restituisce 'null' se non ci sono elementi trovati
	 * @param pagina
	 * @param nome
	 * @param cognome
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> ricercaPerAutore( int pagina, String nome, String cognome )
	{
		int pubbsaltate = pagina * 10;
		Connection connessione = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connessione = MySQLDBConnector.creaConnessione();
			
			stmt=connessione.prepareCall("{call ricercaAutori(?,?,?)}"); 

			stmt.setString(1, nome);
			stmt.setString(2, cognome);
			stmt.setInt(3, pubbsaltate);
	
			rs = stmt.executeQuery();

			return ritornaListaPubblicazioni(rs);

		} catch (SQLException ex) {} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**data una pubblicazione restituire tutte le pubblicazioni con gli stessi autori
	 * @param idPubb
	 * @param pagina
	 * @return
	 */
	public ArrayList<ObservablePubblicazioni> visualizzaElencoPubbConStessiAutori( int idPubb, int pagina )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int pubbsaltate = pagina * 10;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			String query =  "SELECT DISTINCT p.* " + 
							"FROM pubblicazione p " + 
							"INNER JOIN scritto s1 " + 
							"ON p.ID = s1.IDPubblicazione " + 
							"INNER JOIN scritto s2 " + 
							"ON s1.IDAutore = s2.IDAutore " + 
							"WHERE s2.IDPubblicazione = ? " +
							"LIMIT ?,10 ";
			
			stmt = connessione.prepareStatement( query );
			stmt.setInt(1, idPubb);
			stmt.setInt(2, pubbsaltate);
		    rs = stmt.executeQuery();
		    
		    return ritornaListaPubblicazioni(rs);
		    
		} catch (SQLException ex) { ex.printStackTrace();} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return null;
	}
	
	/**inserisci una pubblicazione nel database, gli passi delle liste di autori,sorgenti ecc... ed inserisce anche quelle se è necessario, altrimenti se già presenti le connette alla pubblicazione
	 * @param idUtente
	 * @param iSBN
	 * @param titolo
	 * @param editore
	 * @param dataPubblicazione
	 * @param npag
	 * @param lingua
	 * @param descrizione
	 * @param indice
	 * @return
	 */
	public int inserisciPubb(int idUtente, String iSBN, String titolo, String editore, LocalDate dataPubblicazione, 
	int npag, String lingua, String descrizione,String indice)
	{
		int idPubb;
		Connection connessione = null;
		CallableStatement stmt = null;

		if( !( ricercaPerISBN(iSBN) == null) ) return -2 ;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt=connessione.prepareCall("{call inserimentoPubb(?,?,?,?,?,?,?,?,?,?)}"); 
			
			stmt.setInt(1, idUtente);
			stmt.setString(2, iSBN);
			stmt.setString(3, titolo);
			stmt.setString(4, editore);
			stmt.setObject(5, dataPubblicazione);
			stmt.setInt(6, npag);
			stmt.setString(7, lingua);
			stmt.setString(8, descrizione);
			stmt.setString(9, indice);
			stmt.registerOutParameter(10, Types.INTEGER);
			stmt.execute();
			idPubb = stmt.getInt(10);

			return idPubb;
		} catch (SQLException ex) {ex.printStackTrace();} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
		return -1;
	}
	
	/**modifica una pubblicazione
	 * @param idPubb
	 * @param campo
	 * @param nuovoValore
	 * @param idUtente
	 * @return
	 */
	public boolean modificaPubblicazione( int idPubb,String campo, String nuovoValore, int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			switch(campo) {
			  case "ISBN":				  
				  if( !( ricercaPerISBN(nuovoValore) == null) ) System.out.println("questo isbn è già presente");
				  
				  stmt = connessione.prepareCall("{call modificaISBN(?,?,?)}");
				  stmt.setString(2, nuovoValore);
				  break;
				  
			  case "titolo":				  
				  stmt = connessione.prepareCall("{call modificaTitolo(?,?,?)}");
				  stmt.setString(2, nuovoValore);
				  break;
				  
			  case "editore":				  
				  stmt = connessione.prepareCall("{call modificaEditore(?,?,?)}");
				  stmt.setString(2, nuovoValore);
				  break;
				  
			  case "dataPubblicazione":  
				  stmt = connessione.prepareCall("{call modificaDataPubb(?,?,?)}");
				  LocalDate localDate = LocalDate.parse( nuovoValore ) ;
				  stmt.setObject(2, localDate);
				  break;
				  
			  case "numeroPagine":
				  stmt = connessione.prepareCall("{call modificaNPag(?,?,?)}");
				  int x = Integer.parseInt( nuovoValore );
				  stmt.setInt(2, x);
				  break;
				  
			  case "lingua":				  
				  stmt = connessione.prepareCall("{call modificaLingua(?,?,?)}");
				  stmt.setString(2, nuovoValore);
				  break;
				  
			  case "Descrizione":				  
				  stmt = connessione.prepareCall("{call modificaDescrizione(?,?,?)}");
				  stmt.setString(2, nuovoValore);
				  break;
				  
			  case "Indice":
				  stmt = connessione.prepareCall("{call modificaIndice(?,?,?)}");
				  stmt.setString(2, nuovoValore);
				  break;
			
			  default: System.out.println( campo + "il campo non corrisponde con nessun attributo");
			}
			stmt.setInt(1, idPubb);
			stmt.setInt(3, idUtente);
			stmt.execute();
			return true;
		} catch (SQLException ex) {ex.printStackTrace();} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return false;
	}
	
	/**cancella una pubblicazione
	 * @param idPubb
	 * @param idUtente
	 */
	public void rimuoviPubblicazione( int idPubb, int idUtente )
	{
		Connection connessione = null;
		CallableStatement stmt = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
			stmt=connessione.prepareCall("{call delPubb(?,?)}"); 
			
			stmt.setInt(1, idPubb);
			stmt.setInt(2, idUtente);
			stmt.execute();
		} catch (SQLException ex) {} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	private ArrayList<ObservablePubblicazioni> ritornaListaPubblicazioni( ResultSet rs ) throws SQLException
	{
		ArrayList<ObservablePubblicazioni> listaPubb = new ArrayList<ObservablePubblicazioni>();
		int idPubb, numLike, numRec;
		String titolo, editore,dataUltimaModifica, dataPubblicazione,listaAutori,dataUltimaRistampa;
		Ristampa ultimaRistampa;
		
		if( !rs.isBeforeFirst() ) return null;
		while( rs.next() )
	    {
		   idPubb = rs.getInt("ID");
		   numLike = rs.getInt("NumLike");
		   numRec = rs.getInt("NumRec");
		   titolo = rs.getString("Titolo");
		   editore = rs.getString("Editore");
		   dataUltimaModifica = ControllerUtility.formatLocaDateFromDB(rs.getObject("DataUltimaModifica", LocalDate.class));
		   dataPubblicazione = ControllerUtility.formatLocaDateFromDB(rs.getObject("DataPubblicazione", LocalDate.class));
		   listaAutori = ControllerUtility.arrayDiAutoriToString(ControllerAutore.ListaAutoriPubb(idPubb));
		   
		   ultimaRistampa = ControllerRistampa.getUltimaRistampa(idPubb);
		   if( ultimaRistampa == null ) dataUltimaRistampa = "";
		   else dataUltimaRistampa = ControllerUtility.formatLocaDateFromDB(ultimaRistampa.getDataRistampa());
		   
		   ObservablePubblicazioni pubb = new ObservablePubblicazioni(idPubb, titolo, editore, numLike, numRec, dataPubblicazione, 
				   													  dataUltimaModifica, dataUltimaRistampa, listaAutori);
		   listaPubb.add( pubb );
		} 
		return listaPubb;
	}
	
	/**torna true se l'ISBN e' presente
	 * @param ISBN
	 * @return
	 */
	public boolean esisteISBN( String ISBN )
	{
		Connection connessione = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
			connessione = MySQLDBConnector.creaConnessione();
		
			String query = "SELECT pubblicazione.id FROM pubblicazione WHERE ISBN = ?";
		
			stmt = connessione.prepareStatement( query );
			stmt.setString(1, ISBN);
			rs = stmt.executeQuery();
			if( rs.next() ) { return true; }
			
		} catch (SQLException ex) {} finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { connessione.close(); } catch (Exception e) { /* ignored */ }
		} return false;
	}	
}
