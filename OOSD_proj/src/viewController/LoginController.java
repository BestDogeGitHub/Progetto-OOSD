package viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.UtenteBase;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import controller.ControllerUtente;
import controller.ControllerUtility;

public class LoginController implements Initializable{
	
	@FXML
	JFXButton loginButt;
	@FXML
	Label label;
	@FXML
	JFXTextField nickText;
	@FXML
	JFXPasswordField passText;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginButt.setOnAction( e -> 
		{
			UtenteBase utente = ControllerUtente.getUtenteByUsername(nickText.getText());
			
			if( utente == null ) label.setText("NickName inesistente");
			else 
			{
				if( !(utente.getPass().equals( ControllerUtility.hashPassword(passText.getText( )) ) ) ) label.setText("Password errata");
				else 
				{
					loginButt.setDisable(true);
					label.setText("Login eseguito con successo");
					ControllerUtente.setUtente(utente);
				}
			}
		});
		
	}
	
}