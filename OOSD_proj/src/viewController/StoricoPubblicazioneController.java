package viewController;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.ControllerPubblicazione;
import controller.ControllerStorico;
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
import model.TipiEnum.TipoStoria;

public class StoricoPubblicazioneController implements Initializable {

    private int pagina = 0;
	@FXML private TableView<EntryStoria> storicoPubbTable;
	@FXML private Label titoloLabel;
	@FXML private Label errLabel;

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

    @FXML private JFXButton goToUtente;
    @FXML void apriProfiloUtente() 
    {
    	errLabel.setText("");
    	EntryStoria entry = storicoPubbTable.getSelectionModel().getSelectedItem();
    	if( entry == null ) { errLabel.setText("Devi Prima Selezionare una entry della storia"); return; }
    	int id = entry.getIdUtente();
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

	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		TableColumn<EntryStoria, String> entryCol = new TableColumn<EntryStoria, String>("entry");
		TableColumn<EntryStoria, TipoStoria> tipoCol = new TableColumn<EntryStoria, TipoStoria>("Tipo");
		TableColumn<EntryStoria, LocalDateTime> timeCol= new TableColumn<EntryStoria, LocalDateTime>("Timestamp");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss  dd/MM/yyyy");
		
		entryCol.setCellValueFactory( new PropertyValueFactory<EntryStoria, String>("descrizione"));
		tipoCol.setCellValueFactory( new PropertyValueFactory<EntryStoria, TipoStoria>("tipoStoria"));
		timeCol.setCellValueFactory( new PropertyValueFactory<EntryStoria, LocalDateTime>("timestamp"));	
		
		timeCol.setCellFactory( column -> 
		{
			return new TableCell<EntryStoria,LocalDateTime>()
			{
				protected void updateItem( LocalDateTime item, boolean empty )
				{
					super.updateItem(item, empty);
					if(item == null || empty) { setText(null); setStyle(""); }
					else { setText(dtf.format(item)); }
				}
			};
		});
		
		entryCol.setSortable(false);
		tipoCol.setSortable(false);
		timeCol.setSortable(false);
		
		tipoCol.setMinWidth(140);
		tipoCol.setMaxWidth(140);
		timeCol.setMinWidth(140);
		timeCol.setMaxWidth(140);

		titoloLabel.setText( ControllerPubblicazione.getPubb().getTitolo());
		storicoPubbTable.getColumns().addAll( entryCol, tipoCol, timeCol );
		setTable();
	}
	
	ObservableList<EntryStoria> lista = FXCollections.observableArrayList();
    public void setTable()
	{
    	storicoPubbTable.getItems().clear();

		ControllerStorico.visualizzaLogModifichePubb(pagina, ControllerPubblicazione.getPubb());
		if ( ControllerStorico.getStorico() == null ) return;
		lista.addAll( ControllerStorico.getStorico());
		storicoPubbTable.setItems(lista);
		if( storicoPubbTable.getItems().size() < 30 ) pagSuccButt.setDisable(true);
	}
}
