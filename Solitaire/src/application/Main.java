package application;
	
import application.controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
