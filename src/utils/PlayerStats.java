package utils;

public class PlayerStats {
    private int wins;
    private int losses;
    private int pushes;

    public PlayerStats(int wins, int losses, int pushes) {
        this.wins = wins;
        this.losses = losses;
        this.pushes = pushes;
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
        return new PlayerStats(
                Integer.parseInt(p[0]),
                Integer.parseInt(p[1]),
                Integer.parseInt(p[2])
        );
    }
}
