package viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UtenteConnessoController implements Initializable {

    @FXML private Label ErrLabel;
    
    @FXML private Label usernameLabel;
    @FXML private Label ruoloLabel;
    @FXML private Label nomeLabel;
    @FXML private Label cognomeLabel;
    @FXML private Label emailLabel;
    @FXML private Label luogoNascitaLabel;
    @FXML private Label dataNascitaLabel;
    @FXML private Label residenzaLabel;
    
    @FXML private JFXButton modUsernameButt;
    @FXML private JFXButton modPasswordButt;
    @FXML private JFXButton modNomeButt;
    @FXML private JFXButton modCognomeButt;
    @FXML private JFXButton modEmailButt;
    @FXML private JFXButton modLuogoNascitaButt;
    @FXML private JFXButton modDataNascitaButt;
    @FXML private JFXButton modResidenzaButt;

    
    @FXML void modUsername(ActionEvent event) { modificaUtente("username"); }
    @FXML void modPassword(ActionEvent event) { modificaUtente("password"); }
    @FXML void modNome(ActionEvent event) { modificaUtente("nome"); }
    @FXML void modCognome(ActionEvent event) { modificaUtente("cognome"); }
    @FXML void modEmail(ActionEvent event) { modificaUtente("email"); }
    @FXML void modDataNascita(ActionEvent event) { modificaUtente("datanascita"); }
    @FXML void modLuogoNascita(ActionEvent event) { modificaUtente("luogonascita"); }
    @FXML void modResidenza(ActionEvent event) { modificaUtente("residenza"); }

    public void modificaUtente( String campo ) 
    {
    	try 
		{
    		int id = ControllerUtente.getUtente().getID();
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Modifica Campo");
			window.getIcons().add(new Image("modifica.png"));
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ModificaProfilo.fxml").openStream());
			Scene scene = new Scene(root);
			window.setScene( scene );
			ModificaProfiloController MPC = (ModificaProfiloController) loader.getController();
			MPC.setCampo(campo);
			MPC.chooseText();
			window.showAndWait();
			ControllerUtente.setUtenteConnessoByID(id);
			updateProfilo();
			
		} catch (IOException e) { e.printStackTrace(); }
    }
    
    @FXML private JFXButton visRecInsButt;
    @FXML void visRecIns(ActionEvent event)
    {
    	try 
		{	
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ListaRecensioni.fxml").openStream());
			ListaRecensioniController LRC = (ListaRecensioniController) loader.getController();
			LRC.setModeVisRecUtenteConnesso();
			ControllerUtility.getGui().setCenter(root);
		} catch (IOException e) { e.printStackTrace(); }
    }

	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		updateProfilo();
	}
	
	public void updateProfilo()
	{
		usernameLabel.setText( ControllerUtente.getUtente().getUsername());
	    ruoloLabel.setText( ControllerUtente.getUtente().getRuolo().toString());
	    nomeLabel.setText( ControllerUtente.getUtente().getNome());
	    cognomeLabel.setText( ControllerUtente.getUtente().getCognome());
	    emailLabel.setText( ControllerUtente.getUtente().getEmail());
	    luogoNascitaLabel.setText( ControllerUtente.getUtente().getLuogoNascita());
	    dataNascitaLabel.setText( ControllerUtility.formatLocaDateFromDB(ControllerUtente.getUtente().getDataNascita()));
	    residenzaLabel.setText( ControllerUtente.getUtente().getResidenza());
	}

}
