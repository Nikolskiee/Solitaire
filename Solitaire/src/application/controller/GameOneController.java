package application.controller;



import application.Main;
import application.model.Card;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameOneController
{
	private Main main;
	private int wastePointer = 0;
	private int pilePointer = 23;
	private int heartsPointer = 0;
	private int	spadesPointer = 0;
	private int diamondsPointer = 0;
	private int clubsPointer = 0;
	private int[] originTableu = null;
	private boolean isFirstClick = true;
	private boolean fromWaste = false;
	private boolean fromHearts = false;
	private boolean fromSpades = false;
	private boolean fromDiamonds = false;
	private boolean fromClubs = false;
	private Card[] buffer = new Card[13];
	
	private Card[] cardPile = new Card[24];
	private Card[] cardWaste = new Card[24];
	private Card[] cardFoundationHearts = new Card[13];
	private Card[] cardFoundationSpades = new Card[13];
	private Card[] cardFoundationDiamonds = new Card[13];
	private Card[] cardFoundationClubs = new Card[13];
	private Card[][] cardTableu = new Card[7][52];
	
			
			
	@FXML
	private AnchorPane pile, foundationHearts, foundationSpades, foundationDiamonds, foundationClubs;
	
	@FXML
	private StackPane tableu1, tableu2, tableu3, tableu4, tableu5, tableu6, tableu7, waste;
	
	@FXML
	private Label scoreboard, name;
	
	@FXML 
	private Button button;

	public void setMain(Main main)
	{
		this.main = main;
		System.out.println("Welcome! " + Main.player);
		System.out.println("Mode: " + Main.mode + " Waste");
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
			for (int i = 0; i < Main.mode && i <= pilePointer; i++)
			{
				cardWaste[wastePointer] = new Card(cardPile[pilePointer]);
				cardPile[pilePointer] = null;
				wastePointer++;
				pilePointer--;
			}
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
			if(Main.mode == 1)
			{
				Main.score -= 100;
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
					Main.score += 5;
				}
				j++;
			}
		}
	}
	
	@FXML
	public void clickedOnHearts()
	{
		if(!isFirstClick && cardFoundationHearts[0] == null && buffer[0].toString().compareTo("AH") != 0)
		{
			buffer = null;
			isFirstClick = true;
			return;	
		}
		if(isFirstClick && cardFoundationHearts[0] != null)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationHearts[heartsPointer - 1];
			fromHearts = true;
			isFirstClick = false;
			foundationHearts.setStyle("-fx-background-image: url('" + cardFoundationHearts[heartsPointer - 1] + ".jpg'); -fx-border-color: #ffee00; -fx-border-width: 5; -fx-background-size: 100px 150px; -fx-opacity: 1");
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
			
			Main.score +=10;
			heartsPointer++;
			fromHearts = false;
			fromWaste = false;
			buffer = null;
			isFirstClick = true;
			//foundationHearts.setStyle("-fx-background-image: url('" + cardFoundationHearts[heartsPointer - 1] + ".jpg'); -fx-border-color: #ffee00; -fx-border-width: 5; -fx-background-size: 100px 150px; -fx-opacity: 1");
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
			
			Main.score +=10;
			heartsPointer++;
			buffer = null;
			fromHearts = false;
			fromWaste = false;
			isFirstClick = true;
			refreshScene();
			return;
		}
		buffer = null;
		fromHearts = false;
		fromWaste = false;
		isFirstClick = true;
		refreshScene();
	}
	
	@FXML
	public void clickedOnSpades()
	{
		if(!isFirstClick && cardFoundationSpades[0] == null && buffer[0].toString().compareTo("AS") != 0)
		{
			buffer = null;
			isFirstClick = true;
			return;	
		}
		if(isFirstClick && cardFoundationSpades[0] != null)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationSpades[spadesPointer - 1];
			fromSpades = true;
			isFirstClick = false;
			foundationSpades.setStyle("-fx-background-image: url('" + cardFoundationSpades[spadesPointer - 1] + ".jpg'); -fx-border-color: #ffee00; -fx-border-width: 5; -fx-background-size: 100px 150px; -fx-opacity: 1");
			return;
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
			
			Main.score +=10;
			spadesPointer++;
			buffer = null;
			fromSpades = false;
			fromWaste = false;
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
			
			Main.score +=10;
			spadesPointer++;
			fromSpades = false;
			fromWaste = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}

		fromSpades = false;
		fromWaste = false;
		buffer = null;
		isFirstClick = true;
		refreshScene();
	}
	
	@FXML
	public void clickedOnDiamonds()
	{
		if(!isFirstClick && cardFoundationDiamonds[0] == null && buffer[0].toString().compareTo("AD") != 0)
		{
			buffer = null;
			isFirstClick = true;
			return;	
		}
		if(isFirstClick && cardFoundationDiamonds[0] != null)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationDiamonds[diamondsPointer - 1];
			fromDiamonds = true;
			isFirstClick = false;
			foundationDiamonds.setStyle("-fx-background-image: url('" + cardFoundationDiamonds[diamondsPointer - 1] + ".jpg'); -fx-border-color: #ffee00; -fx-border-width: 5; -fx-background-size: 100px 150px; -fx-opacity: 1");
			return;
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
			
			Main.score +=10;
			diamondsPointer++;
			fromDiamonds = false;
			fromWaste = false;
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
			
			Main.score +=10;
			diamondsPointer++;
			fromDiamonds = false;
			fromWaste = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}
		
		fromDiamonds = false;
		fromWaste = false;
		buffer = null;
		isFirstClick = true;
		refreshScene();
	}
	
	@FXML
	public void clickedOnClubs()
	{
		if(!isFirstClick && cardFoundationClubs[0] == null && buffer[0].toString().compareTo("AC") != 0)
		{
			buffer = null;
			isFirstClick = true;
			return;	
		}
		if(isFirstClick && cardFoundationClubs[0] != null)
		{
			buffer = new Card[52];		
			buffer[0] = cardFoundationClubs[clubsPointer - 1];
			fromClubs = true;
			isFirstClick = false;
			foundationClubs.setStyle("-fx-background-image: url('" + cardFoundationClubs[clubsPointer - 1] + ".jpg'); -fx-border-color: #ffee00; -fx-border-width: 5; -fx-background-size: 100px 150px; -fx-opacity: 1");
			return;
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
			
			Main.score +=10;
			clubsPointer++;
			buffer = null;
			fromClubs = false;
			fromWaste = false;
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
			
			Main.score +=10;
			clubsPointer++;
			fromClubs = false;
			fromWaste = false;
			buffer = null;
			isFirstClick = true;
			refreshScene();
			return;
		}
		fromClubs = false;
		fromWaste = false;
		buffer = null;
		isFirstClick = true;
		refreshScene();
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
			fromWaste = false;
			isFirstClick = true;
			fromHearts = false;
			fromSpades = false;
			fromDiamonds = false;
			fromClubs = false;
		}
		refreshScene();
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
				Main.score -=15;
			}
			else if (fromSpades)
			{
				spadesPointer--;
				cardFoundationSpades[spadesPointer] = null;
				Main.score -=15;
			}
			else if (fromDiamonds)
			{
				diamondsPointer--;
				cardFoundationDiamonds[diamondsPointer] = null;
				Main.score -=15;
			}
			else if (fromClubs)
			{
				clubsPointer--;
				cardFoundationClubs[clubsPointer] = null;
				Main.score -=15;
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
		
		buffer = null;
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
					Main.score -=15;
				}
				else if (fromSpades)
				{
					spadesPointer--;
					cardFoundationSpades[spadesPointer] = null;
					Main.score -=15;
				}
				else if (fromDiamonds)
				{
					diamondsPointer--;
					cardFoundationDiamonds[diamondsPointer] = null;
					Main.score -=15;
				}
				else if (fromClubs)
				{
					clubsPointer--;
					cardFoundationClubs[clubsPointer] = null;
					Main.score -=15;
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
			buffer = null;
			fromWaste = false;
			fromHearts = false;
			fromSpades = false;
			fromDiamonds = false;
			fromClubs = false;
			originTableu = null;
			isFirstClick = true;
			refreshScene();
		}
	}
	
	private void refreshScene()
	{
		updateTurnedUpCards();
		
		Main.score = (Main.score < 0) ? 0 : Main.score;
		
		scoreboard.setText("" + Main.score);
		name.setText(Main.player);
		
		if(isTableauComplete())
		{
			button.setText("Solve");
		}
		
		if(cardFoundationHearts[0] != null)
		{
			foundationHearts.setStyle("-fx-background-image: url('" + cardFoundationHearts[heartsPointer - 1] + ".jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1");
			foundationHearts.setCursor(Cursor.HAND);
		}
		else
		{
			foundationHearts.setStyle("-fx-background-image: url('AH.jpg'); -fx-background-size: 100px 150px; -fx-opacity: 0.10");
			foundationHearts.setCursor(Cursor.DEFAULT);
		}
		
		if(cardFoundationSpades[0] != null)
		{
			foundationSpades.setStyle("-fx-background-image: url('" + cardFoundationSpades[spadesPointer - 1] + ".jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1");
			foundationSpades.setCursor(Cursor.HAND);
		}
		else
		{
			foundationSpades.setStyle("-fx-background-image: url('AS.jpg'); -fx-background-size: 100px 150px; -fx-opacity: 0.10");
			foundationSpades.setCursor(Cursor.DEFAULT);
		}
		
		if(cardFoundationDiamonds[0] != null)
		{
			foundationDiamonds.setStyle("-fx-background-image: url('" + cardFoundationDiamonds[diamondsPointer - 1] + ".jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1");
			foundationDiamonds.setCursor(Cursor.HAND);
		}
		else
		{
			foundationDiamonds.setStyle("-fx-background-image: url('AD.jpg'); -fx-background-size: 100px 150px; -fx-opacity: 0.10");
			foundationDiamonds.setCursor(Cursor.DEFAULT);
		}
		
		if(cardFoundationClubs[0] != null)
		{
			foundationClubs.setStyle("-fx-background-image: url('" + cardFoundationClubs[clubsPointer - 1] + ".jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1");
			foundationClubs.setCursor(Cursor.HAND);
		}
		else
		{
			foundationClubs.setStyle("-fx-background-image: url('AC.jpg'); -fx-background-size: 100px 150px; -fx-opacity: 0.10");
			foundationClubs.setCursor(Cursor.DEFAULT);
		}

		int pointer = (wastePointer - Main.mode < 0)? 0 : wastePointer - Main.mode;
		waste.getChildren().clear();
		int translate = 0;
		waste.setCursor(Cursor.DEFAULT);
		while (pointer < wastePointer)
		{
			if(cardWaste[pointer] != null)
			{
				Pane card = new Pane();
				card.setMinSize(100,150);
				card.setPrefSize(100,150);
				card.setMaxSize(100,150);
				card.setStyle("-fx-background-image: url('" + cardWaste[pointer] + ".jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1; -fx-background-repeat: no-repeat;");
				int ptr = pointer;
				if(pointer == wastePointer - 1)
				{
					//card.setOnMouseClicked(event -> clickedOnWaste());
					card.setOnMouseClicked(new EventHandler<MouseEvent>(){
	                    public void handle(MouseEvent event) {
	                    	clickedOnWaste();
	                    	card.setStyle("-fx-background-image: url('" + cardWaste[ptr] + ".jpg'); -fx-border-color: #ffee00; -fx-border-width: 5; -fx-border-insets: 0.5,0.5,0,0; -fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat;");
	                    }
					});
					card.setCursor(Cursor.HAND);
				}
				else
				{
					card.setCursor(Cursor.DEFAULT);
				}
				StackPane.setAlignment(card, Pos.CENTER_LEFT);
				card.setTranslateX(translate);
				waste.getChildren().add(card);
				translate += 30;
			}
			pointer++;
		}
		
		if (cardPile[0] != null)
		{
			pile.setStyle("-fx-background-image: url('/blue.jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1");
		}
		else
		{
			pile.setStyle("-fx-background-image: url('gray.jpg'); -fx-background-size: 100px 150px; -fx-opacity: 0.50");
		}
		pile.setCursor(Cursor.HAND);
		
		StackPane[] tableu = {tableu1, tableu2, tableu3, tableu4, tableu5, tableu6, tableu7};
		
		for(int j = 0; j < 7; j++)
		{
			tableu[j].getChildren().clear();
			
			if(cardTableu[j][0] == null)
			{
				Pane card = new Pane();
				card.setMinSize(100,150);
				card.setPrefSize(100,150);
				card.setMaxSize(100,150);
				card.setStyle("-fx-background-color: black; -fx-background-size: 100px 150px; -fx-background-repeat: no-repeat; -fx-opacity: 0.50");
				int row = j;
				card.setOnMouseClicked(event -> clickedOnTableu(row));
				card.setCursor(Cursor.DEFAULT);
				StackPane.setAlignment(card, Pos.TOP_CENTER);
				tableu[j].getChildren().add(card);
				continue;
			}
			
			int i = 0;
			double y = 0;
			while(cardTableu[j][i] != null)
			{
				
				Pane card = new Pane();
				card.setMinSize(100,150);
				card.setPrefSize(100,150);
				card.setMaxSize(100,150);
				if(cardTableu[j][i].isTurnedUp())
				{
					card.setStyle("-fx-background-image: url('" + cardTableu[j][i] + ".jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1; -fx-background-repeat: no-repeat;");
					int row = j;
					int column = i;
					card.setOnMouseClicked(new EventHandler<MouseEvent>(){
	                    public void handle(MouseEvent event) {
	                        clickedOnTableu(row, column);
	            			card.setMaxSize(100,550);
	                    	card.setStyle("-fx-background-image: url('" + cardTableu[row][column] + ".jpg'); -fx-border-color: #ff00a2; -fx-border-width: 3;-fx-background-color: linear-gradient(from 30% 30% to 23% 30%, repeat,  #ff008840 62%, #ff0000 88%); -fx-background-size: 100%; -fx-opacity: 1; -fx-background-repeat: no-repeat;");
	                    }
					});
					card.setCursor(Cursor.HAND);
				}
				else
				{
					card.setStyle("-fx-background-image: url('/blue.jpg'); -fx-background-size: 100px 150px; -fx-opacity: 1; -fx-background-repeat: no-repeat;");
					card.setCursor(Cursor.DEFAULT);
				}
				StackPane.setAlignment(card, Pos.TOP_CENTER);
				card.setTranslateY(y);
				
				y = y + 20;
				
				tableu[j].getChildren().add(card);
				i++;
			}
		}
		
		printInConsole();
		
		if (isGameComplete())
		{
			finishGame();
		}
	}
	
	private void printInConsole()
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
	
	private boolean isGameComplete()
	{
		if (cardFoundationHearts[12] != null && cardFoundationSpades[12] != null && cardFoundationDiamonds[12] != null && cardFoundationClubs[12] != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void finishGame()
	{
		main.startGameComplete();
	}
	
	private boolean isTableauComplete()
	{
		boolean yes = true;
		
		for(int j = 0; j < 52; j++)
		{
			for (int i = 0; i < 7; i++)
			{
				if (cardTableu[i][j] != null && !cardTableu[i][j].isTurnedUp())
				{
					yes = false;
				}
			}
		}
		return yes && cardWaste[0] == null && cardPile[0] == null;
	}
	
	@FXML
	public void endGame()
	{
		if(isTableauComplete())
		{
			solve();
		}
		else
		{
			main.startGameNotComplete();
		}
	}
	
	private void solve()
	{
		for(int j = 0; j < 52; j++)
		{
			for (int i = 0; i < 7; i++)
			{
				if(cardTableu[i][j] != null)
				{
					cardTableu[i][j] = null;
					Main.score += 10;
				}
			}
		}
		
		cardFoundationHearts[12] = new Card(13,'H', true);
		cardFoundationSpades[12] = new Card(13, 'S', true);
		cardFoundationDiamonds[12] = new Card(13, 'D', true);
		cardFoundationClubs[12] = new Card(13, 'C', true);
		
		heartsPointer = 13;
		spadesPointer = 13;
		diamondsPointer = 13;
		clubsPointer = 13;
		
		refreshScene();
	}
}
