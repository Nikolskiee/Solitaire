package application.controller;



import application.Main;
import application.model.Card;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	Card[][] cardTableu = new Card[7][13];
	
			
			
	@FXML
	AnchorPane pile, waste, foundationHearts, foundationSpades, foundationDiamonds, foundationClubs;
	
	@FXML
	StackPane tableu1, tableu2, tableu3, tableu4, tableu5, tableu6, tableu7;
	

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
		
		StackPane[] tableu = {tableu1, tableu2, tableu3, tableu4, tableu5, tableu6, tableu7};
		
		for(int j = 0; j < 7; j++)
		{
			tableu[j].getChildren().clear();
			
			int i = 0;
			double y = 0;
			while(cardTableu[j][i] != null)
			{
				Pane card = new Pane();
				card.setPrefSize(100,150);
				if(cardTableu[j][i].isTurnedUp())
				{
					card.setStyle("-fx-background-image: url('" + cardTableu[j][i] + ".jpg'); -fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
				}
				else
				{
					card.setStyle("-fx-background-image: url('/blue.jpg'); -fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat");
				}
				
				card.setTranslateY(y);
				
				y = y + 20;
				
				tableu[j].getChildren().add(card);
				i++;
			}
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
		
		for(int j = 0; j < 13; j++)
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
