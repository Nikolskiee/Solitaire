package application.controller;

import application.Main;
import javafx.fxml.FXML;

public class GameOneController
{
	Main main;
	
	public void setMain(Main main)
	{
		this.main = main;
	}
	
	@FXML
	public void endGame()
	{
		main.exit();
		main.startMainMenu();
	}
}
