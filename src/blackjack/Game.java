package blackjack;
import utils.RoundManager;
import utils.UI;

public class Game {

    //Declare variables needed for Game class
    private Deck deck, discarded;
    private Dealer dealer;
    private Player player;
    private final RoundManager roundManager;
    

    /**
     * Constructor for Game, creates our variables 
     */
    public Game() {
        roundManager = new RoundManager(this);
        UI.printWelcomeMessage();
        //Create a new deck with 52 cards
        deck = new Deck(true);
        //Create a new empty deck
        discarded = new Deck();

        //Create the People
        dealer = new Dealer("Dealer");
        player = new Player("Player");
    }

    /**
     * starts the game
     */
    public void startGame() {
        deck.shuffle();
        roundManager.startRound();
    }

    /**
     * Ends the game with a goodbye message
     */
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
}