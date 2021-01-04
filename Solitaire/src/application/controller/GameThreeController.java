package application.controller;

import application.Main;
import javafx.fxml.FXML;

public class GameThreeController {
	
	Main main;
	
	public void setMain(Main main)
	{
		this.main = main;
		System.out.println("Welcome! " + Main.player);
		System.out.println("Mode: 3 Wastes");
	}
	
	@FXML
	public void endGame()
	{
		main.exit();
		main.startMainMenu();
	}

}
