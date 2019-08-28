package application;

import DAO.connectors.MySQLDBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Catalogo Bibliografico UNIVAQ");
			primaryStage.getIcons().add(new Image("agenda.png"));
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DAO.connectors.MySQLDBConnector.creaConnessione();
		MySQLDBConnector.mysqlInit();
		launch(args);
	}
	
}