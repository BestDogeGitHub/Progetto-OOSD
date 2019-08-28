package model;

import java.time.*;
import model.TipiEnum.*;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *  classe utilizzata per raccogliere i dati di un utente, questo puo' essere l'utente connesso al database
 *  oppure un utente qualsiasi di cui si sta visualizzando il profilo
 * 
 * @author fulvio lapenna
 *
 */
public class UtenteBase {
	/**
	 * l'id del database
	 */
	private int ID;
	/**
	 * l'username dell'utente
	 */
	private String username;
	/**
	 * la password criptata dell'utente
	 */
	private String pass;
	/**
	 * il ruolo dell'utente
	 */
	private RuoloUtente ruolo;
	/**
	 * valore booleano posto a true se l'utente è stato eliminato
	 */
	private boolean utenteEliminato;
	/**
	 * la data in cui ha scritto l'ultima recensione
	 */
	private LocalDate dataUltimaRecensione;
    /**
     * il nome anagrafico dell'utente
     */
    private String nome;
    /**
     * il cognome anagrafico dell'utente
     */
    private String cognome;
    /**
     * la data in cui è nato
     */
    private LocalDate dataNascita;
    /**
     * la città e nazione di nascita
     */
    private String luogoNascita;
    /**
     * la città e nazione di residenza
     */
    private String residenza;
    /**
     * l'email dell'utente
     */
    private String email;
    
	/**
	 * Costruttore per istanziare un utente base con i dati derivanti dal database
	 * 
	 * @param iD
	 * @param username
	 * @param ruolo
	 * @param utenteEliminato
	 * @param dataUltimaRecensione
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param luogoNascita
	 * @param residenza
	 * @param email
	 * @param pass
	 */
	public UtenteBase(int iD, String username, RuoloUtente ruolo, boolean utenteEliminato, LocalDate dataUltimaRecensione,
			String nome, String cognome, LocalDate dataNascita, String luogoNascita, String residenza, String email,
			String pass) {
		ID = iD;
		this.username = username;
		this.ruolo = ruolo;
		this.utenteEliminato = utenteEliminato;
		this.dataUltimaRecensione = dataUltimaRecensione;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.residenza = residenza;
		if ( email == null ) this.email = "eliminata";
		else this.email = email;
		this.pass = pass;
	}
	/**
	 * costruttore utilizzato per istanziare un utente al momento della registrazione
	 * 
	 * @param username
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param luogoNascita
	 * @param residenza
	 * @param email
	 * @param pass
	 */
	public UtenteBase( String username, String nome, String cognome, LocalDate dataNascita,
					   String luogoNascita, String residenza, String email, String pass) 
	{
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.residenza = residenza;
		if ( email == null ) this.email = "eliminata";
		else this.email = email;
		this.pass = pass;
	}
	
	/**
	 * Costruttore per istanziare un utente base con i dati derivanti dal database tramite resultSet
	 * 
	 */
	public UtenteBase( ResultSet rs ) throws SQLException {
		int temp = rs.getInt("Ruolo");
		int utntElim = rs.getInt("UtenteEliminato");
		String email = rs.getString("Email") ;
		
		this.ID = rs.getInt("ID") ;
		this.username = rs.getString("NickName") ;
		this.pass = rs.getString("Password") ;
		this.ruolo = RuoloUtente.values()[temp] ;
		if ( utntElim == 0 ) this.utenteEliminato = false;
		else this.utenteEliminato = true;
		this.dataUltimaRecensione = rs.getObject("DataUltimaRecensione", LocalDate.class) ;
		this.nome = rs.getString("Nome") ;
		this.cognome = rs.getString("Cognome") ;
		this.dataNascita = rs.getObject("DataNascita", LocalDate.class) ;
		this.luogoNascita = rs.getString("LuogoNascita") ;
		this.residenza = rs.getString("Residenza") ;
		if ( email == null ) this.email = "eliminata";
		else this.email = email; 	
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String nickName) {
		this.username = nickName;
	}
	public RuoloUtente getRuolo() {
		return ruolo;
	}
	public void setRuolo(RuoloUtente ruolo) {
		this.ruolo = ruolo;
	}
	public boolean isUtenteEliminato() {
		return utenteEliminato;
	}
	public void setUtenteEliminato(boolean utenteEliminato) {
		this.utenteEliminato = utenteEliminato;
	}
	public LocalDate getDataUltimaRecensione() {
		return dataUltimaRecensione;
	}
	public void setDataUltimaRecensione(LocalDate dataUltimaRecensione) {
		this.dataUltimaRecensione = dataUltimaRecensione;
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
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getLuogoNascita() {
		return luogoNascita;
	}
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	public String getResidenza() {
		return residenza;
	}
	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String toString() {
		return "\n UtenteBase [ID=" + ID + ", nickName=" + username + ", pass=" + pass + ", ruolo=" + ruolo
				+ ", utenteEliminato=" + utenteEliminato + ", dataUltimaRecensione=" + dataUltimaRecensione + ", nome="
				+ nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + ", luogoNascita=" + luogoNascita
				+ ", residenza=" + residenza + ", email=" + email + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UtenteBase))
			return false;
		UtenteBase other = (UtenteBase) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}