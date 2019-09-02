package viewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controller.ControllerAutore;
import controller.ControllerParolaChiave;
import controller.ControllerPubblicazione;
import controller.ControllerRistampa;
import controller.ControllerSorgente;
import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Autore;
import model.ParolaChiave;
import model.Ristampa;
import model.Sorgente;
import model.TipiEnum.RuoloUtente;

public class GestisciTableController implements Initializable{

	@FXML private TableView<Autore> autoriTable;
    @FXML private TableView<ParolaChiave> paroleTable;
    @FXML private TableView<Ristampa> ristampeTable;
    @FXML private TableView<Sorgente> sorgentiTable;
	
    @FXML private JFXDatePicker dataRistampaPicker;
    @FXML private JFXTextField firstText;
    @FXML private JFXComboBox<String> tipoBox;
    @FXML private JFXTextField secondText;
    @FXML private JFXComboBox<String> formatoBox;

    @FXML private Label errLabel;
    
    String campo;
    @FXML private Label titoloLabel;
    
    @FXML private JFXButton cancRowButt;
    @FXML void cancRow() 
    {
    	errLabel.setText("");
    	switch(campo) 
    	{
		  case "Parole Chiavi":
			  ParolaChiave pc = paroleTable.getSelectionModel().getSelectedItem();
			  if( pc == null ) { errLabel.setText("Devi prima selezionare un elemento"); return; }
			  paroleTable.getItems().remove(pc);
			  ControllerParolaChiave.delTag(pc, ControllerPubblicazione.getPubb() );
			  break;
		  case "Sorgenti":
			  Sorgente s = sorgentiTable.getSelectionModel().getSelectedItem();
			  if( s == null ) { errLabel.setText("Devi prima selezionare un elemento"); return; }
			  sorgentiTable.getItems().remove(s);
			  ControllerSorgente.delSorgente(s);
			  break;
		  case "Autori":
			  Autore a = autoriTable.getSelectionModel().getSelectedItem();
			  if( a == null ) { errLabel.setText("Devi prima selezionare un elemento"); return; }
			  autoriTable.getItems().remove(a);
			  ControllerAutore.delAutore(a);
			  break;
		  case "Ristampe":
			  Ristampa r = ristampeTable.getSelectionModel().getSelectedItem();
			  if( r == null ) { errLabel.setText("Devi prima selezionare un elemento"); return; }
			  ristampeTable.getItems().remove(r);
			  ControllerRistampa.delRistampa(r);
			  break;
    	}
    }
    

    Sorgente s;
    Ristampa r;
    @FXML private JFXButton modRowButt;
    @FXML void modRow() 
    {
    	errLabel.setText("");
    	switch(campo) 
    	{
		  case "Sorgenti":
			  s = sorgentiTable.getSelectionModel().getSelectedItem();
			  sorgentiTable.getItems().remove(s);
			  if( s == null ) { errLabel.setText("Devi prima selezionare un elemento"); return; }
			  firstText.setText(s.getURI());
			  secondText.setText(s.getDescrizione());
			  tipoBox.setValue(s.getTipo());
			  formatoBox.setValue(s.getFormato());
			  break;
		  case "Ristampe":
			  r = ristampeTable.getSelectionModel().getSelectedItem();
			  ristampeTable.getItems().remove(r);
			  if( r == null ) { errLabel.setText("Devi prima selezionare un elemento"); return; }
			  firstText.setText( Integer.toString(r.getNumero()) );
			  dataRistampaPicker.setValue(r.getDataRistampa());
			  break;
    	}
    	cancRowButt.setDisable(true);
    	okModRowButt.setDisable(false);
    	insRowButt.setDisable(true);
    	modRowButt.setDisable(true);
    	indietroModRowButt.setDisable(false);
    }
    @FXML private JFXButton indietroModRowButt;
    @FXML void indietroModRow()
    {
    	cancRowButt.setDisable(false);
    	okModRowButt.setDisable(true);
    	insRowButt.setDisable(false);
    	modRowButt.setDisable(false);
    	indietroModRowButt.setDisable(true);
    	errLabel.setText("");
    	switch(campo) 
    	{
		  case "Sorgenti":
			  sorgentiTable.getItems().add(s);
			  break;
		  case "Ristampe":
			  ristampeTable.getItems().add(r);
			  break;
    	}
    	firstText.setText("");
    	secondText.setText("");
    	tipoBox.setValue(null);
    	formatoBox.setValue(null);
    	dataRistampaPicker.setValue(null);
    }
    
    @FXML private JFXButton okModRowButt;
    @FXML void okModRow() 
    {
    	String first,second,tipo,formato;
    	LocalDate data;
    	int numero;
    	errLabel.setText("");
    	switch(campo) 
    	{
		  case "Sorgenti":
			    first = firstText.getText();
	    		second = secondText.getText();
	    		tipo = tipoBox.getValue();
	    		formato = formatoBox.getValue();
	    		if( first.length() == 0 ) { errLabel.setText("Inserisci l'URI"); return; }
	    		if( !ControllerUtility.checkURI(first) ) { errLabel.setText("URI troppo lungo"); return; }
	    		if( second.length() == 0 ) { errLabel.setText("Inserisci la descrizione"); return; }
	    		if( second.length() > 150 ) { errLabel.setText("descrizione troppo lunga"); return; }
	    		if( tipo == null ) { errLabel.setText("Inserisci il tipo"); return; }
	    		if( formato == null ) { errLabel.setText("Inserisci il formato"); return; }

	    		if ( !( s.getURI().equals(first) ) ) { ControllerSorgente.modSorgente(s, "URI", first) ; s.setURI(first);}
	    		if ( !( s.getDescrizione().equals(second) ) ) { ControllerSorgente.modSorgente(s, "Descrizione", second); s.setDescrizione(second);}
	    		if ( !( s.getTipo().equals(tipo) ) ) { ControllerSorgente.modSorgente(s, "Tipo", tipo); s.setTipo(tipo);}
	    		if ( !( s.getFormato().equals(formato) ) ) { ControllerSorgente.modSorgente(s, "Formato", formato); s.setFormato(formato);}
	    		
	    		sorgentiTable.getItems().add(s);
			  break;
		  case "Ristampe":
			    data = dataRistampaPicker.getValue();
	    		if( data == null ) { errLabel.setText("inserisci una data"); return; }
	    		else if( data.isAfter(LocalDate.now()) ) { errLabel.setText("non inserire date future"); return; }
	    		
	    		try { numero = Integer.parseInt( firstText.getText() );
	    		} catch( NumberFormatException ex) { errLabel.setText("inserisci un numero corretto"); return; }
	    		if( numero <= 0 ) { errLabel.setText("inserisci un numero corretto"); return; }
	    		
	    		if ( r.getNumero() != numero ) { ControllerRistampa.modRistampa(r, "numero", Integer.toString(numero)); r.setNumero(numero); }
	    		if ( !( r.getDataRistampa().isEqual(data) ) ) { ControllerRistampa.modRistampa( r, "dataristampa", data.toString() ); r.setDataRistampa(data); }
	    		ristampeTable.getItems().add(r);
			  break;
    	}
    	cancRowButt.setDisable(false);
    	okModRowButt.setDisable(true);
    	insRowButt.setDisable(false);
    	modRowButt.setDisable(false);
    	indietroModRowButt.setDisable(true);
    }
    
    @FXML private JFXButton insRowButt;
    @FXML void insRow() 
    {
    	String first,second,tipo,formato;
    	LocalDate data;
    	int numero;
    	errLabel.setText("");
    	switch(campo) 
    	{
		  case "Parole Chiavi":
			    first = firstText.getText();
	    		if( first.length() == 0 ) { errLabel.setText("Inserisci la parola chiave"); return; }
	    		if( !ControllerUtility.check45(first) ) { errLabel.setText("parola chiave troppo lunga"); return; }
	    		ParolaChiave pc = new ParolaChiave( ControllerUtility.formatInput(first));
	    		pc.setID( ControllerParolaChiave.esisteParolaChiave(pc) );			 
				if( pc.getID() < 0 ){ pc.setID( ControllerParolaChiave.insParolaChiave(pc) ); }	 
				ControllerParolaChiave.insTag( pc, ControllerPubblicazione.getPubb() );
	    		paroleTable.getItems().add(pc);
			  break;
		  case "Sorgenti":
			    first = firstText.getText();
	    		second = secondText.getText();
	    		tipo = tipoBox.getValue();
	    		formato = formatoBox.getValue();
	    		if( first.length() == 0 ) { errLabel.setText("Inserisci l'URI"); return; }
	    		if( !ControllerUtility.checkURI(first) ) { errLabel.setText("URI troppo lungo"); return; }
	    		if( second.length() == 0 ) { errLabel.setText("Inserisci la descrizione"); return; }
	    		if( second.length() > 150 ) { errLabel.setText("descrizione troppo lunga"); return; }
	    		if( tipo == null ) { errLabel.setText("Inserisci il tipo"); return; }
	    		if( formato == null ) { errLabel.setText("Inserisci il formato"); return; }
	    		Sorgente s = new Sorgente( first, tipo, formato, second);
	    		ControllerSorgente.insSorgente( ControllerPubblicazione.getPubb(), s);
	    		sorgentiTable.getItems().add(s);
			  break;
		  case "Autori":
			    first = firstText.getText();
	    		second = secondText.getText();
	    		if( first.length() == 0 ) { errLabel.setText("Inserisci il nome"); return; }
	    		if( !ControllerUtility.check45(first) ) { errLabel.setText("nome troppo lungo"); return; }
	    		if( !ControllerUtility.check45(second) ) { errLabel.setText("cognome troppo lungo"); return; }
	    		Autore a = new Autore( ControllerUtility.formatInput(first), ControllerUtility.formatInput(second));
	    		a.setID( ControllerAutore.esisteAutore(a) );			 
				if( a.getID() < 0 ){ a.setID( ControllerAutore.insAutore(a) ); }		 
				ControllerAutore.insScritto(a, ControllerPubblicazione.getPubb());
	    		autoriTable.getItems().add(a);
			  break;
		  case "Ristampe":
			    data = dataRistampaPicker.getValue();
	    		if( data == null ) { errLabel.setText("inserisci una data"); return; }
	    		else if( data.isAfter(LocalDate.now()) ) { errLabel.setText("non inserire date future"); return; }
	    		
	    		try { numero = Integer.parseInt( firstText.getText() );
	    		} catch( NumberFormatException ex) { errLabel.setText("inserisci un numero corretto"); return; }
	    		if( numero <= 0 ) { errLabel.setText("inserisci un numero corretto"); return; }
	    		Ristampa r = new Ristampa( data, numero );
	    		ControllerRistampa.insRistampa(r, ControllerPubblicazione.getPubb());
	    		ristampeTable.getItems().add(r);
			  break;
    	}
    	firstText.setText("");
    	secondText.setText("");
    	tipoBox.setValue(null);
    	formatoBox.setValue(null);
    	dataRistampaPicker.setValue(null);
    }
    
   
    public void setTitolo( String titolo ) 
    { 
    	titoloLabel.setText(titolo); 
    	campo = titolo; 
    	
    	switch(campo) 
    	{
		  case "Parole Chiavi":	
			    modRowButt.setVisible(false);
			    okModRowButt.setVisible(false);
			    indietroModRowButt.setVisible(false);
				TableColumn<ParolaChiave, String> parolaCol= new TableColumn<ParolaChiave, String>("Parola Chiave");
				parolaCol.setCellValueFactory( new PropertyValueFactory<ParolaChiave, String>("parolaChiave"));	
				paroleTable.getColumns().addAll( parolaCol );
				paroleTable.setVisible(true);
				firstText.setVisible(true);
				firstText.setPromptText("Parola Chiave");
				ObservableList<ParolaChiave> listaParole = FXCollections.observableArrayList();
				listaParole.addAll( ControllerPubblicazione.getPubb().getListaparolechiave());
				paroleTable.setItems(listaParole);
			  break;
		  case "Sorgenti":
				TableColumn<Sorgente, String> uriCol = new TableColumn<Sorgente, String>("URI");
				TableColumn<Sorgente, String> descrizioneCol = new TableColumn<Sorgente, String>("Descrizione");
				TableColumn<Sorgente, String> tipoCol = new TableColumn<Sorgente, String>("Tipo");
				TableColumn<Sorgente, String> formatoCol = new TableColumn<Sorgente, String>("Formato");
				uriCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("URI"));		
				descrizioneCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("descrizione"));	
				tipoCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("tipo"));	
				formatoCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("formato"));
				sorgentiTable.getColumns().addAll( uriCol, descrizioneCol, tipoCol, formatoCol );
				sorgentiTable.setVisible(true);
				firstText.setVisible(true);
				firstText.setPromptText("URI");
				secondText.setVisible(true);
				secondText.setPromptText("Descrizione");
				tipoBox.setVisible(true);
				formatoBox.setVisible(true);
				ObservableList<Sorgente> listaSorgenti = FXCollections.observableArrayList();
				listaSorgenti.addAll( ControllerPubblicazione.getPubb().getListaSorgenti());
				sorgentiTable.setItems(listaSorgenti);
			  break;
		  case "Autori":
			    TableColumn<Autore, String> nomeCol = new TableColumn<Autore, String>("Nome");
				TableColumn<Autore, String> cognomeCol = new TableColumn<Autore, String>("Cognome");
				nomeCol.setCellValueFactory( new PropertyValueFactory<Autore, String>("nome"));
				cognomeCol.setCellValueFactory( new PropertyValueFactory<Autore, String>("cognome"));
				autoriTable.getColumns().addAll( nomeCol, cognomeCol );
				autoriTable.setVisible(true);
				firstText.setVisible(true);
				firstText.setPromptText("Nome");
				secondText.setVisible(true);
				secondText.setPromptText("Cognome");
				ObservableList<Autore> listaAutori = FXCollections.observableArrayList();
				listaAutori.addAll( ControllerPubblicazione.getPubb().getListaAutori());
				autoriTable.setItems(listaAutori);
				modRowButt.setVisible(false);
			    okModRowButt.setVisible(false);
			    indietroModRowButt.setVisible(false);
			  break;
		  case "Ristampe":	
				TableColumn<Ristampa, Integer> numeroCol = new TableColumn<Ristampa, Integer>("Numero");
				TableColumn<Ristampa, LocalDate> dataCol = new TableColumn<Ristampa, LocalDate>("Data Ristampa");
				numeroCol.setCellValueFactory( new PropertyValueFactory<Ristampa, Integer>("numero"));
				dataCol.setCellValueFactory( new PropertyValueFactory<Ristampa, LocalDate>("dataRistampa"));
				ristampeTable.getColumns().addAll( numeroCol, dataCol );
				ristampeTable.setVisible(true);
				firstText.setVisible(true);
				firstText.setPromptText("Numero");
				dataRistampaPicker.setVisible(true);
				ObservableList<Ristampa> listaRistampe = FXCollections.observableArrayList();
				listaRistampe.addAll( ControllerPubblicazione.getPubb().getListaRistampe());
				ristampeTable.setItems(listaRistampe);
			  break;
    	}
    	checkUtente();
    }
    
	public void initialize(URL location, ResourceBundle resources) 
	{
		List<String> tipoList = new ArrayList<String>();
        tipoList.add("Download");
        tipoList.add("Video");
        tipoList.add("Audio");
        tipoList.add("Immagine");
        tipoList.add("Documento");
        tipoList.add("Altro");
        ObservableList<String> obvTipoList = FXCollections.observableList(tipoList);
        tipoBox.setItems(obvTipoList);
        
        List<String> formatoList = new ArrayList<String>();
        formatoList.add("png");
        formatoList.add("jpeg");
        formatoList.add("mp4");
        formatoList.add("mp3");
        formatoList.add("pdf");
        formatoList.add("doc");
        formatoList.add("Altro");
        ObservableList<String> obvFormatoList = FXCollections.observableList(formatoList);
        formatoBox.setItems(obvFormatoList);
	}
	
	private void checkUtente()
	{
		if ( ControllerUtente.getUtente() == null )
		{
			cancRowButt.setVisible(false);
	        indietroModRowButt.setVisible(false);
	        modRowButt.setVisible(false);
	        okModRowButt.setVisible(false);
	        insRowButt.setVisible(false);
	        dataRistampaPicker.setVisible(false);
	        firstText.setVisible(false);
	        tipoBox.setVisible(false);
	        secondText.setVisible(false);
	        formatoBox.setVisible(false);
		}
		else
		{
			RuoloUtente ruolo = ControllerUtente.getUtente().getRuolo();
		
			switch ( ruolo )
			{
				case Passivo :
				case Attivo :
					cancRowButt.setVisible(false);
			        indietroModRowButt.setVisible(false);
			        modRowButt.setVisible(false);
			        okModRowButt.setVisible(false);
			        insRowButt.setVisible(false);
			        dataRistampaPicker.setVisible(false);
			        firstText.setVisible(false);
			        tipoBox.setVisible(false);
			        secondText.setVisible(false);
			        formatoBox.setVisible(false);
					break;
				case Moderatore :
					break;
				case Amministratore :
					break;
				case SuperAmministratore :
					break;
			default:
				break;
			}
		}
	}
}
