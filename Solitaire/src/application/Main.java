package application;
	
import java.io.IOException;

import application.controller.GameOneController;
import application.controller.HighScoreController;
import application.controller.MainMenuController;
import application.controller.ModeController;
import application.model.Deck;
import application.model.Scores;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	
	Stage primaryStage; //stores the "front" view of the application.
	public static Scores[] high = new Scores[1000000];
	public static int mode;
	public static String player = "Player";
	public static Deck deck;
	public static int score;
	
	
	@Override
	public void start(Stage primaryStage) { //starts the application
		if(high[0] == null) //initializes the table of high scores with imaginary pre-existing players
		{
			high[0] = new Scores ("Ivan", 500);
			high[1] = new Scores ("Derick", 450);
			high[2] = new Scores("Fed", 400);
			high[3] = new Scores("Nichol", 350);
			high[4] = new Scores("Romaine", 300);
			high[5] = new Scores("Dan", 250);
			high[6] = new Scores ("Will", 200);
			high[7] = new Scores ("Cornelius", 150);
			high[8] = new Scores ("Beatrice", 100);
			high[9] = new Scores ("Sekki", 50);
		}
		this.primaryStage = primaryStage;
		startMainMenu();
	}
	
	public void startMainMenu() //brings the "Main Menu" view to the front.
	{
		score = 0;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainMenuView.fxml"));
			AnchorPane menu = (AnchorPane) loader.load();

			Scene scene = new Scene(menu);
			scene.getStylesheets().add("application/application.css");
			primaryStage.setTitle("Solitaire");
			primaryStage.setResizable(false);
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
			secondaryStage.setTitle("Solitaire");
			secondaryStage.setResizable(false);
			secondaryStage.initOwner(primaryStage);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
			HighScoreController controller = loader.getController();
			controller.setTable(high);
			controller.setStage(secondaryStage);
	
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void startGameOne() //Shows the "Game" as primary stage.
	{
		deck = new Deck();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GameOneView.fxml"));
			AnchorPane game = (AnchorPane) loader.load();

			Scene scene = new Scene(game);
			scene.getStylesheets().add("application/application.css");
			primaryStage.setTitle("Solitaire");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			GameOneController controller = loader.getController();
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
			secondaryStage.setTitle("Solitaire");
			secondaryStage.setResizable(false);
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
	
	public void recordScore(String name, int score)
	{
		int i = 0;
		
		Scores record = new Scores(name, score);
		
		while(true)
		{
			if(high[i] == null)
			{
				high[i] = record;
				break;
			}
			if(high[i].getScore() < score)
			{
				Scores swap = high[i];
				high[i] = record;
				record = swap;
			}	
			i++;
		}
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
