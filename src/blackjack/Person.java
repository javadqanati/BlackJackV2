package blackjack;

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
    public void setHand(Hand hand){
        this.hand = hand;
    }
    public String getName(){
        return this.name;
    }

    /**
     * Prints a formatted version of the Person's hand
     */
    public void printHand(){
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculatedValue());
    }

    public boolean hasBlackjack(){
        return this.hand.calculatedValue() == 21; // boolean methods do not need if-else, this is more professional
    }
    
    public void hit(Deck deck,Deck discard){
        //if the deck the main deck is out of crads
        if(!deck.hasCards()){
            deck.reloadFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        this.printHand();

    }


}
