package ui;

import blackjack.Game;

public class UserMenu extends Menu{
    private static Game game;

    public UserMenu(Game game){
        UserMenu.game = game;
        addOption("Start the Game", game::startGame);
        addOption("View Your Statistics", () -> MenuManager.showMenu("STATS"));
        addOption("Back to Main Menu:", () -> MenuManager.showMenu("MAIN"));
    }

    @Override
    public void showOptions() {
        printPlayerMessage();
        super.showOptions();
    }

    public static void printPlayerMessage(){
        System.out.println("Welcome to Blackjack!   " + game.getPlayer().getUserName());
        System.out.println("Your current plan is:   " + game.getPlayer().getPlan().toString());
        System.out.println("---------------------");
    }
}
