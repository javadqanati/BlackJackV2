package utils;

import blackjack.Game;
import person.Person;
import ui.UI;

public class RoundManager {

    private int wins, losses, pushes;
    private final Game game;

    public RoundManager(Game game) {
        this.game = game;
    }

    public void startRound() {
        cleanupPreviousRound();
        ensureDeckHasCards();

        dealStartingHands();
        printInitialHands();

        if (checkOpeningBlackjack()) return;

        // Hand control to PlayScreen
        UI.startRound();
    }

    private void cleanupPreviousRound() {
        if (wins > 0 || losses > 0 || pushes > 0) {
            System.out.println("\nStarting Next Round... Wins: " + wins +
                    " Losses: " + losses + " Pushes: " + pushes);

            game.getDiscarded().addCards(game.getDealer().getHand().discardAll());
            game.getDiscarded().addCards(game.getPlayer().getHand().discardAll());
        }
    }

    private void ensureDeckHasCards() {
        if (game.getDeck().cardsLeft() < 4) {
            game.getDeck().reloadFromDiscard(game.getDiscarded());
        }
    }

    private void dealStartingHands() {
        for (Person p : new Person[]{game.getDealer(), game.getPlayer()}) {
            p.getHand().addCard(game.getDeck().takeCard());
            p.getHand().addCard(game.getDeck().takeCard());
        }
    }

    private void printInitialHands() {
        game.getDealer().printFirstHand();
        UI.printHand(game.getPlayer());
    }

    private boolean checkOpeningBlackjack() {
        boolean dealerBJ = game.getDealer().hasBlackjack();
        boolean playerBJ = game.getPlayer().hasBlackjack();

        if (dealerBJ) {
            UI.printHand(game.getDealer());
            if (playerBJ) {
                System.out.println("Both have blackjack — Push.");
                pushes++;
            } else {
                System.out.println("Dealer has Blackjack. You lose.");
                losses++;
            }
            startRound();
            return true;
        }

        if (playerBJ) {
            System.out.println("You have Blackjack! You win!");
            wins++;
            startRound();
            return true;
        }

        return false; // Continue to PlayScreen
    }

    // Called when player chooses STAND in PlayScreen
    public void handleDealerTurn() {
        UI.printHand(game.getDealer());

        while (game.getDealer().getHand().calculatedValue() < 17) {
            game.getDealer().hit(game.getDeck(), game.getDiscarded());
        }
    }

    // Called from PlayScreen after dealer turn
    public void evaluateWinner() {
        int playerValue = game.getPlayer().getHand().calculatedValue();
        int dealerValue = game.getDealer().getHand().calculatedValue();

        String username = game.getPlayer().getUserName();

        if (playerValue > 21) {
            System.out.println("You busted. Dealer wins.");
            StatsManager.addLoss(username);
        } else if (dealerValue > 21) {
            System.out.println("Dealer busted! You win!");
            StatsManager.addWin(username);
        } else if (playerValue > dealerValue) {
            System.out.println("You win!");
            StatsManager.addWin(username);
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins.");
            StatsManager.addLoss(username);
        } else {
            System.out.println("Push.");
            StatsManager.addPush(username);
        }


        startRound(); // next round
    }
}
