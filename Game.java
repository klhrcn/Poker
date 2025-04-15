/*Kayla Cheng
 *khc2144
 *Game.java
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

public class Game {
	
	private Player p;
	private Deck cards;
	private int y = 0;
    private int z = 0;
    private ArrayList<Card> testHandList = new ArrayList<>();
	
	public Game(String[] testHand){
		// This constructor is to help test my code.
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush


        //initialize
        Player p = new Player (50);

        //use provided test hand
        for (String testCard: testHand){
                    
            char x = testCard.charAt(0);
            int rank = Integer.parseInt(testCard.substring(1));
            int suit = 0;

           //convert suit to numeric value 
            if (x == 'd') {
                suit = 1; // Diamonds
            } else if (x == 'h') {
                suit = 2; // Hearts
            } else if (x == 's') {
                suit = 3; // Spades
            } else if (x == 'c') {
                suit = 4; // Clubs
            } 
                   

            testHandList.add(new Card(suit, rank));

        }

        for(Card tCard: testHandList){
            p.addCard(tCard);
        }
            
        String resultTest = checkHand(p.getHand());
        System.out.println("testhand = {" + testHandList + "} = "+resultTest);

             		
	}
	
    //Constructor
	public Game(){
		cards = new Deck(); // initialize new deck
        cards.shuffle(); // shuffle deck
        p = new Player(50); // player starts with bankroll of 50
		
	}
	
	public void play(){
        if (testHandList.isEmpty()){
            Scanner scanner = new Scanner(System.in);


            System.out.println("Current bankroll: " + p.getBankroll());
            System.out.println("Enter a bet between 1 to 5 tokens: ");
            double bet = scanner.nextDouble();
            scanner.nextLine();

            if (bet < 1 || bet > 5 || bet > p.getBankroll()){
                System.out.println("Invalid bet. Enter a bet between 1 to 5 tokens and less than your bankroll");
                // continue;
            }

            p.bets(bet);

            //deal initial hand
            for (int i = 0; i < 5; i++){
                p.addCard(cards.deal());
            }
            System.out.println("Your hand: " + p.getHand());

            //let player replace cards
            String input = "";
            while (!input.equals("done")){
                System.out.println("Enter the position of the cards (0-4) to replace or press done to keep them all or when done: ");
                input = scanner.nextLine();

                if (!input.equals("done")){
                    String[] ind = input.split(",");
                    
                    for (String index: ind){ 
                        String n = "";
                        for (int i = 0; i < index.length(); i++){
                            char c = index.charAt(i);
                            if (c != ' '){
                                n = n + c;
                            }
                        }

                        int cInd = Integer.parseInt(n);
                        if(cInd >= 0 && cInd < 5){
                            p.removeCard(p.getHand().get(cInd));
                            p.addCard(cards.deal());
                        }else{
                            System.out.println("Your index is not valid");
                        }
                    }
                    System.out.println("Your updated hand: " + p.getHand());
                }

            }
            //evaluate hand
            String result = checkHand(p.getHand());
            double payoutMultiplier = getPayoutMultiplier(result);
            System.out.println("Hand result: " + result);

            //calculate how much the player has won
            p.winnings(payoutMultiplier);
            System.out.println("Updated bankroll: " + p.getBankroll());

            //clear hand for next round
            p.clearHand();



        
            System.out.println("Your game is over. Final bankroll is " + p.getBankroll());
        }
		
       
	}
	
	public String checkHand(ArrayList<Card> hand){
        //sorting the hand and then evaluating what each hand is
		Collections.sort(hand, new rankSort()); 

        boolean isFlush = isFlush(hand);
        boolean isStraight = isStraight(hand);
        
        if (isFlush && isStraight){
            y = hand.get(1).getRank();
            z = hand.get(0).getRank();
            if (y == 10 && z != 9){
                return "Royal Flush";
            }
            return "Straight Flush";
            
        } else if (isFourOfAKind(hand)){
            return "Four of a Kind";
        } else if (isFullHouse(hand)){
            return "Full House";
        } else if (isFlush){
            return "Flush";
        } else if (isStraight){
            return "Straight";
        } else if (isThreeOfAKind(hand)){
            return "Three of a Kind";
        } else if (isTwoPair(hand)){
            return "Two Pair";
        } else if (isOnePair(hand)){
            return "One Pair";
        } else {
            return "No Pair";
        }
		
	}
	
    //evaluate payout
    private double getPayoutMultiplier(String result){
        if (result.equals("Royal Flush")) {
            return 250;
        } else if (result.equals("Straight Flush")) {
            return 50;
        } else if (result.equals("Four of a Kind")) {
            return 25;
        } else if (result.equals("Full House")) {
            return 6;
        } else if (result.equals("Flush")) {
            return 5;
        } else if (result.equals("Straight")) {
            return 4;
        } else if (result.equals("Three of a Kind")) {
            return 3;
        } else if (result.equals("Two Pairs")) {
            return 2;
        } else if (result.equals("One Pair")) {
            return 1;
        } else {
            return 0;
        } 
    }

    //evaluate hand types
    //evaluate for flush
    private boolean isFlush(ArrayList<Card> hand){
        int suit = hand.get(0).getSuit();
        for (Card c : hand){
            if (c.getSuit() != suit){
                return false;
            }
        }
        return true;
    }

    //evaluate for straight
    private boolean isStraight(ArrayList<Card> hand){
        if (hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10 && hand.get(2).getRank() == 11 && hand.get(3).getRank()==12 && hand.get(4).getRank()==13){
            return true;
        }

        for (int i = 0; i < hand.size() - 1; i++){
            if (hand.get(i+1).getRank() != hand.get(i).getRank() + 1){
                return false;
            }
        }
        return true;
    }

    //evaluate for four of a kind
    private boolean isFourOfAKind(ArrayList<Card> hand){
        for (int i=0; i<=13; i++){
            int count = 0;
            for (Card c: hand){
                if(c.getRank() == i){
                    count++;
                }
            }
            if (count == 4){
                return true;
            }
        }
        return false;
    }

    //evaluate for full house
    private boolean isFullHouse(ArrayList<Card> hand){
        boolean hasThreeOfAKind = false;
        boolean hasOnePair = false;

        for (int i = 0; i <= 13; i++){
            int count = 0;
            for (Card c: hand){
                if(c.getRank() == i){
                    count++;
                }
            }
            if (count == 3 ){
                hasThreeOfAKind = true;
            }else if (count == 2){
                hasOnePair = true;
            }
        }
        return hasThreeOfAKind && hasOnePair;
    }

    //evaluate for three of a kind
    private boolean isThreeOfAKind(ArrayList<Card> hand){
        boolean threeOfAKind = false; 

        for (int i = 1; i<=13; i++){
            int count = 0;
            for (Card c: hand){
                if (c.getRank() == i){
                    count++;
                }
            }
            if (count == 3){
                threeOfAKind = true;
            }
        }

        return threeOfAKind;
    }

    //evaluate two pair
    private boolean isTwoPair(ArrayList<Card> hand){
        boolean twoPair = false;
        int countTwoPair = 0;

        for (int i=0; i <= 13; i++){
            int countPair = 0;
            for (Card c:hand){
                if(c.getRank() == i){
                    countPair++;
                }
            }
            if (countPair == 2){
                countTwoPair++;
            }
            if (countTwoPair == 2){
                twoPair = true;
            }
        }
        return twoPair;
    }

    //evaluate one pair
    private boolean isOnePair(ArrayList<Card> hand){
        boolean onePair = false;

        for (int i = 1; i <= 13; i++){
            int count = 0;
            for (Card c: hand){
                if (c.getRank() == i){
                    count++;
                }
            }
            if (count == 2){
                onePair = true;
            }
        }

        return onePair;
    }

}

class rankSort implements Comparator<Card>{
    public int compare(Card one, Card two){
        return one.compareTo(two);
    }
}
