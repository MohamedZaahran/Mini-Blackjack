package blackjack;
import java.util.Scanner;

public class BlackJack {
    static Scanner input = new Scanner(System.in);
    static Game game = new Game();
    static GUI gui = new GUI();

    static void Start_Players(Game game) { //To start playing the game
        byte choose; //To know whether the user had chosen 1 or 2, byte data type because it ranges from 0 to 127.

        for(int i = 0; i < 3; i++) { //To start the game and each player chooses to hit or stand.
            if(i > 0)
                System.out.println("\nLoading next player...\n");

            while(game.players[i].getScore() < 21) {
                System.out.printf("\n%s's turn with current score %d:\n\n", game.players[i].getName(), game.players[i].getScore());
                
                while(true) { //Loop for inputting Hit or Stand
                    System.out.println("1) Hit\n2) Stand\n");
                    choose = input.nextByte();

                    if(choose == 1 || choose == 2)
                        break;

                    else
                        System.out.println("Invalid entry, please try again.\n");
                }

                if(choose == 2) {
                    System.out.printf("\n%s standed with score %d.\n\n", game.players[i].getName(), game.players[i].getScore());
                    break; //Goes to the next player if he choosed to stand.
                }

                else { //Gives the player cards if he choosed to hit.
                    while(true) {
                        choose = 0;

                        System.out.println("\nHit! Dealer is giving you a card...\n");
                        Card randomCard = Game.Random_Generator(game.cards);
                        game.players[i].Add_Card(randomCard, randomCard.getValue());
                        gui.updatePlayerHand(randomCard, i);
                        
                        System.out.printf("%s's score now is %d\n\n", game.players[i].getName(), game.players[i].getScore());

                        if(game.players[i].getScore() == 21) {
                            System.out.printf("%s has a blackjack!\n\n", game.players[i].getName());
                            game.players[i].setBlack_jack(true);
                            break;
                        }

                        else if(game.players[i].getScore() > 21) {
                            System.out.printf("\n%s is BUSTED!\n\n", game.players[i].getName());
                            game.players[i].setBusted(true);
                            break;
                        }

                        else {
                        while(true) { //Loop for inputting Hit or Stand
                            choose = 0;
                            System.out.println("1) Hit\n2) Stand\n");
                            choose = input.nextByte();
                            if(choose == 1 || choose == 2)
                                break;

                            else
                                System.out.println("Invalid entry, please try again.\n");
                        }

                        if(choose == 2) {
                            System.out.printf("\n%s standed with score %d.\n\n", game.players[i].getName(), game.players[i].getScore());
                            break; //Goes to the next player if he choosed to stand.
                        }

                        else
                            continue; //Gives the player cards if he choosed to hit.
                        }
                    }
                    if(choose == 2)
                        break;
                }
            }
        }
        BlackJack.Start_Dealer(game);
    }

    static void Check(Game game) { //To check for who won/busted/blackjacked
        byte blackjack_counter = 0; //Counter to count the number of players who got blackjack.
        byte maximum_counter = 0; //Counter to count how many players are having the maximum score.
        int index_blackjack = 0; //Used when a player got blackjack, so his name is mentioned that he won with a blackjack.
        int index_maximum = 0; //Used when a player has maximum score, so his name is mentioned that he won with that score.
        int maximum = 0; //Used to know the maximum score
        
        for(int i = 0; i < 4; i++) {
            if(game.players[i].getBusted() == false) {
                maximum = game.players[i].getScore(); //If it is the first player and he isn't busted, initalize the maximum score with his score
                break;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (game.players[i].getScore() > maximum && game.players[i].getBusted() == false) {
                maximum = game.players[i].getScore(); //If a player's score is greater than the maximum score, then initalize the maximum score with his score
            }
        }

        for(int i = 0; i < 4; i++) {
            if(game.players[i].getBlack_jack() == true) {
                blackjack_counter++;
                index_blackjack = i;
            }
            if(game.players[i].getBusted() == false && game.players[i].getScore() == maximum) {
                index_maximum = i;
                maximum_counter++;
            }
        }

        if(blackjack_counter == 0 && maximum_counter >= 2)
            System.out.printf("Game is push, %d players have the same score!\n\n", maximum_counter);

        else if(blackjack_counter >= 2)
            System.out.printf("Game is push, %d players have a blackjack!\n\n", blackjack_counter);

        else if(blackjack_counter == 1 && maximum_counter == 1)
            System.out.printf("%s won the game with a blackjack!\n\n", game.players[index_blackjack].getName());

        else if(blackjack_counter == 0 && maximum_counter == 1)
            System.out.printf("%s won the game with a score (%d)!\n\n", game.players[index_maximum].getName(), game.players[index_maximum].getScore());
    }

    static void Start_Dealer(Game game) { //For the dealer
        int count = 0; //Counter for the busted players.
        boolean check = false;

        for(int i = 0; i < 3; i++) {
            if(game.players[i].getBusted())
                count++;

            if(i == 2 && count == 3) {
                System.out.printf("Dealer won the game with a score (%d)!\n\n", game.players[3].getScore());
                check = true;
                break; //Exits the loop and end the game if all players are busted.
            }

            else if(i == 2 && count < 3) {
                if(game.players[3].getScore() <= Game.Update_Score(game.players)) { //If dealer's score is less than the maximum score of the players.
                    System.out.println("The dealer is drawing cards....\n");

                    while(true) {
                        Card randomCard = Game.Random_Generator(game.cards);
                        game.players[3].Add_Card(randomCard, randomCard.getValue());
                        gui.updateDealerHand(randomCard, game.cards);
                        System.out.println("Dealer's score now is " + game.players[3].getScore() + "\n");

                        if(game.players[3].getScore() == 21) {
                            System.out.println("Dealer has a blackjack!\n");
                            game.players[3].setBlack_jack(true);
                            break;
                        }

                        else if(game.players[3].getScore() > Game.Update_Score(game.players) && game.players[3].getScore() <= 21) {
                            System.out.printf("Dealer won the game with a score (%d)!\n\n", game.players[3].getScore());
                            check = true;
                            break;
                        }

                        else if(game.players[3].getScore() > 21) {
                            System.out.println("Dealer is BUSTED!\n");
                            game.players[3].setBusted(true);
                            break;
                        }
                    }
                }
            }
        }
        if(check == false)
            BlackJack.Check(game);
    }

    public static void main(String[] args) {
        Game.Card_Generator(game.cards);
        Game.Set_Info(game.cards, game.players);
        BlackJack.gui.runGUI(game.cards, game.players[0].getCardObj(), game.players[1].getCardObj(), game.players[2].getCardObj(), game.players[3].getCardObj());
        BlackJack.Start_Players(game);
    }
}
