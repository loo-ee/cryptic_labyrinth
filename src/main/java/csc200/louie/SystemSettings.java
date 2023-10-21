package csc200.louie;

import java.util.ArrayList;
import java.util.Objects;

public class SystemSettings {
    private static User loggedInUser = null;
    private static ArrayList<User> users = new ArrayList<>();

    public static void loginUser(User user) {
        SystemSettings.loggedInUser = user;
    }

    public static void logoutUser() {
        SystemSettings.loggedInUser = null;
    }

    public static User getLoggedInUser() {
        return SystemSettings.loggedInUser;
    }

    public static void addUser(User user) {
        SystemSettings.users.add(user);
    }

    public static ArrayList<User> getUsers() {
        return SystemSettings.users;
    }

    public static User findUser(String username, String password) {
        for (User user: SystemSettings.users) {
            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password))
                return user;
        }

        return null;
    }
}
