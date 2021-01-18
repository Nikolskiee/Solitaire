package application.model;
import java.util.*;
public class Deck {
	
	private int pointer = 51;
	private Card[] deck; 
	
	private char[] suit = {'H', 'C', 'D', 'S'};
	
	public Deck() //creates an ordered deck
	{
		this.deck = new Card[52];
		
		int k = 0;
		for (int i = 0; i < suit.length; i++)
		{
			for (int j = 1; j <= 13; j++)
			{
				deck[k++] = new Card(j, suit[i], false);
			}
		}
		this.shuffle();
	}
	
	public void shuffle() //shuffles the deck
	{
		Collections.shuffle(Arrays.asList(this.deck));
	}
	
	public Card getCard(int i) //access a card
	{
		return deck[i];
	}
	
	public Card popCard() //get a card and removes it from the deck
	{
		Card temp = new Card(deck[pointer]);
		deck[pointer] = null;
		pointer--;
		return temp;
		
	}
	
	public int getDeckSize() //number of cards in this deck;
	{
		return pointer + 1;
	}

}
