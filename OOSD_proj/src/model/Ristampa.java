package model;

import java.time.*;
import java.sql.*;


/**
 *  classe utilizzata per raccogliere i dati di una ristampa di una pubblicazione
 * 
 * @author fulvio lapenna
 *
 */
public class Ristampa {
	/**
	 * l'id del database
	 */
	private int ID;
	/**
	 * la data della ristampa
	 */
	private LocalDate dataRistampa;
	/**
	 * il numero della ristampa
	 */
	private int numero;
	
	/**
	 * costruttore utilizzato per istanziare una ristampa tramite i dati forniti dal database
	 * 
	 * @param iD
	 * @param dataRistampa
	 * @param numero
	 */
	public Ristampa( int iD, LocalDate dataRistampa, int numero) {
		ID = iD;
		this.dataRistampa = dataRistampa;
		this.numero = numero;
	}
	
	/**
	 * costruttore utilizzato per istanziare una ristampa con i dati forniti da un utente
	 * per poi inserirla nel database legata alla sua pubblicazione, l'id è quindi ignoto
	 * 
	 * @param dataRistampa
	 * @param numero
	 */
	public Ristampa( LocalDate dataRistampa, int numero) {
		this.dataRistampa = dataRistampa;
		this.numero = numero;
	}
	
	/**
	 * costruttore utilizzato per istanziare una ristampa tramite i dati forniti dal database in forma di resultSet
	 * 
	 * @param rsRist
	 * @throws SQLException
	 */
	public Ristampa( ResultSet rsRist ) throws SQLException {
		this.setID( rsRist.getInt("ID") );
		this.setDataRistampa( rsRist.getObject("DataRistampa", LocalDate.class) );
		this.setNumero( rsRist.getInt("Numero") );
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public LocalDate getDataRistampa() {
		return dataRistampa;
	}
	public void setDataRistampa(LocalDate dataRistampa) {
		this.dataRistampa = dataRistampa;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String toString() {
		return "\n Ristampa [ID=" + ID + ", dataRistampa=" + dataRistampa + ", numero=" + numero + "]";
	}	
}
