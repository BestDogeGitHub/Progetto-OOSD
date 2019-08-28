package model;

import java.time.*;
import model.TipiEnum.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * classe utilizzata per raccogliere le informazioni di utenti di grado moderatore o superiore, questi utenti
 * oltre ai dati degli utenti normali conservano l'id dell'utente che gli ha promossi, la data dell'ultima
 * promozione e il numero di pubblicazioni inserite
 * 
 * @author fulvio lapenna
 *
 */
public class UtenteModeratore extends UtenteBase {
	
	/**
	 * l'id dell'utente che lo ha promosso
	 */
	private int promotore;
	/**
	 * la data dell'ultima promozione
	 */
	private LocalDate dataPromozione;
	/**
	 * il numero di pubblicazioni inserite dall'utente
	 */
	private int numPubb;
	
	/**
	 * costruttore che raccoglie tutti i dati di un utente moderatore ottenuti dal database
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
	 * @param promotore
	 * @param dataPromozione
	 * @param numPubb
	 */
	public UtenteModeratore(int iD, String username, RuoloUtente ruolo, boolean utenteEliminato, LocalDate dataUltimaRecensione,
			String nome, String cognome, LocalDate dataNascita, String luogoNascita, String residenza, String email,
			String pass, int promotore, LocalDate dataPromozione, int numPubb) {
		super(iD, username, ruolo, utenteEliminato, dataUltimaRecensione, nome, cognome, dataNascita, luogoNascita, residenza,email, pass);
		this.promotore = promotore;
		this.dataPromozione = dataPromozione;
		this.numPubb = numPubb;
	}
	
	/**
	 * costruttore utilizzato per istanziare un utente moderatore tramite i dati forniti dal database in forma di resultSet
	 * 
	 * @param rs
	 * @param rsmod
	 * @throws SQLException
	 */
	public UtenteModeratore( ResultSet rs, ResultSet rsmod ) throws SQLException {
		super( rs );
		this.setPromotore( rsmod.getInt("Promotore") );
		this.setDataPromozione( rsmod.getObject("DataPromozione", LocalDate.class) );
		this.setNumPubb( rsmod.getInt("NumPubb") );
	}
	
	public int getPromotore() {
		return promotore;
	}
	public void setPromotore(int promotore) {
		this.promotore = promotore;
	}
	public LocalDate getDataPromozione() {
		return dataPromozione;
	}
	public void setDataPromozione(LocalDate dataPromozione) {
		this.dataPromozione = dataPromozione;
	}
	public int getNumPubb() {
		return numPubb;
	}
	public void setNumPubb(int numPubb) {
		this.numPubb = numPubb;
	}

	public String toString() {
		return super.toString() + "UtenteModeratore [promotore=" + promotore + ", dataPromozione=" + dataPromozione + ", numPubb=" + numPubb + "]";
	}
}
