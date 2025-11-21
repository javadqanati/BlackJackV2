package ui;

import blackjack.Game;
import person.Player;

public class PlayScreen extends Menu {

    private final Game game;

    public PlayScreen(Game game) {
        this.game = game;

        addOption("Hit", this::hit);
        addOption("Stand", this::stand);
        addOption("End Game", this::endGame);
    }

    private void hit() {
        game.getPlayer().hit(game.getDeck(), game.getDiscarded());

        int total = game.getPlayer().getHand().calculatedValue();

        if (total > 21) {
            System.out.println("You have gone over 21.");
            // Round ends — restart
            game.getRoundManager().startRound();
            return;
        }

        if (total == 21) {
            System.out.println("You hit 21!");
            // proceed to dealer turn
            game.getDealer().playTurn(game.getDeck(), game.getDiscarded());
            game.getRoundManager().evaluateWinner();
            return;
        }

        // keep playing
        showOptions();
    }

    @Override
    public void showOptions() {
        System.out.println("=== PLAY SCREEN ===");
        System.out.println("Your hand value: " + game.getPlayer().getHand().calculatedValue());
        System.out.println("Dealer shows: " + game.getDealer().getHand().getCard(0));
        super.showOptions();
    }

    private void stand() {
        System.out.println("You stand.");
        game.getDealer().playTurn(game.getDeck(), game.getDiscarded());
        game.getRoundManager().evaluateWinner();
    }


    private void endGame() {
        game.endGame();
        System.exit(0);
    }
}
