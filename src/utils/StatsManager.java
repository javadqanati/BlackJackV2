package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StatsManager {

    private static final String FILE_NAME = "stats.txt";
    private static Map<String, PlayerStats> stats = new HashMap<>();

    // Load stats from file
    public static void loadStats() {
        stats.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                // format: username wins,losses,pushes
                String[] parts = line.split(" ");
                String username = parts[0];
                PlayerStats stat = PlayerStats.fromString(parts[1]);
                stats.put(username, stat);
            }
        } catch (IOException ignored) {}
    }

    // Save all stats to file
    public static void saveStats() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String user : stats.keySet()) {
                pw.println(user + " " + stats.get(user));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PlayerStats getStats(String username) {
        return stats.computeIfAbsent(username, k -> new PlayerStats(0,0,0));
    }

    public static void addWin(String username) {
        getStats(username).addWin();
        saveStats();
    }

    public static void addLoss(String username) {
        getStats(username).addLoss();
        saveStats();
    }

    public static void addPush(String username) {
        getStats(username).addPush();
        saveStats();
    }
}
