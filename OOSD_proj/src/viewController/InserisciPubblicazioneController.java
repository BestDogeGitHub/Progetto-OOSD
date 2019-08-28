package viewController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import controller.ControllerPubblicazione;
import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Autore;
import model.EntryStoria;
import model.ObservablePubblicazioni;
import model.ParolaChiave;
import model.Pubblicazione;
import model.Ristampa;
import model.Sorgente;

public class InserisciPubblicazioneController implements Initializable{

    @FXML private JFXTextField ISBNText;
    @FXML private JFXTextField titoloText;
    @FXML private JFXTextField editoreText;
    @FXML private JFXDatePicker dataPubbPicker;
    @FXML private JFXTextField numPaggText;
    @FXML private JFXComboBox<String> linguaBox;

    @FXML private Label insElemLabel;
    @FXML private Label ErrLabel;
    
    @FXML private JFXButton insPubbButt;
    @FXML void insPubb() 
    {
    	ErrLabel.setText("");
    	String isbn = ISBNText.getText(), titolo = titoloText.getText(), editore = editoreText.getText(), lingua = linguaBox.getValue();
    	LocalDate data = dataPubbPicker.getValue();
    	int numero;
    	
    	if( ControllerPubblicazione.esisteIsbn(isbn)) { ErrLabel.setText("Questo ISBN già è memorizzato"); return; }
    	if( isbn.length() == 0 ) { ErrLabel.setText("Inserisci l'ISBN"); return; }
		if( !ControllerUtility.checkISBN(isbn) ) { ErrLabel.setText("ISBN errato"); return; }
		
		if( titolo.length() == 0 ) { ErrLabel.setText("Inserisci il Titolo"); return; }
		if( !ControllerUtility.checkTitolo(titolo) ) { ErrLabel.setText("Titolo troppo lungo"); return; }
		
		if( editore.length() == 0 ) { ErrLabel.setText("Inserisci l'Editore"); return; }
		if( !ControllerUtility.check45(editore) ) { ErrLabel.setText("Editore troppo lungo"); return; }
		
		if( data == null ) { ErrLabel.setText("inserisci una data"); return; }
		else if( data.isAfter(LocalDate.now()) ) { ErrLabel.setText("non inserire date future"); return; }
		
		try { numero = Integer.parseInt( numPaggText.getText() );
		} catch( NumberFormatException ex) { ErrLabel.setText("inserisci un numero corretto"); return; }
		if( numero <= 0 ) { ErrLabel.setText("inserisci un numero corretto"); return; }
		
		if( lingua == null ) { ErrLabel.setText("Inserisci la Lingua"); return; }
		
		if( indice.length() == 0 ) { ErrLabel.setText("Inserisci l'Indice"); return; }
		if( !ControllerUtility.checkTesto(indice) ) { ErrLabel.setText("indice troppo lungo"); return; }
		
		if( descrizione.length() == 0 ) { ErrLabel.setText("Inserisci la Descrizione"); return; }
		if( !ControllerUtility.checkTesto(descrizione) ) { ErrLabel.setText("Descrzione troppo lunga"); return; }
		
		Pubblicazione pubb = new Pubblicazione( isbn, titolo, editore, data, numero, lingua, descrizione, indice); 
		ControllerPubblicazione.insPubb(pubb, listaAutori, listaSorgenti, listaParole, listaRistampe);
		insPubbButt.setDisable(true);
		insAutoreButt.setDisable(true);
		insParolaButt.setDisable(true);
		insSorgenteButt.setDisable(true);
		insRistampaButt.setDisable(true);
		insElementButt.setDisable(true);
		ErrLabel.setText("Pubblicazione inserita con successo");
    }
    
    String indice = "";
    @FXML private JFXButton insIndiceButt;
    @FXML public void insIndice() 
    {
    	inserisciTesto("Indice");
    }
    
    String descrizione = "";
    @FXML private JFXButton insDescrizioneButt;
    @FXML void insDescrizione() 
    {
    	inserisciTesto("Descrizione");
    }
    
    public void inserisciTesto( String campo ) 
    {
    	try 
		{
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle( campo );
			window.getIcons().add(new Image("modifica.png"));
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/InserisciTesto.fxml").openStream());
			Scene scene = new Scene(root);
			window.setScene( scene );
			InserisciTestoController ITC = (InserisciTestoController) loader.getController();
			ITC.setTitolo(campo);
			if ( campo.equals("Indice") ) ITC.setTesto(indice);
			else ITC.setTesto(descrizione);
			window.showAndWait();
			if ( campo.equals("Indice") ) indice = ITC.getTesto();
			else descrizione = ITC.getTesto();
			
		} catch (IOException e) { e.printStackTrace(); }
    }

    @FXML private TableView<Autore> AutoriTable = new TableView<Autore>();    
    @FXML private JFXButton insAutoreButt;
    @FXML void insAutore() 
    {
    	insElemLabel.setText("Autore");
    	insErrLabel.setText("");
    	firstText.setText("");
    	secondText.setText("");
    	insElementButt.setVisible(true);
    	
    	insAutoreButt.setDisable(true);
    	insParolaButt.setDisable(false);
    	insSorgenteButt.setDisable(false);
    	insRistampaButt.setDisable(false);
    	
    	dataRistampaPicker.setVisible(false);
        firstText.setVisible(true);
        firstText.setPromptText("Nome");
        secondText.setVisible(true);
        secondText.setPromptText("Cognome");
        tipoBox.setVisible(false);
        formatoBox.setVisible(false);
    }
    
    @FXML private TableView<ParolaChiave> paroleTable = new TableView<ParolaChiave>();
    @FXML private JFXButton insParolaButt;
    @FXML void insParola() 
    {
    	insElemLabel.setText("Parola Chiave");
    	insErrLabel.setText("");
    	firstText.setText("");
    	insElementButt.setVisible(true);
    	
    	insAutoreButt.setDisable(false);
    	insParolaButt.setDisable(true);
    	insSorgenteButt.setDisable(false);
    	insRistampaButt.setDisable(false);
    	
    	dataRistampaPicker.setVisible(false);
        firstText.setVisible(true);
        firstText.setPromptText("Parola Chiave");
        secondText.setVisible(false);
        tipoBox.setVisible(false);
        formatoBox.setVisible(false);
    }
    
    @FXML private TableView<Sorgente> sorgentiTable = new TableView<Sorgente>();
    @FXML private JFXButton insSorgenteButt;
    @FXML void insSorgente() 
    {
    	insElemLabel.setText("Sorgente");
    	insErrLabel.setText("");
    	firstText.setText("");
    	secondText.setText("");
    	insElementButt.setVisible(true);
    	
    	insAutoreButt.setDisable(false);
    	insParolaButt.setDisable(false);
    	insSorgenteButt.setDisable(true);
    	insRistampaButt.setDisable(false);
    	
    	dataRistampaPicker.setVisible(false);
        firstText.setVisible(true);
        firstText.setPromptText("URL");
        secondText.setVisible(true);
        secondText.setPromptText("Descrizione");
        tipoBox.setVisible(true);
        formatoBox.setVisible(true);
    }
    
    @FXML private TableView<Ristampa> ristampeTable = new TableView<Ristampa>();
    @FXML private JFXButton insRistampaButt;
    @FXML void insRistampa() 
    {
    	insElemLabel.setText("Ristampa");
    	insErrLabel.setText("");
    	firstText.setText("");
    	insElementButt.setVisible(true);
    	
    	insAutoreButt.setDisable(false);
    	insParolaButt.setDisable(false);
    	insSorgenteButt.setDisable(false);
    	insRistampaButt.setDisable(true);
    	
    	dataRistampaPicker.setVisible(true);
        firstText.setVisible(true);
        firstText.setPromptText("Numero");
        secondText.setVisible(false);
        tipoBox.setVisible(false);
        formatoBox.setVisible(false);
    }

    @FXML private JFXDatePicker dataRistampaPicker;
    @FXML private JFXTextField firstText;
    @FXML private JFXTextField secondText;
    @FXML private JFXComboBox<String> tipoBox;
    @FXML private JFXComboBox<String> formatoBox;

    @FXML private Label insErrLabel;
     
    ArrayList<Autore> listaAutori = new ArrayList<Autore>();
    ArrayList<ParolaChiave> listaParole = new ArrayList<ParolaChiave>();
    ArrayList<Sorgente> listaSorgenti = new ArrayList<Sorgente>();
    ArrayList<Ristampa> listaRistampe = new ArrayList<Ristampa>();
    @FXML private JFXButton insElementButt;
    @FXML void insElement() 
    {
    	insErrLabel.setText("");
    	String first,second,tipo,formato;
    	LocalDate data;
    	int numero;
    	if( insAutoreButt.isDisabled() )
    	{
    		first = firstText.getText();
    		second = secondText.getText();
    		if( first.length() == 0 ) { insErrLabel.setText("Inserisci il nome"); return; }
    		if( !ControllerUtility.check45(first) ) { insErrLabel.setText("nome troppo lungo"); return; }
    		if( !ControllerUtility.check45(second) ) { insErrLabel.setText("cognome troppo lungo"); return; }
    		Autore a = new Autore( ControllerUtility.formatInput(first), ControllerUtility.formatInput(second));
    		listaAutori.add(a);
    		AutoriTable.getItems().add(a);
    	}
    	else if( insParolaButt.isDisabled() )
    	{
    		first = firstText.getText();
    		if( first.length() == 0 ) { insErrLabel.setText("Inserisci la parola chiave"); return; }
    		if( !ControllerUtility.check45(first) ) { insErrLabel.setText("parola chiave troppo lunga"); return; }
    		ParolaChiave pc = new ParolaChiave( ControllerUtility.formatInput(first));
    		listaParole.add(pc);
    		paroleTable.getItems().add(pc);
    	}
    	else if( insSorgenteButt.isDisabled() )
    	{
    		first = firstText.getText();
    		second = secondText.getText();
    		tipo = tipoBox.getValue();
    		formato = formatoBox.getValue();
    		if( first.length() == 0 ) { insErrLabel.setText("Inserisci l'URI"); return; }
    		if( !ControllerUtility.checkURI(first) ) { insErrLabel.setText("URI troppo lungo"); return; }
    		if( second.length() == 0 ) { insErrLabel.setText("Inserisci la descrizione"); return; }
    		if( second.length() > 150 ) { insErrLabel.setText("descrizione troppo lunga"); return; }
    		if( tipo == null ) { insErrLabel.setText("Inserisci il tipo"); return; }
    		if( formato == null ) { insErrLabel.setText("Inserisci il formato"); return; }
    		Sorgente s = new Sorgente( first, tipo, formato, second);
    		listaSorgenti.add(s);
    		sorgentiTable.getItems().add(s);
    	}
    	else if( insRistampaButt.isDisabled() )
    	{
    		data = dataRistampaPicker.getValue();
    		if( data == null ) { insErrLabel.setText("inserisci una data"); return; }
    		else if( data.isAfter(LocalDate.now()) ) { insErrLabel.setText("non inserire date future"); return; }
    		
    		try { numero = Integer.parseInt( firstText.getText() );
    		} catch( NumberFormatException ex) { insErrLabel.setText("inserisci un numero corretto"); return; }
    		if( numero <= 0 ) { insErrLabel.setText("inserisci un numero corretto"); return; }
    		Ristampa r = new Ristampa( data, numero );
    		listaRistampe.add(r);
    		ristampeTable.getItems().add(r);
    	}
    	firstText.setText("");
    	secondText.setText("");
    }
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		List<String> linguaList = new ArrayList<String>();
		linguaList.add("Italiano");
		linguaList.add("Inglese");
		linguaList.add("Tedesco");
		linguaList.add("Francese");
		linguaList.add("Greco");
		linguaList.add("Latino");
		linguaList.add("Spagnolo");
        ObservableList<String> obvLinguaList = FXCollections.observableList(linguaList);
        linguaBox.setItems(obvLinguaList);
        
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
        
        TableColumn<Autore, String> nomeCol = new TableColumn<Autore, String>("Nome");
		TableColumn<Autore, String> cognomeCol = new TableColumn<Autore, String>("Cognome");
		nomeCol.setCellValueFactory( new PropertyValueFactory<Autore, String>("nome"));
		cognomeCol.setCellValueFactory( new PropertyValueFactory<Autore, String>("cognome"));
		nomeCol.setSortable(false);
		cognomeCol.setSortable(false);
		AutoriTable.getColumns().addAll( nomeCol, cognomeCol );
		
		TableColumn<ParolaChiave, String> parolaCol= new TableColumn<ParolaChiave, String>("Parola Chiave");
		parolaCol.setCellValueFactory( new PropertyValueFactory<ParolaChiave, String>("parolaChiave"));	
		parolaCol.setSortable(false);
		paroleTable.getColumns().addAll( parolaCol );
		
		TableColumn<Sorgente, String> uriCol = new TableColumn<Sorgente, String>("URI");
		TableColumn<Sorgente, String> descrizioneCol = new TableColumn<Sorgente, String>("Descrizione");
		TableColumn<Sorgente, String> tipoCol = new TableColumn<Sorgente, String>("Tipo");
		TableColumn<Sorgente, String> formatoCol = new TableColumn<Sorgente, String>("Formato");
		uriCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("URI"));		
		descrizioneCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("descrizione"));	
		tipoCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("tipo"));	
		formatoCol.setCellValueFactory( new PropertyValueFactory<Sorgente, String>("formato"));
		uriCol.setSortable(false);	
		descrizioneCol.setSortable(false);
		tipoCol.setSortable(false);
		formatoCol.setSortable(false);
		sorgentiTable.getColumns().addAll( uriCol, descrizioneCol, tipoCol, formatoCol );
		
		TableColumn<Ristampa, Integer> numeroCol = new TableColumn<Ristampa, Integer>("Numero");
		TableColumn<Ristampa, LocalDate> dataCol = new TableColumn<Ristampa, LocalDate>("Data Ristampa");
		numeroCol.setCellValueFactory( new PropertyValueFactory<Ristampa, Integer>("numero"));
		dataCol.setCellValueFactory( new PropertyValueFactory<Ristampa, LocalDate>("dataRistampa"));
		numeroCol.setSortable(false);
		dataCol.setSortable(false);
		ristampeTable.getColumns().addAll( numeroCol, dataCol );	
	}
}