package model;

import java.time.*;
import java.sql.*;

/**
 * classe utilizzata per raccogliere i dati di una recensione di una pubblicazione
 * 
 * @author fulvio lapenna
 *
 */
public class Recensione {
	
	/**
	 *l'id della pubblicazione riferita dalla recensione 
	 */
	private int IDPubblicazione;
	/**
	 * l'id dell'utente che ha scritto la recensione
	 */
	private int IDUtente;
	/**
	 * l'username dell'utente che ha scritto la recensione
	 */
	private String username;
	/**
	 * il testo della recensione
	 */
	private String descrizione;
	/**
	 * la flag posta a true se la recensione è stata approvata
	 */
	private boolean flagApprovazione;
	/**
	 * l'id dell'utente che l'ha approvata
	 */
	private int approvatoDa;
	/**
	 * il timestamp di quando la recensione è stata scritta
	 */
	private LocalDateTime timestamp;
	
	/**
	 * costruttore utilizzato per istanziare una recensione tramite i dati forniti dal database
	 * 
	 * @param iDPubblicazione
	 * @param iDUtente
	 * @param nickUtente
	 * @param descrizione
	 * @param flagApprovazione
	 * @param approvatoDa
	 * @param timestamp
	 */
	public Recensione(int iDPubblicazione, int iDUtente, String nickUtente, String descrizione, boolean flagApprovazione, int approvatoDa, LocalDateTime timestamp) 
	{
		this.IDPubblicazione = iDPubblicazione;
		this.IDUtente = iDUtente;
		this.username = nickUtente;
		this.descrizione = descrizione;
		this.flagApprovazione = flagApprovazione;
		this.approvatoDa = approvatoDa;
		this.timestamp = timestamp;
	}
	
	public int getIDPubblicazione() {
		return IDPubblicazione;
	}
	public void setIDPubblicazione(int iDPubblicazione) {
		IDPubblicazione = iDPubblicazione;
	}
	public int getIDUtente() {
		return IDUtente;
	}
	public void setIDUtente(int iDUtente) {
		IDUtente = iDUtente;
	}
	public String getUsername() {
		return this.username; 
	}
	public void setUsername( String nickUtente ) {
		this.username = nickUtente;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public boolean isFlagApprovazione() {
		return flagApprovazione;
	}
	public void setFlagApprovazione(boolean flagApprovazione) {
		this.flagApprovazione = flagApprovazione;
	}
	public int getApprovatoDa() {
		return approvatoDa;
	}
	public void setApprovatoDa(int approvatoDa) {
		this.approvatoDa = approvatoDa;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString() {
		return "\n Recensione [IDPubblicazione=" + IDPubblicazione + ", IDUtente=" + IDUtente + "nickname: " + username + ", descrizione="
				+ descrizione + ", flagApprovazione=" + flagApprovazione + ", approvatoDa=" + approvatoDa
				+ ", timestamp=" + timestamp + "]";
	}
}
