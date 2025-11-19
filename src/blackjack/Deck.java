package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<Card>();
    }

    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            //Go through all the suits
            for(Suit suit : Suit.values()){
                //Go through all the ranks
                for(Rank rank : Rank.values()){
                    //add a new card containing each iterations suit and rank
                    deck.add(new Card(suit, rank));
                }
            }
        }

    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    public void addCard(Card card){
        deck.add(card);
    }


    public Card takeCard(){

        //Take a copy of the first card from the deck
        Card cardToTake = new Card(deck.get(0));
        //Remove the card from the deck
        deck.remove(0);
        //Give the card back
        return cardToTake;

    }

    public String toString(){
        //A string to hold everything we're going to return
        String output = "";

        //for each Card "card" in the deck
        for(Card card: deck){
            //add the card and the escape character for a new line
            output += card;
            output += "\n";
        }
        return output;
    }

    //Shuffle the deck
    public void shuffle(){
        Collections.shuffle(deck, new Random());
    }
    // for checking the deck whther it has any cards( for hitting method to person)
    public boolean hasCards(){
        if (deck.size() > 0){
            return true;
        }
        else {
            return false;
        }
    }
    /* the ability for getting empty for all the obejcts from
    this class

     */
    public void emptyDeck(){
        deck.clear();
    }
    // cards are passed as parameters which will be added back to our main deck from discarded pile
    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }
    /**
     * Take all the cards from a discarded deck and place them in this deck, shuffled.
     * Clear the old deck
     * the parameter is the discard - the deck we're getting the cards from
     */
    public void reloadFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }
    public int cardsLeft(){
        return deck.size();
    }

}