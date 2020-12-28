package application;

public class Card {
	
	private int value; // 1 - Ace; 2 to 10; 11 - Jack; 12 - Queen; 13 - King
	private char suit; // C - Clubs; H - Hearts; D - Diamonds; S - Spades
	private char color; // B - Black; R - Red
	
	public Card(int value, char suit)
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
}
