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

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
    public void addCards(Card card) {
        cards.add(card);
    }
    public void addCards(Collection<Card> newCards) {
        cards.addAll(newCards);
    }
    public Card takeCard() {
        return cards.remove(0); // removes and returns
    }
    public int cardsLeft() {
        return cards.size();
    }
    public boolean hasCards() {
        return !cards.isEmpty();
    }
    public void emptyDeck() {
        cards.clear();
    }
    public void reloadFromDiscard(Deck discard) {
        this.addCards(discard.cards);  // add raw list (same package, allowed)
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }
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
