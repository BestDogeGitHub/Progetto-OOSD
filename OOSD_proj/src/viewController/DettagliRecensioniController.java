package viewController;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import controller.ControllerPubblicazione;
import controller.ControllerRecensione;
import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.TipiEnum.RuoloUtente;

public class DettagliRecensioniController implements Initializable{

    @FXML private Label errLabel;

    @FXML private Label usernameLabel;
    @FXML private Label timestampLabel;
    @FXML private JFXTextArea TestoArea;
    @FXML private Label ApprovazioneLabel;

    @FXML private JFXButton indietroButt;
    @FXML void close(ActionEvent event) { ((Stage) ((Node)  event.getSource()).getScene().getWindow()).close(); }

    @FXML private JFXButton approvaButt;
    @FXML void approvaRec() 
    {
    	ControllerPubblicazione.setPubbByID(ControllerRecensione.getDettagliRecensione().getIDPubblicazione());
    	if ( ControllerUtente.getProfiloUtente() == null ) 
    		ControllerRecensione.approvaRecensione( ControllerPubblicazione.getPubb().getID(), ControllerUtente.getUtenteByUsername( ControllerRecensione.getDettagliRecensione().getUsername()).getID());
    	else ControllerRecensione.approvaRecensione(ControllerPubblicazione.getPubb().getID(), ControllerUtente.getProfiloUtente().getID());
    	approvaButt.setDisable(true);
    	ApprovazioneLabel.setText("Approvata");
    }

    @FXML private JFXButton eliminaRecButt;
    @FXML void eliminaRec(ActionEvent event) 
    {
    	ControllerRecensione.delRecensione();
    	errLabel.setText("Recensione Eliminata");
    	approvaButt.setDisable(true);
    	eliminaRecButt.setDisable(true);
    }

    @FXML private JFXButton gotoApprovatoreButt;
    @FXML void apriProfiloApprovatore(ActionEvent event) 
    {
    	errLabel.setText("");
    	int id = ControllerRecensione.getDettagliRecensione().getApprovatoDa();
    	ControllerUtente.setProfiloUtenteByID(id);;

		try {
			Stage window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ProfiloUtente.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.setTitle("Profilo Utente");
			window.getIcons().add(new Image("ricerca.png"));
			((Stage) ((Node)  event.getSource()).getScene().getWindow()).hide();
			window.showAndWait();
		} catch(Exception e) { e.printStackTrace(); }
		((Stage) ((Node)  event.getSource()).getScene().getWindow()).close();
    }

	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		if( ControllerRecensione.getDettagliRecensione().isFlagApprovazione() ) 
		{
			approvaButt.setDisable(true);
			ApprovazioneLabel.setText("Approvata");
		}
		else
		{
			ApprovazioneLabel.setText("Non Approvata");
			gotoApprovatoreButt.setVisible(false);
		}
		
		usernameLabel.setText(ControllerRecensione.getDettagliRecensione().getUsername());
	    timestampLabel.setText( ControllerUtility.formatLocaDateTimeFromDB(ControllerRecensione.getDettagliRecensione().getTimestamp())); 
	    TestoArea.setText(ControllerRecensione.getDettagliRecensione().getDescrizione());
	    
		if ( ControllerUtente.getUtente() == null )
		{
			gotoApprovatoreButt.setVisible(false);
			eliminaRecButt.setVisible(false);
			approvaButt.setVisible(false);
		}
		else
		{
			RuoloUtente ruolo = ControllerUtente.getUtente().getRuolo();
		
			switch ( ruolo )
			{
				case Passivo :
				case Attivo :
					eliminaRecButt.setVisible(false);
					approvaButt.setVisible(false);
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
			
			if ( ControllerRecensione.getDettagliRecensione().getIDUtente() == ControllerUtente.getUtente().getID() ) eliminaRecButt.setVisible(true);
		}
	}
}
