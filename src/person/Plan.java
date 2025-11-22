package person;

public interface Plan {
    boolean isPremium();
    String getName();
    int getDailyBonus();
    int getMaxGamesPerDay();
    boolean hasAds();
    boolean hasAdvancedStats();
}
