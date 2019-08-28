package viewController;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.ControllerPubblicazione;
import controller.ControllerUtente;
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
import model.EntryStoria;
import model.ObservablePubblicazioni;
import model.UtenteBase;
import model.UtenteModeratore;
import model.TipiEnum.RuoloUtente;
import model.TipiEnum.TipoStoria;

public class ListaUtentiController implements Initializable{
    
	@FXML private Label ErrLabel;
    @FXML private TableView<UtenteBase> utentiTable;
    @FXML private TableView<UtenteModeratore> moderatoriTable;
    
    private int pagina = 0;
    
    @FXML private JFXButton pagSuccButt;
    @FXML void paginaSuccessiva() 
    {
    	pagina++;
    	pagPrecButt.setDisable(false);
    	if ( alfOrdButt.isDisabled() ) setUtentiTable();
    	else setModeratoriTable();
    }
    
    @FXML private JFXButton pagPrecButt;
    @FXML void paginaPrecedente() 
    {
    	pagina--;
    	if ( pagina == 0 ) pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	if ( alfOrdButt.isDisabled() ) setUtentiTable();
    	else setModeratoriTable();
    }

    @FXML private JFXButton gotoUtente;
    @FXML void apriProfiloUtente()
    {
    	ErrLabel.setText("");
    	int id;
    	if ( alfOrdButt.isDisabled() ) 
    	{
    		UtenteBase utente = utentiTable.getSelectionModel().getSelectedItem();
    		if( utente == null ) { ErrLabel.setText("Devi Prima Selezionare un Utente"); return; }
    		id = utente.getID();
    	}
    	else 
    	{
    		UtenteModeratore moderatore = moderatoriTable.getSelectionModel().getSelectedItem();
    		if( moderatore == null ) { ErrLabel.setText("Devi Prima Selezionare un Utente"); return; }
    		id = moderatore.getID();
    	}
    	ControllerUtente.setProfiloUtenteByID(id);;
		try {
			Stage window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ProfiloUtente.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Profilo Utente");
			window.getIcons().add(new Image("ricerca.png"));
			window.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML private JFXButton collaborativiButt;
    @FXML void collaborativi()
    {
    	pagina = 0;
    	pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	alfOrdButt.setDisable(false);
    	collaborativiButt.setDisable(true);
    	utentiTable.setVisible(false);
		moderatoriTable.setVisible(true);
		setModeratoriTable();
    }
    
    @FXML private JFXButton alfOrdButt;
    @FXML void ordineAlf()
    {
    	pagina = 0;
    	pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	collaborativiButt.setDisable(false);
    	alfOrdButt.setDisable(true);
    	utentiTable.setVisible(true);
		moderatoriTable.setVisible(false);
		setUtentiTable();
    }
    
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		pagSuccButt.setDisable(true);
		pagPrecButt.setDisable(true);
		utentiTable.setVisible(false);
		moderatoriTable.setVisible(false);
		
		TableColumn<UtenteBase, String> usernameCol = new TableColumn<UtenteBase, String>("Username");
		TableColumn<UtenteBase, RuoloUtente> ruoloCol = new TableColumn<UtenteBase, RuoloUtente>("Tipo");
		TableColumn<UtenteBase, String> emailCol= new TableColumn<UtenteBase, String>("Email");
		TableColumn<UtenteBase, String> nomeCol = new TableColumn<UtenteBase, String>("Nome");
		TableColumn<UtenteBase, String> cognomeCol = new TableColumn<UtenteBase, String>("Cognome");
		usernameCol.setCellValueFactory( new PropertyValueFactory<UtenteBase, String>("username"));
		ruoloCol.setCellValueFactory( new PropertyValueFactory<UtenteBase, RuoloUtente>("ruolo"));
		emailCol.setCellValueFactory( new PropertyValueFactory<UtenteBase, String>("email"));
		nomeCol.setCellValueFactory( new PropertyValueFactory<UtenteBase, String>("nome"));
		cognomeCol.setCellValueFactory( new PropertyValueFactory<UtenteBase, String>("cognome"));
		
		TableColumn<UtenteModeratore, String> usernameModCol = new TableColumn<UtenteModeratore, String>("Username");
		TableColumn<UtenteModeratore, RuoloUtente> ruoloModCol = new TableColumn<UtenteModeratore, RuoloUtente>("Tipo");
		TableColumn<UtenteModeratore, String> emailModCol= new TableColumn<UtenteModeratore, String>("Email");
		TableColumn<UtenteModeratore, String> numPubbModCol= new TableColumn<UtenteModeratore, String>("Num. Pubblicazioni inserite");
		usernameModCol.setCellValueFactory( new PropertyValueFactory<UtenteModeratore, String>("username"));
		ruoloModCol.setCellValueFactory( new PropertyValueFactory<UtenteModeratore, RuoloUtente>("ruolo"));
		emailModCol.setCellValueFactory( new PropertyValueFactory<UtenteModeratore, String>("email"));
		numPubbModCol.setCellValueFactory( new PropertyValueFactory<UtenteModeratore, String>("numPubb"));
		
		usernameCol.setSortable(false);
		ruoloCol.setSortable(false);
		emailCol.setSortable(false);

		utentiTable.getColumns().addAll( usernameCol, ruoloCol, emailCol, nomeCol, cognomeCol );
		moderatoriTable.getColumns().addAll( usernameModCol, ruoloModCol, emailModCol, numPubbModCol );
		alfOrdButt.fire();
	}
	
    ObservableList<UtenteBase> listaUtenti = FXCollections.observableArrayList();
	private void setUtentiTable()
	{
		utentiTable.getItems().clear();
		
		ControllerUtente.visualizzaListaUtentiOrdinatiPerUsername(pagina);
		if ( ControllerUtente.getListaUtente() == null ) return;
		listaUtenti.addAll( ControllerUtente.getListaUtente() );
		utentiTable.setItems(listaUtenti);
		if( utentiTable.getItems().size() < 30 ) pagSuccButt.setDisable(true);
	}
	
	ObservableList<UtenteModeratore> listaMod = FXCollections.observableArrayList();
	private void setModeratoriTable()
	{
		moderatoriTable.getItems().clear();
		
		ControllerUtente.visualizzaListaUtentiCollaborativi(pagina);;
		if ( ControllerUtente.getListaModeratori() == null ) return;
		listaMod.addAll( ControllerUtente.getListaModeratori() );
		moderatoriTable.setItems(listaMod);
		if( moderatoriTable.getItems().size() < 30 ) pagSuccButt.setDisable(true);
	}
}