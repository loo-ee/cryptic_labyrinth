package csc200.louie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final ArrayList<User> users = SystemSettings.getUsers();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        welcomeScreen();
    }

    private static void login() {
        if (users.isEmpty()) {
            System.out.println("[CREATE AN ACCOUNT]");
            System.out.print("Enter user name: ");
            String name = scanner.nextLine();

            while (true) {
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Confirm password: ");
                String confirmPass = scanner.nextLine();

                if (!Objects.equals(password, confirmPass))
                    continue;

                System.out.print("Enter how many points to be given per correct answer: ");
                int points = Integer.parseInt(scanner.nextLine());

                User newUser = new User(name, password, points);
                users.add(newUser);
                break;
            }

            System.out.println("[ACCOUNT CREATION SUCCESSFUL]");
        }

        while (true) {
            System.out.println("[LOGIN ACCOUNT]");
            System.out.print("Enter username: ");
            String usernameInput = scanner.nextLine();
            System.out.print("Enter password: ");
            String passInput = scanner.nextLine();
            User foundUser = SystemSettings.findUser(usernameInput, passInput);

            if (foundUser == null)
                continue;

            System.out.println("[WELCOME " + usernameInput + "]");
            SystemSettings.loginUser(foundUser);
            break;
        }

        String choices = """
                [1] Play game
                [2] Change user password
                [3] Change points given
                [0] Quit
                """;

        System.out.println(choices);
        System.out.print("Enter choice here: ");
        char choice = scanner.next().charAt(0);
        scanner.nextLine();

        switch (choice) {
            case '1' -> System.out.println("Now playing game...");

            case '2' -> {
                System.out.print("Enter old password: ");
                String oldPass = scanner.nextLine();

                if (Objects.equals(SystemSettings.getLoggedInUser().getPassword(), oldPass)) {
                    System.out.print("Enter new password: ");
                    String newPass = scanner.nextLine();
                    System.out.print("Confirm password: ");
                    String confirmPass = scanner.nextLine();

                    if (Objects.equals(newPass, confirmPass)) {
                        SystemSettings.getLoggedInUser().setPassword(newPass);
                        System.out.println("[PASSWORD CHANGED SUCCESSFULLY]");
                    }
                } else {
                    System.out.println("[TRY AGAIN]");
                }
            }


        }
    }

    private static void welcomeScreen() {
        String welcomeMessage = """
                \t\t\t\t\t\tWELCOME TO CRYPTIC LABYRINTH!
                
                "Cryptic Labyrinth: The Quest for the Lost Key" is an immersive text-based adventure
                that plunges you into the depths of a mysterious labyrinth, concealed within the heart
                of a forgotten realm. Your mission is to recover the fabled Lost Key, a powerful artifact
                said to unlock unimaginable secrets. But beware, this labyrinth is riddled with enigmatic
                puzzles and cunning traps. Your every choice and your ability to solve perplexing riddles
                will determine your success.
                            
                    Game Mechanics:
                                
                        Exploration: You'll navigate through a series of interconnected rooms, each with
                        its unique challenges and mysteries. Use simple text commands to move from one room
                        to another, like "go north" or "enter the chamber."
                                
                        Points System: At the outset, you start with 0 points. Correctly answering riddles
                        and quizzes earns you 10 points, while incorrect responses deduct 5 points. To
                        unlock the secret chamber, you must amass a total of 50 points.
                                
                        Riddles and Quizzes: Throughout the labyrinth, you'll encounter riddles and quizzes
                        that demand your wit and knowledge. To answer, simply type your response. A correct
                        answer brings you closer to victory, while a wrong answer may challenge your progress.
                                
                        Decision-Making: The game's narrative is influenced by your choices. Be prepared to
                        make crucial decisions that can impact your point total and the direction of the story.
                                
                        Objective: Your ultimate objective is to collect enough points to unlock the secret
                        chamber and recover the Lost Key. Your decisions, puzzle-solving skills, and knowledge
                        will shape your journey and determine the outcome.
                """;

        System.out.println(welcomeMessage);
    }
}