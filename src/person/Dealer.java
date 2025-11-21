package person;

import blackjack.Deck;
import ui.UI;

public class Dealer extends Person {

    public Dealer(String name) {
        super(name);
    }

    // Show only first card (Blackjack rule)
    public void printFirstHand() {
        System.out.println("The dealer's hand looks like this:");
        System.out.println(getHand().getCard(0));
        System.out.println("The second card is face down.");
    }

    /**
     * Dealer plays according to blackjack rules:
     * - Hit until reaching 17 or higher
     * - Soft/hard 17 handling can be extended later
     */
    public void playTurn(Deck deck, Deck discard) {
        System.out.println("\nDealer reveals the hidden card:");
        UI.printHand(this);

        while (getHand().calculatedValue() < 17) {
            System.out.println("Dealer hits...");
            this.hit(deck, discard);
            UI.printHand(this);
        }

        int total = getHand().calculatedValue();

        if (total > 21) {
            System.out.println("Dealer busts!");
        } else {
            System.out.println("Dealer stands with " + total + ".");
        }
    }
}
