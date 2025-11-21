package blackjack;

/**
 * Contains the suits of a Card, Names
 */
public enum Suit {
    CLUB("Clubs"),
    DIAMOND("Diamonds"),
    HEART("Hearts"),
    SPADE("Spades");

    private final String suitName;

    Suit(String suitName) {
        this.suitName = suitName;
    }

    @Override
    public String toString(){
        return suitName;
    }
}