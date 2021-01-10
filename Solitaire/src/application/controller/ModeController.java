package application.controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModeController {
	
	Main main;
	
	@FXML
	private TextField name;
	
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

			Main.player = name.getText();
			Main.mode = 1;
			main.exit();
			main.startGameOne();
			stage.close();
	}
	
	@FXML
	public void setModeAs3Waste()
	{

			Main.player = name.getText();
			Main.mode = 3;
			main.exit();
			main.startGameOne();
			stage.close();
	}
}
