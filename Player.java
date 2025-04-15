/*Kayla Cheng
 *khc2144
 *Player.java
 */
 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(double initialB){		
	    // create a player here
        hand = new ArrayList<>(); // Initialize the player's hand
        bankroll = initialB;
        bet = 0;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
        }
		
        public void bets(double amt){
            // player makes a bet
            if (amt >= 1 && amt <= 5 && amt < bankroll){
                bankroll = bankroll - amt;
                bet = amt;
            }else{
                System.out.println("Bet must be between 1 and 5 tokens, and less than funds in bankroll.");
            }
            

        }

        public void winnings(double odds){
            //	adjust bankroll if player wins
            bankroll += bet * odds;
            bet = 0; // reset bet after payout
            
        }

        public double getBankroll(){
            // return current balance of bankroll
            return bankroll;
        }

        public ArrayList<Card> getHand(){
            //return player's hand
            return new ArrayList<>(hand);
        }

        public void clearHand(){
            //clear player's hand for a new round
            hand.clear();
        }
}


