package application.model;

public class Card {
	
	private int value; // 1 - Ace; 2 to 10; 11 - Jack; 12 - Queen; 13 - King
	private char suit; // C - Clubs; H - Hearts; D - Diamonds; S - Spades
	private char color; // B - Black; R - Red
	private boolean isTurnedUp;
	
	public Card(int value, char suit, boolean isTurnedUp)
	{
		this.value = value;
		this.suit = suit;
		
		if(suit == 'D' || suit == 'H')
		{
			this.color = 'R';
		}
		if(suit == 'S' || suit == 'C')
		{
			this.color = 'B';
		}
		
		this.isTurnedUp = isTurnedUp;
	}
	
	public Card(Card card)
	{
		this.value = card.getValue();
		this.suit = card.getSuit();
		this.color = card.getColor();
		this.isTurnedUp = card.isTurnedUp();
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public char getSuit()
	{
		return this.suit;
	}
	
	public char getColor()
	{
		return this.color;
	}
	
	public boolean isTurnedUp()
	{
		return this.isTurnedUp;
	}
	
	public void turnCardUp(boolean isTurnedUp)
	{
		this.isTurnedUp = isTurnedUp;
	}
	
	public int cardDifference(Card card) //the absolute value difference of two cards
	{
		return Math.abs(this.value - card.getValue());
		
		//para to doon sa patong-rule ng mga cards sa Solitaire, dapat magkakasunod sila.
	}
	
	public boolean isGreater(Card card) //determines if this card's value is greater than the other card's.
	{
		return this.value > card.getValue();
	}
	
	public boolean isSameSuit(Card card) //determines if the two cards belong to the same suit
	{
		return this.suit == card.getSuit();
	}
	
	public boolean isSameColor(Card card) //determines if the two cards have the same color
	{
		return this.color == card.getColor();
	}
	
	public String toString() 
	{
		/*
		 * /for file fetch purposes. <value><suit>
		 * ex. 10C-> A club with value of 10, 
		 * tingnan niyo sa data folder andoon yung mga cards
		 */

		String[] values = {"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10","J", "Q","K"};
		
		return "" + values[this.value] + this.suit;
	}
}
