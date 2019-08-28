package viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.ControllerLikes;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.TipiEnum.RuoloUtente;

public class ProfiloPubblicazioneController implements Initializable{
    
    @FXML private JFXButton modTitoloButt;
    @FXML private JFXButton modAutoriButt;
    @FXML private JFXButton modEditoreButt;
    @FXML private JFXButton modParoleButt;
    @FXML private JFXButton modIsbnButt;
    @FXML private JFXButton modDataPubbButt;
    @FXML private JFXButton modNPagButt;
    @FXML private JFXButton modLinguaButt;
    
    @FXML private Label titoloLabel;
    @FXML private Label autoriLabel;
    @FXML private Label editoreLabel;
    @FXML private Label paroleLabel;
    @FXML private Label isbnLabel;
    @FXML private Label nLikeLabel;
    @FXML private Label nRecensioniLabel;
    @FXML private Label dataPubbLabel;
    @FXML private Label dataUltModLabel;
    @FXML private Label nPagLabel;
    @FXML private Label linguaLabel;

    @FXML void modTitolo() { modificaPubblicazione("titolo"); }
    @FXML void modEditore() { modificaPubblicazione("editore"); }
    @FXML void modDataPubb() { modificaPubblicazione("dataPubblicazione"); }
    @FXML void modIsbn() { modificaPubblicazione("ISBN"); }
    @FXML void modLingua() { modificaPubblicazione("lingua"); }
    @FXML void modNPag() { modificaPubblicazione("numeroPagine"); }
    
    @FXML private JFXButton eliminaPubbButt;
    @FXML void eliminaPubb() 
    {
    	try {
			Stage window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/EliminaPubblicazioneCheck.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Sei Sicuro?");
			window.getIcons().add(new Image("modifica.png"));
			window.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	updatePubblicazione();
    }
    
    public void modificaPubblicazione( String campo ) 
    {
    	try 
		{
    		int id = ControllerPubblicazione.getPubb().getID();
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
			ControllerPubblicazione.setPubbByID(id);
			updatePubblicazione();
			
		} catch (IOException e) { e.printStackTrace(); }
    }
    

    @FXML private JFXButton visDescrizioneButt;
    @FXML void visDescrizione() 
    {
    	modificaTesto("Descrizione");
    }
    
    @FXML private JFXButton visIndiceButt;
    @FXML void visIndice() 
    {
    	modificaTesto("Indice");
    }
    
    public void modificaTesto( String campo ) 
    {
    	try 
		{
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle( campo );
			window.getIcons().add(new Image("modifica.png"));
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/InserisciTesto.fxml").openStream());
			Scene scene = new Scene(root);
			window.setScene( scene );
			InserisciTestoController ITC = (InserisciTestoController) loader.getController();
			if ( campo.equals("Indice") ) ITC.setTesto( ControllerPubblicazione.getPubb().getIndice() );
			else ITC.setTesto( ControllerPubblicazione.getPubb().getDescrizione() );
			ITC.setTitolo(campo);
			ITC.setMode();
			window.showAndWait();
			updatePubblicazione();
			
		} catch (IOException e) { e.printStackTrace(); }
    }

    @FXML private JFXButton visRistampeButt;
    @FXML void visRistampe() { gestisciTavole("Ristampe"); }
    @FXML private JFXButton visSorgentiButt;
    @FXML void visSorgenti() { gestisciTavole("Sorgenti"); }
    @FXML void modAutori() { gestisciTavole("Autori"); }
    @FXML void modParole() { gestisciTavole("Parole Chiavi"); }
    
    private void gestisciTavole( String campo )
    {
    	try 
		{
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle( campo );
			window.getIcons().add(new Image("modifica.png"));
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/GestisciTable.fxml").openStream());
			Scene scene = new Scene(root);
			window.setScene( scene );
			GestisciTableController GTC = (GestisciTableController) loader.getController();
			GTC.setTitolo(campo);
			window.showAndWait();
			updatePubblicazione();
			
		} catch (IOException e) { e.printStackTrace(); }
    }
    
    @FXML private JFXButton visRecensioniButt;
    @FXML void visRecensioni(ActionEvent event) 
    {
    	try 
		{	
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ListaRecensioni.fxml").openStream());
			ListaRecensioniController LRC = (ListaRecensioniController) loader.getController();
			LRC.setModeVisRecPubb();
			ControllerUtility.getGui().setCenter(root);
		} catch (IOException e) { e.printStackTrace(); }
    	((Stage) ((Node)  event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML private JFXButton visStoricoButt;
    @FXML void visStorico(ActionEvent event) 
    {
    	try {
        	Node nodo = FXMLLoader.load(getClass().getResource("/view/StoricoPubblicazione.fxml"));
    		ControllerUtility.getGui().setCenter(nodo);
    		} catch (IOException e) {e.printStackTrace();}
    		
        	((Stage) ((Node)  event.getSource()).getScene().getWindow()).close();
    }

    @FXML private JFXButton insLikeButt;
    @FXML void insLike() 
    {
    	insLikeButt.setVisible(false);
    	delLikeButt.setVisible(true);
    	ControllerLikes.insLikeAPubb();
    	ControllerPubblicazione.getPubb().setNumLike( ControllerPubblicazione.getPubb().getNumLike() + 1);
    	nLikeLabel.setText(Integer.toString( ControllerPubblicazione.getPubb().getNumLike() ));
    	
    }
    @FXML private JFXButton delLikeButt;
    @FXML void delLike()
    {
    	insLikeButt.setVisible(true);
    	delLikeButt.setVisible(false);
    	ControllerLikes.delLikeAPubb();
    	ControllerPubblicazione.getPubb().setNumLike( ControllerPubblicazione.getPubb().getNumLike() - 1);
    	nLikeLabel.setText(Integer.toString( ControllerPubblicazione.getPubb().getNumLike() ));
    }
    
    @FXML private JFXButton insRecensioneButt;
    @FXML void insRecensione() 
    {
    	try {
    		Stage window = new Stage();
    		window.initModality(Modality.APPLICATION_MODAL);
    		window.setTitle( "Inserisci Recensione" );
    		window.getIcons().add(new Image("modifica.png"));
    		FXMLLoader loader = new FXMLLoader();
    		Pane root;
		
			root = loader.load(getClass().getResource("/view/InserisciTesto.fxml").openStream());
		
			Scene scene = new Scene(root);
			window.setScene( scene );
			InserisciTestoController ITC = (InserisciTestoController) loader.getController();
			ITC.setTitolo("Recensione");
			window.showAndWait();
			String testo = ITC.getTesto();
			if( testo.length() != 0 )
			{
				ControllerRecensione.insRecensione( testo );
				insRecensioneButt.setVisible(false);
				// nel caso l'utente e' di grado moderatore o superiore la sua recensione viene auto-approvata
				RuoloUtente ruolo = ControllerUtente.getUtente().getRuolo();
				switch ( ruolo )
				{
					case Moderatore :
					case Amministratore :
					case SuperAmministratore :
						ControllerRecensione.approvaRecensione( ControllerPubblicazione.getPubb().getID(), ControllerUtente.getUtente().getID());
						break;
				default:
					break;
				}
			}
			
    	} catch (IOException e) {e.printStackTrace();}
    }
    
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		setDettagliPubblicazione();
		
		if ( ControllerUtente.getUtente() == null )
		{
		    modTitoloButt.setVisible(false);
		    modEditoreButt.setVisible(false);
		    modAutoriButt.setVisible(false);
		    modParoleButt.setVisible(false);
		    modIsbnButt.setVisible(false);
		    modDataPubbButt.setVisible(false);
		    modNPagButt.setVisible(false);
		    modLinguaButt.setVisible(false);
		    eliminaPubbButt.setVisible(false);
		    visStoricoButt.setVisible(false);
		    insLikeButt.setVisible(false);
		    insRecensioneButt.setVisible(false);
		    
		}
		else
		{
			RuoloUtente ruolo = ControllerUtente.getUtente().getRuolo();
		
			switch ( ruolo )
			{
				case Passivo :
				case Attivo :
					modTitoloButt.setVisible(false);
				    modAutoriButt.setVisible(false);
				    modEditoreButt.setVisible(false);
				    modParoleButt.setVisible(false);
				    modIsbnButt.setVisible(false);
				    modDataPubbButt.setVisible(false);
				    modNPagButt.setVisible(false);
				    modLinguaButt.setVisible(false);
				case Moderatore :
					eliminaPubbButt.setVisible(false);
					break;
				case Amministratore :
					break;
				case SuperAmministratore :
					break;
			default:
				break;
			}
			
			if ( ControllerLikes.controllaUtenteLikePubb() ) delLikeButt.setVisible(true);
			else insLikeButt.setVisible(true);
			if ( ControllerRecensione.utenteInseritoRecensione() ) insRecensioneButt.setVisible(false);
		}
	}
	private void setDettagliPubblicazione()
	{
		titoloLabel.setText( ControllerPubblicazione.getPubb().getTitolo());
	    autoriLabel.setText( ControllerUtility.arrayDiAutoriToString(ControllerPubblicazione.getPubb().getListaAutori()));
	    editoreLabel.setText( ControllerPubblicazione.getPubb().getEditore());
	    paroleLabel.setText( ControllerUtility.arrayDiParoleToString(ControllerPubblicazione.getPubb().getListaparolechiave()));
	    isbnLabel.setText( ControllerPubblicazione.getPubb().getISBN());
	    nLikeLabel.setText( Integer.toString(ControllerPubblicazione.getPubb().getNumLike()));
	    nRecensioniLabel.setText( Integer.toString(ControllerPubblicazione.getPubb().getNumRec()));
	    dataPubbLabel.setText( ControllerUtility.formatLocaDateFromDB(ControllerPubblicazione.getPubb().getDataPubblicazione()));
	    dataUltModLabel.setText( ControllerPubblicazione.getPubb().getDataUltimaModifica().toString());
	    nPagLabel.setText( Integer.toString(ControllerPubblicazione.getPubb().getNpag()));
	    linguaLabel.setText( ControllerPubblicazione.getPubb().getLingua());
	}

	private void updatePubblicazione()
	{
		if ( ControllerPubblicazione.getPubb() == null )
		{
			titoloLabel.setText("Pubblicazione Eliminata");
		    autoriLabel.setText("");
		    editoreLabel.setText("");
		    paroleLabel.setText("");
		    isbnLabel.setText("");
		    nLikeLabel.setText("");
		    nRecensioniLabel.setText("");
		    dataPubbLabel.setText("");
		    dataUltModLabel.setText("");
		    nPagLabel.setText("");
		    linguaLabel.setText("");
			
			modTitoloButt.setDisable(true);
			modAutoriButt.setDisable(true);
			modEditoreButt.setDisable(true);
		    modParoleButt.setDisable(true);
		    modIsbnButt.setDisable(true);
		    modDataPubbButt.setDisable(true);
		    modNPagButt.setDisable(true);
		    modLinguaButt.setDisable(true);
		    eliminaPubbButt.setDisable(true);
		    visDescrizioneButt.setDisable(true);
		    visIndiceButt.setDisable(true);
		    visRistampeButt.setDisable(true);
		    visSorgentiButt.setDisable(true);
		    visRecensioniButt.setDisable(true);
		    visStoricoButt.setDisable(true);
		    insLikeButt.setDisable(true);
		    insRecensioneButt.setDisable(true);
		    return;
		}
		ControllerPubblicazione.setPubbByID( ControllerPubblicazione.getPubb().getID() );
		setDettagliPubblicazione();
	}
	
	@FXML private JFXButton indietroButt;
	@FXML void close(ActionEvent event) { ((Stage) ((Node)  event.getSource()).getScene().getWindow()).close(); }
}
