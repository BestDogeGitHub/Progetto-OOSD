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
import com.jfoenix.controls.JFXTextField;

import controller.ControllerPubblicazione;
import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModificaProfiloController implements Initializable{

    @FXML private JFXTextField Text;
    @FXML private JFXDatePicker dataText;
    @FXML private JFXComboBox<String> cityText;
    @FXML private HBox dataBox;
    @FXML private JFXButton addcityButt1;
    @FXML private JFXButton ModButt;
    @FXML private Label titoloLabel;
    
    @FXML private Label errLabel;
    private String campo = null;

    ArrayList<String> lista;
    @FXML void addCity(ActionEvent event) 
    {
		try 
		{
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Inserisci la Città");
			window.getIcons().add(new Image("house.png"));
			
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/InserimentoCittà.fxml").openStream());
			Scene scene = new Scene(root);
			window.setScene( scene );
			InserimentoCittàController ic = (InserimentoCittàController) loader.getController();
			window.showAndWait();
			
			String entry = ic.getComb();
			cityText.getItems().add(entry);
		
		} catch (IOException e) { e.printStackTrace(); }
    }

    @FXML void modifica(ActionEvent event) 
    {
    	errLabel.setText("");
    	String nuovoValore = null;
    	int numero;
    	LocalDate dataTemp;
    	switch(campo) 
    	{
		  case "username":	
			  nuovoValore = Text.getText();
			  if ( nuovoValore.length() > 45 ) { errLabel.setText("Username troppo lungo"); return; }
			  if ( nuovoValore.length() == 0 ) { errLabel.setText("inserisci lo username"); return; }
	          if ( ControllerUtente.esisteUsername(nuovoValore) ) { errLabel.setText("questo username è già stato preso"); return; }
	          ControllerUtente.modDatiUtente(campo, nuovoValore);
			  break;
		  case "password":	
			  nuovoValore = Text.getText();
			  if ( nuovoValore.length() == 0 ) { errLabel.setText("inserisci la password"); return; }
	    	  if ( ! ControllerUtility.checkPassword(nuovoValore)) { errLabel.setText("la password deve essere di almeno 8 caratteri"); return; }
	    	  nuovoValore = ControllerUtility.hashPassword(nuovoValore);
	    	  ControllerUtente.modDatiUtente(campo, nuovoValore);
			  break;
		  case "nome":	
		  case "cognome":
			  nuovoValore = ControllerUtility.formatInput(Text.getText()); 
			  if ( nuovoValore == null ) { errLabel.setText("inserisci il nuovo valore"); return; }
			  if ( nuovoValore.length() > 45 ) { errLabel.setText("limite di caratteri superato"); return; }
			  ControllerUtente.modDatiUtente(campo, nuovoValore);
			  break;
		  case "email":
			  nuovoValore = Text.getText();
			  if ( nuovoValore.length() == 0 ) { errLabel.setText("inserisci l'email"); return; }
	    	  if ( ControllerUtente.esisteEmail(nuovoValore) ) { errLabel.setText("questa email è già presente"); return; }
	    	  if ( ! ControllerUtility.checkEmail(nuovoValore)) { errLabel.setText("email errata"); return; }
	    	  ControllerUtente.modDatiUtente(campo, nuovoValore);
			  break;  
		  case "datanascita":
			  dataTemp = dataText.getValue();
			  if( dataTemp == null ) { errLabel.setText("inserisci una data"); return; }
			  else if( dataTemp.isAfter(LocalDate.now()) ) { errLabel.setText("non inserire date future"); return; } 
			  nuovoValore = dataTemp.toString();
			  ControllerUtente.modDatiUtente(campo, nuovoValore);
			  break;
		  case "luogonascita":
		  case "residenza":
			  nuovoValore = cityText.getValue();
			  if( nuovoValore == null ) { errLabel.setText("Inserisci la città"); return; }
			  ControllerUtente.modDatiUtente(campo, nuovoValore);
			  break;
		  case "titolo":
			  nuovoValore = Text.getText();
			  if( nuovoValore.length() == 0 ) { errLabel.setText("Inserisci il Titolo"); return; }
			  if( !ControllerUtility.checkTitolo(nuovoValore) ) { errLabel.setText("Titolo troppo lungo"); return; }
			  ControllerPubblicazione.modPubb(campo, nuovoValore);
			  break;
		  case "editore":
			  nuovoValore = Text.getText();
			  if( nuovoValore.length() == 0 ) { errLabel.setText("Inserisci l'Editore"); return; }
			  if( !ControllerUtility.check45(nuovoValore) ) { errLabel.setText("Editore troppo lungo"); return; }
			  ControllerPubblicazione.modPubb(campo, nuovoValore);
			  break;
		  case "dataPubblicazione":
			  dataTemp = dataText.getValue();
			  if( dataTemp == null ) { errLabel.setText("inserisci una data"); return; }
			  else if( dataTemp.isAfter(LocalDate.now()) ) { errLabel.setText("non inserire date future"); return; } 
			  nuovoValore = dataTemp.toString();
			  ControllerPubblicazione.modPubb(campo, nuovoValore);
			  break;
		  case "ISBN":
			  nuovoValore = Text.getText();
			  if( ControllerPubblicazione.esisteIsbn(nuovoValore)) { errLabel.setText("Questo ISBN già è memorizzato"); return; }
			  if( nuovoValore.length() == 0 ) { errLabel.setText("Inserisci l'ISBN"); return; }
				if( !ControllerUtility.checkISBN(nuovoValore) ) { errLabel.setText("ISBN errato"); return; }
	          ControllerPubblicazione.modPubb(campo, nuovoValore);
			  break;
		  case "lingua":
			  nuovoValore = cityText.getValue();
			  if( nuovoValore == null ) { errLabel.setText("Inserisci la Lingua"); return; }
			  ControllerPubblicazione.modPubb(campo, nuovoValore);
			  break;
		  case "numeroPagine":
			  try { numero = Integer.parseInt( Text.getText() );
			  } catch( NumberFormatException ex) { errLabel.setText("inserisci un numero corretto"); return; }
			  if( numero <= 0 ) { errLabel.setText("inserisci un numero corretto"); return; }
			  ControllerPubblicazione.modPubb(campo, nuovoValore);
			  break;
    	}
    	errLabel.setText("modificato con successo");
    	ModButt.setDisable(true);
    }
    
    public void setCampo( String campo ) { this.campo = campo; }

    public void chooseText()
    {
    	switch(campo) 
    	{
		  case "username":	
			  titoloLabel.setText("Inserisci il nuovo Username");
			  Text.setVisible(true);	  
			  break;
		  case "password":	
			  titoloLabel.setText("Inserisci la nuova Password");
			  Text.setVisible(true);	  
			  break;
		  case "nome":	
			  titoloLabel.setText("Inserisci il tuo nome");
			  Text.setVisible(true);	  
			  break;
		  case "cognome":
			  titoloLabel.setText("Inserisci il tuo cognome");
			  Text.setVisible(true);	  
			  break;
		  case "email":
			  titoloLabel.setText("Inserisci la nuova Email");
			  Text.setVisible(true);	  
			  break;  
		  case "datanascita":
			  titoloLabel.setText("Inserisci la data di nascita");
			  dataText.setVisible(true);
			  break;
		  case "luogonascita":
			  lista = ControllerUtente.getListaCittà();
			  cityText.getItems().addAll(lista);
			  titoloLabel.setText("Inserisci il luogo di nascita");
			  dataBox.setVisible(true);
			  break;
		  case "residenza":
			  lista = ControllerUtente.getListaCittà();
			  cityText.getItems().addAll(lista);
			  titoloLabel.setText("Inserisci la tua  residenza");
			  dataBox.setVisible(true);
			  break;
		  case "titolo":
			  titoloLabel.setText("Inserisci il titolo");
			  Text.setVisible(true);
			  break;
		  case "editore":
			  titoloLabel.setText("Inserisci l'editore");
			  Text.setVisible(true);
			  break;
		  case "dataPubblicazione":
			  titoloLabel.setText("Inserisci la data");
			  dataText.setVisible(true);
			  break;
		  case "ISBN":
			  titoloLabel.setText("Inserisci l'isbn");
			  Text.setVisible(true);
			  break;
		  case "lingua":
			  titoloLabel.setText("Inserisci la lingua");
			  Text.setVisible(false);
			  dataBox.setVisible(true);
			  addcityButt1.setVisible(false);
			  List<String> linguaList = new ArrayList<String>();
				linguaList.add("Italiano");
				linguaList.add("Inglese");
				linguaList.add("Tedesco");
				linguaList.add("Francese");
				linguaList.add("Greco");
				linguaList.add("Latino");
				linguaList.add("Spagnolo");
		        ObservableList<String> obvLinguaList = FXCollections.observableList(linguaList);
		        cityText.setItems(obvLinguaList);
		        cityText.setPromptText("lingua");
			  break;
		  case "numeroPagine":
			  titoloLabel.setText("Inserisci il numero");
			  Text.setVisible(true);
			  break;
    	}  
		  
    }

	public void initialize(URL arg0, ResourceBundle arg1) {}

}
