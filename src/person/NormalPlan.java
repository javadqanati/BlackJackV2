package person;

public class NormalPlan implements Plan{
    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getDailyBonus() {
        return 0;
    }

    @Override
    public String toString() {
        return "Normal Plan";
    }


    @Override
    public int getMaxGamesPerDay() {
        return 0;
    }

    @Override
    public boolean hasAds() {
        return false;
    }

    @Override
    public boolean hasAdvancedStats() {
        return false;
    }
}
