package csc200.louie;

public class UserSettings {
    private int points;
    private int level;
    private float progress;
    private int pointsCount;

    public UserSettings(int pointsCount) {
        this.pointsCount = pointsCount;
        this.level = 1;
        this.progress = 0.1f;
    }

    public void setPointsCount(int newCount) {
        this.pointsCount = newCount;
    }

    public void addPoints() {
        this.points += pointsCount;
    }

    public void addLevel(int level) {
        this.level += level;
    }

    public void addProgress(float progress) {
        this.progress += progress;
    }

    public int getPoints() {
        return this.points;
    }

    public int getLevel() {
        return this.level;
    }

    public float getProgress() {
        return this.progress;
    }

    public float getProgressIn100() {
        return this.progress * 100;
    }

    @Override
    public String toString() {
        return "Player points: " + this.points + "\n" +
                "Player level: " + this.level + "\n" +
                "Player Progress: " + this.getProgressIn100() + "%\n";
    }
}
