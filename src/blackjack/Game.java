package blackjack;
import java.util.Random;
import java.util.Scanner;

class Game {
    Player players[] = new Player[4]; //Array of 4 players (Dealer is the last player [3])
    Card cards[] = new Card[53]; //Array of 52 cards (card deck)
    static Scanner input = new Scanner(System.in);
        
    static void Card_Generator(Card cards[]) { //Function which generates the card deck and put it in the cards[52]
        int value, rank, suit;

        for(int i = 0; i < 52; i++) {
            if(i < 13) {
                suit = 0; //Treffle
                rank = i;

                if(i >= 10) {
                    value = 10;
                }

                else {
                    value = i + 1;
                }
            }

            else if(i >= 13 && i < 26) {
                suit = 1; //Karuuh
                rank = i - 13;

                if(i - 13 >= 10) {
                    value = 10;
                }

                else {
                    value = (i - 13) + 1;
                }
            }

            else if(i >= 26 && i < 39) {
                suit = 2; //Heart
                rank = i - 26;

                if(i - 26 >= 10) {
                    value = 10;
                }

                else {
                    value = (i - 26) + 1;
                }
            }

            else {
                suit = 3; //Beg
                rank = i - 39;

                if(i - 39 >= 10) {
                    value = 10;
                }

                else {
                    value = (i - 39) + 1;
                }
            }
            cards[i] = new Card(suit, rank, value);
        }
    }

    static Card Random_Generator(Card cards[]) { //Function which generate a random card
        Random rand = new Random(); //Creates an object (rand) from the Random class
        Card returnedCard;

        while(true) {
            int randomChoice = rand.nextInt(52); //Generates a random number between [0, 51]
            if(cards[randomChoice] == null) //If the index (randomChoice) of the card deck is null, loop again
                continue;

            else {
                returnedCard = cards[randomChoice];
                cards[randomChoice] = null; //Sets the random returned card to null to make sure that it isn't used again.
                break;
            }
        }
        return returnedCard;
    }

    static void Set_Info(Card cards[], Player players[]) {
        byte count = 0; //Counter to know the number of random cards drawn to each player which must be exactly 2 cards, byte data type because it ranges from 0 to 127.

        for(int i = 0; i < 3; i++) {
            count = 0;
            String c = "st"; //String to type the st for (1st), nd for (2nd), rd for (3rd) when asking for player names input.
            if(i == 1)
                c = "nd";
            else if(i == 2)
                c = "rd";

            System.out.printf("Please enter the %d%s player's name: ", i + 1, c);
            String name = input.next();
            players[i] = new Player(name);

            while(count < 2) {
                Card randomCard = Random_Generator(cards);
                players[i].Add_Card(randomCard, randomCard.getValue());
                count++;
            }
        }

        count = 0;
        players[3] = new Player("Dealer");

        while(count < 2) { //Loop to draw 2 random cards to the dealer
            Card randomCard = Random_Generator(cards); //For dealer
            players[3].Add_Card(randomCard, randomCard.getValue());
            count++;
        }
    }
    
    static int Update_Score(Player players[]) {
        int Max_Score = 0; //Game Maximum Score

        for(int i = 0; i < 3; i++) {
            if(i == 0 && players[i].getScore() <= 21)
                Max_Score = players[i].getScore(); //If it is the first player and he isn't busted (players[i].getScore() <= 21), initalize the maximum score with his score
            if(players[i].getScore() > Max_Score && players[i].getScore() <= 21)
                Max_Score = players[i].getScore(); //If a player's score is greater than the maximum score, then initalize the maximum score with his score
        }
        return Max_Score;
    }
}