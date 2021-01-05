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
	@FXML
	AnchorPane waste;
	
	@FXML
	AnchorPane foundationHearts;
	
	@FXML
	AnchorPane foundationSpades;
	
	@FXML
	AnchorPane foundationDiamonds;
	
	@FXML
	AnchorPane foundationClubs;
	
	public void setMain(Main main)
	{
		this.main = main;
		System.out.println("Welcome! " + Main.player);
		System.out.println("Mode: 1 Waste");
		pile.setStyle("-fx-background-image: url('/blue.jpg'); -fx-background-size: 100%; -fx-opacity: 1");
		foundationHearts.setStyle("-fx-background-image: url('/AH.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		foundationSpades.setStyle("-fx-background-image: url('/AS.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		foundationDiamonds.setStyle("-fx-background-image: url('/AD.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		foundationClubs.setStyle("-fx-background-image: url('/AC.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");

	}
	
	@FXML
	public void endGame()
	{
		main.exit();
		main.startMainMenu();
	}
}
