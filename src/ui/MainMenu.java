package ui;

import blackjack.Game;
import utils.UserManager;

public class MainMenu extends Menu{
    private final Game game;

    public MainMenu(Game game){
        this.game = game;
        addOption("Login", this::login);
        addOption("Create a New Account", this::newAccount);
        addOption("Exit", this::exit);
    }

    @Override
    public void showOptions(){
        UI.printWelcomeMessage();
        super.showOptions();
    }

    public void login(){
        System.out.println("Enter your username:");
        String name = getScanner().nextLine();
        System.out.println("Enter your password:");
        String password = getScanner().nextLine();
        String correctPassword = UserManager.getUserAndPassword().get(name);
        if (correctPassword != null && correctPassword.equals(password)) {
            System.out.println("Login successful!");
            game.getPlayer().setUserName(name);
            MenuManager.showMenu("USER");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void newAccount() {
        System.out.println("Choose a username:");
        String username = getScanner().nextLine();

        if (UserManager.getUserAndPassword().containsKey(username)) {
            System.out.println("This username is already taken.");
            return;
        }

        System.out.println("Choose a password:");
        String password = getScanner().nextLine();

        UserManager.addUser(username, password);
        System.out.println("Account created successfully!");
        MenuManager.showMenu("MAIN");
    }


    public void exit(){
        System.exit(0);
    }
}
