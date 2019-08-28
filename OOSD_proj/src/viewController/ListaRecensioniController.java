package viewController;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import controller.ControllerPubblicazione;
import controller.ControllerRecensione;
import controller.ControllerUtente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.EntryStoria;
import model.Recensione;
import model.TipiEnum.tipoRecensioni;

public class ListaRecensioniController implements Initializable{

	@FXML private Label titoloLabel;
	@FXML private Label errLabel;
    @FXML private TableView<Recensione> recensioniTable;
    
    private int pagina;
    // tipo permette al controller di capire quali recensioni mostrare, prima di mostrare la lista di recensioni una dei 4 metodi setMode deve essere chiamato
    private tipoRecensioni tipo;
    public void setModeVisRecProfiloUtente() 
    { 
    	tipo = tipoRecensioni.ProfiloUtente;
    	titoloLabel.setText("Recensioni dell'Utente : " + ControllerUtente.getProfiloUtente().getUsername());
    	setTable(); 
    }
    
    public void setModeVisRecPubb() 
    {
    	tipo = tipoRecensioni.Pubblicazione; 
    	titoloLabel.setText("Recensioni di : " + ControllerPubblicazione.getPubb().getTitolo());
    	setTable(); 
    }
    
    public void setModeVisRecUtenteConnesso()
    {
    	tipo = tipoRecensioni.UtenteConnesso;
    	gotoUtente.setVisible(false);
    	titoloLabel.setText("Le Tue Recensioni");
    	setTable(); 
    }
    
    public void setModeVisRecNonApprovate() 
    { 
    	tipo = tipoRecensioni.NonApprovate;
    	titoloLabel.setText("Recensioni Non Approvate");
    	setTable(); 
    }
    

    @FXML private JFXButton pagSuccButt;
    @FXML void paginaSuccessiva() 
    {
    	pagina++;
    	pagPrecButt.setDisable(false);
    	setTable();
    }

    @FXML private JFXButton pagPrecButt;
    @FXML void paginaPrecedente() 
    {
    	pagina--;
    	if ( pagina == 0 ) pagPrecButt.setDisable(true);
    	pagSuccButt.setDisable(false);
    	setTable();
    }

    @FXML private JFXButton goToPubb;
    @FXML void apriPubblicazione() 
    {
    	errLabel.setText("");
    	Recensione recensione = recensioniTable.getSelectionModel().getSelectedItem();
    	if( recensione == null ) { errLabel.setText("Devi Prima Selezionare una Recensione"); return; }
    	int id = recensione.getIDPubblicazione();
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

    @FXML private JFXButton gotoUtente;
    @FXML void apriProfiloUtente() 
    {
    	errLabel.setText("");
    	Recensione recensione = recensioniTable.getSelectionModel().getSelectedItem();
    	if( recensione == null ) { errLabel.setText("Devi Prima Selezionare una Recensione"); return; }
    	int id = recensione.getIDUtente();
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

	@FXML private JFXButton gotoRecButt;
	@FXML void apriRec()
	{
		errLabel.setText("");
    	Recensione recensione = recensioniTable.getSelectionModel().getSelectedItem();
    	if( recensione == null ) { errLabel.setText("Devi Prima Selezionare una Recensione"); return; }
    	ControllerRecensione.setDettagliRecensione(recensione);
		try {
			Stage window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/DettagliRecensione.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Dettagli Recensione");
			window.getIcons().add(new Image("Recensioni.png"));
			window.showAndWait();
			setTable();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    public void initialize(URL arg0, ResourceBundle arg1) 
	{
		TableColumn<Recensione, String> usernameCol = new TableColumn<Recensione, String>("Username");
		TableColumn<Recensione, String> descrizioneCol = new TableColumn<Recensione, String>("Testo");
		TableColumn<Recensione, LocalDateTime> timestampCol = new TableColumn<Recensione, LocalDateTime>("Timestamp");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss  dd/MM/yyyy");
		
		usernameCol.setCellValueFactory( new PropertyValueFactory<Recensione, String>("username"));
		descrizioneCol.setCellValueFactory( new PropertyValueFactory<Recensione, String>("descrizione"));
		timestampCol.setCellValueFactory( new PropertyValueFactory<Recensione, LocalDateTime>("timestamp"));	
		
		timestampCol.setCellFactory( column -> 
		{
			return new TableCell<Recensione,LocalDateTime>()
			{
				protected void updateItem( LocalDateTime item, boolean empty )
				{
					super.updateItem(item, empty);
					if(item == null || empty) { setText(null); setStyle(""); }
					else { setText(dtf.format(item)); }
				}
			};
		});
		
		usernameCol.setSortable(false);
		descrizioneCol.setSortable(false);
		timestampCol.setSortable(false);
		
		usernameCol.setMinWidth(200);
		usernameCol.setMaxWidth(200);
		timestampCol.setMinWidth(140);
		timestampCol.setMaxWidth(140);

		recensioniTable.getColumns().addAll( usernameCol, descrizioneCol, timestampCol );
		
		if ( ControllerUtente.getUtente() == null ) gotoUtente.setVisible(false);
	}
	
	
	ObservableList<Recensione> lista = FXCollections.observableArrayList();
    public void setTable()
	{
    	recensioniTable.getItems().clear();

    	switch (tipo) {
        case UtenteConnesso: 
        	ControllerRecensione.visualizzaListaRecensionidiUtente(pagina, ControllerUtente.getUtente());
            break;
        case ProfiloUtente: 
        	ControllerRecensione.visualizzaListaRecensionidiUtente(pagina, ControllerUtente.getProfiloUtente());
            break;
        case NonApprovate:
        	ControllerRecensione.visualizzaListaRecensioniNonApprovate(pagina);
        	break;
        case Pubblicazione: 
            ControllerRecensione.visualizzaListaRecensioniApprovatediPubb(pagina);
            break;
		}
		lista.addAll( ControllerRecensione.getListaRecensioni());
		recensioniTable.setItems(lista);
		if( recensioniTable.getItems().size() < 30 ) pagSuccButt.setDisable(true);
	}

}
