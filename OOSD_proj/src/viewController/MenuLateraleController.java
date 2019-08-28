package viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

import com.jfoenix.controls.JFXButton;

import controller.ControllerUtente;
import controller.ControllerUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.TipiEnum.RuoloUtente;

public class MenuLateraleController implements Initializable {

    @FXML private JFXButton home;
    @FXML public void visHome()
    {
    	if ( !(ultimaChiave == 1) ) selectSection(1);
    	try {
			Node nodo = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			gui.setCenter( nodo );
		} catch (IOException e) { e.printStackTrace(); }
    }
    
    @FXML private JFXButton profilo;
    @FXML public void visProfilo()
    {
    	if ( ControllerUtente.getUtente() == null ) return;
    	if ( !(ultimaChiave == 2) ) selectSection(2);
    	
    	try {
			Node nodo = FXMLLoader.load(getClass().getResource("/view/UtenteConnesso.fxml"));
			gui.setCenter( nodo );
		} catch (IOException e) { e.printStackTrace(); }
    }
    @FXML private JFXButton catalogo;
    @FXML public void visCatalogo()
    {
    	if ( !(ultimaChiave == 3) ) selectSection(3);
    	try {
    		Node nodo = FXMLLoader.load(getClass().getResource("/view/Catalogo.fxml"));
			gui.setCenter( nodo );
		} catch (IOException e) { e.printStackTrace(); }
    }
    
    @FXML private JFXButton ricercaLibro;
    @FXML public void visRicercaPubblicazioni()
    {
    	if ( !(ultimaChiave == 4) ) selectSection(4);
    	try {
			Node nodo = FXMLLoader.load(getClass().getResource("/view/RicercaPubblicazioni.fxml"));
			gui.setCenter( nodo );
		} catch (IOException e) { e.printStackTrace(); }
    }
    
    @FXML private JFXButton listaUtenti;
    @FXML void visListaUtenti()
    {
    	if ( !(ultimaChiave == 5) ) selectSection(5);
    	try {
			Node nodo = FXMLLoader.load(getClass().getResource("/view/ListaUtenti.fxml"));
			gui.setCenter( nodo );
		} catch (IOException e) { e.printStackTrace(); }
    }

    @FXML private JFXButton inserisciLibro;
    @FXML void visInserisciPubblicazione()
    {
    	if ( !(ultimaChiave == 6) ) selectSection(6);
    	try {
			Node nodo = FXMLLoader.load(getClass().getResource("/view/InserisciPubblicazione.fxml"));
			gui.setCenter( nodo );
		} catch (IOException e) { e.printStackTrace(); }
    }
    @FXML private JFXButton storico;
    @FXML void visStorico()
    {
    	if ( !(ultimaChiave == 7) ) selectSection(7);
    	try {
			Node nodo = FXMLLoader.load(getClass().getResource("/view/Storico.fxml"));
			gui.setCenter( nodo );
		} catch (IOException e) { e.printStackTrace(); }
    }

    @FXML private JFXButton RecNonAppButt;
    @FXML void visRecNonApp()
    {
    	if ( !(ultimaChiave == 8) ) selectSection(8);
    	try 
		{	
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ListaRecensioni.fxml").openStream());
			ListaRecensioniController LRC = (ListaRecensioniController) loader.getController();
			LRC.setModeVisRecNonApprovate();
			ControllerUtility.getGui().setCenter(root);
		} catch (IOException e) { e.printStackTrace(); }
    }

    private BorderPane gui;
    public void setGui( BorderPane gui ) { this.gui = gui; }
    
    private TreeMap<Integer,JFXButton> tree = new TreeMap<Integer,JFXButton>();
    private int ultimaChiave = -1;
    
    public void setultimaChiave(int ultimaChiave) { this.ultimaChiave = ultimaChiave; }
    
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		tree.put(1, home); home.setVisible(true);
		tree.put(2, profilo); profilo.setVisible(true);
		tree.put(3, catalogo); catalogo.setVisible(true);
		tree.put(4, ricercaLibro); ricercaLibro.setVisible(true);
		tree.put(5, listaUtenti); listaUtenti.setVisible(true);
		tree.put(6, inserisciLibro); inserisciLibro.setVisible(true);
		tree.put(7, storico); storico.setVisible(true);
		tree.put(8, RecNonAppButt); RecNonAppButt.setVisible(true);
		
		if ( ControllerUtente.getUtente() == null )
		{
			profilo.setVisible(false);
			listaUtenti.setVisible(false);
			inserisciLibro.setVisible(false);
			storico.setVisible(false);
			RecNonAppButt.setVisible(false);
		}
		else
		{
			RuoloUtente ruolo = ControllerUtente.getUtente().getRuolo();
		
			switch ( ruolo )
			{
				case Passivo :
				case Attivo :
					RecNonAppButt.setVisible(false);
					inserisciLibro.setVisible(false);
					break;
				case Moderatore :
				case Amministratore :
				case SuperAmministratore :
			default:
				break;
			}
		}
	}
	
	public void selectSection( int chiaveSezione)
	{
		if( chiaveSezione != -1 )tree.get(chiaveSezione).setStyle("-fx-background-color:   #009bff");
		if( ultimaChiave != -1 )tree.get(ultimaChiave).setStyle("-fx-background-color:   #202020");
		ultimaChiave = chiaveSezione;
	}

}
