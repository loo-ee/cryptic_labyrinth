package csc200.louie;

import java.util.*;
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

            if (!isTimeUp.get()) {
                timer.cancel();
            }

            if (Objects.equals(currentAnswer, answer) && !isTimeUp.get()) {
                SystemSettings.getLoggedInUser().playerAnsweredCorrect();
                SystemSettings.getLoggedInUser().getSettings().setAnswer(questionLevel, currentQuestionNumber, answer);
            } else if (!Objects.equals(currentAnswer, answer) && !Objects.equals(answer, "")) {
                SystemSettings.getLoggedInUser().getSettings().setAnswer(questionLevel, currentQuestionNumber, "[Answered wrong] " + answer);
            } else if (isTimeUp.get()) {
                SystemSettings.getLoggedInUser().getSettings().setAnswer(questionLevel, currentQuestionNumber, "[Ran out of time]");
            }

            currentQuestionNumber += 1;

            if (currentQuestionNumber == 10) {
                currentQuestionNumber = 0;
                questionLevel += 1;
                timerDuration /= 2;
                System.out.println();
            }

            if (questionLevel == 3)
                isGameFinished = true;

            timer.cancel();
            timer.purge();
        }

        User currentUser = SystemSettings.getLoggedInUser();
        System.out.println("[RESULTS]");
        System.out.println(currentUser.getSettings());
        System.out.println("\n[ANSWERS]: ");

        String[][] userAnswers = SystemSettings.getLoggedInUser().getSettings().getAnswers();

        for (int i = 0; i < userAnswers.length; i++) {
            String[] currentSet = userAnswers[i];
            for (int k = 0; k < currentSet.length; k++) {
                System.out.println("\n" + Questionnaire.getQuizQuestionsAndAnswers()[i][k][0]);
                System.out.println("User answer: " + currentSet[k]);
                System.out.println("Correct answer: " + Questionnaire.getQuizQuestionsAndAnswers()[i][k][1]);
            }
        }

        System.out.println("\n[GAME FINISHED]");
        System.out.println("Thank you for playing!");

        System.out.println("Programmed by Jann Louie Q. Almirante");
        System.out.println("Section A");
        System.out.println("CSC 200 - Object Oriented Design and Programming");
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
                
                "Cryptic Labyrinth: The Quest for the Lost Key" is an immersive text-based quiz game. The riddles
                and questions are all about cryptography and algorithms. Beware adventurer as this labyrinth is like
                no other!
                            
                    Game Mechanics:
                        Points System: At the outset, you start with 0 points. A timer will be present and
                        correctly answering questions will earn you points. But beware of the timer! The timer
                        gradually shortens in each round.
                                
                   Set 1: Easy
                        This round is the easiest and only accepts "Yes" or "No".
                       
                   Set 2: Medium
                        This round is a bit harder than the previous. It is an identification type of answering.
                        Only accepts one word for an answer and the first letter must be capitalized.
                        
                   Set 3:
                        This round is the hardest and also has the shortest amount of time to answer.
                        It is an identification type of answering. Only accepts one word for an answer
                        and the first letter must be capitalized.
                """;

        System.out.println(welcomeMessage);
    }
}