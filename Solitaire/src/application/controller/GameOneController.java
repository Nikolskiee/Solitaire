package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GameOneController
{
	Main main;
	
	@FXML
	AnchorPane pile;
	
	public void setMain(Main main)
	{
		this.main = main;
		System.out.println("Welcome! " + Main.player);
		System.out.println("Mode: 1 Waste");
		pile.setStyle("-fx-background-image: url('/2C.jpg'); -fx-background-size: 100%");

	}
	
	@FXML
	public void endGame()
	{
		main.exit();
		main.startMainMenu();
	}
}
