package application.model;
import java.util.*;
public class Deck {
	
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
				deck[k++] = new Card(j, suit[i]);
			}
		}
		this.shuffle();
	}
	
	public void shuffle() //shuffles the deck
	{
		Collections.shuffle(Arrays.asList(this.deck));
	}
	
	public Card getCard(int i) //get a card
	{
		return deck[i];
	}
	
	public static void main (String[] args) 
	{
		//just ignore this one, triny ko lang kung nagana ba yung Deck.java and Card.java hehe
		
		Deck sol = new Deck();
		sol.shuffle();
		
		for (int i = 0; i < 52; i++)
		{
			System.out.println(sol.getCard(i));
		}
	}

}
