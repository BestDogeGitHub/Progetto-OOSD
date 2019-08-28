package viewController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import controller.ControllerPubblicazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ObservablePubblicazioni;
import model.TipiEnum.tipoRicerca;

public class RicercaPubblicazioniController implements Initializable {

    @FXML private TableView<ObservablePubblicazioni> ListaPubbTable;

    @FXML private Label ErrLabel;
    
    private int pagina;
    private tipoRicerca tipo;
    
    private BorderPane gui;
    public void setGui( BorderPane gui ) { this.gui = gui; }
    
    @FXML private JFXButton pagSuccButt;
    @FXML void paginaSuccessiva( ) 
    {
    	pagina++;
    	pagPrecButt.setDisable(false);
    	setTable();
    }
    
    @FXML private JFXButton pagPrecButt;
    @FXML void paginaPrecedente( ) 
    {
    	pagina--;
    	if ( pagina == 0 ) pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	setTable();
    }
    
    @FXML private JFXButton goToPubb;
    @FXML void apriPubblicazione( ) 
    {
    	ErrLabel.setText("");
    	ObservablePubblicazioni pubb = ListaPubbTable.getSelectionModel().getSelectedItem();
    	if( pubb == null ) { ErrLabel.setText("Devi Prima Selezionare una Pubblicazione"); return; }
    	int id = pubb.getId();
    	ControllerPubblicazione.setPubbByID(id);

		try {
			Stage window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ProfiloPubblicazione.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Profilo Pubblicazione");
			window.getIcons().add(new Image("ricerca.png"));
			window.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    
    
    @FXML private JFXButton isbnRicButt;
    @FXML void ricercaPerIsbn( ) 
    {
    	cercaText.setText("");
    	CognomeAutoreText.setText("");
    	tipo = tipoRicerca.ISBN;
    	CognomeAutoreText.setVisible(false);
    	ricercaHBox.setVisible(true);
    	cercaText.setPromptText("ISBN");
    }
    
    @FXML private JFXButton titoloRicButt;
    @FXML void ricercaPerTitolo( ) 
    {
    	cercaText.setText("");
    	CognomeAutoreText.setText("");
    	tipo = tipoRicerca.Titolo;
    	CognomeAutoreText.setVisible(false);
    	ricercaHBox.setVisible(true);
    	cercaText.setPromptText("Titolo");
    }
    
    @FXML private JFXButton parolaRicButt;
    @FXML void ricercaPerParola( ) 
    {
    	cercaText.setText("");
    	CognomeAutoreText.setText("");
    	tipo = tipoRicerca.ParolaChiave;
    	CognomeAutoreText.setVisible(false);
    	ricercaHBox.setVisible(true);
    	cercaText.setPromptText("Parola Chiave");
    }
    
    @FXML private JFXButton autoreRicButt;
    @FXML void ricercaPerAutore( ) 
    {
    	cercaText.setText("");
    	CognomeAutoreText.setText("");
    	tipo = tipoRicerca.Autore;
    	CognomeAutoreText.setVisible(true);
    	ricercaHBox.setVisible(true);
    	cercaText.setPromptText("Nome");
    }
    
    @FXML private HBox ricercaHBox;
    @FXML private JFXTextField cercaText;
    @FXML private JFXTextField CognomeAutoreText;
    @FXML private Label noElemLabel;
    
    @FXML private JFXButton CercaButt;
    @FXML void Ricerca() 
    {
    	pagina = 0;
    	pagPrecButt.setDisable(true);
    	noElemLabel.setVisible(false);
    	if ( cercaText.getText().length() == 0 && CognomeAutoreText.getText().length() == 0 ) return;
    	else setTable();
    }

	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		TableColumn<ObservablePubblicazioni, String> titoloCol = new TableColumn<ObservablePubblicazioni, String>("Titolo");
		TableColumn<ObservablePubblicazioni, String> autoriCol = new TableColumn<ObservablePubblicazioni, String>("Autori");
		TableColumn<ObservablePubblicazioni, String> editoreCol= new TableColumn<ObservablePubblicazioni, String>("Editore");
		TableColumn<ObservablePubblicazioni, Integer> likeCol = new TableColumn<ObservablePubblicazioni, Integer>("Like");
		TableColumn<ObservablePubblicazioni, Integer> recensioniCol = new TableColumn<ObservablePubblicazioni, Integer>("N.Recensioni");
		TableColumn<ObservablePubblicazioni, String> dataPubblicazioneCol = new TableColumn<ObservablePubblicazioni, String>("Pubblicazione");
		TableColumn<ObservablePubblicazioni, String> ultimaModificaCol = new TableColumn<ObservablePubblicazioni, String>("Ultima Modifica");
		TableColumn<ObservablePubblicazioni, String> ultimaRistampaCol = new TableColumn<ObservablePubblicazioni, String>("Ultima Ristampa");

		titoloCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, String>("titolo"));
		autoriCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, String>("listaAutori"));
		editoreCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, String>("editore"));	
		likeCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, Integer>("numLike"));		
		recensioniCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, Integer>("numRec"));	
		dataPubblicazioneCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, String>("dataPubblicazione"));	
		ultimaModificaCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, String>("dataUltimaModifica"));
		ultimaRistampaCol.setCellValueFactory( new PropertyValueFactory<ObservablePubblicazioni, String>("ultimaRistampa"));
		
		titoloCol.setSortable(false);
		autoriCol.setSortable(false);
		editoreCol.setSortable(false);
		likeCol.setSortable(false);	
		recensioniCol.setSortable(false);
		dataPubblicazioneCol.setSortable(false);
		ultimaModificaCol.setSortable(false);
		ultimaRistampaCol.setSortable(false);
		likeCol.setMinWidth(30);
		likeCol.setMaxWidth(30);
		ListaPubbTable.getColumns().addAll( titoloCol, autoriCol, editoreCol, likeCol, recensioniCol, dataPubblicazioneCol, ultimaModificaCol, ultimaRistampaCol );
	}
    
	ObservableList<ObservablePubblicazioni> lista = FXCollections.observableArrayList();
	public void setTable()
	{
		ListaPubbTable.getItems().clear();
		
		switch (tipo) {
        case ISBN: 
        	ControllerPubblicazione.ricercaPerISBN( cercaText.getText() );
            break;
        case Titolo: 
        	ControllerPubblicazione.ricercaPerTitolo( pagina, cercaText.getText() );
            break;
        case ParolaChiave:
        	ControllerPubblicazione.ricercaPerParolaChiave(pagina, cercaText.getText());
        	break;
        case Autore: 
            String nome = cercaText.getText();
            String cognome = CognomeAutoreText.getText();
            if ( nome.length() == 0) nome = null;
            else if ( cognome.length() == 0 ) cognome = null;
            ControllerPubblicazione.ricercaPerAutore(pagina, nome, cognome);
            break;
		}
		if ( ControllerPubblicazione.getListaPubblicazioni() == null ) { noElemLabel.setVisible(true); return; }
		lista.addAll( ControllerPubblicazione.getListaPubblicazioni());
		ListaPubbTable.setItems(lista);
		if( ListaPubbTable.getItems().size() < 10 ) pagSuccButt.setDisable(true);
	}
}
