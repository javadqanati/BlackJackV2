package blackjack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
    }

    public Deck(boolean makeDeck) {
        if (makeDeck) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
    }

    /**
     * Returns an unmodifiable copy of the cards for safety.
     */
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    /**
     * Adds a single card to the deck.
     */
    public void addCard(Card card) {
        cards.add(card);
    }
    

    /**
     * Adds many cards to the deck (supports List, ArrayList, any Collection).
     */
    public void addCards(Collection<Card> newCards) {
        cards.addAll(newCards);
    }

    /**
     * Removes and returns the top card of the deck.
     * No need to copy the card — Card is immutable.
     */
    public Card takeCard() {
        return cards.remove(0); // removes and returns
    }

    /**
     * Returns the number of cards left.
     */
    public int cardsLeft() {
        return cards.size();
    }

    /**
     * Whether the deck has cards left.
     */
    public boolean hasCards() {
        return !cards.isEmpty();
    }

    /**
     * Clears the deck.
     */
    public void emptyDeck() {
        cards.clear();
    }

    /**
     * Converts all discard cards into this deck, shuffles, and clears discard.
     */
    public void reloadFromDiscard(Deck discard) {
        this.addCards(discard.cards);  // add raw list (same package, allowed)
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }

    /**
     * Shuffles the deck using a fresh Random.
     */
    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }
}
