package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameCompleteController {
	
	@FXML
	Label name, score;
	
	Main main;
	Stage stage;
	
	
	public void show(Main main, Stage stage)
	{
		this.main = main;
		this.stage = stage;
		
		name.setText(Main.player);
		score.setText("" + Main.score);
		main.recordScore(Main.player, Main.score);
	}
	
	@FXML
	public void backToMenu()
	{
		main.exit();
		main.startMainMenu();
		stage.close();
	}

}
