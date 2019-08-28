package model;

import java.sql.ResultSet;
import java.sql.SQLException;
	
/**
 * questa classe è utilizzata per raccogliere le informazioni di una parola chiave
 * 
 * @author elful
 *
 */
public class ParolaChiave {
	/**
	 * l'id del database
	 */
	private int ID;
	/**
	 * la parola chiave
	 */
	private String parolaChiave;
	
	/**
	 * costruttore utilizzato per istanziare una parola chiave tramite i dati forniti dal database
	 * 
	 * @param iD
	 * @param parolaChiave
	 */
	public ParolaChiave(int iD, String parolaChiave) 
	{
		this.ID = iD;
		this.parolaChiave = parolaChiave;
	}
	
	/**
	 * cotruttore utilizzato per istanziare una parola chiave con i dati forniti da un utente, l'id è quindi sconosciuto
	 * 
	 * @param parolaChiave
	 */
	public ParolaChiave( String parolaChiave) { this.parolaChiave = parolaChiave; }
	
	public ParolaChiave( ResultSet rsAPrl ) throws SQLException {
		this.setID( rsAPrl.getInt("ID") );
		this.setParolaChiave( rsAPrl.getString("ParolaChiave") );
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getParolaChiave() {
		return parolaChiave;
	}
	public void setParolaChiave(String parolaChiave) {
		this.parolaChiave = parolaChiave;
	}
	
	public String toString() {
		return "\n ParolaChiave [ID=" + ID + ", parolaChiave=" + parolaChiave + "]";
	}
}
