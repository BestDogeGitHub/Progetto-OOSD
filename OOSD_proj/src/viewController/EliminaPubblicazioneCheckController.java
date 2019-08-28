package viewController;

import com.jfoenix.controls.JFXButton;

import controller.ControllerPubblicazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EliminaPubblicazioneCheckController 
{
    @FXML private JFXButton Nobutt;
    @FXML void no(ActionEvent event) 
    {
    	((Stage)((Node)  event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML private JFXButton yesButt;
    @FXML void yes(ActionEvent event) 
    {
    	ControllerPubblicazione.delPubb();
    	
    	((Stage)((Node)  event.getSource()).getScene().getWindow()).close();
    }

}
