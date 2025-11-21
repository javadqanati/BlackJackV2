package blackjack;
import person.Dealer;
import person.Player;
import ui.*;
import utils.RoundManager;
import utils.StatsManager;
import utils.UserManager;

public class Game {

    private final Deck deck;
    private final Deck discarded;
    private final Dealer dealer;
    private final Player player;
    private final RoundManager roundManager;

    public Game() {
        UserManager.loadUsers();
        StatsManager.loadStats();
        roundManager = new RoundManager(this);
        deck = new Deck(true);
        discarded = new Deck();
        dealer = new Dealer("Dealer");
        player = new Player("Player");

        MenuManager.initMenus(this);
        MenuManager.showMenu("MAIN");
    }

    public void startGame() {
        deck.shuffle();
        roundManager.startRound();
    }

    public void endGame() {
        UI.printGoodbyeMessage();
    }
    public Player getPlayer() {
        return player;
    }
    public Dealer getDealer() {
        return dealer;
    }
    public Deck getDeck() {
        return deck;
    }
    public Deck getDiscarded() {
        return discarded;
    }

    public RoundManager getRoundManager() {
        return roundManager;
    }
}