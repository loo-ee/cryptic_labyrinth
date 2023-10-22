package csc200.louie;

public class User {
    private String username;
    private String password;
    private final UserSettings settings;

    public User(String name, String password, int pointsCount) {
        this.username = name;
        this.password = password;
        this.settings = new UserSettings(pointsCount);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UserSettings getSettings() {
        return this.settings;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void playerAnsweredCorrect() {
        this.settings.addLevel(1);
        this.settings.addPoints();
        this.settings.addProgress(0.10f);
    }

    public void showUserStats() {
        System.out.println("Name: " + this.username + "\n");
        System.out.println(this.settings);
    }
}
