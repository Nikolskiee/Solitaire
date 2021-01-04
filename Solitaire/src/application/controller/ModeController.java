package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ModeController {
	
	Main main;
	
	private Stage stage;
	
	public void setMain(Main main)
	{
		this.main = main;
	}
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	
	@FXML
	public void setModeAs1Waste()
	{
		main.exit();
		main.startGameOne();
		stage.close();
	}
	
	@FXML
	public void setModeAs3Waste()
	{
		main.exit();
		main.startGameThree();
		stage.close();
	}
}
