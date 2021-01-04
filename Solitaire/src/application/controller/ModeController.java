package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ModeController {
	
	
	private Stage stage;
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	
	@FXML
	public void setModeAs1Waste()
	{
		Main.isWaste1 = true;
		stage.close();
	}
	
	@FXML
	public void setModeAs3Waste()
	{
		Main.isWaste1 = false;
		stage.close();
	}
}
