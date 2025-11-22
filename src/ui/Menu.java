package ui;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private Map<String, Runnable> options = new LinkedHashMap<>();

    public void addOption(String name, Runnable action) {
        this.options.put(name, action);
    }
    public void showOptions() {
        System.out.println("=== MENU OPTIONS ===");
        int i = 1;
        for (String key : options.keySet()) {
            System.out.println(i + ") " + key);
            i++;
        }
        chooseOption();
    }
    public void chooseOption() {
        System.out.println("\nChoose an option (enter number):");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }

        int index = 1;
        for (String name : options.keySet()) {
            if (index == choice) {
                doAction(name);
                return;
            }
            index++;
        }

        System.out.println("Invalid option number.");
    }
    public void doAction(String name) {
        Runnable action = options.get(name);

        if (action == null) {
            System.out.println("Invalid option: " + name);
            return;
        }

        action.run();
    }
    public Map<String, Runnable> getOptions() {
        return options;
    }
    public void setOptions(Map<String, Runnable> options) {
        this.options = options;
    }
    public Scanner getScanner() {
        return scanner;
    }
}
