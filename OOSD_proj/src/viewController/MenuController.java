package viewController;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import controller.ControllerUtente;
import controller.ControllerUtility;

import com.jfoenix.controls.JFXHamburger;;

public class MenuController implements Initializable{
	
	@FXML private JFXButton accesso;
	@FXML private JFXButton registrazione;
	@FXML private JFXButton logout;
	@FXML private JFXHamburger ham;
	@FXML private ImageView logo;
	@FXML private Label welcome;;
	@FXML private JFXDrawer side;
	@FXML private BorderPane gui;
	@FXML private AnchorPane pannelloCentrale;
	
	public void initialize(URL location, ResourceBundle resources)
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/MenuLaterale.fxml").openStream());
			side.setSidePane(root);
			MenuLateraleController MLC = (MenuLateraleController) loader.getController();
			MLC.setGui(gui);
			MLC.visHome();
			registrazione.setOnAction( e -> 
			{
				MLC.selectSection(-1);
				try {
					
					pannelloCentrale = FXMLLoader.load(getClass().getResource("/view/Registrazione.fxml"));
					gui.setCenter(pannelloCentrale);
					MLC.initialize(null, null);
				} catch (IOException exx) { exx.printStackTrace(); }
			} );
			logout.setOnAction( e ->
			{
				ControllerUtente.setUtente(null);
				MLC.visHome();
				MLC.initialize(null, null);
				accesso.setVisible(true); registrazione.setVisible(true); 
				logout.setVisible(false);
			});
			
			accesso.setOnAction( e ->
			{
				try 
				{
					Stage window = new Stage();
					window.initModality(Modality.APPLICATION_MODAL);
					window.setTitle("Login");
					window.getIcons().add(new Image("loginIcon.png"));
					
					Parent pane = null;
					
					pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
					
					window.setScene(new Scene(pane));
					window.showAndWait();
					if( ControllerUtente.getUtente() != null ) 
					{ 
						accesso.setVisible(false); registrazione.setVisible(false); 
						logout.setVisible(true);
						MLC.visHome();
						MLC.initialize(null, null);
					}
				} catch (IOException ex) { ex.printStackTrace(); }
			});
			
		} catch (IOException e1) { e1.printStackTrace(); }
		
		HamburgerNextArrowBasicTransition transition = new HamburgerNextArrowBasicTransition(ham);

		transition.setRate(-1);
		ham.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
				transition.setRate(transition.getRate()*-1);
				transition.play();
				if ( side.isOpened() || side.isOpening() ) { side.close(); }
				else side.open();
			});
		
		ControllerUtility.setGui(gui);
	}
}
