package application;
	
import java.io.IOException;

import application.controller.GameOneController;
import application.controller.GameThreeController;
import application.controller.HighScoreController;
import application.controller.MainMenuController;
import application.controller.ModeController;
import application.model.Scores;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	
	Stage primaryStage; //stores the "front" view of the application.
	static Scores[] high = new Scores[10];
	public static String player = "Player";
	
	
	
	@Override
	public void start(Stage primaryStage) { //starts the application
		this.primaryStage = primaryStage;
		startMainMenu();
	}
	
	public void startMainMenu() //brings the "Main Menu" view to the front.
	{
		high[0] = new Scores ("Ivan", 400);
		high[1] = new Scores ("Derick", 300);
		high[2] = new Scores ("Fed", 200);
		high[3] = new Scores ("Nichol", 100);
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
			secondaryStage.initModality( Modality.APPLICATION_MODAL);
	
			secondaryStage.initOwner(primaryStage);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
			HighScoreController controller = loader.getController();
			controller.setTable(high);
	
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void startGameOne() //Shows the "Game" as primary stage.
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GameOneView.fxml"));
			AnchorPane game = (AnchorPane) loader.load();

			Scene scene = new Scene(game);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			GameOneController controller = loader.getController();
			controller.setMain(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startGameThree()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GameThreeView.fxml"));
			AnchorPane game = (AnchorPane) loader.load();

			Scene scene = new Scene(game);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			GameThreeController controller = loader.getController();
			controller.setMain(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startMode()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ModeView.fxml"));
			AnchorPane view = (AnchorPane) loader.load();
	
			Scene scene = new Scene(view);
			Stage secondaryStage = new Stage();
			secondaryStage.initModality( Modality.APPLICATION_MODAL);
	
			secondaryStage.initOwner(primaryStage);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
			ModeController controller = loader.getController();
			controller.setMain(this);
			controller.setStage(secondaryStage);
	
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	public void exit() //closes the program.
	{
		primaryStage.close();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
