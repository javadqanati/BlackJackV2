package person;
import blackjack.Card;
import blackjack.Deck;
import blackjack.Hand;
import ui.UI;

public abstract class Person {
    private final Hand hand;
    private final String type;

    public Person(String type){
        this.hand = new Hand();
        this.type = type;
    }

    public Hand getHand(){
        return this.hand;
    }
    public String getType(){
        return this.type;
    }
    public boolean hasBlackjack(){
        return this.hand.calculatedValue() == 21; // boolean methods do not need if-else, this is more professional
    }
    public void hit(Deck deck, Deck discard){
        if(!deck.hasCards()){
            deck.reloadFromDiscard(discard);
        }
        Card drawn = deck.takeCard();   //get card from deck
        hand.addCard(drawn);            //add card to hand
        UI.printHitMessage(this.type);
        UI.printHand(this);
    }
}
