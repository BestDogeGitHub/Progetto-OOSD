package viewController;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.ControllerUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InserimentoCittàController {

    @FXML private JFXButton insButt;
    @FXML private JFXTextField citText;
    @FXML private JFXTextField nazText;
    @FXML private Label labelError;
    
    private String city,nazione,comb;
    
    @FXML public void insCity(ActionEvent event)
    {
    	city = citText.getText(); 
    	nazione = nazText.getText();
    	labelError.setText("");
    	if( city.length() == 0 ) { labelError.setText("inserisci la città"); return;}
    	if( nazione.length() == 0 ) { labelError.setText("inserisci la nazione"); return;}
    	if( city.length() > 45 || nazione.length() > 45 ) { labelError.setText("Limite caratteri superato"); return;}
    	comb = ControllerUtility.formatInput(city) + ", " + ControllerUtility.formatInput(nazione);
    	Node  source = (Node)  event.getSource(); 
    	Stage stage  = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    public String getComb() { return comb; }
}
