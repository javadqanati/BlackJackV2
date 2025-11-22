package utils;

public class PlayerStats {
    private int wins;
    private int losses;
    private int pushes;

    public PlayerStats(double wins, double losses, double pushes) {
        this.wins = (int) wins;
        this.losses = (int) losses;
        this.pushes = (int) pushes;
    }

    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getPushes() { return pushes; }
    public void addWin()  { wins++; }
    public void addLoss() { losses++; }
    public void addPush() { pushes++; }

    @Override
    public String toString() {
        return wins + "," + losses + "," + pushes;
    }

    public static PlayerStats fromString(String line) {
        String[] p = line.split(",");

        int wins   = StatsManager.toInt(p[0]);
        int losses = StatsManager.toInt(p[1]);
        int pushes = StatsManager.toInt(p[2]);

        return new PlayerStats(wins, losses, pushes);
    }

}
