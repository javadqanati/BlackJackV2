package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static final String FILE_NAME = "users.txt";
    private static Map<String, String> userAndPassword = new HashMap<>();

    public static Map<String, String> getUserAndPassword() {
        return userAndPassword;
    }

    public static void addUser(String username, String password) {
        userAndPassword.put(username, password);
        saveUsers();
    }

    // Save to file
    public static void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String user : userAndPassword.keySet()) {
                pw.println(user + " " + userAndPassword.get(user));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load from file
    public static void loadUsers() {
        userAndPassword.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                userAndPassword.put(parts[0], parts[1]);
            }
        } catch (IOException ignored) {}
    }
}
