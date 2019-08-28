package viewController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.UtenteBase;

public class RegistrazioneController implements Initializable{

    @FXML private JFXTextField usernameText;
    @FXML private JFXPasswordField passwordText;
    @FXML private JFXTextField nomeText;
    @FXML private JFXTextField cognomeText;
    @FXML private JFXTextField emailText;
    @FXML private JFXComboBox<String> luogoDiNascitaBox;
    @FXML private JFXDatePicker dataDiNascitaText;
    @FXML private JFXComboBox<String> residenzaBox;

    @FXML private Label usernameErr;
    @FXML private Label passwordErr;
    @FXML private Label nomeErr;
    @FXML private Label cognomeErr;
    @FXML private Label emailErr;
    @FXML private Label luogoDiNascitaErr;
    @FXML private Label dataDiNascitaErr;
    @FXML private Label residenzaErr;
    
    @FXML private JFXButton regButt;
    @FXML private Label regErr;

    @FXML private JFXButton addcityButt1;
    @FXML private JFXButton addcityButt2;

    public void initialize(URL arg0, ResourceBundle arg1) 
    {
		ArrayList<String> lista = ControllerUtente.getListaCitt‡();
		luogoDiNascitaBox.getItems().addAll(lista);
		residenzaBox.getItems().addAll(lista);
	}

	@FXML public void addCity() 
    {
		try 
		{
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Inserisci la Citt‡");
			window.getIcons().add(new Image("house.png"));
			
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/InserimentoCitt‡.fxml").openStream());
			Scene scene = new Scene(root);
			window.setScene( scene );
			InserimentoCitt‡Controller ic = (InserimentoCitt‡Controller) loader.getController();
			window.showAndWait();
			
			String entry = ic.getComb();
			luogoDiNascitaBox.getItems().add(entry);
			residenzaBox.getItems().add(entry);
		
		} catch (IOException e) { e.printStackTrace(); }
    }
	
	@FXML public void registrazione() 
    {
		usernameErr.setText(""); usernameErr.setText(""); passwordErr.setText(""); nomeErr.setText(""); cognomeErr.setText(""); emailErr.setText("");
		luogoDiNascitaErr.setText(""); dataDiNascitaErr.setText(""); residenzaErr.setText("");
    	String temp;
    	boolean go = true;
    	temp = usernameText.getText();
    	if( temp.length() == 0 ) { usernameErr.setText("inserisci il tuo username"); go = false;}
    	else 
    	{
    		if( !ControllerUtility.check45(temp)) { usernameErr.setText("username troppo lungo"); return; }
    		if( ControllerUtente.esisteUsername(temp)) { usernameErr.setText("questo username e' gi‡ stato preso"); return; }
    	}
    	temp = passwordText.getText();
    	if( temp.length() == 0 ) { passwordErr.setText("inserisci la password"); go = false;}
    	else 
    	{
    		if( !ControllerUtility.checkPassword(temp)) { passwordErr.setText("La password deve essere di almeno 8 caratteri"); return; }
    	}
    	temp = nomeText.getText();
    	if( temp.length() == 0 ) { nomeErr.setText("inserisci il tuo nome"); go = false;}
    	else 
    	{
    		if( !ControllerUtility.check45(temp)) { nomeErr.setText("nome troppo lungo"); return; }
    	}
    	temp = cognomeText.getText();
    	if( temp.length() == 0 ) { cognomeErr.setText("inserisci il tuo cognome"); go = false;}
    	else 
    	{
    		if( !ControllerUtility.check45(temp)) { cognomeErr.setText("cognome troppo lungo"); return; }
    	}
    	
    	temp = emailText.getText();
    	if( temp.length() == 0 ) { emailErr.setText("inserisci la tua email"); go = false;}
    	else 
    	{
    		if( !(ControllerUtility.checkEmail(temp))) { emailErr.setText("email errata"); return; }
        	if( ControllerUtente.esisteEmail(temp)) { emailErr.setText("esiste gi‡ un utente con questa email"); return; }
    	}
    	if( luogoDiNascitaBox.getValue() == null ) { luogoDiNascitaErr.setText("inserisci la citt‡ dove sei nato"); go = false;}
    	if( dataDiNascitaText.getValue() == null)  { dataDiNascitaErr.setText("inserisci la tua data di nascita"); go = false;}
    	else if( dataDiNascitaText.getValue().isAfter(LocalDate.now()) ) { dataDiNascitaErr.setText("non inserire date future"); go = false; }
    	if( residenzaBox.getValue() == null ) { residenzaErr.setText("inserisci la tua residenza"); go = false;}
    	
    	if( go ) 
    	{
    		ControllerUtente.registrazione(new UtenteBase ( usernameText.getText(), ControllerUtility.formatInput(nomeText.getText()), 
    				ControllerUtility.formatInput(cognomeText.getText()), dataDiNascitaText.getValue(), luogoDiNascitaBox.getValue(), 
    				residenzaBox.getValue(), emailText.getText(), ControllerUtility.hashPassword(passwordText.getText()) ));
    		regErr.setText("registrazione avvenuta con successo, ora esegui il login");
    		regButt.setDisable(true);
 
    	}
    }    
}
