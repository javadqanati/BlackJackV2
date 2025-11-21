package person;

import blackjack.Deck;

public class Player extends Person {

    private Plan plan = new NormalPlan();
    private String password;
    private String userName;

    public Player(String name) {
        super(name);
    }

    public void makeDecision(boolean hit, Deck deck, Deck discard) {
        if (hit) {
            this.hit(deck, discard);
        }
    }

    public void hitPlayer(Deck deck, Deck discard) {
        this.hit(deck, discard);
    }
    public Plan getPlan() {
        return plan;
    }
    public void upgradeToPremium(Plan plan) {
        this.plan = plan;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
