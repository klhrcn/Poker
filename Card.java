/*Kayla Cheng
 *khc2144
 *Card.java
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		//make a card with suit s and value v
		
		suit = s;
		rank = r;

	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
		
			return this.rank - c.rank; // Compare by rank;
	}
		

	
	public String toString(){
		// use this method to easily print a Card object
		return getRankName() + " of " + getSuitName();

	}
	

	public int getRank(){
		return rank;
	}

	public int getSuit(){
		return suit;
	}



	private String getSuitName(){
		//Convert suit number to suit name

		String[] suits = {"invalid", "Diamonds", "Hearts", "Spades", "Clubs"};
		if (suit >= 1 && suit <= 4){
			return suits[suit];
		}
		return suits[0]; // for when the suit is not between 1 and 4
	}


	private String getRankName(){
		//Convert rank number to rank name
		for (int i = 1; i <= 13; i++){
			if (rank == i){
				if (i ==1){
					return "Ace";
				}else if (i == 11){
					return "Jack";
				}else if (i == 12){
					return "Queen";
				}else if (i == 13){
					return "King";
				} else {
					return String.valueOf(i); // for non-face card and special named cards
				}
			}
		}
		return "unknown"; // for invalid
	}

}
