package viewController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ObservablePubblicazioni;

public class CatalogoController implements Initializable{

    @FXML private TableView<ObservablePubblicazioni> ListaPubbTable;
    
    @FXML private JFXButton pagSuccButt;
    @FXML public void paginaSuccessiva()
    {
    	pagina++;
    	pagPrecButt.setDisable(false);
    	setTable();
    }
    
    @FXML private JFXButton pagPrecButt;
    @FXML public void paginaPrecedente()
    {
    	pagina--;
    	if ( pagina == 0 ) pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	setTable();
    }
    
    @FXML private JFXButton goToPubb;
    @FXML public void apriPubblicazione()
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
			setTable();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML private Label ErrLabel;
    
    @FXML private JFXButton catalogoButt;
    @FXML void visCatalogo() 
    {
    	pagina = 0;
    	pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	catalogoButt.setDisable(true);
    	inserimentoButt.setDisable(false);
    	AggiornateButt.setDisable(false);
    	conDownloadButt.setDisable(false);
    	setTable();
    }
    
    @FXML private JFXButton inserimentoButt;
    @FXML void visInserimento() 
    {
    	pagina = 0;
    	pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	catalogoButt.setDisable(false);
    	inserimentoButt.setDisable(true);
    	AggiornateButt.setDisable(false);
    	conDownloadButt.setDisable(false);
    	setTable();
    }
    @FXML private JFXButton AggiornateButt;
    @FXML void visAggiornate() 
    {
    	pagina = 0;
    	pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	catalogoButt.setDisable(false);
    	inserimentoButt.setDisable(false);
    	AggiornateButt.setDisable(true);
    	conDownloadButt.setDisable(false);
    	setTable();
    }
    
    @FXML private JFXButton conDownloadButt;
    @FXML void visConDownload() 
    {
    	pagina = 0;
    	pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	catalogoButt.setDisable(false);
    	inserimentoButt.setDisable(false);
    	AggiornateButt.setDisable(false);
    	conDownloadButt.setDisable(true);
    	setTable();
    }
    private int pagina;

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
		catalogoButt.fire();
	}
	
	ObservableList<ObservablePubblicazioni> lista = FXCollections.observableArrayList();
	public void setTable()
	{
		ListaPubbTable.getItems().clear();
		
		if ( catalogoButt.isDisabled() )  ControllerPubblicazione.visualizzaElencoCatalogo(pagina);
		else if ( inserimentoButt.isDisabled() )  ControllerPubblicazione.visualizzaElencoPubblicazioniOrdinatePerInserimento(pagina);
		else if ( AggiornateButt.isDisabled() )  ControllerPubblicazione.visualizzaElencoPubblicazioniAggiornate(pagina);
		else if ( conDownloadButt.isDisabled() )  ControllerPubblicazione.visualizzaElencoPubbConDownload(pagina);
		if ( ControllerPubblicazione.getListaPubblicazioni() == null ) return;
		lista.addAll( ControllerPubblicazione.getListaPubblicazioni());
		ListaPubbTable.setItems(lista);
		if( ListaPubbTable.getItems().size() < 10 ) pagSuccButt.setDisable(true);
	}
	

	

}
