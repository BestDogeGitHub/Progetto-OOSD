package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * classe utilizzata per raccogliere i dati di un autore
 * 
 * @author fulvio lapenna
 */
public class Autore {
	/**
	 * l'id all'interno del database
	 */
	private int ID;
	/**
	 * il nome dell'autore
	 */
	private String nome;
	/**
	 * il cognome dell'autore
	 */
	private String cognome;
	
	/**
	 * costruttore utilizzato per istanziare un autore tramite i dati forniti dal database
	 * 
	 * @param iD
	 * @param nome
	 * @param cognome
	 */
	public Autore(int iD, String nome, String cognome) {
		this.ID = iD;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	/**
	 * cotruttore utilizzato per istanziare un autore con i dati forniti da un utente, l'id è quindi sconosciuto
	 * 
	 * @param nome
	 * @param cognome
	 */
	public Autore( String nome, String cognome) {
		this.nome = nome;
	    this.cognome = cognome;
	}
	
	public Autore( String nome ) {
		this.nome = nome;
	}
	
	/**
	 * costruttore tramite result set
	 * 
	 * @param rsAut
	 * @throws SQLException
	 */
	public Autore( ResultSet rsAut ) throws SQLException {
		this.setID( rsAut.getInt("ID") );
		this.setNome( rsAut.getString("nome") );
		this.setCognome( rsAut.getString("cognome") );
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String toString() {
		return "\n Autore [ID=" + ID + ", nome=" + nome + ", cognome=" + cognome + "]";
	}
	
	

}
