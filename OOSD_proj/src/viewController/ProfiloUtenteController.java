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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.TipiEnum.RuoloUtente;
import model.UtenteModeratore;

public class ProfiloUtenteController implements Initializable{

    @FXML private Label ErrLabel;
    
    @FXML private Label promLabel;
    @FXML private Label NumPubbLabe;
    
    @FXML private Label usernameLabel;
    @FXML private Label ruoloLabel;
    @FXML private Label nomeLabel;
    @FXML private Label cognomeLabel;
    @FXML private Label emailLabel;
    @FXML private Label luogoNascitaLabel;
    @FXML private Label dataNascitaLabel;
    @FXML private Label residenzaLabel;
    
    @FXML private Label DataPromozioneLabel;
    @FXML private Label NPubbInsLabel;

    @FXML private JFXButton degAModerButt;
    @FXML void degAModer() 
    {
    	ControllerUtente.recAmministratoreAdModeratore();
    	updateProfilo();
    }
    
    @FXML private JFXButton promAdAmministButt;
    @FXML void promAdAmminist() 
    {
    	ControllerUtente.promuoviModeratoreAdAmministratore();
    	updateProfilo();
    }
    
    @FXML private JFXButton degAUtenteBaseButt;
    @FXML void degAUtenteBase() 
    {
    	ControllerUtente.recUtenteAUtenteBase();
    	updateProfilo();
    }
    
    @FXML private JFXButton promAModerButt;
    @FXML void promAModer() 
    {
    	ControllerUtente.promuoviUtenteBaseAModeratore();
    	updateProfilo();
    }
    
    @FXML private JFXButton eliminUtenteButt;
    @FXML void eliminUtente() 
    {
		try {
			Stage window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/EliminaUtenteCheck.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Sei Sicuro?");
			window.getIcons().add(new Image("modifica.png"));
			window.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
		updateProfilo();
    }
    
    @FXML private JFXButton visPubbInsButt;
    @FXML void visPubbIns(ActionEvent event)
    {
    	try {
    	Node nodo;
			nodo = FXMLLoader.load(getClass().getResource("/view/ListaPubblicazioniUtente.fxml"));
		
		ControllerUtility.getGui().setCenter(nodo);
		} catch (IOException e) {e.printStackTrace();}
		
    	((Stage) ((Node)  event.getSource()).getScene().getWindow()).close();
    
    }
    
    @FXML private JFXButton visRecInsButt;
    @FXML void visRecIns(ActionEvent event)
    {
    	try 
		{	
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ListaRecensioni.fxml").openStream());
			ListaRecensioniController LRC = (ListaRecensioniController) loader.getController();
			LRC.setModeVisRecProfiloUtente();
			ControllerUtility.getGui().setCenter(root);
		} catch (IOException e) { e.printStackTrace(); }
    	((Stage) ((Node)  event.getSource()).getScene().getWindow()).close();
    }
    
	public void initialize(URL arg0, ResourceBundle arg1) { visualizzaProfilo(); }
	
	public void updateProfilo()
	{ 
		ControllerUtente.setProfiloUtenteByID(ControllerUtente.getProfiloUtente().getID());
		visualizzaProfilo();
	}
	
	public void visualizzaProfilo()
	{
		
		RuoloUtente ruoloProfilo = ControllerUtente.getProfiloUtente().getRuolo();
		RuoloUtente ruoloUtenteConnesso = ControllerUtente.getUtente().getRuolo();
		degAModerButt.setVisible(false);
		promAdAmministButt.setVisible(false);
		degAUtenteBaseButt.setVisible(false);
		promAModerButt.setVisible(false);
		eliminUtenteButt.setVisible(false);
		switch ( ruoloUtenteConnesso )
		{
			case Passivo :
				break;
			case Attivo :
				break;
			case Moderatore :		
				break;
			case Amministratore :
				if ( ruoloProfilo == RuoloUtente.Attivo || ruoloProfilo == RuoloUtente.Passivo )
				{
					eliminUtenteButt.setVisible(true);
					promAModerButt.setVisible(true);
				}
				else if ( ruoloProfilo == RuoloUtente.Moderatore)
				{
					eliminUtenteButt.setVisible(true);
					degAUtenteBaseButt.setVisible(true);
				}
				break;
			case SuperAmministratore :
				if ( ruoloProfilo == RuoloUtente.Attivo || ruoloProfilo == RuoloUtente.Passivo )
				{
					eliminUtenteButt.setVisible(true);
					promAModerButt.setVisible(true);
				}
				else if ( ruoloProfilo == RuoloUtente.Moderatore)
				{
					eliminUtenteButt.setVisible(true);
					degAUtenteBaseButt.setVisible(true);
					promAdAmministButt.setVisible(true);
				}
				else if ( ruoloProfilo == RuoloUtente.Amministratore) 
				{
					eliminUtenteButt.setVisible(true);
					degAModerButt.setVisible(true);
				}
				break;
		default:
			break;
		}
		
		usernameLabel.setText( ControllerUtente.getProfiloUtente().getUsername());
	    
	    
		if ( ControllerUtente.getProfiloUtente().isUtenteEliminato() ) 
		{
			ErrLabel.setText("Utente eliminato");
			ruoloLabel.setText("eliminato");
			degAModerButt.setDisable(true);
			promAdAmministButt.setDisable(true);
			degAUtenteBaseButt.setDisable(true);
			promAModerButt.setDisable(true);
			eliminUtenteButt.setDisable(true);
			visPubbInsButt.setDisable(true);
			return;
		}
		ruoloLabel.setText(ruoloProfilo.toString());
		nomeLabel.setText( ControllerUtente.getProfiloUtente().getNome());
	    cognomeLabel.setText( ControllerUtente.getProfiloUtente().getCognome());
	    emailLabel.setText( ControllerUtente.getProfiloUtente().getEmail());
	    luogoNascitaLabel.setText( ControllerUtente.getProfiloUtente().getLuogoNascita());
	    dataNascitaLabel.setText( ControllerUtente.getProfiloUtente().getDataNascita().toString());
	    residenzaLabel.setText( ControllerUtente.getProfiloUtente().getResidenza());
	    if  ( ruoloProfilo.ordinal() > 2 )
	    {
	    	promLabel.setVisible(true);
	    	NumPubbLabe.setVisible(true);
	    	DataPromozioneLabel.setText(( (UtenteModeratore) ControllerUtente.getProfiloUtente()).getDataPromozione().toString());
	    	NPubbInsLabel.setText( Integer.toString( ( ( (UtenteModeratore) ControllerUtente.getProfiloUtente()).getNumPubb() ) ) );
	    }
	}

	 @FXML private JFXButton indietroButt;
	 @FXML void close(ActionEvent event) { ((Stage) ((Node)  event.getSource()).getScene().getWindow()).close(); }
	
}
