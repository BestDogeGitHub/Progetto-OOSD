package viewController;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import controller.ControllerPubblicazione;
import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.TipiEnum.RuoloUtente;

public class InserisciTestoController
{
	@FXML private Label errLabel;
	private String testo ="";
	
	@FXML private JFXTextArea testoText;
    public String getTesto() { return testoText.getText(); }
    public void setTesto( String testo ) { testoText.setText(testo); }
    
    @FXML private JFXButton okButt;
    @FXML void okClose(ActionEvent event) 
    {
    	errLabel.setText("");
    	testo = testoText.getText();
    	if ( testo.length() == 0 ) { errLabel.setText("inserisci il testo"); return; }
    	if ( ! ControllerUtility.checkTesto(testo) ) { errLabel.setText("il testo è troppo lungo"); testo=""; return; }
    	if( modificaButt.isDisabled() )
    	{
    		ControllerPubblicazione.modPubb(titoloLabel.getText(), testo);
    	}
    	((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); 
    }
    
    @FXML private Label titoloLabel;
    public void setTitolo(String titolo) { titoloLabel.setText(titolo); }
    
    
    
    @FXML private JFXButton modificaButt;
    @FXML void modifica() 
    {
    	okButt.setVisible(true);
    	modificaButt.setDisable(true);
    	testoText.setEditable(true);
    }
    
    // modifica la funzionalità della finestra, da inserire testo a modificare testo
    public void setMode()
    {
    	okButt.setVisible(false);
    	testoText.setEditable(false);
    	if ( ControllerUtente.getUtente() == null ) { return; }
		else
		{
			RuoloUtente ruolo = ControllerUtente.getUtente().getRuolo();
		
			switch ( ruolo )
			{
				case Passivo :
				case Attivo :
					break;
				case Moderatore :
				case Amministratore :
				case SuperAmministratore :
					modificaButt.setVisible(true);
					break;
			default:
				break;
			}
		}
    }
}
