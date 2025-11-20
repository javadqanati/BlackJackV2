package utils;

import blackjack.Game;
import blackjack.Person;

public class RoundManager {

    private int wins, losses, pushes;
    private final Game game;

    public RoundManager(Game game) {
        this.game = game;
    }

    public void startRound() {
        UI.startRound();
        handleCleanupIfNotFirstRound();
        ensureDeckHasCards();

        dealStartingHands();
        printInitialHands();

        if (handleOpeningBlackjack()) return;

        handlePlayerTurn();
        handleDealerTurn();
        evaluateOutcome();

        startRound(); // recursive loop
    }

    // ============================
    //  PHASE 1 — ROUND CLEANUP
    // ============================

    private void handleCleanupIfNotFirstRound() {
        if (wins > 0 || losses > 0 || pushes > 0) {
            System.out.println("\nStarting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);

            game.getDiscarded().addCards(game.getDealer().getHand().discardAll());
            game.getDiscarded().addCards(game.getPlayer().getHand().discardAll());
        }
    }

    // ============================
    //  PHASE 2 — DECK PREPARATION
    // ============================

    private void ensureDeckHasCards() {
        if (game.getDeck().cardsLeft() < 4) {
            game.getDeck().reloadFromDiscard(game.getDiscarded());
        }
    }

    // ============================
    //  PHASE 3 — DEAL CARDS
    // ============================

    private void dealStartingHands() {
        for (Person p : getParticipants()) {
            dealCardTo(p);
            dealCardTo(p);
        }
    }

    private Person[] getParticipants() {
        return new Person[] { game.getDealer(), game.getPlayer() };
    }

    private void dealCardTo(Person person) {
        person.getHand().addCard(game.getDeck().takeCard());
    }

    // ============================
    //  PHASE 4 — PRINT INITIAL HANDS
    // ============================

    private void printInitialHands() {
        game.getDealer().printFirstHand();
        UI.printHand(game.getPlayer());
    }

    // ============================
    //  PHASE 5 — OPENING BLACKJACK
    // ============================

    private boolean handleOpeningBlackjack() {
        if (game.getDealer().hasBlackjack()) {
            UI.printHand(game.getDealer());

            if (game.getPlayer().hasBlackjack()) {
                System.out.println("You both have 21 - Push.");
                pushes++;
            } else {
                System.out.println("Dealer has BlackJack. You lose.");
                losses++;
            }
            startRound();
            return true;
        }

        if (game.getPlayer().hasBlackjack()) {
            System.out.println("You have Blackjack! You win!");
            wins++;
            startRound();
            return true;
        }

        return false; // continue round
    }

    // ============================
    //  PHASE 6 — PLAYER TURN
    // ============================

    private void handlePlayerTurn() {
        game.getPlayer().makeDecision(game.getDeck(), game.getDiscarded());

        if (game.getPlayer().getHand().calculatedValue() > 21) {
            System.out.println("You have gone over 21.");
            losses++;
            startRound();
        }
    }

    // ============================
    //  PHASE 7 — DEALER TURN
    // ============================

    private void handleDealerTurn() {
        UI.printHand(game.getDealer());
        while (game.getDealer().getHand().calculatedValue() < 17) {
            game.getDealer().hit(game.getDeck(), game.getDiscarded());
        }
    }

    // ============================
    //  PHASE 8 — FINAL EVALUATION
    // ============================

    private void evaluateOutcome() {
        int dealerValue = game.getDealer().getHand().calculatedValue();
        int playerValue = game.getPlayer().getHand().calculatedValue();

        if (dealerValue > 21) {
            System.out.println("Dealer busts");
            wins++;
        } else if (dealerValue > playerValue) {
            System.out.println("You lose.");
            losses++;
        } else if (playerValue > dealerValue) {
            System.out.println("You win.");
            wins++;
        } else {
            System.out.println("Push.");
            pushes++;
        }
    }
}
