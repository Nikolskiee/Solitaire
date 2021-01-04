package application;
	
import java.io.IOException;

import application.controller.HighScoreController;
import application.controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	
	Stage primaryStage; //stores the "front" view of the application.
	
	
	@Override
	public void start(Stage primaryStage) { //starts the application
		this.primaryStage = primaryStage;
		startMainMenu();
	}
	
	public void startMainMenu() //brings the "Main Menu" view to the front.
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainMenuView.fxml"));
			AnchorPane menu = (AnchorPane) loader.load();

			Scene scene = new Scene(menu);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			MainMenuController controller = loader.getController();
			controller.setMain(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startHighScore() //shows the "High Score" as secondary stage.
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HighScoreView.fxml"));
			AnchorPane view = (AnchorPane) loader.load();
	
			Scene scene = new Scene(view);
			Stage secondaryStage = new Stage();
			secondaryStage.initModality(Modality.APPLICATION_MODAL);
	
			secondaryStage.initOwner(primaryStage);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
			HighScoreController controller = loader.getController();
	
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
