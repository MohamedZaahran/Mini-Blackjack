package blackjack;

class Card {
    private final int suit, rank, value; //Note that all the attributes should be unchanged once they get their values and should follow the rules of the encapsulation concept.
    { /*
    suit = 0 Clubs (Treffle)
    suit = 1 Diamonds (Karuuh)
    suit = 2 Hearts
    suit = 3 Spades (Beg)
    ---------------------------------------
    rank = 0 for Ace card
    rank = 1 for 2 card
    rank = 2 for 3 card
    rank = 3 for 4 card
    rank = 4 for 5 card
    rank = 5 for 6 card
    rank = 6 for 7 card
    rank = 7 for 8 card
    rank = 8 for 9 card
    rank = 9 for 10 card
    rank = 10 for Jack card
    rank = 11 for Queen card
    rank = 12 for King card
    ---------------------------------------
    value = 1 for Ace card
    value = 2 for 2 card
    value = 3 for 3 card
    value = 4 for 4 card
    value = 5 for 5 card
    value = 6 for 6 card
    value = 7 for 7 card
    value = 8 for 8 card
    value = 9 for 9 card
    value = 10 for 10 card
    value = 10 for Jack card
    value = 10 for Queen card
    value = 10 for King card
    */
    }

    Card(int suit, int rank, int value) { //Constructor
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    Card(Card card) { //Copy Constructor for GUI
        this.suit = card.suit;
        this.rank = card.rank;
        this.value = card.value;
    }
    
    //Getters for Suit, Rank and Value
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
}
