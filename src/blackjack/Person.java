package blackjack;
import utils.UI;

public abstract class Person {

    private final Hand hand; // a person's hand shouldn't be changed completely, it's not safe
    private final String name; //it's prettier and safer

    public Person(String name){
        this.hand = new Hand();
        this.name = name;
    }

    public Hand getHand(){
        return this.hand;
    }

    public String getName(){
        return this.name;
    }

    public boolean hasBlackjack(){
        return this.hand.calculatedValue() == 21; // boolean methods do not need if-else, this is more professional
    }
    
    public void hit(Deck deck,Deck discard){
        //if the deck the main deck is out of crads
        if(!deck.hasCards()){
            deck.reloadFromDiscard(discard);
        }
        Card drawn = deck.takeCard();   //get card from deck
        hand.addCard(drawn);            //add card to hand
        UI.printHitMessage(this.name);
        UI.printHand(this);
    }


}
