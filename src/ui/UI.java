package ui;
import person.Person;

public class UI{

    public static void printWelcomeMessage(){
        System.out.println("Welcome to Blackjack!");
        System.out.println("---------------------");
    }



    public static void printGoodbyeMessage(){
        System.out.println("Thanks for playing Blackjack! Goodbye!");
    }

    public static void printHand(Person person){
        System.out.println(person.getType() + "'s hand looks like this:");
        System.out.println(person.getHand() + " Valued at: " + 
        person.getHand().calculatedValue());
    }

    public static void printHitMessage(String name){
        System.out.println(name + " gets a card");
    }

    public static void startRound() {
        System.out.println("Round started!");
        MenuManager.showMenu("PLAY");
    }
}