package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
	
	Main main;
	
	@FXML
	Button highScoreBtn;
	
	@FXML
	Button exitBtn;
	
	public void setMain(Main main)
	{
		this.main = main;
	}
	
	@FXML
	public void showHighScore()
	{
		main.startHighScore();
	}
	
	@FXML
	public void exit()
	{
		main.exit();
	}
	
	
	

}
