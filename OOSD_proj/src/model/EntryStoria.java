package model;

import model.TipiEnum.*;
import java.time.*;

/**
 * questa classe è utilizzata per raccogliere le informazioni di una entry dello storico,
 * una entry rappresenta una modifica ad una pubblicazione da parte di un utente
 * 
 * @author fulvio lapenna
 *
 */
public class EntryStoria {

	/**
	 * l'id della entry nel database
	 */
	int ID;
	/**
	 * l'id della pubblicazione menzionata nella entry
	 */
	int idPubb;
	/**
	 * l'id dell'utente menzionato nella entry
	 */
	int idUtente;
	/**
	 * il timestamp di quando è avvenuta la modifica
	 */
	LocalDateTime timestamp;
	/**
	 * una breve descrizione della modifica effettuata
	 */
	String descrizione;
	/**
	 * il tipo di modifica
	 */
	TipoStoria tipoStoria;
	
	/**
	 * costruttore per le entry dello storico
	 * 
	 * @param ID
	 * @param idPubb
	 * @param idUtente
	 * @param timestamp
	 * @param descrizione
	 * @param tipoStoria
	 */
	public EntryStoria(int ID, int idPubb, int idUtente, LocalDateTime timestamp, String descrizione, int tipoStoria ) 
	{
		this.ID = ID;
		this.idPubb = idPubb;
		this.idUtente = idUtente;
		this.timestamp = timestamp;
		this.descrizione = descrizione;
		this.tipoStoria = TipoStoria.values()[tipoStoria];
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIdPubb() {
		return idPubb;
	}

	public void setIdPubb(int idPubb) {
		this.idPubb = idPubb;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TipoStoria getTipoStoria() {
		return tipoStoria;
	}

	public void setTipoStoria(TipoStoria tipoStoria) {
		this.tipoStoria = tipoStoria;
	}

	public String toString() {
		return "\n EntryStoria [ID=" + ID + ", idPubb=" + idPubb + ", idUtente=" + idUtente + ", timestamp=" + timestamp
				+ ", descrizione=" + descrizione + ", tipoStoria=" + tipoStoria + "]";
	}
	
	

}