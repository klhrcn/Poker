/*Kayla Cheng
 *khc2144
 *Deck.java
 */
 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

public class Deck {
	
    private final int SHUFFLE = 1500; // large integer to shuffle 
	private Card[] cards = new Card[52]; // array to hold the cards
	private int top; // the index of the top of the deck
    private Random r = new Random();

	
	public Deck(){
		// make a 52 card deck here
        top = 0;
        int index = 0; // track current index in array
        for (int suit = 1; suit <= 4; suit++){
            for (int rank = 1; rank <= 13; rank++){//loop through the ranks
                cards[index++] = new Card(suit, rank); //add card
            }
        }
	}
	
	public void shuffle(){
		// shuffle the deck here
        for (int i=0; i<= SHUFFLE; i++){
            int j = r.nextInt(52);
            int k = r.nextInt(52);
            //swap cards at positions one and two
            Card temp = cards[j];
            cards[j] = cards[k];
            cards[k] = temp;
        }
        top = 0; // reset after shuffling
	}
	
	public Card deal(){
		// deal the top card in the deck
        return cards[top++];
	}
	
	public int remain(){
        //return number of remaining cards in the Deck
        return 52 - top;
    }

}
