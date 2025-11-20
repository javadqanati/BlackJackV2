package blackjack;

public class Card {

    private final Suit suit; // indicating variable as final, 
    private final Rank rank; // because they shouldn't change after construction

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Card(Card card){
        this.suit = card.getSuit(); 
        this.rank = card.getRank();
    }

    public int getValue(){
        return rank.getValue();
    }

    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    @Override
    public String toString(){
        return ("["+rank+" of "+ suit + "] ("+this.getValue()+")");

    }
}
