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
	int heartsPointer = 0;
	int	spadesPointer = 0;
	int diamondsPointer = 0;
	int clubsPointer = 0;
	int[] originTableu = null;
	boolean isFirstClick = true;
	boolean fromWaste = false;
	boolean fromHearts = false;
	boolean fromSpades = false;
	boolean fromDiamonds = false;
	boolean fromClubs = false;
	Card[] buffer = new Card[13];
	
	Card[] cardPile = new Card[24];
	Card[] cardWaste = new Card[24];
	Card[] cardFoundationHearts = new Card[13];
	Card[] cardFoundationSpades = new Card[13];
	Card[] cardFoundationDiamonds = new Card[13];
	Card[] cardFoundationClubs = new Card[13];
	Card[][] cardTableu = new Card[7][52];
	
			
			
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
		fromWaste = false;
		fromHearts = false;
		fromSpades = false;
		fromDiamonds = false;
		fromClubs = false;
		buffer = null;
		isFirstClick = true;
		refreshScene();
	}
	
	public void updateTurnedUpCards()
	{
		for (int i = 0; i < 7; i++)
		{
			int j = 0;
			while(cardTableu[i][j] != null && j < cardTableu[i].length - 1)
			{
				if(!cardTableu[i][j].isTurnedUp() && cardTableu[i][j+1] == null)
				{
					cardTableu[i][j].turnCardUp(true);
				}
				j++;
			}
		}
	}
	
	@FXML
	public void clickedOnHearts()
	{
		if(isFirstClick)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationHearts[heartsPointer - 1];
			fromHearts = true;
			isFirstClick = false;
			return;
		}
		if(!isFirstClick && buffer[1] == null && cardFoundationHearts[0] == null && buffer[0].toString().compareTo("AH") == 0)
		{
			cardFoundationHearts[0] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			heartsPointer++;
			fromHearts = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}
		if(!isFirstClick && buffer[1] == null && buffer[0].isSameSuit(cardFoundationHearts[heartsPointer - 1]) && buffer[0].cardDifference(cardFoundationHearts[heartsPointer - 1]) == 1 && buffer[0].isGreater(cardFoundationHearts[heartsPointer - 1]))
		{
			cardFoundationHearts[heartsPointer] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			heartsPointer++;
			buffer = null;
			fromHearts = false;
			isFirstClick = true;
			refreshScene();
			return;
		}
		buffer = null;
		isFirstClick = true;
	}
	
	@FXML
	public void clickedOnSpades()
	{
		if(isFirstClick)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationSpades[spadesPointer - 1];
			fromSpades = true;
			isFirstClick = false;
		}

		if(!isFirstClick && buffer[1] == null && cardFoundationSpades[0] == null && buffer[0].toString().compareTo("AS") == 0)
		{
			cardFoundationSpades[0] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			spadesPointer++;
			buffer = null;
			fromSpades = false;
			isFirstClick = true;
			refreshScene();
			return;
		}
		if(!isFirstClick && buffer[1] == null && buffer[0].isSameSuit(cardFoundationSpades[spadesPointer - 1]) && buffer[0].cardDifference(cardFoundationSpades[spadesPointer - 1]) == 1 && buffer[0].isGreater(cardFoundationSpades[spadesPointer - 1]))
		{
			cardFoundationSpades[spadesPointer] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			spadesPointer++;
			fromSpades = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}

		buffer = null;
		isFirstClick = true;
	}
	
	@FXML
	public void clickedOnDiamonds()
	{
		if(isFirstClick)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationDiamonds[diamondsPointer - 1];
			fromDiamonds = true;
			isFirstClick = false;
		}
		if(!isFirstClick && buffer[1] == null && cardFoundationDiamonds[0] == null && buffer[0].toString().compareTo("AD") == 0)
		{
			cardFoundationDiamonds[0] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			diamondsPointer++;
			fromDiamonds = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}
		if(!isFirstClick && buffer[1] == null && buffer[0].isSameSuit(cardFoundationDiamonds[diamondsPointer - 1]) && buffer[0].cardDifference(cardFoundationDiamonds[diamondsPointer - 1]) == 1 && buffer[0].isGreater(cardFoundationDiamonds[diamondsPointer - 1]))
		{
			cardFoundationDiamonds[diamondsPointer] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			diamondsPointer++;
			fromDiamonds = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}
		
		buffer = null;
		isFirstClick = true;
	}
	
	@FXML
	public void clickedOnClubs()
	{
		if(isFirstClick)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationClubs[clubsPointer - 1];
			fromClubs = true;
			isFirstClick = false;
		}
		if(!isFirstClick && buffer[1] == null && cardFoundationClubs[0] == null && buffer[0].toString().compareTo("AC") == 0)
		{
			cardFoundationClubs[0] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			clubsPointer++;
			buffer = null;
			fromClubs = false;
			isFirstClick = true;
			refreshScene();
			return;
		}
		if(!isFirstClick && buffer[1] == null && buffer[0].isSameSuit(cardFoundationClubs[clubsPointer - 1]) && buffer[0].cardDifference(cardFoundationClubs[clubsPointer - 1]) == 1 && buffer[0].isGreater(cardFoundationClubs[clubsPointer - 1]))
		{
			cardFoundationClubs[clubsPointer] = new Card(buffer[0]);
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
			
			
			clubsPointer++;
			fromClubs = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}
		buffer = null;
		isFirstClick = true;
	}
	
	@FXML
	public void clickedOnWaste()
	{
		if(isFirstClick && wastePointer != 0)
		{
			buffer = new Card[52];
			buffer[0] = new Card(cardWaste[wastePointer - 1]);
			fromWaste = true;
			isFirstClick = false;
			return;
		}
		if(!isFirstClick)
		{
			buffer = null;
			isFirstClick = true;
		}
		fromHearts = false;
		fromSpades = false;
		fromDiamonds = false;
		fromClubs = false;
	}
	
	public void clickedOnTableu(int row) //for an empty row
	{
		if(!isFirstClick && buffer[0].getValue() == 13)
		{
			int i = 0;
			int column = 0;
			while(buffer[i] != null)
			{
				cardTableu[row][column] = new Card(buffer[i]);
				i++;
				column++;
			}
			
			if(fromWaste)
			{
				wastePointer--;
				cardWaste[wastePointer] = null;
			}
			else if (fromHearts)
			{
				heartsPointer--;
				cardFoundationHearts[heartsPointer] = null;
			}
			else if (fromSpades)
			{
				spadesPointer--;
				cardFoundationSpades[spadesPointer] = null;
			}
			else if (fromDiamonds)
			{
				diamondsPointer--;
				cardFoundationDiamonds[diamondsPointer] = null;
			}
			else if (fromClubs)
			{
				clubsPointer--;
				cardFoundationClubs[clubsPointer] = null;
			}
			else
			{
				int r = originTableu[0];
				int c = originTableu[1];
				
				while(cardTableu[r][c] != null)
				{
					cardTableu[r][c] = null;
					c++;
				}
			}
		}
		
		fromWaste = false;
		fromHearts = false;
		fromSpades = false;
		fromDiamonds = false;
		fromClubs = false;
		originTableu = null;
		isFirstClick = true;
		refreshScene();
	}
	
	public void clickedOnTableu(int row, int column) //on specific card
	{
		if(isFirstClick)
		{
			originTableu = new int[2];
			originTableu[0] = row;
			originTableu[1] = column;
			
			buffer = new Card[52];
			int i = 0;
			while(cardTableu[row][column] != null)
			{
				buffer[i] = new Card(cardTableu[row][column]);
				column++;
				i++;
			}
			
			isFirstClick = false;
			return;
		}
		else
		{
			if(buffer[0].getColor() != cardTableu[row][column].getColor() && buffer[0].cardDifference(cardTableu[row][column]) == 1 && cardTableu[row][column].isGreater(buffer[0]))
			{
				int i = 0;
				while(buffer[i] != null)
				{
					cardTableu[row][column + 1] = new Card(buffer[i]);
					i++;
					column++;
				}
				
				if(fromWaste)
				{
					wastePointer--;
					cardWaste[wastePointer] = null;
				}
				else if (fromHearts)
				{
					heartsPointer--;
					cardFoundationHearts[heartsPointer] = null;
				}
				else if (fromSpades)
				{
					spadesPointer--;
					cardFoundationSpades[spadesPointer] = null;
				}
				else if (fromDiamonds)
				{
					diamondsPointer--;
					cardFoundationDiamonds[diamondsPointer] = null;
				}
				else if (fromClubs)
				{
					clubsPointer--;
					cardFoundationClubs[clubsPointer] = null;
				}
				else
				{
					int r = originTableu[0];
					int c = originTableu[1];
					
					while(cardTableu[r][c] != null)
					{
						cardTableu[r][c] = null;
						c++;
					}
				}
			}
		}
		fromWaste = false;
		fromHearts = false;
		fromSpades = false;
		fromDiamonds = false;
		fromClubs = false;
		originTableu = null;
		isFirstClick = true;
		refreshScene();
	}
	
	public void refreshScene()
	{
		updateTurnedUpCards();
		
		if(cardFoundationHearts[0] != null)
		{
			foundationHearts.setStyle("-fx-background-image: url('" + cardFoundationHearts[heartsPointer - 1] + ".jpg'); -fx-background-size: 100%; -fx-opacity: 1");
		}
		else
		{
			foundationHearts.setStyle("-fx-background-image: url('AH.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		}
		
		if(cardFoundationSpades[0] != null)
		{
			foundationSpades.setStyle("-fx-background-image: url('" + cardFoundationSpades[spadesPointer - 1] + ".jpg'); -fx-background-size: 100%; -fx-opacity: 1");
		}
		else
		{
			foundationSpades.setStyle("-fx-background-image: url('AS.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		}
		
		if(cardFoundationDiamonds[0] != null)
		{
			foundationDiamonds.setStyle("-fx-background-image: url('" + cardFoundationDiamonds[diamondsPointer - 1] + ".jpg'); -fx-background-size: 100%; -fx-opacity: 1");
		}
		else
		{
			foundationDiamonds.setStyle("-fx-background-image: url('AD.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		}
		
		if(cardFoundationClubs[0] != null)
		{
			foundationClubs.setStyle("-fx-background-image: url('" + cardFoundationClubs[clubsPointer - 1] + ".jpg'); -fx-background-size: 100%; -fx-opacity: 1");
		}
		else
		{
			foundationClubs.setStyle("-fx-background-image: url('AC.jpg'); -fx-background-size: 100%; -fx-opacity: 0.25");
		}
		
		
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
			
			if(cardTableu[j][0] == null)
			{
				Pane card = new Pane();
				card.setPrefSize(100, 150);
				card.setStyle("-fx-background-image url('gray.jpg'); -fx-background-repeat: no-repeat; -fx-opacity: 0.25");
				int row = j;
				card.setOnMouseClicked(event -> clickedOnTableu(row));
				tableu[j].getChildren().add(card);
				continue;
			}
			
			int i = 0;
			double y = 0;
			while(cardTableu[j][i] != null)
			{
				
				Pane card = new Pane();
				card.setPrefSize(100,150);
				if(cardTableu[j][i].isTurnedUp())
				{
					card.setStyle("-fx-background-image: url('" + cardTableu[j][i] + ".jpg'); -fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat;");
					int row = j;
					int column = i;
					card.setOnMouseClicked(event -> clickedOnTableu(row, column));
				}
				else
				{
					card.setStyle("-fx-background-image: url('/blue.jpg'); -fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat;");
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
		
		for(int j = 0; j < 52; j++)
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
