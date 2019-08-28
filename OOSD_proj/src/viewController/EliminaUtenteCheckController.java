package viewController;

import com.jfoenix.controls.JFXButton;

import controller.ControllerUtente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EliminaUtenteCheckController {

    @FXML private JFXButton yesButt;
    @FXML void yes(ActionEvent event) 
    {
    	ControllerUtente.delUtente();

    	((Stage)((Node)  event.getSource()).getScene().getWindow()).close(); // chiudo la finestra
    }

    @FXML private JFXButton Nobutt;
    @FXML void no(ActionEvent event) 
    {
    	((Stage)((Node)  event.getSource()).getScene().getWindow()).close(); // chiudo la finestra
    }
}
