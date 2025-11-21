package ui;

import utils.StatsManager;
import person.Player;

public class StatsMenu extends Menu {

    private final Player player;

    public StatsMenu(Player player) {
        this.player = player;

        addOption("Show My Statistics", this::showStats);
        addOption("Back to User Menu:", () -> MenuManager.showMenu("USER"));
    }

    private void showStats() {
        var stats = StatsManager.getStats(player.getUserName());

        System.out.println("\n=== Your Statistics ===");
        System.out.println("Wins:   " + stats.getWins());
        System.out.println("Losses: " + stats.getLosses());
        System.out.println("Pushes: " + stats.getPushes());
        System.out.println();
        MenuManager.showMenu("STATS");
    }
}
