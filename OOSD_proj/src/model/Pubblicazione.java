package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.time.*;

/**
 * classe i cui oggetti raccolgono tutti i dati riguardanti una pubblicazione inserita nel database,
 *  compresi le liste di autori,parole chiave, ristampe e sorgenti associate alla pubblicazione
 * 
 * @author fulvio lapenna
 *
 */
public class Pubblicazione {
	/**
	 * l'id della pubblicazione nel database
	 */
	private int ID;
	/**
	 * l'isbn della pubblicazione
	 */
	private String ISBN;
	/**
	 * il titolo della pubblicazione
	 */
	private String titolo;
	/**
	 * l'editore della pubblicazione
	 */
	private String editore;
	/**
	 * il numero di like messi alla pubblicazione
	 */
	private int numLike;
	/**
	 * il numero di recensioni approvate della pubblicazione 
	 */
	private int numRec;
	/**
	 * la data in cui è stata pubblicata
	 */
	private LocalDate dataPubblicazione;
	/**
	 * la data in cui la pubblicazione nel database è stata aggiornata l'ultima volta
	 */
	private LocalDate dataUltimaModifica;
	/**
	 * il numero di pagine della pubblicazione
	 */
	private int npag;
	/**
	 * la lingua in cui è scritto
	 */
	private String lingua;
	/**
	 * una breve descrizione della pubblicazione
	 */
	private String descrizione;
	/**
	 * il suo indice con i capitoli
	 */
	private String indice;
	/**
	 * la lista di autori che ha scritto la pubblicazione
	 */
	ArrayList<Autore> listaAutori = new ArrayList<Autore>();
	/**
	 * la lista di sorgenti della pubblicazione
	 */
	ArrayList<Sorgente> listaSorgenti = new ArrayList<Sorgente>();
	/**
	 * la lista di parole chiave associate alla pubblicazione
	 */
	ArrayList<ParolaChiave> listaparolechiave = new ArrayList<ParolaChiave>();
	/**
	 * la lista di ristampe della pubblicazione 
	 */
	ArrayList<Ristampa> listaRistampe = new ArrayList<Ristampa>();
	
	/**
	 * costruttore per le pubblicazioni che raccoglie le informazioni di una pubblicazione inseriti da un utente, questo
	 * costruttore viene utilizzato al momento dell'inserimento di una nuova pubblicazione all'interno del database
	 * 
	 * @param iSBN
	 * @param titolo
	 * @param editore
	 * @param dataPubblicazione
	 * @param npag
	 * @param lingua
	 * @param descrizione
	 * @param indice
	 */
	public Pubblicazione(String iSBN, String titolo, String editore, LocalDate dataPubblicazione, int npag, String lingua, String descrizione, String indice) 
	{
		this.ISBN = iSBN;
		this.titolo = titolo;
		this.editore = editore;
		this.dataPubblicazione = dataPubblicazione;
		this.npag = npag;
		this.lingua = lingua;
		this.descrizione = descrizione;
		this.indice = indice;
	}
	
	/**
	 * questo costruttore istanzia un oggetto pubblicazione che raccoglie tutti i dati derivanti dallo schema pubblicazione del database, quindi non comprende le varie liste
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public Pubblicazione ( ResultSet rs ) throws SQLException 
	{
		this.setID( rs.getInt("ID") );
		this.setISBN( rs.getString("ISBN") );
		this.setTitolo( rs.getString("Titolo") );
		this.setEditore( rs.getString("Editore") );
		this.setNumLike( rs.getInt("NumLike") );
		this.setNumRec( rs.getInt("NumRec") );
		this.setDataPubblicazione( rs.getObject("DataPubblicazione", LocalDate.class) );
		this.setDataUltimaModifica( rs.getObject("DataUltimaModifica", LocalDate.class) );
		this.setNpag( rs.getInt("Npag") );
		this.setLingua( rs.getString("Lingua") );
		this.setDescrizione( rs.getString("Descrizione") );
		this.setIndice( rs.getString("Indice") );
	}

	public String toString() {
		return "\n Pubblicazione [ID=" + ID + ", ISBN=" + ISBN + ", titolo=" + titolo + ", editore=" + editore
				+ ", numLike=" + numLike + ", numRec=" + numRec + ", dataPubblicazione=" + dataPubblicazione
				+ ", dataUltimaModifica=" + dataUltimaModifica + ", npag=" + npag + ", lingua=" + lingua
				+ ", descrizione=" + descrizione + ", indice=" + indice + ", listaAutori=" + listaAutori
				+ ", listaSorgenti=" + listaSorgenti + ", listaparolechiave=" + listaparolechiave + ", listaRistampe="
				+ listaRistampe + "]";
	}

	public int getID() { return ID; }
	public void setID(int iD) { ID = iD; }
	public String getISBN() { return ISBN; }
	public void setISBN(String iSBN) { ISBN = iSBN; }
	public String getTitolo() { return titolo; }
	public void setTitolo(String titolo) { this.titolo = titolo; }
	public String getEditore() { return editore; }
	public void setEditore(String editore) { this.editore = editore; }
	public int getNumLike() { return numLike; }
	public void setNumLike(int numLike) { this.numLike = numLike; }
	public int getNumRec() { return numRec; }
	public void setNumRec(int numRec) { this.numRec = numRec; }
	public LocalDate getDataPubblicazione() { return dataPubblicazione; }
	public void setDataPubblicazione(LocalDate dataPubblicazione) { this.dataPubblicazione = dataPubblicazione; }
	public LocalDate getDataUltimaModifica() { return dataUltimaModifica; }
	public void setDataUltimaModifica(LocalDate dataUltimaModifica) { this.dataUltimaModifica = dataUltimaModifica; }
	public int getNpag() { return npag; }
	public void setNpag(int npag) { this.npag = npag; }
	public String getLingua() { return lingua; }
	public void setLingua(String lingua) { this.lingua = lingua; }
	public String getDescrizione() { return descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	public String getIndice() { return indice; }
	public void setIndice(String indice) { this.indice = indice; }
	public ArrayList<Autore> getListaAutori() { return listaAutori; }
	public void setListaAutori(ArrayList<Autore> listaAutori) { this.listaAutori = listaAutori; }
	public ArrayList<Sorgente> getListaSorgenti() { return listaSorgenti; }
	public void setListaSorgenti(ArrayList<Sorgente> listaSorgenti) { this.listaSorgenti = listaSorgenti; }
	public ArrayList<ParolaChiave> getListaparolechiave() { return listaparolechiave; }
	public void setListaparolechiave(ArrayList<ParolaChiave> listaparolechiave) { this.listaparolechiave = listaparolechiave; }
	public ArrayList<Ristampa> getListaRistampe() { return listaRistampe; }
	public void setListaRistampe(ArrayList<Ristampa> listaRistampe) { this.listaRistampe = listaRistampe; }
}
