package application.controller;

import application.Main;
import application.model.Card;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameOneController
{
	Main main;
	int wastePointer = 0;
	int pilePointer = 23;
	
	Card[] cardPile = new Card[24];
	Card[] cardWaste = new Card[24];
	Card[] cardFoundationHearts = new Card[13];
	Card[] cardFoundationSpades = new Card[13];
	Card[] cardFoundationDiamonds = new Card[13];
	Card[] cardFoundationClubs = new Card[13];
	Card[][] cardTableu = new Card[7][20];
	
			
			
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
	
	@FXML
	VBox tableu1, tableu2, tableu3, tableu4, tableu5, tableu6, tableu7;
	
	public void setMain(Main main)
	{
		this.main = main;
		System.out.println("Welcome! " + Main.player);
		System.out.println("Mode: 1 Waste");
		setup();
		refreshScene();

	}
	
	public void setup() //distributes the card from the deck to the pile and tableu.
	{
		int i = 1;
		for (int j = 0; j < 7; j++)
		{
			for (int k = 0; k < i; k++)
			{
				cardTableu[j][k] = new Card(Main.deck.popCard());
			}
			cardTableu[j][i-1].turnCardUp(true);
			i++;
		}

		i = 0;
		while(Main.deck.getDeckSize() != 0)
		{
			cardPile[i] = new Card(Main.deck.popCard());
			i++;
		}
		
	}
	
	@FXML
	public void clickedPile()
	{
		if(cardPile[0] != null)
		{
			cardWaste[wastePointer] = new Card(cardPile[pilePointer]);
			cardPile[pilePointer] = null;
			wastePointer++;
			pilePointer--;
		}
		else
		{
			while(wastePointer != 0)
			{
				cardPile[pilePointer + 1] = new Card(cardWaste[wastePointer - 1]);
				cardWaste[wastePointer - 1] = null;
				wastePointer--;
				pilePointer++;
			}
		}
		refreshScene();
	}
	
	public void refreshScene()
	{
		foundationHearts.setStyle("-fx-background-image: url('/AH.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		foundationSpades.setStyle("-fx-background-image: url('/AS.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		foundationDiamonds.setStyle("-fx-background-image: url('/AD.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		foundationClubs.setStyle("-fx-background-image: url('/AC.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		
		if (cardWaste[0] != null)
		{
			waste.setStyle("-fx-background-image: url('" + cardWaste[wastePointer - 1] + ".jpg'); -fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat" );
		}
		else
		{
			waste.setStyle("");
		}
		
		if (cardPile[0] != null)
		{
			pile.setStyle("-fx-background-image: url('/blue.jpg'); -fx-background-size: 100%; -fx-opacity: 1");
		}
		else
		{
			pile.setStyle("");
		}
		int i = 0;
		

		while(cardTableu[0][i] != null)
		{
			if(!cardTableu[0][i].isTurnedUp())
				tableu1.getChildren().get(i).setStyle("-fx-background-image: url('blue.jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			else
				tableu1.getChildren().get(i).setStyle("-fx-background-image: url('" + cardTableu[0][i] + ".jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			i++;
		}
		
		i = 0;
		
		while(cardTableu[1][i] != null)
		{
			if(!cardTableu[1][i].isTurnedUp())
				tableu2.getChildren().get(i).setStyle("-fx-background-image: url('blue.jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			else
				tableu2.getChildren().get(i).setStyle("-fx-background-image: url('" + cardTableu[1][i] + ".jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			i++;
		}
		
		i = 0;
		
		while(cardTableu[2][i] != null)
		{
			if(!cardTableu[2][i].isTurnedUp())
				tableu3.getChildren().get(i).setStyle("-fx-background-image: url('blue.jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			else
				tableu3.getChildren().get(i).setStyle("-fx-background-image: url('" + cardTableu[2][i] + ".jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			i++;
		}
		
		i = 0;
		
		while(cardTableu[3][i] != null)
		{
			if(!cardTableu[3][i].isTurnedUp())
				tableu4.getChildren().get(i).setStyle("-fx-background-image: url('blue.jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			else
				tableu4.getChildren().get(i).setStyle("-fx-background-image: url('" + cardTableu[3][i] + ".jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			i++;
		}
		
		i = 0;
		
		while(cardTableu[4][i] != null)
		{
			if(!cardTableu[4][i].isTurnedUp())
				tableu5.getChildren().get(i).setStyle("-fx-background-image: url('blue.jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			else
				tableu5.getChildren().get(i).setStyle("-fx-background-image: url('" + cardTableu[4][i] + ".jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			i++;
		}
		
		i = 0;
		
		while(cardTableu[5][i] != null)
		{
			if(!cardTableu[5][i].isTurnedUp())
				tableu6.getChildren().get(i).setStyle("-fx-background-image: url('blue.jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			else
				tableu6.getChildren().get(i).setStyle("-fx-background-image: url('" + cardTableu[5][i] + ".jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			i++;
		}
		
		i = 0;
		
		while(cardTableu[6][i] != null)
		{
			if(!cardTableu[6][i].isTurnedUp())
				tableu7.getChildren().get(i).setStyle("-fx-background-image: url('blue.jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			else
				tableu7.getChildren().get(i).setStyle("-fx-background-image: url('" + cardTableu[6][i] + ".jpg');-fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
			i++;
		}
		
		printInConsole();
	}
	
	public void printInConsole()
	{
		System.out.println("Pile:");
		int i = 0;
		while(i < cardPile.length && cardPile[i] != null)
		{
			System.out.print(cardPile[i] + " ");
			if(i == 10)
			{
				System.out.println();
			}
			i++;
		}
		System.out.println();
		
		System.out.println("Waste:");
		i = 0;
		while(i < cardWaste.length &&cardWaste[i] != null)
		{
			System.out.print(cardWaste[i] + " ");
			if(i == 10)
			{
				System.out.println();
			}
			i++;
		}
		System.out.println();
		
		System.out.println("Tableu:");
		
		for(int j = 0; j < 20; j++)
		{
			for (i = 0; i < 7; i++)
			{
				if(cardTableu[i][j] == null)
				{
					System.out.print("-\t");
					continue;
				}
				System.out.print(cardTableu[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	@FXML
	public void endGame()
	{
		main.exit();
		main.startMainMenu();
	}
}
