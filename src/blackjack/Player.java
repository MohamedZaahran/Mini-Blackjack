package blackjack;

class Player {
    private final String name;
    private int score; //The values of all the cards in the player’s hand at the beginning and after each hit.
    private Card cardObj[] = new Card[11]; //Array of 11 Card objects
    private boolean black_jack; //Indicates whether he got a blackjack or not.
    private boolean busted; //Indicates whether he already lost or not.
    private int counter = 0; //Counter to know how many cards each player has.

    Player(String name) { //Constructor
        this.score = 0;
        this.busted = false;
        this.black_jack = false;
        this.name = name;
    }

    void Add_Card(Card co, int s) { //Function which add cards to the cardObj[11] of each player.
        cardObj[counter] = co;
        score += s;
        counter++;
    }
    
    //Getters and Setters
    public boolean getBlack_jack() {
        return black_jack;
    }

    public void setBlack_jack(boolean black_jack) {
        this.black_jack = black_jack;
    }

    public boolean getBusted() {
        return busted;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    
    public Card[] getCardObj() {
        return cardObj;
    }
}