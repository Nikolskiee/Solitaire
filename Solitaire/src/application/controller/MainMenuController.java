package application.controller;

import application.Main;
import javafx.fxml.FXML;


public class MainMenuController {
	
	private Main main;
	
	public void setMain(Main main)
	{
		this.main = main;
	}
	
	@FXML
	public void showGame()
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
