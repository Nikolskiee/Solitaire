package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
	
	Main main;
	
	public void setMain(Main main)
	{
		this.main = main;
	}
	
	@FXML
	public void showGame()
	{
		if(Main.isWaste1)
		{
			main.exit();
			main.startGameOne();
			
		}
		else 
		{
			main.exit();
			main.startGameThree();
		}
	}
	
	@FXML void showMode()
	{
		main.startMode();
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
