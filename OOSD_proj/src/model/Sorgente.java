package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  classe utilizzata per raccogliere i dati di una sorgente di una pubblicazione
 * 
 * @author fulvio lapenna
 *
 */

public class Sorgente {
	/**
	 * l'id del database
	 */
	private int ID;
	/**
	 * l'uri della sorgente
	 */
	private String URI;
	/**
	 * il tipo di sorgente
	 */
	private String tipo;
	/**
	 * il formato del file allegato nella sorgente
	 */
	private String formato;
	/**
	 * una breve descrizione della sorgente
	 */
	private String descrizione;
	
	/**
	 * costruttore utilizzato per istanziare una sorgente tramite i dati forniti dal database
	 * 
	 * @param iD
	 * @param uRI
	 * @param tipo
	 * @param formato
	 * @param descrizione
	 */
	public Sorgente( int iD, String uRI, String tipo, String formato, String descrizione) {
		this.ID = iD;
		this.URI = uRI;
		this.tipo = tipo;
		this.formato = formato;
		this.descrizione = descrizione;
	}
	
	/**
	 * costruttore utilizzato per istanziare una sorgente con i dati forniti da un utente
	 * per poi inserirla nel database legata alla sua pubblicazione, l'id è quindi nullo
	 * 
	 * @param uRI
	 * @param tipo
	 * @param formato
	 * @param descrizione
	 */
	public Sorgente( String uRI, String tipo, String formato, String descrizione) {
		this.URI = uRI;
		this.tipo = tipo;
		this.formato = formato;
		this.descrizione = descrizione;
	}
	
	/**
	 * costruttore utilizzato per istanziare una sorgente tramite i dati forniti dal database in forma di resultSet
	 * 
	 * @param rsSrg
	 * @throws SQLException
	 */
	public Sorgente( ResultSet rsSrg ) throws SQLException {
		this.setID( rsSrg.getInt("ID") );
		this.setURI( rsSrg.getString("URI") );
		this.setTipo( rsSrg.getString("Tipo") );
		this.setFormato( rsSrg.getString("Formato") );
		this.setDescrizione( rsSrg.getString("Descrizione") );
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String toString() {
		return "\n Sorgente [ID=" + ID + ", URI=" + URI + ", tipo=" + tipo + ", formato=" + formato + ", descrizione="
				+ descrizione + "]";
	}
}
