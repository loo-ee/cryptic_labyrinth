package csc200.louie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static final ArrayList<User> users = SystemSettings.getUsers();
    private static final AtomicBoolean isTimeUp = new AtomicBoolean(false);
    private static final Scanner scanner = new Scanner(System.in);
    private static final boolean isGameWon = false;
    private static boolean isGameFinished = false;
    private static int currentQuestionNumber = 0;
    private static int timerDuration = 15;
    private static int questionLevel = 0;
    private final static String[] levels = {"Easy", "Medium", "Hard"};


    public static void main(String[] args) {
        login();
        welcomeScreen();

        System.out.println("Get ready player...");
        System.out.println("Press enter...");
        scanner.nextLine();

        while (!isGameFinished) {
            Timer timer = new Timer();
            TimerTask task = new PrintTask(isTimeUp);
            String currentQuestion = Questionnaire.getQuizQuestionsAndAnswers()[questionLevel][currentQuestionNumber][0];
            String currentAnswer = Questionnaire.getQuizQuestionsAndAnswers()[questionLevel][currentQuestionNumber][1];
            String answer;

            timer.schedule(task, timerDuration * 1000L);
            System.out.println(levels[questionLevel] + ": " + currentQuestion);
            System.out.print(">> ");
            answer= scanner.nextLine();

            currentQuestionNumber += 1;

            if (!isTimeUp.get()) {
                isTimeUp.set(false);
                timer.cancel();
            }

            if (Objects.equals(currentAnswer, answer) && !isTimeUp.get()) {
                SystemSettings.getLoggedInUser().playerAnsweredCorrect();
            }

            if (currentQuestionNumber == 10) {
                currentQuestionNumber = 0;
                questionLevel += 1;
                timerDuration /= 2;
                System.out.println();
            }

            if (questionLevel == 3)
                isGameFinished = true;
        }

        User currentUser = SystemSettings.getLoggedInUser();
        System.out.println("[RESULTS]");
        System.out.println(currentUser.getSettings());
    }

    private static void login() {
        boolean isMenuRunning = true;

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
            System.out.println("\n[LOGIN ACCOUNT]");
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

        while (isMenuRunning) {
            String choices = """
                \n[1] Play game
                [2] Change user password
                [3] Change points given
                [0] Quit
                """;

            System.out.println(choices);
            System.out.print("Enter choice here: ");
            char choice = scanner.next().charAt(0);
            scanner.nextLine();

            switch (choice) {
                case '0' -> isMenuRunning = false;

                case '1' -> {
                    System.out.println("Now playing game...");
                    isMenuRunning = false;
                }

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

                case '3' -> {
                    System.out.print("Enter new points to be given: ");
                    int newPoints = Integer.parseInt(scanner.nextLine());

                    SystemSettings.getLoggedInUser().getSettings().setPointsCount(newPoints);
                    System.out.println("[POINTS COUNT CHANGED]");
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