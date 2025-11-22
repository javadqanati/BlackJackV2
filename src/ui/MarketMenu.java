package ui;

import person.Player;
import person.PremiumPlan;

public class MarketMenu extends Menu {

    private final Player player;

    public MarketMenu(Player player) {
        this.player = player;

        addOption("Show Current Plan", this::showCurrentPlan);
        addOption("Upgrade to Premium", this::upgradeToPremium);
        addOption("Back to User Menu", () -> MenuManager.showMenu("USER"));
    }

    @Override
    public void showOptions() {
        System.out.println("=== MARKET ===");
        System.out.println("Here you can upgrade your plan.");
        super.showOptions();
    }

    private void showCurrentPlan() {
        System.out.println("Your current plan is: " + player.getPlan());
        MenuManager.showMenu("MARKET");
    }

    private void upgradeToPremium() {

        if (player.getPlan().isPremium()) {
            System.out.println("You already have the Premium Plan!");
            MenuManager.showMenu("MARKET");
        }

        player.upgradeToPremium(new PremiumPlan());
        System.out.println("🎉 You upgraded to Premium Plan successfully!");
        System.out.println("Your new plan is: " + player.getPlan());
        MenuManager.showMenu("MARKET");
    }
}
