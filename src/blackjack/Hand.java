package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) { // polymorphic method
        this.cards.addAll(cards); 
    }

    public List<Card> getCards() {
        return List.copyOf(cards);  //safe to copy
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int calculatedValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            value += card.getValue();
            if (card.getRank().isAce()) {
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public List<Card> discardAll() {
        List<Card> discards = new ArrayList<>(cards);
        cards.clear();
        return discards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(" - ");
        }
        return sb.toString();
    }
}
