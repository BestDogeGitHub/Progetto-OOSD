package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Questa classe è stata creata seguendo il pattern JavaBeans per permettere la visualizzazione delle pubblicazioni tramite lista
 * all'interno delle TableView in JavaFX
 * 
 * un oggetto di 'ObservablePubblicazioni' non raccoglie tutti i dati di una pubblicazione, ma solo quelli che si vuole mostrare all'utente mentre sfoglia un elenco di 
 * varie pubblicazioni
 * 
 * @author fulvio lapenna
 *
 */
public class ObservablePubblicazioni {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty titolo;
	private final SimpleStringProperty editore;
	private final SimpleIntegerProperty numLike;
	private final SimpleIntegerProperty numRec;
	private final SimpleStringProperty dataPubblicazione;
	private final SimpleStringProperty dataUltimaModifica;
	private final SimpleStringProperty ultimaRistampa;
	private final SimpleStringProperty listaAutori;
	
	/**
	 * costruttore per ObservablePubblicazioni
	 * 
	 * @param id
	 * @param titolo
	 * @param editore
	 * @param numLike
	 * @param numRec
	 * @param dataPubblicazione
	 * @param dataUltimaModifica
	 * @param ultimaRistampa
	 * @param listaAutori
	 */
	public ObservablePubblicazioni(Integer id, String titolo, String editore, Integer numLike, Integer numRec, String dataPubblicazione,
								   String dataUltimaModifica, String ultimaRistampa, String listaAutori) 
	{
		this.id = new SimpleIntegerProperty(id);
		this.titolo = new SimpleStringProperty(titolo);
		this.editore = new SimpleStringProperty(editore);
		this.numLike = new SimpleIntegerProperty(numLike);
		this.numRec = new SimpleIntegerProperty(numRec);
		this.dataPubblicazione = new SimpleStringProperty(dataPubblicazione);
		this.dataUltimaModifica = new SimpleStringProperty(dataUltimaModifica);
		this.ultimaRistampa = new SimpleStringProperty(ultimaRistampa);
		this.listaAutori = new SimpleStringProperty(listaAutori);
	}
	
	public Integer getId() { return id.get(); }
	public String getTitolo() { return titolo.get(); }
	public String getEditore() { return editore.get(); }
	public Integer getNumLike() { return numLike.get(); }
	public Integer getNumRec() { return numRec.get(); }
	public String getDataPubblicazione() { return dataPubblicazione.get(); }
	public String getDataUltimaModifica() { return dataUltimaModifica.get(); }
	public String getUltimaRistampa() { return ultimaRistampa.get(); }
	public String getListaAutori() { return listaAutori.get(); }
}
